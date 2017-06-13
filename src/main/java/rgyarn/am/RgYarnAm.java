package rgyarn.am;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.apache.log4j.Logger;


@EnableAutoConfiguration
public class RgYarnAm {
    
    @Bean
    public WebServer webServer() {
        WebServer ws = new WebServer();
        try {
            ws.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ws;
    }
    
}
