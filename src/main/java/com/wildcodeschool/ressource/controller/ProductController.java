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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private CertificationRepository certificationRepository;
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


    @GetMapping("/results/filter")
    public String filter2(Model model, @RequestParam(defaultValue = "", required = false) Long[] material,
                          @RequestParam(value = "fabric", defaultValue = "", required = false) Long[] fabric,
                          @RequestParam(value = "fiber", defaultValue = "", required = false) Long[] fiber,
                          @RequestParam(value = "country", defaultValue = "", required = false) Long[] country,
                          @RequestParam(value = "supplier", defaultValue = "", required = false) Long[] supplier,
                          @RequestParam(value = "price", defaultValue = "", required = false) Long[] price,
                          @RequestParam(defaultValue = "75", required = false) Long sliderWeightMin,
                          @RequestParam(defaultValue = "200", required = false) Long sliderWeightMax,
                          @RequestParam(defaultValue = "30", required = false) Long sliderWidthMin,
                          @RequestParam(defaultValue = "500", required = false) Long sliderWidthMax,
                          @RequestParam(value = "certification", defaultValue = "", required = false) Long[] certification,
                          @RequestParam(defaultValue = "", required = false) String search,
                          @RequestParam(defaultValue = "false", required = false) boolean submit) {

        List<Long> productsId = productRepository.findAllIdBySearching(search);
        List<Product> allProducts = productRepository.findAllByIdIn(productsId);
        List<Product> productsWFilter = allProducts.stream()
                .filter(item -> ((material.length == 0 || item.getMaterial() != null &&
                        Arrays.asList(material).contains(item.getMaterial().getId())) &&

                        (fabric.length == 0 || item.getFabricPattern() != null &&
                                Arrays.asList(fabric).contains(item.getFabricPattern().getId())) &&

                        (country.length == 0 || item.getOrigin() != null &&
                                Arrays.asList(country).contains(item.getOrigin().getId())) &&

                        (supplier.length == 0 || item.getCompany() != null &&
                                Arrays.asList(supplier).contains(item.getCompany().getId())) &&

                        (price.length == 0 || item.getPrice() != null &&
                                Arrays.asList(price).contains(item.getPrice().getId())) &&

                        (item.getWeight() != null &&
                                (item.getWeight() >= sliderWeightMin &&
                                        item.getWeight() <= sliderWeightMax)) &&

                        (item.getWidth() != null &&
                                (item.getWidth() >= sliderWidthMin &&
                                        item.getWidth() <= sliderWidthMax))
                ))
                .filter(element -> {
                    if (certification.length == 0) {
                        return true;
                    }
                    for (Certification cert : element.getCertifications()) {

                        if (cert != null && Arrays.asList(certification).contains(cert.getId())) {
                            return true;
                        }
                    }
                    return false;
                })
                .filter(element2 -> {
                    if (fiber.length == 0) {
                        return true;
                    }
                    for (Composition compo : element2.getCompositions()) {
                        if (compo != null && Arrays.asList(fiber).contains(compo.getFiber().getId())) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());

        model.addAttribute("products", productsWFilter);
        if (!submit) {
            return "products_filter";
        }

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

        model.addAttribute("search", search);
        model.addAttribute("certifications", certifications);
        model.addAttribute("prices", priceRepository.findAll());
        model.addAttribute("companies", suppliers);
        model.addAttribute("origins", origins);
        model.addAttribute("compositions", mainCompo);
        model.addAttribute("materials", materialRepository.findAll());
        model.addAttribute("fabricPatterns", fabricPatternRepository.findAll());

        return "results";
    }
}
