package rgyarn.am;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.yarn.am.StaticAppmaster;
import org.springframework.yarn.am.YarnAppmaster;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CustomStaticAppmaster extends StaticAppmaster implements YarnAppmaster {
    
    private final Log log = LogFactory.getLog(getClass());
    
    @Override
    public void submitApplication() {
        log.info("DBGPZ, submitting");
        super.submitApplication();
    }
    
    @Override
    protected void onInit() throws Exception {
        log.info("DBGPZ, initializing");
        super.onInit();
    }
    
    @Override
    protected void doStart() {
        log.info("DBGPZ, starting");
        super.doStart();
        log.info("DBGPZ, starting 2");
        try {
            new WebServer(this).run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void shutdown() {
        notifyCompleted();
    }
}

