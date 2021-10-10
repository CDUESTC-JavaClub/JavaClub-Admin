package club.cduestc.admin.controller;

import club.cduestc.admin.dao.AdminMapper;
import club.cduestc.admin.dao.JobEntity;
import club.cduestc.admin.dao.UserAccount;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class PageController {

    @Resource
    AdminMapper mapper;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/jobs")
    public String jobs(Model model){
        Subject subject = SecurityUtils.getSubject();
        String userId = (String) subject.getPrincipal();
        UserAccount account = mapper.getAccountByStudentId(userId);
        model.addAttribute("username", account.getName()+"("+account.getStudent_id()+")");
        model.addAttribute("jobs", mapper.getPublishedJob(userId));
        return "jobs";
    }
}
