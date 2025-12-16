<%--
    Document   : SuccessMessage
    Created on : Feb 7, 2014, 3:36:10 PM
    Author     : manish.kishore
--%>


<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>


<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String result = (String) request.getAttribute("result");

            ArrayList<String> exists = (ArrayList<String>) request.getAttribute("exists");
            if (exists == null) {
                exists = new ArrayList<String>();
            }

            String contextPath = request.getContextPath();
            String show_message = "" + request.getAttribute("show_message");
            String optionLink = "" + request.getAttribute("optionLink");
            String optionLinkURL = "" + request.getAttribute("optionLinkURL");
            String addMoreLink = "" + request.getAttribute("addMoreLink");
            String addMoreLinkURL = "" + request.getAttribute("addMoreLinkURL");
            String approveMoreLink = "" + request.getAttribute("approveMoreLink");
            String approveMoreLinkURL = "" + request.getAttribute("approveMoreLinkURL");
            String modifyMoreLink = "" + request.getAttribute("modifyMoreLink");
            String modifyMoreLinkURL = "" + request.getAttribute("modifyMoreLinkURL");
            String deleteMoreLink = "" + request.getAttribute("deleteMoreLink");
            String deleteMoreLinkURL = "" + request.getAttribute("deleteMoreLinkURL");
            String assignMoreLink = "" + request.getAttribute("assignMoreLink");
            String assignMoreLinkURL = "" + request.getAttribute("assignMoreLinkURL");
            String generateInvoiceLink = "" + request.getAttribute("generateInvoiceLink");
            String generateInvoiceLinkURL = "" + request.getAttribute("generateInvoiceLinkURL");
            String printInvoiceLinkURL = "" + request.getAttribute("printInvoiceLinkURL");
            String printInvoiceLink = "" + request.getAttribute("printInvoiceLink");
            String viewInstallLink = "" + request.getAttribute("viewInstallLink");
            String viewInstallLinkURL = "" + request.getAttribute("viewInstallLinkURL");
            String taxNotAvailable = "" + request.getAttribute("taxNotAvailable");

            String upperLinks = (String) request.getParameter("upperLink");
            if (upperLinks == null) {
                upperLinks = "";
            }
            String[] uppderLinksArr = upperLinks.split("@@");
            if (uppderLinksArr == null) {
                uppderLinksArr[0] = "";
            }
