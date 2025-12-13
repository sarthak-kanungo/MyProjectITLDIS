<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,EAMG_MethodsUtility_Package.*,java.util.*" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.*,java.text.SimpleDateFormat,viewEcat.comEcat.ConnectionHolder,viewEcat.comEcat.PageTemplate" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String context = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String contextPath = request.getContextPath();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84;
%>

<%
            String grp_no = "" + request.getParameter("group_no");
            Connection conn = null;
            ArrayList grp_detail = null;
            String grp_remarks = null;
            String interchangebility = null;
            String cut_of_chassis = null;
            String cut_of = null;
            boolean ecnImplemented = false;
            try {
                conn = holder.getConnection();
                ResultSet rs = null;
                Statement stmt = null;
                ArrayList changedData = new ArrayList();
                stmt = conn.createStatement();

                /*  rs = stmt.executeQuery("select GROUP_ASSY_NO from CAT_ECN_IMPL_HISTORY where GROUP_ASSY_NO='" + grp_no + "'");
                if (rs.next()) {
                ecnImplemented = true;
                }
                stmt.close();
                 */
                grp_detail = new EAMG_MethodUtility2().grp_detail(conn, grp_no);
                int s = 0;
                int rows = grp_detail.size();
                int cnt = 0;

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript" src="<%=imagesURL%>js/suggestions.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=imagesURL%>/js/autosuggest.css" />
        <link type="text/css" rel="stylesheet" href="<%=imagesURL%>css/dhtmlgoodies_calendar.css" media="screen" />
        <script type="text/javascript" src="<%=imagesURL%>js/dhtmlgoodies_calendar.js"></script>
        <script type="text/javascript">

            var valueArr=new Array();
            //////////////////////////////dynamic function for checking parameter value type//////////////////
            var paramDomainTypeArr=new Array();
            var paramValueTypeArr=new Array();
            var paramIdArray=new Array();
            var multiListArr=new Array();
            var rowLength='<%=rows%>';
            
            var insertArray=new Array();
            for(k=0;k<rowLength;k++)
            {
                insertArray[k]='true';
            }
            var index=rowLength;


            function addRow(i)
            {
                var fixedCol='8';
                var oRows = document.getElementById('myTable1').getElementsByTagName('tr');
                rowLength=oRows.length
                var x=document.getElementById('myTable1').insertRow(rowLength);
                x.bgColor="white";
                x.className = 'tableactive';
                var ch=x.insertCell(0);

                ch.innerHTML="<div align='center' style='width:34px'><font class='text' >"+(rowLength)+"</font></div>";

                var ch=x.insertCell(1);
                ch.innerHTML="<div align='center' ><font class='text' ><input type = checkbox align=left name='chk0' id='chk0"+rowLength+"' value='"+rowLength+"' /></font></div>";

                var seqNo=x.insertCell(2);
                seqNo.innerHTML="<div align='center' ><font class='text'><input type = text align=left id='seq_no"+rowLength+"' name='seq_no' size='2' value='"+(rowLength)+"'  maxlength=5/></font></div>";

                b=x.insertCell(3);
                b.innerHTML="<div align='center' ><font class='text'><input type='text' id='comp_no_text"+rowLength+"' name='testParam0' value='' style='width:100px' maxlength=200/>&nbsp;<a href='#'><img src='<%=imagesURL%>/arrdown.gif' alt='Get Suggestions' style='width:15px' border='0' onclick='getSuggestionsGroup(\"comp_no_text"+rowLength+"\",document.getElementById(\"comp_no_text"+rowLength+"\"),document.getElementById(\"comp_type"+rowLength+"\"),document.getElementById(\"img"+rowLength+"\"));'/><img  id='img"+rowLength+"' style='visibility:hidden;width:15px' border='0' src='<%=imagesURL%>/load.gif'/></font></a></div>";

                b=x.insertCell(4);
                b.innerHTML="<div align='center' ><font class='text'><input id='text"+rowLength+"' type = 'text' size='4'  name='quan' value='' style='width:45px'  maxlength=\"10\" onblur=\"checkingQty(this);\" ></font></div>";
                       
                b=x.insertCell(5);
                b.innerHTML="<div align='center' ><font class='text'><input id='text"+rowLength+"' type = 'text' size='4'  name='groupremarks' value='' style='width:110px' maxlength=200 ><input type='hidden'  name='comp_type' id='comp_type"+rowLength+"' value='PRT'/></font></div>";

                b=x.insertCell(6);
                b.innerHTML="<div align='left' ><font class='text'><select class='text' id='interchangebility"+rowLength+"' name='interchangebility' value=''><option value=''>-Select-</option><option value='YES'>YES</option><option value='NO'>NO</option></select></font></div>";
  
                b=x.insertCell(7);
                b.innerHTML="<div align='center' ><font class='text'><input id='cut_of_chassis"+rowLength+"' type = 'text' size='4'  name='cut_of_chassis' value='' style='width:100px' maxlength=200 ></font></div>";

                b=x.insertCell(8);
                b.innerHTML="<div align='left' ><font class='text'><input id='cut_of"+rowLength+"' type = 'text' size='4'  name='cut_of' value='' style='width:80px' maxlength=200 ><img alt='calendar' id='effectiveDateCalImg' src='<%=imagesURL%>/showCalendar.jpg' width=20 height=20 border=0 onclick=\"displayCalendar(document.getElementById('cut_of"+rowLength+"'),'dd/mm/yyyy',this);\" /></font></div>";

            }
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

            function validate()
            {
                var checkArray=document.getElementsByName('chk0');
                var chCount=0;
                for(var k=0;k<checkArray.length;k++)
                {
                    if(checkArray[k].checked==false)
                        chCount++;
                }
                            
                if(chCount>0)
                {
                    var seqNoArr=document.getElementsByName("seq_no");
                    var compNoArr=document.getElementsByName("testParam0");
                    var quantityArr=document.getElementsByName("quan");
                    var groupRemarkArr=document.getElementsByName("groupremarks");
                    var chk0Arr=document.getElementsByName("chk0");
                    var cut_of_chassis=document.getElementsByName("cut_of_chassis");

                    for(var k=0;k<seqNoArr.length;k++)
                    {
                        if(chk0Arr[k].checked==false && seqNoArr[k].value.trim()=="")
                        {
                            alert("Seq No can not be blank");
                            seqNoArr[k].focus();
                            return false;
                        }
                        res=isProperAll(seqNoArr[k].value);
                        if(chk0Arr[k].checked==false && res==false)
                        {
                            alert("Seq No can not contain any special characters.");
                            seqNoArr[k].value="";
                            seqNoArr[k].focus();
                            return false;
                        }
                        if(chk0Arr[k].checked==false && compNoArr[k].value.trim()=="")
                        {
                            alert("Component can not be blank");
                            compNoArr[k].focus();
                            return false;
                        }
                        res=isProperComponent(compNoArr[k].value);
                        if(chk0Arr[k].checked==false && res==false)
                        {
                            alert('Component can not contain any special characters.');
                            compNoArr[k].value='';
                            compNoArr[k].focus();
                            return false;
                        }
                        if(chk0Arr[k].checked==false && quantityArr[k].value.trim()=="")
                        {
                            alert("Quantity can not be blank");
                            quantityArr[k].focus();
                            return false;
                        }  
                        if(chk0Arr[k].checked==false && !isSpecialchar(quantityArr[k]))
                        {
                            if(quantityArr[k].value.toUpperCase()!="AR")
                            {
                                alert("Quantity should not contain any special characters.");
                                quantityArr[k].value="";
                                quantityArr[k].focus();
                                return false;
                            }
                        }

                        if(groupRemarkArr[k].value!="")
                        {
                            var res=stringOnlyValidation(groupRemarkArr[k]);
                            if(chk0Arr[k].checked==false && res==false)
                            {
                                alert('<%=PageTemplate.GROUP%> Remark can not contain any special characters.');
                                groupRemarkArr[k].value='';
                                groupRemarkArr[k].focus();
                                return false;
                            }
                        }
                        if(chk0Arr[k].checked==false && (cut_of_chassis[k].value.indexOf(" ", 0)>0))
                        {
                            alert("Cut off Chassis cannot contain space.");
                            cut_of_chassis[k].value="";
                            cut_of_chassis[k].focus();
                            return false;
                        }

                    }
                }
                else
                {
                    alert("All the Components of a <%=PageTemplate.GROUP%> can not be deleted.");
                    return false;
                }
            }

            function CancelProcess()
            {
                // var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                // if(cncl==true)
                {
                    parent.content.location="<%=context%>/authJSP/EAMG_Group/EAMG_modify_group.jsp";
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

        </script>
    </head>
    <body>
        <%
                        String tdData = "MANAGE  TABLE >> MODIFY  " + PageTemplate.GROUP.toUpperCase();
                        object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                        heading = "MODIFY " + PageTemplate.GROUP.toUpperCase();
                        out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>


        <%
                        if (ecnImplemented) {
        %>
        <div align="center">
            <table width="740" border="0" cellspacing="1" cellpadding="1">

                <tr><td valign="top">
                        <table align="center" >
                            <tr>
                                <td valign="center" class="red" >
                                    <%=PageTemplate.GROUP%>'s BOM can't be modified as ECN of the <%=PageTemplate.GROUP%> has been implemented.
                                </td>
                            </tr>

                        </table>
                    </td>
                </tr>


            </table>
        </div>
        <%} else {%>
        <div align="center" >
            <table width="740" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td align="center" valign="top" bgcolor="#FFFFFF">
                        <form  name="creation" action="<%=context%>/grp_modify.do" method="post">
                            <br />

                            <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                <tr>
                                    <td colspan="3"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                                            <tr>
                                                <td height="25" valign="middle" align="left" class="blue"><b>&nbsp;<span class="heading"><%=PageTemplate.GROUP%> No: <%=grp_no%></span></b></td>
                                            </tr>

                                        </table>
                                    </td>
                                </tr>
                                <tr><td height="10"></td></tr>
                                <tr><td><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                                            <tr>
                                                <td height="25" valign="middle" align="left" class="blue"><b>&nbsp;<span class="heading">BOM Details </span></b></td>
                                            </tr>
                                        </table></td></tr>
                                <tr>
                                    <td align="center">
                                        <table id="myTable1" width="100%" border="0" cellpadding="1" cellspacing="1" bordercolor="#cccccc" bgcolor="#000000">
                                            <tr>
                                                <td align="center" class="lightgrey"><strong class="text">S.No</strong></td>

                                                <td  align="center" class="lightgrey"><strong class="text">Delete</strong></td>

                                                <td  align="center" class="lightgrey"><strong class="text">Seq.&nbsp;No</strong></td>

                                                <td align="left" class="lightgrey"><strong class="text">Component&nbsp;Number </strong></td>

                                                <td align="center" class="lightgrey"><strong class="text">Qty</strong></td>
                                                <td align="center" class="lightgrey" ><strong class="text">Group Remarks</strong> </td>
                                                <td align="center" class="lightgrey"><strong class="text">Interchange<br>ability</strong></td>
                                                <td align="center" class="lightgrey" ><strong class="text">Cut Off Chassis</strong> </td>
                                                <td align="center" class="lightgrey" ><strong class="text">Cut Off Date</strong> </td>
                                            </tr>

                                            <%  //System.out.println("grpBom size :" + grpBom.size());
                                                                        int count = 1;
                                                                        for (int i = 0; i < grp_detail.size(); i = i + 7) {
                                                                            cnt = cnt + 1;
                                            %>
                                            <tr>
                                                <td align="center" bgcolor="#FFFFFF" class="text" style="width:35px"><%=count%></td>
                                                <td align="center" bgcolor="#FFFFFF" class="text"><input type="checkbox" name="chk0" id="chk0<%=cnt%>" value="<%=count%>" /></td>
                                                    <%   changedData.add(new Integer(count));
                                                                                                                                String seq = "" + grp_detail.get(i);
                                                    %>
                                                <td  align="center" bgcolor="#FFFFFF" class="text">
                                                    <input  type="text" id="seq_no<%=cnt%>" name="seq_no" size="2" value='<%=grp_detail.get(i)%>' maxlength="5" /><input type='hidden'  name='comp_type' id='comp_type<%=cnt%>' value='PRT'/></td>


                                                <% String compNo = "" + grp_detail.get(i + 1);
                                                %>
                                                <td  align="center" bgcolor="#FFFFFF" class="text"><input type="text"  id="comp_no_text<%=cnt%>" name='testParam0' value='<%=compNo%>' style="width:100px" maxlength="200" />&nbsp;<a href='#'><img src='<%=imagesURL%>/arrdown.gif' alt='Get Suggestions' border='0' style='width:15px' onclick='getSuggestionsGroup("comp_no_text<%=cnt%>",document.getElementById("comp_no_text<%=cnt%>"),document.getElementById("comp_type<%=cnt%>"),document.getElementById("img<%=cnt%>"));'/><img  id='img<%=cnt%>' style='visibility:hidden;width:15px' border='0'  src='<%=imagesURL%>/load.gif'/></a>
                                                </td>
                                                <%   String qty = "" + grp_detail.get(i + 2);
                                                                                                                            if (qty.equals("0")) {
                                                                                                                                qty = "AR";
                                                                                                                            }
                                                %>
                                                <td  align="center" bgcolor="#FFFFFF" class="text">
                                                    <input type="text" id="text<%=cnt%>" name='quan' size="4" value='<%=qty%>' maxlength="10" onblur="checkingQty(this);" style="width:45px" />
                                                </td>
                                                <%
                                                                                                                            grp_remarks = "" + grp_detail.get(i + 3);
                                                                                                                            if (grp_remarks == null || grp_remarks.equalsIgnoreCase("NULL")) {
                                                                                                                                grp_remarks = "";
                                                                                                                            }
                                                %>
                                                <td  align="left" bgcolor="#FFFFFF" class="text">
                                                    <input type="text" id="text<%=cnt%>" name='groupremarks' size="4" value='<%=grp_remarks%>' style="width:110px"  maxlength="250"/>
                                                </td>
                                                <%
                                                                                                                            interchangebility = "" + grp_detail.get(i + 4);
                                                                                                                            if (interchangebility == null || interchangebility.equalsIgnoreCase("NULL")) {
                                                                                                                                interchangebility = "";
                                                                                                                            }
                                                %>

                                                <td  align="left" bgcolor="#FFFFFF" class="text">
                                                    <html:select styleClass="text" styleId="interchangebility<%=cnt%>" property="interchangebility" value='<%=interchangebility%>' >
                                                        <html:option value="">-Select-</html:option>
                                                        <html:option value="YES">YES</html:option>
                                                        <html:option value="NO">NO</html:option>
                                                    </html:select>
                                                    <%--<input type="text" id="interchangebility<%=cnt%>" name='interchangebility' size="4" value='<%=interchangebility%>' style="width:100px"  maxlength="200"/>--%>
                                                </td>

                                                <%
                                                                                                                            cut_of_chassis = "" + grp_detail.get(i + 5);
                                                                                                                            if (cut_of_chassis == null || cut_of_chassis.equalsIgnoreCase("NULL")) {
                                                                                                                                cut_of_chassis = "";
                                                                                                                            }
                                                %>
                                                <td  align="left" bgcolor="#FFFFFF">
                                                    <input type="text" id="cut_of_chassis<%=cnt%>" class="text" name='cut_of_chassis'  value='<%=cut_of_chassis%>' style="width:100px"  maxlength="40"/>
                                                </td>
                                                <%
                                                                                                                            cut_of = "" + grp_detail.get(i + 6);

                                                                                                                            if (cut_of == null || cut_of.equalsIgnoreCase("NULL")) {
                                                                                                                                cut_of = "";
                                                                                                                            } else {
                                                                                                                                cut_of = sdf.format(sdf1.parse(cut_of));
                                                                                                                            }
                                                %>

                                                <td align="left" bgcolor="#FFFFFF" >
                                                    <input type="text"  class="text" id="cut_of<%=cnt%>" name='cut_of' value='<%=cut_of%>' readonly="readonly" style="width: 80px">
                                                    <img alt="calendar" id="effectiveDateCalImg" src='<%=imagesURL%>/showCalendar.jpg' width=20 height=20 border=0 onclick="displayCalendar(document.getElementById('cut_of<%=cnt%>'),'dd/mm/yyyy',this);" />
                                                </td>

                                                <%--<td  align="center" bgcolor="#FFFFFF" class="text">
																	 <input type="text" id="cut_of<%=cnt%>" name='cut_of' size="4" value='<%=cut_of%>' style="width:100px"  maxlength="200"/>
																</td>--%>

                                                <% count++;
                                                                            }

                                                                            session.putValue("grp_detail", grp_detail);
                                                                            session.putValue("changedData", changedData);

                                                %>
                                        </table>

                                        <input type="hidden" name="comp_count"   value='<%=grp_detail.size()%>'/>
                                        <input type="hidden" name="no_of_rows"   value='<%=count%>'/>
                                        <input type="hidden" name="groupno"  value="<%=grp_no%>"/>

                                <tr>
                                    <td>
                                        <table  width="100%" border=0>
                                            <tr><td align="center"><input type="button" name="add_row"  value="Add New Part" onclick="javascript:addRow('<%=s++%>');" /></td></tr>
                                            <tr>
                                                <td colspan="2">
                                                    <table width="100%">
                                                        <tr>

                                                            <td align="right">
                                                                <input type="submit" name="submit" id="Next" value="Submit" style="width:70px;" onclick='return validate();'/>
                                                            </td>
                                                            <td align="left">
                                                                <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr valign="bottom"><td> <font colspan='<%=4%>' class="red">*Click on check box to delete any component from group.</font></td></tr>
                                            <tr valign="bottom"><td class="red"> <font colspan='<%=4%>' >* Put AR in Quantity for As Required.</font></td></tr>

                                        </table>
                                    </td></tr>


                            </table>

                        </form>
                        <%--  </table>

                    </td>
                </tr>--%>
            </table></div>
            <%}%>
            <%
                            out.println(object_pageTemplate.tableFooter());
            %>
            <%
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            //conn.close();
                        }%>

    </body>
</html>
