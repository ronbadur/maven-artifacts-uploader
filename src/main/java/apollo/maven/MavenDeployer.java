package apollo.maven;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;

public class MavenDeployer {

    private MavenCommandFactory mavenCommandFactory = new MavenCommandFactory();

    public void deployPom(Path pathToPom) {
        String deployCommand = mavenCommandFactory.createPomDeployCommand(pathToPom);
//        runCommand(deployCommand);
        System.out.println(deployCommand);
    }

    private void runCommand(String deployCommand) {
        try{
            Runtime.getRuntime().exec(deployCommand);
        } catch (IOException ex){
            throw new UncheckedIOException(ex.getMessage(), ex);
        }
    }
}
