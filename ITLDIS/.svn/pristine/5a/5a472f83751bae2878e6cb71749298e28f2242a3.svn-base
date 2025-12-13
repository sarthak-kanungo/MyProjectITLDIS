<%-- 
    Author     : Avinash.Pandey
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String contextPath = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
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
            Connection conn = null;
            String comp_type = "" + request.getParameter("comp_type");
            String dest = "";
            boolean creation_result = false;
            ServletOutputStream out1=null;
            try {
                conn = holder.getConnection();

                //System.out.println("ecatPath:" + ecatPath);

                File dest_folder = new File(ecatPath + "dealer/templates/");
                if (!dest_folder.exists()) {
                    dest_folder.mkdirs();
                }
                dest = ecatPath + "dealer/templates/";

                EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
                ////System.out.println("I m in destnation :"+dest);
                creation_result = methodutil.createTemplate(comp_type, dest, conn);

            
//String xls_file=""+request.getAttribute("xls_file");
//String mainURL=""+session.getAttribute("mainURL");
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
                    response.setHeader("Content-Disposition", "attachment; filename=_template.xls ");
                    FileInputStream input2 = new FileInputStream(dest + "/_template.xls");
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
<% } catch (Exception e) {
                e.printStackTrace();
            } finally {
                    out1.flush();
                    out1.close();

             }%>
</html>