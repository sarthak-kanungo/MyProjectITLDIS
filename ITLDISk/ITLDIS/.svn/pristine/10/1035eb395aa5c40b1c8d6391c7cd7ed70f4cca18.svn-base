<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="viewEcat.comEcat.ConnectionHolder,java.sql.Connection,java.io.*,com.EAMG.common.*,EAMG.PartDAO_CUD.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String context = request.getContextPath();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            String contextPath = request.getContextPath();
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84;
            int noofcomp = Integer.parseInt("" + request.getAttribute("noofcomp"));
            //${NoOfComp};
%>

<html:html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <script type="text/javascript" src="<%=imagesURL%>js/suggestions.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=imagesURL%>js/autosuggest.css" />
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <script type="text/javascript">
            var valueArr=new Array();
            var paramDomainTypeArr=new Array();
            var paramValueTypeArr=new Array();
            var paramIdArray=new Array();
            var multiListArr=new Array();
            var rowLength='<%=noofcomp%>';

            function addRow()
            {
                var oRows = document.getElementById('myTableAddPart').getElementsByTagName('tr');
                rowLength=oRows.length-1;
                //alert(document.getElementById('myTableAddPart').rows.length);
                var x=document.getElementById('myTableAddPart').insertRow(rowLength);
                var addPartCount=document.getElementsByName("group_remarks").length;
                

                //alert(document.getElementById('myTableAddPart').rows[7].cells[0].innerHTML);
                x.bgColor="white";
                x.className = 'tableactive';
                var ch=x.insertCell(0);
                ch.innerHTML="<div align='center'><font class='text' ><input type='hidden' name='index' value='"+(rowLength)+"'/>"+(addPartCount+1)+"</font></div>";

                var ch=x.insertCell(1);
                ch.innerHTML="<div align='center' ><font class='text' ><input type ='text' name='seqeno'  size='5' value='' /></font></div>";

                var b=x.insertCell(2);
                b.innerHTML="<div align='center' ><font class='text'><input type='text' id='comp_no_text"+rowLength+"' name='comp_no_text' value=''  style='width:150px' />&nbsp;<a href='#'><img src='/AMW-AuthEcat/auth_images/arrdown.gif' alt='Get Suggestions' border='0' onclick='getSuggestions(\"comp_no_text"+rowLength+"\",document.getElementById(\"comp_no_text"+rowLength+"\"),document.getElementById(\"comp_type"+rowLength+"\"),document.getElementById(\"img"+rowLength+"\"));'/><img  id='img"+rowLength+"' style='visibility:hidden;' border='0' src='/AMW-AuthEcat/auth_images/load.gif'/></a></font></div>";

                b=x.insertCell(3);
                b.innerHTML="<div align='center' ><font class='text'><input type='text' id='quantity"+rowLength+"' name='quantity' value='' size='6'/><input type='hidden'  name='comp_type' id='comp_type"+rowLength+" value='PRT'/></font></div>";

                b=x.insertCell(4);
                b.innerHTML="<div align='center' ><font class='text'><input type='text' id='group_remarks"+rowLength+"' name='group_remarks' value='' size='6'/></font></div>";


                document.getElementById('comp_count').value=rowLength;
            }

            //End

            function validate(dis)
            {
                var seqNoArr=document.getElementsByName("seqeno");
                var compNoArr=document.getElementsByName("comp_no_text");
                var quantityArr=document.getElementsByName("quantity");
                var groupRemarkArr=document.getElementsByName("group_remarks");

                for(var k=0;k<seqNoArr.length;k++)
                {
                    if(seqNoArr[k].value.trim()=="")
                    {
                        alert("SeqNo CanNot be blank");
                        seqNoArr[k].focus();
                        return false;
                    }
                    else if(compNoArr[k].value.trim()=="")
                    {
                        alert("Component CanNot be blank");
                        compNoArr[k].focus();
                        return false;
                    }
                    else if(quantityArr[k].value.trim()=="")
                    {
                        alert("Quantity CanNot be blank");
                        quantityArr[k].focus();
                        return false;
                    }

                    else if(!checkQuantity(quantityArr[k].value))
                    {
                        alert("Quantity Should Be Numeric");
                        quantityArr[k].value="";
                        quantityArr[k].focus();
                        return false;
                    }
                    else if(groupRemarkArr[k].value.trim()=="")
                    {
                        alert("GroupRemark CanNot be blank");
                        groupRemarkArr[k].focus();
                        return false;
                    }
                }
            }


            function checkParamValues(obj,i)
            {
                var val_obj=obj;
                var param=paramDomainTypeArr[i];

            }
            var paramCol='';
            var partArr=new Array();
            var asmArr=new Array();

            function getComponentList(comptype)
            {

                var res='';
                var strURL ="/AMW-AuthEcat/authJSP/amw_Part/amw_get_component_list.jsp?compno=*";
                xmlHttp = GetXmlHttpObject();
                xmlHttp.onreadystatechange = function()
                {
                    res=stateChanged();

                };
                xmlHttp.open("POST", strURL, true);
                xmlHttp.send(null);

            }
            function stateChanged()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    res = xmlHttp.responseText;
                    var index1 = res.indexOf("<listdata>");
                    var index2 = (index1 == -1) ? res.length : res.indexOf("</listdata>", index1 + 10);
                    if(index1 == -1)
                        index1 = 0;
                    else
                        index1 += 10;

                    var tmpinfo = res.substring(index1, index2);
                    partArr=tmpinfo.split("|");
                }
            }
            function GetXmlHttpObject()
            {
                var objXmlHttp = null;
                if (navigator.userAgent.indexOf('Opera') >= 0)
                {
                    xmlHttp=new XMLHttpRequest();
                    return xmlHttp;
                }
                if (navigator.userAgent.indexOf('MSIE') >= 0)
                {
                    var strName = 'Msxml2.XMLHTTP';
                    if (navigator.appVersion.indexOf('MSIE 5.5') >= 0)
                    {
                        strName = 'Microsoft.XMLHTTP';
                    }
                    try
                    {
                        objXmlHttp = new ActiveXObject(strName);
                        // objXmlHttp.onreadystatechange = handler;
                        return objXmlHttp;
                    }
                    catch(e)
                    {
                        alert('Your Browser Does Not Support Ajax');
                        return;
                    }
                }
                if (navigator.userAgent.indexOf('Mozilla') >= 0)
                {
                    objXmlHttp = new XMLHttpRequest();
                    return objXmlHttp;
                }
            }
            function validateComponent(compno,comptype)
            {

                var flag=false;
                if(comptype=='PRT')
                {
                    for(i=0;i<partArr.length;i++)
                    {
                        if(partArr[i]==compno)
                        {
                            flag=true;
                            break;
                        }
                    }

                }
                else
                {
                    for(i=0;i<asmArr.length;i++)
                    {
                        if(asmArr[i]==compno)
                        {
                            flag=true;
                            break;
                        }
                    }

                }
                return flag;
            }
           
            function CancelProcess()
            {
                //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                //if(cncl==true)
                {
                    location.href="<%=context%>/authJSP/EAMG_Group/EAMG_Create_Group.jsp";
                }

            }
            function selectMultiList(obj,id) {

                var paramVal = "";
                if(obj.value==''){
                    document.getElementById(id).value='';
                }else{
                    for (var i = 0; i < obj.length; i++) {
                        if (obj.options[i].selected) {
                            paramVal +=  obj.options[i].value+",";
                        }
                    }
                    document.getElementById(id).value=paramVal;
                }
            }


        </script>

    </head>

    <body onload="getComponentList('PRT');">
        <%

                    String tdData = "CREATION >> MANAGE GROUP >> ADD NEW GROUP >> ATTACH COMPONENT TO GROUP";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);

                    heading = "ATTACH COMPONENT TO GROUP";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
               <%-- <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Creation >> Manage Group >>Add New Group >> Attach Component To Group</b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td align="right" class="small"><b>Step 2 OF 4</b></td>
                </tr>
                <tr>
                    <td valign="top">&nbsp;</td>
                </tr>
                <html:errors/>
                <%--<tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;ATTACH COMPONENT TO GROUP</b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <html:form styleId="amw_GroupCreationByWzActionForm" action="/amw_GroupCreationByWzAction.do"  onsubmit="return validate(this);">
                                        <br/>
                                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td colspan="3"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                                                        <tr>
                                                            <td height="45" valign="middle" align="left" class="Lightblue"><b>&nbsp;<span class="heading">Group Number: </span><span class="heading"><bean:write name="amw_GroupCreationByWzActionForm" property="groupname"/></span><br>&nbsp;<span class="heading">Group Description: </span><span class="heading"><bean:write name="amw_GroupCreationByWzActionForm" property="groupdesc"/></span></b><br></td>
                                                        </tr>

                                                    </table></td>
                                            </tr>
                                            <tr>
                                                <td align="center">
                                                    <table id="myTableAddPart" width="100%" border="0" cellpadding="1" cellspacing="1" bordercolor="#333333" bgcolor="#000000">
                                                        <tr>
                                                            <td align="center" class="Lightblue"><strong class="text">S.No</strong></td>
                                                            <td  align="center" class="Lightblue"><strong class="text">Seq. No</strong></td>
                                                            <td align="center" class="Lightblue"><strong class="text">Component Number </strong></td>
                                                            <td align="center" class="Lightblue"><strong class="text">Qty</strong></td>
                                                            <td align="center" class="Lightblue"><strong class="text">G_Remarks</strong></td>
                                                        </tr>
                                                        <c:forEach var="i" begin="1" end="${requestScope.noofcomp}" varStatus="status">
                                                            <tr>
                                                                <td align="center" bgcolor="#FFFFFF" class="text"><input type="hidden"  name="index" value=""/>${status.count}</td>
                                                                <td align="center" bgcolor="#FFFFFF" class="text"><html:text size="5" property="seqeno"/></td>
                                                                <td  align="center" bgcolor="#FFFFFF" class="text"><html:text  property="comp_no_text" styleId="comp_no_text${status.count}" styleClass="width:100px"/>&nbsp;<a href='#'><img src='/AMW-AuthEcat/auth_images/arrdown.gif' border='0' alt='Get Suggestions'  onclick='getSuggestions("comp_no_text${status.count}",document.getElementById("comp_no_text${status.count}"),document.getElementById("comp_type${status.count}"),document.getElementById("img${status.count}"));'/><img alt='' id='img${status.count}' style='visibility:hidden;' border='0' src='/AMW-AuthEcat/auth_images/load.gif'/></a></td>
                                                                <td  align="center" bgcolor="#FFFFFF" class="text"><html:text  size="6" property="quantity" styleId="Quantity" /></td>
                                                                <td  align="center" bgcolor="#FFFFFF" class="text"><html:text  size="6" property="group_remarks" styleId="Group_Remarks"/><html:hidden  property="option" value="AttachParts"/><input type="hidden"  name="comp_type" id="comp_type${status.count}" value="PRT"/></td>
                                                            <tr>
                                                        </c:forEach>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr><td> </td></tr>
                                            <tr><td colspan= align="center">
                                                    <table width="100%">
                                                        <tr>
                                                            <td colspan="2" align="center">
                                                                <input type="button" value="Add New Part" onclick="javascript:addRow();" />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td width="50%" align="right">
                                                                <input type="submit" value="Submit" style="width:70px;"/>
                                                            </td>
                                                            <td width="50%" align="left">
                                                                <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
                                                            </td>
                                                        </tr>
                                                        <tr><td class="red">* Put AR in Quantity for As Required.</td></tr>
                                                        <%-- <input type="hidden" id="comp_count" name="comp_count" value="${requestScope["noofcomp"]}"/>
                                                        <input type="hidden" name="groupname"  value="${requestScope["groupno"]}"/> --%>
                                                        <input type="hidden" id="comp_count" name="comp_count" value="<c:out value="${requestScope.noofcomp}" />"/>
                                                        <input type="hidden" name="groupname"  value="<c:out value="${requestScope.groupno}" />"/>
                                                    </table>
                                                </td></tr>
                                        </table>
                                    </td>
                                </tr>

                            <%--</table>
                        </td>
                    </tr>--%>
                </table>
            </div>
            <%
                        out.println(object_pageTemplate.tableFooter());
            %>
        </body>
    </html:form>
</html:html>



