package apollo.command_line;

import com.beust.jcommander.Parameter;

public class OptionalArgs {

    @Parameter(names = {"-d", "--directory"},required = true, description = "the directory of the artifacts")
    private String pathToArtifacts;


    public String getPathToArtifacts() {
        return pathToArtifacts;
    }

    public void setPathToArtifacts(String pathToArtifacts) {
        this.pathToArtifacts = pathToArtifacts;
    }
}
