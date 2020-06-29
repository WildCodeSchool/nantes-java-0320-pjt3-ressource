package com.wildcodeschool.ressource.controller;

import org.hibernate.tool.schema.internal.exec.GenerationTarget;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminLogin() {
        return "admin_login";
    }

    @GetMapping("/admin/admin")
    public String adminAdmin() {
        return "admin_admin";
    }
}
