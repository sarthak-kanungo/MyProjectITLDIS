<%--
    Document   : manageUserVsPriceList
    Created on : Nov 19, 2015, 11:19:54 AM
    Author     : mahendra.rawat
--%>


<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>

<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>

<script language="JavaScript" type="text/javascript">

    function submitForm()
    {
              var percentage = document.getElementById('percentage').value.trim();
              if( percentage<0 || percentage==''){
                document.getElementById("msg_saveFAILED").innerHTML="Percentage Should Not Be Less Than 0 or Blank";
                document.getElementById("percentage").value="";
                return false;
            }
            if( isNaN(percentage)){
                document.getElementById("msg_saveFAILED").innerHTML="Percentage Should  Be a Number";
                document.getElementById("percentage").value="";
                return false;
            }
            document.getElementById("percentage").value=percentage;
            document.getElementById("SellingPercentage").submit();
            
        }


</script>



<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href ='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
            <li class="breadcrumb_link">MANAGE SELLING PERCENTAGE</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED">${successmsg}</div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1>MANAGE SELLING PERCENTAGE</h1>
                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr bgcolor="#FFFFFF">
                        <td width=100% valign="top" >
                            <form action="masterAction.do?option=saveOrUpdateManageSellingPercentage" method="post" id="SellingPercentage" onsubmit="return false;" >
                                <table width=100% border="0" cellpadding="0" cellspacing="0" >
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                                                <tr height="30">
                                                    <td style="padding-left:400px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left" width="33%"><bean:message key="label.master.SellingPercentage" />:</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left" width="33%">
                                                        <c:choose>
                                                            <c:when test="${sellingPercentage eq '0.0'}">
                                                                 <input type="text" value="" name='percentage' id='percentage' class="headingSpas" style='width:120px !important'/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="text" value="${sellingPercentage}" name='percentage' id='percentage' class="headingSpas" style='width:120px !important'/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    
                                                </tr>

                                                <tr height="40">
                                                    <td width="33%">&nbsp;</td>
                                                    <td style="padding-left:15px;padding-bottom: 10px" align="left" bgcolor="#FFFFFF" width="33%">
                                                        <input type='button' name="add" id="submitId" value="Submit" class="headingSpas" onclick="submitForm()"/>
                                                    </td>
                                                   
                                                </tr>
                                            </table>
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

