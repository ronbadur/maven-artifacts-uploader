package apollo.deploy_options;

import apollo.maven.MavenCommand;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.nio.file.Path;
import java.util.Optional;

public class RepositoryIdOption implements MavenCommand {

    private final String repositoryIdOption;

    @Inject
    public RepositoryIdOption(@Named("repositoryIdOption") String repositoryIdOption) {
        this.repositoryIdOption = repositoryIdOption;
    }

    @Override
    public Optional<String> getCommand(Path pathToPom) {
        return Optional.of(repositoryIdOption);
    }
}
