package apollo.maven;

import java.nio.file.Path;

public class GAVFactory {

    public GAV createGAV(Path pathToPom){
        // TODO: Implement groupId logic...

        String artifactId = pathToPom.subpath(pathToPom.getNameCount() - 3, pathToPom.getNameCount() - 2).toString();
        String version = pathToPom.subpath(pathToPom.getNameCount() - 2, pathToPom.getNameCount() -1).toString();

       return new GAV("", artifactId, version);
    }
}
