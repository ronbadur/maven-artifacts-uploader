package apollo.upload;

import java.nio.file.Path;

public interface Uploader {

    void uploadToRepository(Path pathToUpload);
}
