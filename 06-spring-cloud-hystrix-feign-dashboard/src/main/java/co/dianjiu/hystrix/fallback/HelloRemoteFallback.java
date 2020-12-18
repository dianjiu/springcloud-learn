package co.dianjiu.hystrix.fallback;

import co.dianjiu.hystrix.client.IHelloRemote;
import org.springframework.stereotype.Component;

@Component
public class HelloRemoteFallback implements IHelloRemote {
    @Override
    public String hello() {
        return "HelloRemote hello"+", 网络不通或者访问失败! ";
    }

    @Override
    public String nice() {
        return "HelloRemote nice" +", 网络不通或者访问失败! ";
    }
}
