/*
 * AT4_ValidateGroupInsertion.java
 *
 * Created on Jan 16, 2012, 4:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/*
 *
File Name   : 	EAMG_ValidateGroupInsertion.java
PURPOSE     :
1. To validate Group Details from Database.
2. To validate Group BOM from Database.
3. To insert group details and its BOM in database.
HISTORY     :
DATE        	BUILD     	AUTHOR    	MODIFICATIONS
21/02/12	1.0		Avinash pandey	$$1 	Created
 *

 ** S.No.   Method                          Author          Version     Methods Detail
 *    1.     validateGroup                  Mina Yadav      1.0         Group Insertion Process starts and ends in this method.
 *    2.     insertGroupParameters          Mina Yadav      1.0         To insert Group Parameter values in Database.
 *    3.     insertGroupBom                 Mina Yadav      1.0         This methods calls a loop to pick entire group BOM.
 *    4.     getItem                        Mina Yadav      1.0         Picks up level=1 component insert details and call callBomInsertion for assembly and its bom.
 *    5.     callBomInsertion               Mina Yadav      1.0         insert recursively components for assembly details and its BOM.
 *    6.     getPartParamIndex              Mina Yadav      1.0          method that returns a vector with part parameters and their column indexes in excel.
 *    7.     getAssyParamIndex              Mina Yadav      1.0          method that returns a vector with assembly parameters and their column indexes in excel.
 *    8.     getGrpBomLevelParamIndexVec    Mina Yadav      1.0          method that returns a vector with group BOM parameters and their column indexes in excel.
 *    9.     insertCompInDB                 Mina Yadav      1.0          inserts component and its parameters in database.


 */
package EAMG.Group.Action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import com.EAMG.common.EAMG_MethodsUtility;

import EAMG.Group.DAO.EAMGGroupDAO_R;
import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author mina.yadav
 */
public class EAMG_ValidateGroupInsertion {
    //Declaring Class Variables

    Connection conn = null;
    EAMG_MethodsUtility method_obj = new EAMG_MethodsUtility();
    String group_no = "";
    String group_desc = "";
    Vector partParamVec = null;
    Vector assyParamVec = null;
    Vector grpBomLevelParamvec = null;
    Vector groupInsertVec = new Vector();
    Vector compVec = null;
    String group_remarks = "";
    int countIndexNo = 1;
    PreparedStatement ps = null;

    /** Creates a new instance of AT4_ValidateGroupInsertion */
    public EAMG_ValidateGroupInsertion() {
    }

    public String validateGroup(String group_no, Vector compVec, File xlsfile, String user_id, Connection conn) throws Exception {
        this.conn = conn;// initialising conn

        this.group_no = group_no;
        this.compVec = compVec;
        // String write_query="";
        String result = "";
        int row = 2;// to pick Group Number row index=2.
        int column = 1;// column index=1.
        Workbook workbook1 = Workbook.getWorkbook(xlsfile);
        Sheet sheet = workbook1.getSheet(0);// getting first sheet of excel workbook
        row++;// incrementing row to pick group description
        group_desc = sheet.getCell(column, row).getContents().trim();
//        row++;// incrementing row to pick group Remarks
//        group_remarks = sheet.getCell(column, row + 1).getContents().trim();

       
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yy");

        // To check Group Existence in database
        boolean isGroupAlreadyExists = method_obj.checkCompExistsInDatabase(group_no, "GRP", conn);
        if (isGroupAlreadyExists == true) {
            result = "Group Already Exists in the Database.";
        } else {
            // insert group in GROUP_KIT_DETAIL
            //write_query="insert into GROUP_KIT_DETAIL values('"+group_no+"','"+group_desc+"','"+method_obj.getSQLTodaysDate()+"','"+user_id+"',"+method_obj.getCD_No(conn)+","+method_obj.getPatch_No(conn)+",'NP','G');";

            ps = conn.prepareStatement("insert into cat_group_kit_detail(GRP_KIT_NO,p1,CREATION_DATE,CREATOR,CD_NO,PATCH_NO,REMARKS,IMAGE_SOURCE,P3,GK_TYPE) values(?,?,?,?,?,?,?,?,?,?)");
            // ps=conn.prepareStatement("insert into GROUP_KIT_DETAIL values(?,?,?,?,?,?,?,?)");
            ps.setString(1, group_no);
            ps.setString(2, group_desc);
            ps.setDate(3,new java.sql.Date(new java.util.Date().getTime()));
            ps.setString(4, user_id);
            ps.setInt(5, 1);
            ps.setInt(6, 0);
            ps.setString(7, " ");
            ps.setString(8, "NP");
            ps.setString(9, " ");
            ps.setString(10, "G");
            ps.executeUpdate();
            ps.close();
            row++;//increment row to take Group parameter values.

            result = insertGroupBom(row, sheet, user_id);

        }

        return result;
    }

