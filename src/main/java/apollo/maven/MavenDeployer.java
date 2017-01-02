package apollo.maven;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class MavenDeployer {

    private MavenCommandFactory mavenCommandFactory = new MavenCommandFactory();

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
