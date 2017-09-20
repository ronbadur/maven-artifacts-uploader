package predicators;

import apollo.predicators.PomFilePredictor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PomPredicatorTest {

    private final PomFilePredictor pomFilePredictor = new PomFilePredictor();

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void thisIsPomFile() throws Exception {
        File pomToTest = temporaryFolder.newFile("test.pom");

        boolean isPom = pomFilePredictor.test(pomToTest.toPath());

        assertThat(isPom, is(true));

    }

    @Test
    public void thisIsNotPomFile() throws Exception {
        File pomToTest = temporaryFolder.newFile("test.jar");

        boolean isPom = pomFilePredictor.test(pomToTest.toPath());

        assertThat(isPom, is(false));

    }

    @Test
    public void thisIsContainsPomInExtensionFile() throws Exception {
        File pomToTest = temporaryFolder.newFile("test.pom.sha1");

        boolean isPom = pomFilePredictor.test(pomToTest.toPath());

        assertThat(isPom, is(false));

    }
}
