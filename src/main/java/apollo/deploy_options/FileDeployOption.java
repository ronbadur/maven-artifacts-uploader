package apollo.deploy_options;

import apollo.maven.MavenDeployOption;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileDeployOption implements MavenDeployOption {

    private final String fileDeployCommand;

    @Inject
    public FileDeployOption(@Named("fileOption") String fileDeployCommand) {
        this.fileDeployCommand = fileDeployCommand;
    }

    @Override
    public Optional<String> getCommandOption(Path pathToPom) {
        Optional<String> fileOptional = Optional.of(fileDeployCommand + pathToPom.toString());

        if (Files.exists(getJarPath(pathToPom))){
            fileOptional =  Optional.of(fileDeployCommand + getJarPath(pathToPom).toString());
        }

        return fileOptional;
    }

    private Path getJarPath(Path pathToPom){
        int last = pathToPom.toString().lastIndexOf(".");
        return Paths.get(pathToPom.toString().substring(0, last) + ".jar");
    }
}
