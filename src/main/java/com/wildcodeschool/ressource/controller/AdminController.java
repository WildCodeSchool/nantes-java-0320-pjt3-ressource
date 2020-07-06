package com.wildcodeschool.ressource.controller;

import com.wildcodeschool.ressource.entity.*;
import com.wildcodeschool.ressource.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @Autowired
    private CompositionRepository compositionRepository;

    @Autowired
    private FeatureRepository featureRepository;

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
        model.addAttribute("fabrics", fabricRepository.findAll());
        model.addAttribute("handfeels", handFeelRepository.findAll());
        model.addAttribute("finishings", finishingRepository.findAll());
        model.addAttribute("looks", lookRepository.findAll());
        model.addAttribute("products", productRepository.findAll());

        return "productAdmin";
    }

//    @PostMapping("/admin/products")
//    public Product create(@RequestBody Product product){
//
//        return productRepository.save(product) ;
//    }

    @PostMapping("/admin/products")
    public String update(@RequestParam String reference,
                         @RequestParam Long designNumber,
                         @RequestParam String description,
                         @RequestParam Integer width,
                         @RequestParam Integer weight,
                         @RequestParam Integer pieceLength,
                         @RequestParam Integer collectionMOQ,
                         @RequestParam Integer productionMOQ,
                         @RequestParam Integer collectionLeadtime,
                         @RequestParam Integer productionLeadtime,
                         @RequestParam String washingComments,
                         @RequestParam Long material,
                         @RequestParam(value = "certifications", required = false) Long[] certifications,
                         @RequestParam(value = "careLabels", required = false) Long[] careLabels,
                         @RequestParam Long origin,
                         @RequestParam Long price,
                         @RequestParam Long fabricPattern,
                         @RequestParam Long company,
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
                         @RequestParam (value = "technicalProperty", required = false) Long[] technicalProperty,
                         @RequestParam Long fabric,
                         @RequestParam Long handFeel,
                         @RequestParam Long finishing,
                         @RequestParam Long look) {

        Optional<Product> optionalProductToUpdate = productRepository.findByReference(reference);

        Product productToUpdate = new Product();

        if (optionalProductToUpdate.isPresent()) {
            productToUpdate = optionalProductToUpdate.get();
        } else {
            productToUpdate.setReference(reference);
        }

        List<Certification> certificationList = certificationRepository.findAllById(Arrays.asList(certifications));
        List<CareLabel> careLabelList = careLabelRepository.findAllById(Arrays.asList(careLabels));
        List<Product> productList = productRepository.findAllById(Arrays.asList(products));

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
        productToUpdate.setMaterial(materialRepository.findById(material).get());
        productToUpdate.setCertifications(certificationList);
        productToUpdate.setCareLabels(careLabelList);
        productToUpdate.setOrigin(originRepository.findById(origin).get());
        productToUpdate.setPrice(priceRepository.findById(price).get());
        productToUpdate.setFabricPattern(fabricPatternRepository.findById(fabricPattern).get());
        productToUpdate.setCompany(companyRepository.findById(company).get());
        productToUpdate.setProducts(productList);
        productToUpdate = productRepository.save(productToUpdate);

        List<TechnicalProperty> technicalPropertyList = technicalPropertyRepository.findAllById(Arrays.asList(technicalProperty));

        Feature newFeature = new Feature();

        newFeature.setFabric(fabricRepository.findById(fabric).get());
        newFeature.setHandFeel(handFeelRepository.findById(handFeel).get());
        newFeature.setFinishing(finishingRepository.findById(finishing).get());
        newFeature.setLook(lookRepository.findById(look).get());
        newFeature.setTechnicalProperties(technicalPropertyList);
        featureRepository.save(newFeature);

        List<Composition> compositionList = new ArrayList<>();

        if (fiber1 != 0) {
            compositionList.add
                    (new Composition(fiberRepository.findById(fiber1).get(), pourcentageFiber1, productToUpdate));
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
}
