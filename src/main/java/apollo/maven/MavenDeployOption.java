package apollo.maven;

import java.nio.file.Path;
import java.util.Optional;

@FunctionalInterface
public interface MavenDeployOption {

    Optional<String> getCommandOption(Path pathToPom);
}
