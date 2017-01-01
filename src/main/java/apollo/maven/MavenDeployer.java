package apollo.maven;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MavenDeployer {

    private Map<String, String> commandOptions = new HashMap<>();

    public MavenDeployer() {
        initialCommandOptionsMap();
    }

    public void deployPom(Path pathToPom){
        commandOptions.put("pomFile", pathToPom.toString());
        commandOptions.put("file", getJarPathThatMatchToPom(pathToPom));
        System.out.println(createCommandToExecute());
    }

    private void initialCommandOptionsMap(){
        commandOptions.put("repositoryId", "maven-releases");
        commandOptions.put("url", "http://localhost:8081/repository/maven-releases/");
    }

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

    private String createCommandToExecute(){
        StringBuilder commandToExecute = new StringBuilder();
        for (Map.Entry<String,String> currEntry : commandOptions.entrySet()){
            commandToExecute.append("-D");
            commandToExecute.append(currEntry.getKey());
            commandToExecute.append("=");
            commandToExecute.append(currEntry.getValue());
            commandToExecute.append(" ");
        }

        return commandToExecute.toString();
    }
}
