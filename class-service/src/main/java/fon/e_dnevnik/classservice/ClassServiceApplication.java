package fon.e_dnevnik.classservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ClassServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassServiceApplication.class, args);
    }

}
