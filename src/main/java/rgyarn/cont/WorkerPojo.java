package rgyarn.cont;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.yarn.annotation.OnContainerStart;
import org.springframework.yarn.annotation.YarnComponent;

@YarnComponent
public class WorkerPojo {

	private static final Log log = LogFactory.getLog(WorkerPojo.class);

	@Autowired
	private Configuration configuration;

	@OnContainerStart
	public void publicVoidNoArgsMethod() throws Exception {
		log.warn("Hi, Peoplez, from WorkerPojo");
		log.info("ENV_IS: " + System.getenv());
	}

}
