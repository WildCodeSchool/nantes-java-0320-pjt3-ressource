package com.wildcodeschool.ressource.controller;

import com.wildcodeschool.ressource.entity.Company;
import com.wildcodeschool.ressource.repository.CompanyRepository;
import org.hibernate.tool.schema.internal.exec.GenerationTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

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
            Optional<Company> optionalCompany = companyRepository.findByName(name);
            if (optionalCompany.isPresent()) {
                Company companySelected = optionalCompany.get();
                model.addAttribute("companySelected", companySelected);
            }
        }
        return "admin-company-selected";
    }

    @PostMapping("admin/companies")
    public String postCompany(Company companySelected) {

        Optional<Company> optionalCompany = companyRepository.findByName(companySelected.getName());
        if (optionalCompany.isPresent()) {
            Company companyModified = optionalCompany.get();
            companySelected.setId(companyModified.getId());
            companyRepository.save(companySelected);
        } else {
            companyRepository.save(companySelected);
        }
        return "redirect:/admin/companies";
    }

    @GetMapping("/admin/product")
    public String adminProduct() {
        return "productAdmin";
    }
}
