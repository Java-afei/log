package af.log.controller;

import af.log.common.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class MonitorServiceImpl implements MonitorService{

    @Autowired
    Admin admin;
    @Autowired
    Map<Long, TimeOutLog> map;
    @Autowired
    LongAdder longAdder;
    @Autowired
    MonitorService monitorService;

    @Override
    public Result login(UserVo userVo, HttpServletRequest request) {
        if(!admin.getUsername().equals(userVo.getUsername()) ||
                !admin.getPassword().equals(userVo.getPassword())){
            return Result.error(400,"账号密码错误");
        }
        HttpSession session = request.getSession();
        session.setAttribute("admin",userVo);
        return Result.success("尊敬的管理员，恭喜您登陆成功",null);
    }

    @Override
    public Result query(int page, int size) {
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

    @Override
    public Result logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        return Result.success("退出成功",null);
    }

    @Override
    public Result remove(long id) {
        map.remove(id);
        return Result.success("处理成功",null);
    }

    @Override
    public Result newLog(long size) {
        if(size >= map.size()){
            return Result.error(201,null);
        }
        TimeOutLog timeOutLog = map.get(longAdder.sum());
        return Result.success("新的接口超时日志",timeOutLog);
    }


}
