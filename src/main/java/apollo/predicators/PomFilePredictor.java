package apollo.predicators;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Path;
import java.util.function.Predicate;

public class PomFilePredictor implements Predicate<Path> {

    @Override
    public boolean test(Path path) {
        String extension = FilenameUtils.getExtension(path.getFileName().toString());
        return extension.equals("pom");
    }
}
