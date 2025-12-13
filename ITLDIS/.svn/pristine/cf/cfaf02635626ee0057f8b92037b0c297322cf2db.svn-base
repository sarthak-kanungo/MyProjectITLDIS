<%-- 
    Document   : AffectedModelECN
    Created on : May 21, 2013, 12:41:57 PM
    Author     : manish.kishore
--%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page import="authEcat.UtilityMapkeys1,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,java.util.ArrayList"  contentType="text/html" pageEncoding="UTF-8"%>


<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");



            PageTemplate object_pageTemplate = new PageTemplate();
            String cnt = request.getContextPath();
            String imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String contextPath = request.getContextPath();
            String getSession = (String) session.getValue("session_value");



            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }

            
            String heading = null;
            int width = 740;
            int height = 0;


%>

<html>
    <title><%=UtilityMapkeys1.tile%></title>
    <head>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" language="javascript">


            function moveDualList( unattachedlist, attachedlist, moveAll )

            {

                // Do nothing if nothing is selected

                if (  ( unattachedlist.selectedIndex == -1 ) && ( moveAll == false )   )

                {

                    return;

                }
                newattachedlist = new Array( attachedlist.options.length );



                for( len = 0; len < attachedlist.options.length; len++ )

                {

                    if ( attachedlist.options[ len ] != null )

                    {

                        newattachedlist[ len ] = new Option( attachedlist.options[ len ].text, attachedlist.options[ len].value, attachedlist.options[ len ].defaultSelected, attachedlist.options[ len ].selected );

                    }

                }

                for( var i = 0; i < unattachedlist.options.length; i++ )

                {

                    if ( unattachedlist.options[i] != null && ( unattachedlist.options[i].selected == true || moveAll ) )

                    {

                        // Statements to perform if option is selected

                        // Incorporate into new list

                        newattachedlist[ len ] = new Option( unattachedlist.options[i].text, unattachedlist.options[i].value, unattachedlist.options[i].defaultSelected, unattachedlist.options[i].selected );

                        len++;

                    }

                }
                // Sort out the new destination list

                // newattachedlist.sort( userareOptionValues );   // BY VALUES

                // newattachedlist.sort( userareOptionText );   // BY TEXT
                // Populate the destination with the items from the new array

                for ( var j = 0; j < newattachedlist.length; j++ )

                {

                    if ( newattachedlist[ j ] != null )

                    {
                        attachedlist.options[ j ] = newattachedlist[ j ];
                        attachedlist.options[ j ].selected=true;
                        //attachedlist.options[ j ].value=attachedlist.options[ j ].text;
                        //alert(attachedlist.options[ j ].text);
                    }

                }
                // Erase source list selected elements

                for( var i = unattachedlist.options.length - 1; i >= 0; i-- )

                {

                    if ( unattachedlist.options[i] != null && ( unattachedlist.options[i].selected == true || moveAll ) )

                    {

                        // Erase Source

                        unattachedlist.options[i].value = "";

                        unattachedlist.options[i].text  = "";

                        unattachedlist.options[i]       = null;

                    }

                }
                setlengths();
            } // End of moveDualList()
            function setlengths()
            {
                document.getElementById("totaluserlength").innerHTML=document.getElementById("affectedModels").options.length;
                document.getElementById("selecteduserlength").innerHTML=document.getElementById("attachedlist").options.length;
            }

            function validateForm()
            {
                

                var length=document.getElementById("attachedlist").options.length;
                if(length==0)
                {
                    alert("Please Select atleast one User.");
                    return false;
                }
                else
                {
                    var attchedList=document.getElementById("attachedlist");
				
                    for(var p=0;p<attchedList.options.length;p++)
                    {
                        attchedList.options[p].selected=true;
									 
                    }
                }
            }


            function CancelProcess()
            {
                var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                if(cncl==true)
                {
                    parent.content.location="<%=contextPath%>/UploadECN.do?option=init";
                }

            }

        </script>

    </head>
    <body onload="setlengths();">
        <html:form  action="UploadECN.do" method="post"  styleId="form1" >
            <html:hidden property="option" value="implementECNSelectedModel"/>
            <html:hidden property="modelNo" value="${ecnFormBean.modelNo}"/>
            <html:hidden property="changeType" value="${ecnFormBean.changeType}"/>
            <html:hidden property="status" value="${ecnFormBean.status}"/>
            <html:hidden property="effectiveDate" value="${ecnFormBean.effectiveDate}"/>
            <html:hidden property="effectiveTSN" value="${ecnFormBean.effectiveTSN}"/>
            <html:hidden property="oldPartNo" value="${ecnFormBean.oldPartNo}"/>
            <html:hidden property="newPartNo" value="${ecnFormBean.newPartNo}"/>
            <html:hidden property="ecnNo" value="${ecnFormBean.ecnNo}"/>
        <%
                    String tdData = "MANAGE ECN >> UPLOAD ECN";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "IMPLEMENT ECN";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
            <table width=90% border=0 height=340 align="center" valign="top" cellspacing=1 cellpadding=4 bordercolor = #CCCCCC>
                <tbody>
                    <tr valign=top>
                        <td  class="links_txt">&nbsp;</td>
                        <td  class="links_txt">&nbsp;</td>
                    </tr>
                    

                    <tr>
                        <td colspan="2" align="right">
                            <table width="100%" border="0" cellpadding="1" cellspacing="1">
                                <tr>
                                    <td width="43%" align="center"  >
                                        <table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                                            <tr>
                                                <td width="43%" height="25" align="center" class="blue"><strong class="heading">Affected Models</strong></td>
                                            </tr>

                                        </table>
                                    </td>
                                    <td width="7%" >&nbsp;</td>
                                    <td width="43%" align="center" >
                                        <table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">

                                            <tr>
                                                <td width="43%" height="25" align="center" class="blue"><strong class="heading">Selected Models</strong></td>
                                        </table>
                                    </td>
                                    <td width="7%" >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="right"  class="links_txt"># <span  id="totaluserlength">${fn:length(affectedModelList)}</span></td>
                                    <td >&nbsp;</td>
                                    <td align="right"  class="links_txt"># <span id="selecteduserlength"></span></td>
                                    <td >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td rowspan="6" >
                                        
                                        <html:select styleClass="links_txt"  property="affectedModels" size="12" multiple="multiple" styleId="AffectedModels"  style="width:300px;" ondblclick="moveDualList(document.form1.affectedModels,  document.form1.attachedlist, false);">
                                            <html:options collection="affectedModelList" labelProperty="label" property="value"/>
                                        </html:select>
                                       
                                    </td>
                                    <td align="center" >&nbsp;</td>
                                    <td rowspan="6" ><select class="links_txt" name="attachedlist" id="attachedlist" size="12" multiple="multiple" style="width:300px"  ondblclick="moveDualList(document.form1.attachedlist, document.form1.affectedModels, false);">
                                        </select></td>
                                    <td align="center" >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="center" ><label>
                                            <img src="<%=imagesURL%>next.gif" name="selectone" id="button" style="width:24px"  onclick="moveDualList( document.form1.affectedModels,  document.form1.attachedlist, false );"/>
                                        </label>
                                    </td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="center" ><label>
                                            <img src="<%=imagesURL%>next1.gif" name="selectall" id="button2" style="width:24px"  onclick="moveDualList( document.form1.affectedModels,  document.form1.attachedlist, true  );"/>
                                        </label>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="center" ><label>
                                            <img src="<%=imagesURL%>previous.gif" name="removeone" id="button3" style="width:24px"  onclick="moveDualList(document.form1.attachedlist, document.form1.affectedModels,  false );"/>
                                        </label>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>

                                </tr>
                                <tr>
                                    <td align="center" ><label>
                                            <img src="<%=imagesURL%>previous1.gif" name="removeall" id="button4" style="width:24px" onclick="moveDualList(document.form1.attachedlist, document.form1.affectedModels,  true  );"/>
                                        </label>
                                    </td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td >&nbsp;</td>
                                    <td >&nbsp;</td>
                                    <td >&nbsp;</td>
                                    <td >&nbsp;</td>
                                </tr>

                                <tr>
                                    <td colspan="4">
                                        <table width="100%">
                                            <tr>

                                                <td colspan="2"  align="center">
                                                    <input type="submit" name="Next" id="Next" value="Submit" style="width:70px;" onClick="return validateForm();"/>
                                                </td>
                                                <%--          <td width="50%" align="left">
																			 <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
																		</td>--%>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                </tbody>
            </table>
        </html:form>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>

