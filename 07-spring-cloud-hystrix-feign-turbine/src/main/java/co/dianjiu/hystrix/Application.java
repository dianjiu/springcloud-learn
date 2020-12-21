package co.dianjiu.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@EnableEurekaClient
@EnableHystrixDashboard
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("cfg.env","local");
        SpringApplication.run(Application.class, args);
        System.out.println("请访问  http://localhost:18006/turbine.stream");
    }

}
