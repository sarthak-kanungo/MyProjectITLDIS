<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%
    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
    response.setHeader("Expires", "0");
    response.setHeader("Pragma", "no-cache");

%>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="layout/css/login.css" type="text/css" />
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"></link>
        <title>ITLDIS</title>

    </head>

    <body>
        <c:set var="now" value="<%=new java.util.Date()%>" />
        <div class="mainwrap">


            <div class="header_main">
                 <div class="logo">
                    <a href="#"><img src="layout/images/logo.png" alt="" class="fl"></a>
                </div>

                <div class="headright">
                    <div class="toper"> 
                        <ul>
                            <li style="border:none;"><a href="${pageContext.request.contextPath}/index.jsp">Login</a></li>
                        </ul>
                    </div>

                    <%--<div class="lotopbar">
                        <ul>
                            <li><a href="#" usemap="#Map9"><img src="layout/images/help.jpg"  alt="help" /></a></li>
                            <li><a href="#" usemap="#Map9"><img src="layout/images/contact.jpg"  alt="contact" /></a></li>
                        </ul>
                    </div>--%>
                </div>

               
            </div>

            <div class="navigation">
              
                <div class="navmid" style="text-align:right; color:#fff; font-size:11px; line-height: 33px; padding-right: 10px;">
                    <fmt:formatDate type="date" dateStyle="long" value="${now}" />
                </div>
            </div>
        </div>

        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>

        <center>
            <font class="text">You have been successfully logged out. </font>
            <a href="javascript:location.href='${pageContext.request.contextPath}/index.jsp'" class="red-for-temmplate-link"><font class="text">Login Again</font></a>
        </center>

        <map name="Map" id="Map">
            <area shape="rect" coords="25,12,115,31" href=""/>
            <area shape="rect" coords="151,11,197,32" href=""/>
        </map>
        <map name="Map9" id="Map9">

            <area shape="rect" coords="20,8,51,21" href=""/>


            <area shape="rect" coords="76,6,101,21" href="#" />
            <area shape="rect" coords="129,6,184,21" href="${pageContext.request.contextPath}/login/LocateUs.jsp" target="right" />
        </map>
    </body>
</html>
<!--        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                

                <td><table id="Table_" width="100%" height="73" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="567"><img src="${pageContext.request.contextPath}/images/headerImages/Heade_Top_01.jpg" width="527" height="37" /></td>
                            <td width="527" rowspan="2" valign="middle" align="right" style="padding-right:25px ">
                    &nbsp;
                            </td>
                            <td width="37" align="right" valign="top"><img src="${pageContext.request.contextPath}/images/headerImages/Heade_Top_03.jpg" width="11" height="37"/></td>
                            <td width="266" align="right" valign="middle" style="background-image:url(${pageContext.request.contextPath}/images/headerImages/Heade_Top_04.jpg);">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                   <tr>
                                        <td align="center"  style=" padding:2px 0 0 3px"><a target="_top" href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/images/headerImages/login.gif" width="32" height="13" border="0" /></a></td>
                                    </tr>
                            </table></td>
                            <td width="28" rowspan="2" align="left" valign="top"><img src="${pageContext.request.contextPath}/images/headerImages/Heade_Top_05.jpg" width="12" height="37" /></td>
                        </tr>
                        <tr>
                            <td><img src="${pageContext.request.contextPath}/images/headerImages/Heade_Top_06.jpg" width="527" height="36" /></td>
                            <td colspan="2" align="right" valign="top"><img src="${pageContext.request.contextPath}/images/headerImages/top_links_1.gif" width="129" height="22" border="0" usemap="#Map9" /></td>
                        </tr>
                </table></td>
            </tr>  
            <tr>
                <td><table id="Table_01" width="100%" height="45" border="0" cellpadding="0" cellspacing="0" background="${pageContext.request.contextPath}/images/headerImages/Header_Bottom_17.jpg" style="background-repeat: repeat-x ">
                        <tr>    
                            <td width="9" align="left"><img src="${pageContext.request.contextPath}/images/headerImages/Header_Bottom_0.jpg" width="9" height="45" alt=""/></td>
                            <td align="right" background="${pageContext.request.contextPath}/images/headerImages/Header_Bottom_09.jpg" class="date"><fmt:formatDate pattern="dd-MM-yyyy" value="${now}" /></td>
                            <td width="8" align="right"><img src="${pageContext.request.contextPath}/images/headerImages/Header_Bottom_19.jpg" width="8" height="45" alt=""/></td>
                        </tr>
                </table></td>
            </tr>
        </table>-->


