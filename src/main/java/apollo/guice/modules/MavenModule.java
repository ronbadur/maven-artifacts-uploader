package apollo.guice.modules;

import apollo.guice.providers.MavenCommandsProvider;
import apollo.maven.MavenCommand;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import java.util.List;

public class MavenModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<List<MavenCommand>>(){}).toProvider(MavenCommandsProvider.class);
    }
}
