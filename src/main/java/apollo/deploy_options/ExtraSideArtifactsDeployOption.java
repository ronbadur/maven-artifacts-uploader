package apollo.deploy_options;

import apollo.maven.MavenDeployOption;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExtraSideArtifactsDeployOption implements MavenDeployOption {

    private static class SideArtifact {
        private final Path file;
        private final String classifier;
        private final String type;

        private SideArtifact(Path file, String classifier, String type) {
            this.file = file;
            this.classifier = classifier;
            this.type = type;
        }
    }

    private final String filesOption;
    private final String classifiersOption;
    private final String typesOption;

    @Inject
    public ExtraSideArtifactsDeployOption(@Named("filesOption") String filesOption,
                                          @Named("classifiersOption") String classifiersOption,
                                          @Named("typesOption") String typesOption) {
        this.filesOption = filesOption;
        this.classifiersOption = classifiersOption;
        this.typesOption = typesOption;
    }

    @Override
    public Optional<String> getCommandOption(Path pathToPom) {
        String pomFileName = FilenameUtils.getName(pathToPom.getFileName().toString());
        if (!pomFileName.endsWith(".pom")) {
            throw new IllegalArgumentException("POM file name must end with .pom");
        }

        try (Stream<Path> files = Files.walk(pathToPom.getParent(), 1)) {
            String fileNamePrefix = pomFileName.substring(0, pomFileName.length() - ".pom".length()) + "-";
            List<SideArtifact> artifacts = files.filter(Files::isRegularFile)
                    .filter((path) -> path.getFileName().toString().startsWith(fileNamePrefix))
                    .filter(ExtraSideArtifactsDeployOption::notHashFile)
                    .filter(ExtraSideArtifactsDeployOption::notSignatureFile)
                    .filter(ExtraSideArtifactsDeployOption::notLastUpdatedFile)
                    .filter(ExtraSideArtifactsDeployOption::notSourcesFile)
                    .filter(ExtraSideArtifactsDeployOption::notJavadocFile)
                    .map(toArtifact(fileNamePrefix))
                    .collect(Collectors.toList());

            if (!artifacts.isEmpty()) {
                return Optional.of(String.format("%s%s %s%s %s%s",
                        filesOption, artifacts.stream().map(artifact -> artifact.file.toString()).collect(Collectors.joining(",")),
                        classifiersOption, artifacts.stream().map(artifact -> artifact.classifier).collect(Collectors.joining(",")),
                        typesOption, artifacts.stream().map(artifact -> artifact.type).collect(Collectors.joining(","))));

            } else {
                return Optional.empty();
            }

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private Function<Path, SideArtifact> toArtifact(String fileNamePrefix) {
        return path -> {
            String rest = path.getFileName().toString().substring(fileNamePrefix.length());
            int i = rest.lastIndexOf('.');
            if (i == -1) {
                throw new IllegalArgumentException("File name must end with -classifier.type");
            }
            String classifier = rest.substring(0, i);
            String type = rest.substring(i + 1);
            return new SideArtifact(path, classifier, type);
        };
    }

    private static boolean notHashFile(Path path) {
        String fileName = path.getFileName().toString();
        return !fileName.endsWith(".sha1") && !fileName.endsWith(".md5") && !fileName.endsWith(".sha256") && !fileName.endsWith(".sha512");
    }

    private static boolean notSignatureFile(Path path) {
        String fileName = path.getFileName().toString();
        return !fileName.endsWith(".asc");
    }

    private static boolean notLastUpdatedFile(Path path) {
        String fileName = path.getFileName().toString();
        return !fileName.endsWith(".lastUpdated");
    }

    private static boolean notSourcesFile(Path path) {
        String fileName = path.getFileName().toString();
        return !fileName.endsWith("-sources.jar");
    }

    private static boolean notJavadocFile(Path path) {
        String fileName = path.getFileName().toString();
        return !fileName.endsWith("-javadoc.jar");
    }

}
