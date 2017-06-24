package apollo.command_line;

import com.beust.jcommander.JCommander;

public class Main {

    public static void main(String[] args) {
        OptionalArgs optionalArgs = new OptionalArgs();
        JCommander.newBuilder().addObject(optionalArgs).build().parse(args);
        System.out.println(optionalArgs.getPathToArtifacts());
    }
}
