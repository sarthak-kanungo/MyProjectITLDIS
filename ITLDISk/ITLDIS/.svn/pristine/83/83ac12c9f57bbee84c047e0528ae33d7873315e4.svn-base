package com.EAMG.common;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import mnal.utility.mnal_Encryption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;

import EAMG.utility.EAMG_Encryption;

/**
 *
 * @author amandeep.juneja
 */
public class EAMG_commonDAO {

    Connection conn = null;
    HttpSession session = null;
    private Logger logger = Logger.getLogger(this.getClass());

    public EAMG_commonDAO(Connection conn) {
        this.conn = conn;
    }

    public EAMG_commonDAO() {
    }

    public boolean fillUserTypeFunctionality(int typeID, Vector<String> funcVector) {
        boolean retB = false;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            // int user_type = getuserTypeId ( user_id );
            String tempS;
            pstmt = conn.prepareStatement("SELECT FUNCTIONID FROM EMAN1021(NOLOCK) WHERE USERTYPEID=?");
            pstmt.setInt(1, typeID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tempS = String.valueOf(rs.getInt(1));
                if (!funcVector.contains(tempS)) {
                    funcVector.add(tempS);
                }
            }
            rs.close();

            rs = null;
            pstmt.close();
            pstmt = null;
            retB = true;
        } catch (Exception e) {
            logger.error(WebConstants.logException, e);
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
                logger.error(WebConstants.fLogException, e);
            }
        }
        return retB;

    }

    /**
     *
     * @param user_id
     * @param funcVector
     * @return
     */
    public boolean fillUserIdFunctionality(String user_id, Vector<String> funcVector) {
        boolean retB = false;
        ResultSet rs = null;

        PreparedStatement pstmt = null;
        try {
            //$1
            // int user_type = this.getuserTypeId(user_id.toLowerCase ());
            //$2
            String temp;
            pstmt = conn.prepareStatement("SELECT FUNCTIONID FROM EMAN1022(NOLOCK) WHERE USERID=?");
            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //$3
                temp = String.valueOf(rs.getInt(1));
                if (!funcVector.contains(temp)) {
                    funcVector.add(temp);
                }
            }
            rs.close();
            rs = null;
            pstmt.close();
            pstmt = null;
            retB = true;
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error(WebConstants.logException, e);
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
                //e.printStackTrace();
                logger.error(WebConstants.fLogException, e);
            }
        }
        return retB;

    }

    public String getuserType(int user_type_id) {
        String usertype = "";

        //Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //stmt = conn.createStatement();

            //rs = stmt.executeQuery("select * FROM ecat101 where USER_TYPE_ID=" + user_type_id);
        	String query = ("select * FROM ecat101(NOLOCK) where USER_TYPE_ID=" + user_type_id);
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery();
            if (rs.next()) {
                usertype = rs.getString("USER_TYPE");
            }
            rs.close();
            rs = null;
            stmt.close();
            stmt = null;
        } catch (Exception e) {

            logger.error(WebConstants.logException, e);
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
                // e.printStackTrace();
                logger.error(WebConstants.fLogException, e);
            }
        }
        return usertype;
    }

    /*********************************************************************************
     * Method to get user name and designation details.
     * <br><br><b>Steps:</b>
     * <br>1. Get user name and designation details corresponding to user id from USER_MASTER.
     * <br>2. Store user name and designation details in Vector.
     * <br><br><b>Author:</b> Pramod Vishwakarma
     * @param user_id User ID
     * @return Vector contaning user name and designation details
     *********************************************************************************/
    public EAMG_commonForm getUserName(String user_id) {
        EAMG_commonForm user = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            // password=encryptStr(password);
            //$1
            pstmt = conn.prepareStatement("SELECT * FROM USER_MASTER(NOLOCK) WHERE USERID=?");
            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new EAMG_commonForm();
                user.setUserId(user_id);
                user.setFirstName(rs.getString("FIRST_NAME"));
                user.setLastName(rs.getString("LAST_NAME"));
            }
            rs.close();
            rs = null;
            pstmt.close();
            pstmt = null;
            //$2

        } catch (SQLException ae) {
            logger.error(WebConstants.logException, ae);

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
                //e.printStackTrace();
                logger.error(WebConstants.fLogException, e);
            }
        }
        return user;
    }

    public String getuserTypeId(String userId) {
        String usertype = "";

       // Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //stmt = conn.createStatement();

            //rs = stmt.executeQuery("select * from USER_CHECK(NOLOCK) where USER_ID='" + userId + "'");
        	String query = ("select * from USER_CHECK(NOLOCK) where USER_ID='" + userId + "'");
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery();
            if (rs.next()) {
                usertype = rs.getString("USER_TYPE");
            }
            rs.close();
            rs = null;
            stmt.close();
            stmt = null;
        } catch (Exception e) {
            logger.error(WebConstants.logException, e);
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

                logger.error(WebConstants.fLogException, e);
            }
        }
        return usertype;
    }

    public String getPassword(String userid) {
        //Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String userPassword = "";
        try {
           // stmt = conn.createStatement();
           // rs = stmt.executeQuery("select * from USER_MASTER(NOLOCK) where userid='" + userid + "'");
            String query = ("select * from USER_MASTER(NOLOCK) where userid='" + userid + "'");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
        	if (rs.next()) {
                userPassword = rs.getString("PASSWORD");

            }
            rs.close();
            rs = null;
            stmt.close();
            stmt = null;
        } catch (SQLException e) {

            logger.error(WebConstants.logException, e);

        } catch (Exception e) {

            logger.error(WebConstants.logException, e);

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
                //e.printStackTrace();
                logger.error(WebConstants.fLogException, e);
            }
        }
        return userPassword;
    }

    public String checkUser(String userid, String password) {
        String result = "failure";
        String dbpassword = "";
        String dbuserStatus = "";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            String passwordE = encryptStr(password);
            pstmt = conn.prepareStatement("SELECT USERID,ISACTIVE,PASSWORD FROM USER_MASTER(NOLOCK) WHERE USERID=? AND PASSWORD=?");
            pstmt.setString(1, userid);
            pstmt.setString(2, passwordE);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dbuserStatus = rs.getString(2);
                dbpassword = rs.getString(3);
                if (dbuserStatus.equalsIgnoreCase("BLOCKED")) {
                    result = "blocked";
                } else if (dbpassword.equals(passwordE) && dbuserStatus.equalsIgnoreCase("LIVE")) {
                    result = "success";
                }
            }
            rs.close();
            rs = null;
            pstmt.close();
            pstmt = null;
        } catch (SQLException e) {
            //ae.printStackTrace();
            logger.error(WebConstants.logException, e);

        } catch (Exception e) {
            //ae.printStackTrace();
            logger.error(WebConstants.logException, e);

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
                //e.printStackTrace();
                logger.error(WebConstants.fLogException, e);
            }
        }
        return result;
    }

    public int loginTrakerByUserId(String userId, String remoteIPAddr, String loginD) throws Exception, SQLException {
        int isCreated = 0;
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("insert into LOGIN_TRACK(USER_ID,LOGINDATE,ADDRESS)values (?,?,?)");
            pstmt.setString(1, userId);
            pstmt.setString(2, loginD);
            //pstmt.setString(3, loginD);
            pstmt.setString(3, remoteIPAddr);
            pstmt.executeUpdate();
            pstmt.close();
            isCreated = 1;
            conn.commit();
        } catch (Exception ex) {
            isCreated = 2;
            //conn.rollback ();
            logger.error(WebConstants.logException, ex);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                logger.error(WebConstants.fLogException, e);
            }
        }
        return isCreated;
    }

    public int updateloginTrakerByUser(String userId, String loginD) throws Exception, SQLException {
        int isCreated = 0;

        userId = "'" + userId + "'";
        PreparedStatement ps = null;
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date todayDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(todayDate.getTime());
        String logoutD = sdf.format(sqlDate);

        String query = "update LOGIN_TRACK set LOGOUTDATE =? WHERE USER_ID=" + userId + " AND LOGINDATE='" + loginD + "'";
        try {

            ps = conn.prepareStatement(query);
            ps.setString(1, logoutD);
            ps.executeUpdate();
            ps.close();
            isCreated = 1;
            conn.commit();
        } catch (Exception ex) {
            isCreated = 2;
            logger.error(WebConstants.logException, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (Exception e) {
                logger.error(WebConstants.fLogException, e);
            }
        }
        return isCreated;
    }

    public String decryptStr(String str) {
        String decr_str = "";

        if (str != null) {
            try {
                byte[] passBytes = str.getBytes();
                decr_str = EAMG_Encryption.decrypt(passBytes);
            } catch (Exception e) {

                logger.error(WebConstants.logException, e);
            }
        }
        return decr_str;
    }

    public String encryptStr(String str) {
        String encr_str = "";

        if (str != null) {
            try {

                encr_str = EAMG_Encryption.encrypt(str);
            } catch (Exception e) {
                //ae.printStackTrace();
                logger.error(WebConstants.logException, e);
            }
        }
        return encr_str;
    }

    /*********************************************************************************
     * Method to get Today SQL Date.
     * <br><br><b>Steps:</b>
     * <br>1. Get current date.
     * <br><br><b>Author:</b> Avinash Pandey
     * @return Date containing today sql date
     *********************************************************************************/
    public String get_HostAddress() {
        String remoteIPAddr = "";
        try {
            remoteIPAddr = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            logger.error(WebConstants.logException, e);
        }
        return remoteIPAddr;

    }

    /**************************************************************************************
     * Method to fetch all LangName available in database
     * <br><br><b>Steps:</b>
     * 1. fetch all rows of EMAN_LANG_1 table in resultset.
     * 2. Create Platform object for each row and populate that langform object
     * 3. Add each created langform object in ArrayList
     * 4. return langNameList
     * 5. <b>Author</b> - Avinash Pandey
     */
    public String getMobileNo(Connection conn, String mobile_no) {
        String forward = "";
        try {
            //Statement stmt = conn.createStatement();
        	PreparedStatement stmt = null;
        	ResultSet rs = null;
           // ResultSet rs = stmt.executeQuery("select mobile from user_check(NOLOCK) where mobile='"+ mobile_no +"'");
        	String query = ("select mobile from user_check(NOLOCK) where mobile='"+ mobile_no +"'");
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery();
            
        	if (!rs.next()) {
                forward = "valid";
            } else {

                forward = "invalid";
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return forward;
    }

    public String getMobileNo(Connection conn, String user_id, String mobile) {
        String forward = "";

        try {
            //Statement stmt = conn.createStatement();
        	PreparedStatement stmt = null;
        	ResultSet rs = null;
            //ResultSet rs = stmt.executeQuery("select mobile from USER_CHECK(NOLOCK) where mobile='" + mobile + "' and user_id<>'" + user_id + "' ");
            String query = ("select mobile from USER_CHECK(NOLOCK) where mobile='" + mobile + "' and user_id<>'" + user_id + "' ");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
        	if (!rs.next()) {
                forward = "valid";
            } else {
                forward = "invalid";
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return forward;
    }

    public String getParentId(String user_id, Connection conn) {
        String parent_id = "";
        try {
            PreparedStatement pstmt = conn.prepareStatement("Select parent_user_id from user_hierarchy(NOLOCK) where child_user_id=?");
            pstmt.setString(1, user_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                parent_id = rs.getString(1);
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parent_id;

    }

    /*********************************************************************************
     * Method to get user details.
     * <br><br><b>Steps:</b>
     * <br>1. Get user details corresponding to user id from USER_MASTER.
     * <br>2. Store user details in Vector.
     * <br><br><b>Author:</b> Pramod Vishwakarma
     * @param user_id User ID
     * @return Vector contaning user details
     *********************************************************************************/
    public Vector user_details(String user_id) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Vector user_vec = new Vector();
        String password = "";
        String confirm_password = "";
        String email = "";
        String email1 = "";
        String first_name = "";
        String address = "";
        String address1 = "";
        String pincode = "";
        String country = "";
        String state = "";
        String city = "";
        String phone_no = "";
        String mobile = "";
        String contact = "";
        String status = "";


        try {
            //$1
            PreparedStatement pstmt = conn.prepareStatement("select * from User_Check(NOLOCK) where USER_ID=?");
            pstmt.setString(1, user_id); //0
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                password = rs.getString("PASSWORD");//0
                if (password == null || password.equals("")) {
                    password = "--";
                }
                //$2
                user_vec.add(password);
                confirm_password = password;
                if (confirm_password == null || confirm_password.equals("")) {
                    confirm_password = "--";
                }
                user_vec.add(confirm_password);//1


                first_name = rs.getString("NAME");//2
                if (first_name == null || first_name.equals("")) {
                    first_name = "--";
                }
                user_vec.add(first_name);

                address = rs.getString("ADDRESS1");//3
                if (address == null || address.equals("")) {
                    address = "--";
                }
                user_vec.add(address);
                address1 = rs.getString("ADDRESS2");//4
                if (address1 == null || address1.equals("")) {
                    address1 = "--";
                }
                user_vec.add(address1);

                city = rs.getString("CITY");//5
                if (city == null || city.equals("")) {
                    city = "--";
                }
                user_vec.add(city);
                pincode = rs.getString("PINCODE");//6
                if (pincode == null || pincode.equals("0") || pincode.equals("null")) {
                    pincode = "";
                }
                user_vec.add(pincode);
                state = rs.getString("STATE");//7
                if (state == null || state.equals("")) {
                    state = "--";
                }
                user_vec.add(state);
                country = rs.getString("COUNTRY");//8
                if (country == null || country.equals("")) {
                    country = "--";
                }
                user_vec.add(country);
                phone_no = rs.getString("PHONE_NO");//9
                if (phone_no == null || phone_no.equals("0") || phone_no.equals("null")) {
                    phone_no = "";
                }
                user_vec.add(phone_no);
                mobile = rs.getString("MOBILE");//10
                if (mobile == null || mobile.equals("0") || mobile.equals("null")) {
                    mobile = "--";
                }
                user_vec.add(mobile);
                email = rs.getString("email1");//11
                if (email == null || email.equals("")) {
                    email = "--";
                }
                user_vec.add(email);
                email1 = rs.getString("email2");//12
                if (email1 == null || email1.equals("")) {
                    email1 = "--";
                }
                user_vec.add(email1);//13
                contact = rs.getString("CONTACT");//13
                if (contact== null || contact.equals("")) {
                    contact = "--";
                }
                user_vec.add(contact);//13
                status = rs.getString("STATUS");//14
                user_vec.add(status);//14


            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }




        return user_vec;
    }

    /*********************************************************************************
     * @param user_type_id
     * @param conn
     * @return String
     * Method to get user type desc corresponding to user type id
     *********************************************************************************/
    public Vector fillFunctionality(int user_type_id, Connection conn) {
        Vector funcVector = new Vector();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement("SELECT FUNC_ID FROM ecat103(NOLOCK) WHERE USER_TYPE_ID=" + user_type_id + " ");

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

    /*********************************************************************************
     * Method to get functionality id.
     * <br><br><b>Steps:</b>
     * <br>1. Get user type id corresponding to user id.
     * <br>2. Get functionality ids corresponding to user type id from LMS103.
     * <br>3. Store functionality ids in Vector.
     * <br><br><b>Author:</b> Pramod Vishwakarma
     * @param user_id User ID
     * @return Vector containing functionality ids
     * @see lms.com.dao.lms_loginDAO#getuserTypeId(java.lang.String)
     *********************************************************************************/
    public Vector fillFunctionality(String user_id) {
        Vector funcVector = new Vector();

        try {
            //$1
            String user_type = this.getuserTypeId(user_id);
            int user_type_idS = new EAMG_commonDAO().getuserTypeIdByUserType(user_type);
            //$2
            PreparedStatement pstmt = conn.prepareStatement("SELECT FUNC_ID FROM EMAN1021(NOLOCK) WHERE USER_TYPE_ID=?");
            pstmt.setInt(1, user_type_idS);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                //$3
                funcVector.add(rs.getString(1));


            }
            rs.close();
            pstmt.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
        return funcVector;


    }

    /*********************************************************************************
     * Method to get all functionality ids and descriptions.
     * <br><br><b>Steps:</b>
     * <br>1. Get all functionality ids and descriptions.
     * <br>2. Store functionality ids and descriptions in Vector.
     * <br><br><b>Author:</b> Avinash Pandey
     * @return Vector containing functionality ids and descriptions
     *********************************************************************************/
    public Vector getUserFunctionalties_name(Connection conn) {
        Vector userFun = new Vector();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //$1

        try {
            String query = "Select FUNC_ID,FUN_DESC from ecat102(NOLOCK)";
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
            //ex.printStackTrace();
            logger.error("Caught in Exception", e);

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
                // e.printStackTrace();
                logger.error("Caught in Final Exception", e);

            }
        }
        return userFun;

    }

    /*********************************************************************************
     * Method to check subfunctionalities of particular functionality id.
     * <br><br><b>Steps:</b>
     * <br>1. Check whether the subfunctionalities exist or not in SCMS104 corresponding to functionality id.
     * <br>2. If exist then return true.
     * <br><br><b>Author:</b> Avinash Pandey
     * @param func Functionality ID
     * @return boolean contaning whether subfunctionalities exist or not
     *********************************************************************************/
    public boolean subFuncExists(int func) {
        //Statement stmt = null;
    	PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean checkSubfunc = false;

        try {
            //stmt = conn.createStatement();


            //$1
            //rs = stmt.executeQuery("select * from ecat106(NOLOCK) where (MAIN_FUNC_ID = " + func + ") and (SUB_FUNC_ID <> 0) ");
        	String query = ("select * from ecat106(NOLOCK) where (MAIN_FUNC_ID = " + func + ") and (SUB_FUNC_ID <> 0) ");
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery();

            if (rs.next()) {
                //$2
                checkSubfunc = true;

            }
            rs.close();
            rs = null;
            stmt.close();
            stmt = null;


        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("Caught in Exception", e);


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
                // e.printStackTrace();
                logger.error("Caught in Final Exception", e);

            }
        }
        return checkSubfunc;

    }

    /*********************************************************************************
     * Method to create GUI for assigning functionalities to users.
     * <br><br><b>Steps:</b>
     * <br>1. Get subfunctionalities and grp seq no from SCMS104 and SCMS102 corresponding to functionality id.
     * <br>2. If grp_seq_no is 0 then put checkbox otherwise radio button.
     * <br>3. Check whether its subfunctionalities of these subfunctionalities exist or not.
     * <br>4. If exist then get subfunctionalities of this subfunctionalities and create GUI.
     * <br><br><b>Author:</b> Avinash Pandey
     * @param func Functionality ID
     * @param ps JspWriter
     * @param frontSpaces Space
     * @param userFun assigned functionalities
     * @param cbName Checkbox Name
     * @param funcVec total functionalities

     *********************************************************************************/
    public void subFunctionalities(int func, JspWriter ps, String frontSpaces, Vector<String> userFun, String cbName, Vector<String> funcVec) {
        //Statement stmt = null;
    	PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            int grp_seq_no = 0;

            int tempGrpSeqNo = 0;

            int subFunc = 0;

            int subCbCounter = 0;				// Sub checkbox counter


            int subRdCounter = 0;
            String tempS;
            //stmt = conn.createStatement();

            //$1
            //rs = stmt.executeQuery("select ecat106.SUB_FUNC_ID, ecat102.FUN_DESC,ecat106.GRP_SEQ_NO from ecat106(NOLOCK) , ecat102(NOLOCK)  where (ecat106.MAIN_FUNC_ID=" + func + ") and (ecat106.SUB_FUNC_ID<>0) and (ecat102.FUNC_ID = ecat106.SUB_FUNC_ID ) order by ecat106.GRP_SEQ_NO");
              String query = ("select ecat106.SUB_FUNC_ID, ecat102.FUN_DESC,ecat106.GRP_SEQ_NO from ecat106(NOLOCK) , ecat102(NOLOCK)  where (ecat106.MAIN_FUNC_ID=" + func + ") and (ecat106.SUB_FUNC_ID<>0) and (ecat102.FUNC_ID = ecat106.SUB_FUNC_ID ) order by ecat106.GRP_SEQ_NO");
              stmt = conn.prepareStatement(query);
              rs = stmt.executeQuery();
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
                            ps.println("<tr><td class=text bgcolor='#FFFFFF' align='left'>");
                            //To select the checkbox If user has the above functionality

                            if (userFun.contains(tempS)) {
                                ps.println(frontSpaces + "<INPUT TYPE='checkbox' align='left' class='text'  NAME='" + checkBoxName + "' value='" + subFunc + "' onclick=validateCheck('" + checkBoxName + "') checked>");

                            } else {
                                ps.println(frontSpaces + "<INPUT TYPE='checkbox' align='left' class='text' NAME='" + checkBoxName + "' value='" + subFunc + "' onclick=validateCheck('" + checkBoxName + "')>");

                            }

                            ps.println(rs.getString(2));
                            ps.println("</td></tr>");

                        } else {

                            ps.println("<tr><td class=text bgcolor='#FFFFFF' align='left'>");
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
                                tempGrpSeqNo =
                                        grp_seq_no;
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


        } catch (Exception e) {

            // e.printStackTrace();
            logger.error("Caught in Exception", e);


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
                // e.printStackTrace();
                logger.error("Caught in Final Exception", e);

            }
        }
    }

    /*********************************************************************************
     * Method to get user type desc.
     * <br><br><b>Steps:</b>
     * <br>1. Get user type corresponding to user type id from SCMS101.
     * <br><br><b>Author:</b> Pramod Vishwakarma
     * @param userid
     * @return String containing User Type
     *********************************************************************************/
    public String findEMAN1022ByUserID(String userid) {
        String func_id = "";
        //Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //stmt = conn.createStatement();
            //$1
            //rs = stmt.executeQuery("select FUNCTIONID FROM EMAN1022(NOLOCK) where USER_ID='" + userid + "' ");
        	String query = ("select FUNCTIONID FROM EMAN1022(NOLOCK) where USER_ID='" + userid + "' ");
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery();

            if (rs.next()) {
                func_id = rs.getString(1);

            }
            rs.close();
            rs = null;
            stmt.close();
            stmt = null;

        } catch (Exception e) {
            //ae.printStackTrace();
            logger.error("Caught in Exception", e);

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
                // e.printStackTrace();
                logger.error("Caught in Final Exception", e);

            }
        }
        return func_id;

    }

    /*********************************************************************************
     * Method to get all functionality ids and descriptions.
     * <br><br><b>Steps:</b>
     * <br>1. Get all functionality ids and descriptions.
     * <br>2. Store functionality ids and descriptions in Vector.
     * <br><br><b>Author:</b> Pramod Vishwakarma
     * @return Vector containing functionality ids and descriptions
     *********************************************************************************/
    public Vector getUserFunctionalties_name() {
        Vector userFun = new Vector();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //$1

        try {
            String query = "Select FUNCTIONID,FUNCTIONNAME from EMAN1011(NOLOCK)";
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
            //ex.printStackTrace();
            logger.error("Caught in Exception", e);

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
                // e.printStackTrace();
                logger.error("Caught in Final Exception", e);

            }
        }
        return userFun;

    }

    public int getuserTypeIdByUserType(String UserType) {
        int usertypeId = 0;

        //Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //stmt = conn.createStatement();

            //rs = stmt.executeQuery("select * from ecat101 where USER_TYPE='" + UserType + "'");
        	String query = ("select * from ecat101(NOLOCK) where USER_TYPE='" + UserType + "'");
        	stmt = conn.prepareStatement(query);
        	rs = stmt.executeQuery();
            if (rs.next()) {
                usertypeId = rs.getInt("USER_TYPE_ID");
            }
            rs.close();
            rs = null;
            stmt.close();
            stmt = null;
        } catch (Exception e) {
            logger.error(WebConstants.logException, e);
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

                logger.error(WebConstants.fLogException, e);
            }
        }
        return usertypeId;
    }
}
