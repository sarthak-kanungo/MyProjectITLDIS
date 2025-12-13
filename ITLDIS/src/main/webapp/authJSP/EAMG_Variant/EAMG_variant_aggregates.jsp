<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String contextPath = request.getContextPath();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String modelNo = object_pageTemplate.MODEL_NO;
            String table = object_pageTemplate.GROUP;
              String aggregate = object_pageTemplate.AGGREGATE;
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84;
%>
<%
            ArrayList<String> variantArray = (ArrayList<String>) request.getAttribute("result");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest1.js"></script>
        <script type="text/javascript" language="javascript">
            var variantArray =null;
            <%
                        for (String variant : variantArray) {
            %>
                variantArray=new Array(<%=variant%>);
            <%}%>

                function moveDualList( variantList, attachedlist, moveAll )
                {
                    if((variantList.selectedIndex == -1)&&(moveAll == false))
                    {
                        return;
                    }
                    newattachedlist = new Array( attachedlist.options.length );
                    for( len = 0; len < attachedlist.options.length; len++ )
                    {
                        if ( attachedlist.options[ len ] != null )
                        {
                            newattachedlist[ len ] = new Option( attachedlist.options[ len ].text, attachedlist.options[ len ].value, attachedlist.options[ len ].defaultSelected, attachedlist.options[              len ].selected );
                        }
                    }
                    for( var i = 0; i < variantList.options.length; i++ )
                    {
                        if(variantList.options[i]!=null&&( variantList.options[i].selected== true ||moveAll))
                        {
                            newattachedlist[ len ] = new Option( variantList.options[i].text, variantList.options[i].value, variantList.options[i].defaultSelected, variantList.options[i].selected );
                            len++;
                        }
                    }

                    for(var j = 0; j < newattachedlist.length; j++)
                    {
                        if ( newattachedlist[ j ] != null )
                        {
                            attachedlist.options[ j ] = newattachedlist[ j ];
                            attachedlist.options[ j ].value=attachedlist.options[ j ].text;
                        }
                    }

                    for( var i = variantList.options.length - 1; i >= 0; i-- )
                    {
                        if ( variantList.options[i] != null && ( variantList.options[i].selected == true || moveAll ) )
                        {
                            variantList.options[i]= null;
                        }
                    }
                    setlengths();
                }
                function setlengths()
                {
                    document.getElementById("totalaggregates").innerHTML=document.getElementById("variantList").options.length;
                    document.getElementById("selectedaggregates").innerHTML=document.getElementById("attachedlist").options.length;
                }

                function searchelement()
                {
                    var length=document.getElementById("variantList").options.length;
                    var searchtext=document.getElementById("searchunusedgroup").value;
                    var searchtextlength=searchtext.length;
                    searchtextlength=searchtextlength;
                    for(i=0;i<length;i++)
                    {
                        var text=document.getElementById("variantList").options[i].text;
                        text=text.toUpperCase();
                        var index= text.indexOf(searchtext.toUpperCase());
                        if(index!=-1)
                        {
                            document.getElementById("variantList").options[i].selected=true;
                        }
                        else
                        {
                            document.getElementById("variantList").options[i].selected=false;
                        }
                    }
                }

                function validate()
                {
                    var length=document.EAMG_Variant_AggregatesActionForm.attachedlist.options.length;
        
                    if(document.EAMG_Variant_AggregatesActionForm.model_no.value=="")
                    {
                        alert("Please Select <%=modelNo%>.");
                        return false;
                    }
                    else if(length==0)
                    {
                        alert("No <%=aggregate%> are attached. Please Select atleast one <%=aggregate%>.");
                        return false;
                    }
                    else
                    {
                        for(i=0;i<length;i++)
                        {
                            document.getElementById("attachedlist").options[i].selected=true;
                        }
                        return true;
                    }
                }

                function CancelProcess()
                {
                    //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                   // if(cncl==true)
                    {
                        location.href="<%=contextPath%>/common/EANG_home_page.jsp";
                    }

                }

        </script>

    </head>
    <body onload="setlengths();">
        <%
                    String tdData ="MANAGE "+modelNo.toUpperCase()+" >>  "+modelNo.toUpperCase()+" VS "+aggregate.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                    heading = ""+modelNo+" Vs "+aggregate+"";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));%>
        <logic:empty name="EAMG_Variant_AggregatesActionForm" property="model" scope="session">
            <div align="center">
                <table width="700" border="0" cellspacing="1" cellpadding="1">
                   <%-- <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                <tr>
                                    <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%> >>  <%=modelNo%> Vs <%=aggregate%></b></td>
                                </tr>
                            </table></td>
                    </tr>--%>
                    <tr>
                        <td align="right" class="small"><b>Step 1 OF 2</b></td>
                    </tr>

                    <tr>
                        <td valign="top">&nbsp;</td>
                    </tr>
                    <tr>
                        <td valign="top" class="red" align="center">No <%=modelNo%> available in Database.</td>
                    </tr>

                </table>
            </div>
        </logic:empty>
        <logic:notEmpty name="EAMG_Variant_AggregatesActionForm" property="model" scope="session">
            <div align="center">
                <table width="700" border="0" cellspacing="1" cellpadding="1">
                   <%-- <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                <tr>
                                    <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%> >>  <%=modelNo%> Vs <%=aggregate%> </b></td>
                                </tr>
                            </table></td>
                    </tr>--%>
                    <tr>
                        <td align="right" class="small"><b>Step 1 OF 2</b></td>
                    </tr>

                    <%--<tr>
                        <td valign="top">
                            <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                <tr>
                                    <td height="25" align="left" class="blue"><b class="heading">&nbsp;Add <%=modelNo%></b></td>
                                </tr>--%>
                                <tr>
                                    <td align="center" valign="top" bgcolor="#FFFFFF">
                                        <html:form styleId="EAMG_Variant_AggregatesActionForm" method="post"  action="/EAMG_Variant_Aggregates.do">
                                            <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                                <tr>
                                                    <td width="30%" class="text" align="left">&nbsp;Select <%=modelNo%>:
                                                    </td>
                                                    <td width="70%" align="left"><label>
                                                            <input type="text" name="model_no" style='width:200px;height:20px;' id="Model_No"/> 
                                                        </label>
                                                        <html:hidden property="option" value="variantAggregates"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3" align="right"><table width="100%" border="0" cellpadding="1" cellspacing="1">
                                                            <tr>
                                                                <td width="43%" align="center" bgcolor="#CCCCCC" ><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                                                                        <tr>
                                                                            <td width="43%" height="25" align="center" class="blue"><strong class="heading">Available <%=aggregate%></strong></td>
                                                                        </tr>
                                                                    </table></td>
                                                                <td width="7%" bgcolor="#FFFFFF">&nbsp;</td>
                                                                <td width="43%" align="center" bgcolor="#CCCCCC"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                                                                        <tr>
                                                                            <td width="43%" height="25" align="center" class="blue"><strong class="heading">Selected <%=aggregate%></strong></td>
                                                                        </tr>
                                                                    </table></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="right" bgcolor="#FFFFFF" class="text"># <span  id="totalaggregates"></span></td>
                                                                <td bgcolor="#FFFFFF">&nbsp;</td>
                                                                <td align="right" bgcolor="#FFFFFF" class="text"># <span id="selectedaggregates"></span></td>
                                                            </tr>
                                                            <tr>
                                                                <td rowspan="6" bgcolor="#FFFFFF">
                                                                    <select id="variantList" name="variantList" size="12" multiple="multiple" style="width:310px ">
                                                                        <logic:iterate id="data" name="EAMG_Variant_AggregatesActionForm" property="aggregates" scope="session">
                                                                            <option>${data}</option> 
                                                                        </logic:iterate>
                                                                    </select></td>
                                                                <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                                <td rowspan="6" bgcolor="#FFFFFF">
                                                                    <select name="attachedlist" id="attachedlist" size="12" multiple="multiple" style="width:310px ">
                                                                    </select></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="center" bgcolor="#FFFFFF"><label>
                                                                        <img src="<%=imagesURL%>/next.gif" name="selectone" style="width:24px " onclick="moveDualList( document.EAMG_Variant_AggregatesActionForm.variantList,  document.EAMG_Variant_AggregatesActionForm.attachedlist, false);" />
                                                                    </label></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="center" bgcolor="#FFFFFF"><label>
                                                                        <img src="<%=imagesURL%>/next1.gif" name="selectall" style="width:24px " onclick="moveDualList( document.EAMG_Variant_AggregatesActionForm.variantList,  document.EAMG_Variant_AggregatesActionForm.attachedlist, true);"/>
                                                                    </label></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="center" bgcolor="#FFFFFF"><label>
                                                                        <img src="<%=imagesURL%>/previous.gif" name="removeone" style="width:24px " onclick="moveDualList( document.EAMG_Variant_AggregatesActionForm.attachedlist,document.EAMG_Variant_AggregatesActionForm.variantList,false);"/>
                                                                    </label></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="center" bgcolor="#FFFFFF"><label>
                                                                        <img src="<%=imagesURL%>/previous1.gif" name="removeall" style="width:24px " onclick="moveDualList(document.EAMG_Variant_AggregatesActionForm.attachedlist,document.EAMG_Variant_AggregatesActionForm.variantList,true);"/>
                                                                    </label></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                            </tr>

                                                        </table></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3">
                                                        <table width="100%">
                                                            <tr>

                                                                <td width="50%" align="right">
                                                                    <input type="submit" name="Next" id="Next" value="Next" style="width:70px;" onClick="return validate();"/>
                                                                </td>
                                                                <td width="50%" align="left">
                                                                    <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </html:form></td>
                                </tr>
                            <%--</table></td>
                    </tr>--%>
                </table>
            </div>
        </logic:notEmpty>
        <script type="text/javascript" language="javascript">
            if(variantArray!=null) 
                new actb1('Model_No', variantArray); 
        </script>
        <%
                                            out.println(object_pageTemplate.tableFooter());
                            %>
    </body>
</html>
