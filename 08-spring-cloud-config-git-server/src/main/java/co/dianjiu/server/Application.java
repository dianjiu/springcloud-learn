package co.dianjiu.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class Application {

    /**
     * {application} 就是应用名称，对应到配置文件上来，就是配置文件的名称部分，例如我上面创建的配置文件。
     *
     * {profile} 就是配置文件的版本，我们的项目有开发版本、测试环境版本、生产环境版本，对应到配置文件上来就是以 application-{profile}.yml 加以区分，例如application-dev.yml、application-sit.yml、application-prod.yml。
     *
     * {label} 表示 git 分支，默认是 master 分支，如果项目是以分支做区分也是可以的，那就可以通过不同的 label 来控制访问不同的配置文件了。
     */
    public static void main(String[] args) {
        System.setProperty("cfg.env","test");
        SpringApplication.run(Application.class, args);
        // /{application}/{profile}[/{label}]
        System.out.println("请访问  http://localhost:18007/config-git-client/test");
        System.out.println("请访问  http://localhost:18007/config-git-client/test/master");
        // /{application}-{profile}.yml
        System.out.println("请访问  http://localhost:18007/config-git-client-test.yml");
        ///{label}/{application}-{profile}.yml
        System.out.println("请访问  http://localhost:18007/master/config-git-client-test.yml");
        // /{application}-{profile}.properties
        //System.out.println("请访问  http://localhost:18007/config-git-client-test.properties");
        // /{label}/{application}-{profile}.properties
        //System.out.println("请访问  http://localhost:18007/master/config-git-client-test.properties");
    }

}
