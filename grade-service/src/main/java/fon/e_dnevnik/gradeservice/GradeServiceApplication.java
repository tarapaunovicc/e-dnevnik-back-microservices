package fon.e_dnevnik.gradeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GradeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradeServiceApplication.class, args);
    }

}
