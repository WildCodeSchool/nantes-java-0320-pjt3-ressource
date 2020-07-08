package com.wildcodeschool.ressource.controller;


import com.wildcodeschool.ressource.entity.Company;
import com.wildcodeschool.ressource.repository.CompanyRepository;
import com.wildcodeschool.ressource.storage.StorageFileNotFoundException;
import com.wildcodeschool.ressource.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controllers for a sample web app that deal with file upload and serving
 */
@Controller
public class FileUploadController {

    /**
     * Injecting the StorageService
     */
    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    public CompanyRepository companyRepository;

    @PostMapping("admin/upload/company")
    public String postCompany(@ModelAttribute Company companySelected,
                              @RequestParam (defaultValue = "", required = false) MultipartFile filePictureFromSky,
                              @RequestParam (defaultValue = "", required = false) MultipartFile fileCompanyMap) {

        storageService.store(filePictureFromSky, 0);
        storageService.store(fileCompanyMap, 0);

        Optional<Company> optionalCompany = companyRepository.findByName(companySelected.getName());

        if (companySelected.getPictureFromSky() == null || companySelected.getPictureFromSky().equals("")) {
            companySelected.setPictureFromSky(filePictureFromSky.getOriginalFilename());
        }

        if (companySelected.getCompanyMap() == null || companySelected.getCompanyMap().equals("")) {
            companySelected.setCompanyMap(fileCompanyMap.getOriginalFilename());
        }

        if (optionalCompany.isPresent()) {
            Company companyModified = optionalCompany.get();
            companySelected.setId(companyModified.getId());
            companyRepository.save(companySelected);
        } else {
            companyRepository.save(companySelected);
        }
        return "redirect:/admin/companies";
    }

    /**
     * Indicate how Spring is supposed to deal with Exceptions thrown by controllers
     *
     * @param exc StorageFileNotFoundException
     * @return a HTTP NOT FOUND response
     */
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}