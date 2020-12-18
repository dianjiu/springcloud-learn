package co.dianjiu.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableHystrixDashboard
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("cfg.env","local");
        SpringApplication.run(Application.class, args);
        System.out.println("请访问  http://localhost:18005/hystrix.stream");
    }

}
