package com.wildcodeschool.ressource.controller;

import com.wildcodeschool.ressource.entity.Certification;
import com.wildcodeschool.ressource.entity.Company;
import com.wildcodeschool.ressource.entity.Fiber;
import com.wildcodeschool.ressource.entity.Origin;
import com.wildcodeschool.ressource.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private CareLabelRepository careLabelRepository;
    @Autowired
    private CertificationRepository certificationRepository;
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private ImgProductRepository imgProductRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FabricPatternRepository fabricPatternRepository;
    @Autowired
    private FiberRepository fiberRepository;
    @Autowired
    private OriginRepository originRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PriceRepository priceRepository;

    @GetMapping("/results")
    public String result(Model model, @RequestParam(defaultValue = "", required = false) String search) {

        Pageable PageFiber = PageRequest.of(0, 12);
        Page<Fiber> FiberSub = fiberRepository.findAll(PageFiber);
        List<Fiber> mainCompo = FiberSub.get().collect(Collectors.toList());

        Pageable PageOrigin = PageRequest.of(0, 4);
        Page<Origin> originSub = originRepository.findAll(PageOrigin);
        List<Origin> origins = originSub.get().collect(Collectors.toList());

        Pageable PageSupplier = PageRequest.of(0, 4);
        Page<Company> supplierSub = companyRepository.findAll(PageSupplier);
        List<Company> suppliers = supplierSub.get().collect(Collectors.toList());

        Pageable PageCert = PageRequest.of(0, 3);
        Page<Certification> certificationSub = certificationRepository.findAll(PageCert);
        List<Certification> certifications = certificationSub.get().collect(Collectors.toList());
        List<Long> productsId = productRepository.findAllIdBySearching(search);
        model.addAttribute("search", search);
        model.addAttribute("products", productRepository.findAllByIdIn(productsId));
        model.addAttribute("certifications", certifications);
        model.addAttribute("prices", priceRepository.findAll());
        model.addAttribute("companies", suppliers);
        model.addAttribute("origins", origins);
        model.addAttribute("compositions", mainCompo);
        model.addAttribute("materials", materialRepository.findAll());
        model.addAttribute("fabricPatterns", fabricPatternRepository.findAll());

        return "results";
    }

    @GetMapping("/results/more/{filter}/{all}")
    public String moreFilter(@PathVariable String filter, Model model, @PathVariable Boolean all) {
        int size;
        int start;
        if (filter.equals("more-origin")) {
            List<Origin> origins = originRepository.findAll();
            if (all) {
                size = origins.size();
                start = 4;
            } else {
                size = 4;
                start = 0;
            }
            model.addAttribute("lists", origins.subList(start, size));
            model.addAttribute("name", "origin");
        } else if (filter.equals("more-composition")) {
            List<Fiber> fibers = fiberRepository.findAll();
            if (all) {
                size = fibers.size();
                start = 12;
            } else {
                size = 12;
                start = 0;
            }
            model.addAttribute("lists", fibers.subList(start, size));
            model.addAttribute("name", "composition");
        } else if (filter.equals("more-supplier")) {
            List<Company> companies = companyRepository.findAll();
            if (all) {
                size = companies.size();
                start = 4;
            } else {
                size = 4;
                start = 0;
            }
            model.addAttribute("lists", companies.subList(start, size));
            model.addAttribute("name", "supplier");
        } else if (filter.equals("more-certification")) {
            List<Certification> certifications = certificationRepository.findAll();
            if (all) {
                size = certifications.size();
                start = 4;
            } else {
                size = 4;
                start = 0;
            }
            model.addAttribute("lists", certifications.subList(start, size));
            model.addAttribute("name", "certification");
        }
        return "listsToSeeMore";
    }

    @GetMapping("/product")
    public String product(Model model, @RequestParam Long reference) {

        Optional<Product> optionalProduct = productRepository.findById(reference);
        optionalProduct.ifPresent(product -> model.addAttribute("product", product));
        return "product";
    }

}
