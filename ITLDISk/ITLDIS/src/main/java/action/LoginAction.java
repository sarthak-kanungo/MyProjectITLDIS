/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.common.EncryptionDecryption;

import beans.LoginForm;
import dao.LoginDAO;
import dao.inventoryDAO;
import dbConnection.dbConnection;
import viewEcat.comEcat.ConnectionHolder;
import viewEcat.comEcat.PageTemplate;
import viewEcat.orderEcat.PriceDetails;

/**
 *
 * @author manish.kishore
 */
public class LoginAction extends DispatchAction
{

    String dbPATH = new dbConnection().dbPathAuth;

    public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {

        LoginForm loginForm = (LoginForm) form;
        LoginDAO dao = new LoginDAO();
        inventoryDAO invDAO=new inventoryDAO();
        String userId = loginForm.getUserid();
        String password = loginForm.getPassword();
        String language = loginForm.getLanguage();
        String forward = "fail";
  //      String userTypeId = null;
        
        String[] tempArr = null;

        String result = null;

        HttpSession session = request.getSession();

        ActionMessages messages = new ActionMessages();

        if (userId == null || userId.trim().equals(""))
        {
            messages.add("name", new ActionMessage("error.userid.required"));
        }
        else if (password == null || password.trim().equals(""))
        {
            messages.add("password", new ActionMessage("error.password.required"));
        }
        else
        {
            String pwd= "";
            try
            {
             pwd = EncryptionDecryption.encrypt(password);
            }
            catch (Exception ee)
            {
                ee.printStackTrace();
                pwd = "";
            }
            result = dao.checkUser(userId,pwd );

            if (result.equals("Connection Problem"))
            {
                messages.add("ConnectionProblem", new ActionMessage("error.connection"));
            }
            else if (result.equals("NotActive"))
            {
                messages.add("InActiveUser", new ActionMessage("error.login.inactive"));
            }
            else if (result.equals("NotExist"))
            {
                messages.add("Invalid User", new ActionMessage("error.login.failed"));
            }
            else if (!result.contains("Active@@"))
            {
                messages.add("Unknown Error", new ActionMessage("error.unknown"));
            }
        }


      //  System.out.println("language selected is" + language);


        /*

        if(language!=null && language.equalsIgnoreCase("English"))
        {
        request.getSession().setAttribute(
        Globals.LOCALE_KEY, Locale.US);

        }
        else
         */








        if (!messages.isEmpty())
        {
            saveErrors(request, messages);
            forward = "fail";
        }
        else
        {

//              String lastChange = null;
//        String registrationDate = null;
//        boolean flag = false;
//        boolean check = false;
//        boolean check_reg = false;

            try
            {
                dao.getLanguageDetails(loginForm);
//                System.out.println("loginForm.getLanguageName()" + loginForm.getLanguageName());
//                System.out.println("loginForm.getLanguageCode()" + loginForm.getLanguageCode());
//                System.out.println("loginForm.getLanguageCountry()" + loginForm.getLanguageCountry());
    
                Locale locale_lang = new Locale(loginForm.getLanguageCode(), loginForm.getLanguageCountry());
                request.getSession().setAttribute(Globals.LOCALE_KEY, locale_lang);

                session.setAttribute("locale_lang", locale_lang);

//                System.out.println("inside it");

                Properties prop = new Properties();
                prop.load(this.getServlet().getServletContext().getResourceAsStream("/WEB-INF/classes/com/myapp/struts/ApplicationResource_" + locale_lang + ".properties"));

                session.setAttribute("prop", prop);

            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("ApplicationResource_" + e);
            }




            tempArr = result.split("@@");

            // lastChange = tempArr[3];
            //  registrationDate = tempArr[4];

            session.setAttribute("user_id", userId.toUpperCase());
            session.setAttribute("password", password);
            session.setAttribute("session_active", "true");

            session.setAttribute("user_type", tempArr[2]);
            session.setAttribute("user_type_id", tempArr[1]);
            session.setAttribute("username", tempArr[5]);
            session.setAttribute("dealerCode", tempArr[6]);
            session.setAttribute("dealerName", tempArr[7]);
//            System.out.println("tempArr[6]==" + tempArr[6]);
            session.setAttribute("language", language);//language id
            ConnectionHolder holder = null;
            Connection conn = null;
            try
            {
                PageTemplate object_pageTemplate = new PageTemplate();
                Class.forName(object_pageTemplate.jdbcDriverMAIN());
                holder = new ConnectionHolder(DriverManager.getConnection(object_pageTemplate.dsnPATH(), object_pageTemplate.dbUserName(), object_pageTemplate.dbPasswd()));
                conn = holder.getConnection();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            session.putValue("servletapp.connection", holder);

            String session_id = session.getId();

            session.putValue("session_value", session_id);

            session.putValue("screenWidth", "");
            session.putValue("screenHeight", "");


            session.putValue("server_name", PageTemplate.server_name);
            session.putValue("mainURL", PageTemplate.mainURL);
            session.putValue("ecatPATH", PageTemplate.ecatPATH);
            session.putValue("userCode", userId);
            session.setAttribute("effectiveDate", PageTemplate.effectiveDate);

            java.sql.Date inputDate = new java.sql.Date(new java.util.Date().getTime());
            java.sql.Date buckleDate = new java.sql.Date(new java.util.Date().getTime());

            session.putValue("date_OR_serial", "latest");
            session.putValue("input_Date", inputDate);
            session.putValue("modelNo", "");
            session.putValue("input_serialNo", "");
            session.putValue("buckleDate", buckleDate);


            ArrayList arr = dao.getUserModels(userId);
            StringBuilder productFamilySubQuery = new StringBuilder("");
            StringBuilder applicationTypSubQuery = new StringBuilder("");

            if ((arr != null) && (arr.size() == 2))
            {
                productFamilySubQuery = (StringBuilder) arr.get(0);
                applicationTypSubQuery = (StringBuilder) arr.get(1);
            }

            session.setAttribute("productFamilySubQuery", productFamilySubQuery.toString());
            session.setAttribute("applicationTypSubQuery", applicationTypSubQuery.toString());

            applicationTypSubQuery.setLength(0);
            applicationTypSubQuery = null;
            productFamilySubQuery.setLength(0);
            productFamilySubQuery = null;
            //getting user functionalities 

            Vector userFun = dao.getUserFunctionalties(tempArr[1]);
            session.setAttribute("lockedDealerlist",invDAO.getDealerCode());
            session.setAttribute("userFun", userFun);
            if(userFun.contains("99")){
                String binaryString= dao.getBinaryUrl(userId);
                Properties proper=(Properties)session.getAttribute("prop");
              //  String propvalue=proper.getProperty("sonalikaCatalogueUrl");
              //  session.setAttribute("binaryString", propvalue+(binaryString.equals("")?"":binaryString.split("@@")[0]));
            }

            ArrayList userSessionList = new ArrayList();
            userSessionList.add(userId);
            userSessionList.add(session);
            session.setAttribute("userSessionList", userSessionList);

            PriceDetails pObj =  new PriceDetails(conn);
            
            //COMMENTED BY AMAN
          //  String priceListCode = pObj.getPriceListCode(userId);
            String priceListCode = "MRP_INR";
        //    Double sellingPercentage = pObj.getSellingPercentageByDealerCode(tempArr[6],conn);
            Double sellingPercentage = 0.0;


            session.setAttribute("priceListCode", priceListCode);
            session.setAttribute("sellingPercentage", sellingPercentage);

     //       Vector ecatFun = EcatAuthorisation.getAllEcatFunctionalities(conn);
            try
            {
       //         pObj.loginTrakerByUserId(userId, 1, request.getRemoteAddr(), conn);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }

            Vector tempVec = new Vector();
            tempVec.addAll(userFun);
     //       tempVec.removeAll(ecatFun);

            if (tempVec.size() > 0)
            {
                forward = "success";
            }
            else if (userFun.contains("1") || userFun.contains("2"))
            {
                forward = "successEcat";
            }
            else
            {
                forward = "success";
            }





            /*if (userFun.contains("222"))//subdealer
            {
            String parent_dealer = dao.getParent(userTypeId, userId);
            session.setAttribute("parent_dealer", parent_dealer);
            }*/



        }
        return mapping.findForward(forward);
    }

    public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String forward = "logout";
        HttpSession session = request.getSession();
        session.invalidate();
        return mapping.findForward(forward);
    }

    public ActionForward homePage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String forward = "success";
        return mapping.findForward(forward);
    }
    public ActionForward contactUS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String forward = "contactUS";
        return mapping.findForward(forward);
    }
}
