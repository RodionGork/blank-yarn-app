package rgyarn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


@EnableAutoConfiguration
public class RgAppMaster {

	public static void maine(String[] args) {
		Logger.getRootLogger().info("ENV_IS: " + System.getenv());
		SpringApplication.run(RgAppMaster.class, args);
	}

}
