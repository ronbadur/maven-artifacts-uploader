package apollo;

import apollo.guice.modules.MavenModule;
import apollo.guice.modules.UploadModule;
import apollo.maven.MavenDeployer;
import apollo.predicators.PomFilePredictor;
import apollo.upload.MavenUploader;
import apollo.upload.Uploader;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MavenModule(), new UploadModule());
        Uploader uploader = injector.getInstance(Uploader.class);
        uploader.uploadToRepository(Paths.get("C:\\tmp\\repo"));
    }
}
