
package EAMG.Group.Action;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.EAMG_MethodsUtility;

import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author shivani.chauhan
 * @version
 */
public class EAMG_GrpModifiedAction extends Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        String res = "";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        ArrayList grpList = new ArrayList();
        ArrayList compNoAl = new ArrayList();
        ArrayList quantity = new ArrayList();
        ArrayList delAl = new ArrayList();

        ArrayList tempBomParam = new ArrayList();
        String grp_no = request.getParameter("groupno");
        String qtyArr[] = request.getParameterValues("quan");//
        //int grp_bom_param_count = Integer.parseInt(request.getParameter("grp_bom_param_count"));
        String testParam[] = request.getParameterValues("testParam0");
        String chk[] = request.getParameterValues("chk0");
        String seqno[] = request.getParameterValues("seq_no");
        String groupremarks[] = request.getParameterValues("groupremarks");
        String interchangebility[] = request.getParameterValues("interchangebility");
        String cut_of_chassis[] = request.getParameterValues("cut_of_chassis");
        String cut_of[] = request.getParameterValues("cut_of");
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        SimpleDateFormat df1 = null;


        try {
            conn = holder.getConnection();
            conn.setAutoCommit(false);

//             Date today = new java.sql.Date(new java.util.Date().getTime());
//             df1 = new SimpleDateFormat("mm/dd/yyyy");
              java.sql.Date todayDate=methodutil.getSQLTodaysDate();
             int revision=0;


            if (chk != null) {
                for(int i = 0; i < chk.length; i++) {
                    delAl.add(chk[i]);
                }
            }
            String iscomppresent = "";
            for (int i = 0; i < testParam.length; i++) {
                String compno = testParam[i];
                iscomppresent = new EAMG_MethodUtility3().isCompExist(compno, "", conn);
                if (iscomppresent.equals("notpresent") && (!compno.equals(""))) {
                    res = "Group '" + grp_no + "' can not be modified as Component '" + compno + "' does not exist in database.";
                    request.setAttribute("res", res);
                    conn.rollback();
                    return mapping.findForward(FAILURE);
                }
                compNoAl.add(compno);
            }
            if (qtyArr != null) {

                for (int i = 0; i < qtyArr.length; i++) {
                    if (qtyArr[i] == null) {
                        quantity.add("NA");
                    }
                    else {
                        quantity.add(qtyArr[i]);
                    }
                }
            }
            int totalrows = quantity.size();

            ArrayList tempAl;
            for (int i = 0; i < totalrows; i++) {
                tempAl = new ArrayList();
                tempAl.add(compNoAl.get(i));
                tempAl.add(quantity.get(i));
                grpList.add(tempAl);
            }
            String finalQuery = "";

            stmt = conn.createStatement();

//            rs=stmt.executeQuery("select max(rev_no) from GROUP_REVISIONS where GRP_KIT_NO='" + grp_no + "'");
//            if(rs.next())
//                revision=rs.getInt(1);
//
//            revision++;
//            String queryd="insert into GROUP_KIT_BOM_OLD select * from GROUP_KIT_BOM WHERE GRP_KIT_NO ='" + grp_no + "'";
//            stmt.addBatch(queryd);

            String queryd = "Delete from CAT_GROUP_KIT_BOM WHERE GRP_KIT_NO ='" + grp_no + "'";// AND COMP_TYPE= '"+type+"' AND COMPONENT = '"+compno+"' ";
            stmt.addBatch(queryd);//commented by ng
            stmt.executeBatch();

            tempAl = new ArrayList();
            int countIndexNo = 1;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < (totalrows); i++) {
                tempAl = (ArrayList) grpList.get(i);
                String compno = tempAl.get(0).toString();
                compno = compno.toUpperCase();
                String qty = tempAl.get(1).toString();
                String asreq = "";
                String quan = "";
                String date = cut_of[i].trim();
                if (date.equalsIgnoreCase("")) {
                    date=null;
                } else {
                    date = "'"+sdf1.format(sdf.parse(cut_of[i].trim()))+"'";
                }
                if (qty.equalsIgnoreCase("AR")) {
                    asreq = "YES";
                    quan = "AR";
                }
                else {
                    asreq = "NO";
                    quan = qty;
                }
                int valDel = i + 1;
                if (!delAl.contains(String.valueOf(valDel))) {
                    finalQuery = "insert into CAT_GROUP_KIT_BOM(GRP_KIT_NO,COMPONENT,COMP_TYPE,QTY,"
                            + "AS_REQUIRED,SEQUENCE,INDEX_NO,REMARKS,INTERCHANGEABILITY,CUT_OFF_CHASSIS,CUT_OFF)"
                            + "  values('" + grp_no + "','" + compno + "','PRT','" + quan + "','" + asreq + "'"
                            + ",'" + seqno[i].trim() + "','" + (countIndexNo++) + "',"
                            + "'" + groupremarks[i].trim() + "','" + interchangebility[i].trim() + "',"
                            + "'" + cut_of_chassis[i].trim() + "'," + date + ")";
                    stmt.addBatch(finalQuery);
                }
            }
            stmt.executeBatch();
//            finalQuery="insert into GROUP_REVISIONS(GRP_KIT_NO,DONE_ON,REV_NO,CMT_REV_NO,ecn) values('"+ grp_no+ "','"+todayDate+"','"+revision+"','0','0')";
//            stmt.addBatch(finalQuery);
//            System.out.println("finalQuery..."+finalQuery);
            stmt.executeBatch();
            res = "Group '" + grp_no + "' has been modified successfully.";
            stmt.close();
            conn.commit();
            request.setAttribute("flag", "Success");

        } catch (SQLException ex) {
            res = "Group '" + grp_no + "' has not been modified, Please contact System Admininstrator.";
            request.setAttribute("flag", "Failure");
            conn.rollback();
            ex.printStackTrace();
        } catch (Exception ex) {
            res = "Group '" + grp_no + "' has not been modified, Please contact System Admininstrator.";
            request.setAttribute("flag", "Failure");
            conn.rollback();
            ex.printStackTrace();
        } finally {
            //conn.close();
        }
        request.setAttribute("res", res);
        request.setAttribute("group_no", grp_no);
        return mapping.findForward(SUCCESS);

    }
}
