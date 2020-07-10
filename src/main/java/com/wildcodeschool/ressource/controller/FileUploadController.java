package com.wildcodeschool.ressource.controller;


import com.wildcodeschool.ressource.entity.Company;
import com.wildcodeschool.ressource.repository.CompanyRepository;
import com.wildcodeschool.ressource.storage.StorageFileNotFoundException;
import com.wildcodeschool.ressource.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

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

        String prefix = companySelected.getName() + "-";

        String pictureFromSky = prefix + filePictureFromSky.getOriginalFilename();
        String companyMap = prefix + fileCompanyMap.getOriginalFilename();
        String ceoPhoto = prefix + fileCeoPhoto.getOriginalFilename();
        String thumbnailOneTopLeft = prefix + fileThumbnailOneTopLeft.getOriginalFilename();
        String thumbnailOneTopMiddle = prefix + fileThumbnailOneTopMiddle.getOriginalFilename();
        String thumbnailOneTopRight = prefix + fileThumbnailOneTopRight.getOriginalFilename();
        String thumbnailOneSideWords = prefix + fileThumbnailOneSideWords.getOriginalFilename();
        String thumbnailTwoTopLeft = prefix + fileThumbnailTwoTopLeft.getOriginalFilename();
        String thumbnailTwoTopMiddle = prefix + fileThumbnailTwoTopMiddle.getOriginalFilename();
        String thumbnailTwoTopRight = prefix + fileThumbnailTwoTopRight.getOriginalFilename();
        String thumbnailTwoSideWords = prefix + fileThumbnailTwoSideWords.getOriginalFilename();
        String endPhoto = prefix + fileEndPhoto.getOriginalFilename();

        if (optionalCompany.isPresent()) {
            Company companyModified = optionalCompany.get();
            companySelected.setId(companyModified.getId());

            if (!(companyModified.getPictureFromSky() == null)
                && !companyModified.getPictureFromSky().equals(pictureFromSky)
                && !filePictureFromSky.getOriginalFilename().equals("")) {
                storageService.deleteByName(companyModified.getPictureFromSky(), 0);
            }
            storageService.deleteByName(filePictureFromSky.getOriginalFilename(), 0);
            storageService.store(filePictureFromSky, 0, pictureFromSky);

            if (!(companyModified.getCompanyMap() == null)
                    && !companyModified.getCompanyMap().equals(companyMap)
                    && !fileCompanyMap.getOriginalFilename().equals("")) {
                storageService.deleteByName(companyModified.getCompanyMap(), 0);
            }
            storageService.deleteByName(companyMap, 0);
            storageService.store(fileCompanyMap, 0, companyMap);

            if (!(companyModified.getCeoPhoto() == null)
                    && !companyModified.getCeoPhoto().equals(ceoPhoto)
                    && !fileCeoPhoto.getOriginalFilename().equals("")) {
                storageService.deleteByName(companyModified.getCeoPhoto(), 0);
            }
            storageService.deleteByName(ceoPhoto, 0);
            storageService.store(fileCeoPhoto, 0, ceoPhoto);

            if (!(companyModified.getThumbnailOneTopLeft() == null)
                    && !companyModified.getThumbnailOneTopLeft().equals(thumbnailOneTopLeft)
                    && !fileThumbnailOneTopLeft.getOriginalFilename().equals("")) {
                storageService.deleteByName(companyModified.getThumbnailOneTopLeft(), 0);
            }
            storageService.deleteByName(thumbnailOneTopLeft, 0);
            storageService.store(fileThumbnailOneTopLeft, 0, thumbnailOneTopLeft);

            if (!(companyModified.getThumbnailOneTopMiddle() == null)
                    && !companyModified.getThumbnailOneTopMiddle().equals(thumbnailOneTopMiddle)
                    && !fileThumbnailOneTopMiddle.getOriginalFilename().equals("")) {
                storageService.deleteByName(companyModified.getThumbnailOneTopMiddle(), 0);
            }
            storageService.deleteByName(thumbnailOneTopMiddle, 0);
            storageService.store(fileThumbnailOneTopMiddle, 0, thumbnailOneTopMiddle);

            if (!(companyModified.getThumbnailOneTopRight() == null)
                    && !companyModified.getThumbnailOneTopRight().equals(thumbnailOneTopRight)
                    && !fileThumbnailOneTopRight.getOriginalFilename().equals("")) {
                storageService.deleteByName(companyModified.getThumbnailOneTopRight(), 0);
            }
            storageService.deleteByName(thumbnailOneTopRight, 0);
            storageService.store(fileThumbnailOneTopRight, 0, thumbnailOneTopRight);

            if (!(companyModified.getThumbnailOneSideWords() == null)
                    && !companyModified.getThumbnailOneSideWords().equals(thumbnailOneSideWords)
                    && !fileThumbnailOneSideWords.getOriginalFilename().equals("")) {
                storageService.deleteByName(companyModified.getThumbnailOneSideWords(), 0);
            }
            storageService.deleteByName(thumbnailOneSideWords, 0);
            storageService.store(fileThumbnailOneSideWords, 0, thumbnailOneSideWords);

            if (!(companyModified.getThumbnailTwoTopLeft() == null)
                    && !companyModified.getThumbnailTwoTopLeft().equals(thumbnailTwoTopLeft)
                    && !fileThumbnailTwoTopLeft.getOriginalFilename().equals("")) {
                storageService.deleteByName(companyModified.getThumbnailTwoTopLeft(), 0);
            }
            storageService.deleteByName(thumbnailTwoTopLeft, 0);
            storageService.store(fileThumbnailTwoTopLeft, 0, thumbnailTwoTopLeft);

            if (!(companyModified.getThumbnailTwoTopMiddle() == null)
                    && !companyModified.getThumbnailTwoTopMiddle().equals(thumbnailTwoTopMiddle)
                    && !fileThumbnailTwoTopMiddle.getOriginalFilename().equals("")) {
                storageService.deleteByName(companyModified.getThumbnailTwoTopMiddle(), 0);
            }
            storageService.deleteByName(thumbnailTwoTopMiddle, 0);
            storageService.store(fileThumbnailTwoTopMiddle, 0, thumbnailTwoTopMiddle);

            if (!(companyModified.getThumbnailTwoTopRight() == null)
                    && !companyModified.getThumbnailTwoTopRight().equals(thumbnailTwoTopRight)
                    && !fileThumbnailTwoTopRight.getOriginalFilename().equals("")) {
                storageService.deleteByName(companyModified.getThumbnailTwoTopRight(), 0);
            }
            storageService.deleteByName(thumbnailTwoTopRight, 0);
            storageService.store(fileThumbnailTwoTopRight, 0, thumbnailTwoTopRight);

            if (!(companyModified.getThumbnailTwoSideWords() == null)
                    && !companyModified.getThumbnailTwoSideWords().equals(thumbnailTwoSideWords)
                    && !fileThumbnailTwoSideWords.getOriginalFilename().equals("")) {
                storageService.deleteByName(companyModified.getThumbnailTwoSideWords(), 0);
            }
            storageService.deleteByName(thumbnailTwoSideWords, 0);
            storageService.store(fileThumbnailTwoSideWords, 0, thumbnailTwoSideWords);

            if (!(companyModified.getEndPhoto() == null)
                    && !companyModified.getEndPhoto().equals(endPhoto)
                    && !fileEndPhoto.getOriginalFilename().equals("")) {
                storageService.deleteByName(companyModified.getEndPhoto(), 0);
            }
            storageService.deleteByName(endPhoto, 0);
            storageService.store(fileEndPhoto, 0, endPhoto);
        } else {
            storageService.store(filePictureFromSky, 0, pictureFromSky);
            storageService.store(fileCompanyMap, 0, companyMap);
            storageService.store(fileCeoPhoto, 0, ceoPhoto);
            storageService.store(fileThumbnailOneTopLeft, 0, thumbnailOneTopLeft);
            storageService.store(fileThumbnailOneTopMiddle, 0, thumbnailOneTopMiddle);
            storageService.store(fileThumbnailOneTopRight, 0, thumbnailOneTopRight);
            storageService.store(fileThumbnailOneSideWords, 0, thumbnailOneSideWords);
            storageService.store(fileThumbnailTwoTopLeft, 0, thumbnailTwoTopLeft);
            storageService.store(fileThumbnailTwoTopMiddle, 0, thumbnailTwoTopMiddle);
            storageService.store(fileThumbnailTwoTopRight, 0, thumbnailTwoTopRight);
            storageService.store(fileThumbnailTwoSideWords, 0, thumbnailTwoSideWords);
            storageService.store(fileEndPhoto, 0, endPhoto);
        }

        if (filePictureFromSky.getOriginalFilename().equals("")) {
            companySelected.setPictureFromSky(companySelected.getPictureFromSky());
        } else {
            companySelected.setPictureFromSky(pictureFromSky);
        }

        if (fileCompanyMap.getOriginalFilename().equals("")) {
            companySelected.setCompanyMap(companySelected.getCompanyMap());
        } else {
            companySelected.setCompanyMap(companyMap);
        }

        if (fileCeoPhoto.getOriginalFilename().equals("")) {
            companySelected.setCeoPhoto(companySelected.getCeoPhoto());
        } else {
            companySelected.setCeoPhoto(ceoPhoto);
        }

        if (fileThumbnailOneTopLeft.getOriginalFilename().equals("")) {
            companySelected.setThumbnailOneTopLeft(companySelected.getThumbnailOneTopLeft());
        } else {
            companySelected.setThumbnailOneTopLeft(thumbnailOneTopLeft);
        }

        if (fileThumbnailOneTopMiddle.getOriginalFilename().equals("")) {
            companySelected.setThumbnailOneTopMiddle(companySelected.getThumbnailOneTopMiddle());
        } else {
            companySelected.setThumbnailOneTopMiddle(thumbnailOneTopMiddle);
        }

        if (fileThumbnailOneTopRight.getOriginalFilename().equals("")) {
            companySelected.setThumbnailOneTopRight(companySelected.getThumbnailOneTopRight());
        } else {
            companySelected.setThumbnailOneTopRight(thumbnailOneTopRight);
        }

        if (fileThumbnailOneSideWords.getOriginalFilename().equals("")) {
            companySelected.setThumbnailOneSideWords(companySelected.getThumbnailOneSideWords());
        } else {
            companySelected.setThumbnailOneSideWords(thumbnailOneSideWords);
        }

        if (fileThumbnailTwoTopLeft.getOriginalFilename().equals("")) {
            companySelected.setThumbnailTwoTopLeft(companySelected.getThumbnailTwoTopLeft());
        } else {
            companySelected.setThumbnailTwoTopLeft(thumbnailTwoTopLeft);
        }

        if (fileThumbnailTwoTopMiddle.getOriginalFilename().equals("")) {
            companySelected.setThumbnailTwoTopMiddle(companySelected.getThumbnailTwoTopMiddle());
        } else {
            companySelected.setThumbnailTwoTopMiddle(thumbnailTwoTopMiddle);
        }

        if (fileThumbnailTwoTopRight.getOriginalFilename().equals("")) {
            companySelected.setThumbnailTwoTopRight(companySelected.getThumbnailTwoTopRight());
        } else {
            companySelected.setThumbnailTwoTopRight(thumbnailTwoTopRight);
        }

        if (fileThumbnailTwoSideWords.getOriginalFilename().equals("")) {
            companySelected.setThumbnailTwoSideWords(companySelected.getThumbnailTwoSideWords());
        } else {
            companySelected.setThumbnailTwoSideWords(thumbnailTwoSideWords);
        }

        if (fileEndPhoto.getOriginalFilename().equals("")) {
            companySelected.setEndPhoto(companySelected.getEndPhoto());
        } else {
            companySelected.setEndPhoto(endPhoto);
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