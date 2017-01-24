package apollo.modules;

import apollo.deploy_options.*;
import apollo.maven.MavenCommand;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

public class MavenCommandsProvider implements Provider<List<MavenCommand>> {

    private final PomDeployOption pomDeployOption;
    private final FileDeployOption fileDeployOption;
    private final RepositoryIdOption repositoryIdOption;
    private final UrlDeployOption urlDeployOption;
    private final PackagingOption packagingOption;
    private final SourcesDeployOption sourcesDeployOption;
    private final JavadocDeployOption javadocDeployOption;

    @Inject
    public MavenCommandsProvider(PomDeployOption pomDeployOption, FileDeployOption fileDeployOption, RepositoryIdOption repositoryIdOption, UrlDeployOption urlDeployOption, PackagingOption packagingOption, SourcesDeployOption sourcesDeployOption, JavadocDeployOption javadocDeployOption) {
        this.pomDeployOption = pomDeployOption;
        this.fileDeployOption = fileDeployOption;
        this.repositoryIdOption = repositoryIdOption;
        this.urlDeployOption = urlDeployOption;
        this.packagingOption = packagingOption;
        this.sourcesDeployOption = sourcesDeployOption;
        this.javadocDeployOption = javadocDeployOption;
    }

    @Override
    public List<MavenCommand> get() {
        return Arrays.asList(pomDeployOption, fileDeployOption, repositoryIdOption, urlDeployOption, packagingOption, sourcesDeployOption, javadocDeployOption);
    }
}
