package apollo.delpoy_options_tests;

import apollo.deploy_options.PomDeployOption;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PomDeployOptionTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    private final String pomOption = "-DpomFile=";
    private final PomDeployOption pomDeployOption = new PomDeployOption(pomOption);

    @Test
    public void onlyPomFile() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpPom.pom").toString());

        Optional<String> command = pomDeployOption.getCommand(tmpPom.toPath());

        assertThat(command.get(), is(pomOption + tmpPom.toString()));
    }

    @Test
    public void notOnlyPomFile() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile.pom").toString());
        File tmpJar = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile.jar").toString());
        File tmpJavadoc = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-javadoc.jar").toString());

        Optional<String> command = pomDeployOption.getCommand(tmpPom.toPath());

        assertThat(command.get(), is(pomOption + tmpPom.toString()));
    }
}