%>
<script>
   function printInvoice()
   {
      var strURL = "${pageContext.request.contextPath}/<%=printInvoiceLinkURL%>";
      window.open(strURL,'iji','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
   }
</script>
<link href="${pageContext.request.contextPath}/layout/css/login.css" rel="stylesheet" type="text/css">


<div class="contentmain1" style="width: 100%">
    <div class="breadcrumb_container" style="font-size:10px !important;width: 100%">
        <ul class="hText">
            <%for (int i = 0; i < uppderLinksArr.length; i++) {
                            if (!uppderLinksArr[i].equals("")) {
            %>
            <li class="breadcrumb_link"><%=uppderLinksArr[i]%></li>
            <%}
                        }%>
            <%-- <li class="breadcrumb_link">Success Page</li>--%>
        </ul>
    </div>
    <center>

        <div class="content_right1">

              <div class="con_slidediv1" style="position: relative;width: 100%">

                <h1></h1>
            <div align="center" style="height: 350px;">
                <table  align="center" cellpadding="0" cellspacing="0" border=0 height="70%" >
                    <%            if (exists.size() > 0) {%>

                    <%}%>
                    <%if ((show_message != null) && !(show_message.equals("null"))) {%>
                    <tr style="height: 20px">
                        <td align="center" class="red">
                            <%if ((result != null) && !result.equals("null")) {%>
                            <%if (result.equals("SUCCESS")) {%>
                            <img alt="" src="${pageContext.request.contextPath}/images/success.gif"/>
                            <%} else if (result.equals("FAILURE")) {%>
                            <img alt="" src="${pageContext.request.contextPath}/images/failure.gif"/>
                            <%}%>
                            <%}%>
                            <html:errors/><font class="text"><%=show_message%></font>
                            <%}%>
                            <%if ((addMoreLink != null) && !addMoreLink.equals("null") && addMoreLink.equals("YES")) {%>
                            <br>
                            <a href="${pageContext.request.contextPath}/<%=addMoreLinkURL%>"><bean:message key="label.common.addMore" />..</a>

                            <% if ((printInvoiceLink != null) && !printInvoiceLink.equals("null") && printInvoiceLink.equals("YES")) {%>
                            <br>
                            or  <a onclick="printInvoice();" href="javascript:void(0);" ><bean:message key="label.common.printInvoice" />..</a>
                            <%}  }
                            
                           else if ((approveMoreLink != null) && !approveMoreLink.equals("null") && approveMoreLink.equals("YES")) {%>
                            <br>
                            <a href="${pageContext.request.contextPath}/<%=approveMoreLinkURL%>"><bean:message key="label.common.approveMore" />..</a>
                            <%}
                           else if ((taxNotAvailable != null) && !taxNotAvailable.equals("null") && taxNotAvailable.equals("YES")) {%>
                           <br>
                           <bean:message key="label.common.wcTaxFailure" />
                           <%} 

                            else if ((optionLink != null) && !optionLink.equals("null") && optionLink.equals("YES")) {%>
                            <br>
                            <a href="${pageContext.request.contextPath}/<%=optionLinkURL%>"><bean:message key="label.common.tryAgain" />..</a>
                            <%} else if ((modifyMoreLink != null) && !modifyMoreLink.equals("null") && modifyMoreLink.equals("YES")) {%>
                            <br>
                            <a href="${pageContext.request.contextPath}/<%=modifyMoreLinkURL%>"><bean:message key="label.common.modifyMore" />..</a>
                            <%} else if ((viewInstallLink != null) && !viewInstallLink.equals("null") && viewInstallLink.equals("YES")) {%>
                            <br>
                            <a href="${pageContext.request.contextPath}/<%=viewInstallLinkURL%>"><bean:message key="label.install.viewInstall" />..</a>
                            <%}else if ((deleteMoreLink != null) && !deleteMoreLink.equals("null") && deleteMoreLink.equals("YES")) {%>
                            <br>
                            <a href="${pageContext.request.contextPath}/<%=deleteMoreLinkURL%>"><bean:message key="label.common.deleteMore" />..</a>
                            <%} else if ((assignMoreLink != null) && !assignMoreLink.equals("null") && assignMoreLink.equals("YES")) {%>
                            <br>
                            <a href="${pageContext.request.contextPath}/<%=assignMoreLinkURL%>"><bean:message key="label.common.assignMore" />..</a>
                            <%} else if ((generateInvoiceLink != null) && !generateInvoiceLink.equals("null") && generateInvoiceLink.equals("YES")) {%>
                            <br>
                            <a href="${pageContext.request.contextPath}/<%=generateInvoiceLinkURL%>"><bean:message key="label.common.generateinvoice" />..</a> or
                            <a onclick="printInvoice();" href="javascript:void(0);" ><bean:message key="label.common.printInvoice" />..</a>
                            <%}%>

                        </td>
                    </tr>
                    <%            if (exists.size() > 0) {%>
                    <tr style="height: 10px">
                        <td align="center" class="red">&nbsp;
                        </td>
                    </tr>
                     <tr>
                         <td align="center">
                             <table align="center" border="0" >
                                 <td valign="center" >
                             <%if (exists.size() > 1) {%>
                             Following Chassis Numbers are not Added
                             <%} else {%>
                             Following Chassis Number is not Added
                             <%}%>
                              </td>
                          </table>
                         </td>
                     </tr>
                    <tr>
                        <td align="center">
                            <div style="overflow:auto; height:250px;width:600px;">
                                <table width="600" border="0" cellpadding="5" cellspacing="1" bgcolor="#CCCCCC">
                                    <tr style="background:#c0c0c0 ">
                                        <td  height="10" align="center" ><strong>S.No.</strong></td>
                                        <td  align="left" style="padding-left: 3px"><strong >&nbsp;Chassis No.</strong></td>
                                        <td  align="left" style="padding-left: 3px"><strong >&nbsp;Remarks</strong></td>
                                    </tr>
                                    <%
                                                    int ind = 1;
                                                    for (int i = 0; i < exists.size(); i++) {%>
                                    <tr bgcolor="#FFFFFF">
                                        <td align="center"  ><%=ind%></td>
                                        <td align="left" style="padding-left: 3px">&nbsp;<%=exists.get(i).split("@@")[0]%></td>
                                        <td align="left" style="padding-left: 3px">&nbsp;<%=exists.get(i).split("@@")[1]%></td>
                                    </tr>
                                    <% ind++;
                                                    }
                                                }
                                    %>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        </div>
    </center>
</div>




