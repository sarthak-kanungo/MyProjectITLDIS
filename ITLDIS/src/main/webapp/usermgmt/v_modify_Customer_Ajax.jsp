<%-- 
    Document   : v_modify_Customer_Ajax
    Created on : Feb 21, 2014, 4:43:37 PM
    Author     : sreeja.vijayakumaran
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%String cntxpath = request.getContextPath();%>
<div class="contentmain1">
    <div class="content_right1">
        <div class="con_slidediv1" style="position: relative;width: 100%">
            <c:if test = "${result eq 1}">
                <table width="100%" border="0" cellpadding="1" cellspacing="5" >
                    <form action="UserManagement.do?option=updatecustomer" method="post" onsubmit="return validateForm();">
                        <input type="hidden" name="upperLink" value="<a href='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a><a HREF='<%=cntxpath%>/UserManagement.do?option=initManageCust'>MANAGE CUSTOMER</a>@@MODIFY CUSTOMER"/>
                        <tr bgcolor="#FFFFFF">
                            <td align="center" bgcolor="#EEEEEE">CUSTOMER DETAIL</td>
                        </tr>
                        <tr>
                            <td ><table border="0" cellspacing="1" width="100%" class="tableStyle">
                                    <tr height="20">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Customer Name:</td>
                                        <td style="padding-left:10px"  bgcolor="#FFFFFF" align="left">
                                            <input type="hidden" name="id" value="${userForm.id}"/>
                                            <input type="text" name="name"  id="Customer Name" maxlength="100" class="headingSpas"  style="width:170px" value="${userForm.name}" >
                                        </td>
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Status<%--</td>--%>
                                            <%--  <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">--%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <select name="status" id="status" class="headingSpas" onChange="findselected(this.value)" style="width:177px">
                                                <option value="">--Select Here--</option>
                                                <%--  <option value="Y">Active</option>
                                                  <option value="N">Inactive</option>--%>
                                                <c:choose>
                                                    <c:when test="${userForm.status eq 'Y'}">
                                                        <option selected value="Y">Active</option>
                                                        <option  value="N">Inactive</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option  value="Y">Active</option>
                                                        <option selected value="N">Inactive</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Address:</td>
                                        <td style="padding-left:10px" colspan="" bgcolor="#FFFFFF" align="left">
                                            <textarea name="address1"  id="Address" maxlength="255" class="headingSpas"  style="width:170px;height: 35px">${userForm.address1}</textarea>
                                        </td>
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Contact Person:&nbsp;&nbsp;<%--</td>--%>
                                            <%-- <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">--%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="text" name="firstName"  id="Contact Person" class="headingSpas" maxlength="100" style="width:170px" value="${userForm.firstName}"/>
                                        </td>
                                    </tr>
                                    <tr height="20" bgcolor="#FFFFFF">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">City:</td>
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                            <select name="city" id="City" style="width:177px" class="headingSpas" onchange="checkValueSelect(this,'citySpan','Other City');">
                                                <option value="">--Select Here--</option>

                                                <c:forEach items="${userForm.labelList}" var="cityTemp">
                                                    <c:choose>
                                                        <c:when test="${fn:toUpperCase(userForm.city) eq fn:toUpperCase(cityTemp.value)}">
                                                            <option selected value="${cityTemp.value}">${cityTemp.label}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option  value="${cityTemp.value}">${cityTemp.label}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                                <option value="Other">Other</option>
                                            </select>
                                        </td>
                                        <td width="50%" align="left"  >
                                            <span id="citySpan" style="display:none;"><input type="text" name="cityOther"  maxlength="20" id="Other City" style="width:170px"/></span>
                                        </td>
                                    </tr>
                                    <tr height="20" bgcolor="#FFFFFF">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">State:</td>
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                            <select name="state" id="State" style="width:177px" class="headingSpas" onchange="checkValueSelect(this,'stateSpan','Other State');">
                                                <option value="">--Select Here--</option>
                                                <c:forEach items="${userForm.labelList1}" var="stateTemp">
                                                    <c:choose>
                                                        <c:when test="${fn:toUpperCase(userForm.state) eq fn:toUpperCase(stateTemp.value)}">
                                                            <option selected value="${stateTemp.value}">${stateTemp.label}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option  value="${stateTemp.value}">${stateTemp.label}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                                <option value="Other">Other</option>
                                            </select>
                                        </td>
                                        <td align="left">
                                            <span id="stateSpan" style="display:none;"><input type="text" name="stateOther" maxlength="30" id="Other State" style="width:170px"/></span>

                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Pin Code:</td>
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="" align="left">
                                            <input type="text" name="pincode"  id="Pin Code" class="headingSpas" maxlength="6" style="width:170px" value="${userForm.pincode}"/>
                                        </td>
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Email:<%--</td>
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">--%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="text" name="email"  id="Email" class="headingSpas" maxlength="50" style="width:170px" value="${userForm.email}"/>
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Country:</td>
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                            <select name="country" id="Country" style="width:177px" class="headingSpas" onchange="checkValueSelect(this,'countrySpan','Other Country');">
                                                <option value="">--Select Here--</option>
                                                <c:forEach items="${userForm.labelList2}" var="countryTemp">
                                                    <c:choose>
                                                        <c:when test="${fn:toUpperCase(userForm.country) eq fn:toUpperCase(countryTemp.value)}">
                                                            <option selected value="${countryTemp.value}">${countryTemp.label}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option  value="${countryTemp.value}">${countryTemp.label}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                                <option value="Other">Other</option>
                                            </select>
                                        </td>
                                        <td bgcolor="#FFFFFF" align="left">
                                            <span id="countrySpan" style="display:none;"><input type="text" name="countryOther" maxlength="20" id="Other Country" style="width:170px"/></span>
                                        </td>
                                    </tr>
                                    <%-- <tr height="20">
                                         <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Contact Person</td>
                                         <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">
                                             <input type="text" name="firstName"  id="Contact Person" class="headingSpas" maxlength="100" style="width:170px" value="${userForm.firstName}"/>
                                         </td>
                                     </tr>--%>
                                    <tr height="20">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Contact No.:</td>
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="" align="left">
                                            <input type="text" name="mobile"  id="Contact No." class="headingSpas" maxlength="10" style="width:170px"value="${userForm.mobile}" />
                                        </td><td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Customer Type ID:<%--</td>--%>
                                            <%--  <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">--%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <select name="userTypeId" id="Customer Type ID" class="headingSpas" style="width:177px">
                                                <option value="">--Select Here--</option>
                                                <c:forEach items="${userForm.labelList3}" var="custtypeidTemp">
                                                    <c:choose>
                                                        <c:when test="${userForm.userTypeId eq custtypeidTemp.value}">
                                                            <option selected value="${custtypeidTemp.value}">${custtypeidTemp.label}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option  value="${custtypeidTemp.value}">${custtypeidTemp.label}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </td>

                                    </tr>
                                    <%-- <tr height="20">
                                         <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Email</td>
                                         <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                             <input type="text" name="email"  id="Email" class="headingSpas" maxlength="50" style="width:170px" value="${userForm.email}"/>
                                         </td>
                                     </tr>--%>
                                    <%--<tr height="20">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Customer Type ID</td>
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                            <select name="userTypeId" id="Customer Type ID" class="headingSpas" style="width:177px">
                                                <option value="">--Select Here--</option>
                                                <c:forEach items="${userForm.labelList3}" var="custtypeidTemp">
                                                    <c:choose>
                                                        <c:when test="${userForm.userTypeId eq custtypeidTemp.value}">
                                                            <option selected value="${custtypeidTemp.value}">${custtypeidTemp.label}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option  value="${custtypeidTemp.value}">${custtypeidTemp.label}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>--%>
                                    <tr height="20">
                                        <td colspan="3" style="padding-left:10px" align="center" bgcolor="#FFFFFF">
                                            <input type=submit name="submitBtn" class="headingSpas" id="submitBtn" value="Submit" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </form>
                </table>
            </c:if>
            <c:if test = "${result eq 0}">
                <table border="0" width="100%">
                    <tr><td align="center" colspan="5">
                            <b>Customer '${userForm.name}' does not exist in the Database.</b>
                        </td>
                    </tr>
                </table>
            </c:if>
        </div>
    </div>
</div>
