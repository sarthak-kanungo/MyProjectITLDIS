<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility" %>
<html>
    
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
				String contextPath = request.getContextPath();
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                return;
            }
    %>
    <%
            String ref_number = request.getParameter("refno");
            String x1 = request.getParameter("x");

            String y1 = request.getParameter("y");
            //System.out.println("Refnumer:"+ref_number);

            String group_no = "" + session.getAttribute("group_no");
            String del_index = "" + request.getParameter("del_index");
            String side = "" + request.getParameter("side");


            int index_no = 0;
            Connection conn = null;
            ResultSet rs = null;
            try {

                conn = holder.getConnection();
                if (conn == null) {
                    out.println("Connection not established");
                }
                //Statement stmt1=conn.createStatement();

                /*String sql1="select X,Y,SIDE,INDEX_NO from REF_COORD where GROUP_NO='"+group_no+"' and PART_NO='"+ref_number+"'";
                ResultSet rs=stmt1.executeQuery(sql1);


                boolean flag=rs.next();

                while(flag) {
                String x=rs.getString("X");
                String y=rs.getString("Y");
                side=rs.getString("SIDE");
                index_no=rs.getInt("INDEX_NO");
                flag=rs.next();
                }
                rs.close();
                stmt1.close();*/


                Statement stmt = conn.createStatement();

                String sql = "delete from REF_COORD where GROUP_NO='" + group_no + "'  and X='" + x1 + "' and Y='" + y1 + "' and SIDE='" + side+"'";
                //System.out.println("sql:"+sql);
                int result = stmt.executeUpdate(sql);
                //System.out.println("result:"+result);
                
                stmt.close();
                //conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                //conn.close();
            }

    %>
     <script language="javascript">

        function closeparent()
        {
            window.opener.parent.right_middle.location.reload();
            self.close();
        }
    </script>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <body onload="javascript:closeparent();">


    </body>
</html>
