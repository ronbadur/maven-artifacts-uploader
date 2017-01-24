package apollo.deploy_options;

import apollo.maven.MavenCommand;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.nio.file.Path;
import java.util.Optional;

public class UrlDeployOption implements MavenCommand {

    private final String urlOption;

    @Inject
    public UrlDeployOption(@Named("urlOption")String urlOption) {
        this.urlOption = urlOption;
    }

    @Override
    public Optional<String> getCommand(Path pathToPom) {
        return Optional.of(urlOption);
    }
}
