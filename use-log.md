## Log-Time

## 1.基本使用

首先在需要统计时间的接口上加上@LogTime注解

**@LogTime属性：**

- tag：标注该方法，起识别作用（**必须**）
- maxRT：自定义该方法的最大耗时时间（单位毫秒）

示例：

```java
@RestController
@RequestMapping("/com")
public class UserController {

    @LogTime(tag = "findUser",maxRT = 1L)
    @GetMapping("/user")
    public String findUser(){
        return "你好";
    }

    @LogTime(tag = "set")
    public void setUser(String name){
        System.out.println(name);
    }

    @GetMapping("/set")
    public String updateUser(){
        UserController userController = (UserController) AopContext.currentProxy();
        userController.setUser("afei");
        return "success";
    }
}
```

对于不同的方法可以给定不同的**maxRT**属性

对于所有方法一致的**maxRT**可以全局配置

```yaml
log:
  time:
    pale: 2 #maxRT时间，单位：毫秒
    enable-config: true #开启全局配置
```

## 2.超时处理

默认的超时处理是打日志

```java
@Slf4j
public class DefaultLogTimeout implements LogTimeout{

    @Override
    public void handle(Method method,long time) {
        log.info("默认的处理方法执行");
    }
}
```

可以自定义一个类实现**LogTimeout**接口重写**handle**方法：

1. method：被代理统计耗时的方法对象
2. time：方法的实际执行耗时

