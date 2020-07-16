package com.wildcodeschool.ressource.service;


import com.wildcodeschool.ressource.entity.Product;
import org.springframework.core.io.InputStreamResource;

import java.net.URISyntaxException;


public interface PdfGeneratorService {

    InputStreamResource html2PdfGenerator(Product product) throws URISyntaxException;
}
