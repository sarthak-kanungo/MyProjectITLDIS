/*
 * EAMG_AttachGroupAction.java
 *
 * Created on Nov 08, 2011, 5:34 PM
 */
/*
File Name: 	EAMG_AttachGroupAction.java
PURPOSE: 	TO insert values to Model Group in the DATABASE.
HISTORY:
DATE        	BUILD     	AUTHOR              MODIFICATIONS
08/11/11	1.0		avinash.pandey  $$1 Created
 */
package EAMG.Model.Action;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author pramod.vishwakarma
 * @version
 */
public class EAMG_AttachGroupAction extends Action
{

    private final static String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ////////////////getting the values from session//////////////////////
        HttpSession hs = request.getSession();
        String mno = (String) hs.getAttribute("modelno");
        String[] grpnos = (String[]) hs.getAttribute("selectedgrps");
        ConnectionHolder holder = (ConnectionHolder) hs.getValue("servletapp.connection");
        ////////////////////////////////////////////////////////////////////////
        //declaration of variables used.
        String sql = "select max(SEQ_ORDER) from CAT_MODEL_CLASSIFICATION where MODEL_NO='" + mno + "'";
        //String query1="delete from MODEL_GROUP_MAP where MODEL_NO='"+mno+"'";
        //String query2="insert into MODEL_GROUP_MAP values(?,?,?,?)";
        String query3 = "delete from CAT_MODEL_GROUP where MODEL_NO='" + mno + "'";
        String query4 = "insert into CAT_MODEL_GROUP(MODEL_NO, GROUP_TYPE, GROUP_NO, TYPE_LEVEL, LEVEL_IMG, MAP_NAME, FDATE, TDATE,GROUP_SEQUENCE) values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String grpno = "";
        String disgrpno = "";
        // String typelevel="";
        //  String levelimg="";
        String type = "";
        try
        {
            //getting a connection.
            conn = holder.getConnection();
            Date today = new java.sql.Date(new java.util.Date().getTime());
            int rev_no = 0;

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next())
            {
                rev_no = rs.getInt(1);
            }

            rev_no++;
//            sql="insert into  MODEL_GROUP_MAP_OLD select * from MODEL_GROUP_MAP where MODEL_NO='"+mno+"'";
//            stmt.addBatch(sql);
//            sql="insert into  MODEL_GROUP_OLD select * from MODEL_GROUP where MODEL_NO='"+mno+"'";
//            stmt.addBatch(sql);
//            sql="insert into MODEL_REVISIONS(MODEL_NO,DONE_ON,REV_NO,EFFECTIVE_DATE) values('"+mno+"',"+today+",'"+rev_no+"',"+today+")";
//            stmt.addBatch(sql);

//            stmt.executeBatch();

            //deletion of model group if exists.
//            ps=conn.prepareStatement(query1);
//            ps.executeUpdate();
//            ps.close();

            //insertion into Model Group.

//            for(int i=1;i<=grpnos.length;i++)
//            {
//
//                grpno=grpnos[i-1];
//                disgrpno=request.getParameter("disgrpno"+i);
//                type=request.getParameter("type"+i);
//
//                if(!type.equals("--Select--"))
//                {
//                    if(type.equals("Other"))
//                    {
//                        type=request.getParameter("txt_type"+i);
//                    }
//                    ps=conn.prepareStatement(query2);
//                    ps.setString(1,mno);
//                    ps.setString(2,grpno);
//                    ps.setString(3,disgrpno);
//                    ps.setInt(4,rev_no);
//                    ps.executeUpdate();
//                }
//            }
            ps = conn.prepareStatement(query3);
            ps.executeUpdate();
            ps.close();
            //insertion into Model Group.            

            if (grpnos != null)
            {
                ps = conn.prepareStatement(query4);
                for (int i = 1; i <= grpnos.length; i++)
                {

                    grpno = grpnos[i - 1];
                    disgrpno = request.getParameter("disgrpno" + i);
                    type = request.getParameter("type" + i);

                    ////System.out.println("Group No:"+grpno);
                    // //System.out.println(disgrpno);
                    ////System.out.println(typelevel);
                    // //System.out.println(type);
                    if (!type.equals("--Select--"))
                    {
                        if (type.equals("Other"))
                        {
                            type = request.getParameter("txt_type" + i);
                        }

                        ps.setString(1, mno);
                        ps.setString(2, type);
                        ps.setString(3, grpno);
                        ps.setInt(4, 1);
                        ps.setString(5, "EL1");
                        ps.setString(6, disgrpno);
                        ps.setDate(7, today);
                        ps.setDate(8, today);
                        ps.setInt(9, i);
                        ps.executeUpdate();
                    }
                }

                ps.close();
            }

            conn.commit();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            //throw new amw_SQLExceptionCatcher("SQLException",ex);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //throw new amw_ExceptionCatcher("Exception",e);
        }
        finally
        {
            //conn.close();
            if (stmt != null)
            {
                stmt.close();
            }
        }
        return mapping.findForward("success");

    }
}
