package af.log.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "log.admin")
public class Admin {

    private String username="admin";

    private String password="123456";
}