    public int insertGroupParameters(int row, Sheet sheet) throws Exception {
        ////System.out.println("i am in insertGroupParameters");

        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        //String write_query="";
        ResultSet rs, rs1;
        String param_desc = "";
        String cell_param_contents = "";
        int param_id = 0;
        // write_query="NA";


        //delete existing group parameter values if exists.
        //write_query="delete from COMP_PARAM_VALUES where COMP_NO='"+group_no+"' and COMP_TYPE='GRP';";

        ps = conn.prepareStatement("delete from COMP_PARAM_VALUES where COMP_NO=? and COMP_TYPE=?");
        ps.setString(1, group_no);
        ps.setString(2, "GRP");
        ps.executeUpdate();
        ps.close();
        //insertion of group parameters pick all group parameters from COMP_PARAM_MASTER
        pst = conn.prepareStatement("select PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='GRP' order by PARAM_DESC");
        rs = pst.executeQuery();
        while (rs.next()) {
            param_desc = rs.getString(1);
            cell_param_contents = sheet.getCell(1, row).getContents().trim();
            if (!cell_param_contents.equals("")) {
                param_id = 0;

                //take param_id from COMP_PARAM_MASTER for Group Parameter to insert parameter value.
                pst1 = conn.prepareStatement("select PARAM_ID from COMP_PARAM_MASTER(NOLOCK) where PARAM_DESC=? and PARAM_TYPE=?");
                pst1.setString(1, param_desc);
                pst1.setString(2, "GRP");
                rs1 = pst1.executeQuery();
                if (rs1.next()) {
                    param_id = rs1.getInt(1);

                }
                rs1.close();

                //write_query="select * from COMP_PARAM_VALUES where COMP_NO='"+group_no+"' and PARAM_ID="+param_id+";";

                //rs1=st1.executeQuery("select * from COMP_PARAM_VALUES where COMP_NO='"+group_no+"' and PARAM_ID="+param_id);
                pst1 = conn.prepareStatement("select * from COMP_PARAM_VALUES(NOLOCK) where COMP_NO=? and PARAM_ID=?");
                pst1.setString(1, group_no);
                pst1.setInt(2, param_id);
                rs1 = pst1.executeQuery();
                if (!rs1.next()) {
                    //insert group parameter value
                    //write_query="insert into COMP_PARAM_VALUES values ('"+group_no+"','GRP',"+param_id+",'"+cell_param_contents+"');";

                    ps = conn.prepareStatement("insert into COMP_PARAM_VALUES values (?,?,?,?)");
                    ps.setString(1, group_no);
                    ps.setString(2, "GRP");
                    ps.setInt(3, param_id);
                    ps.setString(4, cell_param_contents);
                    ps.executeUpdate();

                }
                rs1.close();
                pst1.close();
            }
            row++;//increment to pick next parameter
        }
        rs.close();
        pst.close();

        return row;//return row index after group parameters
    }
    //Declaring class variables.
    int row_index = 0;
    int bom_header_index = 0;
    Vector partParamIndexesVec = new Vector();
    Vector assyParamIndexesVec = new Vector();
    Vector grpBomLevelParamIndexesVec = new Vector();

    public String insertGroupBom(int row, Sheet sheet, String user_id) throws Exception {
        row_index = row + 2;//increment row to pick Group BOM
        bom_header_index = row_index - 1;// initializes GROUP BOM Header index

        //while loop to get component numbers till row index reaches end value.
        while (!sheet.getCell(0, row_index).getContents().trim().equalsIgnoreCase("end")) {
            String result = getItem(sheet, user_id);
            if (!result.equals("success") || result.equals("end")) {
                if (result.equals("end")) {
                    result = "success";
                }
                return result;// return result from Group BOM insertion.
            }
        }
        return "success";
    }// end Of insertGroupBom method
    //Declaring Class Variables.
    int last_level = 0;// initialize last_level=0
    String assy_no = "";
    int assy_level = 0;

