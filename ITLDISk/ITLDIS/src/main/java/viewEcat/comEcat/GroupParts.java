package viewEcat.comEcat;

/*
File Name: GroupParts.java
PURPOSE: It is used to find the various parts, assy, kits in a group.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class GroupParts
{

    Connection conn;
    //String databaseNAME = ""; //***************************** FOR ACCESS
    //String databaseNAME = "upper"; //***************************** FOR ORACLE

    public GroupParts(Connection conn)
    {
        this.conn = conn;
    }
    Vector printVec = new Vector();
    public String[] print_data;// = new String[50000];
    public int print_data_counter = 0;
    public int part_serial_no = 0;

    /* public GroupParts()
    {
    print_data_counter = 0;
    part_serial_no = 0;
    
    for (int v = 0; v < 50000; v++)
    {
    print_data[v] = null;
    }
    }
     */
    public GroupParts(Vector printVec, int tot_parts_arr_count)
    {
        print_data_counter = tot_parts_arr_count;
        print_data = new String[print_data_counter];
        for (int kk = 0; kk < print_data_counter; kk++)
        {
            print_data[kk] = "" + printVec.elementAt(kk);
        }
    }

    ///////////////////////////////////////////////////// GET PARTS FOR ASSY ///////////////////////////////////////////////
    public void get_level_bom_n_qty(PrintWriter ps, String date_OR_serial, java.sql.Date inputDate, String serialNo, String subassy, int level, java.sql.Date buckleDate)
    {
        try
        {
            ResultSet rs;
            PreparedStatement pstmt;

            java.sql.Date currentDate = new java.sql.Date((new java.util.Date()).getTime());

            pstmt = conn.prepareStatement("SELECT COMPONENT,COMP_TYPE FROM CAT_GROUP_KIT_BOM(NOLOCK) WHERE GRP_KIT_NO =?  and ((FDATE is NULL and TDATE is NULL) or (FDATE is NULL and TDATE>?) or (FDATE <=? and TDATE>? and TDATE is not NULL) or (FDATE <=? and TDATE is NULL))");
            pstmt.setString(1, subassy);

            if (date_OR_serial.equals("model_date"))
            {
                pstmt.setDate(2, inputDate);
                pstmt.setDate(3, inputDate);
                pstmt.setDate(4, inputDate);
                pstmt.setDate(5, inputDate);
            }
            else if (date_OR_serial.equals("serialNo"))
            {
                pstmt.setDate(2, buckleDate);
                pstmt.setDate(3, buckleDate);
                pstmt.setDate(4, buckleDate);
                pstmt.setDate(5, buckleDate);
            }
            else
            {
                pstmt.setDate(2, currentDate);
                pstmt.setDate(3, currentDate);
                pstmt.setDate(4, currentDate);
                pstmt.setDate(5, currentDate);
            }


            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// V3

            /* String tableName_AB = "ASSY_BOM";
            
            int assyRevNo = 0;
            String new_old_assy = "";
            
            get_assyInfo oo = new get_assyInfo(conn, ps, date_OR_serial, inputDate, serialNo, subassy);
            assyRevNo = oo.get_assyRevisionNo();
            new_old_assy = oo.get_assyTable(assyRevNo);
            
            if (new_old_assy.equals("old"))
            {
            tableName_AB = "ASSY_BOM_OLD";
            }
            else
            {
            tableName_AB = "ASSY_BOM";
            }
             */
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// V3

            String temp_1 = "";
            String temp_2 = "";

            //rs = stmt.executeQuery("SELECT COMPONENT,COMP_TYPE FROM " + tableName_AB + " WHERE ASSY_NO ='" + subassy + "' AND REV_NO = " + assyRevNo);
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    temp_1 = rs.getString(1);
                    temp_2 = rs.getString(2);

                    printVec.add(temp_1);
                    //print_data[print_data_counter] = temp_1;
                    print_data_counter++;

                    if (!(temp_2.equals("PRT")))
                    {
                        get_level_bom_n_qty(ps, date_OR_serial, inputDate, serialNo, temp_1, (level + 1), buckleDate);
                    }
                }
                while (rs.next());
            }
            rs.close();
            pstmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////// GET PARTS FOR ASSY ENDS ///////////////////////////////////////////////////

    ///////////////////////////////////////////////////// GET PARTS FOR S KIT STARTS ///////////////////////////////////////////////////
    public void get_org_kit_parts(PrintWriter ps, String date_OR_serial, java.sql.Date inputDate, String serialNo, String subassy, int level, java.sql.Date buckleDate)
    {
        try
        {
            //Statement stmt = conn.createStatement();
        	PreparedStatement stmt = null;

            ResultSet rs;

            String temp_1 = "";
            String temp_2 = "";

            //rs = stmt.executeQuery("SELECT COMPONENT,COMP_TYPE FROM CAT_S_KIT_BOM WHERE KIT_NO = '" + subassy + "'");
            String query = ("SELECT COMPONENT,COMP_TYPE FROM CAT_S_KIT_BOM(NOLOCK) WHERE KIT_NO = '" + subassy + "'");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    temp_1 = rs.getString(1);
                    temp_2 = rs.getString(2);
                    printVec.add(temp_1);
                    // print_data[print_data_counter] = temp_1;
                    print_data_counter++;

                    if (!(temp_2.equals("PRT")))
                    {
                        get_level_bom_n_qty(ps, date_OR_serial, inputDate, serialNo, temp_1, (level + 1), buckleDate);
                    }
                }
                while (rs.next());
            }
            rs.close();
            stmt.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////// GET PARTS FOR S KIT ENDS ///////////////////////////////////////////////////
    public GroupParts getGroupParts(PrintWriter ps, String date_OR_serial, java.sql.Date inputDate, String serialNo, String groupNo, java.sql.Date buckleDate)
    {
        try
        {
           // Statement stmt, stmt1;
        	PreparedStatement stmt1 = null;
        	PreparedStatement stmt = null;
            //stmt = conn.createStatement();
            //stmt1 = conn.createStatement();

            ResultSet rs, rs1;

            PreparedStatement pstmt;

            java.sql.Date currentDate = new java.sql.Date((new java.util.Date()).getTime());

            pstmt = conn.prepareStatement("SELECT COMPONENT,COMP_TYPE FROM CAT_GROUP_KIT_BOM(NOLOCK) WHERE GRP_KIT_NO =?  and ((FDATE is NULL and TDATE is NULL) or (FDATE is NULL and TDATE>?) or (FDATE <=? and TDATE>? and TDATE is not NULL) or (FDATE <=? and TDATE is NULL))");
            pstmt.setString(1, groupNo);

            if (date_OR_serial.equals("model_date"))
            {
                pstmt.setDate(2, inputDate);
                pstmt.setDate(3, inputDate);
                pstmt.setDate(4, inputDate);
                pstmt.setDate(5, inputDate);
            }
            else if (date_OR_serial.equals("serialNo"))
            {
                pstmt.setDate(2, buckleDate);
                pstmt.setDate(3, buckleDate);
                pstmt.setDate(4, buckleDate);
                pstmt.setDate(5, buckleDate);
            }
            else
            {
                pstmt.setDate(2, currentDate);
                pstmt.setDate(3, currentDate);
                pstmt.setDate(4, currentDate);
                pstmt.setDate(5, currentDate);
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// V3

            /*String tableName_GKB = "GROUP_KIT_BOM";
            
            int revNo = 0;
            String new_old = "";
            
            Get_groupInfo ob_get_grp_inf = new Get_groupInfo();
            
            
            Get_groupInfo ob_get_rev_no = new Get_groupInfo(conn, ps, date_OR_serial, inputDate, serialNo, groupNo);
            revNo = ob_get_rev_no.get_groupRevisionNo();
            new_old = ob_get_rev_no.get_groupTable(revNo);
            
            if (new_old.equals("old"))
            {
            tableName_GKB = "GROUP_KIT_BOM_OLD";
            }
            else
            {
            tableName_GKB = "GROUP_KIT_BOM";
            }
             */
            String temp_1 = "";
            String temp_2 = "";
            String altPart = "";
            String var_ALT_TYPE = "";

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// V3

            //rs = stmt.executeQuery("SELECT COMPONENT,COMP_TYPE FROM " + tableName_GKB + " WHERE GRP_KIT_NO ='" + groupNo + "' AND REV_NO = " + revNo);
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    temp_1 = rs.getString(1);
                    temp_2 = rs.getString(2);

                    printVec.add(temp_1);
                    //print_data[print_data_counter] = temp_1;
                    print_data_counter++;

                    if (!(temp_2.equals("PRT")))
                    {
                        get_level_bom_n_qty(ps, date_OR_serial, inputDate, serialNo, temp_1, 1, buckleDate);
                    }

                    //rs1 = stmt1.executeQuery("SELECT * FROM CAT_ALTERNATE WHERE COMPONENT ='" + temp_1 + "' AND GRP_KIT_NO ='" + groupNo + "'");
                    String query = ("SELECT * FROM CAT_ALTERNATE(NOLOCK) WHERE COMPONENT ='" + temp_1 + "' AND GRP_KIT_NO ='" + groupNo + "'");
                    stmt1 = conn.prepareStatement(query);
                    rs1 = stmt1.executeQuery();
                    
                    if (rs1.next())
                    {
                        do
                        {
                            altPart = rs1.getString(3);
                            var_ALT_TYPE = rs1.getString(6);

                            printVec.add(altPart);
                            //  print_data[print_data_counter] = altPart;
                            print_data_counter++;

                            if (var_ALT_TYPE.equals("ASM"))
                            {
                                get_level_bom_n_qty(ps, date_OR_serial, inputDate, serialNo, altPart, 1, buckleDate);
                            }

                        }
                        while (rs1.next());
                    }
                    rs1.close();
                }
                while (rs.next());
            }
            rs.close();
            stmt1.close();
            pstmt.close();

          /*  rs = stmt.executeQuery("SELECT PART_NO FROM OPTION_SERVICE_PART WHERE OPTION_NO ='" + groupNo + "'");
            if (rs.next())
            {
                do
                {
                    printVec.add(rs.getString(1));
                    // print_data[print_data_counter] = rs.getString(1);
                    print_data_counter++;

                }
                while (rs.next());
            }
            rs.close();
*/
           // rs = stmt.executeQuery("SELECT KIT_NO FROM CAT_GROUP_S_KIT WHERE GROUP_NO ='" + groupNo + "'");
            String query = ("SELECT KIT_NO FROM CAT_GROUP_S_KIT(NOLOCK) WHERE GROUP_NO ='" + groupNo + "'");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    printVec.add(rs.getString(1));
                    // print_data[print_data_counter] = rs.getString(1);
                    print_data_counter++;

                    get_org_kit_parts(ps, date_OR_serial, inputDate, serialNo, temp_1, 1, buckleDate);

                }
                while (rs.next());
            }
            rs.close();

            stmt.close();


            GroupParts obj = new GroupParts(printVec, print_data_counter);
            return (obj);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Vector vc = new Vector();
            vc.add("1");
            GroupParts obj = new GroupParts(vc, 0);
            return (obj);
        }
    }

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    public void get_level_bom_n_qty_refined(PrintWriter ps, String subassy, int level, String partNo, java.sql.Date viewDate)
    {
        try
        {
            ResultSet rs;

           // java.sql.Date currentDate = new java.sql.Date((new java.util.Date()).getTime());

            PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT ASSY_NO FROM CAT_ASSY_BOM(NOLOCK) WHERE ASSY_NO LIKE = ? and ((FDATE is NULL and TDATE is NULL) or (FDATE is NULL and TDATE>?) or (FDATE <=? and TDATE>? and TDATE is not NULL) or (FDATE <=? and TDATE is NULL))");
            pstmt.setString(1, partNo);
            pstmt.setDate(2, viewDate);
            pstmt.setDate(3, viewDate);
            pstmt.setDate(4, viewDate);
            pstmt.setDate(5, viewDate);


            String temp_1 = "";
            //rs = stmt.executeQuery("SELECT DISTINCT ASSY_NO FROM ASSY_BOM WHERE ASSY_NO LIKE '" + partNo + "'");
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    temp_1 = rs.getString(1);
                    if (temp_1.equals(subassy))
                    {
                        printVec.add(temp_1);
                        //print_data[print_data_counter] = temp_1;
                        print_data_counter++;
                    }
                }
                while (rs.next());
            }
            rs.close();
            pstmt.close();

            pstmt = conn.prepareStatement("SELECT COMPONENT FROM CAT_ASSY_BOM(NOLOCK) WHERE ASSY_NO = ? and AND COMPONENT LIKE ? AND COMP_TYPE = 'PRT' and ((FDATE is NULL and TDATE is NULL) or (FDATE is NULL and TDATE>?) or (FDATE <=? and TDATE>? and TDATE is not NULL) or (FDATE <=? and TDATE is NULL))");
            pstmt.setString(1, subassy);
            pstmt.setString(2, partNo);
            pstmt.setDate(3, viewDate);
            pstmt.setDate(4, viewDate);
            pstmt.setDate(5, viewDate);
            pstmt.setDate(6, viewDate);

            //rs = stmt.executeQuery("SELECT COMPONENT FROM ASSY_BOM WHERE ASSY_NO ='" + subassy + "' AND COMPONENT LIKE '" + partNo + "' AND COMP_TYPE = 'PRT'");
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    printVec.add(rs.getString(1));
                    // print_data[print_data_counter] = rs.getString(1);
                    print_data_counter++;
                }
                while (rs.next());
            }
            rs.close();
            pstmt.close();

            pstmt = conn.prepareStatement("SELECT COMPONENT FROM CAT_ASSY_BOM(NOLOCK) WHERE ASSY_NO = ? and AND COMP_TYPE = 'ASM' and ((FDATE is NULL and TDATE is NULL) or (FDATE is NULL and TDATE>?) or (FDATE <=? and TDATE>? and TDATE is not NULL) or (FDATE <=? and TDATE is NULL))");
            pstmt.setString(1, subassy);
            pstmt.setDate(2, viewDate);
            pstmt.setDate(3, viewDate);
            pstmt.setDate(4, viewDate);
            pstmt.setDate(5, viewDate);

            // rs = stmt.executeQuery("SELECT COMPONENT FROM ASSY_BOM WHERE ASSY_NO ='" + subassy + "' AND COMP_TYPE = 'ASM'");
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    temp_1 = rs.getString(1);

                    get_level_bom_n_qty_refined(ps, temp_1, 1, partNo, viewDate);

                }
                while (rs.next());
            }
            rs.close();

            pstmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////// GET PARTS FOR S KIT STARTS ///////////////////////////////////////////////////
    public void get_org_kit_parts_refined(PrintWriter ps, String subassy, int level, String partNo, java.sql.Date viewDate)
    {
        try
        {
            //Statement stmt = conn.createStatement();
        	PreparedStatement stmt = null;
            ResultSet rs;

            String temp_1 = "";
            //rs = stmt.executeQuery("SELECT DISTINCT KIT_NO FROM CAT_S_KIT_BOM WHERE KIT_NO LIKE '" + partNo + "'");
           String query = ("SELECT DISTINCT KIT_NO FROM CAT_S_KIT_BOM(NOLOCK) WHERE KIT_NO LIKE '" + partNo + "'");
           stmt = conn.prepareStatement(query);
           rs = stmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    temp_1 = rs.getString(1);
                    if (temp_1.equals(subassy))
                    {
                        printVec.add(temp_1);
                        //print_data[print_data_counter] = temp_1;
                        print_data_counter++;
                    }
                }
                while (rs.next());
            }
            rs.close();

            //rs = stmt.executeQuery("SELECT COMPONENT FROM CAT_S_KIT_BOM WHERE KIT_NO = '" + subassy + "' AND COMP_TYPE = 'PRT' AND COMPONENT LIKE '" + partNo + "'");
            String query1 = ("SELECT COMPONENT FROM CAT_S_KIT_BOM(NOLOCK) WHERE KIT_NO = '" + subassy + "' AND COMP_TYPE = 'PRT' AND COMPONENT LIKE '" + partNo + "'");
            stmt = conn.prepareStatement(query1);
            rs = stmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    printVec.add(rs.getString(1));
                    // print_data[print_data_counter] = rs.getString(1);
                    print_data_counter++;
                }
                while (rs.next());
            }
            rs.close();

            //rs = stmt.executeQuery("SELECT COMPONENT FROM CAT_S_KIT_BOM WHERE KIT_NO = '" + subassy + "' AND COMP_TYPE = 'ASM'");
           String query2 = ("SELECT COMPONENT FROM CAT_S_KIT_BOM(NOLOCK) WHERE KIT_NO = '" + subassy + "' AND COMP_TYPE = 'ASM'");
           stmt = conn.prepareStatement(query2);
           rs = stmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    temp_1 = rs.getString(1);
                    get_level_bom_n_qty_refined(ps, temp_1, (level + 1), partNo, viewDate);

                }
                while (rs.next());
            }
            rs.close();

            stmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////// GET PARTS FOR S KIT ENDS ///////////////////////////////////////////////////
    public GroupParts getGroupPartsRefined(PrintWriter ps, String groupNo, String partNo, java.sql.Date viewDate)
    {
        try
        {
            //Statement stmt = conn.createStatement();
        	PreparedStatement stmt = null;
           // java.sql.Date currentDate = new java.sql.Date((new java.util.Date()).getTime());

            PreparedStatement pstmt = conn.prepareStatement("SELECT COMPONENT FROM CAT_GROUP_KIT_BOM(NOLOCK) WHERE GRP_KIT_NO = ? AND COMPONENT LIKE ? AND COMP_TYPE = 'PRT' and ((FDATE is NULL and TDATE is NULL) or (FDATE is NULL and TDATE>?) or (FDATE <=? and TDATE>? and TDATE is not NULL) or (FDATE <=? and TDATE is NULL))");
            pstmt.setString(1, groupNo);
            pstmt.setString(2, partNo);
            pstmt.setDate(3, viewDate);
            pstmt.setDate(4, viewDate);
            pstmt.setDate(5, viewDate);
            pstmt.setDate(6, viewDate);

            ResultSet rs;

            String temp_1 = "";
            String altPart = "";
            //rs = stmt.executeQuery("SELECT COMPONENT FROM GROUP_KIT_BOM WHERE GRP_KIT_NO ='" + groupNo + "' AND COMPONENT LIKE '" + partNo + "' AND COMP_TYPE = 'PRT'");
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    printVec.add(rs.getString(1));
                    //   print_data[print_data_counter] = rs.getString(1);
                    print_data_counter++;
                }
                while (rs.next());
            }
            rs.close();
            pstmt.close();
            
            pstmt = conn.prepareStatement("SELECT COMPONENT FROM CAT_GROUP_KIT_BOM(NOLOCK) WHERE GRP_KIT_NO = ? AND COMP_TYPE = 'ASM' and ((FDATE is NULL and TDATE is NULL) or (FDATE is NULL and TDATE>?) or (FDATE <=? and TDATE>? and TDATE is not NULL) or (FDATE <=? and TDATE is NULL))");
            pstmt.setString(1, groupNo);
            pstmt.setDate(2, viewDate);
            pstmt.setDate(3, viewDate);
            pstmt.setDate(4, viewDate);
            pstmt.setDate(5, viewDate);

           // rs = stmt.executeQuery("SELECT COMPONENT FROM GROUP_KIT_BOM WHERE GRP_KIT_NO ='" + groupNo + "' AND COMP_TYPE = 'ASM'");
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    temp_1 = rs.getString(1);
                    get_level_bom_n_qty_refined(ps, temp_1, 1, partNo, viewDate);

                }
                while (rs.next());
            }
            rs.close();
            pstmt.close();

            //rs = stmt.executeQuery("SELECT * FROM CAT_ALTERNATE WHERE GRP_KIT_NO ='" + groupNo + "' AND COMP_TYPE = 'PRT' AND ALTERNATE_COMPONENT LIKE '" + partNo + "'");
           String query = ("SELECT * FROM CAT_ALTERNATE(NOLOCK) WHERE GRP_KIT_NO ='" + groupNo + "' AND COMP_TYPE = 'PRT' AND ALTERNATE_COMPONENT LIKE '" + partNo + "'");
           stmt = conn.prepareStatement(query);
           rs = stmt.executeQuery();
           if (rs.next())
            {
                do
                {
                    printVec.add(rs.getString(3));
                    //print_data[print_data_counter] = rs.getString(3);
                    print_data_counter++;
                }
                while (rs.next());
            }
            rs.close();

           // rs = stmt.executeQuery("SELECT * FROM CAT_ALTERNATE WHERE GRP_KIT_NO ='" + groupNo + "' AND COMP_TYPE = 'ASM'");
           String query1 =  ("SELECT * FROM CAT_ALTERNATE(NOLOCK) WHERE GRP_KIT_NO ='" + groupNo + "' AND COMP_TYPE = 'ASM'");
            stmt = conn.prepareStatement(query1);
            rs = stmt.executeQuery();
           if (rs.next())
            {
                do
                {
                    altPart = rs.getString(3);
                    get_level_bom_n_qty_refined(ps, altPart, 1, partNo, viewDate);
                }
                while (rs.next());
            }
            rs.close();

          /*  rs = stmt.executeQuery("SELECT PART_NO FROM OPTION_SERVICE_PART WHERE OPTION_NO ='" + groupNo + "' AND PART_NO LIKE '" + partNo + "'");
            if (rs.next())
            {
                do
                {
                    temp_1 = rs.getString(1);
                    printVec.add(temp_1);
                    // print_data[print_data_counter] = temp_1;
                    print_data_counter++;

                }
                while (rs.next());
            }
            rs.close();
*/
           // rs = stmt.executeQuery("SELECT KIT_NO FROM CAT_GROUP_S_KIT WHERE GROUP_NO ='" + groupNo + "'");
            String query2 = ("SELECT KIT_NO FROM CAT_GROUP_S_KIT(NOLOCK) WHERE GROUP_NO ='" + groupNo + "'");
            stmt = conn.prepareStatement(query2);
            rs = stmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    temp_1 = rs.getString(1);
                    get_org_kit_parts_refined(ps, temp_1, 1, partNo, viewDate);

                }
                while (rs.next());
            }
            rs.close();
            stmt.close();

            GroupParts obj = new GroupParts(printVec, print_data_counter);
            return (obj);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Vector vc = new Vector();
            vc.add("1");
            GroupParts obj = new GroupParts(vc, 0);
            return (obj);
        }
    }
}
