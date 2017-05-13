package apollo.deploy_options;

import apollo.maven.MavenDeployOption;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.nio.file.Path;
import java.util.Optional;

public class RepositoryIdOption implements MavenDeployOption {

    private final String repositoryIdOption;

    @Inject
    public RepositoryIdOption(@Named("repositoryIdOption") String repositoryIdOption) {
        this.repositoryIdOption = repositoryIdOption;
    }

    @Override
    public Optional<String> getCommandOption(Path pathToPom) {
        return Optional.of(repositoryIdOption);
    }
}