    public String getItem(Sheet sheet, String user_id) throws Exception {

        if (sheet.getCell(0, row_index).getContents().trim().equals("end")) {
            return "success";
        }
        String comp_no = sheet.getCell(1, row_index).getContents().trim();
        String comp_Desc = sheet.getCell(2, row_index).getContents().trim();
        comp_no = comp_no.toUpperCase();//Change part number to its uppercase

        //insert component in compVec Vector
        if (!compVec.contains(comp_no)) {
            compVec.addElement(comp_no);
        }

        //pick component description
        String group_remark = sheet.getCell(4, row_index).getContents().trim();
        //pick Qty value
        String qty_str = sheet.getCell(3, row_index).getContents().trim();
        String intercgange = sheet.getCell(5, row_index).getContents().trim();
        String cut_off_chassis = sheet.getCell(6, row_index).getContents().trim();
        String cut_off = sheet.getCell(7, row_index).getContents().trim();
        SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat sd1 = new SimpleDateFormat("dd/MM/yyyy");
        String date = cut_off.trim();
        if (date.equalsIgnoreCase("")) {
            date = null;
        } else {
            date = sd.format(sd1.parse(date).getTime());
        }
        //cut_off = sd.format(sd1.parse(cut_off));
        String qty = "0";
        if (!qty_str.equals("AR")) {
            qty = qty_str;
            //qty = Integer.parseInt(qty_str);
        }

        String seq_no = sheet.getCell(0, row_index).getContents().trim();
        EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        if (!dao.isCompExist(comp_no)) {
            ps = conn.prepareStatement("Insert into cat_part (part_no,part_type,CREATION_DATE,CREATOR,CD_NO,PATCH_NO, p1) values(?,?,?,?,?,?,?)");
            ps.setString(1, comp_no);
            ps.setString(2, "PRT");
            ps.setDate(3, methodutil.getSQLTodaysDate());
            ps.setString(4, user_id);
            ps.setInt(5, 1);//insert 1
            ps.setInt(6, 0);//insert 0
            ps.setString(7, comp_Desc);// Part Desc
            //ps.setString(8, "");// p3
            //ps.setString(9, "");// p4
            //ps.setString(10, "");// np4
            ps.executeUpdate();
            ps.close();
        }

        ps = conn.prepareStatement("insert into CAT_GROUP_KIT_BOM(GRP_KIT_NO,COMPONENT,COMP_TYPE,QTY,AS_REQUIRED,SEQUENCE,INDEX_NO,REMARKS, INTERCHANGEABILITY, CUT_OFF_CHASSIS, CUT_OFF  )  values(?,?,?,?,?,?,?,?,?,?,?)");
        //ps = conn.prepareStatement("insert into GROUP_KIT_BOM values (?,?,?,?,?,?,?)");
        ps.setString(1, group_no);
        ps.setString(2, comp_no);
        ps.setString(3, "PRT");
        if (qty_str.equals("AR")) {
            ps.setString(4, "0");//insert 0
            ps.setString(5, "YES");// as_required as 'YES'
            //write_query="insert into GROUP_KIT_BOM values ('"+group_no+"','"+comp_no+"','"+comp_type+"','0','YES','"+seq_no+"',0);";

        } else {
            ps.setString(4, qty);//String 0
            ps.setString(5, "NO");// as_required as 'YES'
            // write_query="insert into GROUP_KIT_BOM values ('"+group_no+"','"+comp_no+"','"+comp_type+"','"+qty+"','NO','"+seq_no+"',0);";
        }
        ps.setString(6, seq_no);
        ps.setInt(7, countIndexNo++);
        ps.setString(8,group_remark);
        ps.setString(9,intercgange.toUpperCase());
        ps.setString(10,cut_off_chassis.toUpperCase());
        ps.setString(11,date);
        ps.executeUpdate();
        ps.close();
        ////////////////////////end of if Bom Insertion recursion is not called//////////
        row_index++;
        return "success";
    }
    // Declaring Class level Assembly and Assembly Level Vectors
    Vector assyVec = new Vector();
    Vector assyLevelVec = new Vector();

