package iisi.example.gia3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ImportResource("classpath:batch-config.xml")
@SpringBootApplication
//@EnableScheduling
//@EnableTransactionManagement
//@EnableJpaRepositories
public class B2GiaApplication {
    public static void main(String[] args) {

        SpringApplication.run(B2GiaApplication.class, args);
    }
}