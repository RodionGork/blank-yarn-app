package rgyarn.am;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.yarn.am.StaticAppmaster;
import org.springframework.yarn.config.annotation.EnableYarn;
import org.springframework.yarn.config.annotation.SpringYarnConfigurerAdapter;
import org.springframework.yarn.config.annotation.builders.YarnAppmasterConfigurer;
import org.springframework.yarn.config.annotation.builders.YarnConfigConfigurer;

@Configuration
@EnableYarn(enable=EnableYarn.Enable.APPMASTER)
//@EnableAutoConfiguration
public class RgYarnAm extends SpringYarnConfigurerAdapter {
    
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
    
    @Override
    public void configure(YarnConfigConfigurer config) throws Exception {
        config
            .loadDefaults(true)
            .fileSystemUri("hdfs://localhost:9000")
            .resourceManagerAddress("localhost:8032");
    }
    
    @Override
    public void configure(YarnAppmasterConfigurer master) throws Exception {
        master
            .appmasterClass(CustomStaticAppmaster.class)
            .withContainerRunner();
        master
            .withContainerAllocator()
            .priority(0)
            .virtualCores(1)
            .memory(512);
    }
    
}

