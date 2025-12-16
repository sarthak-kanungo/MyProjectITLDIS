<%--
    Document   : upload_pricelist_excel
    Created on : Nov 18, 2015, 04:32:46 PM
    Author     : avinash.pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.*,java.text.SimpleDateFormat,viewEcat.comEcat.ConnectionHolder" %>
<%@ page import="viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<html>
    <%
                String contextPath = request.getContextPath();
                response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                response.setHeader("Expires", "0");
                response.setHeader("Pragma", "no-cache");
                session.setAttribute("comp_type", "PRT");
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String todaydate = sdf.format(date);
                String mainURL = (String) session.getValue("mainURL");

    %>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<link rel="stylesheet" href="layout/css/login.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css" media="screen" />
<link href="${pageContext.request.contextPath}/css/dynCalendar.css" rel="stylesheet" type="text/css"/>
    <script language="javascript">
        function check_method_select()
        {
            var flag="masterFlag";
            document.form1.action="<%=contextPath%>/uploadPricelist.do?flag="+flag+"";

            return true;
        }
        function validateExcel()
        {
                
            var filePath= document.getElementById("excelFile").value;
            var extension = filePath.split('.').pop().toLowerCase();
            //if(filePath.indexOf(':\\') ==-1 || filePath.indexOf(':\\')==0)
            //{
                //alert('Invalids Excel File. Please Browse only .xls file.');
                //return false;
            //}

            if(extension!="xls" && extension!="xlsx"){
                alert('Invalid Excel File. Please Browse only .xls file or .xlsx file.');
                return false;
                         
            }
                    
            return true;
                    
        }
        $(document).ready(function () {

            $(function() {
                $( ".datepicker" ).datepicker();
            });
        });


    </script>

    <div class="contentmain1">
        <div class="breadcrumb_container">
            <ul class="hText">
                <li class="breadcrumb_link"><a href ='<%=contextPath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
                <li class="breadcrumb_link">UPLOAD PRICE LIST</li>

            </ul>
        </div>
        <br/>
        <center>
            <div class="content_right1">
                <div class="con_slidediv2" style="position: relative;width: 100%">
                    <h1 class="hText">UPLOAD PRICE LIST</h1>
                    <table cellspacing=1 cellpadding=5 border=0 width=100% >
                        <tr height=30 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <form onsubmit="return check_method_select();" method=post name=form1 ENCTYPE="multipart/form-data" >
                                    <br />
                                    <table width="100%" border="0" cellspacing="2" cellpadding="2">

                                        <tr>
                                            <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">&nbsp;</td>
                                            <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                Effective Date
                                            </td>
                                            
                                            <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left" colspan="2">
                                                <input type="text" name="effectiveDate" value="<%=todaydate%>" class="datepicker"    readonly="readonly" id="Effective Date">
                                            </td>

                                        </tr>
                                        <tr>
                                            <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">&nbsp;</td>
                                            <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Browse Input File</td>
                                            <td style="padding-left:14px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left" colspan="2"><input type=file id="excelFile" name=excelFile class="headingSpas" value='' size=20 style="width:275px"  ></td>
                                        </tr>


                                        <tr>
                                            <td colspan="4" align="right"><a href="<%=mainURL%>/dealer/templates/pricelist_template.xls"   class="red-for-temmplate-link">* Click Here To Download Template </a><span class="red-for-temmplate">(xls).</span></td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" align="center" bgcolor="#FFFFFF" style="padding-left:10px;padding-bottom: 5px" >

                                                <input type=submit name="add" class="headingSpas" id="Next" value="Next" style="width:70px;" onclick="return validateExcel();"/>
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

