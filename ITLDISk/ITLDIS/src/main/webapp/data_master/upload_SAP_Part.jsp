<%-- 
    Document   : EAMG_Upload_SAP_Part
    Created on : Jul 29, 2014, 5:11:49 PM
    Author     : sreeja.vijayakumaran
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.sql.*,viewEcat.comEcat.ConnectionHolder" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<html>
    <%
                String contextPath = request.getContextPath();
                response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                response.setHeader("Expires", "0");
                response.setHeader("Pragma", "no-cache");
                session.removeAttribute("ECNImplementation");
                session.setAttribute("comp_type", "PRT");
                String mainURL = (String) session.getValue("mainURL");
    %>
    <script language="javascript">
        function check_method_select()
        {
            var flag="masterFlag";
            document.form1.action="<%=contextPath%>/sapPartExcel.do?flag="+flag+"";
            return true;
        }

        function validateExcel()
        {

                
            var filePath= document.getElementById("excelFile").value;
            if(filePath == '')
            {
                alert('Please Browse Excel  File.');
                return false;
            }
            <%--else if(filePath.indexOf(':\\') ==-1 || filePath.indexOf(':\\')==0)
            {
                alert('Invalid Excel File. Please Browse only .xls/.xlsx file.');
                return false;
            }--%>
            else if(filePath.indexOf('.xls') != (filePath.length-4) && filePath.indexOf('.xlsx') != (filePath.length-5))
            {
                alert('Invalid Excel File. Please Browse only .xls/.xlsx file.');
                return false;
            }
            return true;
                
        }

        function create_template()
        {

            var url='EAMG_create_template_save.jsp?comp_type=SAP';
            if(document.getElementById("downloadDiv") == null)
            {
                downloadDiv = document.createElement("div");
                document.getElementsByTagName("body")[0].appendChild(downloadDiv);
                downloadDiv.style.width = "0px";
                downloadDiv.style.height = "0px";
                downloadDiv.id = "downloadDiv";
            }
            else
                downloadDiv = document.getElementById("downloadDiv");
            if(document.getElementById("downloadFrame") == null)
            {
                downloadFrame = document.createElement("iframe");
                downloadFrame.id = "downloadFrame";
                downloadFrame.src = url;
                downloadFrame.scrolling = "no";
                downloadFrame.style.width = "0px";
                downloadFrame.style.height = "0px";
                downloadDiv.innerHTML = downloadFrame.outerHTML;
            }
            else
            {
                downloadFrame = document.getElementById("downloadFrame");
                downloadFrame.src = url;
            }
        }
            

    </script>

    <div class="contentmain1">
        <div class="breadcrumb_container">
            <ul class="hText">
                <li class="breadcrumb_link"><a href ='<%=contextPath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
                <li class="breadcrumb_link">UPLOAD SAP PART MASTER</li>
            </ul>
        </div>
        <br/>
        <center>
            <div class="content_right1">
                <div class="con_slidediv2" style="position: relative;width: 100%">
                    <h1 class="hText">UPLOAD SAP PART MASTER</h1>
                    <table cellspacing=1 cellpadding=5 border=0 width=100% >
                        <tr height=30 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <form name="form1" onsubmit="return check_method_select();" ENCTYPE="multipart/form-data" method="post">
                                    <br />
                                    <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                        <tr>
                                            <td width="29%" height="25" align="left" class="headingSpas">&nbsp;Browse Input File</td>
                                            <td width="65%" height="25" align="left" class="headingSpas"><input type=file id="excelFile" name=excelFile value='' size=20 style="width:275px"></td>
                                        </tr>


                                        <tr>
                                            <td colspan="3" align="right"><em><a href="<%=mainURL%>/dealer/templates/sap_PartTemplate.xls"   class="red-for-temmplate-link">* Click Here To Download Template </a><span class="red-for-temmplate">(xls).</span></em></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" align="center">
                                                <label>
                                                    <input type="submit" name="Next" id="Next" value="Submit" class="headingSpas" style="width:70px;" onclick="return validateExcel();"/>
                                                </label>
                                            </td>
                                        </tr>

                                    </table>
                                </form>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </center>
    </div>

