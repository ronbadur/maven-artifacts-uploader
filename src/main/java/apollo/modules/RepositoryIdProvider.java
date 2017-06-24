package apollo.modules;

import com.google.inject.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class RepositoryIdProvider implements Provider<String> {

    private static final String CONFIGURATION_FILENAME = "config.properties";
    private static final String DEPLOY_OPTION = "-DrepositoryId=";

    @Override
    public String get() {
        Properties properties = new Properties();

        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(CONFIGURATION_FILENAME);
            properties.load(inputStream);
        } catch (IOException e) {
            throw new UncheckedIOException("there is problem with " + CONFIGURATION_FILENAME, e);
        }

        return DEPLOY_OPTION + properties.getProperty("repository.id");
    }
}
