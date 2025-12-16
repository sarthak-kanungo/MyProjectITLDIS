<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>

<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>
<html >
    <head>
        <%
                    String contextPath = request.getContextPath();
                    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                    response.setHeader("Expires", "0");
                    response.setHeader("Pragma", "no-cache");
                    ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
                    String server_name = (String) session.getValue("server_name");
                    String ecatPath = (String) session.getValue("ecatPATH");
                    String mainURL = (String) session.getValue("mainURL");
                    PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
                    String servletURL = "";
                    servletURL = object_pageTemplate.servletURL();
                    String imagesURL = "";
                    imagesURL = object_pageTemplate.imagesURL();
                    String modelNo = object_pageTemplate.MODEL_NO;
                    String engineModel = object_pageTemplate.ENGINE_MODEL;
                    String engineSeries = object_pageTemplate.ENGINE_SERIES;
                    String MODELTYPE = object_pageTemplate.MODELTYPE;
                    String modelImages = object_pageTemplate.modelImages;

                    String session_id = session.getId();
                    String getSession = (String) session.getValue("session_value");
                    if (!session_id.equals(getSession)) {
                        object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                        return;
                    }
                    String heading = null;
                    int width = 659;
                    int height = 84;
                    Vector levelsVec = (Vector) session.getAttribute("levelidVec");
                    session.setAttribute("levelid", levelsVec);
                    String variantEffectiveDate = "" + session.getAttribute("effectiveDate");


        %>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=UtilityMapkeys1.tile%></title>
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <link type="text/css" rel="stylesheet" href="<%=imagesURL%>css/dhtmlgoodies_calendar.css" media="screen" />
        <script type="text/javascript" src="<%=imagesURL%>js/dhtmlgoodies_calendar.js"></script>
        <script language="javascript">
            function imgcompare()
            {
                var modelno=document.getElementById("modelno").value;
                var img=document.getElementById("modelimg").value;
                if(img.indexOf(':\\') ==-1 || img.indexOf(':\\')==0)
                {
                    alert('Invalid Image File path. Please Attach Valid .jpg Extension File.');
                    return false;
                }
                var pos1=img.lastIndexOf("\\");
                var pos2=img.lastIndexOf(".");
                var name=img.substring(pos1+1,pos2);
                name=name.toLowerCase();
                modelno=modelno.toLowerCase();
                modelno=TrimAll(modelno);
                var ext=img.substring(pos2+1);
                ext=ext.toLowerCase();
           
                if(ext=="jpg")
                {
                    if(modelno==name)
                    {
                        //document.getElementById("Next").style.visibility='visible';
                        return true;
                    }    
                    else
                    {
                        alert("<%=modelNo%> Image Name should be same as <%=modelNo%> Number.");
                        // document.getElementById("Next").style.visibility='hidden';
                        return false;
                    }
                }
                else
                {
                   
                    alert("Please Attach Valid .jpg Extension File.");
                    //document.getElementById("Next").style.visibility='hidden';
                    return false;
                }

            }

            function imgProductCompare()
            {
                var modelno=document.getElementById("variantNameOth").value;
                var img=document.getElementById("variantNameImageFileOth").value;
                if(img.indexOf(':\\') ==-1 || img.indexOf(':\\')==0)
                {
                    alert('Invalid Image File path. Please Attach Valid .jpg Extension File.');
                    return false;
                }
                var pos1=img.lastIndexOf("\\");
                var pos2=img.lastIndexOf(".");
                var name=img.substring(pos1+1,pos2);
                name=name.toLowerCase();
                modelno=modelno.toLowerCase();
                modelno=TrimAll(modelno);
                var ext=img.substring(pos2+1);
                ext=ext.toLowerCase();

                if(ext=="jpg")
                {
                    if(modelno==name)
                    {
                        //document.getElementById("Next").style.visibility='visible';
                        return true;
                    }
                    else
                    {
                        alert("<%=engineSeries%> Image Name should be same as <%=engineSeries%> Name.");
                        // document.getElementById("Next").style.visibility='hidden';
                        return false;
                    }
                }
                else
                {

                    alert("Please Attach Valid .jpg Extension File.");
                    //document.getElementById("Next").style.visibility='hidden';
                    return false;
                }

            }

            function imgModelCompare()
            {
                var modelno=document.getElementById("engineModelOth").value;
                var img=document.getElementById("engineModelImageFileOth").value;
                if(img.indexOf(':\\') ==-1 || img.indexOf(':\\')==0)
                {
                    alert('Invalid Image File path. Please Attach Valid .jpg Extension File.');
                    return false;
                }
                var pos1=img.lastIndexOf("\\");
                var pos2=img.lastIndexOf(".");
                var name=img.substring(pos1+1,pos2);
                name=name.toLowerCase();
                modelno=modelno.toLowerCase();
                modelno=TrimAll(modelno);
                var ext=img.substring(pos2+1);
                ext=ext.toLowerCase();

                if(ext=="jpg")
                {
                    if(modelno==name)
                    {
                        //document.getElementById("Next").style.visibility='visible';
                        return true;
                    }
                    else
                    {
                        alert("<%=engineModel%> Image Name should be same as <%=engineModel%> Name.");
                        // document.getElementById("Next").style.visibility='hidden';
                        return false;
                    }
                }
                else
                {

                    alert("Please Attach Valid .jpg Extension File.");
                    //document.getElementById("Next").style.visibility='hidden';
                    return false;
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
            function showEngineModel(variantName)
            {
                var selectValue=variantName.value;
                var strURL = "<%=contextPath%>/authJSP/EAMG_Variant/EAMG_get_engineModel_list.jsp?selectValue="+selectValue;
                //alert(strURL);
                xmlHttp = GetXmlHttpObject();
                xmlHttp.onreadystatechange = function()
                {
                    stateChanged_showValue();
                };
                xmlHttp.open("POST", strURL, true);
                xmlHttp.send(null);
            }
            
            function stateChanged_showValue()
            {   if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    document.getElementById('statusDivId').innerHTML='';
                    res = xmlHttp.responseText;
                    document.getElementById('statusDivId').innerHTML=res;
                }
            }

            function validateVarient()
            {

                if(document.getElementById("variantName").value!='0'){
                    var modelno=document.getElementById("modelno").value;
                    modelno=TrimAll(modelno);
                    var desc=document.getElementById("modeldesc").value;
                    desc=TrimAll(desc);
            <%--var img=document.getElementById("modelimg").value;
            img=TrimAll(img);--%>
                                var variantName=document.getElementById("variantName").value;
                                variantName=TrimAll(variantName);
                                var engineModel=document.getElementById("engineModel").value;
                                engineModel=TrimAll(engineModel);
                                var applicationType=document.getElementById("applicationType").value;
                                applicationType=TrimAll(applicationType);
                                if(modelno=='')
                                {
                                    alert("Please Enter <%=modelNo%> Number.");
                                    document.getElementById("modelno").value="";
                                    document.getElementById("modelno").focus();
                                    return false;
                                }
                                var res=isProperComponent(modelno);
                                if(res==false)
                                {
                                    alert('<%=modelNo%> Number can not contain any special characters. Allowed Characters are Underscore(_), Hyphen(-) and Dot(.).');
                                    document.getElementById("modelno").value="";
                                    document.getElementById("modelno").focus();
                                    return false;
                                }
                    
                                var res=checkSpace(modelno);
                                if(res==false)
                                {
                                    alert('<%=modelNo%> Number can not contain any Space.');
                                    document.getElementById("modelno").value="";
                                    document.getElementById("modelno").focus();
                                    return false;
                                }
                    
                                var res=isValidCharacterLength(modelno);
                                if(res==false)
                                {
                                    alert('<%=modelNo%> Number can not be greater than 31 characters.');
                                    document.getElementById("modelno").value="";
                                    document.getElementById("modelno").focus();
                                    return false;
                                }
                                if(desc=='')
                                {
                                    alert("Please Enter <%=modelNo%> Description.");
                                    document.getElementById("modeldesc").value="";
                                    document.getElementById("modeldesc").focus();
                                    return false;
                                }
                                   
                                var res=isProperParameter(desc);
                                if(res==false)
                                {
                                    alert('<%=modelNo%> Description can not contain any special characters. Allowed Characters are Underscore(_), Hyphen(-) and Dot(.).');
                                    document.getElementById("modeldesc").value="";
                                    document.getElementById("modeldesc").focus();
                                    return false;
                                }
                
                                if(variantName=='0')
                                {
                                    alert("Please Select <%=engineSeries%>.");
                                    document.getElementById("variantName").value="";
                                    document.getElementById("variantName").focus();
                                    return false;
                                }
                                if(variantName=='1Oth')
                                {
                                    if(document.getElementById("variantNameOth").value=="")
                                    {
                                        alert("Please Enter <%=engineSeries%>.");
                                        document.getElementById("variantNameOth").value="";
                                        document.getElementById("variantNameOth").focus();
                                        return false;
                                    }

                                    if(document.getElementById("variantNameImageFileOth").value=="")
                                    {
                                        alert("Please Choose Image For <%=engineSeries%>.");
                                        document.getElementById("variantNameImageFileOth").value="";
                                        document.getElementById("variantNameImageFileOth").focus();
                                        return false;
                                    }
                                    else
                                    {
                                        var result=imgProductCompare();
                                        if (result==false)
                                            return result;
                                    }
                                }
                                if(engineModel=='0')
                                {
                                    alert("Please Select <%=engineModel%>.");
                                    // document.getElementById("engineModel").value="";
                                    document.getElementById("engineModel").focus();
                                    return false;
                                }

                                if(engineModel=='1Oth')
                                {
                                    if(document.getElementById("engineModelOth").value=="")
                                    {
                                        alert("Please Enter <%=engineModel%>.");
                                        document.getElementById("engineModelOth").value="";
                                        document.getElementById("engineModelOth").focus();
                                        return false;
                                    }

                                    if(document.getElementById("engineModelImageFileOth").value=="")
                                    {
                                        alert("Please Choose Image For <%=engineModel%>.");
                                        document.getElementById("engineModelImageFileOth").value="";
                                        document.getElementById("engineModelImageFileOth").focus();
                                        return false;
                                    }else
                                    {
                                        var result=imgModelCompare();
                                        if (result==false)
                                            return result;
                                    }

                                }
                                if(applicationType=='0')
                                {
                                    alert("Please Select <%=MODELTYPE%>.");
                                    //document.getElementById("applicationType").value="";
                                    document.getElementById("applicationType").focus();
                                    return false;
                                }

                                if(applicationType=='1Oth')
                                {
                                    if(document.getElementById("applicationTypeOth").value=="")
                                    {
                                        alert("Please Enter <%=MODELTYPE%>.");
                                        document.getElementById("applicationTypeOth").value="";
                                        document.getElementById("applicationTypeOth").focus();
                                        return false;
                                    }
                                }                            
                            }
                            else
                            {
                                alert("Please Select <%=engineSeries%>.");
                                document.getElementById("variantName").focus();
                                return false;
                            }
                
                        }
                
             
                        function CancelProcess()
                        {
                           // var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                           // if(cncl==true)
                            {
                                location.href="<%=contextPath%>/common/EAMG_home_page.jsp";
                            }
	
                        }

                        function enableVarientType()
                        {
                            if(document.getElementById("variantName").value == '1Oth')
                            {
                                document.getElementById("variantNameOth").style.display ='block';
                                document.getElementById("variantNameImageFileOth").style.display ='block';
                                document.getElementById("varientBrowseId").style.display ='block';
                                document.getElementById("variantNameOth").focus();
                                return;
                            }
                            else
                            {
                                document.getElementById("variantNameOth").value='';
                                document.getElementById("variantNameOth").style.display ='none';
                                document.getElementById("variantNameImageFileOth").value='';
                                document.getElementById("variantNameImageFileOth").style.display ='none';
                                document.getElementById("varientBrowseId").style.display ='none';
                                return;
                            }
                        }




                        function showOtherModel()
                        {
                            if(document.getElementById("engineModel").value == '1Oth')
                            {
                                document.getElementById("engineModelOth").style.display ='block';
                                document.getElementById("engineModelImageFileOth").style.display ='block';
                                document.getElementById("engineModelBrowseId").style.display ='block';
                                document.getElementById("engineModelOth").focus();
                                return;
                            }
                            else
                            {
                                document.getElementById("engineModelOth").value='';
                                document.getElementById("engineModelOth").style.display ='none';
                                document.getElementById("engineModelImageFileOth").value='';
                                document.getElementById("engineModelImageFileOth").style.display ='none';
                                document.getElementById("engineModelBrowseId").style.display ='none';
                                return;
                            }
                        }

                        function showOther()
                        {
                            if(document.getElementById("applicationType").value == '1Oth')
                            {
                                document.getElementById("applicationTypeId").style.display ='block';
                                document.getElementById("applicationTypeOth").focus();
                                return;
                            }
                            else
                            {
                                document.getElementById("applicationTypeOth").value='';
                                document.getElementById("applicationTypeId").style.display ='none';
                                return;
                            }
                        }
 
                        function submit_method_select()
                        {

                            var url;
                            var modelno=document.getElementById("modelno").value;
                            var modeldesc=document.getElementById("modeldesc").value;
                            url="modelno="+modelno+"&modeldesc="+modeldesc;
                            //alert("url 1="+url);
                            var variantName=document.getElementById("variantName").value;
                            if(variantName=='1Oth')
                            {
                                var variantNameOth=document.getElementById("variantNameOth").value;
                                url=url+"&variantName="+variantName+"&variantNameOth="+variantNameOth;
                            }
                            else
                            {
                                url=url+"&variantName="+variantName;
                            }

                            // alert("url 2="+url);
                            var engineModel=document.getElementById("engineModel").value;
                            if(engineModel=='1Oth')
                            {

                                var engineModelOth=document.getElementById("engineModelOth").value;
                                url=url+"&engineModel="+engineModel+"&engineModelOth="+engineModelOth;
                            }
                            else
                            {
                                url=url+"&engineModel="+engineModel;
                            }

                            //alert("url 3="+url);
                            var applicationType=document.getElementById("applicationType").value;
                            if(applicationType=='1Oth')
                            {
                                var applicationTypeOth=document.getElementById("applicationTypeOth").value;
                                url=url+"&applicationType="+applicationType+"&applicationTypeOth="+applicationTypeOth;
                            }else
                             {
                                    url=url+"&applicationType="+applicationType;
                             }
                            var effectiveDate=document.getElementById("effectiveDate").value;
                            //alert('effectiveDate'+effectiveDate);
                            url=url+"&effectiveDate="+effectiveDate;
                            document.form1.action="<%=contextPath%>/EAMG_CreateModel.do?"+url;
                            return true;
                        }
        </script>

    </head>
    <body >

        <form  method=post ENCTYPE="multipart/form-data" name="form1" onsubmit="return submit_method_select();">
            <input type="hidden" name="effectiveDate" id="effectiveDate" value="<%=variantEffectiveDate%>"/>
            <%
                        String tdData = "MANAGE " + modelNo.toUpperCase() + " >> ADD NEW " + modelNo.toUpperCase();
                        object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
            %> 
            <%
                        heading = "ADD NEW " + modelNo.toUpperCase();
                        out.println(object_pageTemplate.tableHeader(heading, width, height));
            %>

            <div align="center">
                <table width="700" border="0" cellspacing="1" cellpadding="1">                  
                    <tr>
                        <td align="right" class="small"><b>Step 1 OF 3</b></td>
                    </tr>            
                    <tr>
                        <td align="center" valign="top" bgcolor="#FFFFFF">

                            <br />
                            <%
                                        String status = (String) session.getAttribute("modelstatus");
                                        if (status.equals("exist")) {%>

                            <div class="red"><%=modelNo%> already Exist...</div>

                            <%}

                                        session.setAttribute("flag", "1");
                            %>
                            <table width="95%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td colspan="2"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                            <tr>
                                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;New <%=modelNo%> Details</b></td>
                                            </tr>
                                        </table></td>
                                </tr>
                                <tr>
                                    <td width="29%" height="25" align="left" class="text">&nbsp;<%=modelNo%> Number</td>
                                    <td width="70%" align="left" class="text"><label>
                                            <input type="text" name="modelno" id="modelno" value="" class="text"/>
                                        </label></td>
                                </tr>
                                <tr>
                                    <td height="25" align="left" class="text">&nbsp;<%=modelNo%> Description</td>
                                    <td height="25" align="left" class="text"><label>
                                            <input type="text" name="modeldesc" id="modeldesc" class="text"/>
                                        </label></td>
                                </tr>


                            </table>
                        </td>
                    </tr>
                    
                    <%if (levelsVec.size() == 0) {%>
                    <tr>
                        <td >&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="center" valign="top" bgcolor="#FFFFFF">
                            <table width="95%" border="0" cellspacing="0" cellpadding="0">
                                <tr><td height="100" align="center" class="red" colspan="2" >No <%=modelNo%> Classification Level available in Database.. </td></tr>
                            </table>

                        </td>
                    </tr>
                    <%
                    } else {
                    %>
                    <tr>
                        <td >&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="center" valign="top" bgcolor="#FFFFFF">
                            <table width="650" border="0" cellpadding="2" cellspacing="2"  bgcolor="#CCCCCC">
                                <tr>
                                    <td width="120" align="center" class="blue"><strong class="heading">Level Description</strong></td>
                                    <td width="480" align="left" class="blue"><strong class="heading">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Level Value</strong></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" valign="top" bgcolor="#FFFFFF">
                            <table width="650" border="0" cellspacing="2" cellpadding="2">
                                    <%if (levelsVec.size() > 0) {
                                                    if (levelsVec != null) {%>
                                <tr>
                                    <td height="25" width="152" align="left" bgcolor="#FFFFFF" class="text">&nbsp;<%=engineSeries%></td>
                                    <td height="25" width="140" align="left" bgcolor="#FFFFFF" class="text">
                                        <select name="variantName"  style="width:'110px'" id="variantName" class="text" onchange="showEngineModel(document.getElementById('variantName')),enableVarientType();">
                                            <option value='0'>--Select--</option>
                                            <%
                                                                                                                for (int i = 0; i < levelsVec.size(); i++) {
                                                                                                                    String variantName = ((String) levelsVec.elementAt(i));
                                            %>
                                            <option value="<%=variantName%>"><%=variantName%></option>
                                            <%}%>
                                            <option value="1Oth">OTHER</option>
                                        </select>

                                    </td>
                                    <td id="varientTextId" width="120" align="center" bgcolor="#FFFFFF">
                                            <input type="text" name="variantNameOth" id="variantNameOth" value="" style="display:none;"   onblur="stringOnlyValidation(document.form1.variantNameOth)"/>
                                       </td>

                                    <td width="120" height="25" align="right" bgcolor="#FFFFFF" class="text" style="display:none;" id="varientBrowseId">Browse Image File</td>
                                    <td width="120" height="25" align="left" bgcolor="#FFFFFF" class="text"><input type=file id="variantNameImageFileOth" name="variantNameImageFileOth" value='' style="display:none;"></td>
                                </tr>
                                <tr><td colspan="5"><span id="statusDivId" align=center ></span></td></tr>
                            </table>
                        </td>
                    </tr>
                    <%
                                        }
                                    }
                                }
                    %>
                    <tr>
                        <td align="center" valign="top" bgcolor="#FFFFFF">
                            <table width="95%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td  align="center"><label>

                                            <input type="submit" name="Next" id="Next" value="Next" onClick="return validateVarient();" style="width:70px;"/>
                                           <%-- <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />--%>
                                        </label></td>

                                </tr>
                            </table>
                        </td>
                    </tr>
                    <%--</table></td>
            </tr>--%>
                </table>
            </div>
            <%
                        out.println(object_pageTemplate.tableFooter());
            %>
        </form>
    </body>
</html>
