package co.dianjiu.zuul;

import co.dianjiu.zuul.filter.MyTokenFilter;
import com.fasterxml.jackson.core.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        /*二、快速上手*/
        System.out.println("请访问 http://localhost:8888");
        System.out.println("请访问 http://localhost:18001/nice");
        System.out.println("请访问 http://localhost:18003/nice");
        System.out.println("请访问 http://localhost:18019/server-provider/nice");
        System.out.println("请访问 http://localhost:18019/server-consumer-feign/nice");
        /*三、管理端点*/
        System.out.println("请访问 http://localhost:18019/actuator/routes");
        System.out.println("请访问 http://localhost:18019/actuator/filters");
        System.out.println("请访问 http://localhost:18019/actuator/hystrix.stream");
        /*四、路由测试*/
        System.out.println("请访问 http://localhost:18019/dwz/spring-cloud");
        System.out.println("请访问 http://localhost:18019/server/nice");
        System.out.println("请访问 http://localhost:18019/server/nice?token=12341234");
    }

    @Bean
    public MyTokenFilter myTokenFilter() {
        return new MyTokenFilter();
    }
}