    public String callBomInsertion(String assy_no1, int assy_level1, Sheet sheet, String user_id) throws Exception {
        //int index=assyLevelVec.indexOf(assy_level1);
        //if(index!=-1)// if current assembly level exists in assyLevelVec
        //{
        //for(int i=index;i<assyLevelVec.size();i++)
        //{
        // assyLevelVec.remove(i);// remove to new assembly of same level
        // assyVec.remove(i);
        //}
        //}

        // String write_query="";
        ////////System.out.println("in callbom insertion");
        //////System.out.println("row in cbinsertion="+row_index+"****"+sheet.getCell(0,row_index).getContents().trim());
        if (sheet.getCell(0, row_index).getContents().trim().equals("end")) {
            return "end";// if end reaches return end.
        }
        //pick current level
        int curr_level = Integer.parseInt(sheet.getCell(0, row_index).getContents().trim());
//////System.out.println("curr level in cbi="+curr_level);

        int index = -1;
        for (int i = 0; i < assyLevelVec.size(); i++) {
            if (assy_level1 == ((Integer.parseInt("" + assyLevelVec.elementAt(i))))) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            assyLevelVec.removeElementAt(index);
            assyVec.removeElementAt(index);
        }

        if (!assyVec.contains(assy_no1))// add assembly no and its index if not already exists.
        {
            assyLevelVec.add(assy_level1);
            assyVec.add(assy_no1);
        }

        //System.out.println("assyLevelVec:"+assyLevelVec);
        //System.out.println("assyVec:"+assyVec);


        //pick current component
        String comp_no = sheet.getCell(2, row_index).getContents().trim();
        comp_no = comp_no.toUpperCase();// uppercase

        //insert in comp vector
        if (!compVec.contains(comp_no)) {
            compVec.addElement(comp_no);
        }

        //pick component description
        String comp_desc = sheet.getCell(3, row_index).getContents().trim();
        //pick component type
        String comp_type = sheet.getCell(4, row_index).getContents().trim();
//System.out.println("comp type in cbi="+comp_type);
        //pick qty value
        String qty_str = sheet.getCell(5, row_index).getContents().trim();
        int qty = 0;
        if (!qty_str.equals("AR")) {
            qty = Integer.parseInt(qty_str);
        }

        ///////////////////insertion of component///////////////////////////////////////////////////
        //System.out.println("2comp_no:"+comp_no+"*********comp_type:"+comp_type);
        String result = validateComponents(comp_no, comp_type, comp_desc, user_id, row_index, sheet);
        if (!result.equals("success")) {
            return result;
        }
        ////////////////////////////////////////////////////////////////////////////

        //System.out.println("1last_level:"+last_level+"*****curr_level:"+curr_level+"*********assy_level1:"+assy_level1+"*******assy_no1:"+assy_no1);

        if (last_level > curr_level && curr_level != 1)// for sub assemblies
        {
            //set new assembly number and assembly level//////////////////////
            assy_level1 = assy_level1 - (last_level - curr_level);
            assy_no1 = "" + assyVec.elementAt(assy_level1 - 1);
        }
        last_level = curr_level;//set current level as last level
        //System.out.println("assyVec:"+assyVec);
        if (curr_level == 1 && comp_type.equals("ASM"))// for new assembly at level 1
        {
            assy_no = comp_no;
            assy_level = curr_level;
        }

        //System.out.println("1last_level:"+last_level+"*****curr_level:"+curr_level+"*********assy_level1:"+assy_level1+"*******assy_no1:"+assy_no1);
        //System.out.println("3comp_no:"+comp_no+"*********comp_type:"+comp_type);
        //if current level is greater than current assembly level.
        if (curr_level > assy_level1) {
            boolean isAssyBomExists = method_obj.checkAssyBomExistsinDB(assy_no1, conn);
            if (isAssyBomExists == false) {
                //write_query="NA";
                //insert queries of assy bom insertion in vector that executes in getItem() when level is 1
                //System.out.println("insert into CAT_ASSY_BOM values('"+assy_no1+"','"+comp_no+"','"+comp_type+"','"+qty_str+"',0);");
                Vector tempVec = new Vector();
                tempVec.add("" + assy_no1);
                tempVec.add("" + comp_no);
                tempVec.add("" + comp_type);
                tempVec.add("" + qty_str);
                tempVec.add("" + 0);

                //write_query="insert into CAT_ASSY_BOM values('"+assy_no1+"','"+comp_no+"','"+comp_type+"','"+qty_str+"',0);";
                groupInsertVec.add(tempVec);
//                ps=conn.prepareStatement("insert into CAT_ASSY_BOM values(?,?,?,?,?)");
//                ps.setString(1, assy_no1);
//                ps.setString(2, comp_no);
//                ps.setString(3, comp_type);
//                ps.setString(4, qty_str);
//                ps.setInt(5, 0);
//                ps.executeUpdate();
//                ps.close();
            }

            // if assembly set new assembly number and new level.
            if (comp_type.equals("ASM")) {
                assy_no1 = comp_no;
                assy_level1 = curr_level;
            }
            row_index++;
            //System.out.println("calling cbi recursively...................");

            String result11 = callBomInsertion(assy_no1, assy_level1, sheet, user_id);
            ////System.out.println("result11:"+result11);
            if (!result11.equals("success") || result11.equals("end")) {
                return result11;
            }
        } else {
            row_index--;
        }
        return "success";
    }

