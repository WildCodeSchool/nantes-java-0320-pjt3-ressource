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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;
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
    private PriceRepository priceRepository;


    @GetMapping("/company")
    public String findCompanyById(@RequestParam Long id,
                                   Model model) {

        Pageable PageFiber = PageRequest.of(0, 12);
        Page<Fiber> FiberSub = fiberRepository.findAll(PageFiber);
        List<Fiber> mainCompo = FiberSub.get().collect(Collectors.toList());

        Pageable PageOrigin = PageRequest.of(0, 4);
        Page<Origin> originSub = originRepository.findAll(PageOrigin);
        List<Origin> origins = originSub.get().collect(Collectors.toList());

        Pageable PageSupplier = PageRequest.of(0, 4);
        Page<Company> supplierSub = companyRepository.findAll(PageSupplier);
        List<Company> suppliers = supplierSub.get().collect(Collectors.toList());

        Company company = companyRepository.findById(id).get();

        Pageable PageCert = PageRequest.of(0, 3);
        Page<Certification> certificationSub = certificationRepository.findAll(PageCert);
        List<Certification> certifications = certificationSub.get().collect(Collectors.toList());
        List<Long> productsId = productRepository.findAllIdBySearching(company.getName());
        model.addAttribute("products", productRepository.findAllByIdIn(productsId));
        model.addAttribute("certifications", certifications);
        model.addAttribute("prices", priceRepository.findAll());
        model.addAttribute("companies", suppliers);
        model.addAttribute("origins", origins);
        model.addAttribute("compositions", mainCompo);
        model.addAttribute("materials", materialRepository.findAll());
        model.addAttribute("fabricPatterns", fabricPatternRepository.findAll());
        model.addAttribute("company", company);

        return "company";
    }
}
