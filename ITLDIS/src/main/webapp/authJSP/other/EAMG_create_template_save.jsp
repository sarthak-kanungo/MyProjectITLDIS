<%-- 
    Author     : Avinash.Pandey
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="viewEcat.comEcat.ConnectionHolder,java.sql.Connection,java.io.*,com.EAMG.common.*,EAMG.PartDAO_CUD.*" %>
<%
    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
    response.setHeader("Expires", "0");
    response.setHeader("Pragma", "no-cache");
    ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
    String contextPath = request.getContextPath ();
%>
<%
    Connection conn = null;
    String comp_type = "" + request.getParameter("comp_type");
    String ecatPath = "" + session.getAttribute("ecatPath");
    String dest = "";
    boolean creation_result = false;
    ServletOutputStream out1=null;
    try {
        conn = holder.getConnection();

        //System.out.println("ecatPath:"+ecatPath);

        File dest_folder = new File(ecatPath + "dealer/templates/");
        if (!dest_folder.exists()) {
            dest_folder.mkdirs();
        }
        dest = ecatPath + "dealer/templates/";

        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        ////System.out.println("I m in destnation :"+dest);
        creation_result = methodutil.createAMWPartandOEMPartTemplate(comp_type, dest, conn);

    

//String xls_file=""+request.getAttribute("xls_file");
//String mainURL=""+session.getAttribute("mainURL");
%>

<html>
    <head>
        <title>AMW -Ecatalogue</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link href="<%=contextPath%>/css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <%
        if (creation_result == true) {
            //System.out.println("made");
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=" + comp_type + "_template.xls ");
            FileInputStream input2 = new FileInputStream(dest + "/" + comp_type + "_template.xls");
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