package apollo;

import apollo.maven.MavenDeployer;
import apollo.predicators.PomFilePredictor;
import apollo.upload.MavenUploader;
import apollo.upload.Uploader;

import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Uploader uploader = new MavenUploader(new PomFilePredictor(), new MavenDeployer());
        uploader.uploadToRepository(Paths.get("C:\\tmp\\repo"));
    }
}
