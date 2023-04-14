package af.log.controller;

import af.log.common.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/af/log/admin")
public class MonitorController {

    @Autowired
    Admin admin;
    @Autowired
    Map<Long, TimeOutLog> map;
    @Autowired
    LongAdder longAdder;

    @PostMapping("/login")
    public Result login(@RequestBody UserVo userVo, HttpSession session){
        if(!admin.getUsername().equals(userVo.getUsername()) ||
                !admin.getPassword().equals(userVo.getPassword())){
            return Result.error(400,"账号密码错误");
        }
        session.setAttribute("admin",userVo);
        return Result.success("尊敬的管理员，恭喜您登陆成功",null);
    }

    @GetMapping("/query/{page}/{size}")
    public Result query(@PathVariable("page") int page,@PathVariable("size") int size){
        int index = (page-1)*size;
        if(index<0 && index> map.size()){
            return Result.error(400,"参数出错");
        }
        List<TimeOutLog> list = map.entrySet().stream().skip(index).limit(size)
                .map((m) -> m.getValue()).collect(Collectors.toList());
        Page<TimeOutLog> logPage = new Page<>();
        logPage.setList(list);
        logPage.setNum(map.size());
        return Result.success("查询成功",logPage);
    }

    @DeleteMapping("/logout")
    public Result logout(HttpSession session){
        session.removeAttribute("admin");
        return Result.success("退出成功",null);
    }

    @PutMapping("/remove/{id}")
    public Result remove(@PathVariable("id") long id){
        map.remove(id);
        return Result.success("处理成功",null);
    }

    @GetMapping("/new-log/{size}")
    public Result newLog(@PathVariable("size") long size){
        if(size >= map.size()){
            return Result.error(201,null);
        }
        TimeOutLog timeOutLog = map.get(longAdder.sum());
        return Result.success("新的接口超时日志",timeOutLog);
    }
}
