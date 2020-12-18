package co.dianjiu.hystrix.client;

import co.dianjiu.hystrix.fallback.HelloRemoteFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IHelloService
 * 配置服务提供者：single-provider 是服务提供者的 application.name
 */
@FeignClient(name = "server-provider",fallbackFactory = HelloRemoteFallbackFactory.class )
//@FeignClient(name = "server-provider",fallback = HelloRemoteFallback.class )
public interface IHelloRemote {

    @RequestMapping(value = "/hello")
    String hello();

    @RequestMapping(value = "nice")
    String nice();
}
