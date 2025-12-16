<%--
    Document   : ${vijaymishra}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8"  />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
       
    </head>
    <body>


    <div>

        <table  width="95%" border="0" align="center" cellpadding="3" cellspacing="3"  class="LiteBorder" >
            <tbody>
                <tr>
                    <td align="left" style="font-weight:bold; " > <label><bean:message key="label.common.jobcardno" />:</label></td>
                    <td align="left" >
                        ${serviceform.jobCardNo}</td>
                    <td align="left"  style="font-weight:bold; width: 170px;" >
                        <label><bean:message key="label.common.Jobtype" /> :</label></td>
                    <td align="left"  >${serviceform.jobTypeDesc}</td>

            <td align="left" style="font-weight:bold; width: 250px;" >
                        <label><bean:message key="label.common.warrantyapplicable" /> :</label></td>
                    <td align="left"  >
                        
                    <c:choose>
                        <c:when test="${serviceform.warrantyApplicable eq 'Y'}">
                            Yes
                        </c:when>
                         <c:when test="${serviceform.warrantyApplicable eq 'N'}">
                            Novvvvv
                        </c:when>
                            <c:otherwise>
                                No
                            </c:otherwise>
                    </c:choose>
                    </td>

                </tr>
                <tr>
                    <td align="left" style="font-weight:bold"  >
                        <label><bean:message key="label.common.Productcategory" /> :</label></td>
                    <td align="left"  >${serviceform.productionCategory_Desc}</td>

                    <td align="left" style="font-weight:bold"  >
                        <label><bean:message key="label.common.Chassisno" /> :</label>
                    </td>
                    <td align="left"  >
                        ${serviceform.vinNo}</td>
                    <td align="left" style="font-weight:bold" >
                        <label><bean:message key="label.common.engineno" /> :</label></td>
                    <td align="left"  >
                        ${serviceform.engineNumber}</td>

                </tr>


            </tbody>
        </table>

    </div>



    </body>
</html>
