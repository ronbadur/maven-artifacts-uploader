package delpoy_options_tests;

import apollo.deploy_options.FileDeployOption;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

public class FileDeployOptionTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private final String fileOption = "-Dfile=";
    private FileDeployOption fileDeployOption = new FileDeployOption(fileOption);

    @Test
    public void pomWithoutJar() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpPom.pom").toString());

        Optional<String> command = fileDeployOption.getCommandOption(tmpPom.toPath());

        assertThat(command.get(), Matchers.is(fileOption + tmpPom.toString()));
    }

    @Test
    public void pomWithJar() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile.pom").toString());
        File tmpJar = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile.jar").toString());

        Optional<String> command = fileDeployOption.getCommandOption(tmpPom.toPath());

        assertThat(command.get(), Matchers.is(fileOption + tmpJar.toString()));
    }

    @Test
    public void pomWithAnotherFile() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile.pom").toString());
        File tmpSha1 = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile.jar.sha1").toString());

        Optional<String> command = fileDeployOption.getCommandOption(tmpPom.toPath());

        assertThat(command.get(), Matchers.is(fileOption + tmpPom.toString()));
    }
}
