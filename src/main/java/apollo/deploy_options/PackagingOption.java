package apollo.deploy_options;

import apollo.maven.MavenDeployOption;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class PackagingOption implements MavenDeployOption {

    private final String packagingOption;

    @Inject
    public PackagingOption(@Named("packagingOption")String packagingOption) {
        this.packagingOption = packagingOption;
    }

    @Override
    public Optional<String> getCommandOption(Path pathToPom) {
        Optional<String> packagingOptional = Optional.empty();

        if (Files.notExists(getJarPath(pathToPom))){
            packagingOptional = Optional.of(packagingOption);
        }

        return packagingOptional;
    }

    private Path getJarPath(Path pathToPom){
        int last = pathToPom.toString().lastIndexOf(".");
        return Paths.get(pathToPom.toString().substring(0, last) + ".jar");
    }
}
