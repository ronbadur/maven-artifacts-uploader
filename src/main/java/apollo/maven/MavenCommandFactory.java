package apollo.maven;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class MavenCommandFactory {

    private final List<MavenDeployOption> mavenDeployOptions;
    private final String deployStartCommand;

    @Inject
    public MavenCommandFactory(List<MavenDeployOption> mavenDeployOptions,
                               @Named("deploy-start-command") String deployStartCommand) {
        this.mavenDeployOptions = mavenDeployOptions;
        this.deployStartCommand = deployStartCommand;
    }

    public String getMavenDeployCommand(Path pathToPom){
        StringBuilder mavenDeployCommand = new StringBuilder(deployStartCommand);

        for (MavenDeployOption currOption : mavenDeployOptions){
            Optional<String> commandOption = currOption.getCommandOption(pathToPom);
            commandOption.ifPresent(option -> mavenDeployCommand.append(option).append(" "));
        }

        return mavenDeployCommand.toString();
    }
}
