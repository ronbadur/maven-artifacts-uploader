package apollo.maven;

import java.nio.file.Path;
import java.util.Optional;

public class SourcesDeployCommand implements MavenCommand {

    @Override
    public Optional<String> getCommand(Path pathToPom) {
        return null;
    }
}
