package af.log.config;

import af.log.common.TimeOutLog;
import af.log.core.DefaultLogTimeout;
import af.log.core.LogTimeout;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

@ServletComponentScan
@EnableAspectJAutoProxy
@EnableConfigurationProperties
@Configuration
public class LogConfig {

    @Bean
    @ConditionalOnMissingBean
    public LogTimeout logTimeout(){
        return new DefaultLogTimeout();
    }

    @Bean
    public Map<Long, TimeOutLog> setMap(){
        return new ConcurrentHashMap<>();
    }

    @Bean
    public LongAdder setNum(){
        return new LongAdder();
    }
}
