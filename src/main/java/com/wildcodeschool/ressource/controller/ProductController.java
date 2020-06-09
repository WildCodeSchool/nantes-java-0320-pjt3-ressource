package com.wildcodeschool.ressource.controller;

import com.wildcodeschool.ressource.entity.CareLabel;
import com.wildcodeschool.ressource.entity.Material;
import com.wildcodeschool.ressource.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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


    @GetMapping("/results")
    public String result(Model model) {

        model.addAttribute("materials", materialRepository.findAll());
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
