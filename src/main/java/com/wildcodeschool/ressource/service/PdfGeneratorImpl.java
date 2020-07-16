package com.wildcodeschool.ressource.service;

import com.itextpdf.html2pdf.HtmlConverter;

import com.wildcodeschool.ressource.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;


@Service
public class PdfGeneratorImpl implements PdfGeneratorService {

    @Autowired
    TemplateEngine templateEngine;

    @Override
    public InputStreamResource html2PdfGenerator(Product product) {

        Context context = new Context();
        context.setVariable("product", product);
        //TODO prendre le bon chemin application properties
        context.setVariable("upload", "upload");
        final String html = templateEngine.process("pdf_hello", context);

        final String outputFolder = "upload" + File.separator + "product.pdf";


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



