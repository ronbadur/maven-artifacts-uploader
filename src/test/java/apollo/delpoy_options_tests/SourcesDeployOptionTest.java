package apollo.delpoy_options_tests;

import apollo.deploy_options.SourcesDeployOption;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SourcesDeployOptionTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    private final String sourcesOption = "-Dsources=";
    private final SourcesDeployOption sourcesDeployOption = new SourcesDeployOption(sourcesOption);


    @Test
    public void thereIsNoSources() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpPom.pom").toString());

        Optional<String> command = sourcesDeployOption.getCommandOption(tmpPom.toPath());

        assertThat(command.isPresent(), is(false));
    }

    @Test
    public void thereIsSources() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpPom.pom").toString());
        File tmpSources = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpPom-sources.jar").toString());

        Optional<String> command = sourcesDeployOption.getCommandOption(tmpPom.toPath());

        assertThat(command.get(), is(sourcesOption + tmpSources.toString()));
    }

}
