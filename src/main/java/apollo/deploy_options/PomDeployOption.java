package apollo.deploy_options;

import apollo.maven.MavenDeployOption;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.nio.file.Path;
import java.util.Optional;

public class PomDeployOption implements MavenDeployOption {

    private final String pomFileCommandOption;

    @Inject
    public PomDeployOption(@Named("pomOption") String pomFileCommandOption) {
        this.pomFileCommandOption = pomFileCommandOption;
    }

    @Override
    public Optional<String> getCommandOption(Path pathToPom) {
        return Optional.of(pomFileCommandOption + pathToPom);
    }
}
