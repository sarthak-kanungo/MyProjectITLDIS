package viewEcat.comEcat;

/*
File Name: CompWhereUsedReport.java
PURPOSE: To find where the specific component is used.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class CompWhereUsedReport {

    Connection conn;

    public CompWhereUsedReport(Connection conn) {
        this.conn = conn;
    }
    Vector allASSY = new Vector();
    Vector allSKITS = new Vector();
    Vector allGROUPS = new Vector();
    Vector allMODELS = new Vector();

    public void getASSY(String component, java.sql.Date viewDate) {
        try {
            //java.sql.Date currentDate = new java.sql.Date((new java.util.Date()).getTime());

            PreparedStatement pstmt;
            ResultSet rs;

            pstmt = conn.prepareStatement("SELECT ASSY_NO FROM CAT_ASSY_BOM(NOLOCK) WHERE COMPONENT = ? and ((FDATE is NULL and TDATE is NULL) or (FDATE is NULL and TDATE>?) or (FDATE <=? and TDATE>? and TDATE is not NULL) or (FDATE <=? and TDATE is NULL))");
            pstmt.setString(1, component);
            pstmt.setDate(2, viewDate);
            pstmt.setDate(3, viewDate);
            pstmt.setDate(4, viewDate);
            pstmt.setDate(5, viewDate);

            // String sqlQuery = ;
            rs = pstmt.executeQuery();
            if (rs.next()) {
                do {
                    String temp_1 = rs.getString(1);
                    if (allASSY.indexOf(temp_1) == -1) {
                        allASSY.addElement(temp_1);
                        allMODELS.addElement(temp_1 + "@@@A");
                    } else {
                        continue;
                    }
                    getASSY(temp_1, viewDate);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void getSKITS(String component, java.sql.Date viewDate) {
        try {
           // Statement stmt;
           PreparedStatement stmt = null;
        	ResultSet rs;
            //stmt = conn.createStatement();
            String temp_1 = "";

            String sqlQuery = "SELECT KIT_NO FROM CAT_S_KIT_BOM(NOLOCK) WHERE COMPONENT = '" + component + "'";
           stmt = conn.prepareStatement(sqlQuery);
           rs = stmt.executeQuery();
            // rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) {
                do {
                    temp_1 = rs.getString(1);
                    if (allSKITS.indexOf(temp_1) == -1) {
                        allSKITS.addElement(temp_1);
                        allMODELS.addElement(temp_1 + "@@@K");
                    } else {
                        continue;
                    }
                } while (rs.next());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void getGROUPS(String component, java.sql.Date viewDate) {
        try {
            // java.sql.Date currentDate = new java.sql.Date((new java.util.Date()).getTime());

            PreparedStatement pstmt;
            ResultSet rs;
            String temp_1 = "";

            pstmt = conn.prepareStatement("SELECT GRP_KIT_NO FROM CAT_GROUP_KIT_BOM(NOLOCK) WHERE COMPONENT = ? and ((FDATE is NULL and TDATE is NULL) or (FDATE is NULL and TDATE>?) or (FDATE <=? and TDATE>? and TDATE is not NULL) or (FDATE <=? and TDATE is NULL))");
            pstmt.setString(1, component);
            pstmt.setDate(2, viewDate);
            pstmt.setDate(3, viewDate);
            pstmt.setDate(4, viewDate);
            pstmt.setDate(5, viewDate);

            // String sqlQuery = ;
            rs = pstmt.executeQuery();
            if (rs.next()) {
                do {
                    temp_1 = rs.getString(1);
                    if (allGROUPS.indexOf(temp_1) == -1) {
                        allGROUPS.addElement(temp_1);
                        allMODELS.addElement(temp_1 + "@@@G");
                    } else {
                        continue;
                    }
                } while (rs.next());
            }
            rs.close();

            pstmt.close();
/*
            Statement stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT OPTION_NO FROM OPTION_SERVICE_PART WHERE PART_NO = '" + component + "'");
            if (rs.next()) {
                do {
                    temp_1 = rs.getString(1);
                    if (allGROUPS.indexOf(temp_1) == -1) {
                        allGROUPS.addElement(temp_1);
                        allMODELS.addElement(temp_1 + "@@@G");
                    } else {
                        continue;
                    }
                } while (rs.next());
            }
            rs.close();
            stmt.close();
 
 */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void getGROUPS_KITS(String component, java.sql.Date viewDate) {
        try {
            //Statement stmt;
        	PreparedStatement stmt = null;
            ResultSet rs;
            //stmt = conn.createStatement();
            String temp_1 = "";

           // String sqlQuery = "SELECT GROUP_NO FROM CAT_GROUP_S_KIT WHERE KIT_NO = '" + component + "'";
           // rs = stmt.executeQuery(sqlQuery);
           String query = "SELECT GROUP_NO FROM CAT_GROUP_S_KIT(NOLOCK) WHERE KIT_NO = '" + component + "'";
           stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()) {
                do {
                    temp_1 = rs.getString(1);
                    if (allGROUPS.indexOf(temp_1) == -1) {
                        allGROUPS.addElement(temp_1);
                        allMODELS.addElement(temp_1 + "@@@G");
                    } else {
                        continue;
                    }
                } while (rs.next());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void getMODELS(String component, java.sql.Date viewDate, String productFamilySubQuery, String applicationTypSubQuery) {
        try {
            //Statement stmt;
        	PreparedStatement stmt = null;
            ResultSet rs;
            //stmt = conn.createStatement();
            String temp_1 = "";
            String subQuery = "";

            if (productFamilySubQuery.equals("")) {
                subQuery = " where MC.MODEL_NO= MG.MODEL_NO "+applicationTypSubQuery;
            } else {
                subQuery = productFamilySubQuery + " AND MC.MODEL_NO= MG.MODEL_NO "+applicationTypSubQuery;
            }

            //String sqlQuery = "SELECT distinct MC.MODEL_NO FROM CAT_MODEL_GROUP MG,CAT_MODEL_CLASSIFICATION MC "+subQuery+" AND  GROUP_NO = '" + component + "'";
            //rs = stmt.executeQuery(sqlQuery);
           String query = "SELECT distinct MC.MODEL_NO FROM CAT_MODEL_GROUP(NOLOCK) MG,CAT_MODEL_CLASSIFICATION(NOLOCK) MC "+subQuery+" AND  GROUP_NO = '" + component + "'";
           stmt = conn.prepareStatement(query);
           rs = stmt.executeQuery();
            if (rs.next()) {
                do {
                    temp_1 = rs.getString(1);
                    if (allMODELS.indexOf(temp_1) == -1) {
                        allMODELS.addElement(temp_1 + "@@@M");
                    } else {
                        continue;
                    }
                } while (rs.next());
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void getAlternate(String component, java.sql.Date viewDate) {
        try {
            // java.sql.Date currentDate = new java.sql.Date((new java.util.Date()).getTime());

            PreparedStatement pstmt;
            ResultSet rs;
            String temp_1 = "";
            pstmt = conn.prepareStatement("SELECT A.GRP_KIT_NO, A.COMPONENT, GKB.GRP_KIT_NO, GKB.COMPONENT FROM CAT_ALTERNATE(NOLOCK) A, CAT_GROUP_KIT_BOM(NOLOCK) GKB WHERE ALTERNATE_COMPONENT = ? AND A.GRP_KIT_NO = GKB.GRP_KIT_NO AND A.COMPONENT = GKB.COMPONENT and ((GKB.FDATE is NULL and GKB.TDATE is NULL) or (GKB.FDATE is NULL and GKB.TDATE>?) or (GKB.FDATE <=? and GKB.TDATE>? and GKB.TDATE is not NULL) or (GKB.FDATE <=? and GKB.TDATE is NULL))");
            pstmt.setString(1, component);
            pstmt.setDate(2, viewDate);
            pstmt.setDate(3, viewDate);
            pstmt.setDate(4, viewDate);
            pstmt.setDate(5, viewDate);


            rs = pstmt.executeQuery();
            if (rs.next()) {
                do {
                    temp_1 = rs.getString(1);
                    if (allGROUPS.indexOf(temp_1) == -1) {
                        allGROUPS.addElement(temp_1);
                        allMODELS.addElement(temp_1 + "@@@G");
                    } else {
                        continue;
                    }
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public String[] print_data;
    public int print_data_counter = 0;
    public int part_serial_no = 0;

    public CompWhereUsedReport() {
        allASSY.removeAllElements();
        allSKITS.removeAllElements();
        allGROUPS.removeAllElements();
        allMODELS.removeAllElements();
    }

    public CompWhereUsedReport(Vector allMODELS) {
        print_data_counter = allMODELS.size();
        print_data = new String[print_data_counter];
        for (int kk = 0; kk < print_data_counter; kk++) {
            print_data[kk] = "" + allMODELS.elementAt(kk);
        }
    }
    /////////////////////////////////////////////////////////////////////////////

    public CompWhereUsedReport getTotalModels(String component, java.sql.Date viewDate, String productFamilySubQuery, String applicationTypSubQuery) {
        try {
            // ADD INPUT COMPONENT TO THE MAIN VECTOR
            allASSY.addElement(component);

            // GET APPLICABLE ASSEMBIES FOR A PART 
            getASSY(component, viewDate);
            getGROUPS_KITS(component, viewDate);

            for (int i = 0; i < allASSY.size(); i++) {
                //GET ALTERNATES FOR A COMPONENT 
                getAlternate("" + allASSY.elementAt(i), viewDate);

                //GET APPLICABLE S KITS FOR ASSEMBLIES
                getSKITS("" + allASSY.elementAt(i), viewDate);

                //GET APPLICABLE GROUPS FOR ASSEMBLIES
                getGROUPS("" + allASSY.elementAt(i), viewDate);
            }

            // GET APPLICABLE GROUPS FOR S KITS
            for (int i = 0; i < allSKITS.size(); i++) {
                getGROUPS_KITS("" + allSKITS.elementAt(i), viewDate);
            }

            // GET APPLICABLE MODELS FOR GROUPS
            for (int i = 0; i < allGROUPS.size(); i++) {
                getMODELS("" + allGROUPS.elementAt(i), viewDate, productFamilySubQuery, applicationTypSubQuery);
            }

            CompWhereUsedReport obj = new CompWhereUsedReport(allMODELS);
            return (obj);
        } catch (Exception e) {
            Vector vc = new Vector();
            CompWhereUsedReport obj = new CompWhereUsedReport(vc);
            return (obj);
        }
    }
}
