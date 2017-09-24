package apollo.upload;

import apollo.maven.MavenDeployer;
import apollo.predicators.PomFilePredictor;
import apollo.xml_handlers.XmlReformer;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class MavenUploader implements Uploader {

    private final PomFilePredictor pomFilePredictor;
    private final MavenDeployer mavenDeployer;
    private final XmlReformer xmlReformer;
    private static final Logger logger = LogManager.getLogger(MavenUploader.class);
    private static final int maxThreadNo = 8;
    private static ExecutorService executor = Executors.newFixedThreadPool(maxThreadNo);

    @Inject
    public MavenUploader(PomFilePredictor pomFilePredictor, MavenDeployer mavenDeployer, XmlReformer xmlReformer) {
        this.pomFilePredictor = pomFilePredictor;
        this.mavenDeployer = mavenDeployer;
        this.xmlReformer = xmlReformer;
    }

    @Override
    public void uploadToRepository(Path pathToUpload) {

        logger.info("Starting to upload artifacts from " + pathToUpload.toString());
        try (Stream<Path> files = Files.walk(pathToUpload)){
            files.filter(pomFilePredictor).filter(path -> {
                try {
                    xmlReformer.prepareXmlToDeploy(path);
                    return true;
                } catch (Exception e) {
                    logger.error("Error uploading " + path + " : " + e);
                    return false;
                }
            }).forEach(path -> {
                Runnable uploadThread = new MavenUploadThread(mavenDeployer, path);
                executor.submit(uploadThread);
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        executor.shutdown();
        try {
            if (executor.awaitTermination(5, TimeUnit.DAYS)) {
                logger.info("Executor service has shut down");
            }
        } catch (InterruptedException e) {
            logger.info("Executor service interrupted");
        }
    }
}
