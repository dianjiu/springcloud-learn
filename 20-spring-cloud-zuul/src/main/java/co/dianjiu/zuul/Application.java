package co.dianjiu.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("请访问 http://localhost:8888");
        System.out.println("请访问 http://localhost:18001/nice");
        System.out.println("请访问 http://localhost:18003/nice");
        System.out.println("请访问 http://localhost:18019/server-provider/nice");
        System.out.println("请访问 http://localhost:18019/server-consumer-feign/nice");
    }

}
