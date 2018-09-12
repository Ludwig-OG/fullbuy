package com.we.fullbuy.controller;

import com.we.fullbuy.pojo.Sales;
import com.we.fullbuy.service.SalesService;
import com.we.fullbuy.utils.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/sales")
public class SalesController {
    @Resource
    private SalesService salesService;

    //登录
    @RequestMapping("/login")
    @ResponseBody
    public int login(@RequestParam("phone") String phone, @RequestParam("password") String password, HttpSession session) {

        String cpassword = MD5Util.md5(password);
        System.out.println(phone);
        System.out.println(password);
        System.out.println(cpassword);

        Sales sales = salesService.login(phone);

        if (sales != null) {
            if (sales.getBlack()==0) {
                if (sales.getSalaespwd().equals(password))
                {
                    session.setAttribute("salesId",sales.getSalesid());
                    return 1;//登录成功
                }
                else
                    return 2;//密码错误
            }
            else
                return 3;//黑名单
        } else
            return 0;//账号不存在
    }

    //注册
    @RequestMapping("/register")
    @ResponseBody
    public int register(@RequestBody Sales sales)
    {
        Sales check = salesService.login("1");
        if(check==null)
        {
            if(salesService.registerSales(sales)!=0)
                return 1;//注册成功
            else
                return 0;//注册失败
        }
        else
            return 2;//账号已存在
    }

    //查看商家信息
    @RequestMapping("/displaySalesDetail")
    @ResponseBody
    public Sales displaySalesDetail(HttpSession session)
    {
        return salesService.displaySalesDetail((int)session.getAttribute("salesId"));
    }

    //修改商家信息
    @RequestMapping("/modifySales")
    @ResponseBody
    public boolean modifySales(@RequestBody Sales sales, HttpSession session)
    {
        sales.setSalesid((int)session.getAttribute("salesId"));

        if(salesService.modifySales(sales)!=0)
            return true;
        else
            return false;
    }


}
