package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class getModelDescByProduct extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        //Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();
                String product = req.getParameter("value");
                String source = req.getParameter("source");
                //stmt = conn.createStatement();


                List<String> engineModelList = new ArrayList<String>();

                if (source.equalsIgnoreCase("products")) {
                   // rs = stmt.executeQuery("SELECT DISTINCT ENGINE_MODEL FROM CAT_MODEL_CLASSIFICATION where ENGINE_SERIES='" + product + "' order by ENGINE_MODEL");
                    String query = ("SELECT DISTINCT ENGINE_MODEL FROM CAT_MODEL_CLASSIFICATION(NOLOCK) where ENGINE_SERIES='" + product + "' order by ENGINE_MODEL");
                    stmt = conn.prepareStatement(query);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        engineModelList.add(rs.getString(1));
                    }
                    rs.close();


                    ps.println("<select name='modelTxt' style=\"width:210px\" id=\"modelTxt\" class=\"text\" onchange=\"javascript:getModelDesc(this.value,'models')\">");
//                    if(parduct.equalsIgnoreCase("All")){
                    ps.println("<option value='All' title='ALL'>ALL</option>");
//                    }else{
//                     ps.println("<option value='Select' >--Select--</option>");
//                    }
                    for (int i = 0; i < engineModelList.size(); i++) {
                        ps.println("<option value='" + engineModelList.get(i) + "' title='" + engineModelList.get(i) + "'>" + engineModelList.get(i) + "</option>");

                    }
                    ps.println("</select>");
                } else if (source.equalsIgnoreCase("models")) {
                    //rs = stmt.executeQuery("SELECT DISTINCT GROUP_TYPE FROM CAT_MODEL_GROUP MG, CAT_MODEL_CLASSIFICATION MC WHERE MG.MODEL_NO=MC.MODEL_NO AND MC.ENGINE_MODEL='" + product + "' ORDER BY GROUP_TYPE");
                    String query = ("SELECT DISTINCT GROUP_TYPE FROM CAT_MODEL_GROUP(NOLOCK) MG, CAT_MODEL_CLASSIFICATION(NOLOCK) MC WHERE MG.MODEL_NO=MC.MODEL_NO AND MC.ENGINE_MODEL='" + product + "' ORDER BY GROUP_TYPE");
                    stmt = conn.prepareStatement(query);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        engineModelList.add(rs.getString(1));
                    }
                    rs.close();


                    ps.println("<select name='aggTxt' style=\"width:210px\" id=\"aggTxt3\" class=\"text\" onchange=\"javascript:getModelDesc(this.value,'aggregates')\">");
//                    if(parduct.equalsIgnoreCase("All")){
                    ps.println("<option value='All' title='ALL'>ALL</option>");
//                    }else{
//                     ps.println("<option value='Select' >--Select--</option>");
//                    }
                    for (int i = 0; i < engineModelList.size(); i++) {
                        ps.println("<option value='" + engineModelList.get(i) + "' title='" + engineModelList.get(i) + "'>" + engineModelList.get(i) + "</option>");

                    }
                    ps.println("</select>");
                } else if (source.equalsIgnoreCase("aggregates")) {
                    String model = req.getParameter("model");
                   // rs = stmt.executeQuery("SELECT DISTINCT P1 FROM CAT_MODEL_CLASSIFICATION MC, CAT_MODEL_GROUP MG, CAT_GROUP_KIT_DETAIL GKD WHERE MC.MODEL_NO=MG.MODEL_NO  and MG.GROUP_NO=GKD.GRP_KIT_NO  AND MC.ENGINE_MODEL='" + model + "' AND MG.GROUP_TYPE='" + product + "' ORDER BY P1");
                   String query = ("SELECT DISTINCT P1 FROM CAT_MODEL_CLASSIFICATION(NOLOCK) MC, CAT_MODEL_GROUP(NOLOCK) MG, CAT_GROUP_KIT_DETAIL(NOLOCK) GKD WHERE MC.MODEL_NO=MG.MODEL_NO  and MG.GROUP_NO=GKD.GRP_KIT_NO  AND MC.ENGINE_MODEL='" + model + "' AND MG.GROUP_TYPE='" + product + "' ORDER BY P1");
                   stmt = conn.prepareStatement(query);
                   rs = stmt.executeQuery();
                    while (rs.next()) {
                        engineModelList.add(rs.getString(1));
                    }
                    rs.close();


                    ps.println("<select name='groupTxt' style=\"width:210px\" id=\"groupTxt3\" class=\"text\" >");
//                    if(parduct.equalsIgnoreCase("All")){
                    ps.println("<option value='All' title='ALL'>ALL</option>");
//                    }else{
//                     ps.println("<option value='Select' >--Select--</option>");
//                    }
                    for (int i = 0; i < engineModelList.size(); i++) {
                        ps.println("<option value='" + engineModelList.get(i) + "' title='" + engineModelList.get(i) + "'>" + engineModelList.get(i) + "</option>");
                    }
                    ps.println("</select>");

                }

            }
        } catch (Exception e) {
            ps.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                ps.close();
                wps.close();
            } catch (Exception e) {
            }
        }
    }
}
