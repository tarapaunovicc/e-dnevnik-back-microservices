package fon.e_dnevnik.userservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
public class AppConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder b) {
        return b.errorHandler(new DefaultResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return response.getStatusCode().is5xxServerError();
            }
        }).build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}