package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIdIn(List<Long> ids);

    @Query(value = "SELECT DISTINCT p.id FROM product p" +
            " LEFT JOIN fabric_pattern fp ON p.fabric_pattern_id = fp.id" +
            " LEFT JOIN material mat ON p.material_id = mat.id" +
            " LEFT JOIN origin ori ON p.origin_id = ori.id" +
            " LEFT JOIN company sup ON p.company_id = sup.id" +
            " LEFT JOIN product_care_label pcl ON p.id = pcl.product_id" +
            " LEFT JOIN care_label cl ON cl.id = pcl.care_label_id" +
            " LEFT JOIN product_certification pcert ON p.id = pcert.product_id" +
            " LEFT JOIN certification cert ON cert.id = pcert.certification_id" +
            " LEFT JOIN composition compo ON compo.product_id = p.id" +
            " LEFT JOIN fiber ON compo.fiber_id = fiber.id" +
            " LEFT JOIN feature ft ON p.feature_id = ft.id" +
            " LEFT JOIN finishing f ON ft.finishing_id = f.id" +
            " LEFT JOIN hand_feel hf ON hf.id = ft.handFeel_id" +
            " LEFT JOIN look ON look.id = ft.look_id" +
            " LEFT JOIN fabric ON fabric.id = ft.fabric_id" +
            " LEFT JOIN feature_technical_property ftp ON ftp.feature_id = ft.id" +
            " LEFT JOIN technical_property tp ON tp.id = ftp.technical_property_id" +
            " WHERE (p.description IS NOT NULL AND p.description LIKE %:search%)" +
            " OR (p.reference IS NOT NULL AND p.reference LIKE %:search%)" +
            " OR (fp.name IS NOT NULL AND fp.name LIKE %:search%)" +
            " OR (ori.country IS NOT NULL AND ori.country LIKE %:search%)" +
            " OR (mat.name IS NOT NULL AND mat.name LIKE %:search%)" +
            " OR (sup.name IS NOT NULL AND sup.name LIKE %:search%)" +
            " OR (cl.label IS NOT NULL AND cl.label LIKE %:search%)" +
            " OR (cert.name IS NOT NULL AND cert.name LIKE %:search%)" +
            " OR (fiber.name IS NOT NULL AND fiber.name LIKE %:search%)" +
            " OR (f.finishing IS NOT NULL AND f.finishing LIKE %:search%)" +
            " OR (hf.handFeel IS NOT NULL AND hf.handFeel LIKE %:search%)" +
            " OR (look.look IS NOT NULL AND look.look LIKE %:search%)" +
            " OR (fabric.fabric IS NOT NULL AND fabric.fabric LIKE %:search%)" +
            " OR (tp.technicalProperty IS NOT NULL AND tp.technicalProperty LIKE %:search%)",
            nativeQuery = true)
    List<Long> findAllIdBySearching(@Param("search") String search);


    @Query(value = "SELECT DISTINCT p.id FROM product p" +
            " LEFT JOIN fabric_pattern fp ON p.fabric_pattern_id = fp.id" +
            " LEFT JOIN material mat ON p.material_id = mat.id" +
            " LEFT JOIN origin ori ON p.origin_id = ori.id" +
            " LEFT JOIN company sup ON p.company_id = sup.id" +
            " LEFT JOIN product_certification pcert ON p.id = pcert.product_id" +
            " LEFT JOIN certification cert ON cert.id = pcert.certification_id" +
            " LEFT JOIN composition compo ON compo.product_id = p.id" +
            " LEFT JOIN fiber ON compo.fiber_id = fiber.id" +
            " LEFT JOIN price ON price.id = p.price_id" +
            " WHERE (fp.id IS NULL OR fp.id LIKE :fpId)" +
            " AND (ori.id IS NULL OR ori.id LIKE :oriId)" +
            " AND (mat.id IS NULL OR mat.id LIKE %:matId)" +
            " AND (sup.id IS NULL OR sup.id LIKE %:supId%)" +
            " AND (cert.id IS NULL OR cert.id LIKE %:certId%)" +
            " AND (fiber.id IS NULL OR fiber.id LIKE %:fiberId%)" +
            " AND (price.id IS NULL OR price.id LIKE %:priceId%)" +
            " AND (p.weight IS NULL OR p.weight BETWEEN :weightMin AND :weightMax)" +
            " AND (p.width IS NULL OR p.width BETWEEN :widthMin AND :widthMax)",
            nativeQuery = true)
    List<Long> findAllIdWithFilter(@Param("fpId") String fpId, @Param("oriId") String oriId,
                                   @Param("matId") String matId, @Param("supId") String supId,
                                   @Param("certId") String certId, @Param("fiberId") String fiberId,
                                   @Param("weightMin") Integer weightMin, @Param("weightMax") Integer weightMax,
                                   @Param("widthMin") Integer widthMin, @Param("widthMax") Integer widthMax,
                                   @Param("priceId") String priceId);
}

