package af.log.config;

import af.log.core.DefaultLogTimeout;
import af.log.core.LogTimeout;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@EnableConfigurationProperties
@Configuration
public class LogConfig {

    @Bean
    @ConditionalOnMissingBean
    public LogTimeout logTimeout(){
        return new DefaultLogTimeout();
    }
}
