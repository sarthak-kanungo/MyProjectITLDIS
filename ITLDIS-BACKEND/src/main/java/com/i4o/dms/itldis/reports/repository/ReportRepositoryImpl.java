package com.i4o.dms.itldis.reports.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Report Repository Implementation
 * Implements native query methods for reports
 */
@Repository
public class ReportRepositoryImpl implements ReportRepository {
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public List<Object[]> getJobTypes(String dealerCode, String fromDate, String toDate) {
        Query query = entityManager.createNativeQuery(
            "EXEC SP_DMIS_JobTypes :dealerCode, :fromDate, :toDate"
        );
        query.setParameter("dealerCode", dealerCode);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        return query.getResultList();
    }
    
    @Override
    public List<Object[]> getJobTypeInstallations(String dealerCode, String fromDate, String toDate) {
        Query query = entityManager.createNativeQuery(
            "EXEC SP_DMIS_Installations :dealerCode, :fromDate, :toDate"
        );
        query.setParameter("dealerCode", dealerCode);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        return query.getResultList();
    }
}

