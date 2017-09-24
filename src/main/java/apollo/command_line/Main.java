package apollo.command_line;

import apollo.modules.MavenCommandOptionsModule;
import apollo.modules.MavenModule;
import apollo.modules.XmlModule;
import apollo.upload.Uploader;
import com.beust.jcommander.JCommander;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MavenModule(), new MavenCommandOptionsModule(), new XmlModule());
        OptionalArgs optionalArgs = injector.getInstance(OptionalArgs.class);
        JCommander jCommander = JCommander.newBuilder().addObject(optionalArgs).build();
        jCommander.setProgramName("mvnUploader");
        jCommander.parse(args);

        if (optionalArgs.isHelp()){
            jCommander.usage();
        } else {
            logger.info("Welcome To Maven Artifacts Uploader");
            Uploader uploader = injector.getInstance(Uploader.class);
            uploader.uploadToRepository(Paths.get(optionalArgs.getPathToArtifacts()));
            logger.info("Done uploading all the artifacts!");
        }
    }
}
