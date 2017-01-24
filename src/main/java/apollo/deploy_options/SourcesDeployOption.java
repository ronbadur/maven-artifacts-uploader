package apollo.deploy_options;

import apollo.maven.MavenCommand;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class SourcesDeployOption implements MavenCommand {

    private final String sourcesOption;

    @Inject
    public SourcesDeployOption(@Named("sourcesOption")String sourcesOption) {
        this.sourcesOption = sourcesOption;
    }

    @Override
    public Optional<String> getCommand(Path pathToPom) {
        Optional<String> sourcesOptional = Optional.empty();

        if (Files.exists(getSourcesPath(pathToPom))){
            sourcesOptional = Optional.of(sourcesOption + getSourcesPath(pathToPom).toString());
        }

        return sourcesOptional;
    }

    private Path getSourcesPath(Path pathToPom){
        int last = pathToPom.toString().lastIndexOf(".");
        return Paths.get(pathToPom.toString().substring(0, last) + "-sources.jar");
    }

}
