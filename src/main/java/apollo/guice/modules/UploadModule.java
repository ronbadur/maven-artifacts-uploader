package apollo.guice.modules;

import apollo.upload.MavenUploader;
import apollo.upload.Uploader;
import com.google.inject.AbstractModule;

public class UploadModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Uploader.class).to(MavenUploader.class);
    }
}
