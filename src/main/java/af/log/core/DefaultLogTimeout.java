package af.log.core;

import af.log.common.DateFormatUtils;
import af.log.common.TimeOutLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
public class DefaultLogTimeout implements LogTimeout{

    @Autowired
    LongAdder longAdder;
    @Autowired
    Map<Long, TimeOutLog> map;

    @Override
    public void handle(Method method,long time,String methodTag,long fix) {
//        log.info("默认的处理方法执行");
        longAdder.increment();
        TimeOutLog timeOutLog = new TimeOutLog();
        timeOutLog.setTime(time);
        Long id = longAdder.sum();
        timeOutLog.setId(id);
        timeOutLog.setMethodName(methodTag);
        timeOutLog.setFixTime(fix);
        timeOutLog.setDate(DateFormatUtils.getFormat());
        map.put(id,timeOutLog);
//        System.out.println(map);
    }
}
