package club.cduestc.admin.controller;

import club.cduestc.admin.dao.AdminMapper;
import club.cduestc.admin.dao.UserAccount;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Resource
    AdminMapper mapper;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("remember_me") boolean remember_me,
                          HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, remember_me);
        try {
            subject.login(token);
            UserAccount account = mapper.getAccountByStudentId(username);
            session.setAttribute("account", account);
            return new Response(200, "/"+account.getRole());
        }catch (Exception e){
            return new Response(401, "登陆失败，用户名或密码错误！");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:../login";
    }

    @RequestMapping(value = "/delete-job", method = RequestMethod.GET)
    public String deleteJob(@RequestParam("id") int id){
        Subject subject = SecurityUtils.getSubject();
        String userId = (String) subject.getPrincipal();
        mapper.deleteJob(id, userId);
        return "redirect:../jobs";
    }

    @RequestMapping(value = "/publish-job", method = RequestMethod.POST)
    @ResponseBody
    public void addJob(@RequestParam("title") String title,
                         @RequestParam("host") String host,
                         @RequestParam("local") String local,
                         @RequestParam("desc") String desc,
                         @RequestParam("type") String type,
                         @RequestParam("min") int min,
                         @RequestParam("max") int max,
                         @RequestParam("unit") String unit,
                         @RequestParam("contact") String contact){
        Subject subject = SecurityUtils.getSubject();
        String userId = (String) subject.getPrincipal();
        if(!contact.startsWith("http")) contact = "mqqwpa://im/chat?chat_type=wpa&uin=" + contact;
        mapper.addJob(title, host, local, desc, type, min, max, unit, contact, userId);
    }

    @RequestMapping("/confirm-item")
    @ResponseBody
    public void confirmItem(@RequestParam("id") int id){
        mapper.confirmItem(id);
    }

    @RequestMapping("/cancel-item")
    @ResponseBody
    public void cancelItem(@RequestParam("id") int id){
        mapper.cancelItem(id);
    }
}
