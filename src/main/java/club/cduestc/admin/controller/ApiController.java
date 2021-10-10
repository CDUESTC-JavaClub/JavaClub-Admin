package club.cduestc.admin.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping("/login")
    public JSONObject login(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("remember-me") String remember_me){
        JSONObject object = new JSONObject();
        return object;
    }
}
