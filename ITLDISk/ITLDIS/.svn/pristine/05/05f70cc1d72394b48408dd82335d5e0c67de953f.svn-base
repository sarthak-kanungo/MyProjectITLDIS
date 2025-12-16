<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.*,java.text.SimpleDateFormat,viewEcat.comEcat.ConnectionHolder" %>
<%@ page import="viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<html>
    <%
                String contextPath = request.getContextPath();
                response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                response.setHeader("Expires", "0");
                response.setHeader("Pragma", "no-cache");
                session.removeAttribute("ECNImplementation");
                session.setAttribute("comp_type", "PRT");
                //System.out.println("set comp_type in session:PRT");
                String server_name = (String) session.getValue("server_name");
                String ecatPath = (String) session.getValue("ecatPATH");
                String mainURL = (String) session.getValue("mainURL");
                System.out.println("mainURL"+mainURL);
                Date date=new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String todaydate=sdf.format(date);
                PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
                String servletURL = object_pageTemplate.servletURL();
                String imagesURL = object_pageTemplate.imagesURL();// imagesURL = object_pageTemplate.imagesURL();
               // System.out.println("servletURL"+servletURL);
               // System.out.println("imagesURL=="+imagesURL);
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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        
        <link type="text/css" rel="stylesheet" href="<%=imagesURL%>/css/dhtmlgoodies_calendar.css" media="screen" />
        <script type="text/javascript" src="<%=servletURL%>/js/dhtmlgoodies_calendar.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/suggestions.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=imagesURL%>js/autosuggest.css" />
        

        <script language="javascript">
            function check_method_select()
            {
               var flag="cataFlag";
               document.form1.action="<%=contextPath%>/upload_pricelist.do?flag="+flag+"";
               return true;
            }




            function validateExcel()
            {
                
                    var filePath= document.getElementById("excelFile").value;
                    var extension = filePath.split('.').pop().toLowerCase();
                   


                     <%--if(filePath.indexOf(':\\') ==-1 || filePath.indexOf(':\\')==0)
                    {
                        alert('Invalids Excel File. Please Browse only .xls file.');
                        return false;
                    }--%>

                     if(extension!="xls" && extension!="xlsx"){
                        alert('Invalid Excel File. Please Browse only .xls file or .xlsx file.');
                        return false;
                         
                     }
                    
                    return true;
                    
                }
            

            function create_template()
            {

                <%
                String dest = ecatPath + "dealer/templates/pricelist_template.xls";

                
                %>

<%--                var url='EAMG_upload_template_save.jsp?comp_type=PRT';
                if(document.getElementById("downloadDiv") == null)
                {
                    downloadDiv = document.createElement("div");
                    document.getElementsByTagName("body")[0].appendChild(downloadDiv);
                    downloadDiv.style.width = "0px";
                    downloadDiv.style.height = "0px";
                    downloadDiv.id = "downloadDiv";
                }
                else
                    downloadDiv = document.getElementById("downloadDiv");
                if(document.getElementById("downloadFrame") == null)
                {
                    downloadFrame = document.createElement("iframe");
                    downloadFrame.id = "downloadFrame";
                    downloadFrame.src = url;
                    downloadFrame.scrolling = "no";
                    downloadFrame.style.width = "0px";
                    downloadFrame.style.height = "0px";
                    downloadDiv.innerHTML = downloadFrame.outerHTML;
                }
                else
                {
                    downloadFrame = document.getElementById("downloadFrame");
                    downloadFrame.src = url;
                }
--%>


}
            function enableExcelBrowse()
            {
                document.form1.radio4.checked=false;
                document.form1.excelFile.disabled=false;
            }

            function partWiz()
            {
                document.form1.radio3.checked=false;
                document.form1.excelFile.disabled=true;


            }

            function CancelProcess()
            {
                var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                if(cncl==true)
                    parent.content.location="<%=contextPath%>/common/EAMG_home_page.jsp";

            }

        </script>

    </head>
    <body>
        <%
                    String tdData = "MANAGE PART >> UPLOAD PRICE LIST";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "UPLOAD PRICE LIST";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center" width="700">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td align="center" valign="top" bgcolor="#FFFFFF">
                        <form name="form1" onsubmit="return check_method_select();" ENCTYPE="multipart/form-data" method="post">
                            <br />
                            <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                <%--<tr>
                                    <td colspan="3"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                            <tr>
                                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;Select Criteria</b></td>
                                            </tr>
                                        </table></td>
                                </tr>--%>
                                <tr>
                                    <td width="6%" height="25" align="center" class="text"><label>
                                            <td width="19%" height="25" align="left" class="text">&nbsp;Effective Date</td> </label>
                                    </td>
                                    <td width="75%" height="25" align="left" class="text"><input type="text" name="effectiveDate" value="<%=todaydate%>"   readonly="readonly" id="Effective Date">
                                        <img alt="calendar" id="effectiveDateCalImg" src='<%=imagesURL%>/showCalendar.jpg' width=20 height=20 border=0 onclick="displayCalendar(document.getElementById('Effective Date'),'dd/mm/yyyy',this);" /></td>
                                    <td width="19%" height="25" align="left" class="text">&nbsp</td>
                                    
                                </tr>
                                <tr>
                                    <td width="6%" height="25" align="center" class="text">&nbsp</td>
                                    <td width="29%" height="25" align="left" class="text">&nbsp;Browse Input File</td>
                                    <td width="65%" height="25" align="left" class="text"><input type=file id="excelFile" name=excelFile value='' size=20 style="width:275px"  ></td>
                                </tr>


                                <tr>
                                    <td colspan="3" align="right"><a href="<%=mainURL%>/dealer/templates/pricelist_template.xls"   class="red-for-temmplate-link">* Click Here To Download Template </a><span class="red-for-temmplate">(xls).</span></td>
                                </tr>
                                <tr>
                                    <td colspan="3" align="center"><label>

                                            <input type="submit" name="Next" id="Next" value="Next" style="width:70px;" onclick="return validateExcel();"/>
                                            <%--<input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />--%>
                                        </label></td>
                                </tr>

                            </table>
                        </form>
                    </td>
                </tr>

            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>

