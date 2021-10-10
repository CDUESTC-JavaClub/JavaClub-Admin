package club.cduestc.admin.controller;

import club.cduestc.admin.dao.AdminMapper;
import club.cduestc.admin.dao.UserAccount;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    AdminMapper mapper;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("remember_me") boolean remember_me){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, remember_me);
        try {
            subject.login(token);
            UserAccount account = mapper.getAccountByStudentId(username);
            return new Response(200, "/"+account.getRole());
        }catch (Exception e){
            return new Response(401, e.getMessage());
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
