package apollo.maven;

import apollo.deploy_options.PomDeployOption;
import com.google.inject.Inject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MavenCommandFactory {

    private final List<MavenCommand> mavenCommands;

    @Inject
    public MavenCommandFactory(List<MavenCommand> mavenCommands) {
        this.mavenCommands = mavenCommands;
    }

    public List<Optional<String>> getMavenDeployCommandsForArtifact(Path pathToPom){
        return mavenCommands.stream().map(currCommand -> currCommand.getCommand(pathToPom)).collect(Collectors.toList());
    }
}
