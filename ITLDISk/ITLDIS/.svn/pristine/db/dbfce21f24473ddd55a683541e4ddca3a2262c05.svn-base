<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String context = request.getContextPath();
            int noofcomp = Integer.parseInt("" + request.getAttribute("noofcomp"));
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            String contextPath = request.getContextPath();
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84, cnt = 0;
            ;

%>

<html:html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/suggestions.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=imagesURL%>js/autosuggest.css" />
        <link type="text/css" rel="stylesheet" href="<%=imagesURL%>css/dhtmlgoodies_calendar.css" media="screen" />
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/dhtmlgoodies_calendar.js"></script>
        <script type="text/javascript">
            var valueArr=new Array();
            var paramDomainTypeArr=new Array();
            var paramValueTypeArr=new Array();
            var paramIdArray=new Array();
            var multiListArr=new Array();
            var rowLength='<%=noofcomp%>';

            function addRow()
            {
                // var oRows = document.getElementById('myTableAddPart').getElementsByTagName('tr');
                var oRows = document.getElementById('myTableAddPart').rows.length;

                rowLength=oRows.length-1;
					 
                //alert(document.getElementById('myTableAddPart').rows.length);
                var x=document.getElementById('myTableAddPart').insertRow(-1);
                var addPartCount=document.getElementsByName("group_remarks").length;


                //alert(document.getElementById('myTableAddPart').rows[7].cells[0].innerHTML);
                x.bgColor="white";
                x.className = 'tableactive';
                var ch=x.insertCell(0);
                ch.innerHTML="<div align='center'><font class='text' ><input type='hidden' name='index' value='"+(rowLength)+"'/>"+(addPartCount+1)+"</font></div>";

                var ch=x.insertCell(1);
                ch.innerHTML="<div align='center' ><font class='text' ><input type ='text' name='seqeno'  size='5' value='' /></font></div>";

                var b=x.insertCell(2);
                b.innerHTML="<div align='center' ><font class='text'><input type='text' id='comp_no_text"+rowLength+"' name='comp_no_text' value=''  style='width:118px' />&nbsp;<a href='#'><img src='<%=imagesURL%>/arrdown.gif' alt='Get Suggestions' border='0' onclick='getSuggestionsGroup(\"comp_no_text"+rowLength+"\",document.getElementById(\"comp_no_text"+rowLength+"\"),document.getElementById(\"comp_type"+rowLength+"\"),document.getElementById(\"img"+rowLength+"\"));'/><img  id='img"+rowLength+"' style='visibility:hidden;' border='0' src='<%=imagesURL%>/load.gif'/></a></font></div>";

                var c=x.insertCell(3);
                c.innerHTML="<div align='center' ><font class='text'><input type='text' id='quantity"+rowLength+"' name='quantity' value='' size='4' maxlength=\"10\" onblur=\"checkingQty(this);\"/><input type='hidden'  name='comp_type' id='comp_type"+rowLength+" value='PRT'/></font></div>";

                var d=x.insertCell(4);
                d.innerHTML="<div align='center' ><font class='text'><input type=\"hidden\"  name=\"comp_type\" id=\"comp_type"+rowLength+"\" value=\"PRT\"/><input type='text' id='group_remarks"+rowLength+"' name='group_remarks' value='' size='20'/></font></div>";
                
                var e=x.insertCell(5);
                e.innerHTML="<div align='center' ><font class='text'><input type='text' id='Interchangeability"+rowLength+"' name='interchangeability' value='' size='20'/></font></div>";

                var f=x.insertCell(6);
                f.innerHTML="<div align='center' ><font class='text'><input type='text' id='Cutoffchassis"+rowLength+"' name='cutoffchassis' value='' size='15'/></font></div>";

                var g=x.insertCell(7);
                g.innerHTML="<div align='center' ><font class='text'><input type='text' id='cutoff"+rowLength+"' name='cutoff'   style='width:70px' readonly=\"readonly\" />&nbsp;<img  id='effectiveDateCalImg"+rowLength+"'  src='<%=imagesURL%>/showCalendar.jpg' alt='calendar' width=20 height=20 onclick='displayCalendar(document.getElementById(\"cutoff"+rowLength+"\"),\"dd/mm/yyyy\",this)';\"/></font></div>";


                document.getElementById('comp_count').value=rowLength;
            }

            //End

            function validate(dis)
            {
                var seqNoArr=document.getElementsByName("seqeno");
                var compNoArr=document.getElementsByName("comp_no_text");
                var quantityArr=document.getElementsByName("quantity");
                var groupRemarkArr=document.getElementsByName("group_remarks");
                var interchangeabilityArr=document.getElementsByName("interchangeability");
                var cutoffchassisArr=document.getElementsByName("cutoffchassis");
                var cutoffArr=document.getElementsByName("cutoff");

                for(var k=0;k<seqNoArr.length;k++)
                {
                    if(seqNoArr[k].value.trim()=="")
                    {
                        alert("Seq No. can not be blank");
                        seqNoArr[k].focus();
                        return false;
                    }
                    res=isProperAll(seqNoArr[k].value);
                    if(res==false)
                    {
                        alert("Sequence No. can not contain any special characters.");
                        seqNoArr[k].value="";
                        seqNoArr[k].focus();
                        return false;
                    }
                    if(compNoArr[k].value.trim()=="")
                    {
                        alert("Component can not be blank");
                        compNoArr[k].focus();
                        return false;
                    }
                    res=isProperComponent(compNoArr[k].value);
                    if(res==false)
                    {
                        alert('Component can not contain any special characters.');
                        compNoArr[k].value='';
                        compNoArr[k].focus();
                        return false;
                    }
                    if(quantityArr[k].value.trim()=="")
                    {
                        alert("Quantity can not be blank");
                        quantityArr[k].focus();
                        return false;
                    }
                    if(!isSpecialchar(quantityArr[k]))
                    {
                        if(quantityArr[k].value.toUpperCase()!="AR")
                        {
                            alert("Quantity should not contain any special characters.");
                            quantityArr[k].value="";
                            quantityArr[k].focus();
                            return false;
                        }
                    }
                    if(groupRemarkArr[k].value.trim()=="")
                    {
                        alert("Group Remark can not be blank");
                        groupRemarkArr[k].focus();
                        return false;
                    }
                    res=isProperAll(groupRemarkArr[k].value);
                    if(res==false)
                    {
                        alert('Group Remark can not contain any special characters.');
                        groupRemarkArr[k].value='';
                        groupRemarkArr[k].focus();
                        return false;
                    }
                    if(interchangeabilityArr[k].value.trim()!="")
                    {
                        if(interchangeabilityArr[k].value!='YES' || interchangeabilityArr[k].value!='NO')
                        alert("Interchangeability can contain only 'YES' or 'NO' ");
                        interchangeabilityArr[k].focus();
                        return false;
                    }
                    <%--if(cutoffchassisArr[k].value.trim()=="")
                    {
                        alert("cutoffchassis can not be blank");
                        cutoffchassisArr[k].focus();
                        return false;
                    }--%>
                    <%--if(cutoffArr[k].value.trim()=="" )
                    {
                        alert("cutoff can not be blank");
                        cutoffArr[k].focus();
                        return false;
                    }--%>
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

            function checkingQty(Obj) {
                if(Obj.value!='' && !isSpecialchar(Obj))
                {
                    if(Obj.value.toUpperCase()!="AR")
                    {
                        alert("Quantity should not contain any special characters.");
                        //Obj.value="";
                        return false;
                    }
                }
            }
            /*
             *@author tarun.lal
             *
             *@date 23/12/2014
             *
             * Used to validate Alpha numeric with space and dots Values.
             */
            function isSpecialchar(obj){
                var regexp=/^[a-zA-Z0-9. ]+$/;
                if (!regexp.test(obj.value)) {
                    return false;
                }else{
                    return true;
                }
            }
            function CancelProcess()
            {
                // var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                //if(cncl==true)
                {
                    parent.content.location="<%=context%>/authJSP/EAMG_Group/EAMG_group_creation_wz.jsp";
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

    <body>
        <%
                    String tdData = "MANAGE " + PageTemplate.GROUP.toUpperCase() + " >>ADD NEW " + PageTemplate.GROUP.toUpperCase() + " >> ATTACH COMPONENT TO " + PageTemplate.GROUP.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "ATTACH PART TO TABLE";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=PageTemplate.GROUP%>>>Add New <%=PageTemplate.GROUP%> >> Attach Component To <%=PageTemplate.GROUP%></b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td align="right" class="small"><b>Step 3 OF 3</b></td>
                </tr>

                <html:errors/>
                <%--<tr>
                    <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;ATTACH PART TO <%=PageTemplate.GROUP.toUpperCase()%></b></td>
                            </tr>--%>
                <tr>
                    <td align="center" valign="top" bgcolor="#FFFFFF">
                        <html:form styleId="EAMG_GroupCreationByWzActionForm" action="/EAMG_GroupCreationByWzAction.do"  onsubmit="return validate(this);">
                            <br/>
                            <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                <tr>
                                    <td colspan="3"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                                            <tr>
                                                <td height="25" valign="middle" align="left" class="blue"><b>&nbsp;<span class="heading"><%=PageTemplate.GROUP%> Number: </span><span class="heading"><bean:write name="EAMG_GroupCreationByWzActionForm" property="groupname"/></span></b><br></td>
                                            </tr>

                                        </table></td>
                                </tr>
                                <tr><td height="10"></td></tr>
                                <tr><td><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                                            <tr>
                                                <td height="25" valign="middle" align="left" class="blue"><b>&nbsp;<span class="heading">BOM Details </span></b></td>
                                            </tr>
                                        </table></td></tr>
                                <tr>
                                    <td align="center">
                                        <table id="myTableAddPart" width="100%" border="1" cellpadding="1" cellspacing="1" bordercolor="#cccccc" bgcolor="#000000">
                                            <tr>
                                                <td align="center" class="lightgrey"><strong class="text">S.No</strong></td>
                                                <td  align="center" class="lightgrey"><strong class="text">&nbsp;Seq.No</strong></td>
                                                <td align="center" class="lightgrey"><strong class="text">Component Number</strong></td>
                                                <td align="center" class="lightgrey"><strong class="text">Qty</strong></td>
                                                <td align="center" class="lightgrey"><strong class="text">G_Remarks</strong></td>
                                                <td align="center" class="lightgrey"><strong class="text">Interchangeability</strong></td>
                                                <td align="center" class="lightgrey"><strong class="text">Cutoffchassis</strong></td>
                                                <td align="center" class="lightgrey"><strong class="text">&nbsp;&nbsp;Cutoff&nbsp;&nbsp;</strong></td>
                                            </tr>
                                            <c:forEach var="i" begin="1" end="${requestScope.noofcomp}" varStatus="status">
                                                <% cnt = cnt + 1;%>
                                                <tr>
                                                    <td align="center" bgcolor="#FFFFFF" class="text" ><input type="hidden"  name="index" value=""/>${status.count}</td>
                                                    <td align="left" bgcolor="#FFFFFF" class="text" ><html:text size="5" property="seqeno"/></td>
                                                    <td  align="left" bgcolor="#FFFFFF" class="text" ><table cellspacing=0 cellpadding=0 border=0><tr><td><html:text  property="comp_no_text" styleId="comp_no_text${status.count}" styleClass="width:20px;"/></td><td>&nbsp;<a href='#'><img src='<%=imagesURL%>/arrdown.gif' border='0' alt='Get Suggestions'  onclick='getSuggestionsGroup("comp_no_text${status.count}",document.getElementById("comp_no_text${status.count}"),document.getElementById("comp_type${status.count}"),document.getElementById("img${status.count}"));'/></a></td><td><img alt='' id='img${status.count}' style='visibility:hidden;' border='0' src='<%=imagesURL%>/load.gif'/></td></tr></table>
                                                    <td  align="left" bgcolor="#FFFFFF" class="text" ><html:text  size="4" property="quantity" styleId="Quantity" maxlength="10" onblur="checkingQty(this);" /></td>
                                                    <td  align="left" bgcolor="#FFFFFF" class="text" ><html:text  size="20" property="group_remarks" styleId="Group_Remarks"/><html:hidden  property="option" value="AttachParts"/><input type="hidden"  name="comp_type" id="comp_type${status.count}" value="PRT"/></td>
                                                    <td  align="left" bgcolor="#FFFFFF" class="text" ><html:text  size="20" property="interchangeability" styleId="Interchangeability" /></td>
                                                    <td  align="left" bgcolor="#FFFFFF" class="text" ><html:text  size="15" property="cutoffchassis" styleId="Cutoffchassis"/><html:hidden  property="option" value="AttachParts"/><input type="hidden"  name="comp_type" id="comp_type${status.count}" value="PRT"/></td>
                                                    <td  align="left" bgcolor="#FFFFFF" class="text" >
                                                        <input type="text" id="cutoff<%=cnt%>" name='cutoff'  readonly="readonly" style="width:70px"><img alt="calendar" id="effectiveDateCalImg" src='<%=imagesURL%>/showCalendar.jpg' width=20 height=20 onclick="displayCalendar(document.getElementById('cutoff<%=cnt%>'),'dd/mm/yyyy',this);" />
                                                    </td>

                                                </tr>
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
                                            <%-- <input type="hidden" id="comp_count" name="comp_count" value="${requestScope["noofcomp"]}"/> --%>
                                            <input type="hidden" id="comp_count" name="comp_count" value="<c:out value="${requestScope.noofcomp}" />"/>
                                            <%-- <input type="hidden" name="groupname"  value="${requestScope["groupno"]}"/> --%>
                                            <input type="hidden" name="groupname" value="<c:out value="${requestScope.groupno}" />"/>
                                        </table>
                                    </td></tr>
                            </table>
                        </td>
                    </tr>

                    <%-- </table>
                 </td>
             </tr>--%>
                </table>
            </div>
        </body>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </html:form>
</html:html>






