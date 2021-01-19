package co.dianjiu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("请访问 http://localhost:18023/get");
    }

    /*@Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes().
                route("gongjie",j -> j.path("/get").uri("http://point9.top"))
                .build();
    }*/
}
