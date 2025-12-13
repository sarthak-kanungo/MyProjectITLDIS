package com.i4o.dms.itldis.sapintegration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * TCP (Travel Card Processing) DAO
 * Migrated from: ITLDIS/src/main/java/sapIntegration/TcpDAO.java
 * 
 * @author chandan.sharma
 * @author Migrated to Spring Boot
 */
@Repository
public class TcpDAO {
    
    private static final Logger logger = LoggerFactory.getLogger(TcpDAO.class);
    
    @Autowired
    private DBUtils dbUtils;
    
    /**
     * Check if chassis number exists in travel card master
     * 
     * @param chassisNo Chassis number
     * @return true if exists, false otherwise
     */
    public boolean isExistChassis(String chassisNo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        
        try {
            conn = dbUtils.getConnection();
            stmt = conn.prepareStatement("SELECT CHASSIS_NO FROM TRAVEL_CARD_MASTER WHERE CHASSIS_NO = ?");
            stmt.setString(1, chassisNo);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                result = true;
            }
        } catch (Exception ex) {
            logger.error("Error checking chassis existence: {}", chassisNo, ex);
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return result;
    }
    
    /**
     * Get TCP ID by TCP name
     * 
     * @param tcpName TCP name
     * @return TCP ID, 0 if not found
     */
    public int isExistTcpName(String tcpName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int tcpId = 0;
        
        try {
            conn = dbUtils.getConnection();
            stmt = conn.prepareStatement("SELECT TCP_ID FROM TCP_MASTER WHERE TCP_NAME=?");
            stmt.setString(1, tcpName);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                tcpId = rs.getInt(1);
            }
        } catch (Exception ex) {
            logger.error("Error getting TCP ID for name: {}", tcpName, ex);
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return tcpId;
    }
    
    /**
     * Update or insert TCP master
     * 
     * @param tcpName TCP name
     * @return TCP ID
     */
    public int updatTcpMaster(String tcpName) {
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        int tcpId = 0;
        
        try {
            conn = dbUtils.getConnection();
            stmt = conn.prepareStatement(
                "INSERT INTO TCP_MASTER (TCP_NAME,STATUS,CREATED_BY,CREATED_ON) values (?,?,?,?)", 
                Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, tcpName);
            stmt.setString(2, "Active");
            stmt.setString(3, "ho");
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
            
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                tcpId = rs.getInt(1);
            }
        } catch (Exception ex) {
            logger.error("Error updating TCP master: {}", tcpName, ex);
        } finally {
            closeResources(rs, stmt, conn);
        }
        
        return tcpId;
    }
    
    /**
     * Insert travel card details
     * 
     * @param newTcpIdMap Map of TCP IDs
     * @param chassisNo Chassis number
     * @return true if successful
     */
    public boolean insertTravelCardDetails(Map<Integer, String> newTcpIdMap, String chassisNo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean insertFlag = false;
        int flag = 0, res = 0, res1 = 0;
        
        try {
            conn = dbUtils.getConnection();
            conn.setAutoCommit(false);
            
            // Insert data in Card Master
            flag = insertTravelCardMaster(chassisNo, conn);
            
            if (flag == 1) {
                Iterator<Map.Entry<Integer, String>> itr = newTcpIdMap.entrySet().iterator();
                stmt = conn.prepareStatement(
                    "INSERT INTO TRAVEL_CARD_DETAILS (CHASSIS_NO,TCP_ID,REV_NO,IS_LATEST,SERIAL_NO,CREATED_BY,CREATED_ON) VALUES (?,?,?,?,?,?,?)"
                );
                
                while (itr.hasNext()) {
                    Map.Entry<Integer, String> mapData = itr.next();
                    stmt.setString(1, chassisNo);
                    stmt.setInt(2, mapData.getKey());
                    stmt.setInt(3, 0);
                    stmt.setString(4, "Y");
                    stmt.setString(5, mapData.getValue());
                    stmt.setString(6, "ho");
                    stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                    res = stmt.executeUpdate();
                }
                
                stmt = conn.prepareStatement("UPDATE CHASSIS_MODEL set TRAVEL_CARD_STATUS='Y' where chassis_no=?");
                stmt.setString(1, chassisNo);
                res1 = stmt.executeUpdate();
                
                if (res > 0 && res1 > 0) {
                    insertFlag = true;
                }
            }
        } catch (Exception ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                logger.error("Error rolling back transaction", ex1);
            }
            logger.error("Error inserting travel card details", ex);
        } finally {
            try {
                if (flag == 0 && conn != null) {
                    conn.rollback();
                }
                if (conn != null) {
                    conn.commit();
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                logger.error("Error in finally block", e);
            }
        }
        
        return insertFlag;
    }
    
    /**
     * Insert travel card master record
     * 
     * @param chassisNo Chassis number
     * @param conn Database connection
     * @return 1 if successful, 0 otherwise
     */
    private int insertTravelCardMaster(String chassisNo, Connection conn) {
        PreparedStatement stmt = null;
        int flag = 0;
        
        try {
            String subQuery = "INSERT INTO TRAVEL_CARD_MASTER (CHASSIS_NO,ENGINE_NO,CREATED_BY,CREATED_ON,GENERAL_COMMENTS,VERIFICATION_STATUS,IS_APPROVAL_REQUIRED,IS_APPROVED) VALUES (?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(subQuery);
            stmt.setString(1, chassisNo);
            stmt.setString(2, null);
            stmt.setString(3, "ho");
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            stmt.setString(5, null);
            stmt.setString(6, "N");
            stmt.setString(7, "N");
            stmt.setString(8, "N");
            flag = stmt.executeUpdate();
        } catch (Exception e) {
            logger.error("Error inserting travel card master", e);
            flag = 0;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                logger.error("Error closing statement", e);
            }
        }
        
        return flag;
    }
    
    /**
     * Close database resources
     */
    private void closeResources(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            logger.error("Error closing resources", e);
        }
    }
}

