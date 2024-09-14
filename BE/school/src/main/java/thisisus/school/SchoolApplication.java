package thisisus.school;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EnableJpaRepositories
@EnableMongoRepositories
@EnableBatchProcessing
@EnableFeignClients
@EnableCaching
@ConfigurationPropertiesScan(basePackages = "thisisus.school")
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}
}