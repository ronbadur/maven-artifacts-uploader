package apollo.maven;

import com.google.inject.Inject;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class MavenCommandFactory {

    private final List<MavenDeployOption> mavenDeployOptions;

    @Inject
    public MavenCommandFactory(List<MavenDeployOption> mavenDeployOptions) {
        this.mavenDeployOptions = mavenDeployOptions;
    }

    public String getMavenDeployCommand(Path pathToPom){
        StringBuilder mavenDeployCommand = new StringBuilder("deploy:deploy-file ");

        for (MavenDeployOption currOption : mavenDeployOptions){
            Optional<String> commandOption = currOption.getCommandOption(pathToPom);
            commandOption.ifPresent(option -> mavenDeployCommand.append(option).append(" "));
        }

        return mavenDeployCommand.toString();
    }
}
