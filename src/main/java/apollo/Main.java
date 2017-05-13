package apollo;

import apollo.modules.MavenCommandOptionsModule;
import apollo.modules.MavenModule;
import apollo.modules.XmlModule;
import apollo.upload.Uploader;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.maven.shared.invoker.MavenInvocationException;

import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws MavenInvocationException {
        long startTime = System.nanoTime();
        Injector injector = Guice.createInjector(new MavenModule(), new MavenCommandOptionsModule(), new XmlModule());
        Uploader uploader = injector.getInstance(Uploader.class);
        uploader.uploadToRepository(Paths.get("C:\\Users\\Ron\\.m2\\check"));
        long endTime = System.nanoTime() - startTime;
        System.out.println("The process took " + endTime / 1000000000 + " seconds" +
                "");
    }
}
