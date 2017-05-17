package apollo.maven;

import com.google.inject.Inject;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import java.nio.file.Path;
import java.util.Collections;

public class MavenDeployer {

    private final MavenCommandFactory mavenCommandFactory;
    private final InvocationRequest invocationRequest;
    private final Invoker invoker;

    @Inject
    public MavenDeployer(MavenCommandFactory mavenCommandFactory, InvocationRequest invocationRequest,
                         Invoker invoker) {
        this.mavenCommandFactory = mavenCommandFactory;
        this.invocationRequest = invocationRequest;
        this.invoker = invoker;
    }

    public void deployArtifact(Path pathToPom) {
        String commandToExecute = mavenCommandFactory.getMavenDeployCommand(pathToPom);
        invocationRequest.setGoals(Collections.singletonList(commandToExecute));

        try {
            invoker.execute(invocationRequest);
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }
}
