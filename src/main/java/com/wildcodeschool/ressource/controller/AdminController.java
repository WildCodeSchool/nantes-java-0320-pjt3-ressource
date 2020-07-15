package com.wildcodeschool.ressource.controller;


import com.wildcodeschool.ressource.entity.*;
import com.wildcodeschool.ressource.repository.*;
import com.wildcodeschool.ressource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private ProductRepository productRepository;

    @Autowired
    private CompositionRepository compositionRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String adminLogin() {
        return "admin_login";
    }


    @GetMapping("/admin/profile")
    public String adminProfile(Model model) {
        Admin admin = userService.getLoggedUsername();
        model.addAttribute("admin", admin);
        return "admin_profile";
    }

    @PostMapping("/admin/profile")
    public String changePsw(@RequestParam String password) {
        Admin admin = userService.getLoggedUsername();
        admin.setPassword(passwordEncoder.encode(password));
        adminRepository.save(admin);
        return "redirect:/admin/profile";
    }

    @GetMapping("/admin/admin")
    public String adminAdmin(Model model) {

        Admin admin = userService.getLoggedUsername();
        model.addAttribute("admin", admin);

        List<Admin> admins = adminRepository.findAllByOrderByIdDesc();

        List<Role> roles = roleRepository.findAll();
        model.addAttribute("adminNew", new Admin());
        model.addAttribute("admins", admins);
        model.addAttribute("roles", roles);
        return "admin_admin";
    }

    @PostMapping("/admin/admin/create")
    public String adminCreate(@RequestParam(defaultValue = "", required = false) Long id,
                              @RequestParam String username,
                              @RequestParam String email,
                              @RequestParam(defaultValue = "", required = false) String password,
                              @RequestParam Long roleId) {

        Admin admin = new Admin();
        if (id != null) {
            if(adminRepository.findById(id).isPresent()) {
                admin = adminRepository.findById(id).get();
            }
        }

        admin.setEmail(email);
        admin.setUsername(username);
        if (!password.equals("")){
            admin.setPassword(passwordEncoder.encode(password));
        }

        Optional<Role> optionalRole = roleRepository.findById(roleId);

        if (optionalRole.isPresent()) {
            admin.setRole(optionalRole.get());
            adminRepository.save(admin);
        }

        return "redirect:/admin/admin";
    }

    @GetMapping("/admin/companies")
    public String adminCompanies(Model model) {

        Admin admin = userService.getLoggedUsername();
        List<Company> companyList = companyRepository.findAll();
        model.addAttribute("companies", companyList);
        model.addAttribute("admin", admin);
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

    @GetMapping("/admin/product")
    public String adminProduct(Model model) {

        Admin admin = userService.getLoggedUsername();
        model.addAttribute("admin", admin);
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
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("product", new Product());
        return "productAdmin";
    }

    @PostMapping("/admin/products")
    public String updateProduct(@RequestParam String reference,
                                @RequestParam(defaultValue = "", required = false) Long designNumber,
                                @RequestParam(defaultValue = "", required = false) String description,
                                @RequestParam(defaultValue = "", required = false) Integer width,
                                @RequestParam(defaultValue = "", required = false) Integer weight,
                                @RequestParam(defaultValue = "", required = false) Integer pieceLength,
                                @RequestParam(defaultValue = "", required = false) Integer collectionMOQ,
                                @RequestParam(defaultValue = "", required = false) Integer productionMOQ,
                                @RequestParam(defaultValue = "", required = false) Integer collectionLeadtime,
                                @RequestParam(defaultValue = "", required = false) Integer productionLeadtime,
                                @RequestParam(defaultValue = "", required = false) String washingComments,
                                @RequestParam(defaultValue = "", required = false) Long material,
                                @RequestParam(value = "certifications", required = false) Long[] certifications,
                                @RequestParam(value = "careLabels", required = false) Long[] careLabels,
                                @RequestParam(defaultValue = "", required = false) Long origin,
                                @RequestParam(defaultValue = "", required = false) Long price,
                                @RequestParam(defaultValue = "", required = false) Long fabricPattern,
                                @RequestParam(defaultValue = "", required = false) Long company,
                                @RequestParam(value = "products", required = false) Long[] products,
                                @RequestParam(defaultValue = "0", required = false) Long fiber1,
                                @RequestParam(defaultValue = "0.0", required = false) Double pourcentageFiber1,
                                @RequestParam(defaultValue = "0", required = false) Long fiber2,
                                @RequestParam(defaultValue = "0.0", required = false) Double pourcentageFiber2,
                                @RequestParam(defaultValue = "0", required = false) Long fiber3,
                                @RequestParam(defaultValue = "0.0", required = false) Double pourcentageFiber3,
                                @RequestParam(defaultValue = "0", required = false) Long fiber4,
                                @RequestParam(defaultValue = "0.0", required = false) Double pourcentageFiber4,
                                @RequestParam(defaultValue = "0", required = false) Long fiber5,
                                @RequestParam(defaultValue = "0.0", required = false) Double pourcentageFiber5,
                                @RequestParam(defaultValue = "0", required = false) Long fiber6,
                                @RequestParam(defaultValue = "0.0", required = false) Double pourcentageFiber6,
                                @RequestParam(value = "technicalProperty", required = false) Long[] technicalProperty,
                                @RequestParam(defaultValue = "", required = false) Long fabric,
                                @RequestParam(defaultValue = "", required = false) Long handFeel,
                                @RequestParam(defaultValue = "", required = false) Long finishing,
                                @RequestParam(defaultValue = "", required = false) Long look) {

        Optional<Product> optionalProductToUpdate = productRepository.findByReference(reference);

        Product productToUpdate = new Product();

        if (optionalProductToUpdate.isPresent()) {
            productToUpdate = optionalProductToUpdate.get();
        } else {
            productToUpdate.setReference(reference);
        }

        if (certifications != null) {
            List<Certification> certificationList = certificationRepository.findAllById(Arrays.asList(certifications));
            productToUpdate.setCertifications(certificationList);
        }
        if (careLabels != null) {
            List<CareLabel> careLabelList = careLabelRepository.findAllById(Arrays.asList(careLabels));
            productToUpdate.setCareLabels(careLabelList);
        }
        if (products != null) {
            List<Product> productList = productRepository.findAllById(Arrays.asList(products));
            productToUpdate.setProducts(productList);
        }

        productToUpdate.setDesignNumber(designNumber);
        productToUpdate.setDescription(description);
        productToUpdate.setWidth(width);
        productToUpdate.setWeight(weight);
        productToUpdate.setPieceLength(pieceLength);
        productToUpdate.setCollectionMOQ(collectionMOQ);
        productToUpdate.setProductionMOQ(productionMOQ);
        productToUpdate.setCollectionLeadtime(collectionLeadtime);
        productToUpdate.setProductionLeadtime(productionLeadtime);
        productToUpdate.setWashingComments(washingComments);
        if (material != null) {
            productToUpdate.setMaterial(materialRepository.findById(material).get());
        }
        if (origin != null) {
            productToUpdate.setOrigin(originRepository.findById(origin).get());
        }
        if (price != null) {
            productToUpdate.setPrice(priceRepository.findById(price).get());
        }
        if (fabricPattern != null) {
            productToUpdate.setFabricPattern(fabricPatternRepository.findById(fabricPattern).get());
        }
        if (company != null) {
            productToUpdate.setCompany(companyRepository.findById(company).get());
        }
        productToUpdate = productRepository.save(productToUpdate);

        Feature newFeature = new Feature();

        if (productToUpdate.getFeature() != null) {
            newFeature = productToUpdate.getFeature();
        }

        if (technicalProperty != null) {
            List<TechnicalProperty> technicalPropertyList = technicalPropertyRepository.findAllById(Arrays.asList(technicalProperty));
            newFeature.setTechnicalProperties(technicalPropertyList);
        }
        if (fabric != null) {
            newFeature.setFabric(fabricRepository.findById(fabric).get());
        }

        if (handFeel != null) {
            newFeature.setHandFeel(handFeelRepository.findById(handFeel).get());
        }
        if (finishing != null) {
            newFeature.setFinishing(finishingRepository.findById(finishing).get());
        }

        if (look != null) {
            newFeature.setLook(lookRepository.findById(look).get());
        }

        Feature feature = featureRepository.save(newFeature);

        productToUpdate.setFeature(feature);

        List<Composition> compositionList = new ArrayList<>();

        compositionRepository.deleteByProductId(productToUpdate.getId());

        if (fiber1 != 0) {
            compositionList.add(new Composition(fiberRepository.findById(fiber1).get(), pourcentageFiber1, productToUpdate));
        }
        if (fiber2 != 0) {
            compositionList.add(new Composition(fiberRepository.findById(fiber2).get(), pourcentageFiber2, productToUpdate));
        }
        if (fiber3 != 0) {
            compositionList.add(new Composition(fiberRepository.findById(fiber3).get(), pourcentageFiber3, productToUpdate));
        }
        if (fiber4 != 0) {
            compositionList.add(new Composition(fiberRepository.findById(fiber4).get(), pourcentageFiber4, productToUpdate));
        }
        if (fiber5 != 0) {
            compositionList.add(new Composition(fiberRepository.findById(fiber5).get(), pourcentageFiber5, productToUpdate));
        }
        if (fiber6 != 0) {
            compositionList.add(new Composition(fiberRepository.findById(fiber6).get(), pourcentageFiber6, productToUpdate));
        }

        compositionRepository.saveAll(compositionList);
        return "redirect:/admin/product";
    }

    @PostMapping("/admin/products/search")
    public String productSearch(Model model, @RequestParam String reference) {

        Product productModified = new Product();

        Optional<Product> optionalProduct = productRepository.findByReference(reference);
        if (optionalProduct.isPresent()) {
            productModified = optionalProduct.get();
        }

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
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("product", new Product());
        model.addAttribute("product", productModified);
        return "productAdmin";
    }

    @PostMapping("/admin/admin/delete")
    public String deleteAdmin(@RequestParam Long idAdmin) {
        adminRepository.deleteById(idAdmin);
        return "redirect:/admin/admin";
    }

    // http://websystique.com/spring-security/spring-security-4-logout-example/
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";

    }

    @GetMapping("/admin/products/delete")
    public String deleteProduct(@RequestParam Long id){

        compositionRepository.deleteByProductId(id);
        productRepository.deleteById(id);

        return "redirect:/admin/product";
    }

}

