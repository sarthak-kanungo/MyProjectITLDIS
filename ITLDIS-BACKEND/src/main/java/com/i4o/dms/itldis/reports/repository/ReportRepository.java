package com.i4o.dms.itldis.reports.repository;

import java.util.List;

/**
 * Report Repository Interface
 * TODO: Add custom query methods based on ReportDao methods
 * Reference: ITLDIS/src/main/java/dao/ReportDao.java
 * 
 * Note: Implementation is provided by ReportRepositoryImpl
 */
public interface ReportRepository {
    
    /**
     * Get job types for MIS report
     * Reference: ReportDao.getJobTypes()
     */
    List<Object[]> getJobTypes(String dealerCode, String fromDate, String toDate);
    
    /**
     * Get job type installations
     * Reference: ReportDao.getJobTypeInstallation()
     */
    List<Object[]> getJobTypeInstallations(String dealerCode, String fromDate, String toDate);
    
    // TODO: Add more query methods based on ReportDao methods
}
