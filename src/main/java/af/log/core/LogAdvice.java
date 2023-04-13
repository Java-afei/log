package af.log.core;

import af.log.annotation.LogTime;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Data
@Slf4j
@Aspect
@Component
@ConfigurationProperties(prefix = "log.time")
public class LogAdvice {

    @Autowired
    private LogTimeout logTimeout;

    public int pale=0;

    public boolean enableConfig=false;

    @Around("@annotation(logTime)")
    public Object roundLog(ProceedingJoinPoint joinPoint,LogTime logTime) throws Throwable {
        long start = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> aClass = method.getDeclaringClass();
        String classTag = "";
        long classMaxRT = -1l;
        if (aClass.isAnnotationPresent(LogTime.class)) {
            LogTime classAnnotation = aClass.getAnnotation(LogTime.class);
            classTag = classAnnotation.tag();
            classMaxRT = classAnnotation.maxRT();
        }
        String methodTag = "";
        long methodMaxRT = -1l;
        if (method.isAnnotationPresent(LogTime.class)) {
            LogTime annotation = method.getAnnotation(LogTime.class);
            methodTag = annotation.tag();
            methodMaxRT = annotation.maxRT();
        }
        if(!(aClass.isAnnotationPresent(LogTime.class) || method.isAnnotationPresent(LogTime.class))){
            Object result = joinPoint.proceed();
            return result;
        }
        log.info("已经进入{}",methodTag==""? classTag : methodTag);
        Object result = joinPoint.proceed();
        log.info("已经执行完成{}",methodTag==""? classTag : methodTag);
        long end = System.currentTimeMillis();
        log.info("{}接口耗时为:{}毫秒",methodTag==""? classTag : methodTag,end-start);
        if(methodMaxRT!=-1l && (end-start)>=methodMaxRT){
            log.error("{}该接口响应时间超出预期,需引起注意,时间为：{}毫秒",
                    methodTag==""? classTag : methodTag,end-start);
            logTimeout.handle(method,end-start);
        }else if(classMaxRT!=-1l && (end-start)>=classMaxRT){
            log.error("{}该接口响应时间超出预期,需引起注意,时间为：{}毫秒",
                    methodTag==""? classTag : methodTag,end-start);
            logTimeout.handle(method,end-start);
        }
        if(enableConfig && (end-start)>=pale*1l){
            log.error("{}该接口响应时间超出预期,需引起注意,时间为：{}毫秒",
                    methodTag==""? classTag : methodTag,end-start);
            logTimeout.handle(method,end-start);

        }
        return result;
    }

}
