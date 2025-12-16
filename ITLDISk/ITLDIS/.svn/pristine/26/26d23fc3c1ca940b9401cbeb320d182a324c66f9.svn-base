<%-- 
    Document   : EAMG_get_components_with_type
    Author     : avinash.pandey
--%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            Connection conn = null;
				String contextPath = request.getContextPath();
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                return;
            }
%>
<%
            String compno = request.getParameter("comp_no");
            if (compno != null && compno.equals("*")) {
                compno = "%";
            }
            String comptype = request.getParameter("comp_type");
            //String comptype = request.getParameter("comp_type");
            String creation = request.getParameter("creation");
            ////System.out.println("compno in jsp :"+compno);
            ////System.out.println("comptype in jsp :"+comptype);
            String compParamstr = "";
            conn = holder.getConnection();
            ResultSet rs;
            Statement stmt = null;
            stmt = conn.createStatement();
            String query = "";
            int i = 0;
            String comp_no = "";
            String comp_type="";
        
            //query = "Select  part_no  from part where  part_no LIKE '" + compno + "' order by part_no";
            if (comptype.equals("All")) {
                query = "Select part_no,part_type from cat_part where part_no LIKE ('" + compno + "') order by part_type desc, part_no";
            } else {
                query = "Select part_no,part_type from cat_part where part_type='" + comptype + "' and part_no LIKE '" + compno + "' order by part_no";

            }
            ////System.out.println("query:"+query);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                //comp_no = rs.getString(1);
                //compParamstr += comp_no + " (Part)" + "|";
                //i++;
                comp_no = rs.getString(1);
                comp_type = rs.getString(2);
                if (comp_type.equals("PRT") && (creation.equals("asm") || creation.equals("kit") || creation.equals("Tool"))) {
                    compParamstr += comp_no + " (Part)" + "|";
                } else if (comp_type.equals("ASM") && (creation.equals("asm"))) {
                    compParamstr += comp_no + " (Assembly)" + "|";
                } else if (comp_type.equals("KIT") && creation.equals("kit")) {
                    compParamstr += comp_no + " (Kit)" + "|";
                }
                 else if (comp_type.equals("TOOL") && creation.equals("Tool")) {
                    compParamstr += comp_no + " (Tool)" + "|";
                }
                i++;
            }

            rs.close();
            stmt.close();

            //conn.close();
            ////System.out.println("compParamstr :"+compParamstr);
            response.getWriter().write(compParamstr);



%>

