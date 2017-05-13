package apollo.delpoy_options_tests;

import apollo.deploy_options.RepositoryIdOption;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RepositoryIdOptionTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    private String repositoryIdOption = "-DrepositoryId=nexus";
    private RepositoryIdOption repositoryIdDeployOption = new RepositoryIdOption(repositoryIdOption);

    @Test
    public void correctRepositoryId() throws Exception {
        File tmpPom = temporaryFolder.newFile("tmpPom");

        Optional<String> command = repositoryIdDeployOption.getCommandOption(tmpPom.toPath());

        assertThat(command.get(), is(repositoryIdOption));
    }
}
