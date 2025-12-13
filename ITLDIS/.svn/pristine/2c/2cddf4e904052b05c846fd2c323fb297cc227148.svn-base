<%-- 
    Document   : EAMG_show_grp_image
    Created on : Nov 4, 2011, 3:03:46 PM
    Author     : manish.kishore
--%>

<%@page contentType="text/html"%>
<%@page import="com.EAMG.common.EAMG_FolderPath" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility" %>
<%
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
				String contextPath = request.getContextPath();
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                return;
            }
%>
<%
            Vector Xvector = new Vector();
            Vector Yvector = new Vector();
            Vector Refvector = new Vector();
            Vector Sidevector = new Vector();
            Vector Indexvector = new Vector();
            //System.out.println("q");
            String group_no = request.getParameter("group_no");
            Vector GrpBomVec = (Vector) session.getAttribute("GrpBomVec");
            Vector SeqNoVec = (Vector) GrpBomVec.elementAt(3);
            Vector IndexVec = (Vector) GrpBomVec.elementAt(5);
            Vector itemIndexVector = new Vector();
            for (int i = 0; i < IndexVec.size(); i++) {
                itemIndexVector.add(IndexVec.elementAt(i).toString());

            }
            

            EAMG_FolderPath obj = new EAMG_FolderPath(server_name, mainURL, ecatPath);
            //String group_imagesPath=obj.group_imagesPath();
            //System.out.println("group_imagesPath:"+group_imagesPath);

            try
            {
            Connection conn = holder.getConnection();

            String part_no = "";
            String index_no = "";
            int INDEX = 0;
            if (conn == null) {
                out.println("Connection not established");
            }
            Statement stm = conn.createStatement();
            Statement stm1 = conn.createStatement();
            //deleting extra part ref coords that are not in GROUP_KIT_BOM_CUSTOM.

            /*
            manish changes
            ResultSet rs2 = stm.executeQuery("select PART_NO,INDEX_NO from REF_COORD where GROUP_NO='" + group_no + "'");
            while (rs2.next()) {
                String comp = rs2.getString(1);
                String index = rs2.getString(2);
                ResultSet rs1 = stm1.executeQuery("select * from GROUP_KIT_BOM_CUSTOM where GRP_KIT_NO='" + group_no + "' and COMPONENT='" + comp + "' and INDEX_NO=" + index);
                if (!rs1.next()) {
                    //System.out.println("delete from REF_COORD where GROUP_NO='"+group_no+"' and PART_NO='"+comp+"' and INDEX_NO="+index);
                    PreparedStatement ps = conn.prepareStatement("delete from REF_COORD where GROUP_NO='" + group_no + "' and PART_NO='" + comp + "' and INDEX_NO=" + index);
                    ps.executeUpdate();
                    ps.close();
                }
                rs1.close();
            }
            rs2.close();
            stm1.close();*/



          /*  String isupdate = "select count(*) from REF_COORD where ref_no=0 and GROUP_NO='" + group_no + "'";
            //System.out.println("isupdate"+isupdate);
            ResultSet rst = stm.executeQuery(isupdate);
            if (rst.next()) {
                INDEX = rst.getInt(1);
            }
            rst.close();
            stm.close();
            if (INDEX > 0) {
                //System.out.println("update");
                Statement stmt = conn.createStatement();
                String sq = "select PART_NO from REF_COORD where GROUP_NO='" + group_no + "'";
                //System.out.println(sq);
                ResultSet rs = stmt.executeQuery(sq);
                while (rs.next()) {
                    part_no = rs.getString("PART_NO");

                    if (!part_no.equals("")) {
                        Statement stmt1 = conn.createStatement();
                        String sql1 = "select INDEX_NO from GROUP_KIT_BOM_CUSTOM where GRP_KIT_NO='" + group_no + "' and COMPONENT='" + part_no + "'";
                        //System.out.println(sql1);
                        ResultSet rs1 = stmt1.executeQuery(sql1);
                        while (rs1.next()) {
                            index_no = rs1.getString("INDEX_NO");

                            Statement stmt2 = conn.createStatement();
                            String s = "update REF_COORD set INDEX_NO='" + index_no + "' where GROUP_NO='" + group_no + "' and PART_NO='" + part_no + "'";
                            //System.out.println(s);
                            int res = 0;
                            res = stmt2.executeUpdate(s);
                            if (res == 1) {
                                //System.out.println("Table REF_COORD updated");
                            }
                        }
                    }
                }
            }
            else {
                //System.out.println("no need to update");
            }*/
            Statement stmt3 = conn.createStatement();
            int rev_no = 0;
            String sql = "select X,Y,component,SIDE,REF_NO from REF_COORD rc,cat_group_kit_bom gkb where rc.GROUP_NO=gkb.grp_kit_no and rc.GROUP_NO='" + group_no + "'";
            ResultSet rs3 = stmt3.executeQuery(sql);
            boolean flag = rs3.next();
            while (flag) {
                Xvector.add(rs3.getString(1));
                Yvector.add(rs3.getString(2));
                Refvector.add(rs3.getString(3));
                Sidevector.add(rs3.getString(4));
                Indexvector.add(rs3.getString(5));
                flag = rs3.next();
            }

            //conn.close();
%>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script language="javascript">


            var xxcor;
            var ycor;

            var IE = document.all?true:false
            if (!IE)document.captureEvents(Event.MOUSEMOVE);

            document.onmousemove=getMouseXY;


            var tempX = 0
            var tempY = 0

            function getMouseXY(e) {
                if (IE) {
                    tempX = event.clientX +document.documentElement.scrollLeft
                    tempY = event.clientY +document.documentElement.scrollTop
                } else {
                    tempX = e.pageX
                    tempY = e.pageY
                }
                if (tempX < 0){tempX = 0}
                if (tempY < 0){tempY = 0}

                return true
            }

            function storepoint(x,y, target){

                window.status=('X='+(x-target.offsetLeft-3)+',Y='+(y-target.offsetTop-27));
                document.getElementById("xs").innerHTML=(x-target.offsetLeft-3);
                document.getElementById("ys").innerHTML=(y-target.offsetTop-27);
                xxcor=x-target.offsetLeft-3;
                ycor=y-target.offsetTop-27;

            }

            function openfile()
            {
                var group_no='';
                group_no='<%=group_no%>';
                ycor=ycor+30;
                var url="EAMG_group_refcoord_input.jsp?x="+xxcor+"&y="+ycor+"&img="+group_no;
                window.open(url,'inputwindow','left=300,top=300,width=350,height=160,toolbar=0,status=0');

            }

            function delete_refcoord(ref_no,x,y,side,index)
            {
                var con=window.confirm("Are you sure you want to delete ref_no?");
                if(con==true)
                {

                    var url="EAMG_grp_refcoord_delete.jsp?x="+x+"&y="+y+"&refno="+ref_no+"&side="+side+"&del_index="+index;

                    var inputwindow=window.open(url,'deletewindow','left=0,top=10,width=300,height=100,toolbar=0,status=0');

                }

            }


        </script>


    </head>
    <body>
        <center>
            <table cellspacing=2 cellpadding=2 border=0 width=100%>
                <tr>
                    <td  height = 20 align="left"><font class="text">
                            X:<span id="xs">0</span>
                            Y:<span id="ys">0</span></font>
                    </td>
                    <!-- <td width="95%" align="left">
                         <font face="verdana" color=red size=1><marquee behavior="alternate" width="40%">Click on Image to add RefCoord </marquee>&nbsp;&nbsp;<marquee behavior="alternate" width="45%">Click RefCoord to Delete</marquee></font>
                     </td>-->
                </tr>
                <tr>
                    <td valign=top align="left" colspan="1">
                        <IMG alt="" SRC="<%=mainURL%>/dealer/ecat_print/group_jpg/<%=group_no%>.jpg"  border ="0" ONMOUSEMOVE="storepoint(tempX, tempY, this);" onclick="openfile();" />
                </tr>
            </table>
        </center>
        <%
            //System.out.println("Xvector:"+Xvector);
            //System.out.println("Yvector:"+Yvector);
            //System.out.println("Refvector:"+Refvector);
            //System.out.println("Indexvector:"+Indexvector);
            int length = Xvector.size();
            String side = "";
            String seqId = "";
            Object indxStr;
            int indexI = 0;
            int x = 0, y = 0;
            for (int i = 0; i < length; i++) {
                //indexId=Indexvector.elementAt(i).toString();
                side = "" + Sidevector.elementAt(i);
                if (side.equalsIgnoreCase("RIGHT")) {

                    x = 0;
                    y = 0;
                    indxStr = Indexvector.elementAt(i);
                    if (itemIndexVector.contains(indxStr)) {
                        indexI = itemIndexVector.indexOf(indxStr);
                        seqId = SeqNoVec.elementAt(indexI).toString();
                    }
                    if (indxStr.toString().length() == 2) {
                        x = Integer.parseInt((String) Xvector.elementAt(i)) + 10;
                        y = Integer.parseInt((String) Yvector.elementAt(i)) - 13;
                    }
                    else {
                        x = Integer.parseInt((String) Xvector.elementAt(i)) + 10;
                        y = Integer.parseInt((String) Yvector.elementAt(i)) - 13;
                    }
        %>
        <div title="<%=Refvector.elementAt(i)%>"  style="position:absolute; width:60px; height:20px; z-index:1; left: <%=x%>px; top: <%=y%>px;">
            <span><font face="verdana" color="#FF0000" size="1"><a href="#" onclick="return delete_refcoord('<%=Refvector.elementAt(i)%>','<%=Xvector.elementAt(i)%>','<%=Yvector.elementAt(i)%>','<%=Sidevector.elementAt(i)%>','<%=Indexvector.elementAt(i)%>');"><b><%=seqId%></b></a></font>
            </span></div>
            <%

                }
                else if (side.equalsIgnoreCase("LEFT")) {
                    x = 0;
                    y = 0;
                    indxStr = Indexvector.elementAt(i);
                    if (itemIndexVector.contains(indxStr)) {
                        indexI = itemIndexVector.indexOf(indxStr);
                        seqId = SeqNoVec.elementAt(indexI).toString();
                    }
                    if (indxStr.toString().length() == 2) {
                        x = Integer.parseInt((String) Xvector.elementAt(i)) - 13;
                        y = Integer.parseInt((String) Yvector.elementAt(i)) - 13;
                    }
                    else {
                        x = Integer.parseInt((String) Xvector.elementAt(i)) - 5;
                        y = Integer.parseInt((String) Yvector.elementAt(i)) - 13;
                    }
            %>
        <div title="<%=Refvector.elementAt(i)%>"  style="position:absolute; width:60px; height:20px; z-index:1; left: <%=x%>px; top: <%=y%>px;">
            <span><font color="#FF0000" face="verdana" size="1"><a href="#" onclick="return delete_refcoord('<%=Refvector.elementAt(i)%>','<%=Xvector.elementAt(i)%>','<%=Yvector.elementAt(i)%>','<%=Sidevector.elementAt(i)%>','<%=Indexvector.elementAt(i)%>');" ><b><%=seqId%></b></a></font>
            </span></div>
            <%
                }
                else if (side.equalsIgnoreCase("TOP")) {

                    x = 0;
                    y = 0;
                    indxStr = Indexvector.elementAt(i);
                    if (itemIndexVector.contains(indxStr)) {
                        indexI = itemIndexVector.indexOf(indxStr);
                        seqId = SeqNoVec.elementAt(indexI).toString();
                    }
                    if (indxStr.toString().length() == 2) {
                        x = Integer.parseInt((String) Xvector.elementAt(i)) - 2;
                        y = Integer.parseInt((String) Yvector.elementAt(i)) - 20;
                    }
                    else {
                        x = Integer.parseInt((String) Xvector.elementAt(i)) + 2;
                        y = Integer.parseInt((String) Yvector.elementAt(i)) - 20;
                    }
            %>
        <div title="<%=Refvector.elementAt(i)%>"  style="position:absolute; width:60px; height:20px; z-index:1; left: <%=x%>px; top: <%=y%>px;">
            <span><font face="verdana" color="#FF0000" size="1"><a href="#" onclick="return delete_refcoord('<%=Refvector.elementAt(i)%>','<%=Xvector.elementAt(i)%>','<%=Yvector.elementAt(i)%>','<%=Sidevector.elementAt(i)%>','<%=Indexvector.elementAt(i)%>');"><b><%=seqId%></b></a></font>
            </span></div>
            <%

                }
                else if (side.equalsIgnoreCase("BOTTOM")) {
                    x = 0;
                    y = 0;
                    indxStr = Indexvector.elementAt(i);
                    if (itemIndexVector.contains(indxStr)) {
                        indexI = itemIndexVector.indexOf(indxStr);
                        seqId = SeqNoVec.elementAt(indexI).toString();
                    }
                    if (indxStr.toString().length() == 2) {
                        x = Integer.parseInt((String) Xvector.elementAt(i)) - 2;
                        y = Integer.parseInt((String) Yvector.elementAt(i)) - 5;
                    }
                    else {
                        x = Integer.parseInt((String) Xvector.elementAt(i)) + 0;
                        y = Integer.parseInt((String) Yvector.elementAt(i)) - 5;
                    }
            %>
        <div title="<%=Refvector.elementAt(i)%>"  style="position:absolute; width:60px; height:20px; z-index:1; left: <%=x%>px; top: <%=y%>px;">
            <span><font face="verdana" color="#FF0000" size="1"><a href="#" onclick="return delete_refcoord('<%=Refvector.elementAt(i)%>','<%=Xvector.elementAt(i)%>','<%=Yvector.elementAt(i)%>','<%=Sidevector.elementAt(i)%>','<%=Indexvector.elementAt(i)%>');"><b><%=seqId%></b></a></font>
            </span></div>
            <%

                }
            }


            }catch(Exception e){e.printStackTrace();}%>
    </body>
</html>

