package rgyarn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.yarn.client.YarnClient;
import org.springframework.yarn.fs.ResourceLocalizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

@EnableAutoConfiguration
public class RgYarnCli {

	public static void maine(String[] args) {
	    BasicConfigurator.configure();
	    Logger.getRootLogger().setLevel(Level.INFO);
		ConfigurableApplicationContext ctx = SpringApplication.run(RgYarnCli.class, args);
		System.out.println("LOCRES:\n" + ctx.getBean(ResourceLocalizer.class).getResources());
		YarnClient cli = ctx.getBean(YarnClient.class);
		cli.submitApplication(true);
	}

}
