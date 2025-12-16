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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;

import javax.servlet.jsp.JspWriter;

import org.apache.struts.util.LabelValueBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StringType;

import com.common.DBQueryConstant;
import com.common.EncryptionDecryption;

import HibernateMapping.DmsUserCheck;
import HibernateMapping.UmDmsDealerMaster;
import HibernateUtil.HibernateUtil;
import beans.UserManagementForm;
import dbConnection.dbConnection;

/**
 *
 * @author manish.kishore
 */
public class UserManagementDAO implements DBQueryConstant {

    Connection conn = null;

    public UserManagementDAO() {
    }

    public UserManagementDAO(Connection conn) {
        this.conn = conn;
    }

    public void getUserDataList(UserManagementForm userForm) {
//        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<LabelValueBean> regionList = null, userTypeList = null, cityList = null, stateList = null, countryList = null, dealerNameList = null;
        LabelValueBean lv = null;
        String stateTemp = null;
        String cityTemp = null;
        String countryTemp = null;

        try {
            regionList = new ArrayList();
            userTypeList = new ArrayList();
            cityList = new ArrayList();
            stateList = new ArrayList();
            countryList = new ArrayList();
            dealerNameList = new ArrayList();

//            con = new dbConnection().getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery(Get_FROM_REGION_MASTER);
            while (rs.next()) {
                lv = new LabelValueBean();

                lv.setLabel(rs.getString(2));
                lv.setValue(rs.getString(1));

                regionList.add(lv);
            }
            userForm.setLabelList(regionList);

            rs = stmt.executeQuery(Get_USER_TYPE_FROM_SPAS101);
            while (rs.next()) {
                lv = new LabelValueBean();

                lv.setLabel(rs.getString(2));
                lv.setValue(rs.getString(1));

                userTypeList.add(lv);
            }
            userForm.setLabelList1(userTypeList);


            rs = stmt.executeQuery(Get_STATE_FROM_STATES);
            while (rs.next()) {

                stateTemp = rs.getString(1);
                lv = new LabelValueBean();

                lv.setLabel(stateTemp);
                lv.setValue(stateTemp);

                stateList.add(lv);
            }
            userForm.setLabelList2(stateList);


            rs = stmt.executeQuery(Get_CITY_FROM_CITIES);
            while (rs.next()) {

                cityTemp = rs.getString(1);
                lv = new LabelValueBean();

                lv.setLabel(cityTemp);
                lv.setValue(cityTemp);

                cityList.add(lv);
            }
            userForm.setLabelList3(cityList);

            rs = stmt.executeQuery(Get_COUNTRY_FROM_COUNTRIES);
            while (rs.next()) {

                countryTemp = rs.getString(1);
                lv = new LabelValueBean();

                lv.setLabel(countryTemp);
                lv.setValue(countryTemp);

                countryList.add(lv);
            }
            userForm.setLabelList4(countryList);
            rs = stmt.executeQuery(Get_DealerName_FROM_UM_DealerMaster);
            while (rs.next()) {
                lv = new LabelValueBean();

                lv.setLabel(rs.getString(2));
                lv.setValue(rs.getString(1));

                dealerNameList.add(lv);
            }
            userForm.setLabelList5(dealerNameList);

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
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
                e.printStackTrace();
            }
        }
    }

    public void getUserDataListNew(UserManagementForm userForm) {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<LabelValueBean> userTypeList = null, dealerNameList = null;
        LabelValueBean lv = null;

        try {
            userTypeList = new ArrayList();
            dealerNameList = new ArrayList();

            stmt = conn.createStatement();

            rs = stmt.executeQuery(Get_USER_TYPE_FROM_SPAS101);
            while (rs.next()) {
                lv = new LabelValueBean();

                lv.setLabel(rs.getString(2));
                lv.setValue(rs.getString(1));

                userTypeList.add(lv);
            }
            userForm.setLabelList1(userTypeList);


            rs = stmt.executeQuery(Get_DealerName_FROM_UM_DealerMaster);
            while (rs.next()) {
                lv = new LabelValueBean();

                lv.setLabel(rs.getString(2));
                lv.setValue(rs.getString(1));

                dealerNameList.add(lv);
            }
            userForm.setLabelList5(dealerNameList);

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
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
                e.printStackTrace();
            }
        }
    }

    public void getDealerDataList(UserManagementForm userForm) {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<LabelValueBean> countryList = null;
        LabelValueBean lv = null;

        try {
            countryList = new ArrayList();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Get_country_FROM_MSP_Countries_EXP);
            while (rs.next()) {
                lv = new LabelValueBean();

                lv.setLabel(rs.getString(2) + "[" + rs.getString(1) + "]");
                lv.setValue(rs.getString(1));

                countryList.add(lv);
            }
            userForm.setLabelList1(countryList);


        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
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
                e.printStackTrace();
            }
        }
    }

    public String insertUserDetails(UserManagementForm userForm, String user_id) {
        EncryptionDecryption passEncry = null;
//        Connection con = null;
        PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null, pstmt3 = null, pstmt4 = null, pstmt5 = null;
        // ResultSet rs = null;
        String result = "fail";
        //String regionId = null, city = null, state = null, country = null;

        java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
        java.sql.Date todaysDate1 = null;
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Query query = null, query1 = null;

        try {
//            con = new dbConnection().getConnection();


            // regionId = userForm.getRegionId();
            //city = userForm.getCity();
            //state = userForm.getState();
            //country = userForm.getCountry();

//            if (regionId.equals("Other")) {
//                pstmt1 = conn.prepareStatement("insert into UM_region_master(REGION_NAME) values(?)", Statement.RETURN_GENERATED_KEYS);
//                pstmt1.setString(1, userForm.getRegionIdOther());
//
//                try {
//                    pstmt1.executeUpdate();
//
//                    rs = pstmt1.getGeneratedKeys();
//
//                    if (rs.next()) {
//                        regionId = rs.getString(1);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }

//            if (city.equals("Other")) {
//                userForm.setCity(userForm.getCityOther());
//
//                pstmt2 = conn.prepareStatement("insert into GEN_cities(CITY_NAME) values(?)");
//                pstmt2.setString(1, userForm.getCityOther());
//                try {
//                    pstmt2.executeUpdate();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }

//            if (state.equals("Other")) {
//                userForm.setState(userForm.getStateOther());
//
//                pstmt3 = conn.prepareStatement("insert into GEN_states(STATE_NAME) values(?)");
//                pstmt3.setString(1, userForm.getStateOther());
//                pstmt3.executeUpdate();
//            }
//
//            if (country.equals("Other")) {
//                userForm.setCountry(userForm.getCountryOther());
//
//                pstmt4 = conn.prepareStatement("insert into GEN_countries(COUNTRY_NAME) values(?)");
//                pstmt4.setString(1, userForm.getCountryOther());
//                pstmt4.executeUpdate();
//            }

//            query = s.createQuery("INSERT INTO DmsUserCheck(userId, password, userTypeId,userName,designation,address1,mobile,email, regDate, status) "
//                    + " values (:userId,:password,:userTypeId,:userName,:designation,:address1,:mobile,:email,:regDate,:status)");

//            pstmt = conn.prepareStatement("INSERT INTO UM_DMS_USER_CHECK(USER_ID, PASSWORD, USER_TYPE_ID, "
//                    + "USER_NAME, DESIGNATION, ADDRESS1,"
//                    + "MOBILE, EMAIL, REG_DATE, STATUS) "
//                    + "VALUES(?,?,?,?,?,?,?,?,?,?)");

            query1 = s.createQuery("select userId from UserCheck where userId=?");
            query1.setString(0, userForm.getUserId());
            List list = query1.list();
            Iterator i = list.iterator();
            if (!i.hasNext()) {
                DmsUserCheck duc = new DmsUserCheck(userForm.getUserId(), EncryptionDecryption.encrypt(userForm.getPassword()), Long.parseLong(userForm.getUserTypeId()), userForm.getFirstName(), userForm.getAddress1(), userForm.getMobile(), userForm.getEmail(), todaysDate, "A", userForm.getDealerCode(), user_id, "", todaysDate1);
                s.save(duc);
                t.commit();
                result = "success";
            } else {
                result = "alreadyexist";
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (s != null) {
                    s.close();
                    s = null;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      //  System.out.println("" + result);
        return result;
    }
    ///////////////////////////////////////***User Type MASTER***////////////////////////////////////////////////

    public ArrayList<UserManagementForm> getUserTypeList(UserManagementForm UserManagementForm, String nameSrch) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<UserManagementForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;


        try {
            dataList = new ArrayList<UserManagementForm>();
            stmt = conn.createStatement();
            if (!SearchQuery.equals("")) {
                Subsql = " where USER_TYPE like '%" + SearchQuery + "%' ";
            }
            sql = "select USER_TYPE_ID,USER_TYPE from UM_spas101 "
                    + "" + Subsql + " ";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UserManagementForm = new UserManagementForm();
                UserManagementForm.setId(rs.getString(1));
                UserManagementForm.setName(rs.getString(2));
                //UserManagementForm.setDescription(rs.getString(3));
                //UserManagementForm.setStatus(rs.getString(4));
                dataList.add(UserManagementForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public String addUserType(String desc, String name, String userId) {
        String results = "";
        PreparedStatement pstmt = null;
        try {
            masterDAO obj = new masterDAO(conn);
            String check = obj.check_in_Db(name, "UM_spas101", "USER_TYPE", "");
            if (check.equalsIgnoreCase("notexist")) {
                pstmt = conn.prepareStatement("insert into UM_spas101 (USER_TYPE,CREATED_BY ,CREATED_ON) values(?,?,?)");
                pstmt.setString(1, name.toUpperCase());
                // pstmt.setString(2, desc);
                pstmt.setString(2, userId);
                pstmt.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
                //pstmt.setString(5, "Active");
                pstmt.executeUpdate();
                conn.commit();
                results = "Success@@User Type '" + name.toUpperCase() + "' has been Successfully Added.";
            } else {
                results = "Failure@@User Type '" + name.toUpperCase() + "' Already Exists.";
                conn.rollback();
            }
        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to add User Type, Please contact system Administrator.";
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public String UpdateUserType(String status, String userId, String desc, String name, String type, String id) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular User Type.
         */
        String result = "";
        PreparedStatement pstmt = null;
        String newStatus = "";
        try {
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Active")) {
                    newStatus = "Inactive";
                } else {
                    newStatus = "Active";
                }
                pstmt = conn.prepareStatement("UPDATE UM_spas101 SET MODIFIED_BY=?,MODIFIED_ON=? WHERE USER_TYPE_ID=? ");
                //pstmt.setString(1, newStatus);
                pstmt.setString(1, userId);
                pstmt.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
                pstmt.setString(3, id);
                pstmt.executeUpdate();
                conn.commit();
                result = "Success@@Status has been updated Successfully.";
            } else {
                masterDAO obj = new masterDAO(conn);
                String check = obj.check_in_Db(name, "UM_spas101", "USER_TYPE", " and USER_TYPE_ID <> " + id + "");
                if (check.equalsIgnoreCase("notexist")) {
                    pstmt = conn.prepareStatement("UPDATE UM_spas101 SET USER_TYPE=?,MODIFIED_BY=?,MODIFIED_ON=? WHERE USER_TYPE_ID=? ");
                    pstmt.setString(1, name.toUpperCase());
                    // pstmt.setString(2, desc);
                    pstmt.setString(2, userId);
                    pstmt.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
                    pstmt.setString(4, id);
                    pstmt.executeUpdate();
                    conn.commit();
                    result = "Success@@User Type '" + name.toUpperCase() + "' has been updated Successfully.";
                } else {
                    result = "Failure@@User Type '" + name.toUpperCase() + "' Already Exists.";
                    conn.rollback();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update User Type, Please contact system Administrator.";
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    ///////////////////////////////////////***Region MASTER***////////////////////////////////////////////////

    public ArrayList<UserManagementForm> getRegionList(UserManagementForm UserManagementForm, String nameSrch) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<UserManagementForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch;


        try {
            dataList = new ArrayList<UserManagementForm>();
            stmt = conn.createStatement();
            if (!SearchQuery.equals("")) {
                Subsql = " where REGION_NAME like '%" + SearchQuery + "%' ";
            }
            sql = "select REGION_ID,REGION_NAME,ADDRESS,STATUS from UM_region_master "
                    + "" + Subsql + " ";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UserManagementForm = new UserManagementForm();
                UserManagementForm.setId(rs.getString(1).trim());
                UserManagementForm.setName(rs.getString(2).trim());
                UserManagementForm.setDescription(rs.getString(3).trim());
                UserManagementForm.setStatus(rs.getString(4).trim());
                dataList.add(UserManagementForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public String addRegion(String desc, String name, String userId) {
        String results = "";
        PreparedStatement pstmt = null;
        try {
            masterDAO obj = new masterDAO(conn);
            String check = obj.check_in_Db(name, "UM_region_master", "REGION_NAME", "");
            if (check.equalsIgnoreCase("notexist")) {
                pstmt = conn.prepareStatement("insert into UM_region_master (REGION_NAME,ADDRESS,CREATED_BY ,CREATED_ON,STATUS) values(?,?,?,?,?)");
                pstmt.setString(1, name.toUpperCase());
                pstmt.setString(2, desc);
                pstmt.setString(3, userId);
                pstmt.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
                pstmt.setString(5, "Active");
                pstmt.executeUpdate();
                conn.commit();
                results = "Success@@Region '" + name.toUpperCase() + "' has been Successfully Added.";
            } else {
                results = "Failure@@Region '" + name.toUpperCase() + "' Already Exists.";
                conn.rollback();
            }
         //   System.out.println("add region:" + results);
        } catch (Exception ae) {
            ae.printStackTrace();
            results = "Failure@@Unable to add Region, Please contact system Administrator.";
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public String getUserList(UserManagementForm userForm) {
        Statement stmt = null;
        ResultSet rs = null;
        //String result = "fail";
        StringBuilder data = new StringBuilder("<listdata>");

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select USER_ID from UM_user_check where USER_ID like '%" + userForm.getUserId() + "%'");
            while (rs.next()) {
                data.append(rs.getString(1) + "|");
            }
            data.append("</listdata>");

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
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
                e.printStackTrace();
            }
        }
        return data.toString();
    }

    public String getUserByID(UserManagementForm userForm) {
        Statement stmt = null;
        ResultSet rs = null;
        String result = "fail";

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select USER_ID,PASSWORD,USER_TYPE_ID,USER_NAME,COMPANY_NAME,ADDRESS1,ADDRESS2,CITY,STATE,COUNTRY,PINCODE,PHONE,MOBILE,EMAIL,COMMUNICATION_MOB,COMMUNICATION_EMAIL,REG_DATE,STATUS,RegionId  from UM_user_check where USER_ID='" + userForm.getUserId() + "'");
            if (rs.next()) {
                userForm.setUserId(rs.getString("USER_ID"));
                userForm.setPassword(rs.getString("PASSWORD"));
                userForm.setUserTypeId(rs.getString("USER_TYPE_ID"));
                userForm.setFirstName(rs.getString("USER_NAME"));
                userForm.setCompanyName(rs.getString("COMPANY_NAME"));
                userForm.setAddress1(rs.getString("ADDRESS1"));
                userForm.setAddress2(rs.getString("ADDRESS2"));
                userForm.setCity(rs.getString("city"));
                userForm.setState(rs.getString("STATE"));
                userForm.setCountry(rs.getString("COUNTRY"));
                userForm.setPincode(rs.getString("PINCODE"));
                userForm.setPhoneNo(rs.getString("PHONE"));
                userForm.setMobile(rs.getString("MOBILE"));
                userForm.setEmail(rs.getString("EMAIL"));
                userForm.setRegionId(rs.getString("RegionId"));
                userForm.setStatus(rs.getString("STATUS"));

                result = "success";
            } else {
                result = "notexists";
            }



        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
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
                e.printStackTrace();
            }
        }
        return result;
    }

    public String updateUserStatus(UserManagementForm userForm) {
        Connection con = null;
        PreparedStatement pstmt = null, pstmt1 = null;
        ResultSet rs = null;
        String result = "fail";

        try {
            con = new dbConnection().getConnection();

            pstmt1 = con.prepareStatement("select USER_ID from UM_user_check where USER_ID=?");
            pstmt1.setString(1, userForm.getUserId());
            rs = pstmt1.executeQuery();

            if (rs.next()) {

                pstmt = con.prepareStatement("UPDATE UM_user_check SET STATUS=? WHERE USER_ID=?");

                pstmt.setString(1, userForm.getStatus());
                pstmt.setString(2, userForm.getUserId());

                pstmt.executeUpdate();

                result = "success";
            } else {
                result = "notexists";
            }

        } catch (Exception ae) {

            ae.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (pstmt1 != null) {
                    pstmt1.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String UpdateRegion(String status, String userId, String desc, String name, String type, String id) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular Region.
         */
        String result = "";
        PreparedStatement pstmt = null;
        String newStatus = "";
        try {
            masterDAO obj = new masterDAO(conn);
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("Active")) {
                    newStatus = "Inactive";
                } else {
                    newStatus = "Active";
                }
                String check = obj.check_in_Db(id, "UM_user_check", "RegionId", "");
                if (check.equalsIgnoreCase("notexist")) {
                    pstmt = conn.prepareStatement("UPDATE UM_region_master SET status=?,MODIFIED_BY=?,MODIFIED_ON=? WHERE REGION_ID=? ");
                    pstmt.setString(1, newStatus);
                    pstmt.setString(2, userId);
                    pstmt.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
                    pstmt.setString(4, id);
                    pstmt.executeUpdate();
                    result = "Success@@Status has been updated Successfully.";
                } else {
                    result = "Failure@@Can not update status as Region is in use.";
                }
            } else {
                String check = obj.check_in_Db(name, "UM_region_master", "REGION_NAME", " and REGION_ID <> " + id + "");
                if (check.equalsIgnoreCase("notexist")) {
                    pstmt = conn.prepareStatement("UPDATE UM_region_master SET REGION_NAME=?,ADDRESS=?,MODIFIED_BY=?,MODIFIED_ON=? WHERE REGION_ID=? ");
                    pstmt.setString(1, name.toUpperCase());
                    pstmt.setString(2, desc);
                    pstmt.setString(3, userId);
                    pstmt.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
                    pstmt.setString(5, id);
                    pstmt.executeUpdate();
                    result = "Success@@Region '" + name.toUpperCase() + "' has been updated Successfully.";
                } else {
                    result = "Failure@@Region '" + name.toUpperCase() + "' Already Exists.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update Region, Please contact system Administrator.";
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void getAllUsers(UserManagementForm userForm) {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        LabelValueBean lv = null;
        ArrayList<LabelValueBean> labelList = null;
        String userId = null;
        try {
            con = new dbConnection().getConnection();
            stmt = con.createStatement();

            labelList = new ArrayList();

            rs = stmt.executeQuery("select USER_ID from UM_user_check");

            while (rs.next()) {
                userId = rs.getString(1);

                lv = new LabelValueBean();

                lv.setLabel(userId);
                lv.setValue(userId);

                labelList.add(lv);
            }

            userForm.setLabelList(labelList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String updateUserDetails(UserManagementForm userForm, String userId) {
        String result = "fail";
//        String city = null;
//        String state = null;
//        String country = null;
//        String regionId = null;
        java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Query query = null, query1 = null;
        try {
//            regionId = userForm.getRegionId();
//            city = userForm.getCity();
//            state = userForm.getState();
//            country = userForm.getCountry();

            query1 = s.createQuery("select userId from UserCheck where userId='" + userForm.getUserId() + "'");
            List list = query1.list();
            Iterator itr = list.iterator();
            if (itr.hasNext()) {

//                if (regionId.equals("Other")) {
//                    pstmt1 = conn.prepareStatement("insert into UM_region_master(REGION_NAME) values(?)", Statement.RETURN_GENERATED_KEYS);
//                    pstmt1.setString(1, userForm.getRegionIdOther());
//
//                    try {
//                        pstmt1.executeUpdate();
//
//                        rs = pstmt1.getGeneratedKeys();
//
//                        if (rs.next()) {
//                            regionId = rs.getString(1);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                if (city.equals("Other")) {
//                    userForm.setCity(userForm.getCityOther());
//
//                    pstmt2 = conn.prepareStatement("insert into GEN_cities(CITY_NAME) values(?)");
//                    pstmt2.setString(1, userForm.getCityOther());
//                    try {
//                        pstmt2.executeUpdate();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                if (state.equals("Other")) {
//                    userForm.setState(userForm.getStateOther());
//
//                    pstmt3 = conn.prepareStatement("insert into GEN_states(STATE_NAME) values(?)");
//                    pstmt3.setString(1, userForm.getStateOther());
//                    try {
//                        pstmt3.executeUpdate();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                if (country.equals("Other")) {
//                    userForm.setCountry(userForm.getCountryOther());
//
//                    pstmt4 = conn.prepareStatement("insert into GEN_countries(COUNTRY_NAME) values(?)");
//                    pstmt4.setString(1, userForm.getCountryOther());
//                    try {
//                        pstmt4.executeUpdate();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
                if (userForm.getFlag().equals("edit")) {
                    query = s.createQuery("UPDATE DmsUserCheck SET userTypeId=?,updatedon=?,userName=?,address1=?,mobile=?,email=?,status=?,dealercode=?,updatedby=? WHERE userId=? ");

                } else {
                    query = s.createQuery("UPDATE DmsUserCheck SET userTypeId=?,updatedon=?,userName=?,address1=?,mobile=?,email=?,status=?,dealercode=?,updatedby=?,password=? WHERE userId=? ");

                }
                //query.setString(0, new EncryptionDecryption().encrypt(userForm.getPassword()));
                query.setString(0, userForm.getUserTypeId());
                query.setTimestamp(1, todaysDate);
                query.setString(2, userForm.getFirstName());
                query.setString(3, userForm.getAddress1());
                query.setString(4, userForm.getMobile());
                query.setString(5, userForm.getEmail());
                query.setString(6, userForm.getStatus());
                query.setString(7, userForm.getDealerCode());
                query.setString(8, userId);
                if (userForm.getFlag().equals("edit")) {
                    query.setString(9, userForm.getUserId());
                } else {
                    query.setString(9, new EncryptionDecryption().encrypt(userForm.getPassword()));
                    query.setString(10, userForm.getUserId());
                }
                query.executeUpdate();
                t.commit();

                result = "success";
            } else {
                result = "notexists";
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null) {
                    s.close();
                    s = null;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    ///////////////////////////////////////////////////////Manage Customer//////////////////////////////////////

    public void getCustomerDataList(UserManagementForm userForm) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<LabelValueBean> cityList = null, stateList = null, countryList = null, customerTypeIdList = null;
        LabelValueBean lv = null;
        String stateTemp = null;
        String cityTemp = null;
        String countryTemp = null;

        try {
            cityList = new ArrayList();
            stateList = new ArrayList();
            countryList = new ArrayList();
            customerTypeIdList = new ArrayList();

            con = new dbConnection().getConnection();
            stmt = con.createStatement();

            rs = stmt.executeQuery("SELECT distinct CITY_NAME FROM GEN_cities order by CITY_NAME");
            while (rs.next()) {
                cityTemp = rs.getString(1);
                lv = new LabelValueBean();
                lv.setLabel(cityTemp);
                lv.setValue(cityTemp);
                cityList.add(lv);
            }
            userForm.setLabelList(cityList);

            rs = stmt.executeQuery("SELECT distinct STATE_NAME FROM GEN_states order by STATE_NAME");
            while (rs.next()) {

                stateTemp = rs.getString(1);
                lv = new LabelValueBean();
                lv.setLabel(stateTemp);
                lv.setValue(stateTemp);
                stateList.add(lv);
            }
            userForm.setLabelList1(stateList);

            rs = stmt.executeQuery("SELECT distinct COUNTRY_NAME FROM GEN_countries order by COUNTRY_NAME");
            while (rs.next()) {

                countryTemp = rs.getString(1);
                lv = new LabelValueBean();
                lv.setLabel(countryTemp);
                lv.setValue(countryTemp);
                countryList.add(lv);
            }
            userForm.setLabelList2(countryList);

            rs = stmt.executeQuery("SELECT distinct CUST_TYPE_ID,CUSTOMER_TYPE FROM CUSTOMER_TYPE_MASTER Where STATUS='Active' ");
            while (rs.next()) {
                lv = new LabelValueBean();

                lv.setLabel(rs.getString(2));
                lv.setValue(rs.getString(1));

                customerTypeIdList.add(lv);
            }
            userForm.setLabelList3(customerTypeIdList);

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String insertCustomerDetails(UserManagementForm userForm, String userid) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null, pstmt3 = null, pstmt4 = null;
        ResultSet rs = null;
        Statement stmt = null;
        String result = "fail";
        int custId = 0;
        String city = null, state = null, country = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.sql.Timestamp filledon = null;

        try {
            con = new dbConnection().getConnection();
            city = userForm.getCity();
            state = userForm.getState();
            country = userForm.getCountry();
            filledon = new java.sql.Timestamp(new java.util.Date().getTime());
            if (city.equals("Other")) {
                userForm.setCity(userForm.getCityOther());

                pstmt1 = con.prepareStatement("insert into GEN_cities(CITY_NAME) values(?)");
                pstmt1.setString(1, userForm.getCityOther());
                try {
                    pstmt1.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (state.equals("Other")) {
                userForm.setState(userForm.getStateOther());

                pstmt2 = con.prepareStatement("insert into GEN_states(STATE_NAME) values(?)");
                pstmt2.setString(1, userForm.getStateOther());
                try {
                    pstmt2.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (country.equals("Other")) {
                userForm.setCountry(userForm.getCountryOther());

                pstmt3 = con.prepareStatement("insert into GEN_countries(COUNTRY_NAME) values(?)");
                pstmt3.setString(1, userForm.getCountryOther());
                try {
                    pstmt3.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }




            pstmt4 = con.prepareStatement("select CUSTOMER_NAME from CUSTOMER_MASTER where CUSTOMER_NAME=?");
            pstmt4.setString(1, userForm.getName());
            rs = pstmt4.executeQuery();

            String sql = "INSERT INTO CUSTOMER_MASTER(CUST_ID,CUSTOMER_NAME, ADDRESS, CITY, "
                    + "STATE, PIN_CODE, COUNTRY, CONTACT_PERSON, CONTACT_NO, EMAIL, CUST_TYPE_ID, FILLED_ON, FILLED_BY,CUST_STATUS)"
                    + "VALUES(?,?,?,?,?,?,?,?, ?,?,?,?,?,?)";


            if (!rs.next()) {

                stmt = con.createStatement();

                rs = stmt.executeQuery("select max(CUST_ID) from CUSTOMER_MASTER");
                if (rs.next()) {
                    custId = rs.getInt(1);
                }

                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, custId + 1);
                pstmt.setString(2, userForm.getName());
                pstmt.setString(3, userForm.getAddress1());
                pstmt.setString(4, userForm.getCity());
                pstmt.setString(5, userForm.getState());
                pstmt.setString(6, userForm.getPincode());
                pstmt.setString(7, userForm.getCountry());
                pstmt.setString(8, userForm.getFirstName());
                pstmt.setString(9, userForm.getMobile());
                pstmt.setString(10, userForm.getEmail());
                pstmt.setString(11, userForm.getUserTypeId());
                pstmt.setTimestamp(12, filledon);
                pstmt.setString(13, userid);
                pstmt.setString(14, "Y");
                pstmt.executeUpdate();
                result = "success";

            } else {
                result = "already exist";
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (pstmt1 != null) {
                    pstmt1.close();
                }
                if (pstmt2 != null) {
                    pstmt2.close();
                }
                if (pstmt3 != null) {
                    pstmt3.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String getCustNameList(UserManagementForm userForm) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        StringBuilder data = new StringBuilder("<listdata>");

        try {
            con = new dbConnection().getConnection();
            stmt = con.createStatement();

            rs = stmt.executeQuery("select CUSTOMER_NAME from CUSTOMER_MASTER where CUSTOMER_NAME like '%" + userForm.getName() + "%'");
            while (rs.next()) {
                data.append(rs.getString(1) + "|");
            }
            data.append("</listdata>");

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data.toString();
    }

    public int getCustDetAjax(UserManagementForm uf) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int result = 0;
        //ArrayList<UserManagementForm> list = null;

        try {
            con = new dbConnection().getConnection();
            stmt = con.createStatement();
            //list = new ArrayList<UserManagementForm>();
            rs = stmt.executeQuery("SELECT CUSTOMER_NAME,ADDRESS,CITY,STATE,PIN_CODE,COUNTRY,CONTACT_PERSON,CONTACT_NO,EMAIL,CUST_TYPE_ID,CUST_ID,CUST_STATUS FROM CUSTOMER_MASTER where CUSTOMER_NAME='" + uf.getName() + "'");
            if (rs.next()) {
                // UserManagementForm uf = new UserManagementForm();

                uf.setName(rs.getString("CUSTOMER_NAME"));
                uf.setAddress1(rs.getString("ADDRESS"));
                uf.setCity(rs.getString("CITY"));
                uf.setState(rs.getString("STATE"));
                uf.setPincode(rs.getString("PIN_CODE"));
                uf.setCountry(rs.getString("COUNTRY"));
                uf.setFirstName(rs.getString("CONTACT_PERSON"));
                uf.setMobile(rs.getString("CONTACT_NO"));
                uf.setEmail(rs.getString("EMAIL"));
                uf.setUserTypeId(rs.getString("CUST_TYPE_ID"));
                uf.setId(rs.getString("CUST_ID"));
                uf.setStatus(rs.getString("CUST_STATUS"));

                result = 1;

                //list.add(uf);
            } else {
                result = 0;
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String updateCustomerDetails(UserManagementForm userForm, String custname, String userid) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null, pstmt3 = null, pstmt4 = null, pstmt5 = null;
        ResultSet rs = null;
        String result = "fail";
        String city = null, state = null, country = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.sql.Timestamp filledon = null;

        try {
            con = new dbConnection().getConnection();
            city = userForm.getCity();
            state = userForm.getState();
            country = userForm.getCountry();
            filledon = new java.sql.Timestamp(new java.util.Date().getTime());
            if (city.equals("Other")) {
                userForm.setCity(userForm.getCityOther());

                pstmt1 = con.prepareStatement("insert into GEN_cities(CITY_NAME) values(?)");
                pstmt1.setString(1, userForm.getCityOther());
                try {
                    pstmt1.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (state.equals("Other")) {
                userForm.setState(userForm.getStateOther());

                pstmt2 = con.prepareStatement("insert into GEN_states(STATE_NAME) values(?)");
                pstmt2.setString(1, userForm.getStateOther());
                try {
                    pstmt2.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (country.equals("Other")) {
                userForm.setCountry(userForm.getCountryOther());

                pstmt3 = con.prepareStatement("insert into GEN_countries(COUNTRY_NAME) values(?)");
                pstmt3.setString(1, userForm.getCountryOther());
                try {
                    pstmt3.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            pstmt4 = con.prepareStatement("select CUSTOMER_NAME from CUSTOMER_MASTER where CUSTOMER_NAME=?");//already exist
            pstmt4.setString(1, userForm.getName());
            rs = pstmt4.executeQuery();


            String sql = "UPDATE CUSTOMER_MASTER SET CUSTOMER_NAME=?, ADDRESS=?, CITY=?, "
                    + "STATE=?, PIN_CODE=?, COUNTRY=?, CONTACT_PERSON=?, CONTACT_NO=?, EMAIL=?, CUST_TYPE_ID=?, FILLED_ON=?, FILLED_BY=?,CUST_STATUS=? WHERE CUST_ID= '" + userForm.getId() + "'";
            //System.out.println(sql);

            if (rs.next()) {

                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, userForm.getName());
                pstmt.setString(2, userForm.getAddress1());
                pstmt.setString(3, userForm.getCity());
                pstmt.setString(4, userForm.getState());
                pstmt.setString(5, userForm.getPincode());
                pstmt.setString(6, userForm.getCountry());
                pstmt.setString(7, userForm.getFirstName());
                pstmt.setString(8, userForm.getMobile());
                pstmt.setString(9, userForm.getEmail());
                pstmt.setString(10, userForm.getUserTypeId());
                pstmt.setTimestamp(11, filledon);
                pstmt.setString(12, userid);
                pstmt.setString(13, "Y");
                pstmt.executeUpdate();
                result = "success";

            } else {
                result = "doesnotexist";
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (pstmt1 != null) {
                    pstmt1.close();
                }
                if (pstmt2 != null) {
                    pstmt2.close();
                }
                if (pstmt3 != null) {
                    pstmt3.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String updateCustomer(UserManagementForm userForm, String userid) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String res = "fail";
        try {
            con = new dbConnection().getConnection();

            String query = "UPDATE CUSTOMER_MASTER SET CUST_STATUS=? WHERE CUST_ID= '" + userForm.getId() + "'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "N");
            pstmt.executeUpdate();
            res = "success";

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }


                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public ArrayList<UserManagementForm> getUserList(UserManagementForm UserManagementForm, String nameSrch) throws SQLException {
        ArrayList<UserManagementForm> dataList = null;
        String sql = null, Subsql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch.trim();
        Query query = null;
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();


        try {
            dataList = new ArrayList<UserManagementForm>();
            if (!SearchQuery.equals("")) {
                Subsql = " and (uc.USER_NAME like '%" + SearchQuery + "%' OR uc.USER_ID like '%" + SearchQuery + "%')  ";
            }
            sql = "select uc.USER_ID,uc.USER_NAME, uc.ADDRESS1,uc.STATUS,sp.USER_TYPE,uc.MOBILE from UM_DMS_USER_CHECK uc, UM_spas101 sp where uc.USER_TYPE_ID=sp.USER_TYPE_ID and sp.RoleCategory='EXPORT' and sp.RoleSource='DMS' "
                    + "" + Subsql + " ";
            // hql = "select uc.userId,uc.userName, uc.address1,uc.status,tr.roleDesc from DmsUserCheck uc,TmsRolesMaster tr where uc.userTypeId=tr.roleId "+ Subsql;

            query = s.createSQLQuery(sql).addScalar("USER_ID", new StringType()).addScalar("USER_NAME", new StringType()).addScalar("ADDRESS1", new StringType()).addScalar("STATUS", new StringType()).addScalar("USER_TYPE", new StringType()).addScalar("MOBILE", new StringType());
            List list = query.list();

            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                UserManagementForm = new UserManagementForm();
                UserManagementForm.setId(obj[0] == null ? "" : obj[0].toString().trim());
                UserManagementForm.setName(obj[1] == null ? "" : obj[1].toString().trim());
                UserManagementForm.setAddress1(obj[2] == null ? "" : obj[2].toString().trim());
                UserManagementForm.setStatus(obj[3] == null ? "" : obj[3].toString().trim());
                UserManagementForm.setUserTypeDesc(obj[4] == null ? "" : obj[4].toString().trim());
                UserManagementForm.setMobile(obj[5] == null ? "" : obj[5].toString().trim());
                dataList.add(UserManagementForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null) {
                    s.close();
                    s = null;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public String UpdateUserState(String status, String type, String id) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular User Type.
         */
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        String result = "";
        Query query = null;
        String newStatus = "";
        try {
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("A")) {
                    newStatus = "I";
                } else {
                    newStatus = "A";
                }
                query = s.createQuery("UPDATE DmsUserCheck SET status=? WHERE userId=? ");
                query.setString(0, newStatus);
                query.setString(1, id);
                query.executeUpdate();
                t.commit();
                result = "Success@@Status has been updated Successfully.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update User Type, Please contact system Administrator.";
        } finally {
            try {
                if (s != null) {
                    s.close();
                    s = null;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String getUserListByID(UserManagementForm userForm, String user_id) {
//        Connection con = null; 
        //Statement stmt = null;
        //ResultSet rs = null;
        String result = "fail";
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Query query = null;

        try {
//            conn = new dbConnection().getConnection();
//            stmt = conn.createStatement();

            String hql = "select userId,password,userTypeId,userName,address1,mobile,email,status,dealercode  from DmsUserCheck where userId='" + user_id + "'";
            query = s.createQuery(hql);
            List list = query.list();
            if (query != null && list.size() > 0) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    userForm.setUserId(obj[0] == null ? "" : obj[0].toString().trim());
                    userForm.setPassword(new EncryptionDecryption().decrypt(obj[1] == null ? "" : obj[1].toString().trim()));
                    userForm.setUserTypeId(obj[2] == null ? "" : obj[2].toString().trim());
                    userForm.setFirstName(obj[3] == null ? "" : obj[3].toString().trim());
                    //userForm.setDesignation(obj[4] == null ? "" : obj[4].toString().trim());
                    //userForm.setCompanyName(rs.getString("COMPANY_NAME"));
                    userForm.setAddress1(obj[4] == null ? "" : obj[4].toString().trim());
//                userForm.setAddress2(rs.getString("ADDRESS2"));
//                userForm.setCity(rs.getString("city"));
//                userForm.setState(rs.getString("STATE"));
//                userForm.setCountry(rs.getString("COUNTRY"));
//                userForm.setPincode(rs.getString("PINCODE"));
//                userForm.setPhoneNo(rs.getString("PHONE"));
                    userForm.setMobile(obj[5] == null ? "" : obj[5].toString().trim());
                    userForm.setEmail(obj[6] == null ? "" : obj[6].toString().trim());
                    // userForm.setRegionId(obj[8] == null ? "" : obj[8].toString().trim());
                    userForm.setStatus(obj[7] == null ? "" : obj[7].toString().trim());
                    userForm.setDealerCode(obj[8] == null ? "" : obj[8].toString().trim());
                    getUserDataListNew(userForm);
                    result = "success";
                }
            } else {
                result = "notexists";
            }



        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (s != null) {
                    s.close();
                    s = null;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String deleteUserType(String userTypeId, String name) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String results = "";
        try {
            con = new dbConnection().getConnection();

            pstmt = con.prepareStatement("select USER_ID from UM_user_check where USER_TYPE_ID=?");
            pstmt.setString(1, userTypeId);
            rs = pstmt.executeQuery();

            if (!rs.next()) {

                pstmt = con.prepareStatement("delete from UM_spas103 WHERE USER_TYPE_ID=?");
                pstmt.setString(1, userTypeId);
                pstmt.executeUpdate();

                pstmt = con.prepareStatement("delete from UM_spas101 WHERE USER_TYPE_ID=?");
                pstmt.setString(1, userTypeId);
                pstmt.executeUpdate();
                conn.commit();
                results = "Success@@User Type '" + name.toUpperCase() + "' has been Successfully Deleted.";
            } else {
                //results = "Failure@@User Type '" + name.toUpperCase() + "' cannot be Deleted.";
                results = "Failure@@User Type '" + name.toUpperCase() + "' cannot be deleted because User Exist for this User Type.";
                conn.rollback();
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }


                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    // Get User types for Asign User Role page
    public LinkedHashSet<LabelValueBean> getUserType() {

        Statement stmt = null;
        ResultSet rs = null;
        LinkedHashSet<LabelValueBean> result = new LinkedHashSet<LabelValueBean>();
        String name = "", id = "";
        String query = "select distinct USER_TYPE_ID,USER_TYPE FROM UM_spas101 order by USER_TYPE";
        try {
            LabelValueBean lv = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                name = rs.getString(2);
                id = rs.getString(1);
                lv = new LabelValueBean(name == null ? "" : name.trim(), id == null ? "" : id.trim());
                result.add(lv);
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    //getuserType by id created on 13/05/14 by Megha
    public String getuserType(int user_type_id) {
        String usertype = "";

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery("select * FROM UM_spas101 where USER_TYPE_ID=" + user_type_id);
            if (rs.next()) {
                usertype = rs.getString("USER_TYPE");
            }
            rs.close();
            rs = null;
            stmt.close();
            stmt = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return usertype;
    }

    //fillFunctionality by usertype id created on 13/05/14 by Megha
    public Vector fillFunctionality(int user_type_id, Connection conn) {
        Vector funcVector = new Vector();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement("SELECT FUNC_ID FROM UM_spas103 WHERE USER_TYPE_ID=" + user_type_id + " ");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                funcVector.add(rs.getString(1));


            }
            rs.close();
            rs = null;
            pstmt.close();
            pstmt = null;

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;

                }

                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;

                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return funcVector;
    }

    //getUserFunctionalties_name created on 13/05/14 by Megha
    public Vector getUserFunctionalties_name(Connection conn) {
        Vector userFun = new Vector();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //$1

        try {
            String query = "Select FUNC_ID,FUNC_DESC from UM_spas102";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            int func_id;
            String func_desc = "";

            if (rs.next()) {
                do {
                    func_id = rs.getInt(1);
                    func_desc = rs.getString(2);

                    if (func_id != 0) {
                        //$2
                        userFun.add(String.valueOf(func_id));
                        userFun.add(String.valueOf(func_desc));

                    }
                } while (rs.next());

            }
            rs.close();
            rs = null;
            pstmt.close();
            pstmt = null;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;

                }
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;

                }
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        return userFun;

    }

    //updatUserFunctionalities created on 13/05/14 by Megha
    public int updatUserFunctionalities(int type_id, String roleArr[], Connection conn) {
        int forward = 0;
        int arrElement = 0;
        PreparedStatement pstmt1 = null, pstmt = null;

        try {
            pstmt = conn.prepareStatement("delete from UM_spas103 where USER_TYPE_ID =?");
            if (type_id != 0) {
                // Deleting all the functionalities assigned to the user.
                pstmt.setInt(1, type_id);
                pstmt.executeUpdate();
                forward = 1;
            }
            if (roleArr.length > 0) {
                for (int i = 1; i < roleArr.length; i++) {
                    pstmt1 = conn.prepareStatement("insert into UM_spas103 values(?,?)");
                    arrElement = Integer.parseInt(roleArr[i]);
                    pstmt1.setInt(1, type_id);
                    pstmt1.setInt(2, arrElement);

                    pstmt1.executeUpdate();
                    pstmt1.close();
                    pstmt1 = null;
                    conn.commit();
                    forward = 1;
                }

            }
            pstmt.close();
            pstmt = null;
            conn.commit();

        } catch (Exception ae) {
            ae.printStackTrace();
            forward = 2;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;
                }
                if (pstmt1 != null) {
                    pstmt1.close();
                    pstmt1 = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return forward;
    }

    //subFuncExists add on 13/05/14 by Megha
    public boolean subFuncExists(int func) {
        Statement stmt = null;
        ResultSet rs = null;

        boolean checkSubfunc = false;

        try {
            stmt = conn.createStatement();

            //$1
            rs = stmt.executeQuery("select * from UM_spas104 where (MAIN_FUNC_ID = " + func + ") and (SUB_FUNC_ID <> 0) ");

            if (rs.next()) {
                //$2
                checkSubfunc = true;

            }
            rs.close();
            rs = null;
            stmt.close();
            stmt = null;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;

                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;

                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return checkSubfunc;

    }

    //subFunctionalities add on 13/05/14 by Megha
    public void subFunctionalities(int func, JspWriter ps, String frontSpaces, Vector<String> userFun, String cbName, Vector<String> funcVec) {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            int grp_seq_no = 0;
            int tempGrpSeqNo = 0;
            int subFunc = 0;
            int subCbCounter = 0;				// Sub checkbox counter
            int subRdCounter = 0;
            String tempS = "";

            stmt = conn.createStatement();

            //$1
            rs = stmt.executeQuery("select UM_spas104.SUB_FUNC_ID, UM_spas102.FUNC_DESC,UM_spas104.GRP_SEQ_NO from UM_spas104 , UM_spas102  where (UM_spas104.MAIN_FUNC_ID=" + func + ") and (UM_spas104.SUB_FUNC_ID<>0) and (UM_spas102.FUNC_ID = UM_spas104.SUB_FUNC_ID ) order by UM_spas104.GRP_SEQ_NO");

            if (rs.next()) {
                grp_seq_no = 0;
                tempGrpSeqNo = 0;
                subFunc = 0;
                subCbCounter = 0;				// Sub checkbox counter

                subRdCounter = 0;				// Sub radio counter

                String radioName = "";
                String checkBoxName = "";

                do {
                    subFunc = rs.getInt(1);			// Sub Functonality

                    grp_seq_no = rs.getInt(3);		// Group Sequence Number
                    tempS = String.valueOf(subFunc);
                    // Checking If funtiona has already been printed

                    if (!funcVec.contains(tempS)) {
                        funcVec.add(tempS);
                        //$2
                        //If grp_seq_no is 0 then put checkbox otherwise radio button

                        if (grp_seq_no == 0) {
                            checkBoxName = cbName + "_" + subCbCounter;
                            subCbCounter++;
                            ps.println("<tr><td class=headingSpas bgcolor='#FFFFFF' align='left'>");
                            //To select the checkbox If user has the above functionality

                            if (userFun.contains(tempS)) {
                                ps.println(frontSpaces + "<INPUT TYPE='checkbox' align='left' class='headingSpas'  NAME='" + checkBoxName + "' value='" + subFunc + "' onclick=validateCheck('" + checkBoxName + "') checked>");

                            } else {
                                ps.println(frontSpaces + "<INPUT TYPE='checkbox' align='left' class='headingSpas' NAME='" + checkBoxName + "' value='" + subFunc + "' onclick=validateCheck('" + checkBoxName + "')>");

                            }

                            ps.println(rs.getString(2));
                            ps.println("</td></tr>");

                        } else {

                            ps.println("<tr><td class=headingSpas bgcolor='#FFFFFF' align='left'>");
                            // Condition for making radio groups of functionality

                            if (tempGrpSeqNo == grp_seq_no) {

                                //To select the radio button If user has the above functionality
                                if (userFun.contains(tempS)) {
                                    ps.println(frontSpaces + "<INPUT TYPE='radio' align='left' NAME='" + radioName + "' value='" + subFunc + "' onclick=validateCheck('" + radioName + "') checked >");

                                } else {
                                    ps.println(frontSpaces + "<INPUT TYPE='radio' align='left' NAME='" + radioName + "' value='" + subFunc + "' onclick=validateCheck('" + radioName + "')>");

                                }
                            } else {
                                radioName = cbName + "_rd_" + subRdCounter;
                                subRdCounter++;
                                tempGrpSeqNo = grp_seq_no;
                                //To select the radio button If user has the above functionality

                                if (userFun.contains(tempS)) {
                                    ps.println(frontSpaces + "<INPUT TYPE='radio' align='left' NAME='" + radioName + "' value='" + subFunc + "' onclick=validateCheck('" + radioName + "') checked >");

                                } else {
                                    ps.println(frontSpaces + "<INPUT TYPE='radio' align='left' NAME='" + radioName + "' value='" + subFunc + "' onclick=validateCheck('" + radioName + "')>");

                                }
                            }
                            ps.println(rs.getString(2));
                            ps.println("</td></tr>");

                        }
                    }
                    //$3
                    boolean checkSubfunc = subFuncExists(subFunc);

                    if (checkSubfunc) {
                        String addSpaces = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                        addSpaces = frontSpaces + addSpaces;
                        //$4
                        subFunctionalities(subFunc, ps, addSpaces, userFun, checkBoxName, funcVec);

                    }
                } while (rs.next());

            }
            rs.close();
            rs = null;
            stmt.close();
            stmt = null;

        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;

                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;

                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    public String changePassword(String user_id, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = "";
        try {
            session.beginTransaction();

            Query update = session.createQuery("UPDATE DmsUserCheck SET password=:Password,updatedon=:UpdatedOn where userId=:userID");
            update.setParameter("Password", EncryptionDecryption.encrypt(password));
            update.setParameter("UpdatedOn", new java.util.Date());
            update.setParameter("userID", user_id);
            update.executeUpdate();
            session.getTransaction().commit();
            result = "Password has been changed successfully.";
        } catch (Exception e) {
            result = "Password could not be changed. Please contact administrator.";
            e.printStackTrace();
        } finally
        {
            try {
                if(session!=null)
                {
                session.close();
                session=null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String checkPasswordExist(String user_id, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String result = "";
        try {
            session.beginTransaction();

            Query qry = session.createSQLQuery("select userId from DmsUserCheck where password=:Password");
            qry.setParameter("Password", password);
            List<String> list = qry.list();
            if (list != null && list.size() > 0) {
                result = "Exist";
            } else {
                result = "NotExist";
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally
        {
            try {
                if(session!=null)
                {
                session.close();
                session=null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<UserManagementForm> getDealerList(UserManagementForm UserManagementForm, String nameSrch) throws SQLException {
        ArrayList<UserManagementForm> dataList = null;
        String hql = null, Subhql = "";
        String SearchQuery = nameSrch == null ? "" : nameSrch.trim();
        Query query = null;
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();


        try {
            dataList = new ArrayList<UserManagementForm>();
            if (!SearchQuery.equals("")) {
                Subhql = " where (uc.dealerCode like '%" + SearchQuery + "%' OR uc.dealerName like '%" + SearchQuery + "%')  ";
            }
            hql = "select uc.dealerCode, uc.dealerName, uc.locationCode, uc.location, uc.address, uc.status from UmDmsDealerMaster uc "
                    + "" + Subhql + " ";

            //query = s.createSQLQuery(hql).addScalar("USER_ID", new StringType()).addScalar("USER_NAME", new StringType()).addScalar("ADDRESS1", new StringType()).addScalar("STATUS", new StringType()).addScalar("USER_TYPE", new StringType());
            query = s.createQuery(hql);
            List list = query.list();

            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                UserManagementForm = new UserManagementForm();
                UserManagementForm.setDealerCode(obj[0] == null ? "" : obj[0].toString().trim());
                UserManagementForm.setDealerName(obj[1] == null ? "" : obj[1].toString().trim());
                UserManagementForm.setLocationCode(obj[2] == null ? "" : obj[2].toString().trim());
                UserManagementForm.setLocation(obj[3] == null ? "" : obj[3].toString().trim());
                UserManagementForm.setAddress1(obj[4] == null ? "" : obj[4].toString().trim());
                UserManagementForm.setStatus(obj[5] == null ? "" : obj[5].toString().trim());
                dataList.add(UserManagementForm);
//                UserManagementForm.setId(obj[0] == null ? "" : obj[0].toString().trim());
//                UserManagementForm.setName(obj[1] == null ? "" : obj[1].toString().trim());
//                UserManagementForm.setAddress1(obj[2] == null ? "" : obj[2].toString().trim());
//                UserManagementForm.setStatus(obj[3] == null ? "" : obj[3].toString().trim());
//                UserManagementForm.setUserTypeDesc(obj[4] == null ? "" : obj[4].toString().trim());
//                dataList.add(UserManagementForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null) {
                    s.close();
                    s = null;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public String getDealerListByDealerCode(UserManagementForm userForm, String dealerCode) {
        String result = "fail";
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Query query = null;

        try {

            String hql = "select dealerCode,dealerName,locationCode,location,address,contactNo,status,disttName,stateName,tinNo,countryCode from UmDmsDealerMaster where dealerCode='" + dealerCode + "'";
            query = s.createQuery(hql);
            List list = query.list();
            if (query != null && list.size() > 0) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    userForm.setDealerCode(obj[0] == null ? "" : obj[0].toString().trim());
                    //userForm.setPassword(new EncryptionDecryption().decrypt(obj[1] == null ? "" : obj[1].toString().trim()));
                    userForm.setDealerName(obj[1] == null ? "" : obj[1].toString().trim());
                    userForm.setLocationCode(obj[2] == null ? "" : obj[2].toString().trim());
                    userForm.setLocation(obj[3] == null ? "" : obj[3].toString().trim());
                    userForm.setAddress1(obj[4] == null ? "" : obj[4].toString().trim());
                    userForm.setMobile(obj[5] == null ? "" : obj[5].toString().trim());
                    userForm.setStatus(obj[6] == null ? "" : obj[6].toString().trim());
                    userForm.setDistrict(obj[7] == null ? "" : obj[7].toString().trim());
                    userForm.setState(obj[8] == null ? "" : obj[8].toString().trim());
                    userForm.setTinNo(obj[9] == null ? "" : obj[9].toString().trim());
                    userForm.setCountry(obj[10] == null ? "" : obj[10].toString().trim());
                    getUserDataList(userForm);
                    result = "success";
                }
            } else {
                result = "notexists";
            }



        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (s != null) {
                    s.close();
                    s = null;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String updateDealerDetails(UserManagementForm userForm, String userId) {
        String result = "fail";
        java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Query query = null, query1 = null;
        try {

            query1 = s.createQuery("select dealerCode from UmDmsDealerMaster where dealerCode='" + userForm.getDealerCode() + "'");
            List list = query1.list();
            Iterator itr = list.iterator();
            if (itr.hasNext()) {

                query = s.createQuery("UPDATE UmDmsDealerMaster SET dealerName=?,locationCode=?,location=?,address=?,contactNo=?,disttName=?, stateName=?,tinNo=?,status=?,countryCode=?,lastUpdatedOn=?,lastUpdatedBy=? WHERE dealerCode=? ");
                //query.setString(0, new EncryptionDecryption().encrypt(userForm.getPassword()));
                query.setString(0, userForm.getDealerName());
                query.setString(1, userForm.getLocationCode());
                query.setString(2, userForm.getLocation());
                query.setString(3, userForm.getAddress1());
                query.setString(4, userForm.getMobile());
                query.setString(5, userForm.getDistrict());
                query.setString(6, userForm.getState());
                query.setString(7, userForm.getTinNo());
                query.setString(8, userForm.getStatus());
                query.setString(9, userForm.getCountry());
                query.setTimestamp(10, todaysDate);
                query.setString(11, userId);
                query.setString(12, userForm.getDealerCode());
                query.executeUpdate();
                t.commit();
                
                result = "success";
            } else {
                result = "notexists";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null) {
                    s.close();
                    s = null;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String UpdateDealerState(String status, String type, String dealerCode) throws SQLException {
        /*
         * this method is used to update status
         * corrosponding to particular User Type.
         */
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        String result = "";
        Query query = null;
        String newStatus = "";
        try {
            if (!type.equalsIgnoreCase("edit")) {
                if (status.equals("A")) {
                    newStatus = "I";
                } else {
                    newStatus = "A";
                }
                query = s.createQuery("UPDATE UmDmsDealerMaster SET status=? WHERE dealerCode=? ");
                query.setString(0, newStatus);
                query.setString(1, dealerCode);
                query.executeUpdate();
                t.commit();
                result = "Success@@Status has been updated Successfully.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure@@Unable to update User Type, Please contact system Administrator.";
        } finally {
            try {
                if (s != null) {
                    s.close();
                    s = null;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String insertDealerDetails(UserManagementForm userForm, String user_id) {
        EncryptionDecryption passEncry = null;
        PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null, pstmt3 = null, pstmt4 = null, pstmt5 = null;
        String result = "fail";

        java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Query query = null, query1 = null;

        try {

            query1 = s.createQuery("select dealerCode from UmDmsDealerMaster where dealerCode=?");
            query1.setString(0, userForm.getDealerCode());
            List list = query1.list();
            Iterator i = list.iterator();
            if (!i.hasNext()) {//Long.parseLong(
                UmDmsDealerMaster duc = new UmDmsDealerMaster(userForm.getDealerCode(), userForm.getDealerName(), userForm.getLocationCode(), userForm.getLocation(), userForm.getAddress1(), Long.parseLong("0"), userForm.getDistrict(), Long.parseLong("0"), userForm.getState(), userForm.getMobile(), "A", userForm.getTinNo(), userForm.getCountry(), "EXPORT", "Authorized Dealer", user_id, todaysDate);
                s.save(duc);
                t.commit();
                result = "success";
            } else {
                result = "alreadyexist";
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        } finally {
            try {
                if (s != null) {
                    s.close();
                    s = null;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       // System.out.println("" + result);
        return result;
    }
}
