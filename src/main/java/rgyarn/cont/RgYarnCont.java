package rgyarn.cont;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class RgYarnCont {

	@Bean
	public WorkerPojo workerPojo() {
		return new WorkerPojo();
	}

}

