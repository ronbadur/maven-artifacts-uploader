package apollo.modules;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.InvocationRequest;

public class InvocationRequestProvider implements Provider<InvocationRequest> {

    private final DefaultInvocationRequest defaultInvocationRequest;

    @Inject
    public InvocationRequestProvider(DefaultInvocationRequest defaultInvocationRequest) {
        this.defaultInvocationRequest = defaultInvocationRequest;
    }

    @Override
    public InvocationRequest get() {
        defaultInvocationRequest.setBatchMode(true);

        return defaultInvocationRequest;
    }
}
