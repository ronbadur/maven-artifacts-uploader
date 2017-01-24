package apollo.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class MavenCommandOptionsModule extends AbstractModule {

    private static final String REPOSITORY_ID = "nexus";
    private static final String REPOSITORY_URL = "http://localhost:8081/repository/maven-releases/";


    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("pomOption")).toInstance("-DpomFile=");
        bind(String.class).annotatedWith(Names.named("fileOption")).toInstance("-Dfile=");
        bind(String.class).annotatedWith(Names.named("repositoryIdOption")).toInstance("-DrepositoryId=" + REPOSITORY_ID);
        bind(String.class).annotatedWith(Names.named("urlOption")).toInstance("-Durl=" + REPOSITORY_URL);
        bind(String.class).annotatedWith(Names.named("packagingOption")).toInstance("-Dpackaging=pom");
        bind(String.class).annotatedWith(Names.named("sourcesOption")).toInstance("-Dsources=");
        bind(String.class).annotatedWith(Names.named("javadocOption")).toInstance("-Djavadoc=");
    }
}
