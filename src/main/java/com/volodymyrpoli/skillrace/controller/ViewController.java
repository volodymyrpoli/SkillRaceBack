package com.volodymyrpoli.skillrace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping({
            "/",
            "work", "work/grid", "work/dashboard",
            "admin", "admin/editor", "admin/dashboard", "admin/users/accounts", "admin/settings", "admin/users/results",
            "login"
    })
    public String index() {
        return "forward:/index.html";
    }
}
