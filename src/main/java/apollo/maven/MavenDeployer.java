package apollo.maven;

import apollo.Main;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import java.io.InputStream;
import java.io.PipedInputStream;
import java.nio.file.Path;
import java.util.Collections;

public class MavenDeployer {

    private final MavenCommandFactory mavenCommandFactory;
    private final InvocationRequest invocationRequest;
    private final Invoker invoker;
    private static final Logger logger = LogManager.getLogger(Main.class);

    @Inject
    public MavenDeployer(MavenCommandFactory mavenCommandFactory, InvocationRequest invocationRequest,
                         Invoker invoker) {
        this.mavenCommandFactory = mavenCommandFactory;
        this.invocationRequest = invocationRequest;
        this.invoker = invoker;
    }

    public void deployArtifact(Path pathToPom) {
        String commandToExecute = mavenCommandFactory.getMavenDeployCommand(pathToPom);
        logger.info("Executing command - " + commandToExecute);
        invocationRequest.setGoals(Collections.singletonList(commandToExecute));

        try {
            InvocationResult invocationResult = invoker.execute(invocationRequest);

            if (invocationResult.getExitCode() != 0){
                System.out.println("Failed");
            } else {
                logger.info(pathToPom.toString() + " uploaded successfully");
            }
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }
}
