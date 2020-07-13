package com.wildcodeschool.ressource.controller;


import com.wildcodeschool.ressource.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.lowagie.text.DocumentException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() throws IOException, DocumentException{
        String html = parseThymeleafTemplate();
        generatePdfFromHtml(html);
        return "home";
    }

    private String parseThymeleafTemplate() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("/templates/");
        templateResolver.setTemplateMode("HTML5");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("to", "Baeldung");

        return templateEngine.process("pdf_hello", context);
    }

    public void generatePdfFromHtml(String html) throws IOException, DocumentException {
        String outputFolder = "upload" + File.separator + "product.pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }
}
