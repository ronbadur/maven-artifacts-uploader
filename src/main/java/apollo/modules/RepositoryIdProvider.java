package apollo.modules;

import com.google.inject.Provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class RepositoryIdProvider implements Provider<String> {

    private static final String CONFIGURATION_FILENAME = "config.properties";
    private static final String DEPLOY_OPTION = "-DrepositoryId=";

    @Override
    public String get() {
        Properties properties = new Properties();

        try {
            Path currentPath = Paths.get(".").toAbsolutePath().normalize();
            Path configurationFilePath = currentPath.resolve("conf").resolve(CONFIGURATION_FILENAME);
            BufferedReader bufferedReader = Files.newBufferedReader(configurationFilePath);
            properties.load(bufferedReader);
        } catch (IOException e) {
            throw new UncheckedIOException("there is problem with " + CONFIGURATION_FILENAME, e);
        }

        return DEPLOY_OPTION + properties.getProperty("repository.id");
    }
}
