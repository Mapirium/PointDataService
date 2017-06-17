package ch.mapirium.server.pointdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
public class PointDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointDataServiceApplication.class, args);
	}
}
