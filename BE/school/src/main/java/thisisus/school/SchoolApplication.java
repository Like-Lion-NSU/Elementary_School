package thisisus.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.context.annotation.ComponentScan;
=======
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
>>>>>>> 974a4080c73342c106cf470a3fd8503f75193db9

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class SchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }


}
