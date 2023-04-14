package af.log.controller;

import af.log.common.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    MonitorService monitorService;

    @PostMapping("/login")
    public Result login(@RequestBody UserVo userVo, HttpSession session, HttpServletRequest request){
        return monitorService.login(userVo,request);
    }

    @GetMapping("/query/{page}/{size}")
    public Result query(@PathVariable("page") int page,@PathVariable("size") int size){
        return monitorService.query(page,size);
    }

    @DeleteMapping("/logout")
    public Result logout(HttpSession session,HttpServletRequest request){
        return monitorService.logout(request);
    }

    @PutMapping("/remove/{id}")
    public Result remove(@PathVariable("id") long id){
        return monitorService.remove(id);
    }

    @GetMapping("/new-log/{size}")
    public Result newLog(@PathVariable("size") long size){
        return monitorService.newLog(size);
    }
}
