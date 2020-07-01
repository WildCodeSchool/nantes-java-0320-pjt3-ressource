package com.wildcodeschool.ressource.controller;

import com.wildcodeschool.ressource.entity.Company;
import com.wildcodeschool.ressource.repository.CompanyRepository;
import org.hibernate.tool.schema.internal.exec.GenerationTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/admin")
    public String adminLogin() {
        return "admin_login";
    }

    @GetMapping("/admin/admin")
    public String adminAdmin() {
        return "admin_admin";
    }

    @GetMapping("/admin/companies")
    public String adminCompanies(Model model) {

        List<Company> companyList = companyRepository.findAll();
        model.addAttribute("companies", companyList);
        return "admin-companies";
    }

    @GetMapping("/admin/company/{name}")
    public String getCompanySelected(@PathVariable String name, Model model) {

        if (name.equals("new")) {
            Company company = new Company();
            model.addAttribute("companySelected", company);
        } else {
            Company companySelected = companyRepository.findByName(name);
            model.addAttribute("companySelected", companySelected);
        }
        return "admin-company-selected";
    }

    @GetMapping("/admin/product")
    public String adminProduct() {
        return "productAdmin";
    }
}
