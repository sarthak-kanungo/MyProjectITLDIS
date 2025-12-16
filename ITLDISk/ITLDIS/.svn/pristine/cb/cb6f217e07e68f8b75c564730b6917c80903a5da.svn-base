<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
				String contextPath = request.getContextPath();
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
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
%>
<%
            String x = request.getParameter("x");//out.println(x);
            String y = request.getParameter("y");//out.println(y);
            String group_no = "" + session.getAttribute("group_no");
            Vector GrpBomVec = (Vector) session.getAttribute("GrpBomVec");
//System.out.println("GrpBomVec :"+GrpBomVec);

            Vector CompVec = (Vector) GrpBomVec.elementAt(0);
            Vector IndexVec = (Vector) GrpBomVec.elementAt(5);
            Vector SeqNoVec = (Vector) GrpBomVec.elementAt(3);
            Vector CompDescVec = (Vector) GrpBomVec.elementAt(4);

            Vector uniqueCompVec = new Vector();
            Vector uniqueIndexVec = new Vector();
            for (int i = 0; i < CompVec.size(); i++) {
                if (!uniqueCompVec.contains(CompVec.elementAt(i))) {
                    uniqueCompVec.add(CompVec.elementAt(i));
                    uniqueIndexVec.add(IndexVec.elementAt(i));
                }
            }
//System.out.println("uniqueCompVec :"+uniqueCompVec);
            String component = "";
            String index = "";
            String seq = "";
            String descS = "";
            session.setAttribute("IndexVec", IndexVec);
            session.setAttribute("CompVec", CompVec);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />

        <script language="javascript">
            var indexnoarr=new Array();
            var compnoarr=new Array();
            function load_arrays()
            {
            <%for (int i = 0; i < IndexVec.size(); i++) {%>
                    indexnoarr[<%=i%>]='<%=IndexVec.elementAt(i)%>';
            <%}%>
            <%for (int i = 0; i < IndexVec.size(); i++) {%>
                    compnoarr[<%=i%>]='<%=CompVec.elementAt(i)%>';
            <%}%>

                }
                function showCompIndex()
                {
                    var select_ind= document.getElementById("ref").selectedIndex;
                    var comp_no= document.getElementById("ref").options[select_ind].value;
                    var select=document.getElementById("index");
                    select.options.length=0;
                    var j=0;
                    for (var i=0;i < compnoarr.length;i++)
                    {
                        if(compnoarr[i]==comp_no)
                        {
                            select.options[j]=new Option(indexnoarr[i]);
                            select.options[j].value=indexnoarr[i];
                            j++;
                        }

                    }
                }

                function validate()
                {
                    var refcode=document.getElementById('ref').value;
                    //var indexno=document.getElementById('index').value;
                    var side=document.getElementById('side').value;
                    if(refcode=='')
                    {
                        alert("Please Select Valid Part Number.");
                        document.getElementById('ref').focus();
                        return false;
                    }

                    /*if(indexno=='')
                   {
                   alert("Please Enter Valid Index Number.");
                   document.getElementById('index').focus();
                   return false;
                   }
                   else
                   {
                       if(isNaN(indexno))
                       {
                        alert("Please Enter numeric value for Index Number.");
                        document.getElementById('index').value='';
                        document.getElementById('index').focus();
                       return false;
                        }
                   }*/
                    if(side=='')
                    {
                        alert("Please Select Valid side.");
                        document.getElementById('side').focus();
                        return false;
                    }

                    return true;

                }


        </script>
    </head>
    <body onload="load_arrays();">
       
        <form name="form1" method="post" action="EAMG_grp_update_refcoord.jsp">
            <table width="450" height="154" border="0" cellspacing="1" cellpadding="1">
                <tr valign="top">
                    <td height="21"  colspan="4" valign="middle" bgcolor="" class="blue"><b class="heading">&nbsp;ADD REFERENCE COORDINATE </b></td>
                </tr>
                <tr><td height="10"></td></tr>
                <tr>
                    <td width="3">&nbsp;</td>
                    <td width="117" class="text" >Select Seq No:</td>
                    <!--td width="191"><select id="ref"  name="refno" align="left" onchange="showCompIndex();" style="width:200px;"-->
                    <td width="191"><select id="ref"  name="refno" style="width:215px;font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 11px;color: #000000;">
                            <option selected>Seq[component(sNo)]</option>
                            <%                        for (int i = 0; i < CompVec.size(); i++) {
                                            component = "" + CompVec.elementAt(i);
                                            index = "" + IndexVec.elementAt(i);
                                            seq = "" + SeqNoVec.elementAt(i);
                                            descS = "" + CompDescVec.elementAt(i);
                            %>
                            <option value='<%=index%>@@<%=component%>'><%=seq%> [<%=component%>:<%=descS%>:(<%=index%>)]</option>
                            <%}%>                    </select>
                    </td>
                    <td width="1">&nbsp;</td>
                </tr>
                <!--tr>
                   <td width="3">&nbsp;</td>
                   <td width="117" class="text">Enter Index No:</td>
                   <td width="191" class="text"><select id="index"  name="indexno" align="left" style="width:200px;">
                                   </select>

                   <td width="1">&nbsp;</td>
               </tr-->
                <tr>
                    <td width="3">&nbsp;</td>
                    <td width="117" class="text" >Select Side:</td>
                    <td width="191"><select name="side" id="side" style="width:225px;">
                            <option selected>--Select--</option>
                            <option value="LEFT">Left</option>
                            <option value="RIGHT">Right</option>
                            <option value="TOP">Top</option>
                            <option value="BOTTOM">Bottom</option>
                        </select></td>
                    <td width="1">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4"><table width="321" border="0">
                            <tr>

                                <td colspan="2" align="right"><input type="submit" value="Submit" onclick="return validate();" align="center"></td>
                                <td width="121" colspan="2" align="center"><input type="button" value="Cancel" onclick="window.close();" align="center" ></td>
                            </tr>
                            <tr>
                                <td colspan="4"><font color="red" size="1" face="Arial"></font></td>
                            </tr>
                        </table>                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>

            </table>
            <input type="hidden" name="group_no" value='<%=group_no%>'/>
            <input type="hidden" name="x" value='<%=x%>'/>
            <input type="hidden" name="y" value='<%=y%>'/>
        </form>

    </body>
</html>
