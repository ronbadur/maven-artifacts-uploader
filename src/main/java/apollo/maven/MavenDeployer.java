package apollo.maven;

import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import java.nio.file.Path;
import java.util.Collections;

public class MavenDeployer {

    private final MavenCommandFactory mavenCommandFactory;
    private final InvocationRequest invocationRequest;
    private final Invoker invoker;
    private final GAVFactory gavFactory;
    private static final Logger logger = LogManager.getLogger(MavenDeployer.class);

    @Inject
    public MavenDeployer(MavenCommandFactory mavenCommandFactory, InvocationRequest invocationRequest,
                         Invoker invoker, GAVFactory gavFactory) {
        this.mavenCommandFactory = mavenCommandFactory;
        this.invocationRequest = invocationRequest;
        this.invoker = invoker;
        this.gavFactory = gavFactory;
    }

    public void deployArtifact(Path pathToPom) {
        String commandToExecute = mavenCommandFactory.getMavenDeployCommand(pathToPom);
        GAV gav = gavFactory.createGAV(pathToPom);
        logger.debug("Executing command - " + commandToExecute);
        invocationRequest.setGoals(Collections.singletonList(commandToExecute));

        try {
            InvocationResult invocationResult = invoker.execute(invocationRequest);

            if (invocationResult.getExitCode() != 0){
                System.out.println("Failed");
            } else {
                logger.info(gav.getArtifactId() + "-" + gav.getVersion() + " uploaded successfully");
            }
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }
}
