package co.dianjiu.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableEurekaServer
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("cfg.env","local");
        SpringApplication.run(Application.class, args);
    }

}
