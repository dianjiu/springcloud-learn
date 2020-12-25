# Spring Cloud Zuul
在Spring Cloud体系中， Spring Cloud Zuul就是提供负载均衡、反向代理、权限认证的一个API gateway。  

## 一、框架理解
Spring Cloud Zuul路由是微服务架构的不可或缺的一部分，提供动态路由，监控，弹性，安全等的边缘服务。  
Zuul是Netflix出品的一个基于JVM路由和服务端的负载均衡器。

## 二、快速上手
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

## 三、管理端点
当@EnableZuulProxy与Spring Boot Actuator配合使用时,Zuul会暴露两个端点：  
一个路由管理端点/actuator/routes和/actuator/filters，借助这两个端点，可以方便、直观地查看以及管理Zuul的路由。
spring-cloud-starter-netflix-zuul已经包含了spring-boot-starter-actuator，因此不需再次引入  

1、修改application.yml,暴露端点  
```yml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

2、重启网关  
访问：http://localhost:18019/actuator/hystrix.stream 成功，  
说明已经zuul已经整合了hystrix。  

3、查看routes端点  
访问：http://localhost:8060/actuator/routes 可以查看路由设置。
  

4、查看filters端点  
访问：http://localhost:8060/actuator/filters 可以查看过滤器端点


5、解释说明  
从SpringCloud Edgware版本开始，Zuul提供了filters端点。  
访问该端点即可返回Zuul 中当前所有过滤器的详情，并按照类型分类。  
上面是filters端点的展示结果，从中，  
我们可以了解当前Zuul中，error、post、pre、route四种类型的过滤器分别有哪些，  
每个过滤器的order（执行顺序）是多少，以及是否启用等信息。这对Zul问题的定位很有用  