package co.dianjiu.gateway.filter;

import co.dianjiu.gateway.util.IpUtil;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class IpLimitFilter implements GlobalFilter, Ordered {


    public static final String WARNING_MSG = "请求过于频繁，请稍后再试";

    public static final Map<String, Bucket> LOCAL_CACHE = new ConcurrentHashMap<>();

    // 令牌桶初始容量
    public static final long capacity = 2;

    // 补充桶的时间间隔，即2秒补充一次
    public static final long seconds = 2;

    // 每次补充token的个数
    public static final long refillTokens = 1;



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String ip = IpUtil.getIpAddr(request);

        log.info("访问IP为:{}", ip);

        Bucket bucket = LOCAL_CACHE.computeIfAbsent(ip, k -> createNewBucket(ip));

        log.info("IP:{} ,令牌通可用的Token数量:{} ", ip, bucket.getAvailableTokens());

        if (bucket.tryConsume(1)) {
            // 直接放行
            return chain.filter(exchange);
        } else {

            byte[] bits = WARNING_MSG.getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            //指定编码，否则在浏览器中会中文乱码
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }

    }

    @Override
    public int getOrder() {
        return 1;
    }


    private Bucket createNewBucket(String ip) {
        Duration refillDuration = Duration.ofSeconds(seconds);
        Refill refill = Refill.of(refillTokens, refillDuration);
        Bandwidth limit = Bandwidth.classic(capacity, refill);
        return Bucket4j.builder().addLimit(limit).build();
    }
}
