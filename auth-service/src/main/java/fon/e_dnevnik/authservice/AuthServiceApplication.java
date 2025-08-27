package fon.e_dnevnik.authservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;


@EnableDiscoveryClient
@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner dbProbe(JdbcTemplate jdbc) {
        return args -> {
            var info = jdbc.queryForMap("SELECT @@hostname host, @@port port, @@version ver, DATABASE() db");
            System.out.println("DB PROBE -> " + info);

            var ddl = jdbc.queryForObject("SHOW CREATE TABLE token", (rs, i) -> rs.getString(2));
            System.out.println("TOKEN DDL ->\n" + ddl);
        };
    }
}
