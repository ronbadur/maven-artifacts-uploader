package apollo.maven;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MavenCommandFactory {

    private final List<MavenCommand> mavenCommands;
    private static final String MAVEN_DEPLOY_COMMAND = "mvn deploy:deploy-file ";

    public MavenCommandFactory(List<MavenCommand> mavenCommands) {
        this.mavenCommands = mavenCommands;
    }

    public List<Optional<String>> getMavenDeployCommandsForArtifact(Path pathToPom){
        return mavenCommands.stream().map(currCommand -> currCommand.getCommand(pathToPom)).collect(Collectors.toList());
    }

    private void initialMavenCommandsList(){
        mavenCommands.add(new PomDeployCommand());
        mavenCommands.add(new SourcesDeployCommand());
    }

//    public String createPomDeployCommand(Path pathToPom){
//        initialCommandOptionsList();
//        commandOptions.set(0, commandOptions.get(0) + pathToPom.toString());
//        commandOptions.set(1, commandOptions.get(1) + getJarPathThatMatchToPom(pathToPom));
//        String deployCommand = commandOptions.stream().reduce((first, second) -> first + " " + second).get();
//        return MAVEN_DEPLOY_COMMAND + deployCommand;
//    }
//
//    public String createSourcesDeployCommand(Path pathToPom){
//        return "";
//    }
//
//    private void initialCommandOptionsList(){
//        commandOptions = new ArrayList<>();
//        commandOptions.add("-DpomFile=");
//        commandOptions.add("-Dfile=");
//        commandOptions.add("-DrepositoryId=maven-releases");
//        commandOptions.add("-Durl=http://localhost:8081/repository/maven-releases/");
//    }

    private String getJarPathThatMatchToPom(Path pathToPom){
        String fileName = FilenameUtils.getName(pathToPom.getFileName().toString());
        String baseName = FilenameUtils.getBaseName(pathToPom.getFileName().toString());
        String jarPath = pathToPom.toString().replace(fileName, baseName + ".jar");
        if (Files.exists(Paths.get(jarPath))){
            return jarPath;
        } else {
            return pathToPom.toString();
        }
    }
}
