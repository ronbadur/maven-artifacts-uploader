package apollo.modules;

import com.google.inject.Provider;

import java.util.Arrays;
import java.util.List;

public class TagsToRemoveProvider implements Provider<List<String>> {

    @Override
    public List<String> get() {
        return Arrays.asList("repositories", "pluginRepositories");
    }
}
