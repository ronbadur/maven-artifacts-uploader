package apollo.deploy_options;

import apollo.maven.MavenCommand;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class JavadocDeployOption implements MavenCommand {

    private final String javadocOption;

    @Inject
    public JavadocDeployOption(@Named("javadocOption") String javadocOption) {
        this.javadocOption = javadocOption;
    }

    @Override
    public Optional<String> getCommand(Path pathToPom) {
        Optional<String> javadocOptional = Optional.empty();

        if (Files.exists(getJavadocPath(pathToPom))){
            javadocOptional = Optional.of(javadocOption + getJavadocPath(pathToPom).toString());
        }

        return javadocOptional;
    }

    private Path getJavadocPath(Path pathToPom){
        int last = pathToPom.toString().lastIndexOf(".");
        return Paths.get(pathToPom.toString().substring(0, last) + "-javadoc.jar");
    }
}
