package apollo.predicators;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Path;
import java.util.function.Predicate;

public class PomFilePredictor implements Predicate<Path> {

    private static final String POM_EXTENSION = "pom";

    @Override
    public boolean test(Path path) {
        String extension = FilenameUtils.getExtension(path.getFileName().toString());
        return extension.equals(POM_EXTENSION);
    }
}
