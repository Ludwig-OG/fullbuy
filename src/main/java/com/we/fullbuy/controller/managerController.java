package com.we.fullbuy.controller;

import com.we.fullbuy.service.managerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manager")
public class managerController {
    private managerService mService;

    //管理员登录
    @RequestMapping("/login")
    @ResponseBody
    public int login(@RequestParam("managerId") int managerId, @RequestParam("password") String password)
    {

        //String cpassword = MD5Util.md5(password);
        System.out.println(managerId);
        System.out.println(password);
        int result = mService.login(managerId,password);
        if(result!=0)
            return 1;
        else
            return 0;
    }

}
