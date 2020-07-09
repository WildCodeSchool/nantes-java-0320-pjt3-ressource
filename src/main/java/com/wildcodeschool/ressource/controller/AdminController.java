package com.wildcodeschool.ressource.controller;


import com.wildcodeschool.ressource.entity.Admin;
import com.wildcodeschool.ressource.entity.Company;
import com.wildcodeschool.ressource.entity.Role;
import com.wildcodeschool.ressource.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private FabricPatternRepository fabricPatternRepository;

    @Autowired
    private FiberRepository fiberRepository;

    @Autowired
    private OriginRepository originRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private CareLabelRepository careLabelRepository;

    @Autowired
    private CertificationRepository certificationRepository;

    @Autowired
    private TechnicalPropertyRepository technicalPropertyRepository;

    @Autowired
    private FabricRepository fabricRepository;

    @Autowired
    private HandFeelRepository handFeelRepository;

    @Autowired
    private FinishingRepository finishingRepository;

    @Autowired
    private LookRepository lookRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String adminLogin() {
        return "admin_login";
    }

    @GetMapping("/admin/admin")
    public String adminAdmin(Model model) {

        List<Admin> admins = adminRepository.findAll();
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("admin", new Admin());
        model.addAttribute("admins", admins);
        model.addAttribute("roles", roles);
        return "admin_admin";
    }

    @PostMapping("/admin/admin/create")
    public String adminCreate(@ModelAttribute Admin admin,
                              @RequestParam Long role) {

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        Optional<Role> optionalRole = roleRepository.findById(role);

        if (optionalRole.isPresent()) {
            admin.setRole(optionalRole.get());
            adminRepository.save(admin);
        }

        return "redirect:/admin/admin";
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
    public String adminProduct(Model model) {

        model.addAttribute("companies", companyRepository.findAll());
        model.addAttribute("materials", materialRepository.findAll());
        model.addAttribute("fabricPatterns", fabricPatternRepository.findAll());
        model.addAttribute("fibers", fiberRepository.findAll());
        model.addAttribute("origins", originRepository.findAll());
        model.addAttribute("prices", priceRepository.findAll());
        model.addAttribute("carelabels", careLabelRepository.findAll());
        model.addAttribute("certifications", certificationRepository.findAll());
        model.addAttribute("technicalProperties", technicalPropertyRepository.findAll());
        model.addAttribute("fabrics", fabricRepository.findAll());
        model.addAttribute("handfeels", handFeelRepository.findAll());
        model.addAttribute("finishings", finishingRepository.findAll());
        model.addAttribute("looks", lookRepository.findAll());

        return "productAdmin";
    }

    @PostMapping("/admin/admin/delete")
    public String deleteAdmin(@RequestParam Long idAdmin) {
        adminRepository.deleteById(idAdmin);
        return "redirect:/admin/admin";
    }
}
