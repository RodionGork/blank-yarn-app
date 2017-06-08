package rgyarn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;

@Configuration
@EnableAutoConfiguration
public class RgYarnCont {

	public static void maine(String[] args) {
	    BasicConfigurator.configure();
	    Logger.getRootLogger().setLevel(Level.INFO);
		SpringApplication.run(RgYarnCont.class, args);
	}

	@Bean
	public WorkerPojo workerPojo() {
		return new WorkerPojo();
	}

}

