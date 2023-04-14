package af.log.controller;

import af.log.common.Result;
import af.log.common.UserVo;

import javax.servlet.http.HttpServletRequest;

public interface MonitorService {


    Result login(UserVo userVo, HttpServletRequest request);

    Result query(int page, int size);

    Result logout(HttpServletRequest request);

    Result remove(long id);

    Result newLog(long size);
}
