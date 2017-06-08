package rgyarn;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class App {

    public static void main(String... args) {
	    BasicConfigurator.configure();
	    Logger.getRootLogger().setLevel(Level.INFO);
        if (System.getenv("SHDP_HD_RM") != null) {
            Logger.getRootLogger().warn("YARN_MODE = MASTER");
            RgAppMaster.maine(args);
        } else if (System.getenv("SHDP_CONTAINERID") != null) {
            Logger.getRootLogger().warn("YARN_MODE = CONTAINER");
            RgYarnCont.maine(args);
        } else {
            Logger.getRootLogger().warn("YARN_MODE = CLIENT");
            RgYarnCli.maine(args);
        }
    }

}
