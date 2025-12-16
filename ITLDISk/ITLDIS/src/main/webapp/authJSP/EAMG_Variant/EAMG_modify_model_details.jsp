<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*" %>

<%          String contextPath = request.getContextPath();
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
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
            String group = object_pageTemplate.GROUP;
            String modelImages = object_pageTemplate.modelImages;
            String MODELTYPE = object_pageTemplate.MODELTYPE;
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84;
            String model_no = "" + request.getParameter("model_no");
            session.setAttribute("model_no", model_no);
            String variantEffectiveDate = "" + session.getAttribute("effectiveDate");
            EAMG_ModelDAO dao = new EAMG_ModelDAO();
            Connection conn = null;
            try {
                conn = holder.getConnection();
                ResultSet rs;
                Statement stmt = conn.createStatement();
                Vector levelsVec = dao.getModelEngineSeries(conn);
                String model_desc = "";
                rs = stmt.executeQuery("Select DESCRIPTION from CAT_MODELS where Model_No='" + model_no + "'");
                if (rs.next()) {
                    model_desc = rs.getString(1);
                }
                rs.close();
                String path = mainURL + "dealer/ecat_print/model_images/" + model_no + ".jpg";

                session.setAttribute("model_no", model_no);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>

        <style type="text/css">
            <!--
            body {
                margin-left: 0px;
                margin-top: 0px;
                margin-right: 0px;
                margin-bottom: 0px;
                background-image: url(<%=contextPath%>/backBlue.gif);
            }
            -->
        </style>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>

        <script language="javascript">

				function doOnChange()
				{
					 modfiyEngineModel();
					 enableVarientType();
				}
            
            function imgcompare()
            {
                var modelno='<%=model_no%>';
                var img=document.getElementById("modelimg").value;
                if(img.indexOf(':\\') ==-1 || img.indexOf(':\\')==0)
                {
                    alert('Invalid Image File path. Please Attach Valid .jpg Extension File');
                    return false;
                }
                var pos1=img.lastIndexOf("\\");
                var pos2=img.lastIndexOf(".");
                var name=img.substring(pos1+1,pos2);
                name=name.toLowerCase();
                modelno=modelno.toLowerCase();
                var ext=img.substring(pos2+1);
                ext=ext.toLowerCase();
           
                if(ext=="jpg")
                {
                    if(modelno==name)
                    {
                        return true;
                    }    
                    else
                    {
                        alert("<%=modelNo%> Image Name should be same as <%=modelNo%> Number");
                        return false;
                    }
                }
                else
                {
                    alert("Please Attach Valid .jpg Extension File");
                    return false;
                }

            }
            
        
            function validate()
            {
       
                var desc=document.getElementById("modeldesc").value;
                desc=TrimAll(desc);
                if(desc=='')
                {
                    alert("Please Enter <%=modelNo%> Description.");
                    document.getElementById("modeldesc").value="";
                    document.getElementById("modeldesc").focus();
                    return false;
                }
           
                var variantName=document.getElementById("variantName").value;
                variantName=TrimAll(variantName);
                if(variantName=='0')
                {
                    alert("Please Select <%=engineSeries%>.");
                    //document.getElementById("variantName").value="";
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

                var engineModel=document.getElementById("engineModel").value;
                engineModel=TrimAll(engineModel);
                if(engineModel=='0')
                {
                    alert("Please Select <%=engineModel%>.");
                    //document.getElementById("engineModel").value="";
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

                var applicationType=document.getElementById("applicationType").value;
                applicationType=TrimAll(applicationType);
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
            function change_image()
            {
                if(document.getElementById("change_img1").checked==true)
                    document.getElementById("modelimg").disabled=false;
                else
                    document.getElementById("modelimg").disabled=true;
            }
            function loadImage()
            {
                var now = new Date();
                document.form1.img1.src = "<%=path%>?time="+now.getTime();
                //setTimeout('loadImage()',1000);
            }
       
            function CancelProcess()
            {
                //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                //if(cncl==true)
                {
                    location.href="<%=contextPath%>/authJSP/EAMG_Variant/EAMG_modify_model_bypass.jsp";
                }
	
            }
            function modfiyEngineModel()
            {
					 var variantName=document.getElementById('variantName');
                var selectValue=variantName.value;
                var strURL = "<%=contextPath%>/authJSP/EAMG_Variant/EAMG_getModfiy_engineModel.jsp?selectValue="+selectValue;
                //alert(strURL);
                xmlHttp = GetXmlHttpObject();
                xmlHttp.onreadystatechange = function()
                {
                    stateChangedD();
                };
                xmlHttp.open("POST", strURL, true);
                xmlHttp.send(null);
            }

            function stateChangedD()
            {   if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    document.getElementById('statusDivId').innerHTML='';
                    res = xmlHttp.responseText;
                    //window.prompt("jk",res);
                    document.getElementById('statusDivId').innerHTML=res;
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
function getType()
{

modfiyEngineModel();



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
                var model_no=document.getElementById("model_no").value;
                var modeldesc=document.getElementById("modeldesc").value;
                url="model_no="+model_no+"&modeldesc="+modeldesc;
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
                url=url+"&effectiveDate="+effectiveDate;
                document.form1.action="<%=contextPath%>/EAMG_modify_model_details.do?"+url;
                return true;
            }
        </script>
    </head>
    <body onload="getType();"><BR/>

        <%
                        String tdData = "MANAGE " + modelNo.toUpperCase() + " >> MODIFY " + modelNo.toUpperCase() + " >> " + modelNo + " DETAILS";
                        object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                        heading = "" + modelNo.toUpperCase() + " DETAILS";
                        out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <form name="form1" ENCTYPE="multipart/form-data" method="post" onsubmit="return submit_method_select();">
                <input type="hidden" name="effectiveDate" id="effectiveDate" value="<%=variantEffectiveDate%>"/>
                <input type="hidden" name="model_no" id="model_no" value="<%=model_no%>"/>


                <table width="700" border="0" cellspacing="1" cellpadding="1">                 
                    <tr>
                        <td align="center" valign="top" bgcolor="#FFFFFF" colspan="2">


                            <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                <tr>
                                    <td width="27%" height="25" align="left" class="text">&nbsp;<%=modelNo%> Number :</td>
                                    <td width="73%" align="left" class="text"><label>
                                            <%=model_no%>
                                        </label></td>
                                </tr>
                                <tr>
                                    <td height="25" align="left" class="text">&nbsp;<%=modelNo%> Description :</td>
                                    <td height="25" align="left" class="text"><label>
                                            <input type="text" name="modeldesc" id="modeldesc" value="<%=model_desc%>" style="width:200px" />
                                        </label></td>
                                </tr>                            
                            </table>

                        </td>
                    </tr>                  
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>               
                    <%
                                    String level_val = dao.getModelLevelValue(model_no, conn);
                                    if (levelsVec.size() == 0) {%>
                    <tr>
                        <td height="100" align="center" class="red" colspan="2" >No <%=modelNo%> Classification Level available in Database.. </td>

                    </tr>
                    <%} else {%>

                    <tr>
                        <td>
                            <table width="650" border="0" cellpadding="2" cellspacing="2"  bgcolor="#CCCCCC" style="border-width:0px;">
                                <tr>
                                    <td width="120" height="25" align="center" class="blue"><strong class="heading">Level Description</strong></td>
                                    <td width="480" height="25" align="left" class="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong class="heading">Level Value</strong></td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <table width="650" border="0" cellspacing="2" cellpadding="2">
                                <tr>
                                    <td height="25" width="152" align="left"  bgcolor="#FFFFFF" class="text"><%=engineSeries%></td>
                                    <td height="25" width="140" align="left" bgcolor="#FFFFFF" class="text">
                                       <select name="variantName"  id="variantName" style="width:110px;" onchange="doOnChange();" class="text">
                                            <option value="0" selected>--Select--</option>
                                             
                                            <%

                                                                                    for (int i = 0; i < levelsVec.size(); i++) {
                                                                                       
                                                                                        String variantName = ((String) levelsVec.elementAt(i));
                                                                                         //System.out.println(level_val);
                                                                                        if(variantName!=null && !variantName.equals("null")){
                                                  if (variantName.equals(level_val)) {
                                            //System.out.println(variantName+"if");
                                            
                                            %>
                                            <option value="<%=variantName%>" selected><%=variantName%></option>
                                            <%} else {
                                             //System.out.println(variantName+"else");
                                            %>
                                            <option value="<%=variantName%>" ><%=variantName%></option>
                                            <%}
                                                                                    }}%>
                                            
                                            <option value="1Oth" >OTHER</option>
                                        </select>
                                    </td>
                                    <td id="varientTextId" width="120" align="left" bgcolor="#FFFFFF" >
                                        <input type="text" name="variantNameOth" id="variantNameOth" value="" style="display:none;"   onblur="stringOnlyValidation(document.form1.variantNameOth)"/> <!--style="display:none;"-->
                                    </td>

                                    <td  height="25" width="120"  align="right" bgcolor="#FFFFFF" class="text" style="display:none;"  id="varientBrowseId">&nbsp;Browse Image File</td>  <!--style="display:none;"-->
                                    <td  height="25" width="120" align="left" bgcolor="#FFFFFF" class="text"><input type=file id="variantNameImageFileOth" name="variantNameImageFileOth" value='' style="display:none;"></td> <!--style="display:none;"-->
                                    
                                </tr>

                                <tr><td colspan="5" align=center><span id="statusDivId"></span></td></tr>

                            </table></td>
                    </tr>
                    <%}%>
                    <tr>
                        <td >&nbsp;</td>
                    </tr>
                    <tr>
                        <td >
                            <table width="100%">
                                <tr>

                                    <td width="50%" align="right">
                                        <input type="submit" name="Next" id="Next" value="Submit" style="width:70px;" onClick="return validate();"/>
                                    </td>
                                    <td width="50%" align="left">
                                        <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                    <%-- </table>
                 </td>
             </tr>--%>
                </table>


            </form>
        </div>
        <%--<script>loadImage();</script>--%>
        <%
                        out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
<%
            } catch (Exception e) {
                e.printStackTrace();
            }
%>