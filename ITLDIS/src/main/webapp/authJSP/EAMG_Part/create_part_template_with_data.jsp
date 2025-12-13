<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.io.*" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="viewEcat.comEcat.*,java.sql.Connection,java.io.*,EAMG_MethodsUtility_Package.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String contextPath = request.getContextPath();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
            String user_id = session.getAttribute("userCode").toString();
%>

<%           Connection conn = null;
            String functionality_type = "" + request.getParameter("functionality");
            String dest = "";
            boolean creation_result = false;
            ServletOutputStream out1 =null;
            try {

                conn = holder.getConnection();
                File dest_folder = new File(ecatPath + "dealer/tempExcels/" + user_id+"/");
                if (!dest_folder.exists()) {
                    dest_folder.mkdirs();
                }
                dest = ecatPath + "dealer/tempExcels/" + user_id;
               
                EAMG_MethodUtility3 methodutil = new EAMG_MethodUtility3();
                creation_result = methodutil.createTemplateWithDataPart(functionality_type, dest, conn);
           
%>

<html>
    <head>
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

    </head>
    <%
                if (creation_result == true) {
                    //System.out.println("made");
                    response.setContentType("application/x-download");
                    response.setHeader("Content-Disposition", "attachment; filename=" + functionality_type + "_template.xls ");
                    FileInputStream input2 = new FileInputStream(dest + "/" + functionality_type + "_template.xls");
                    BufferedInputStream bis = new BufferedInputStream(input2);
                    int filesize = input2.available();
                    int dataarray[] = new int[filesize];
                    int temp = 0, i = 0;

                    while ((temp = bis.read()) != -1) {
                        dataarray[i] = temp;
                        i++;
                    }

                    out1 = response.getOutputStream();
                    for (int j = 0; j < dataarray.length; j++) {
                        out1.write(dataarray[j]);
                    }
                    input2.close();
                    bis.close();


    %>
    <body >
        <%} else {%>
    <body>
        Unable to create.
        <%}
        %>  
    </body>
<%} catch (Exception e) {
                e.printStackTrace();
            } finally {

                    out1.flush();
                    out1.close();


            }%>
</html>
