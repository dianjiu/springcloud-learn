package co.dianjiu.fegin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("cfg.env","local");
        SpringApplication.run(Application.class, args);
    }

}
