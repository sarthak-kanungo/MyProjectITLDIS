package com.i4o.dms.itldis.eamg.servicebulletin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.eamg.servicebulletin.domain.EamgServiceBulletin;
import java.util.List;

/**
 * EAMG Service Bulletin Repository
 */
@Repository
public interface EamgServiceBulletinRepository extends JpaRepository<EamgServiceBulletin, Long> {
    
    EamgServiceBulletin findByBulletinNo(String bulletinNo);
    
    @Query("SELECT sb FROM EamgServiceBulletin sb WHERE sb.type = :type AND sb.yearOfIssue = :yearOfIssue")
    List<EamgServiceBulletin> findByTypeAndYearOfIssue(@Param("type") String type, 
                                                       @Param("yearOfIssue") String yearOfIssue);
}
