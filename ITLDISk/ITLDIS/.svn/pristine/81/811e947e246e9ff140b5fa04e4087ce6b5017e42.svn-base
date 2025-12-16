package sapIntegration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chandan.sharma
 */
public class TcpDAO
{

    public boolean isExistChassis(String chassisNo)
    {
        Connection conn1 = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        try
        {
            conn1 = DBUtills.getConnection();
//            if (conn1 == null)
//            {
//                System.out.println(" Did not get the connection");
//                return true;
//            }

            stmt = conn1.prepareStatement("SELECT CHASSIS_NO FROM TRAVEL_CARD_MASTER WHERE CHASSIS_NO = ?");
            stmt.setString(1, chassisNo);
            rs = stmt.executeQuery();
            if (rs.next())
            {
                result = true;
                return result;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {

            try
            {
                if (rs != null)
                {
                    rs.close();
                    rs = null;
                }
                if (stmt != null)
                {
                    stmt.close();
                    stmt = null;
                }
                if (conn1 != null)
                {
                    conn1.close();
                    conn1 = null;
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        return result;
    }

    public int isExistTcpName(String tcpName)
    {
        Connection conn1 = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int tcpId = 0;
        try
        {
            conn1 = DBUtills.getConnection();
            stmt = conn1.prepareStatement("SELECT TCP_ID FROM TCP_MASTER WHERE TCP_NAME=?");
            stmt.setString(1, tcpName);
            rs = stmt.executeQuery();
            if (rs.next())
            {
                tcpId = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {

            try
            {
                if (rs != null)
                {
                    rs.close();
                    rs = null;
                }
                if (stmt != null)
                {
                    stmt.close();
                    stmt = null;
                }
                if (conn1 != null)
                {
                    conn1.close();
                    conn1 = null;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
      //  System.out.println("tcp  Id  " + tcpId);
        return tcpId;
    }

    public int updatTcpMaster(String tcpName)
    {
        PreparedStatement stmt = null;
        Connection conn1 = null;
        ResultSet rs = null;
        int tcpId = 0;
        try
        {
            conn1 = DBUtills.getConnection();
            stmt = conn1.prepareStatement("INSERT INTO TCP_MASTER (TCP_NAME,STATUS,CREATED_BY,CREATED_ON) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, tcpName);
            stmt.setString(2, "Active");
            stmt.setString(3, "ho");
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next())
            {
                tcpId = rs.getInt(1);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                    rs = null;
                }
                if (stmt != null)
                {
                    stmt.close();
                    stmt = null;
                }
                if (conn1 != null)
                {
                    conn1.close();
                    conn1 = null;
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        return tcpId;

    }

    public boolean insertTravelCardDetails(Map<Integer, String> newTcpIdMap, String chassisNo)
    {

        Connection conn1 = null;
        PreparedStatement stmt = null;
       boolean insertFlag=false;
        int flag = 0,res=0,res1=0;
        try
        {
            conn1 = DBUtills.getConnection();
            conn1.setAutoCommit(false);
            //Call To insert Data in Card Master
            flag = insertTravelCardMaster(chassisNo, conn1);

            if(flag==1)
            {
                Iterator itr = newTcpIdMap.entrySet().iterator();
                stmt = conn1.prepareStatement("INSERT INTO TRAVEL_CARD_DETAILS (CHASSIS_NO,TCP_ID,REV_NO,IS_LATEST,SERIAL_NO,CREATED_BY,CREATED_ON) VALUES (?,?,?,?,?,?,?)");
                while (itr.hasNext())
                {
                    Map.Entry mapData = (Map.Entry) itr.next();
                 
                    stmt.setString(1, chassisNo);
                    stmt.setInt(2, (Integer) mapData.getKey());
                    stmt.setInt(3, 0);
                    stmt.setString(4, "Y");
                    stmt.setString(5, (String) mapData.getValue());
                    stmt.setString(6, "ho");
                    stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                    res=stmt.executeUpdate();

                }
                stmt = conn1.prepareStatement("UPDATE CHASSIS_MODEL set TRAVEL_CARD_STATUS='Y' where chassis_no=?");
                stmt.setString(1,chassisNo);
                res1=stmt.executeUpdate();
                if(res>0 && res1>0)
                {
                    insertFlag=true;
                }
            }
        }
        catch (Exception ex)
        {
            try
            {
                conn1.rollback();
            }
            catch (SQLException ex1)
            {
                ex1.printStackTrace();
                //Logger.getLogger(TcpDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }

            ex.printStackTrace();
        }
        finally
        {

            try
            {
                if (flag == 0)
                {
                    conn1.rollback();
                }
                conn1.commit();

                if (stmt != null)
                {
                    stmt.close();
                    stmt = null;
                }
                if (conn1 != null)
                {
                    conn1.close();
                    conn1 = null;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        return insertFlag;
    }

    public int insertTravelCardMaster(String chassisNo, Connection conn1)
    {
       
        PreparedStatement stmt = null;
        String subQuery = null;
     
        int flag = 0;
        try
        {
            subQuery ="INSERT INTO TRAVEL_CARD_MASTER (CHASSIS_NO,ENGINE_NO,CREATED_BY,CREATED_ON,GENERAL_COMMENTS,VERIFICATION_STATUS,IS_APPROVAL_REQUIRED,IS_APPROVED) VALUES (?,?,?,?,?,?,?,?)";
            stmt = conn1.prepareStatement(subQuery);
            stmt.setString(1, chassisNo);
            stmt.setString(2, null);
            stmt.setString(3, "ho");
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            stmt.setString(5, null);
            stmt.setString(6, "N");
            stmt.setString(7, "N");
            stmt.setString(8, "N");
            flag = stmt.executeUpdate();
        }
        catch (Exception e)
        {
            flag = 0;
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                    stmt = null;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return flag;

    }

   
}
