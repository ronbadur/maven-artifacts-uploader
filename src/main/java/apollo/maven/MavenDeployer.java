package apollo.maven;

import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.shared.invoker.*;

import java.io.PrintStream;
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
        invocationRequest.setMavenOpts("-client -XX:+TieredCompilation -XX:TieredStopAtLevel=1 -Xverify:none");
        MavenInvokerOutputHandler outputHandler = new MavenInvokerOutputHandler();
        invoker.setOutputHandler(outputHandler);

        try {
            InvocationResult invocationResult = invoker.execute(invocationRequest);

            writeResultToLog(gav, outputHandler, invocationResult);
        } catch (MavenInvocationException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void writeResultToLog(GAV gav, MavenInvokerOutputHandler outputHandler, InvocationResult invocationResult) {
        if (outputHandler.isArtifactExist()){
            logger.info(gav.toString() + " already exists in nexus");
        } else if (invocationResult.getExitCode() != 0){
            logger.info("failed to upload - " + gav.toString());
        } else {
            logger.info(gav.toString() + " uploaded successfully");
        }
    }
}
