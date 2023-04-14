package af.log.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = {"/af/log/admin/*"})
@Component
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if(request.getRequestURI().length()<"/af/log/admin/".length()){
            filterChain.doFilter(request,servletResponse);
            return;
        }
        if("/af/log/admin/login".equals(request.getRequestURI())){
            filterChain.doFilter(request,servletResponse);
            return;
        }
        if(request.getRequestURI().substring(0,"/af/log/admin/".length()).equals("/af/log/admin/") &&
                session.getAttribute("admin")==null){
            response.sendRedirect("/html/login.html");
            return;
        }
        filterChain.doFilter(request,servletResponse);
    }
}
