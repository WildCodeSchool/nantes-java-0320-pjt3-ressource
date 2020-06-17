package com.wildcodeschool.ressource.controller;

import com.wildcodeschool.ressource.entity.Company;
import com.wildcodeschool.ressource.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/company")
    public String findCompanyById(@RequestParam Long id,
                                   Model model) {

        Company company = companyRepository.findById(id).get();
        model.addAttribute("company", company);

        return "company";
    }
}
