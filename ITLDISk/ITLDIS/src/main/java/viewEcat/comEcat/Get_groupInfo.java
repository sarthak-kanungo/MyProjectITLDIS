package viewEcat.comEcat;

/*
File Name: Get_groupInfo.java
PURPOSE: To get the information of a group like its revision no, image path,etc.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.Date;
//import java.util.Arrays;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class Get_groupInfo
{

    /*    Connection conn;
    PrintWriter ps;
    String date_OR_serial;
    java.sql.Date inputDate = new java.sql.Date(0);
    String serialNo;
    String group;
    int revisionNo = 0;
    int cmt_Rev_No = 0;
    int cmt_Rev_No_img = 0;
    String new_old = "";
    String imagePath = "";
    
    Get_groupInfo(Connection conn, PrintWriter ps, String date_OR_serial, java.sql.Date inputDate, String serialNo, String group)
    {
    this.conn = conn;
    this.ps = ps;
    this.date_OR_serial = date_OR_serial;
    this.inputDate = inputDate;
    this.serialNo = serialNo;
    this.group = group;
    
    revisionNo = 0;
    cmt_Rev_No_img = 0;
    cmt_Rev_No = 0;
    new_old = "";
    imagePath = "";
    }
     */
    /*	Functions Used:
    1. get_groupRevisionNo		-		GET GROUP REVISION NO FROM GROUP_REVISIONS
    2. get_groupTable			-		FIND WHICH TABLE TO GO TO OLD OR NEW
    3. get_groupImagePath		-		GET IMAGE PATH
     */

