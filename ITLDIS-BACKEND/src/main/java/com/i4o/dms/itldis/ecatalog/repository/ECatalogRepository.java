package com.i4o.dms.itldis.ecatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.ecatalog.domain.CatalogPart;
import java.util.List;

/**
 * E-Catalog Repository
 * TODO: Add custom query methods based on legacy DAO methods
 */
@Repository
public interface ECatalogRepository extends JpaRepository<CatalogPart, Long> {
    
    /**
     * Find part by part number
     */
    CatalogPart findByPartNo(String partNo);
    
    /**
     * Search parts
     */
    @Query("SELECT p FROM CatalogPart p WHERE " +
           "(:partNo IS NULL OR p.partNo LIKE %:partNo%) AND " +
           "(:partName IS NULL OR p.partName LIKE %:partName%) AND " +
           "(:category IS NULL OR p.category = :category)")
    List<CatalogPart> searchParts(@Param("partNo") String partNo,
                                  @Param("partName") String partName,
                                  @Param("category") String category);
}
