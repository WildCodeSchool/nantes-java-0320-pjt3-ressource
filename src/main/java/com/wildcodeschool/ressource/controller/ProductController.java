package com.wildcodeschool.ressource.controller;

import com.wildcodeschool.ressource.entity.*;
import com.wildcodeschool.ressource.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
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

    @GetMapping("/results")
    public String result(Model model) {
        //TODO : ajax to retrieve the next pages
        Pageable PageFiber = PageRequest.of(0, 12);
        Page<Fiber> FiberSub = fiberRepository.findAll(PageFiber);
        List<Fiber> mainCompo = FiberSub.get().collect(Collectors.toList());

        Pageable PageOrigin = PageRequest.of(0, 4);
        Page<Origin> originSub = originRepository.findAll(PageOrigin);
        List<Origin> origins = originSub.get().collect(Collectors.toList());

        Pageable PageSupplier = PageRequest.of(0, 4);
        Page<Company> supplierSub = companyRepository.findAll(PageSupplier);
        List<Company> suppliers = supplierSub.get().collect(Collectors.toList());

        model.addAttribute("origins", origins);
        model.addAttribute("compositions", mainCompo);
        model.addAttribute("materials", materialRepository.findAll());
        model.addAttribute("fabricPatterns", fabricPatternRepository.findAll());

        return "results";
    }

    @PostMapping("/results")
    public String postResult() {
        return "results";
    }

    @GetMapping("/product")
    public String product() {

        return "product";
    }
}
