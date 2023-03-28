package io.wdefassio.msavaliationcredit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsAvaliationCreditApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAvaliationCreditApplication.class, args);
    }

}
