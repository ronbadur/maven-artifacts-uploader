package apollo.deploy_options;

import apollo.maven.MavenDeployOption;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.nio.file.Path;
import java.util.Optional;

public class UrlDeployOption implements MavenDeployOption {

    private final String urlOption;

    @Inject
    public UrlDeployOption(@Named("urlOption")String urlOption) {
        this.urlOption = urlOption;
    }

    @Override
    public Optional<String> getCommandOption(Path pathToPom) {
        return Optional.of(urlOption);
    }
}
