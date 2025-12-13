/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.common.EncryptionDecryption;

import beans.UserManagementForm;
import dao.UserManagementDAO;
import dbConnection.dbConnection;

/**
 *
 * @author manish.kishore
 */
public class UserManagementAction extends DispatchAction {

    public ActionForward addUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementForm userForm = (UserManagementForm) form;
            UserManagementDAO userDAO = new UserManagementDAO(conn);
            userDAO.getUserDataListNew(userForm);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return mapping.findForward("manageUser");
    }

    public ActionForward insertUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementForm userForm = (UserManagementForm) form;
           // System.out.println("in insert action:" + userForm.getUserId());
            UserManagementDAO userDAO = new UserManagementDAO(conn);
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String result = userDAO.insertUserDetails(userForm,user_id);

            if (result.equals("success")) {
                request.setAttribute("show_message", "User \"" + userForm.getUserId() + "\" has been created successfully.");
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "UserManagement.do?option=addUser");

            } else if (result.equals("alreadyexist")) {
                request.setAttribute("show_message", "User \"" + userForm.getUserId() + "\" already exists.");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "UserManagement.do?option=addUser");
            } else {
                request.setAttribute("show_message", "User \"" + userForm.getUserId() + "\" has not been created , please contact System Admin.");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "UserManagement.do?option=addUser");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return mapping.findForward("message");
    }
    ///////////////////////////////////////***USER TYPE MASTER***////////////////////////////////////////////////

    public ActionForward initUserType(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementDAO obj = new UserManagementDAO(conn);
            UserManagementForm mf = (UserManagementForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<UserManagementForm> userTypeList = obj.getUserTypeList(mf, nameSrch);
            request.setAttribute("userTypeList", userTypeList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("manageUserType");
    }

    public ActionForward manageUserType(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            UserManagementDAO obj = new UserManagementDAO(conn);
            UserManagementForm mf = (UserManagementForm) form;
            String name = mf.getName();
            String desc = mf.getDescription();
            String type = request.getParameter("type");
            String id = mf.getId();
            String status = mf.getStatus();
            String user_id = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addUserType(desc, name, user_id);
            } else if (type.equalsIgnoreCase("delete")) {
                String typeName = request.getParameter("name");
                data_str = obj.deleteUserType(id, typeName);
            } else {
                data_str = obj.UpdateUserType(status, user_id, desc, name, type, id);
            }
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
    ///////////////////////////////////////***REGION MASTER***////////////////////////////////////////////////

    public ActionForward initRegion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String type = "";
        try {
            conn = new dbConnection().getConnection();
            UserManagementDAO obj = new UserManagementDAO(conn);
            UserManagementForm mf = (UserManagementForm) form;
            String nameSrch = request.getParameter("nameSrch");
            type = request.getParameter("type");
            ArrayList<UserManagementForm> regionList = obj.getRegionList(mf, nameSrch);
            request.setAttribute("regionList", regionList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return mapping.findForward("manageRegion");
    }

    public ActionForward manageRegion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            UserManagementDAO obj = new UserManagementDAO(conn);
            UserManagementForm mf = (UserManagementForm) form;
            String name = mf.getName();
            String desc = mf.getDescription();
            String type = request.getParameter("type");
            String id = mf.getId();
            String status = mf.getStatus();
            String user_id = (String) session.getAttribute("user_id");
            if (type.equalsIgnoreCase("add")) {
                data_str = obj.addRegion(desc, name, user_id);
            } else if (type.equalsIgnoreCase("addGB")) {
                data_str = obj.addRegion(desc, name, user_id);
                request.setAttribute("result", data_str.split("@@")[1]);
                return mapping.findForward("addRegionResult");
            } else {
                data_str = obj.UpdateRegion(status, user_id, desc, name, type, id);
            }
            PrintWriter pw = response.getWriter();

            pw.write(data_str);

            pw.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public ActionForward getUsers(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementForm userForm = (UserManagementForm) form;
            UserManagementDAO dao = new UserManagementDAO(conn);
            String result = dao.getUserList(userForm);
            PrintWriter pw = response.getWriter();
            pw.write(result);
            pw.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return null;
    }

    public ActionForward modifyUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("modifyuser");
    }

    public ActionForward getUserAjax(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementForm userForm = (UserManagementForm) form;
            UserManagementDAO dao = new UserManagementDAO(conn);
            dao.getUserByID(userForm);
            dao.getUserDataList(userForm);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }


        return mapping.findForward("getUserAjax");
    }

    public ActionForward updateUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementForm userForm = (UserManagementForm) form;
            String userId = request.getParameter("userId");
            String flag = request.getParameter("flag");
            userForm.setFlag(flag);
            HttpSession session = request.getSession();
            String user_id = (String)session.getAttribute("user_id");
            UserManagementDAO dao = new UserManagementDAO(conn);
            String result = dao.updateUserDetails(userForm, user_id);

            if (result.equals("success")) {
                request.setAttribute("show_message", "User \"" + userForm.getUserId() + "\"  has been modified successfully.");
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("modifyMoreLink", "YES");
                request.setAttribute("modifyMoreLinkURL", "UserManagement.do?option=initUserInformation");
            } else if (result.equals("notexists")) {
                request.setAttribute("show_message", "User \"" + userForm.getUserId() + "\" doesn't exist in database.");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "UserManagement.do?option=initUserInformation");
            } else {
                request.setAttribute("show_message", "User \"" + userForm.getUserId() + "\" has not been modified , please contact System Admin.");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "UserManagement.do?option=initUserInformation");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return mapping.findForward("message");
    }

    public ActionForward changeStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        //  UserManagementForm userForm = (UserManagementForm) form;

        //  UserManagementDAO dao = new UserManagementDAO();
        //dao.getAllUsers(userForm);

        return mapping.findForward("showchangestatus");
    }

    public ActionForward getStatusAjax(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        UserManagementForm userForm = (UserManagementForm) form;

        UserManagementDAO dao = new UserManagementDAO();
        String result = dao.getUserByID(userForm);

        PrintWriter pw = response.getWriter();

        String name = userForm.getFirstName();
        String presentStatus = userForm.getStatus();


        if (result.equals("notexists")) {
            pw.print("notexists");
        } else {
            name = name == null ? "&nbsp;" : name;
            presentStatus = presentStatus == null ? "&nbsp;" : presentStatus;

            pw.print(name + "@@##" + presentStatus);
        }

        return mapping.findForward(null);
    }

    public ActionForward updateUserStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        UserManagementForm userForm = (UserManagementForm) form;

        UserManagementDAO dao = new UserManagementDAO();
        String result = dao.updateUserStatus(userForm);

        if (result.equals("success")) {
            request.setAttribute("result", "User \"" + userForm.getUserId() + "\" status has been modified successfully.");

        } else if (result.equals("notexists")) {
            request.setAttribute("result", "User \"" + userForm.getUserId() + "\" doesn't exist in database.");

        } else {
            request.setAttribute("result", "User \"" + userForm.getUserId() + "\" status has not been modified , please contact System Admin.");

        }
        return mapping.findForward("message");
    }

    ///////////////////////////////////////////MANAGE CUSTOMER/////////////////////////////////////////////////////////
    public ActionForward initManageCust(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("manageCustomer");
    }

    public ActionForward addCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        UserManagementForm userForm = (UserManagementForm) form;
        UserManagementDAO userDAO = new UserManagementDAO();
        userDAO.getCustomerDataList(userForm);

        String goLink = userForm.getGoLink();
        if (goLink != null) {
            return mapping.findForward("addCustOpenWindow");
        } else {
            return mapping.findForward("addCust");
        }
    }

    public ActionForward insertcustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        UserManagementForm userForm = (UserManagementForm) form;
        UserManagementDAO userDAO = new UserManagementDAO();
        String userID = (String) session.getAttribute("user_id");
        String result = userDAO.insertCustomerDetails(userForm, userID);

        if (result.equals("success")) {
            request.setAttribute("show_message", "Customer  \"" + userForm.getName() + "\" has been Added successfully.");
            request.setAttribute("result", "SUCCESS");
            request.setAttribute("addMoreLink", "YES");
            request.setAttribute("addMoreLinkURL", "UserManagement.do?option=addCustomer");
        } else if (result.equals("already exist")) {
            request.setAttribute("show_message", "Customer  \"" + userForm.getName() + "\" already exists in database.");
            request.setAttribute("result", "FAILURE");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", "UserManagement.do?option=addCustomer");

        } else {
            request.setAttribute("show_message", "Customer  \"" + userForm.getName() + "\" has not been Added , please contact System Admin.");
            request.setAttribute("result", "FAILURE");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", "UserManagement.do?option=addCustomer");
        }

        String goLink = userForm.getGoLink();
        if (goLink != null) {
            return mapping.findForward("messagenewwindow");
        } else {
            return mapping.findForward("message");
        }
    }

    public ActionForward modifyCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        return mapping.findForward("modifyCust");
    }

    public ActionForward getCustomerNames(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserManagementForm userForm = (UserManagementForm) form;

        UserManagementDAO userdao = new UserManagementDAO();
        String result = userdao.getCustNameList(userForm);
        PrintWriter pw = response.getWriter();
        pw.write(result);
        pw.close();

        return null;
    }

    public ActionForward getCustomerDetAjax(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        UserManagementForm userForm = (UserManagementForm) form;
        UserManagementDAO userdao = new UserManagementDAO();


        int result = userdao.getCustDetAjax(userForm);
        userdao.getCustomerDataList(userForm);
        request.setAttribute("result", result);

        return mapping.findForward("getCustomerAjax");
    }

    public ActionForward updatecustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        UserManagementForm userForm = (UserManagementForm) form;
        UserManagementDAO userDAO = new UserManagementDAO();
        String status = null;
        status = userForm.getStatus();
        String custname = userForm.getName();
        String userID = (String) session.getAttribute("user_id");
        if (status.equals("N")) {
            String res = userDAO.updateCustomer(userForm, userID);
            if (res.equals("success")) {
                request.setAttribute("show_message", "Customer has been deleted successfully.");
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("deleteMoreLink", "YES");
                request.setAttribute("deleteMoreLinkURL", "UserManagement.do?option=modifyCustomer");
            }
        } else {
            String result = userDAO.updateCustomerDetails(userForm, custname, userID);
            if (result.equals("success")) {
                request.setAttribute("show_message", "Customer  \"" + userForm.getName() + "\" has been updated successfully.");
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("modifyMoreLink", "YES");
                request.setAttribute("modifyMoreLinkURL", "UserManagement.do?option=modifyCustomer");
            } else if (result.equals("doesnotexist")) {
                request.setAttribute("show_message", "Customer  \"" + userForm.getName() + "\" does not exist in database.");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "UserManagement.do?option=modifyCustomer");
            } else {
                request.setAttribute("show_message", "Customer  \"" + userForm.getName() + "\" has not been updated , please contact System Admin.");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "UserManagement.do?option=modifyCustomer");
            }
        }
        return mapping.findForward("message");
    }

    public ActionForward initUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementDAO obj = new UserManagementDAO(conn);
            UserManagementForm mf = (UserManagementForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<UserManagementForm> userList = obj.getUserList(mf, nameSrch);
            request.setAttribute("userList", userList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("manageUserList");
    }

    public ActionForward getUserData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementDAO obj = new UserManagementDAO(conn);
            UserManagementForm userForm = (UserManagementForm) form;
            String user_id = request.getParameter("user_id");
            obj.getUserListByID(userForm, user_id);
//            obj.getUserDataList(userForm);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("getUserAjax");
    }

    public ActionForward manageUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            // HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            UserManagementDAO obj = new UserManagementDAO(conn);
            String type = request.getParameter("type");
            String status = request.getParameter("status");
            String id = request.getParameter("id");
            data_str = obj.UpdateUserState(status, type, id);
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public ActionForward getUserProfile(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        UserManagementForm userForm = (UserManagementForm) form;

        UserManagementDAO dao = new UserManagementDAO();
        String user_id = request.getParameter("user_id");
        dao.getUserListByID(userForm, user_id);
        dao.getUserDataList(userForm);

        return mapping.findForward("getUserProfile");
    }

    public ActionForward updateUserProfile(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        UserManagementForm userForm = (UserManagementForm) form;
        String userId = request.getParameter("userId");
        UserManagementDAO dao = new UserManagementDAO();
        String result = dao.updateUserDetails(userForm, userId);

        if (result.equals("success")) {
            request.setAttribute("show_message", "Your profile has been modified successfully.");
            request.setAttribute("result", "SUCCESS");
            request.setAttribute("modifyMoreLink", "YES");
            request.setAttribute("modifyMoreLinkURL", "UserManagement.do?option=getUserProfile&user_id=" + userId);
        } else {
            request.setAttribute("show_message", "Your profile has not been modified , please contact System Admin.");
            request.setAttribute("result", "FAILURE");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", "UserManagement.do?option=getUserProfile&user_id=" + userId);
        }
        return mapping.findForward("message");
    }

    // for Opening User Management Page
    public ActionForward initUserInformation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("UserInformation");
    }

    // Init Assign Role To User Type 
    public ActionForward initAssignRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementDAO obj = new UserManagementDAO(conn);
            request.setAttribute("UserTypeList", obj.getUserType());
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("initAssignRoles");
    }

    //assignRolesResponsibilities created on 13/05/14 by Megha
    public ActionForward assignRolesResponsibilities(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementDAO obj = new UserManagementDAO(conn);
            UserManagementForm uf = (UserManagementForm) form;
            String user_type = uf.getUser_type_desc();
            int user_type_id = Integer.parseInt(user_type);
            String userTypedesc = obj.getuserType(user_type_id);
            Vector userFun = new Vector();
            Vector func_nameVec = new Vector();
            userFun = obj.fillFunctionality(user_type_id, conn);
            func_nameVec = obj.getUserFunctionalties_name(conn);
            request.setAttribute("userType", userTypedesc);
            request.setAttribute("userTypeId", user_type_id);
            request.setAttribute("userFun", userFun);
            request.setAttribute("func_nameVec", func_nameVec);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("initAssignRoles");
    }

    //assignRolesToUserType created on 13/05/14 by Megha
    public ActionForward assignRolesToUserType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementDAO obj = new UserManagementDAO(conn);
            UserManagementForm uf = (UserManagementForm) form;
            int user_type_id = Integer.parseInt(request.getParameter("userTypeId"));
            String checkedValues = request.getParameter("checkedValues"); // Values of roles assigned
            String roleArr[] = checkedValues.split("_");
            int forward_main = obj.updatUserFunctionalities(user_type_id, roleArr, conn);
            if (forward_main == 1) {
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("show_message", "Successfully Assigned the Roles.");
                request.setAttribute("assignMoreLink", "YES");
                request.setAttribute("assignMoreLinkURL", "UserManagement.do?option=initAssignRoles");
            } else if (forward_main == 0) {
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "Error Occured While Assigning the Roles.");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "UserManagement.do?option=initAssignRoles");
            } else if (forward_main == 2) {
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "Unable to Assign Roles. Please Contact to System Administrator.");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "UserManagement.do?option=initAssignRoles");
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("message");
    }

    public ActionForward initChangePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        try {
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward("changePassword");
    }

    public ActionForward changePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Connection conn = null;
        String result = "";
        try {
            conn = new dbConnection().getConnection();
            ActionMessages messages = new ActionMessages();
             HttpSession session = request.getSession();
            String user_id = (String)session.getAttribute("user_id");
            String pwd = "", successmsg = "";
            String password = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");

            try {
                pwd = EncryptionDecryption.encrypt(password);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            result = new UserManagementDAO().checkPasswordExist(user_id, pwd);
            if (result.equals("NotExist")) {
                successmsg = "Invalid Password. Please Enter Correct Password.";
            } else {

                String pattern = "^([a-zA-Z0-9]){7,}$";
                if (newPassword.matches(pattern)) {
                     successmsg = new UserManagementDAO().changePassword(user_id, newPassword);
                   
                }
                else
                    successmsg = "Password Criteria Not Matched";


                // change password

            }
//            masterDAO obj = new masterDAO();
//            LinkedHashSet<LabelValueBean> userList = obj.getUserList("34");
//
//            request.setAttribute("userList", userList);
            request.setAttribute("successmsg", successmsg);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("changePassword");
    }
     public ActionForward initDealer(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementDAO obj = new UserManagementDAO(conn);
            UserManagementForm mf = (UserManagementForm) form;
            String nameSrch = request.getParameter("nameSrch");
            ArrayList<UserManagementForm> dealerList = obj.getDealerList(mf, nameSrch);
            request.setAttribute("dealerList", dealerList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("manageDealerList");
    }

     public ActionForward addDealer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementForm userForm = (UserManagementForm) form;
            UserManagementDAO userDAO = new UserManagementDAO(conn);            
            userDAO.getDealerDataList(userForm);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return mapping.findForward("manageDealer");
    }
     public ActionForward getDealerData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementDAO obj = new UserManagementDAO(conn);
            UserManagementForm userForm = (UserManagementForm) form;
            String dealerCode = request.getParameter("dealerCode");
            obj.getDealerDataList(userForm);
            obj.getDealerListByDealerCode(userForm, dealerCode);
            
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("getDealerAjax");
    }
     public ActionForward updateDealer(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementForm userForm = (UserManagementForm) form;
            String dealerCode = request.getParameter("dealerCode");
            String flag = request.getParameter("flag");
            userForm.setFlag(flag);
            HttpSession session = request.getSession();
            String user_id = (String)session.getAttribute("user_id");
            UserManagementDAO dao = new UserManagementDAO(conn);
            String result = dao.updateDealerDetails(userForm, user_id);

            if (result.equals("success")) {
                request.setAttribute("show_message", "Dealer Code \"" + userForm.getDealerCode() + "\"  has been modified successfully.");
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("modifyMoreLink", "YES");
                request.setAttribute("modifyMoreLinkURL", "UserManagement.do?option=initDealer");
            } else if (result.equals("notexists")) {
                request.setAttribute("show_message", "Dealer Code \"" + userForm.getDealerCode() + "\" doesn't exist in database.");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "UserManagement.do?option=initDealer");
            } else {
                request.setAttribute("show_message", "Dealer Code \"" + userForm.getDealerCode() + "\" has not been modified , please contact System Admin.");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "UserManagement.do?option=initDealer");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return mapping.findForward("message");
    }
      public ActionForward manageDealer(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String data_str = "";
        try {
            // HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            UserManagementDAO obj = new UserManagementDAO(conn);
            String type = request.getParameter("type");
            String status = request.getParameter("status");
            String dealerCode = request.getParameter("dealerCode");
            data_str = obj.UpdateDealerState(status, type, dealerCode);
            PrintWriter pw = response.getWriter();
            pw.write(data_str);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
       public ActionForward insertDealer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            UserManagementForm userForm = (UserManagementForm) form;
           // System.out.println("in insert action:" + userForm.getUserId());
            UserManagementDAO userDAO = new UserManagementDAO(conn);
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String result = userDAO.insertDealerDetails(userForm,user_id);

            if (result.equals("success")) {
                request.setAttribute("show_message", "Dealer \"" + userForm.getDealerCode() + "\" has been created successfully.");
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "UserManagement.do?option=initDealer");

            } else if (result.equals("alreadyexist")) {
                request.setAttribute("show_message", "Dealer \"" + userForm.getDealerCode() + "\" already exists.");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "UserManagement.do?option=initDealer");
            } else {
                request.setAttribute("show_message", "Dealer \"" + userForm.getDealerCode() + "\" has not been created , please contact System Admin.");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "UserManagement.do?option=initDealer");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return mapping.findForward("message");
    }
}
