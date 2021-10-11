package club.cduestc.admin.controller;

import club.cduestc.admin.dao.AdminMapper;
import club.cduestc.admin.dao.ItemDetailEntity;
import club.cduestc.admin.dao.UserAccount;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {

    @Resource
    AdminMapper mapper;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/jobs")
    public String jobs(Model model,
                       HttpSession session){
        UserAccount account = (UserAccount) session.getAttribute("account");
        model.addAttribute("username", account.getName()+"("+account.getStudent_id()+")");
        model.addAttribute("jobs", mapper.getPublishedJob(account.getStudent_id()));
        return "jobs";
    }

    @RequestMapping("/market")
    public String market(Model model,
                         HttpSession session){
        UserAccount account = (UserAccount) session.getAttribute("account");
        model.addAttribute("username", account.getName()+"("+account.getStudent_id()+")");
        model.addAttribute("items", mapper.getAllItem());
        return "market";
    }

    @RequestMapping(value = "/market-detail", method = RequestMethod.GET)
    public String detail(@RequestParam("id") int id,
                         Model model,
                         HttpSession session){
        UserAccount account = (UserAccount) session.getAttribute("account");
        ItemDetailEntity item = mapper.getDetailItem(id);
        model.addAttribute("username", account.getName()+"("+account.getStudent_id()+")");
        model.addAttribute("item", item);
        model.addAttribute("images", JSONArray.parseArray(item.getImages()));
        return "market-detail";
    }
}
