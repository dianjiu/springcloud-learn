# Spring Cloud Zuul
在Spring Cloud体系中， Spring Cloud Zuul就是提供负载均衡、反向代理、权限认证的一个API gateway。  

## 框架理解
Spring Cloud Zuul路由是微服务架构的不可或缺的一部分，提供动态路由，监控，弹性，安全等的边缘服务。  
Zuul是Netflix出品的一个基于JVM路由和服务端的负载均衡器。

## 快速上手
1、添加依赖    
引入spring-cloud-starter-zuul包  
```xml
<!--引入网关依赖-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>
<!--引入注册中心依赖-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
2、配置文件   
```yml
server:
  port: 18019
spring:
  application:
    name:  spring-cloud-zuul
# 网关会注册到注册中心
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8888/eureka/
```
3、启动类  
在启动类上添加注解@EnableZuulProxy ，声明一个Zuul代理。  
该代理使用Ribbon来定位注册在Eureka Server中的微服务；  
同时，该代理还整合了 Hystrix,从而实现了容错，所有经过Zuul的请求都会在Hystrix命令中执行。  
```java
@EnableZuulProxy
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```
4、测试步骤  
启动项目 注册中心 01-spring-cloud-eureka  
启用服务提供者 02-spring-cloud-server-provider  
启动服务消费者  04-spring-cloud-server-consumer-feign  
启动网关 20-spring-cloud-zuul  
测试访问 http://localhost:18019/server-provider/nice  
测试访问 http://localhost:18019/server-consumer-feign/nice  
5、解释说明
说明默认情况下，Zuul会代理所有注册到Eureka Server的微服务，并且Zuul的路由规则如下：  
http://ZUUL_HOST:ZUUL_PORT/微服务在Eureka 注册中心上的serviced/** 会被转发到 serviceld 对应的微服务。