package viewEcat.comEcat;

/*
File Name: GroupBom.java
PURPOSE: To get the BOM of a Group
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class GroupBom {

    Connection conn;
    PrintWriter ps;
    String date_OR_serial;
    java.sql.Date inputDate = new java.sql.Date(0);
    java.sql.Date buckleDate = new java.sql.Date(0);
    String serialNo;
    String group;

    public GroupBom(Connection conn, PrintWriter ps, String date_OR_serial, java.sql.Date inputDate, String serialNo, String group, java.sql.Date buckleDate) {
        this.conn = conn;
        this.ps = ps;
        this.date_OR_serial = date_OR_serial;
        this.inputDate = inputDate;
        this.serialNo = serialNo;
        this.group = group;
        this.buckleDate = buckleDate;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String get_superceedence(PrintWriter ps, String part, String group) {
        String retrun_type = "";
        try {

            retrun_type = "NO";
            //Statement stmt;
            PreparedStatement stmt = null;
            //stmt = conn.createStatement();

            ResultSet rs;

            //rs = stmt.executeQuery("SELECT * FROM CAT_ECN_IMPL_HISTORY WHERE NEW_PART ='" + part + "' AND change_type ='Replaced' AND GROUP_ASSY_NO='" + group + "'");
            String query = ("SELECT * FROM CAT_ECN_IMPL_HISTORY(NOLOCK) WHERE NEW_PART ='" + part + "' AND change_type ='Replaced' AND GROUP_ASSY_NO='" + group + "'");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next()) {
                retrun_type = "YES";
            } else {
                retrun_type = "NO";
            }
            rs.close();
            stmt.close();

            return retrun_type;
        } catch (Exception e) {
            ps.println(e);
            e.printStackTrace();
            return retrun_type;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int no_array_parameters = 20;
    Vector printVec = new Vector();
    public String[][] print_data;
    //= new String[50000][no_array_parameters];
    public int print_data_counter = 0;
    public int part_serial_no = 0;

    /* public GroupBom()
    {
    print_data_counter = 0;
    part_serial_no = 0;
    for (int v = 0; v < 50000; v++)
    {
    for (int i = 0; i < no_array_parameters; i++)
    {
    print_data[v][i] = null;
    }
    }
    }
     */
    public GroupBom(String[][] tot_parts_arr, int tot_parts_arr_count) {
        print_data_counter = tot_parts_arr_count;
        print_data = new String[print_data_counter][no_array_parameters];
        for (int kk = 0; kk < print_data_counter; kk++) {
            for (int i = 0; i < no_array_parameters; i++) {
                print_data[kk][i] = tot_parts_arr[kk][i];
            }
        }
    }

    public GroupBom getGroupBom() {
        try {
            //Statement stmt1 = conn.createStatement();
        	PreparedStatement stmt = null;

            ResultSet rs, rs1;

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// V3

            String var_COMP_TYPE = "";
            String var_COMPONENT = "";
            String var_QTY = "";
            String var_SEQUENCE = "";
            String var_AS_REQUIRED = "";
            String interchangeability = "";
            String cutoffchassis = "";
            String cut_off = "";
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String var_SUPERCEEDENCE = "";
            // String var_HEADER_DESCRIPTION = "";
            String var_ALT_COMPONENT = "";
            String var_ALT_TYPE = "";
            String var_REF_NO = "";
            String var_DESCRIPTION = "";
            String var_SERVICEABLE = "";
            String var_REMARK = "";

            String var_HEADER_DESCRIPTION = "";

            PreparedStatement pstmt;

            int index=0;

            PreparedStatement pstmt1 = conn.prepareStatement("select p1,p2,p4,p5,part_type FROM CAT_PART(NOLOCK) where part_no=?");


            // java.sql.Date currentDate = new java.sql.Date((new java.util.Date()).getTime());

            if (date_OR_serial.equals("serialNo") || date_OR_serial.equals("model_date")) {
                //    pstmt = conn.prepareStatement("SELECT COMP_TYPE, COMPONENT, QTY, SEQUENCE, AS_REQUIRED,p1,p2,gkb.remarks,p4,p5,part_type FROM part p,group_kit_bom gkb WHERE gkb.component=p.part_no and gkb.GRP_KIT_NO =?  and ((FDATE is NULL and TDATE is NULL) or (FDATE is NULL and TDATE>?) or (FDATE <=? and TDATE>? and TDATE is not NULL) or (FDATE <=? and TDATE is NULL)) order by index_no");
                pstmt = conn.prepareStatement("SELECT COMP_TYPE, COMPONENT, QTY, SEQUENCE, AS_REQUIRED,remarks,INTERCHANGEABILITY,CUT_OFF_CHASSIS,CUT_OFF,INDEX_NO FROM CAT_GROUP_KIT_BOM(NOLOCK) WHERE GRP_KIT_NO =? and ((FDATE is NULL and TDATE is NULL) or (FDATE is NULL and TDATE>?) or (FDATE <=? and TDATE>? and TDATE is not NULL) or (FDATE <=? and TDATE is NULL)) order by index_no");
            } else {
                // pstmt = conn.prepareStatement("SELECT COMP_TYPE, COMPONENT, QTY, SEQUENCE, AS_REQUIRED,p1,p2,gkb.remarks,p4,p5,part_type FROM part p,GROUP_KIT_BOM gkb WHERE gkb.component=p.part_no and GRP_KIT_NO =?  order by index_no");
                pstmt = conn.prepareStatement("SELECT COMP_TYPE, COMPONENT, QTY, SEQUENCE, AS_REQUIRED,remarks,INTERCHANGEABILITY,CUT_OFF_CHASSIS,CUT_OFF,INDEX_NO FROM CAT_GROUP_KIT_BOM(NOLOCK) WHERE GRP_KIT_NO =? order by index_no");
            }

            pstmt.setString(1, group);

            //System.out.println("group.."+group);

            if (date_OR_serial.equals("model_date")) {
                pstmt.setDate(2, inputDate);
                pstmt.setDate(3, inputDate);
                pstmt.setDate(4, inputDate);
                pstmt.setDate(5, inputDate);
            } else if (date_OR_serial.equals("serialNo")) {
                pstmt.setDate(2, buckleDate);
                pstmt.setDate(3, buckleDate);
                pstmt.setDate(4, buckleDate);
                pstmt.setDate(5, buckleDate);
            }
//            else
//            {
//                pstmt.setDate(2, currentDate);
//                pstmt.setDate(3, currentDate);
//                pstmt.setDate(4, currentDate);
//                pstmt.setDate(5, currentDate);
//            }

            rs = pstmt.executeQuery();
            if (rs.next()) {
                do {
                    var_COMP_TYPE = rs.getString(1);
                    var_COMPONENT = rs.getString(2);
                    var_QTY = rs.getString(3);
                    var_SEQUENCE = rs.getString(4);
                    var_AS_REQUIRED = rs.getString(5);

                    var_SUPERCEEDENCE = get_superceedence(ps, var_COMPONENT, group);

                    printVec.add(var_COMPONENT);//0
                    //System.out.println("var_COMPONENT....."+var_COMPONENT);

                    printVec.add("NO");//1

                    printVec.add(var_SUPERCEEDENCE);//2

                    printVec.add(var_QTY);//3

                    printVec.add(var_COMP_TYPE);//4

                    printVec.add(var_SEQUENCE);//5
                    //System.out.println("var_SEQUENCE....."+var_SEQUENCE);

                    printVec.add(group);//6

                    printVec.add(var_AS_REQUIRED);//7

                    printVec.add("NO");//8

                    var_REMARK = rs.getString(6);
                    if(var_REMARK==null || "".equals(var_REMARK.trim()))
                    {
                      var_REMARK = "";
                    }


                    interchangeability = rs.getString(7);

                    if(interchangeability==null || "".equals(interchangeability.trim()))
                    {
                      interchangeability = "-";
                    }
                    cutoffchassis = rs.getString(8);
                    if(cutoffchassis==null  || "".equals(cutoffchassis.trim()))
                    {
                      cutoffchassis = "-";
                    }
                    
                    if (rs.getString(9) != null) {
                        try {
                            cut_off = sdf.format(rs.getDate(9));

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            cut_off="-";
                        }
                    }else{
                        cut_off="-";
                    }

                    pstmt1.setString(1, var_COMPONENT);
                    rs1 = pstmt1.executeQuery();
                    if (rs1.next()) {
                        var_DESCRIPTION = rs1.getString(1);
                        var_REF_NO = rs1.getString(2);

                        var_SERVICEABLE = rs1.getString(3);
                        var_HEADER_DESCRIPTION = rs1.getString(4);
                    } else {
                        var_DESCRIPTION = "";
                        var_REF_NO = "";

                        var_SERVICEABLE = "";
                        var_HEADER_DESCRIPTION = "";
                    }
                    rs1.close();
                    if (var_DESCRIPTION == null) {
                        var_DESCRIPTION = "";
                    }

                    if (var_HEADER_DESCRIPTION == null) {
                        var_HEADER_DESCRIPTION = "";
                    }

                    if (var_REF_NO == null) {
                        var_REF_NO = "";
                    }

                    if (var_REMARK == null) {
                        var_REMARK = "";
                    }


                    if (var_SERVICEABLE == null) {
                        var_SERVICEABLE = "";
                    }
                    printVec.add(var_REF_NO);   //9

                    printVec.add(var_DESCRIPTION.toUpperCase());   //10

                    printVec.add(var_SERVICEABLE);   //11

                    printVec.add(var_REMARK);   //12

                    printVec.add(var_HEADER_DESCRIPTION);   //13

                    printVec.add("YES");//14

                    printVec.add("YES");//15

                    printVec.add(interchangeability);//16
                    printVec.add(cutoffchassis);//17
                    printVec.add(cut_off);//18

                    index = rs.getInt(10);
                       printVec.add(""+index);//19

                    print_data_counter++;

                    if (var_COMP_TYPE.equals("ASM")) {
                        get_level_bom_n_qty(ps, var_COMPONENT, 1);
                    }

                    //rs1 = stmt1.executeQuery("SELECT CAT_ALTERNATE.ALTERNATE_COMPONENT,CAT_ALTERNATE.COMP_TYPE,p1,p2,p3,p4,p5,part_type FROM CAT_ALTERNATE , CAT_PART WHERE CAT_ALTERNATE.ALTERNATE_COMPONENT=CAT_PART.PART_NO and CAT_ALTERNATE.COMPONENT ='" + var_COMPONENT + "' AND CAT_ALTERNATE.GRP_KIT_NO ='" + group + "'");
                   String query = ("SELECT CAT_ALTERNATE.ALTERNATE_COMPONENT,CAT_ALTERNATE.COMP_TYPE,p1,p2,p3,p4,p5,part_type FROM CAT_ALTERNATE(NOLOCK) , CAT_PART(NOLOCK) WHERE CAT_ALTERNATE.ALTERNATE_COMPONENT=CAT_PART.PART_NO and CAT_ALTERNATE.COMPONENT ='" + var_COMPONENT + "' AND CAT_ALTERNATE.GRP_KIT_NO ='" + group + "'");
                   stmt = conn.prepareStatement(query);
                   rs1 = stmt.executeQuery();
                    if (rs1.next()) {

                        do {
                            var_ALT_COMPONENT = rs1.getString(3);
                            var_ALT_TYPE = rs1.getString(6);

                            if (var_ALT_TYPE.equals("ASM")) {
                                var_ALT_TYPE = "AA";
                            } else {
                                var_ALT_TYPE = "AP";
                            }

                            printVec.add(var_ALT_COMPONENT);//0

                            printVec.add(var_ALT_TYPE);//1

                            printVec.add("NO");//2

                            printVec.add(var_QTY);//3

                            printVec.add(var_COMP_TYPE);//4

                            printVec.add(var_SEQUENCE);//5

                            printVec.add(group);//6

                            printVec.add(var_AS_REQUIRED);//7

                            printVec.add("NO");//8

                            var_DESCRIPTION = rs1.getString(3);
                            var_REF_NO = rs1.getString(4);
                            var_REMARK = rs1.getString(5);
                            var_SERVICEABLE = rs1.getString(6);
                            var_HEADER_DESCRIPTION = rs1.getString(7);

                            if (var_DESCRIPTION == null) {
                                var_DESCRIPTION = "";
                            }

                            if (var_HEADER_DESCRIPTION == null) {
                                var_HEADER_DESCRIPTION = "";
                            }

                            if (var_REF_NO == null) {
                                var_REF_NO = "";
                            }

                            if (var_REMARK == null) {
                                var_REMARK = "";
                            }


                            if (var_SERVICEABLE == null) {
                                var_SERVICEABLE = "";
                            }
                            printVec.add(var_REF_NO);   //9

                            printVec.add(var_DESCRIPTION.toUpperCase());   //10

                            printVec.add(var_SERVICEABLE);   //11

                            printVec.add(var_REMARK);   //12

                            printVec.add(var_HEADER_DESCRIPTION);   //13

                            printVec.add("NO");//14

                            printVec.add("NO");//15

                            printVec.add(interchangeability);//16
                            printVec.add(cutoffchassis);//17
                            printVec.add(cut_off);//18
                            printVec.add(""+index);//19

                            print_data_counter++;

                            if (var_COMP_TYPE.equals("ASM")) {
                                get_level_bom_n_qty(ps, var_ALT_COMPONENT, 1);
                            }
                        } while (rs1.next());
                    }
                    rs1.close();

                } while (rs.next());
            }
            rs.close();

            pstmt.close();
           // stmt1.close();
            stmt.close();

            int cnt = 0;

            String[][] trim_groupArray = new String[print_data_counter][no_array_parameters];
            for (int v = 0; v < print_data_counter; v++) {
                for (int j = 0; j < no_array_parameters; j++) {
                    trim_groupArray[v][j] = "" + printVec.elementAt(cnt++);
                    //print_data[v][j];
                }
            }

            //GroupBom obj = new GroupBom(print_data, print_data_counter);
            GroupBom obj = new GroupBom(trim_groupArray, print_data_counter);
            return (obj);
        } catch (Exception e) {
            e.printStackTrace();
            String[][] vc = {
                {
                    "1", "1"
                }
            };
            GroupBom obj = new GroupBom(vc, 0);
            return (obj);
        }
    }

    public void get_level_bom_n_qty(PrintWriter ps, String subassy, int level) {
        try {
            //Statement stmt = conn.createStatement();

            ResultSet rs;

            String space_str = "";
            for (int j = 1; j <= level; j++) {
                space_str += "&nbsp;&nbsp;&nbsp;";
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// V3

            String var_COMP_TYPE = "";
            String var_COMPONENT = "";
            String var_QTY = "";

            String var_SUPERCEEDENCE = "";
            int flag = 0;

            String var_REF_NO = "";
            String var_DESCRIPTION = "";
            String var_SERVICEABLE = "";
            String var_REMARK = "";

            String var_HEADER_DESCRIPTION = "";
            PreparedStatement pstmt;

            java.sql.Date currentDate = new java.sql.Date((new java.util.Date()).getTime());

            pstmt = conn.prepareStatement("SELECT COMP_TYPE,COMPONENT,QTY,p1,p2,p3,p4,p5,part_type FROM CAT_ASSY_BOM(NOLOCK), CAT_PART(NOLOCK) WHERE CAT_PART.PART_NO=CAT_ASSY_BOM.COMPONENT and  ASSY_NO=?  and ((FDATE is NULL and TDATE is NULL) or (FDATE is NULL and TDATE>?) or (FDATE <=? and TDATE>? and TDATE is not NULL) or (FDATE <=? and TDATE is NULL))");
            pstmt.setString(1, subassy);

            if (date_OR_serial.equals("model_date")) {
                pstmt.setDate(2, inputDate);
                pstmt.setDate(3, inputDate);
                pstmt.setDate(4, inputDate);
                pstmt.setDate(5, inputDate);
            } else if (date_OR_serial.equals("serialNo")) {
                pstmt.setDate(2, buckleDate);
                pstmt.setDate(3, buckleDate);
                pstmt.setDate(4, buckleDate);
                pstmt.setDate(5, buckleDate);
            } else {
                pstmt.setDate(2, currentDate);
                pstmt.setDate(3, currentDate);
                pstmt.setDate(4, currentDate);
                pstmt.setDate(5, currentDate);
            }

            rs = pstmt.executeQuery();
            if (rs.next()) {
                flag = 1;
                do {
                    var_COMP_TYPE = rs.getString(1);
                    var_COMPONENT = rs.getString(2);
                    var_QTY = rs.getString(3);

                    var_SUPERCEEDENCE = get_superceedence(ps, var_COMPONENT, subassy);

                    printVec.add(space_str + var_COMPONENT);//0

                    printVec.add("NO");//1

                    printVec.add(var_SUPERCEEDENCE);//2

                    printVec.add(var_QTY);//3

                    printVec.add(var_COMP_TYPE);//4

                    printVec.add(null);//5

                    printVec.add(subassy);//6

                    printVec.add(null);//7

                    printVec.add("NO");//8

                    var_DESCRIPTION = rs.getString(4);
                    var_REF_NO = rs.getString(5);
                    var_REMARK = rs.getString(6);
                    var_SERVICEABLE = rs.getString(7);
                    var_HEADER_DESCRIPTION = rs.getString(8);


                    if (var_DESCRIPTION == null) {
                        var_DESCRIPTION = "";
                    }

                    if (var_HEADER_DESCRIPTION == null) {
                        var_HEADER_DESCRIPTION = "";
                    }

                    if (var_REF_NO == null) {
                        var_REF_NO = "";
                    }

                    if (var_REMARK == null) {
                        var_REMARK = "";
                    }


                    if (var_SERVICEABLE == null) {
                        var_SERVICEABLE = "";
                    }
                    printVec.add(var_REF_NO);   //9

                    printVec.add(var_DESCRIPTION.toUpperCase());   //10

                    printVec.add(var_SERVICEABLE);   //11

                    printVec.add(var_REMARK);   //12

                    printVec.add(var_HEADER_DESCRIPTION);   //13

                    printVec.add("NO");//14

                    printVec.add("NO");//15


                    print_data_counter++;

                    if (var_COMP_TYPE.equals("ASM")) {
                        get_level_bom_n_qty(ps, var_COMPONENT, (level + 1));
                    }
                } while (rs.next());
            }
            if (flag == 0) {
                if (printVec.size() > 15) {
                    printVec.setElementAt("NO", printVec.size() - 8);
                }
                //print_data[print_data_counter - 1][8] = "NO";
            }
            rs.close();

            pstmt.close();
        } catch (Exception e) {
            ps.println(e);
            e.printStackTrace();
        }
    }

    public GroupBom get_assyBOM(PrintWriter ps, String subassy, int level) {
        try {
           // Statement stmt = conn.createStatement();
        	PreparedStatement stmt = null;
        	ResultSet rs = null;
            String temp_1 = "";
            String temp_2 = "";
            String temp_3 = "";

            String var_REF_NO = "";
            String var_DESCRIPTION = "";
            String var_SERVICEABLE = "";
            String var_REMARK = "";

            String var_HEADER_DESCRIPTION = "";

            //ResultSet rs = stmt.executeQuery("SELECT COMP_TYPE,COMPONENT,QTY,p1,p2,p3,p4,p5,part_type FROM CAT_ASSY_BOM, CAT_PART WHERE CAT_PART.PART_NO=CAT_ASSY_BOM.COMPONENT and ASSY_NO ='" + subassy + "'");
            String query = ("SELECT COMP_TYPE,COMPONENT,QTY,p1,p2,p3,p4,p5,part_type FROM CAT_ASSY_BOM(NOLOCK), CAT_PART(NOLOCK) WHERE CAT_PART.PART_NO=CAT_ASSY_BOM.COMPONENT and ASSY_NO ='" + subassy + "'");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                do {
                    temp_1 = rs.getString(1);
                    temp_2 = rs.getString(2);
                    temp_3 = rs.getString(3);

                    printVec.add(temp_2);//0

                    printVec.add("NO");//1

                    printVec.add(get_superceedence(ps, temp_2, subassy));//2

                    printVec.add(temp_3);//3

                    printVec.add(temp_1);//4

                    printVec.add(null);//5

                    printVec.add(subassy);//6

                    printVec.add("NO");//7

                    printVec.add("NO");//8

                    var_DESCRIPTION = rs.getString(4);
                    var_REF_NO = rs.getString(5);
                    var_REMARK = rs.getString(6);
                    var_SERVICEABLE = rs.getString(7);
                    var_HEADER_DESCRIPTION = rs.getString(8);

                    if (var_DESCRIPTION == null) {
                        var_DESCRIPTION = "";
                    }

                    if (var_HEADER_DESCRIPTION == null) {
                        var_HEADER_DESCRIPTION = "";
                    }

                    if (var_REF_NO == null) {
                        var_REF_NO = "";
                    }

                    if (var_REMARK == null) {
                        var_REMARK = "";
                    }


                    if (var_SERVICEABLE == null) {
                        var_SERVICEABLE = "";
                    }
                    printVec.add(var_REF_NO);   //9

                    printVec.add(var_DESCRIPTION.toUpperCase());   //10

                    printVec.add(var_SERVICEABLE);   //11

                    printVec.add(var_REMARK);   //12

                    printVec.add(var_HEADER_DESCRIPTION);   //13

                    printVec.add("NO");//14

                    printVec.add("NO");//15

                    print_data_counter++;

                    if (temp_1.equals("ASM")) {
                        get_level_bom_n_qty(ps, temp_2, (level + 1));
                    }
                } while (rs.next());
            }
            rs.close();

            stmt.close();

            int cnt = 0;

            String[][] trim_groupArray = new String[print_data_counter][no_array_parameters];
            for (int v = 0; v < print_data_counter; v++) {
                for (int j = 0; j < no_array_parameters; j++) {
                    trim_groupArray[v][j] = "" + printVec.elementAt(cnt++);
                    //print_data[v][j];
                }
            }

            //GroupBom obj = new GroupBom(print_data, print_data_counter);
            GroupBom obj = new GroupBom(trim_groupArray, print_data_counter);
            return (obj);
        } catch (Exception e) {
            e.printStackTrace();
            ps.println(e);
            String[][] vc = {
                {
                    "1", "1"
                }
            };
            GroupBom obj = new GroupBom(vc, 0);
            return (obj);
        }
    }
}
