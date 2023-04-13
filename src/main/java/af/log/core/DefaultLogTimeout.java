package af.log.core;

import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Method;

@Slf4j
public class DefaultLogTimeout implements LogTimeout{

    @Override
    public void handle(Method method,long time) {
        log.info("默认的处理方法执行");
    }
}
