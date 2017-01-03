package apollo.guice.providers;

import apollo.maven.MavenCommand;
import apollo.maven.PomDeployCommand;
import apollo.maven.SourcesDeployCommand;
import com.google.inject.Provider;

import java.util.Arrays;
import java.util.List;

public class MavenCommandsProvider implements Provider<List<MavenCommand>> {

    @Override
    public List<MavenCommand> get() {
        return Arrays.asList(new PomDeployCommand(), new SourcesDeployCommand());
    }
}
