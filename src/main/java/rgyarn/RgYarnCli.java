package rgyarn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.yarn.client.YarnClient;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import rgyarn.am.RgYarnAm;
import rgyarn.cont.RgYarnCont;

@EnableAutoConfiguration
public class RgYarnCli {

    public static void main(String... args) {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);
        if (System.getenv("SHDP_HD_RM") != null) {
            Logger.getRootLogger().info("YARN_MODE = MASTER");
            SpringApplication.run(RgYarnAm.class, args);
        } else if (System.getenv("SHDP_CONTAINERID") != null) {
            Logger.getRootLogger().info("YARN_MODE = CONTAINER");
            SpringApplication.run(RgYarnCont.class, args);
        } else if (System.getProperty("test-web") != null) {
            Logger.getRootLogger().info("LAUNCHING WEB-INTERFACE FOR TEST");
            new RgYarnAm().webServer();
        } else {
            Logger.getRootLogger().info("YARN_MODE = CLIENT");
            SpringApplication.run(RgYarnCli.class, args).getBean(YarnClient.class).submitApplication(true);
        }
    }
}
