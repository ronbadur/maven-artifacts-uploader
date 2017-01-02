package apollo.maven;

import java.nio.file.Path;
import java.util.Optional;

public class PomDeployCommand implements MavenCommand {

    @Override
    public Optional<String> getCommand(Path pathToPom) {
        return null;
    }
}
