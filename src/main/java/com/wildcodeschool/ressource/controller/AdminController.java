package com.wildcodeschool.ressource.controller;

import com.wildcodeschool.ressource.entity.Admin;
import com.wildcodeschool.ressource.entity.Role;
import com.wildcodeschool.ressource.repository.AdminRepository;
import com.wildcodeschool.ressource.repository.RoleRepository;
import org.hibernate.tool.schema.internal.exec.GenerationTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/admin")
    public String adminLogin() {
        return "admin_login";
    }

    @GetMapping("/admin/admin")
    public String adminAdmin(Model model) {

        List<Admin> admins = adminRepository.findAll();
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("admins", admins);
        model.addAttribute("roles", roles);
        return "admin_admin";
    }

    @PostMapping("/admin/admin/create")
    public String adminCreate(@ModelAttribute Admin admin) {
        return "redirect:/admin/admin";
    }

    @GetMapping("/admin/companies")
    public String adminCompanies() {
        return "admin-companies";
    }
  
    @GetMapping("/admin/product")
    public String adminProduct() {
        return "productAdmin";
    }
}
