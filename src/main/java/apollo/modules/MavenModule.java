package apollo.modules;

import apollo.maven.MavenDeployOption;
import apollo.upload.MavenUploader;
import apollo.upload.Uploader;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.Invoker;

import java.util.List;

public class MavenModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Uploader.class).to(MavenUploader.class);
        bind(new TypeLiteral<List<MavenDeployOption>>(){}).toProvider(MavenCommandsProvider.class);
        bind(Invoker.class).to(DefaultInvoker.class);
    }
}
