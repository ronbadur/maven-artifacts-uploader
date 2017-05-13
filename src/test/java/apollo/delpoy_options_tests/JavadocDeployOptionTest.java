package apollo.delpoy_options_tests;

import apollo.deploy_options.JavadocDeployOption;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JavadocDeployOptionTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    private final String javadocOption = "-Djavadoc=";
    private final JavadocDeployOption javadocDeployOption = new JavadocDeployOption(javadocOption);


    @Test
    public void thereIsNoJavadoc() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpPom.pom").toString());

        Optional<String> command = javadocDeployOption.getCommand(tmpPom.toPath());

        assertThat(command.isPresent(), is(false));
    }

    @Test
    public void thereIsJavadoc() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpPom.pom").toString());
        File tmpJavadoc = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpPom-javadoc.jar").toString());

        Optional<String> command = javadocDeployOption.getCommand(tmpPom.toPath());

        assertThat(command.get(), is(javadocOption + tmpJavadoc.toString()));
    }
}