    // method that returns a vector with part parameters and their column indexes in excel
    public Vector getPartParamIndex(Sheet sheet, Vector partParamIndexesVec, Connection conn) throws Exception {

        int param_count = method_obj.getPrtUAsmParamCount(conn);
        ////System.out.println("param_count1:"+param_count);
        int param_index = 6;
        ////System.out.println("partParamVec:"+partParamVec);
        for (int i = 0; i < param_count; i++) {
            String param_str = sheet.getCell(param_index, bom_header_index).getContents().trim();
            ////System.out.println("param_str:"+param_str);
            if (partParamVec.contains(param_str)) {
                partParamIndexesVec.add(param_index);
                partParamIndexesVec.add(param_str);
            }
            param_index++;
        }
        ////System.out.println("partParamIndexesVec in getPartParamIndex:"+partParamIndexesVec);
        return partParamIndexesVec;
    }//getPartParamIndex ends

    // method that returns a vector with assembly parameters and their column indexes in excel
    public Vector getAssyParamIndex(Sheet sheet, Vector assyParamIndexesVec, Connection conn) throws Exception {

        int param_count = method_obj.getPrtUAsmParamCount(conn);
        ////System.out.println("param_count2:"+param_count);
        ////System.out.println("assyParamVec:"+assyParamVec);
        int param_index = 6;
        for (int i = 0; i < param_count; i++) {
            String param_str = sheet.getCell(param_index, bom_header_index).getContents().trim();
            if (assyParamVec.contains(param_str)) {
                assyParamIndexesVec.add(param_index);
                assyParamIndexesVec.add(param_str);
            }
            param_index++;
        }
        ////System.out.println("assyParamIndexesVec in getPartParamIndex:"+assyParamIndexesVec);
        return assyParamIndexesVec;
    }//getAssyParamIndex ends

    // method that returns a vector with Group BOM parameters and their column indexes in excel
    public Vector getGrpBomLevelParamIndexVec(Sheet sheet, Vector grpBomLevelParamIndexesVec, Connection conn) throws Exception {

        int param_count = method_obj.getPrtUAsmParamCount(conn);
        int param_index = 6 + param_count;
        ////System.out.println("grpBomLevelParamvec:"+grpBomLevelParamvec);
        for (int i = 0; i < grpBomLevelParamvec.size(); i++) {
            String param_str = sheet.getCell(param_index, bom_header_index).getContents().trim();
            //////System.out.println("param_str:"+param_str);
            if (grpBomLevelParamvec.contains(param_str)) {
                grpBomLevelParamIndexesVec.add(param_index);
                grpBomLevelParamIndexesVec.add(param_str);
            }
            param_index++;
        }
        ////System.out.println("grpBomLevelParamIndexesVec in *****:"+grpBomLevelParamIndexesVec);
        return grpBomLevelParamIndexesVec;
    }// getGrpBomLevelParamIndexVec ends

