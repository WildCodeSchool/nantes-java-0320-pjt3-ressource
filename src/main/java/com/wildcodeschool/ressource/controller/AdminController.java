package com.wildcodeschool.ressource.controller;

import com.wildcodeschool.ressource.entity.Product;
import com.wildcodeschool.ressource.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AdminController {

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

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/admin")
    public String adminLogin() {
        return "admin_login";
    }

    @GetMapping("/admin/admin")
    public String adminAdmin() {
        return "admin_admin";
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
        model.addAttribute("products", productRepository.findAll());

        return "productAdmin";
    }

    @PostMapping("/admin/products")
    public Product create(@RequestBody Product product){

        return productRepository.save(product) ;
    }

    @PutMapping("/admin/products/{reference}")
    public Product update(@PathVariable String reference, @RequestBody Product product){

        Product productToUpdate = productRepository.findByReference(reference);
        productToUpdate.setDesignNumber(product.getDesignNumber());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setWidth(product.getWidth());
        productToUpdate.setWeight(product.getWeight());
        productToUpdate.setPieceLength(product.getPieceLength());
        productToUpdate.setCollectionMOQ(product.getCollectionMOQ());
        productToUpdate.setProductionMOQ(product.getProductionMOQ());
        productToUpdate.setCollectionLeadtime(product.getCollectionLeadtime());
        productToUpdate.setProductionLeadtime(product.getProductionLeadtime());
        productToUpdate.setWashingComments(product.getWashingComments());
        productToUpdate.setMaterial(product.getMaterial());
        productToUpdate.setImageProducts(product.getImageProducts());
        productToUpdate.setCertifications(product.getCertifications());
        productToUpdate.setCareLabels(product.getCareLabels());
        productToUpdate.setOrigin(product.getOrigin());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setFabricPattern(product.getFabricPattern());
        productToUpdate.setCompositions(product.getCompositions());
        productToUpdate.setFeature(product.getFeature());
        productToUpdate.setCompany(product.getCompany());
        productToUpdate.setProducts(product.getProducts());

        return productRepository.save(productToUpdate);
    }
}
