package af.log.core;

import java.lang.reflect.Method;

public interface LogTimeout {

    void handle(Method method,long time);
}
