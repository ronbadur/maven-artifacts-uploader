package apollo.deploy_options;

import apollo.maven.MavenCommand;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.nio.file.Path;
import java.util.Optional;

public class PomDeployOption implements MavenCommand {

    private final String pomFileCommandOption;

    @Inject
    public PomDeployOption(@Named("pomOption") String pomFileCommandOption) {
        this.pomFileCommandOption = pomFileCommandOption;
    }

    @Override
    public Optional<String> getCommand(Path pathToPom) {
        return Optional.of(pomFileCommandOption + pathToPom);
    }
}
