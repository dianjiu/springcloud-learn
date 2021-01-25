package co.dianjiu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("请访问 http://localhost:18023/baidu");
        System.out.println("请访问 http://localhost:18023/consumer/nice");
        System.out.println("请访问 http://localhost:18023/server-consumer-feign/nice");
    }

    /*@Beanke
    RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes().
                route("gongjie",j -> j.path("/get").uri("http://point9.top"))
                .build();
    }*/
}
