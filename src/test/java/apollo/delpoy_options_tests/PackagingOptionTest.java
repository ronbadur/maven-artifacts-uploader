package apollo.delpoy_options_tests;

import apollo.deploy_options.PackagingOption;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

public class PackagingOptionTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private final String packagingOption = "-Dpackaging=pom";
    private PackagingOption packagingDeployOption = new PackagingOption(packagingOption);

    @Test
    public void thereIsOnlyPom() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpPom.pom").toString());

        Optional<String> command = packagingDeployOption.getCommandOption(tmpPom.toPath());

        assertThat(command.get(), Matchers.is(packagingOption));

    }

    @Test
    public void thereIsPomAndJar() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile.pom").toString());
        File tmpJar = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile.jar").toString());

        Optional<String> command = packagingDeployOption.getCommandOption(tmpPom.toPath());

        assertThat(command.isPresent(), Matchers.is(false));

    }
}
