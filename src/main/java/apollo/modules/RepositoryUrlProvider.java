package apollo.modules;

import com.google.inject.Provider;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class RepositoryUrlProvider implements Provider<String> {

    private static final String CONFIGURATION_FILENAME = "config.properties";
    private static final String DEPLOY_OPTION = "-Durl=";

    @Override
    public String get() {
        Properties properties = new Properties();

        try {
            Path currentPath = Paths.get(".").toAbsolutePath().normalize();
            Path configurationFilePath = currentPath.resolve("conf").resolve(CONFIGURATION_FILENAME);
            BufferedReader bufferedReader = Files.newBufferedReader(configurationFilePath);
            properties.load(bufferedReader);
        } catch (IOException e) {
            throw new UncheckedIOException("there is problem with " + CONFIGURATION_FILENAME + " file", e);
        }

        return DEPLOY_OPTION + properties.getProperty("repository.url");
    }
}
