package apollo;

import apollo.deploy_options.*;
import apollo.maven.MavenCommandFactory;
import apollo.maven.MavenDeployOption;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;

public class MavenCommandFactoryTest {

    private MavenDeployOption pomFileOption = Mockito.mock(PomDeployOption.class);
    private MavenDeployOption fileOption = Mockito.mock(FileDeployOption.class);
    private MavenDeployOption repositoryIdOption = Mockito.mock(RepositoryIdOption.class);
    private MavenDeployOption urlOption = Mockito.mock(UrlDeployOption.class);
    private MavenDeployOption javadocOption = Mockito.mock(JavadocDeployOption.class);
    private MavenDeployOption sourcesOption = Mockito.mock(SourcesDeployOption.class);
    private MavenCommandFactory mavenCommandFactory;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        Mockito.when(pomFileOption.getCommandOption(any())).thenReturn(Optional.of("-DpomFile=test"));
        Mockito.when(fileOption.getCommandOption(any())).thenReturn(Optional.of("-DFile=test"));
        Mockito.when(repositoryIdOption.getCommandOption(any())).thenReturn(Optional.of("-DrepositoryId=test"));
        Mockito.when(urlOption.getCommandOption(any())).thenReturn(Optional.of("-Durl=test"));
        Mockito.when(javadocOption.getCommandOption(any())).thenReturn(Optional.empty());
        Mockito.when(sourcesOption.getCommandOption(any())).thenReturn(Optional.empty());


    }

    @Test
    public void allOptionalsPresents() throws Exception {
        mavenCommandFactory = new MavenCommandFactory(Arrays.asList(pomFileOption, fileOption,
                                                                    repositoryIdOption, urlOption));
        File tmpPom = temporaryFolder.newFile("test.pom");

        String deployCommand = mavenCommandFactory.getMavenDeployCommand(tmpPom.toPath());

        assertThat(deployCommand, is("deploy:deploy-file -DpomFile=test -DFile=test -DrepositoryId=test -Durl=test "));
    }

    @Test
    public void someOptionalsNotPresent() throws Exception {
        mavenCommandFactory = new MavenCommandFactory(Arrays.asList(pomFileOption, fileOption,
                                                                    repositoryIdOption, urlOption,
                                                                    javadocOption, sourcesOption));
        File tmpPom = temporaryFolder.newFile("test.pom");

        String deployCommand = mavenCommandFactory.getMavenDeployCommand(tmpPom.toPath());

        assertThat(deployCommand, is("deploy:deploy-file -DpomFile=test -DFile=test -DrepositoryId=test -Durl=test "));
    }


}
