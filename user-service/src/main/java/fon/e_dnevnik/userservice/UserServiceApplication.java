package fon.e_dnevnik.userservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner dbProbe(DataSource ds) {
        return args -> {
            try (var c = ds.getConnection(); var st = c.createStatement()) {
                var rs = st.executeQuery("SELECT @@hostname, @@port, @@version, DATABASE()");
                if (rs.next()) {
                    System.out.println("DB PROBE -> {host=" + rs.getString(1)+", port="+rs.getInt(2)+", ver="+rs.getString(3)+", db="+rs.getString(4)+"}");
                }
                var rs2 = st.executeQuery("SHOW CREATE TABLE `teacher`");
                if (rs2.next()) {
                    System.out.println("USER DDL ->\n" + rs2.getString(2));
                }
            }
        };
    }
}
