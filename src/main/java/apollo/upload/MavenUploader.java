package apollo.upload;

import apollo.maven.MavenDeployer;
import apollo.predicators.PomFilePredictor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class MavenUploader implements Uploader {

    private final PomFilePredictor pomFilePredictor;
    private final MavenDeployer mavenDeployer;

    public MavenUploader(PomFilePredictor pomFilePredictor, MavenDeployer mavenDeployer) {
        this.pomFilePredictor = pomFilePredictor;
        this.mavenDeployer = mavenDeployer;
    }

    @Override
    public void uploadToRepository(Path pathToUpload) {
        try (Stream<Path> files = Files.walk(pathToUpload)){
            files.filter(pomFilePredictor).forEach(mavenDeployer::deployPom);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}