package delpoy_options_tests;

import apollo.deploy_options.UrlDeployOption;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UrlDeployOptionTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    private String urlOption = "-Durl=http://test.com";
    private UrlDeployOption urlDeployOption = new UrlDeployOption(urlOption);

    @Test
    public void correctUrl() throws Exception {
        File tmpPom = temporaryFolder.newFile("tmpPom");

        Optional<String> command = urlDeployOption.getCommandOption(tmpPom.toPath());

        assertThat(command.get(), is(urlOption));
    }
}
