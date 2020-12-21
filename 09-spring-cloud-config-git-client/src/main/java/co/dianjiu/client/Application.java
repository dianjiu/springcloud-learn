package co.dianjiu.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("请访问  http://localhost:18008/hello");
        System.out.println("POST  http://localhost:18008/actuator/refresh");
    }

}
