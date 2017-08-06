package apollo.maven;

import org.apache.maven.shared.invoker.InvocationOutputHandler;

public class MavenInvokerOutputHandler implements InvocationOutputHandler {

    private static final String ALREADY_EXIST_ERROR = "Repository does not allow updating assets";
    private boolean isExist = false;

    @Override
    public void consumeLine(String line) {
        if (line.contains(ALREADY_EXIST_ERROR)){
            isExist = true;
        }

        if (!isExist){
            System.out.println(line);
        }
    }

    public boolean isArtifactExist() {
        return isExist;
    }
}
