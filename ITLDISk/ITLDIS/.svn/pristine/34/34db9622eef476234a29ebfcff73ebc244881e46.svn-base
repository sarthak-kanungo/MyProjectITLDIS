<%-- 
    Document   : v_uploadfile
    Created on : May 20, 2014, 03:23:40 PM
    Author     : Avinash.pandey
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<!doctype html>
<html>
    <head>
        <%
                    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                    response.setHeader("Expires", "0");
                    response.setHeader("Pragma", "no-cache");
                    String option = request.getParameter("option");
                    String prtno = request.getParameter("workCode");
                    String index = request.getParameter("index");
                    String add = request.getParameter("add");
                 
                    String jobCardNo = request.getParameter("jobCardNo");
                    System.out.println("jobcardno in upload"+jobCardNo);
                    // String pdino=request.getParameter("pdi_no");
                    // request.setAttribute("chassis", chassis);
                    session.setAttribute("option", option);
                    session.setAttribute("index", index);
                    session.setAttribute("prtno", prtno);
                    session.setAttribute("add", add);
                    String cntxpath = request.getContextPath();
        %>
        <script type="text/javascript" language="javascript">
            var contextPath = '${pageContext.request.contextPath}';
        </script>
        <link rel="stylesheet" href="layout/css/login.css" type="text/css" />
        <script language="JavaScript" src='${pageContext.request.contextPath}/js/validation.js'></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Master_290414.css" type="text/css" />
        <script type="text/javascript"src="${pageContext.request.contextPath}/js/Master_290414.js"></script>
<!--        <script language="javascript">
              
    
         function uploadfile()
            {
                var upload=document.getElementsByName("file1").value;
                if(upload=='')
                {
                    alert("Please Upload the required file.");
                    document.getElementsByName("file1").focus();
                    return false;
                }
            
            }
           
        </script>-->
    </head>
    <body>
        <div class="content">
            <tiles:insert attribute="body"/>
      </div> 
        
        <br>

        <%--  <% if (option != null && option.equals("OtherChargesWork")) {--%>
      <html:form method="post" enctype="multipart/form-data" action="serviceProcess.do?option=uploadEstimeteFile">
           

            <table align=center border="0" cellpadding="1" cellspacing="1">
                <tr>
                    <td> <html:file property="file" size="50"/>
                        <input type="hidden" name="jobCardNo"  value='<%= jobCardNo %>'>
                    </td>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center">
                        <html:submit onclick="uploadfile();">Submit</html:submit>&nbsp;<html:submit onclick="javascript:self.close();">Cancel</html:submit>
                        <!--<input type="button"  value="Cancel"  onclick="javascript:self.close();">-->
                    </td>
                </tr>

            </table>

        </html:form>
        <%--<html:form action="serviceProcess.do?option=uploadEstimeteFile" method="post" enctype="multipart/form-data">--%>
        <!--<br />-->
      <%--<html:file property="file" size="50" />--%>
        <!--<br />-->
        <!--<br />-->
        <%--<html:submit>Submit</html:submit>--%>
    <%--</html:form>--%>
          
    </body>
</html>