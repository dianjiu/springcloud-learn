package co.dianjiu.gateway.predicate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
@Component
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {


    public CheckAuthRoutePredicateFactory() {
        super(Config.class);
        log.info("Loaded RoutePredicateFactory [CheckAuth]");
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name");
    }


    @Override
    public Predicate<ServerWebExchange> apply(Config config) {

        return exchange -> {
            if (config.getName().equals("zhangsan")) {
                return true;
            }
            return false;
        };
    }


    public static class Config {

        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
