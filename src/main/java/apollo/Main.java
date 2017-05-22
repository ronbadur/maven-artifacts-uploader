package apollo;

import apollo.modules.MavenCommandOptionsModule;
import apollo.modules.MavenModule;
import apollo.modules.XmlModule;
import apollo.upload.Uploader;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.shared.invoker.MavenInvocationException;

import java.nio.file.Paths;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws MavenInvocationException {
        logger.info("Welcome To Maven Artifacts Uploader!");
        Injector injector = Guice.createInjector(new MavenModule(), new MavenCommandOptionsModule(), new XmlModule());
        Uploader uploader = injector.getInstance(Uploader.class);
        uploader.uploadToRepository(Paths.get("C:\\Users\\Ron\\.m2\\check"));
        logger.info("Uploading done...");
    }
}
