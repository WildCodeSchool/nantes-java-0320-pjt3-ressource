package com.wildcodeschool.ressource.controller;


import com.wildcodeschool.ressource.entity.Company;
import com.wildcodeschool.ressource.repository.CompanyRepository;
import com.wildcodeschool.ressource.storage.StorageFileNotFoundException;
import com.wildcodeschool.ressource.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
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
                              @RequestParam (defaultValue = "", required = false) MultipartFile fileCompanyMap,
                              @RequestParam (defaultValue = "", required = false) MultipartFile fileCeoPhoto,
                              @RequestParam (defaultValue = "", required = false) MultipartFile fileThumbnailOneTopLeft,
                              @RequestParam (defaultValue = "", required = false) MultipartFile fileThumbnailOneTopMiddle,
                              @RequestParam (defaultValue = "", required = false) MultipartFile fileThumbnailOneTopRight,
                              @RequestParam (defaultValue = "", required = false) MultipartFile fileThumbnailOneSideWords,
                              @RequestParam (defaultValue = "", required = false) MultipartFile fileThumbnailTwoTopLeft,
                              @RequestParam (defaultValue = "", required = false) MultipartFile fileThumbnailTwoTopMiddle,
                              @RequestParam (defaultValue = "", required = false) MultipartFile fileThumbnailTwoTopRight,
                              @RequestParam (defaultValue = "", required = false) MultipartFile fileThumbnailTwoSideWords,
                              @RequestParam (defaultValue = "", required = false) MultipartFile fileEndPhoto) {

        Optional<Company> optionalCompany = companyRepository.findByName(companySelected.getName());
        if (optionalCompany.isPresent()) {
            Company companyModified = optionalCompany.get();
            companySelected.setId(companyModified.getId());
            if (!companyModified.getPictureFromSky().equals(filePictureFromSky.getOriginalFilename())
                    && !(filePictureFromSky.getOriginalFilename().isEmpty())) {
                storageService.deleteByName(companyModified.getPictureFromSky(), 0);
            }
            storageService.deleteByName(filePictureFromSky.getOriginalFilename(), 0);
            storageService.store(filePictureFromSky, 0);

            if (!companyModified.getCompanyMap().equals(fileCompanyMap.getOriginalFilename())
                    && !(fileCompanyMap.getOriginalFilename().isEmpty())) {
                storageService.deleteByName(companyModified.getCompanyMap(), 0);
            }
            storageService.deleteByName(fileCompanyMap.getOriginalFilename(), 0);
            storageService.store(fileCompanyMap, 0);

            if (!companyModified.getCeoPhoto().equals(fileCeoPhoto.getOriginalFilename())
                    && !(fileCeoPhoto.getOriginalFilename().isEmpty())) {
                storageService.deleteByName(companyModified.getCeoPhoto(), 0);
            }
            storageService.deleteByName(fileCeoPhoto.getOriginalFilename(), 0);
            storageService.store(fileCeoPhoto, 0);

            if (!companyModified.getThumbnailOneTopLeft().equals(fileThumbnailOneTopLeft.getOriginalFilename())
                    && !(fileThumbnailOneTopLeft.getOriginalFilename().isEmpty())) {
                storageService.deleteByName(companyModified.getThumbnailOneTopLeft(), 0);
            }
            storageService.deleteByName(fileThumbnailOneTopLeft.getOriginalFilename(), 0);
            storageService.store(fileThumbnailOneTopLeft, 0);

            if (!companyModified.getThumbnailOneTopMiddle().equals(fileThumbnailOneTopMiddle.getOriginalFilename())
                    && !(fileThumbnailOneTopMiddle.getOriginalFilename().isEmpty())) {
                storageService.deleteByName(companyModified.getThumbnailOneTopMiddle(), 0);
            }
            storageService.deleteByName(fileThumbnailOneTopMiddle.getOriginalFilename(), 0);
            storageService.store(fileThumbnailOneTopMiddle, 0);

            if (!companyModified.getThumbnailOneTopRight().equals(fileThumbnailOneTopRight.getOriginalFilename())
                    && !(fileThumbnailOneTopRight.getOriginalFilename().isEmpty())) {
                storageService.deleteByName(companyModified.getThumbnailOneTopRight(), 0);
            }
            storageService.deleteByName(fileThumbnailOneTopRight.getOriginalFilename(), 0);
            storageService.store(fileThumbnailOneTopRight, 0);

            if (!companyModified.getThumbnailOneSideWords().equals(fileThumbnailOneSideWords.getOriginalFilename())
                    && !(fileThumbnailOneSideWords.getOriginalFilename().isEmpty())) {
                storageService.deleteByName(companyModified.getThumbnailOneSideWords(), 0);
            }
            storageService.deleteByName(fileThumbnailOneSideWords.getOriginalFilename(), 0);
            storageService.store(fileThumbnailOneSideWords, 0);

            if (!companyModified.getThumbnailTwoTopLeft().equals(fileThumbnailTwoTopLeft.getOriginalFilename())
                    && !(fileThumbnailTwoTopLeft.getOriginalFilename().isEmpty())) {
                storageService.deleteByName(companyModified.getThumbnailTwoTopLeft(), 0);
            }
            storageService.deleteByName(fileThumbnailTwoTopLeft.getOriginalFilename(), 0);
            storageService.store(fileThumbnailTwoTopLeft, 0);

            if (!companyModified.getThumbnailTwoTopMiddle().equals(fileThumbnailTwoTopMiddle.getOriginalFilename())
                    && !(fileThumbnailTwoTopMiddle.getOriginalFilename().isEmpty())) {
                storageService.deleteByName(companyModified.getThumbnailTwoTopMiddle(), 0);
            }
            storageService.deleteByName(fileThumbnailTwoTopMiddle.getOriginalFilename(), 0);
            storageService.store(fileThumbnailTwoTopMiddle, 0);

            if (!companyModified.getThumbnailTwoTopRight().equals(fileThumbnailTwoTopRight.getOriginalFilename())
                    && !(fileThumbnailTwoTopRight.getOriginalFilename().isEmpty())) {
                storageService.deleteByName(companyModified.getThumbnailTwoTopRight(), 0);
            }
            storageService.deleteByName(fileThumbnailTwoTopRight.getOriginalFilename(), 0);
            storageService.store(fileThumbnailTwoTopRight, 0);

            if (!companyModified.getThumbnailTwoSideWords().equals(fileThumbnailTwoSideWords.getOriginalFilename())
                    && !(fileThumbnailTwoSideWords.getOriginalFilename().isEmpty())) {
                storageService.deleteByName(companyModified.getThumbnailTwoSideWords(), 0);
            }
            storageService.deleteByName(fileThumbnailTwoSideWords.getOriginalFilename(), 0);
            storageService.store(fileThumbnailTwoSideWords, 0);

            if (!companyModified.getEndPhoto().equals(fileEndPhoto.getOriginalFilename())
                    && !(fileEndPhoto.getOriginalFilename().isEmpty())) {
                storageService.deleteByName(companyModified.getEndPhoto(), 0);
            }
            storageService.deleteByName(fileEndPhoto.getOriginalFilename(), 0);
            storageService.store(fileEndPhoto, 0);
        }

        if (companySelected.getPictureFromSky() == null || companySelected.getPictureFromSky().equals("")) {
            companySelected.setPictureFromSky(filePictureFromSky.getOriginalFilename());
        }

        if (companySelected.getCompanyMap() == null || companySelected.getCompanyMap().equals("")) {
            companySelected.setCompanyMap(fileCompanyMap.getOriginalFilename());
        }

        if (companySelected.getCeoPhoto() == null || companySelected.getCeoPhoto().equals("")) {
            companySelected.setCeoPhoto(filePictureFromSky.getOriginalFilename());
        }

        if (companySelected.getThumbnailOneTopLeft() == null || companySelected.getThumbnailOneTopLeft().equals("")) {
            companySelected.setThumbnailOneTopLeft(fileCompanyMap.getOriginalFilename());
        }

        if (companySelected.getThumbnailOneTopMiddle() == null || companySelected.getThumbnailOneTopMiddle().equals("")) {
            companySelected.setThumbnailOneTopMiddle(filePictureFromSky.getOriginalFilename());
        }

        if (companySelected.getThumbnailOneTopRight() == null || companySelected.getThumbnailOneTopRight().equals("")) {
            companySelected.setThumbnailOneTopRight(fileCompanyMap.getOriginalFilename());
        }

        if (companySelected.getThumbnailOneSideWords() == null || companySelected.getThumbnailOneSideWords().equals("")) {
            companySelected.setThumbnailOneSideWords(filePictureFromSky.getOriginalFilename());
        }

        if (companySelected.getThumbnailTwoTopLeft() == null || companySelected.getThumbnailTwoTopLeft().equals("")) {
            companySelected.setThumbnailTwoTopLeft(fileCompanyMap.getOriginalFilename());
        }

        if (companySelected.getThumbnailTwoTopMiddle() == null || companySelected.getThumbnailTwoTopMiddle().equals("")) {
            companySelected.setThumbnailTwoTopMiddle(filePictureFromSky.getOriginalFilename());
        }

        if (companySelected.getThumbnailTwoTopRight() == null || companySelected.getThumbnailTwoTopRight().equals("")) {
            companySelected.setThumbnailTwoTopRight(fileCompanyMap.getOriginalFilename());
        }

        if (companySelected.getThumbnailTwoSideWords() == null || companySelected.getThumbnailTwoSideWords().equals("")) {
            companySelected.setThumbnailTwoSideWords(filePictureFromSky.getOriginalFilename());
        }

        if (companySelected.getThumbnailTwoSideWords() == null || companySelected.getThumbnailTwoSideWords().equals("")) {
            companySelected.setThumbnailTwoSideWords(fileCompanyMap.getOriginalFilename());
        }

        companyRepository.save(companySelected);
        return "redirect:/admin/companies";
    }

    /**
     * Serving a file
     *
     * @param filename
     * @return the ResponseEntity
     */
    @GetMapping("/photo/{folder}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> displayFile(@PathVariable String folder, @PathVariable String filename) throws IOException {

        Resource file = null;
        if (folder.equals("company")) {
            file = storageService.loadAsResource(filename, 0);
        } else {
            file = storageService.loadAsResource(filename, 1);
        }
        Optional<MediaType> type = MediaTypeFactory.getMediaType(file);
        if (type.isPresent()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + file.getFilename() + "\"")
                    .contentType(type.get())
                    .contentLength(file.contentLength())
                    .body(file);
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Type of the file could be determined");
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