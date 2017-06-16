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
        setEnvironment(System.getenv());
        log.info("DBGPZ, initializing");
        super.onInit();
    }
    
    @Override
    protected void doStart() {
        super.doStart();
        registerAppmaster();
        log.info("DBGPZ, registered");
		if (getAppmasterService() == null) {
			log.info("AppmasterService is null");
		}
        try {
            new WebServer(this).run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public void shutdown() {
        doStop();
        stop();
    }
}

