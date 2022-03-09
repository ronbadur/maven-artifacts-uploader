package delpoy_options_tests;

import apollo.deploy_options.ExtraSideArtifactsDeployOption;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ExtraSideArtifactsDeployOptionTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private final String filesOption = "-Dfiles=";
    private final String classifiersOption = "-Dclassifiers=";
    private final String typesOption = "-Dtypes=";

    private ExtraSideArtifactsDeployOption extraSideArtifactsDeployOption = new ExtraSideArtifactsDeployOption(filesOption, classifiersOption, typesOption);

    @Test
    public void pomWithExtraSideArtifacts() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile.pom").toString());
        File tmpDebugJar = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-debug.jar").toString());
        File tmpSitePdf = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-site.pdf").toString());

        Optional<String> command = extraSideArtifactsDeployOption.getCommandOption(tmpPom.toPath());

        assertThat(command.get(), Matchers.is(filesOption + tmpDebugJar + "," + tmpSitePdf + " " + classifiersOption + "debug,site" + " " + typesOption + "jar,pdf"));
    }

    @Test
    public void hashAndSignatureFilesShouldBeOmitted() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile.pom").toString());
        File tmpDebugJar = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-debug.jar").toString());
        File tmpDebugJarAsc = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-debug.jar.asc").toString());
        File tmpDebugJarHash = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-debug.jar.sha512").toString());
        File tmpDebugJarAscHash = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-debug.jar.asc.sha512").toString());
        File tmpSitePdf = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-site.pdf").toString());

        Optional<String> command = extraSideArtifactsDeployOption.getCommandOption(tmpPom.toPath());

        assertThat(command.get(), Matchers.is(filesOption + tmpDebugJar + "," + tmpSitePdf + " " + classifiersOption + "debug,site" + " " + typesOption + "jar,pdf"));
    }

    @Test
    public void lastUpdatedFilesShouldBeOmitted() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile.pom").toString());
        File tmpDebugJarLastUpdated = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-debug.jar.lastUpdated").toString());

        Optional<String> command = extraSideArtifactsDeployOption.getCommandOption(tmpPom.toPath());

        assertThat(command.isPresent(), is(false));
    }

    @Test
    public void sourcesAndJavdocFilesShouldBeOmitted() throws Exception {
        File tmpFolder = temporaryFolder.newFolder("test");
        File tmpPom = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile.pom").toString());
        File tmpDebugJar = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-debug.jar").toString());
        File tmpSourcesJar = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-sources.jar").toString());
        File tmpSourcesJarAsc = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-sources.jar.asc").toString());
        File tmpSourcesJarHash = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-sources.jar.md5").toString());
        File tmpSourcesJarAscHash = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-sources.jar.asc.md5").toString());
        File tmpJavadocJar = temporaryFolder.newFile(Paths.get(tmpFolder.getName()).resolve("tmpFile-javadoc.jar").toString());

        Optional<String> command = extraSideArtifactsDeployOption.getCommandOption(tmpPom.toPath());

        assertThat(command.get(), Matchers.is(filesOption + tmpDebugJar  + " " + classifiersOption + "debug" + " " + typesOption + "jar"));
    }

}