//	1. get_groupRevisionNo	////////////////////////// GET GROUP REVISION NO FROM GROUP_REVISIONS ///////////////////////////////////////////////////////
 /*   public int get_groupRevisionNo()
    {
    try
    {
    Statement stmt;
    stmt = conn.createStatement();
    
    ResultSet rs;
    
    if (date_OR_serial.equals("model_date"))
    {
    int counter = 0;
    
    rs = stmt.executeQuery("SELECT revision FROM change_mgmt WHERE change_type = 'Group Modified' AND component = '" + group + "' AND effective_date = {d '1970-01-01'}");
    if (rs.next())
    {
    do
    {
    counter = 1;
    }
    while (rs.next());
    }
    rs.close();
    
    if (counter == 1)
    {
    rs = stmt.executeQuery("SELECT MAX(revision) FROM change_mgmt WHERE change_type = 'Group Modified' AND component = '" + group + "'");
    if (rs.next())
    {
    do
    {
    cmt_Rev_No = rs.getInt(1);
    }
    while (rs.next());
    }
    rs.close();
    }
    else
    {
    rs = stmt.executeQuery("SELECT MAX(revision) FROM change_mgmt WHERE change_type = 'Group Modified' AND component = '" + group + "' AND effective_date <= {d '" + inputDate + "'}");
    if (rs.next())
    {
    do
    {
    cmt_Rev_No = rs.getInt(1);
    }
    while (rs.next());
    }
    rs.close();
    }
    
    rs = stmt.executeQuery("SELECT MAX(REV_NO) FROM GROUP_REVISIONS WHERE GRP_KIT_NO = '" + group + "' AND CMT_REV_NO <=" + cmt_Rev_No);
    if (rs.next())
    {
    do
    {
    revisionNo = rs.getInt(1);
    }
    while (rs.next());
    }
    rs.close();
    }
    else if (date_OR_serial.equals("serialNo"))
    {
    int intSerialNo = 0;
    try
    {
    intSerialNo = Integer.parseInt(serialNo);
    }
    catch (Exception v)
    {
    intSerialNo = 0;
    }
    
    rs = stmt.executeQuery("SELECT MAX(revision) FROM change_mgmt WHERE change_type = 'Group Modified' AND component = '" + group + "' AND serialNo_ID <=" + intSerialNo);
    if (rs.next())
    {
    do
    {
    cmt_Rev_No = rs.getInt(1);
    }
    while (rs.next());
    }
    rs.close();
    
    rs = stmt.executeQuery("SELECT MAX(REV_NO) FROM GROUP_REVISIONS WHERE GRP_KIT_NO = '" + group + "' AND CMT_REV_NO <=" + cmt_Rev_No);
    if (rs.next())
    {
    do
    {
    revisionNo = rs.getInt(1);
    }
    while (rs.next());
    }
    rs.close();
    
    }
    else
    {
    rs = stmt.executeQuery("SELECT MAX(REV_NO) FROM GROUP_KIT_BOM WHERE GRP_KIT_NO = '" + group + "'");
    if (rs.next())
    {
    do
    {
    revisionNo = rs.getInt(1);
    }
    while (rs.next());
    }
    rs.close();
    }
    
    stmt.close();
    return revisionNo;
    }
    catch (Exception e)
    {
    ps.println(e);
    return revisionNo;
    }
    }
    
    //	2. get_groupTable   //////////////////////////////////// FIND WHICH TABLE TO GO TO OLD OR NEW ///////////////////////////////////////////////////////
    public String get_groupTable(int revNo)
    {
    try
    {
    Statement stmt;
    stmt = conn.createStatement();
    
    ResultSet rs;
    
    rs = stmt.executeQuery("SELECT REV_NO FROM GROUP_KIT_BOM WHERE GRP_KIT_NO = '" + group + "' AND REV_NO =" + revNo);
    if (rs.next())
    {
    new_old = "new";
    }
    else
    {
    new_old = "old";
    }
    rs.close();
    
    stmt.close();
    return new_old;
    }
    catch (Exception e)
    {
    ps.println(e);
    return new_old;
    }
    }
    
    //	3. get_groupImagePath	///////////////////////////////////// GET IMAGE PATH ///////////////////////////////////////////////////////
    public String get_groupImagePath()
    {
    try
    {
    Statement stmt;
    stmt = conn.createStatement();
    
    ResultSet rs;
    
    if (date_OR_serial.equals("model_date"))
    {
    int counter = 0;
    
    rs = stmt.executeQuery("SELECT revision FROM change_mgmt WHERE change_type = 'Group Modified' AND component = '" + group + "' AND effective_date = {d '1970-01-01'}");
    if (rs.next())
    {
    do
    {
    counter = 1;
    }
    while (rs.next());
    }
    rs.close();
    
    if (counter == 1)
    {
    rs = stmt.executeQuery("SELECT MAX(revision) FROM change_mgmt WHERE change_type = 'Group Modified' AND component = '" + group + "'");
    if (rs.next())
    {
    do
    {
    cmt_Rev_No_img = rs.getInt(1);
    }
    while (rs.next());
    }
    rs.close();
    }
    else
    {
    rs = stmt.executeQuery("SELECT MAX(revision) FROM change_mgmt WHERE change_type = 'Group Modified' AND component = '" + group + "' AND effective_date <= {d '" + inputDate + "'}");
    if (rs.next())
    {
    do
    {
    cmt_Rev_No_img = rs.getInt(1);
    }
    while (rs.next());
    }
    rs.close();
    }
    }
    else if (date_OR_serial.equals("serialNo"))
    {
    int intSerialNo = 0;
    try
    {
    intSerialNo = Integer.parseInt(serialNo);
    }
    catch (Exception v)
    {
    intSerialNo = 0;
    }
    
    rs = stmt.executeQuery("SELECT MAX(revision) FROM change_mgmt WHERE change_type = 'Group Modified' AND component = '" + group + "' AND serialNo_ID <=" + intSerialNo + " AND serialNo_ID <> 0");
    if (rs.next())
    {
    do
    {
    cmt_Rev_No_img = rs.getInt(1);
    }
    while (rs.next());
    }
    rs.close();
    }
    else
    {
    imagePath = "new";
    }
    
    ///////////////////// AFTER GETTING CMT_REV_NO GO TO IMAGE_BACKUP FOR GETTING URL ////////////////////////
    
    if (date_OR_serial.equals("model_date") || date_OR_serial.equals("serialNo"))
    {
    rs = stmt.executeQuery("SELECT REVISION_NO FROM IMAGE_BACKUP WHERE GROUP_NO = '" + group + "' AND REVISION_NO > " + cmt_Rev_No_img + " ORDER BY REVISION_NO");
    if (rs.next())
    {
    imagePath = "" + rs.getInt(1);
    }
    else
    {
    imagePath = "new";
    }
    rs.close();
    }
    
    stmt.close();
    return imagePath;
    }
    catch (Exception e)
    {
    ps.println(e);
    return imagePath;
    }
    }
     */
    public String getAltOrReplBy(Connection conn, String part)
    {
        String altReplBy = "";
        String change_type = "";
        String alt_replBy_item = "";
        boolean flag = false;
//        try
//        {
//            PreparedStatement pstmt = conn.prepareStatement("Select ITEM_TYPE,ALTERNATE_REPLACE_ITEM from ALTERNATE_REPLACE_BY where ITEM_CODE=?");
//            pstmt.setString(1, part);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next())
//            {
//
//                do
//                {
//                    change_type = rs.getString(1);
//                    alt_replBy_item = rs.getString(2);
//
//                    if (altReplBy.indexOf(alt_replBy_item) == -1)
//                    {
//                        if (flag)
//                        {
//                            altReplBy = altReplBy + "<br>";
//                        }
//                        if (change_type.equalsIgnoreCase("Alternate"))
//                        {
//                            altReplBy = altReplBy + "ALT BY " + alt_replBy_item + "&nbsp;";
//                        }
//                        else if (change_type.equalsIgnoreCase("Replaced By"))
//                        {
//                            altReplBy = altReplBy + "REP BY " + alt_replBy_item + "&nbsp;";
//                        }
//                        flag = true;
//                    }
//
//                }
//                while (rs.next());
//            }
//            rs.close();
//            pstmt.close();
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        return altReplBy;

    }

    public Vector getPartHistory(boolean flag, Connection conn, String component, String groupNo, Vector superVec, String spaceString, java.sql.Date sqlEffDate)
    {
        String replaced_comp = "";
        String partOrAssy = "PRT";
        String EFFECTIVE_TSN="";
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    //    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

        try
        {
           // Statement stmt = conn.createStatement();
        	PreparedStatement stmt =  null;
        	PreparedStatement stmt1 = null;
            //Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            ResultSet rs, rs1, rs2;

            if (flag)
            {
                //    rs = stmt.executeQuery("SELECT replaced_comp,revision,serialNo_ID FROM change_mgmt WHERE component ='" + component + "' AND change_type ='Replaced' AND changed_group_engine = '" + groupNo + "' order by revision desc");
                //commented by annpurna
            	//rs = stmt.executeQuery("SELECT OLD_PART,EFFECTIVE_DATE,EFFECTIVE_TSN FROM CAT_ECN_IMPL_HISTORY WHERE NEW_PART ='" + component + "' AND change_type ='Replaced' AND GROUP_ASSY_NO = '" + groupNo + "' order by EFFECTIVE_DATE desc");
                String query = ("SELECT OLD_PART,EFFECTIVE_DATE,EFFECTIVE_TSN FROM CAT_ECN_IMPL_HISTORY(NOLOCK) WHERE NEW_PART ='" + component + "' AND change_type ='Replaced' AND GROUP_ASSY_NO = '" + groupNo + "' order by EFFECTIVE_DATE desc");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
            }
            else
            {
                //   rs = stmt.executeQuery("SELECT replaced_comp,revision,serialNo_ID FROM change_mgmt WHERE component ='" + component + "' AND change_type ='Replaced' AND changed_group_engine = '" + groupNo + "' and revision<=" + revision + " order by revision desc");
                //rs = stmt.executeQuery("SELECT OLD_PART,EFFECTIVE_DATE,EFFECTIVE_TSN FROM CAT_ECN_IMPL_HISTORY WHERE NEW_PART ='" + component + "' AND change_type ='Replaced' AND GROUP_ASSY_NO = '" + groupNo + "' and EFFECTIVE_DATE<= {d '" + sqlEffDate + "'} order by EFFECTIVE_DATE desc");
                String query = ("SELECT OLD_PART,EFFECTIVE_DATE,EFFECTIVE_TSN FROM CAT_ECN_IMPL_HISTORY(NOLOCK) WHERE NEW_PART ='" + component + "' AND change_type ='Replaced' AND GROUP_ASSY_NO = '" + groupNo + "' and EFFECTIVE_DATE<= {d '" + sqlEffDate + "'} order by EFFECTIVE_DATE desc");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
            }
            if (rs.next())
            {
                String description = "-";
                do
                {
                    //    printString = "";
                    //    revision = rs.getInt(2);
                    partOrAssy = "PRT";

                    replaced_comp = rs.getString(1);
                    sqlEffDate = rs.getDate(2);
                    EFFECTIVE_TSN=rs.getInt(3)+"";
                    description = "-";

                    //rs1 = stmt1.executeQuery("select * from CAT_PART where part_no ='" + replaced_comp + "'");
                    String query = ("select * from CAT_PART where part_no ='" + replaced_comp + "'");
                    stmt1 = conn.prepareStatement(query);
                    rs1 = stmt1.executeQuery();
                    if (rs1.next())
                    {
                        partOrAssy = rs1.getString("part_type");
                        description = rs1.getString(6);
                    }
                   /* else
                    {
                        rs2 = stmt2.executeQuery("select * from assy_detail where assy_no ='" + replaced_comp + "'");
                        if (rs2.next())
                        {
                            partOrAssy = "ASM";
                            description = rs2.getString(7);
                        }
                        rs2.close();
                    }
                   */ rs1.close();

                    if ((replaced_comp != null) && (!replaced_comp.equals("")))
                    {
                        if (!superVec.contains(replaced_comp))
                        {
                            superVec.add(partOrAssy);
                            superVec.add(replaced_comp);
                            superVec.add(description);
                            //superVec.add("" + df.format(df.parse(sdf1.format(sqlEffDate))));
                            superVec.add("" + df.format(sqlEffDate));
                            superVec.add(spaceString);
                            superVec.add(EFFECTIVE_TSN);
                            //  superVec.add(serialNo);

                            superVec = getPartHistory(false, conn, replaced_comp, groupNo, superVec, spaceString + "", sqlEffDate);
                        }
                    }
                }
                while (rs.next());
            }
            rs.close();
            stmt2.close();
            stmt1.close();
            stmt.close();



            return superVec;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return superVec;
        }
    }
    
    
    public Vector getPartDeleteHistory(boolean flag, Connection conn, String component, String groupNo, Vector superVec, String spaceString, java.sql.Date sqlEffDate)
    {
        String replaced_comp = "";
        String partOrAssy = "PRT";
        String EFFECTIVE_TSN="";
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    //    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

        try
        {
            //Statement stmt = conn.createStatement();
        	PreparedStatement stmt = null;
        	PreparedStatement stmt1 = null;
            //Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            ResultSet rs, rs1, rs2;

            if (flag)
            {
                //    rs = stmt.executeQuery("SELECT replaced_comp,revision,serialNo_ID FROM change_mgmt WHERE component ='" + component + "' AND change_type ='Replaced' AND changed_group_engine = '" + groupNo + "' order by revision desc");
                //rs = stmt.executeQuery("SELECT OLD_PART,EFFECTIVE_DATE,EFFECTIVE_TSN FROM CAT_ECN_IMPL_HISTORY WHERE change_type ='Deleted' AND GROUP_ASSY_NO = '" + groupNo + "' order by EFFECTIVE_DATE desc");
                String query = ("SELECT OLD_PART,EFFECTIVE_DATE,EFFECTIVE_TSN FROM CAT_ECN_IMPL_HISTORY(NOLOCK) WHERE change_type ='Deleted' AND GROUP_ASSY_NO = '" + groupNo + "' order by EFFECTIVE_DATE desc");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
            }
            else
            {
                //   rs = stmt.executeQuery("SELECT replaced_comp,revision,serialNo_ID FROM change_mgmt WHERE component ='" + component + "' AND change_type ='Replaced' AND changed_group_engine = '" + groupNo + "' and revision<=" + revision + " order by revision desc");
                //rs = stmt.executeQuery("SELECT OLD_PART,EFFECTIVE_DATE,EFFECTIVE_TSN FROM CAT_ECN_IMPL_HISTORY WHERE NEW_PART ='" + component + "' AND change_type ='Replaced' AND GROUP_ASSY_NO = '" + groupNo + "' and EFFECTIVE_DATE<= {d '" + sqlEffDate + "'} order by EFFECTIVE_DATE desc");
                String query = ("SELECT OLD_PART,EFFECTIVE_DATE,EFFECTIVE_TSN FROM CAT_ECN_IMPL_HISTORY(NOLOCK) WHERE NEW_PART ='" + component + "' AND change_type ='Replaced' AND GROUP_ASSY_NO = '" + groupNo + "' and EFFECTIVE_DATE<= {d '" + sqlEffDate + "'} order by EFFECTIVE_DATE desc");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
            }
            if (rs.next())
            {
                String description = "-";
                do
                {
                    //    printString = "";
                    //    revision = rs.getInt(2);
                    partOrAssy = "PRT";

                    replaced_comp = rs.getString(1);
                    sqlEffDate = rs.getDate(2);
                    EFFECTIVE_TSN=rs.getInt(3)+"";
                    description = "-";

                    //rs1 = stmt1.executeQuery("select * from CAT_PART where part_no ='" + replaced_comp + "'");
                    String query = ("select * from CAT_PART(NOLOCK) where part_no ='" + replaced_comp + "'");
                    stmt1 = conn.prepareStatement(query);
                    rs1 = stmt1.executeQuery();
                    if (rs1.next())
                    {
                        partOrAssy = rs1.getString("part_type");
                        description = rs1.getString(6);
                    }
                  /*  else
                    {
                        rs2 = stmt2.executeQuery("select * from assy_detail where assy_no ='" + replaced_comp + "'");
                        if (rs2.next())
                        {
                            partOrAssy = "ASM";
                            description = rs2.getString(7);
                        }
                        rs2.close();
                    }
                  */  rs1.close();

                    if ((replaced_comp != null) && (!replaced_comp.equals("")))
                    {
                        if (!superVec.contains(replaced_comp))
                        {
                            superVec.add(partOrAssy);
                            superVec.add(replaced_comp);
                            superVec.add(description);
                            //superVec.add("" + df.format(df.parse(sdf1.format(sqlEffDate))));
                            superVec.add("" + df.format(sqlEffDate));
                            superVec.add(spaceString);
                            superVec.add(EFFECTIVE_TSN);
                            //  superVec.add(serialNo);

                            superVec = getPartHistory(false, conn, replaced_comp, groupNo, superVec, spaceString + "", sqlEffDate);
                        }
                    }
                }
                while (rs.next());
            }
            rs.close();
            stmt2.close();
            stmt1.close();
            stmt.close();



            return superVec;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return superVec;
        }
    }
    
    
     public String getDescription(String partno, Connection conn)
    {
        String description = "";
        try
        {
            ResultSet rs = null;
           // Statement stmt = conn.createStatement();
            PreparedStatement stmt = null;
            String sqlQuery = "";

            String component_available = "N";

            //rs = stmt.executeQuery("SELECT part_no FROM CAT_PART WHERE part_no = '" + partno + "'");
            String query = ("SELECT part_no FROM CAT_PART(NOLOCK) WHERE part_no = '" + partno + "'");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next())
            {
                component_available = "Y";
                sqlQuery = "SELECT p1 FROM CAT_PART WHERE part_no ='" + partno + "'";
            }
          /*  else
            {
                rs.close();
               rs = stmt.executeQuery("SELECT assy_no FROM assy_detail WHERE assy_no = '" + partno + "'");
                if (rs.next())
                {
                    component_available = "Y";
                    sqlQuery = "SELECT p1 FROM ASSY_DETAIL WHERE ASSY_NO ='" + partno + "'";
                }
                else
                {
                    rs.close();
                    rs = stmt.executeQuery("SELECT KIT_NO FROM S_KIT_DETAIL WHERE KIT_NO = '" + partno + "'");
                    if (rs.next())
                    {
                        component_available = "Y";
                        sqlQuery = "SELECT DESCRIPTION FROM S_KIT_DETAIL WHERE KIT_NO ='" + partno + "'";
                    }
                }
            }
           */ rs.close();

            if (component_available.equals("N"))
            {
                description = "--";
            }
            else
            {
                //$4
                //rs = stmt.executeQuery(sqlQuery);
            	 stmt = conn.prepareStatement(sqlQuery);
                
                 rs = stmt.executeQuery();
                if (rs.next())
                {
                    description = rs.getString(1);
                    if (description == null)
                    {
                        description = "--";
                    }
                    else
                    {
                        description = description.toUpperCase();
                    }
                }
                rs.close();
            }

            stmt.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return description;
    }
}
