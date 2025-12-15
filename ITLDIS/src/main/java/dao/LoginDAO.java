/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;

import org.apache.struts.util.LabelValueBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringType;

import com.common.MethodUtility;

import HibernateMapping.DealervslocationcodeAll;
import HibernateMapping.GenLanguages;
import HibernateUtil.HibernateUtil;
import beans.LoginForm;
import beans.serviceForm;
import dbConnection.dbConnection;

/**
 *
 * @author manish.kishore
 */
public class LoginDAO
{

    String dbPATH = new dbConnection().dbPathAuth;

    /**************************************************************************************
     * Method to check whether password change is required for a user id or not from the database.
     * <br><br><b>Steps:</b>
     * <br>1. Select the password change days timer.
     * <br>2. Make a query to check whether password Change is required for a user id or not.
     * <br>3. return the status.
     * <br><br><b>Author</b> - Sonia
     * @param user_id User Id of a user
     * @return boolean     
     ****************************************************************************************/
    public boolean getLastdate(String user_id)
    {
        boolean result = false;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try
        {
            con = new dbConnection().getConnection();
            MethodUtility obj = new MethodUtility();

            //getting password change days timer/////////////////
            //$1
            String element = obj.getSPASConstants(con, 9);

            // DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            //String date = sdf.format(new java.util.Date());

            //System.out.println("select user_id from user_check where user_id=? and DATEADD(day," + element + ",LAST_CHANGED)<=(select getdate()) and status=?");
            // pstmt = con.prepareStatement("select user_id from UM_user_check where user_id=? and DATEADD(day," + element + " , LAST_CHANGED)<=(select getdate()) and status=?");
            pstmt = con.prepareStatement("select user_id from UM_USERS(NOLOCK) where loginid=? and DATEADD(day," + element + " , UpdatedDate)<=(select getdate()) and status='A'");
            pstmt.setString(1, user_id);
            //  pstmt.setInt(2, 1);
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                result = true;
            }

        }
        catch (Exception ae)
        {
            ae.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }
                if (con != null)
                {
                    con.close();
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**************************************************************************************
     * Method to get Parent user id of particular userid from database.
     * <br><br><b>Steps:</b>
     * <br>1. Select the Parent user id of a user id from 'USER_ASSIGN' table.
     * <br>2. Check the status of Parent User id.
     * <br>3. If staus of Parent User id is 'Active' then retun Parent User id.  
     * <br>4. else return empty string.
     * <br><br><b>Author</b> - 
     * @param type_id User Type Id
     * @param user User Id of a user
     * @return String
     ****************************************************************************************/
    /*  public String getParent(String type_id, String user)
    {
    String parentUser = "";
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try
    {
    con = new dbConnection().getConnection();
    pstmt = con.prepareStatement("select User_Id from USER_ASSIGN ua,UM_user_check uc where uc.user_id=ua.user_id and uc.status=1 and ua.Sub_User_Id =? AND ua.Sub_User_Type_Id=? order by ua.USER_TYPE_ID ASC");
    
    pstmt.setString(1, user);
    pstmt.setString(2, type_id);
    rs = pstmt.executeQuery();
    if (rs.next())
    {
    parentUser = rs.getString(1);
    }
    }
    catch (Exception ae)
    {
    ae.printStackTrace();
    }
    finally
    {
    try
    {
    if (rs != null)
    {
    rs.close();
    }
    if (pstmt != null)
    {
    pstmt.close();
    }
    if (con != null)
    {
    con.close();
    }
    
    }
    catch (Exception e)
    {
    e.printStackTrace();
    }
    }
    return parentUser;
    }
     */
    public Vector getUserFunctionalties(String userType)
    {
        Vector userFun = new Vector();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int temp;
        try
        {
            //$1
            con = new dbConnection().getConnection();
            pstmt = con.prepareStatement("Select Func_Id from UM_spas103(NOLOCK) where User_Type_Id=" + userType);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                do
                {
                    temp = rs.getInt(1);
                    if (temp != 0)
                    {
                        userFun.add("" + temp);
                    }
                }
                while (rs.next());
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
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }
                if (con != null)
                {
                    con.close();
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return userFun;
    }

    /**
     *
     * @param userId
     * @param password
     * @return
     */
    public String checkUser(String userId, String password)
    {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //  PasswordEncryption passEncry = null;
        java.util.Date lastchangeDate = null, registerDate = null;
        String lastChangeTemp = null;
        String registerDateTemp = null;
        String result = "fail";
        try
        {
            con = new dbConnection().getConnection();
            // If database connection fails (e.g. SQL Server not running), avoid NullPointerException
            // and return a clear status string.
            if (con == null)
            {
                System.err.println("WARN: Database connection is null in LoginDAO.checkUser()");
                return "Connection Problem";
            }

            //System.out.println("select uc.status,uc.user_type_id,spas1.User_Type,uc.Last_Changed,Reg_Date from USER_CHECK uc,SPAS101  spas1 where uc.user_type_id=spas1.User_Type_Id and User_Id='" + userId + "' and password='" + password + "'");
            pstmt = con.prepareStatement("select uc.status,uc.user_type_id,spas1.User_Type,uc.Last_Changed,Reg_Date,uc.USER_NAME,uc.password, uc.DealerCode ,uc.DealerName from UM_user_check(NOLOCK) uc,UM_spas101  spas1 where uc.user_type_id=spas1.User_Type_Id and User_Id=? and password=?");

            //  passEncry = new PasswordEncryption();

            // password = PasswordEncryption.encrypt(password);

            pstmt.setString(1, userId);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                if (rs.getString(7).equals(password))
                {
                    lastchangeDate = rs.getDate(4);

                    lastChangeTemp = lastchangeDate == null ? "false" : "true";

                    registerDate = rs.getDate(5);

                    registerDateTemp = registerDate == null ? "false" : "true";

                    // if (rs.getBoolean(1))
                    if (rs.getString(1).equalsIgnoreCase("A"))
                    {
                        result = "Active@@" + rs.getString(2) + "@@" + rs.getString(3) + "@@" + lastChangeTemp + "@@" + registerDateTemp + "@@" + rs.getString(6) + "@@" + rs.getString(8)+ "@@" + rs.getString(9);
                    }
                    else
                    {
                        result = "NotActive";
                    }
                }
                else
                {
                    result = "NotExist";
                }

            }
            else
            {
                result = "NotExist";
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }
                if (con != null)
                {
                    con.close();
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList getUserModels(String userId)
    {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        String result = "fail";
        ArrayList arrList = new ArrayList();
        StringBuilder productFamilySubQuery = new StringBuilder("");
        StringBuilder applicationTypSubQuery = new StringBuilder("");


        try
        {
            con = new dbConnection().getConnection();

            if (con == null)
            {
                result = "Connection Problem";
            }

            stmt = con.createStatement();
            rs = stmt.executeQuery("select distinct ENGINE_SERIES from CAT_USER_PRODUCT_FAMILY(NOLOCK) where USER_ID='" + userId + "'");


            while (rs.next())
            {
                productFamilySubQuery.append("'" + rs.getString(1) + "',");
            }
            if (productFamilySubQuery.length() > 1)
            {
                productFamilySubQuery.deleteCharAt(productFamilySubQuery.length() - 1);
                productFamilySubQuery.insert(0, " where ENGINE_SERIES in(");
                productFamilySubQuery.append(")");
            }

            rs.close();

            rs = stmt.executeQuery("select distinct APPLICATION_TYPE from CAT_USER_PRODUCT_TYPE(NOLOCK) where USER_ID='" + userId + "'");
            while (rs.next())
            {
                applicationTypSubQuery.append("'" + rs.getString(1) + "',");
            }
            if (applicationTypSubQuery.length() > 1)
            {
                applicationTypSubQuery.deleteCharAt(applicationTypSubQuery.length() - 1);
                applicationTypSubQuery.insert(0, " and APPLICATION_TYPE in(");
                applicationTypSubQuery.append(")");
            }

            rs.close();

            arrList.add(productFamilySubQuery);
            arrList.add(applicationTypSubQuery);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (stmt != null)
                {
                    stmt.close();
                }
                if (con != null)
                {
                    con.close();
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return arrList;
    }

    public LinkedHashSet<LabelValueBean> getLanguage()
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        Iterator itr = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();

        String name = "", id = "";
        String hql = " FROM GenLanguages  order by languageName";
        try
        {
            LabelValueBean lv = null;
            query = hrbsession.createQuery(hql);
            itr = query.list().iterator();
            while (itr.hasNext())
            {
                GenLanguages gl = (GenLanguages) itr.next();
                name = gl.getLanguageDisplayCode() == null ? "" : gl.getLanguageDisplayCode();
                id = Integer.toString(gl.getLangId()) == null ? "" : Integer.toString(gl.getLangId()) + "@@" + gl.getIsUserDefaultLanguge();
                lv = new LabelValueBean(name, id);
                result.add(lv);
            }
        }
        catch (Exception ae)
        {
            ae.printStackTrace();
        }
        finally
        {
            try
            {
                if (hrbsession != null)
                {
                    hrbsession.close();
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    public LoginForm getLanguageDetails(LoginForm loginform) throws SQLException
    {

        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        try
        {

            GenLanguages gm = null;
            Query query = hrbsession.createQuery("FROM GenLanguages where langId=?");
            query.setInteger(0, Integer.parseInt(loginform.getLanguage()));
            Iterator itr = query.list().iterator();

            if (itr.hasNext())
            {
                gm = (GenLanguages) itr.next();
                loginform.setLanguageName(gm.getLanguageName());
                loginform.setLanguageCountry(gm.getLanguageCountry());
                loginform.setLanguageCode(gm.getLanguageCode());
                loginform.setIsAppDefaultLanguge1(gm.getIsAppDefaultLanguge1());
                loginform.setIsUserDefaultLanguge(gm.getIsUserDefaultLanguge());
                //languageName,languageCode,languageCountry,isAppDefaultLanguge1,isUserDefaultLanguge

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (hrbsession != null)
                {
                    hrbsession.close();
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return loginform;
    }

 //getDealerAddress(
    public serviceForm getDealerAddress(String dealerCode)
    {
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        serviceForm sf = new serviceForm();
        try
        {
            String hql = "from DealervslocationcodeAll where dealerCode='" + dealerCode + "'";
            Query query = hrbsession.createQuery(hql);
            Iterator itr = query.list().iterator();

            if (itr.hasNext())
            {
                DealervslocationcodeAll dAdd = (DealervslocationcodeAll) itr.next();
                /*result = dAdd.getAddress()==null?"":(dAdd.getAddress());
                result += dAdd.getContactNo()==null?"":(" Ph. "+dAdd.getContactNo());
                result += dAdd.getNetworkCategory()==null?"":("\n"+dAdd.getNetworkCategory());
                result += dAdd.getTinNo()==null?"":("\nTin No. "+dAdd.getTinNo());*/
                sf.setLocationName(dAdd.getLocation()==null?"":dAdd.getLocation());
                sf.setDealerName(dAdd.getDealerName()==null?"":dAdd.getDealerName());
                sf.setPayeeMobilePhone(dAdd.getContactNo()==null?"":dAdd.getContactNo());
                sf.setPayeeDistrict(dAdd.getAddress()==null?"":dAdd.getAddress());
                sf.setPayeePinCode(dAdd.getTinNo()==null?"":dAdd.getTinNo());
                sf.setGstCode(dAdd.getGstNo()==null?"":dAdd.getGstNo());
                sf.setPaymentMode(dAdd.getNetworkCategory()==null?"":dAdd.getNetworkCategory());
                sf.setStockistId(dAdd.getStockistId()==null?"":dAdd.getStockistId());
                sf.setStockistName(dAdd.getStockistName()==null?"":dAdd.getStockistName());
            }

        } catch (Exception ae)
        {
            hrbsession.getTransaction().rollback();
            ae.printStackTrace();
        } finally
        {
            try
            {
                if (hrbsession != null)
                {

                    hrbsession.close();

                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return sf;
    }

    public String getBinaryUrl(String userId){
    Session hrbsession = HibernateUtil.getSessionFactory().openSession();
    String binaryString="";
        try{
            Query qry=hrbsession.createSQLQuery("SELECT  CONVERT(VARBINARY(100), 'IBDMS::"+userId+"'+substring(convert(varchar(10),getdate(),108 ),4,2) + substring(convert(varchar(10),getdate(),108 ),7,2)) AS ToBinary,"+
                                                "cast(0x4942444D533A3A313536323532373035 as VARCHAR(100) )as StringFromBinaryFromString ").addScalar("ToBinary",StringType.INSTANCE).addScalar("StringFromBinaryFromString",StringType.INSTANCE);
            List list=qry.list();

            if(list !=null && !list.isEmpty()){
                Iterator it=list.iterator();
                Object[] obj=(Object[])it.next();
                binaryString = (obj[0]==null ? "" : "0x"+obj[0].toString())+"@@"+(obj[1]==null ? "" : obj[1].toString());
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally
        {
            try
            {
                if (hrbsession != null)
                {

                    hrbsession.close();

                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return binaryString;
    }
}