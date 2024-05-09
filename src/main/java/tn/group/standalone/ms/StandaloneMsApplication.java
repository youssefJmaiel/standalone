package tn.group.standalone.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import tn.group.standalone.ms.config.StandaloneMsConfiguration;

@SpringBootApplication
@Import(StandaloneMsConfiguration.class)
//@ComponentScan("tn.group.standalone.ms.repository.OwnerRepository")
public class StandaloneMsApplication {

	public static void main(String[] args) {

		SpringApplication.run(StandaloneMsApplication.class, args);
	}

}
