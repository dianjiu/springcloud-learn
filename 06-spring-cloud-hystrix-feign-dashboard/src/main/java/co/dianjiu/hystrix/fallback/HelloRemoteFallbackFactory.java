package co.dianjiu.hystrix.fallback;

import co.dianjiu.hystrix.client.IHelloRemote;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * fallbackFactory实现类，该类需实现FallbackFactory接口，并覆写create方法，
 * 当网络不通或者访问失败时，返回固定/默认内容
 */
@Component
public class HelloRemoteFallbackFactory implements FallbackFactory<IHelloRemote> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloRemoteFallbackFactory.class);

    @Override
    public IHelloRemote create(Throwable cause) {
        return new IHelloRemote() {

            @Override
            public String hello() {
                HelloRemoteFallbackFactory.LOGGER.info("fallback; reason was:", cause);
                return "HelloRemote hello"+", 网络不通或者访问失败! ";
            }

            @Override
            public String nice() {
                HelloRemoteFallbackFactory.LOGGER.info("fallback; reason was:", cause);
                return "HelloRemote nice" +", 网络不通或者访问失败! ";
            }
        };
    }

}

