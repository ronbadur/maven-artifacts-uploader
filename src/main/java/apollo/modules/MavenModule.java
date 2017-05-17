package apollo.modules;

import apollo.maven.MavenDeployOption;
import apollo.upload.MavenUploader;
import apollo.upload.Uploader;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;

import java.util.List;
import java.util.StringJoiner;

public class MavenModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Uploader.class).to(MavenUploader.class);
        bind(new TypeLiteral<List<MavenDeployOption>>(){}).toProvider(MavenCommandsProvider.class);
        bind(Invoker.class).to(DefaultInvoker.class);
        bind(InvocationRequest.class).to(DefaultInvocationRequest.class);
        bind(String.class).annotatedWith(Names.named("deploy-start-command")).toInstance("deploy:deploy-file ");
    }
}
