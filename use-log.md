## Log-Time

## 1.基本使用

在启动上加上**@ComponentScan(basePackages = {"af.*","自己的项目包"})**

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

默认的超时处理是将超时日志记录到Map集合，推荐自己重写handle方法

```java
@Slf4j
public class DefaultLogTimeout implements LogTimeout{

    @Override
    public void handle(Method method,long time,String methodTag,long fix) {
        log.info("默认的处理方法执行");
    }
}
```

可以自定义一个类实现**LogTimeout**接口重写**handle**方法：

1. method：被代理统计耗时的方法对象
2. time：方法的实际执行耗时
2. methodTag：方法上的标签
2. fix：规定的方法执行时间

## 3.默认监控

项目启动后访问127.0.0.1:8080/html/login.html

默认账号：admin

默认密码：123456

也可以自己配置账号密码：

```yaml
log:
 admin:
  username: afei #要设置的账号
  password: 666 #要设置的密码
```

可以自定义一个类实现**MonitorService**接口重写方法实现自定义的业务逻辑
