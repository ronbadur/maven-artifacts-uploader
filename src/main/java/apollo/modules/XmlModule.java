package apollo.modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

import java.util.List;

public class XmlModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<List<String>>(){}).toProvider(TagsToRemoveProvider.class);
        bind(String.class).annotatedWith(Names.named("tagToChange")).toInstance("packaging");
        bind(String.class).annotatedWith(Names.named("newValue")).toInstance("jar");
    }
}
