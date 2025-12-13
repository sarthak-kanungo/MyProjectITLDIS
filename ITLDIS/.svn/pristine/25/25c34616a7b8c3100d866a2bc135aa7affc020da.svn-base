<%--
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="viewEcat.comEcat.ConnectionHolder,java.sql.Connection,java.io.*,EAMG.PartDAO_CUD.*,EAMG_MethodsUtility_Package.*" %>
<%@ page import="java.sql.*, java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
    response.setHeader("Expires", "0");
    response.setHeader("Pragma", "no-cache");
    String grp_no = "" + request.getParameter("groupno");

    Connection conn = null;
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String context = request.getContextPath();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
				String contextPath = request.getContextPath();
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84;
    ArrayList grpBom = null;

    conn = holder.getConnection();
    grpBom = new EAMG_MethodUtility2().grp_bom_param(conn);
    String comp = (String) request.getAttribute("comp");
    comp = comp.trim();
    if (comp == "") {
        comp = "0";

    }
    int no_of_comp = Integer.parseInt(comp);
    //System.out.println("no_of_comp is:" + no_of_comp);
%>



<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>



<html:html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/suggestions.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=imagesURL%>js/autosuggest.css" />
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <script type="text/javascript">
            var valueArr=new Array();
            var paramDomainTypeArr=new Array();
            var paramValueTypeArr=new Array();
            var paramIdArray=new Array();
            var multiListArr=new Array();
            <%
          response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1

          response.setHeader("Pragma", "no-cache"); //HTTP 1.0

          response.setDateHeader("Expires", 0); //prevents caching at the proxy server

          com.EAMG.common.EAMG_MethodsUtility methodutil = new com.EAMG.common.EAMG_MethodsUtility();
          String conditions = "";
          //ArrayList grpBom =null;
          //Connection conn=null;
          try {
              boolean multiFlag = false;
              conn = holder.getConnection();
              grpBom = new EAMG_MethodUtility2().grp_bom_param(conn);
              conditions = methodutil.getFunctionBody(conn);
              for (int i = 0; i < grpBom.size(); i++) {
                  multiFlag = false;
                  String paramtype = "" + grpBom.get(i);
                  int param_id = methodutil.getGrpBomParamIdForList(paramtype, conn);
                  if (param_id == 0) {
                      int new_param_id = methodutil.getGrpBomParamIdForMultiList(paramtype, conn);
                      if (new_param_id != 0) {
                          multiFlag = true;
                          param_id = new_param_id;
            %>
                            multiListArr[<%=i%>]='true';
            <%}
                        }
            %>

        paramIdArray[<%=i%>]='<%=param_id%>';

            <%    String param = methodutil.getDomainTypeFrmBomParamMaster(paramtype, conn);
                String valType = methodutil.getValueTypeFrmBomParamMaster(paramtype, conn);
                Vector listVec = methodutil.getGrpBomParamListValues(param_id, conn);
                //System.out.println(listVec);
                if (listVec.size() > 0) {%>
                    var options1="<option value=''>--Select--</option>";
            <%
                    for (int j = 0; j < listVec.size(); j++) {
                        String val = "" + listVec.elementAt(j);%>
                            options1=options1+"<option value=<%=val%>"+"><%=val%></option>";
            <%}%>
                        valueArr[<%=param_id%>]=options1;
            <%}

            %>
                paramDomainTypeArr[<%=i%>]='<%=param%>';
                paramIdArray[<%=i%>]='<%=param_id%>';
                paramValueTypeArr[<%=i%>]='<%=valType%>';

            <%}%>

                 var rowLength='<%=no_of_comp%>';
                 var paramCol='<%=grpBom.size()%>';
                 var insertArray=new Array();
                 for(k=0;k<rowLength;k++)
                 {
                     insertArray[k]='true';
                 }
                 var index=rowLength;
                 //add dynamic row
                 function addRow()
                 {

                     var fixedCol='5';
                     var oRows = document.getElementById('myTable1').getElementsByTagName('tr');

                     rowLength=oRows.length-1


                     var x=document.getElementById('myTable1').insertRow(rowLength);
                     x.bgColor="white";
                     x.className = 'tableactive';
                     var ch=x.insertCell(0);
                     ch.innerHTML="<div align='center'><font class='text' ><input type='hidden' name='index' value='"+(rowLength)+"'/>"+(rowLength)+"</font></div>";

                     var ch=x.insertCell(1);
                     ch.innerHTML="<div align='center' ><font class='text' ><input type ='text' name='seqeno'  size='5' value='' /></font></div>";

                     var seqNo=x.insertCell(2);
                     seqNo.innerHTML="<div align='center' ><font class='text'><select id='comp_type"+rowLength+"'   name='comp_type"+rowLength+"' value=''  onchange='document.getElementById(\"comp_no_text"+rowLength+"\").value=\"\";document.getElementById(\"comp_no_text"+rowLength+"\").focus();'><option value=''>--Select--</option><option value='PRT'>Part</option><option value='ASM'>Assembly</option></select></font></div>";

                     var b=x.insertCell(3);
                     b.innerHTML="<div align='center' ><font class='text'><input type='text' id='comp_no_text"+rowLength+"' name='comp_no_text"+rowLength+"' value=''   style='width:100px' />&nbsp;<a href='#'><img src='/AMW-AuthEcat/auth_images/arrdown.gif' alt='Get Suggestions' border='0' onclick='getSuggestions(\"comp_no_text"+rowLength+"\",document.getElementById(\"comp_no_text"+rowLength+"\"),document.getElementById(\"comp_type"+rowLength+"\"),document.getElementById(\"img"+rowLength+"\"));'/><img  id='img"+rowLength+"' style='visibility:hidden;' border='0' src='/AMW-AuthEcat/auth_images/load.gif'/></a></font></div>";

                     b=x.insertCell(4);
                     b.innerHTML="<div align='center' ><font class='text'><input type='text' id='quantity"+rowLength+"' name='quantity' value='' size='6'    style='width:70px' /></font></div>";

                     for(var i=0;i<paramCol;i++)
                     {
                         var ind=paramIdArray[i];
                         var b=x.insertCell(i+5);
                         var str="<input type = 'hidden' size='9' id='grpBomParamType"+rowLength+""+(i+1)+"' name='grpBomParamType"+rowLength+"' value='"+paramValueTypeArr[i]+"'>";

                         if(ind==0)
                         {

                             b.innerHTML="<div align='center' ><font class='text'><input type = 'text' size='9' id='val"+rowLength+""+(i+1)+"' name='grpBomParam"+rowLength+"' value=''></font></div>"+str;

                         }
                         else
                         {
                             var option=valueArr[ind];

                             if(multiListArr[i]=='true')
                                 b.innerHTML="<div align='center' ><font class='text'><input type = 'hidden' size='9' id='val"+rowLength+""+(i+1)+"' name='grpBomParam"+rowLength+"' value=''><select id='val1"+rowLength+""+(i+1)+"' name='grpBomParam1"+rowLength+"' multiple style='height: 50px;'>"+option+"</select></font></div>"+str;
                             else
                                 b.innerHTML="<div align='center' ><font class='text'><select id='param_type' id='val"+rowLength+""+(i+1)+"' name='grpBomParam"+rowLength+"'>"+option+"</select></font></div>"+str;
                         }

                     }
                     document.getElementById('comp_type'+rowLength).focus();
                     document.getElementById('comp_count').value=rowLength;
                 }

                 //End


                 function checkParamValues(obj,i)
                 {
                     var val_obj=obj;
                     var param=paramDomainTypeArr[i];
            <%=conditions%>
                }
                var paramCol='<%=grpBom.size()%>';
                var partArr=new Array();
                var asmArr=new Array();

                function getComponentList(comptype)
                {

                    if(comptype=='ASM')
                    {
                        if(asmArr.length>0)
                        {
                            return;
                        }
                    }
                    var res='';
                    var strURL ="<%=context%>/authJSP/EAMG_get_component_list.jsp?compno=*&comptype="+comptype;
                    xmlHttp = GetXmlHttpObject();
                    xmlHttp.onreadystatechange = function()
                    {
                        res=stateChanged(comptype);

                    };
                    xmlHttp.open("POST", strURL, true);
                    xmlHttp.send(null);

                }
                function stateChanged(comptype)
                {
                    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                    {
                        res = xmlHttp.responseText;
                        //alert(res);

                        var index1 = res.indexOf("<listdata>");
                        var index2 = (index1 == -1) ? res.length : res.indexOf("</listdata>", index1 + 10);
                        if(index1 == -1)
                            index1 = 0;
                        else
                            index1 += 10;

                        var tmpinfo = res.substring(index1, index2);
                        if(comptype=='PRT')
                        {
                            partArr=tmpinfo.split("|");
                        }
                        else
                        {
                            asmArr=tmpinfo.split("|");
                        }
                        getComponentList('ASM');

                        // alert(res_txt);
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
                function validate()
                {
                    //alert(partArr);
                    // alert(document.getElementById('comp_count').value);

                    var cnt1=0;
                    if(rowLength==1)
                    {
                        //alert('in if');
                        cnt1=1;
                        var compno=document.getElementById('comp_no_text1').value;
                        compno=TrimAll(compno);
                        if(compno=='')
                        {
                            alert('Group BOM can not be empty.');
                            creation.seqeno.focus();
                            return false;
                        }
                        var seqno=document.creation.seqeno.value;
                        seqno=TrimAll(seqno);
                        if(seqno=='')
                        {
                            alert("Please Enter Sequence Number for S.No. 1");
                            creation.seqeno.focus();
                            document.creation.seqeno.value="";
                            return false;
                        }
                        else
                        {
                            var res=isProperAll(seqno);
                            if(res==false)
                            {
                                alert("Sequence Number for S.No. 1 can not contain any special character.");
                                creation.seqeno.focus();
                                document.creation.seqeno.value="";
                                return false;
                            }
                        }
                        var obj=document.getElementById('comp_type1');
                        var ind=obj.selectedIndex;
                        var type=obj.options[ind].value;
                        var type_txt=obj.options[ind].text;
                        if(ind==0)
                        {
                            alert('Please Select Component Type for S.No. 1');
                            obj.focus();
                            return false;
                        }

                        var quantity=document.creation.quantity.value;
                        quantity=TrimAll(quantity);
                        if(quantity=='')
                        {
                            alert("Please Enter Quantity for Component '"+compno+"'.");
                            document.creation.quantity.focus();
                            return false;
                        }
                        if(quantity!='AR')
                        {

                            if(quantity==0)
                            {
                                alert("Quantity of Component '"+compno+"' can not be 0, Please give AR for 'As Required'.");
                                document.creation.quantity.value='';
                                document.creation.quantity.focus();
                                return false;
                            }
                            var res=isProperAll(quantity);
                            if(res==false)
                            {
                                alert("No Special Symbols allowed. Please Enter only Integer Value for Quantity of Component '"+compno+"', Please give AR for 'As Required'.");
                                document.creation.quantity.value='';
                                document.creation.quantity.focus();
                                return false;
                            }

                            var res=checkQuantity(quantity);
                            if(res==false)
                            {
                                alert("No Special Symbols allowed. Please Enter only Integer Value for Quantity of Component '"+compno+"', Please give AR for 'As Required'.");
                                document.creation.quantity.value='';
                                document.creation.quantity.focus();
                                return false;
                            }
                            if(quantity>500)
                            {
                                alert("Quantity of Component '"+compno+"' can not be greater than 500, Please give AR for 'As Required'.");
                                document.creation.quantity.value='';
                                document.creation.quantity.focus();
                                return false;

                            }
                        }
                        for(var j=0;j<paramCol;j++)
                        {
                            var grpBomParamType=document.getElementById('grpBomParamType1'+(j+1)).value;
                            if(grpBomParamType!='MULTI-LIST'){
                                var val_obj=document.getElementById('val1'+(j+1));
                                if(paramIdArray[j]==0 && val_obj.value!=''){
                                    var res=checkParamValues(val_obj,j);
                                    if(res==false){
                                        return false;
                                    }
                                }
                            }else{
                                selectMultiList(document.getElementById('val11'+(j+1)),'val1'+(j+1));
                            }
                        }
                        var isvalid=validateComponent(compno.toUpperCase(),type);
                        if(isvalid==false)
                        {
                            alert("Component '"+compno+"' does not exist in database.");
                            document.getElementById('comp_no_text1').focus();
                            document.getElementById('comp_no_text1').value="";
                            return false;
                        }

                    }
                    else
                    {
                        //alert('in else');
                        var cnt=0;

                        for(var i=0;i<rowLength;i++)
                        {
                            var compno=document.getElementById('comp_no_text'+(i+1)).value;
                            compno=TrimAll(compno);
                            if(compno!='')
                            {
                                cnt++;
                                cnt1=i;
                            }
                        }
                        if(cnt==0)
                        {
                            alert('Group BOM can not be empty.');
                            creation.seqeno[0].focus();
                            return false;
                        }

                        //alert('cnt1 :'+cnt1);
                        //validateComponent(cnt1);
                        //alert("res_txt :"+res_txt);
                        //return false;
                        for(var i=0;i<=cnt1;i++)
                        {
                            var seqno=document.creation.seqeno[i].value;
                            seqno=TrimAll(seqno);
                            if(seqno=='')
                            {
                                alert('Please Enter Sequence Number for S.No. '+(i+1));
                                document.creation.seqeno[i].focus();
                                document.creation.seqeno[i].value="";
                                return false;
                            }
                            else
                            {
                                var res=isProperAll(seqno);
                                if(res==false)
                                {
                                    alert('Sequence Number for S.No. '+(i+1)+' can not contain any special character.');
                                    document.creation.seqeno[i].focus();
                                    document.creation.seqeno[i].value="";
                                    return false;
                                }
                            }
                            var obj=document.getElementById('comp_type'+(i+1));
                            var ind=obj.selectedIndex;
                            var type=obj.options[ind].value;
                            var type_txt=obj.options[ind].text;
                            if(ind==0)
                            {
                                alert('Please Select Component Type for S.No. '+(i+1));
                                obj.focus();
                                return false;
                            }
                            var compno=document.getElementById('comp_no_text'+(i+1)).value;
                            compno=TrimAll(compno);
                            if(compno=='')
                            {
                                alert("Please Enter Component Number for S.No. '"+(i+1));
                                document.getElementById('comp_no_text'+(i+1)).value='';
                                document.getElementById('comp_no_text'+(i+1)).focus();
                                return false;
                            }
                            var quantity=document.creation.quantity[i].value;
                            quantity=TrimAll(quantity);
                            if(quantity=='')
                            {
                                alert("Please Enter Quantity for Component '"+compno+"'.");
                                creation.quantity[i].value='';
                                creation.quantity[i].focus();
                                return false;
                            }
                            else if(quantity!='AR')
                            {
                                if(quantity==0)
                                {
                                    alert("Quantity of Component '"+compno+"' can not be 0, Please give AR for 'As Required'.");
                                    creation.quantity[i].value='';
                                    creation.quantity[i].focus();
                                    return false;
                                }


                                var res=isProperAll(quantity);
                                if(res==false)
                                {
                                    alert("No Special Symbols allowed. Please Enter only Integer Value for Quantity of Component '"+compno+"', or Give AR for 'As Required'.");
                                    creation.quantity[i].value='';
                                    creation.quantity[i].focus();
                                    return false;
                                }

                                var res=checkQuantity(quantity);
                                if(res==false)
                                {
                                    alert("No Special Symbols allowed. Please Enter only Integer Value for Quantity of Component '"+compno+"', or Give AR for 'As Required'.");
                                    creation.quantity[i].value='';
                                    creation.quantity[i].focus();
                                    return false;
                                }

                                if(quantity>500)
                                {
                                    alert("Quantity of Component '"+compno+"' can not be greater than 500, Please give AR for 'As Required'.");
                                    creation.quantity[i].value='';
                                    creation.quantity[i].focus();
                                    return false;
                                }
                            }
                            for(var j=0;j<paramCol;j++)
                            {

                                var grpBomParamType=document.getElementById('grpBomParamType'+(i+1)+(j+1)).value;
                                if(grpBomParamType!='MULTI-LIST'){
                                    var val_obj=document.getElementById('val'+(i+1)+(j+1));
                                    if(paramIdArray[j]==0 && val_obj.value!=''){
                                        var res=checkParamValues(val_obj,j);
                                        if(res==false){
                                            return false;
                                        }
                                    }
                                }else{

                                    selectMultiList(document.getElementById('val1'+(i+1)+(j+1)),'val'+(i+1)+(j+1));
                                }
                            }
                        }

                        for(var i=0;i<=cnt1;i++)
                        {
                            var compno=document.getElementById('comp_no_text'+(i+1)).value;
                            var comptype=document.getElementById('comp_type'+(i+1)).value;
                            compno=TrimAll(compno);

                            var isvalid=validateComponent(compno.toUpperCase(),comptype);
                            if(isvalid==false)
                            {
                                alert("Component '"+compno+"' does not exist in database.");
                                document.getElementById('comp_no_text'+(i+1)).focus();
                                document.getElementById('comp_no_text'+(i+1)).value="";
                                return false;
                            }

                        }
                    }
                    return true;
                }


                function CancelProcess()
                {
                    //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                    //if(cncl==true)
                    {
                        parent.right.location="/AMW-AuthEcat/authJSP/amw_Group/amw_Create_Group.jsp";
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
                    String tdData = "MANAGE GROUP >> ADD NEW GROUP >> ATTACH COMPONENT TO GROUP";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "ATTACH COMPONENT TO GROUP";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Group >>Add New Group >> Attach Component To Group</b></td>
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
               <%-- <tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;ATTACH COMPONENT TO GROUP</b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form name="creation" action="context/EAMG_partcreation.do" method="post" onsubmit="return validate(this);">
                                        <br />
                                        <% String groupno = (String) request.getAttribute("groupno");
                                            String desc = (String) request.getAttribute("desc");
                                        %>
                                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td colspan="3"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                                                        <tr>
                                                            <td height="45" valign="middle" align="left" class="grey"><b>&nbsp;<span class="heading">Group Number: </span><span class="heading"><%=groupno%></span><br>&nbsp;<span class="heading">Group Description: </span><span class="heading"><%=desc%></span></b><br></td>
                                                        </tr>

                                                    </table></td>
                                            </tr>

                                            <tr>
                                                <td align="center">
                                                    <table id="myTable1" width="100%" border="0" cellpadding="1" cellspacing="1" bordercolor="#333333" bgcolor="#000000">
                                                        <tr>
                                                            <td align="center" class="lightgrey"><strong class="text">S.No</strong></td>

                                                            <td  align="center" class="lightgrey"><strong class="text">Seq. No</strong></td>

                                                            <td  align="center" class="lightgrey"><strong class="text"> Type </strong></td>

                                                            <td align="center" class="lightgrey"><strong class="text">Component Number </strong></td>

                                                            <td align="center" class="lightgrey"><strong class="text">Qty</strong></td>

                                                            <%


                                                                Statement statement = conn.createStatement();
                                                                Statement statement1 = conn.createStatement();
                                                                Statement statement2 = conn.createStatement();
                                                                ResultSet rs = null;
                                                                ResultSet rs1 = null;
                                                                ResultSet rs2 = null;
                                                                String paramlst = "";
                                                                String paramdesc = "";
                                                                int paramid = 0;
                                                                int counter = 0;
                                                                Vector paramDescVec = new Vector();
                                                                Vector paramtypeVec = new Vector();
                                                                Vector paramlistVec = new Vector();
                                                                rs = statement.executeQuery("select PARAM_DESC,PARAM_VALUE_TYPE from GRP_BOM_PARAM_MASTER  order by PARAM_DESC");
                                                                while (rs.next()) {
                                                                    paramdesc = rs.getString(1);
                                                                    String parantype = rs.getString(2);
                                                            %>


                                                            <td align="center" class="lightgrey"><strong class="text"><%=paramdesc%></strong></td>
                                                            <%
                                                                    paramDescVec.add(paramdesc);
                                                                    paramtypeVec.add(parantype);
                                                                counter++;
                                                            }%>
                                                            <%

                                                                //System.out.println("paramlistVec"+paramlistVec);
                                                                request.setAttribute("length", "" + counter);
                                                                session.setAttribute("paramdesc", paramDescVec);
                                                                //System.out.println(""+counter);
                                                                rs.close();

                                                            %>

                                                        </tr>

                                                        <% for (int i = 1; i <= no_of_comp; i++) {
                                                        %>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF" class="text"><input type="hidden" name="index" value=<%=i%>/>
                                                                <%=i%></td>
                                                            <td align="center" bgcolor="#FFFFFF" class="text"><input type="text" size="5" name="seqeno" value=""/></td>

                                                            <td  align="center" bgcolor="#FFFFFF" class="text">
                                                                <select name="comp_type<%=i%>" id="comp_type<%=i%>" onchange='document.getElementById("comp_no_text<%=i%>").value="";document.getElementById("comp_no_text<%=i%>").focus();'>
                                                                    <option value="select">--Select--</option>
                                                                    <option value="PRT">Part</option>
                                                                    <option value="ASM">Assembly</option>
                                                                </select></td>


                                                            <td  align="center" bgcolor="#FFFFFF" class="text"><input type="text" name="comp_no_text<%=i%>" id="comp_no_text<%=i%>" style='width:100px'  />&nbsp;<a href='#'><img src='/AMW-AuthEcat/auth_images/arrdown.gif' border='0' alt='Get Suggestions'  onclick='getSuggestions("comp_no_text<%=i%>",document.getElementById("comp_no_text<%=i%>"),document.getElementById("comp_type<%=i%>"),document.getElementById("img<%=i%>"));'/><img  id='img<%=i%>' style='visibility:hidden;' border='0' src='/AMW-AuthEcat/auth_images/load.gif'/></a>
                                                            </td>

                                                            <td  align="center" bgcolor="#FFFFFF" class="text"><input type="text" size="6" name="quantity" id="quantity" /></td>
                                                                <% for (int j = 1; j <= counter; j++) {
                                                                %>

                                                            <td  align="center" bgcolor="#FFFFFF" class="text">
                                                                <% if (paramtypeVec.elementAt(j - 1).equals("TXT")) {%>
                                                                <input type="text" size=10 id="val<%=i%><%=j%>" name="grpBomParam<%=j%>" value=""/>
                                                                <%} else if (paramtypeVec.elementAt(j - 1).equals("LIST")) {%>
                                                                <select name="grpBomParam<%=j%>" id="val<%=i%><%=j%>">
                                                                    <option value="">--Select--</option>
                                                                    <%

                                                                        int param_id = methodutil.getGrpBomParamId("" + paramDescVec.elementAt(j - 1), conn);
                                                                        //System.out.println("PARAM ID"+param_id);
                                                                        Vector paramiid = methodutil.getGrpBomParamListValues(param_id, conn);
                                                                        //System.out.println("PARAM values"+paramiid);
                                                                        for (int d = 0; d < paramiid.size(); d++) {
                                                                    %>
                                                                    <option value="<%=paramiid.elementAt(d)%>"><%=paramiid.elementAt(d)%></option>
                                                                    <% }%>
                                                                </select>

                                                                <% } else {%>
                                                                <input type="hidden" size=10 id="val<%=i%><%=j%>" name="grpBomParam<%=j%>" value=""/>
                                                                <select name="grpBomParam1<%=j%>" id="val1<%=i%><%=j%>" multiple style='height: 50px;'>
                                                                    <%

                                                                        int param_id = methodutil.getGrpBomParamId("" + paramDescVec.elementAt(j - 1), conn);
                                                                        //System.out.println("PARAM ID"+param_id);
                                                                        Vector paramiid = methodutil.getGrpBomParamListValues(param_id, conn);
                                                                //System.out.println("PARAM values"+paramiid);%>
                                                                    <option value="">--Select--</option>
                                                                    <%
                                                                        for (int d = 0; d < paramiid.size(); d++) {
                                                                    %>
                                                                    <option value="<%=paramiid.elementAt(d)%>"><%=paramiid.elementAt(d)%></option>
                                                                    <% }%>
                                                                </select>
                                                                <%}%>
                                                                <input type="hidden" size=10 name="grpBomParamType<%=j%>" id="grpBomParamType<%=i%><%=j%>" value="<%=paramtypeVec.elementAt(j - 1)%>"/>
                                                                <%}%>
                                                            </td>
                                                            <% }%>

                                                        <tr>
                                                            <td colspan="2"></td></tr>
                                                    </table></td>
                                            </tr>


                                            <tr><td <%=counter + 4%> ></td></tr>
                                            <tr><td colspan=<%=counter + 5%> align="center">
                                                    <table width="100%">
                                                        <tr>
                                                            <td colspan="2" align="center">
                                                                <input type="button" value="Add New Part/Assembly" onclick="javascript:addRow();" />
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
                                                        <tr><td class="red"> * Put AR in Quantity for As Required.</td></tr>
                                                    </table>
                                                    <input type="hidden" name="groupno"  value='<%=groupno%>'/>
                                                    <input type="hidden" id="comp_count" name="comp_count" value='<%=no_of_comp%>'/>
                                                    <input type="hidden" name="grp_bom_param_count" width="30%"  value='<%=counter%>'/>
                                                </td></tr>


                                        </table>
                                </form></td>
                            </tr>
<%--
                        </table>
                    </td>
                </tr>--%>
            </table>

        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
    <% } catch (Exception e) {
            e.printStackTrace();
        } %>
</html:html>

