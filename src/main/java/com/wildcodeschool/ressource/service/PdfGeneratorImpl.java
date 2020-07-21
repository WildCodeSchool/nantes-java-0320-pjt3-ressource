package com.wildcodeschool.ressource.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.wildcodeschool.ressource.entity.CareLabel;
import com.wildcodeschool.ressource.entity.Certification;
import com.wildcodeschool.ressource.entity.Product;
import com.wildcodeschool.ressource.storage.StorageProperties;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PdfGeneratorImpl implements PdfGeneratorService {

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    private StorageProperties properties;

    @Autowired
    ResourceLoader resourceLoader;


    @Override
    public InputStreamResource html2PdfGenerator(Product product) {

        Context context = new Context();
        context.setVariable("product", product);

        String pathProduct = Paths.get(properties.getLocationProducts()).toString();
        String pathCompany = Paths.get(properties.getLocationCompany()).toString();

        try {
            String logo = product.getCompany().getLogo();
            String suffixLogo = suffix(logo);
            String imageLogo = "data:image/" + suffixLogo + ";base64," + convertToBase64(pathCompany + File.separator + logo);
            context.setVariable("imageLogo", imageLogo);

            String imgProduct = product.getImageProducts().get(0).getImage();
            String suffixProduct = suffix(imgProduct);
            String imageProduct = "data:image/" + suffixProduct + ";base64," + convertToBase64(pathProduct + File.separator + imgProduct);
            context.setVariable("imageProduct", imageProduct);

            List<Certification> certifications = product.getCertifications();
            Map<String, String> imgCert = new HashMap<>();
            for (Certification cert : certifications) {
                String suffixCert = suffix(cert.getImage());
                String resource = resourceLoader.getResource("classpath:static/image/logo_certification/" + cert.getImage())
                        .getFile()
                        .getPath();
                String image = "data:image/" + suffixCert + ";base64," + convertToBase64(resource);
                imgCert.put(cert.getName(), image);
            }
            context.setVariable("imageCert", imgCert);

            List<CareLabel> labels = product.getCareLabels();
            Map<String, String> imgLabel = new HashMap<>();
            for (CareLabel label : labels) {
                String suffixCert = suffix(label.getImage());

                String resource = resourceLoader.getResource("classpath:static/image/carelabel/" + label.getImage())
                        .getFile()
                        .getPath();
                String image = "data:image/" + suffixCert + ";base64," + convertToBase64(resource);
                imgLabel.put(label.getLabel(), image);
            }
            context.setVariable("imageLabel", imgLabel);


        } catch (IOException e) {
            e.printStackTrace();
        }

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

    private String convertToBase64(String path) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(path));
        return Base64.getEncoder().encodeToString(fileContent);
    }

    private String suffix(String file) {
        String suffix = FilenameUtils.getExtension(file).toLowerCase();
        if (suffix.equals("jpg")) {
            suffix = "jpeg";
        }
        return suffix;
    }
}



