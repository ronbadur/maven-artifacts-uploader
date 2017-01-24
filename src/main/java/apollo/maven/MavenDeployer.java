package apollo.maven;

import com.google.inject.Inject;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MavenDeployer {

    private final MavenCommandFactory mavenCommandFactory;
    private final Invoker invoker;

    @Inject
    public MavenDeployer(MavenCommandFactory mavenCommandFactory, Invoker invoker) {
        this.mavenCommandFactory = mavenCommandFactory;
        this.invoker = invoker;
    }

    public void deployArtifact(Path pathToPom) {
        List<Optional<String>> mavenDeployCommandsForArtifact = mavenCommandFactory.getMavenDeployCommandsForArtifact(pathToPom);
        Optional<Optional<String>> reduce = mavenDeployCommandsForArtifact.stream().filter(Optional::isPresent).reduce((x, y) -> Optional.of(x.get() + " " + y.get()));
        InvocationRequest invocationRequest = new DefaultInvocationRequest();
        invocationRequest.setGoals(Collections.singletonList("deploy:deploy-file " + reduce.get().get()));
        try {
            invoker.execute(invocationRequest);
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }
}
