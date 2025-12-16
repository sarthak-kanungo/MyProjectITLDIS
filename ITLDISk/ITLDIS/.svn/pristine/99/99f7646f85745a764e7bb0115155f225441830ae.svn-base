/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EAMG.Group.Action;

import java.sql.Connection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import EAMG.Group.DAO.EAMGGroupDAO_R;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author manish.kishore
 */
public class EAMG_ShowGrpBomCustomAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session=request.getSession();
        String groupno=request.getParameter("group_no");
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        Connection conn=null;

        try
        {
        //System.out.println("groupno:"+groupno);
        Vector GrpBomVec=new Vector();
        session.setAttribute("group_no",groupno);
        Vector SeqNoVec=new Vector();
        Vector CompVec=new Vector();
        Vector CompTypeVec=new Vector();
        Vector QtyVec=new Vector();
        Vector RevNoVec=new Vector();
        Vector LevelVec=new Vector();
        Vector CompDescVec=new Vector();
        Vector IndexVec=new Vector();

        GrpBomVec.add(CompVec);
        GrpBomVec.add(CompTypeVec);
        GrpBomVec.add(QtyVec);
        GrpBomVec.add(SeqNoVec);
        //GrpBomVec.add(RevNoVec);
        //GrpBomVec.add(LevelVec);
        GrpBomVec.add(CompDescVec);
        GrpBomVec.add(IndexVec);

        conn=holder.getConnection();
        EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);
        GrpBomVec=dao.getGrpBomVecFromCustom(GrpBomVec,groupno,0,conn);
        //System.out.println("CompVec:"+GrpBomVec);
        session.setAttribute("GrpBomVec",GrpBomVec);
        }catch(Exception e){e.printStackTrace();}
        finally
        {
//            if (conn != null) {
//                conn.close();
//                conn = null;
//
//            }
        }
        return mapping.findForward(SUCCESS);
    }
}
