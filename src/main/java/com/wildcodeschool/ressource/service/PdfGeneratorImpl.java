package com.wildcodeschool.ressource.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.wildcodeschool.ressource.entity.Product;
import com.wildcodeschool.ressource.storage.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;


@Service
public class PdfGeneratorImpl implements PdfGeneratorService {

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    private StorageProperties properties;

    @Override
    public InputStreamResource html2PdfGenerator(Product product) {

        Context context = new Context();
        context.setVariable("product", product);

        //String pathProduct = Paths.get(properties.getLocationProducts()).toString();
        String pathProduct = "upload/products";
        //String pathCompany = Paths.get(properties.getLocationCompany()).toString();
        String pathCompany = "upload/company";
        context.setVariable("uploadProduct", pathProduct);
        context.setVariable("uploadCompany", pathCompany);

        final String html = templateEngine.process("pdf", context);
        final String outputFolder = pathProduct + File.separator + "product.pdf";


        try {
            FileOutputStream outFolder = new FileOutputStream(outputFolder);
            HtmlConverter.convertToPdf(html, outFolder);
            return new InputStreamResource(new FileInputStream(outputFolder));

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}



