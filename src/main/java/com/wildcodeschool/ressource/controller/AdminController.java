package com.wildcodeschool.ressource.controller;


import com.wildcodeschool.ressource.entity.Admin;
import com.wildcodeschool.ressource.entity.Role;
import com.wildcodeschool.ressource.repository.AdminRepository;
import com.wildcodeschool.ressource.repository.RoleRepository;
import com.wildcodeschool.ressource.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CompanyRepository companyRepository;

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


    @GetMapping("/admin")
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
                              @RequestParam String role) {

        Role role1 = roleRepository.findByRole(role).get();
        admin.setRole(role1);
        adminRepository.save(admin);

        return "redirect:/admin/admin";
    }

    @GetMapping("/admin/companies")
    public String adminCompanies() {
        return "admin-companies";
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
        model.addAttribute("fabrics",fabricRepository.findAll());
        model.addAttribute("handfeels", handFeelRepository.findAll());
        model.addAttribute("finishings", finishingRepository.findAll());
        model.addAttribute("looks", lookRepository.findAll());

        return "productAdmin";
    }
}