    public String validateComponents(String comp_no, String comp_type, String comp_desc, String user_id, int row_index, Sheet sheet) throws Exception {
        String compTypeinDB = method_obj.checkCompExistsinDB(comp_no, conn);
        ////System.out.println("compTypeinDB:"+compTypeinDB);
        if (!compTypeinDB.equals("false"))// if component exists in database
        {
            if (comp_type.equals("PRT"))// for part
            {
                if (!comp_type.equals(compTypeinDB))// if existing type in database doesnt match
                {
                    return comp_no + " of PRT type exists in Database as " + compTypeinDB + ".";
                }
            } else// for Assembly
            {
                if (!comp_type.equals(compTypeinDB))// if existing type in database doesnt match
                {
                    return comp_no + " of ASM type exists in Database as " + compTypeinDB + ".";
                }
            }
        } else // if component not exists in database then insert in database
        {
            if (comp_type.equals("PRT"))// for Part
            {
                ////System.out.println("partParamIndexesVec in else:"+partParamIndexesVec);
                String insert_result = insertCompInDB(comp_no, comp_type, comp_desc, user_id, row_index, partParamIndexesVec, sheet);
                if (insert_result.equals("failure")) {
                    return "Error in insertion of " + comp_no + " and " + comp_type;
                }
            } else// for assembly
            {
                String insert_result = insertCompInDB(comp_no, comp_type, comp_desc, user_id, row_index, assyParamIndexesVec, sheet);
                if (insert_result.equals("failure")) {
                    return "Error in insertion of " + comp_no + " and " + comp_type;
                }
            }
        }

        return "success";

    }

    // insertion of component and their parameter values in database.///////////////////
    public String insertCompInDB(String comp_no, String comp_type, String comp_desc, String user_id, int row_index, Vector compParamIndexVec, Sheet sheet) {
        ////System.out.println("i am in insertCompInDB");
        ////System.out.println("compParamIndexVec:"+compParamIndexVec);
        //String write_query="";

        try {
            PreparedStatement pst = null;
            ResultSet rs;
            //write_query="select * from COMP_DETAIL where COMP_NO='"+comp_no+"' and COMP_TYPE='"+comp_type+"';";



            //////System.out.println("insert into COMP_DETAIL values ('"+comp_no+"','"+comp_desc+"','"+comp_type+"','"+method_obj.getSQLTodaysDate()+"','"+user_id+"',"+method_obj.getCD_No(conn)+","+method_obj.getPatch_No(conn)+");");
            //insert into COMP_DETAILS
            //write_query="insert into COMP_DETAIL values ('"+comp_no+"','"+comp_desc+"','"+comp_type+"','"+method_obj.getSQLTodaysDate()+"','"+user_id+"',"+method_obj.getCD_No(conn)+","+method_obj.getPatch_No(conn)+");";

            ps = conn.prepareStatement("insert into COMP_DETAIL values (?,?,?,?,?,?,?)");
            ps.setString(1, comp_no);
            ps.setString(2, comp_desc);
            ps.setString(3, comp_type);
            ps.setDate(4, method_obj.getSQLTodaysDate());
            ps.setString(5, user_id);
            ps.setInt(6, method_obj.getCD_No(conn));
            ps.setInt(7, method_obj.getPatch_No(conn));
            ps.executeUpdate();
            ps.close();

            //to insert part parameters values
            pst = conn.prepareStatement("select * from COMP_PARAM_VALUES(NOLOCK) where COMP_NO=? and PARAM_ID=?");
            ps = conn.prepareStatement("insert into COMP_PARAM_VALUES values(?,?,?,?)");
            for (int i = 0; i < compParamIndexVec.size(); i += 2) {
                int param_index = Integer.parseInt("" + compParamIndexVec.get(i));
                String param = "" + compParamIndexVec.get(i + 1);
                String param_value = sheet.getCell(param_index, row_index).getContents().trim();
                ////System.out.println("param_valuevvvvvvvvvvvvvvv:"+param_value);
                if (!param_value.equals("")) {
                    int param_id = method_obj.getCompParamId(param, comp_type, conn);
                    ////System.out.println("param_idvvvvvvvvvvvvvvv:"+param_id);


                    //write_query="select * from COMP_PARAM_VALUES where COMP_NO='"+comp_no+"' and PARAM_ID="+param_id+";";


                    pst.setString(1, comp_no);
                    pst.setInt(2, param_id);
                    rs = pst.executeQuery();
                    if (!rs.next()) {
                        //write_query="insert into COMP_PARAM_VALUES values('"+comp_no+"','"+comp_type+"',"+param_id+",'"+param_value+"');";

                        ps.setString(1, comp_no);
                        ps.setString(2, comp_type);
                        ps.setInt(3, param_id);
                        ps.setString(4, param_value);
                        ps.executeUpdate();

                    }
                    rs.close();


                }
            }
            ps.close();
            pst.close();
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return "failure";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "failure";
        }
        return "success";
    }//insertCompInDB ends
}
