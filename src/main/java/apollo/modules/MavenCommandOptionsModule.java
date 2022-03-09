package apollo.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class MavenCommandOptionsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("pomOption")).toInstance("-DpomFile=");
        bind(String.class).annotatedWith(Names.named("fileOption")).toInstance("-Dfile=");
        bind(String.class).annotatedWith(Names.named("repositoryIdOption")).toProvider(RepositoryIdProvider.class);
        bind(String.class).annotatedWith(Names.named("urlOption")).toProvider(RepositoryUrlProvider.class);
        bind(String.class).annotatedWith(Names.named("packagingOption")).toInstance("-Dpackaging=pom");
        bind(String.class).annotatedWith(Names.named("sourcesOption")).toInstance("-Dsources=");
        bind(String.class).annotatedWith(Names.named("javadocOption")).toInstance("-Djavadoc=");
        bind(String.class).annotatedWith(Names.named("filesOption")).toInstance("-Dfiles=");
        bind(String.class).annotatedWith(Names.named("classifiersOption")).toInstance("-Dclassifiers=");
        bind(String.class).annotatedWith(Names.named("typesOption")).toInstance("-Dtypes=");
    }
}
