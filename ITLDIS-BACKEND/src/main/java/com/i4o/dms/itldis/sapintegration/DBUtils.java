package com.i4o.dms.itldis.sapintegration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Database Utilities for SAP Integration
 * Migrated from: ITLDIS/src/main/java/sapIntegration/DBUtills.java
 * 
 * @author chandan.sharma
 * @author Migrated to Spring Boot
 */
@Component
public class DBUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);
    
    @Value("${sap.integration.db.url:jdbc:sqlserver://localhost:1433;databaseName=SPASVOLTAS;useUnicode=true;characterEncoding=utf8;}")
    private String dbUrl;
    
    @Value("${sap.integration.db.username:eman_test}")
    private String dbUsername;
    
    @Value("${sap.integration.db.password:eman123}")
    private String dbPassword;
    
    @Value("${sap.integration.db.driver:com.microsoft.sqlserver.jdbc.SQLServerDriver}")
    private String dbDriver;
    
    /**
     * Get database connection for SAP integration
     * 
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            logger.debug("SAP Integration DB connection established");
            return conn;
        } catch (ClassNotFoundException ex) {
            logger.error("Database driver not found: {}", dbDriver, ex);
            throw new SQLException("Database driver not found: " + dbDriver, ex);
        } catch (SQLException ex) {
            logger.error("Failed to establish database connection", ex);
            throw ex;
        }
    }
}

