package apollo;

import apollo.modules.MavenCommandOptionsModule;
import apollo.modules.MavenModule;
import apollo.upload.Uploader;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.maven.shared.invoker.MavenInvocationException;

import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws MavenInvocationException {
        Injector injector = Guice.createInjector(new MavenModule(), new MavenCommandOptionsModule());
        Uploader uploader = injector.getInstance(Uploader.class);
        uploader.uploadToRepository(Paths.get("C:\\tmp\\repo"));
    }
}
