package com.wildcodeschool.ressource.controller;

import org.hibernate.tool.schema.internal.exec.GenerationTarget;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/admin")
    public String adminAdmin() {
        return "admin_admin";
    }


    @GetMapping("/admin/product")
    public String adminProduct() {
        return "productAdmin";
    }
}
