package apollo.command_line;

import com.beust.jcommander.Parameter;

public class OptionalArgs {

    @Parameter(names = {"-d", "--directory"}, required = true, description = "the directory of the artifacts to upload")
    private String pathToArtifacts;

    @Parameter(names ={"-h", "--help"} , help = true, description = "print help message")
    private boolean help = false;


    public String getPathToArtifacts() {
        return pathToArtifacts;
    }

    public boolean isHelp() {
        return help;
    }
}
