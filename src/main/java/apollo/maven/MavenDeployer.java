package apollo.maven;

import com.google.inject.Inject;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class MavenDeployer {

    private final MavenCommandFactory mavenCommandFactory;

    @Inject
    public MavenDeployer(MavenCommandFactory mavenCommandFactory) {
        this.mavenCommandFactory = mavenCommandFactory;
    }

    public void deployArtifact(Path pathToPom) {
        List<Optional<String>> mavenDeployCommandsForArtifact = mavenCommandFactory.getMavenDeployCommandsForArtifact(pathToPom);
        mavenDeployCommandsForArtifact.stream().filter(Optional::isPresent).forEach(this::runCommand);
    }

    private void runCommand(Optional<String> deployCommand) {
        try{
            Runtime.getRuntime().exec(deployCommand.get());
        } catch (IOException ex){
            throw new UncheckedIOException(ex.getMessage(), ex);
        }
    }
}
