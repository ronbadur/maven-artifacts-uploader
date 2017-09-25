package apollo.maven;

import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.shared.invoker.*;

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
        /*
            -client: The Client VM compiler does not try to execute many of the more complex optimizations performed by the compiler in the Server VM, but in exchange, it requires less time to analyze and compile a piece of code. This means the Client VM can start up faster and requires a smaller memory footprint.
            -XX:+TieredCompilation -XX:TieredStopAtLevel=1: Perform the basic just-in-time compilation of the code only, this results in faster start-up
            -Xverify:none: disables class file validation (security/stability feature) and results in a faster start-up time
         */
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
            logger.info("failed to upload - " + gav.toString() + " reason:" + invocationResult.getExecutionException().getMessage());
        } else {
            logger.info(gav.toString() + " uploaded successfully");
        }
    }
}
