<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
				String contextPath = request.getContextPath();
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                return;
            }
%>
<%
            String x = request.getParameter("x");//out.println(x);
            String y = request.getParameter("y");
            String group_no = "" + session.getAttribute("group_no");
//System.out.println("GROUP1"+group_no);
            String ref_no = request.getParameter("refno");
            ref_no = ref_no.trim();
            ref_no = ref_no.toUpperCase();
            String[] tempArr = ref_no.split("@@");
            ref_no = tempArr[1];
            ref_no = ref_no.trim();
            ref_no = ref_no.toUpperCase();
//System.out.println("in update ref_cords"+ref_no);

            String side = request.getParameter("side");
            side = side.toUpperCase();
            String index_no = tempArr[0];
            index_no = index_no.trim();

//System.out.println("index_no:"+index_no);
            Connection conn = null;
            try {
                conn = holder.getConnection();
                if (conn == null) {
                    out.println("Connection not established");
                }
                Statement stmt = conn.createStatement();
                int rev_no = 0;
                //System.out.println("insert into REF_COORD values('"+group_no+"','"+ref_no+"',"+index_no+",'"+side+"','"+x+"','"+y+"',0)");
                String sql = "insert into REF_COORD values('" + group_no + "','" + index_no + "','" + side + "','" + x + "','" + y + "',0)";
                int result = stmt.executeUpdate(sql);
                if (result == 1) {
                    /*ResultSet rs=stmt.executeQuery("select * from GROUP_STATUS where GROUP_NO='"+group_no+"' and REF_COORDS=1");
                    if(!rs.next())
                    {
                    //System.out.println("ok");
                    PreparedStatement ps=conn.prepareStatement("update GROUP_STATUS set REF_COORDS=1 where GROUP_NO='"+group_no+"'");

                    ps.executeUpdate();
                    ps.close();
                    }
                    rs.close();*/
                    //System.out.println("RefCoord updated successfully");
                }

                stmt.close();
                //conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
               //conn.close();
            }

%>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script language="javascript">



            function closeparent()
            {
                window.opener.parent.right_middle.location.reload();
                self.close();
            }
        </script>
    </head>
    <body onload="javascript:closeparent();">

        <h1><font color="#0033AB" size="2" face="Arial">Group refcoord Updated Sucessfully</font></h1>


    </body>
</html>
