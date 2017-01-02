package apollo.maven;

import java.nio.file.Path;
import java.util.Optional;

@FunctionalInterface
public interface MavenCommand {

    Optional<String> getCommand(Path pathToPom);
}
