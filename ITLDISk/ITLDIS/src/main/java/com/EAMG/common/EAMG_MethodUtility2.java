/*
 * EAMG_MethodUtility2.java
 *
 * Created on November 13, 2008, 5:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.EAMG.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author shivani.chauhan
 * SNo  Methods Detail    Author               Version    Methods Detail
 *
 *  1.  getParamId :      Shivani Chauhan       1.0     - Fetch param id of assembly type. -
 *  2.  param_partUassy : Shivani Chauhan       1.0     - Fetch parameters of ASM & PRT union - Shivani Chauhan
 *  3.  param_part      : Shivani Chauhan       1.0     - Fetch part type parameters - Shivani Chauhan
 *  4.  param_asy       : Shivani Chauhan       1.0     - Fetch assemly type parameters - Shivani Chauhan
 *  5.  assy_bom_in_db  : Shivani Chauhan       1.0     - To bring the BOM present in assymbely for duplication prevention - Shivani Chauhan
 *  6.  del_comp_db     : Shivani Chauhan       1.0     - Delete Components, if already present - Shivani Chauhan
 *  7.  getCDNo         : Shivani Chauhan       1.0     - For getting highest CD NO running - Shivani Chauhan
 *  8.  getPatchNo      : Shivani Chauhan       1.0     - For getting highest Patch No against Max CD NO - Shivani Chauhan
 *  9.  compDetail_in_db: Shivani Chauhan       1.0     - To bring data already present in comp_detail - Shivani Chauhan
 *  10. param_partUassyUkit: Shivani Chauhan    1.0     - Fetch parameters of ASM , PRT & Kit union - Shivani Chauhan
 *  11. param_kit       : Shivani Chauhan       1.0     - Fetch kit type parameters - Shivani Chauhan
 *  12. kit_bom_in_db   : Shivani Chauhan       1.0     - To bring the BOM present in Kit for duplication prevention - Shivani Chauhan
 *  13. grp_in_db        : Shivani Chauhan      1.0     - Fetch all groups - Shivani Chauhan
 *  14. grp_bom_param    : Shivani Chauhan      1.0     - Fetch param desc from GRP_BOM_PARAM_MASTER - Shivani Chauhan
 *  15. grp_detail       : Shivani Chauhan      1.0     - To get the seq, type, qty of a paricular group - Shivani Chauhan
 *  16. grp_bom_detail   : Shivani Chauhan      1.0     - To get the parameter value against a paricular component of a group from GRP_BOM_PARAM_VALUES & GRP_BOM_PARAM_MASTER. - Shivani Chauhan
 *  17. getIndexNo       : Shivani Chauhan      1.0     - To get index no against grpNo, compNo and paramId.  - Shivani Chauhan
 *  18. isGrpPresent     : Shivani Chauhan      1.0     - Check about presence of a group in GRP_BOM_PARAM_VALUES. - Shivani Chauhan
 *  19. isGrpPresent_GKB : Shivani Chauhan      1.0     - Check about presence of a group in GROUP_KIT_BOM.   - Shivani Chauhan
 *  20. isGrpBomPresent_GKB : Shivani Chauhan   1.0     - Check presence of BOM of a group in GROUP_KIT_BOM_CUSTOM.- pramod.vishwakarma
 *  21. getAsmBomAl      : Shivani Chauhan      1.0     - To bring the Assembly and its BOM at any level.- pramod.vishwakarma
 *  22. findOccurance    : Shivani Chauhan      1.0     - To find the occurance of Assembly and its BOM in same Parent Level ASM.- pramod.vishwakarma
 *  23. checkBom         : Shivani Chauhan      1.0     - For checking The ASM Bom in the same excel.- pramod.vishwakarma
 *  24. checkAssyBom     : Shivani Chauhan      1.0     - To cross check The ASM/KIT Bom in the same excel.- pramod.vishwakarma
 *  25. checkDuplicates  : Shivani Chauhan      1.0     - To check the duplicasy of any assembly against its type.- pramod.vishwakarma
 */
public class EAMG_MethodUtility2 {

    /**
     * Creates a new instance of EAMG_MethodUtility2
     */
    public EAMG_MethodUtility2() {
    }

    // 1.Fetch param id of assembly type.
    public ArrayList getParamId(Connection conn) throws Exception {
        ArrayList paramMaster = new ArrayList();
       PreparedStatement stmt = null;
       ResultSet rs = null;
        String query = "Select PARAM_ID,PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='ASM' ";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //Statement stmt = conn.createStatement();
        //ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            paramMaster.add(rs.getInt(1));
            paramMaster.add(rs.getString(2));
        }
        return paramMaster;
    }

    //2. Fetch parameters of ASM & PRT union
    public ArrayList param_partUassy(Connection conn) throws Exception {
        ArrayList paramMaster = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select distinct PARAM_DESC,PARAM_DOMAIN_TYPE from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE in ('PRT','ASM') order by PARAM_DESC ";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //Statement stmt = conn.createStatement();
        //ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            paramMaster.add(rs.getString(1));
        }
        //System.out.println("paramMaster :"+paramMaster);
        rs.close();
        return paramMaster;
    }

    //3. Fetch part type parameters
    public ArrayList param_part(Connection conn) throws Exception {

        ArrayList paramMaster = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE = 'PRT' ";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //Statement stmt = conn.createStatement();
        //ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            paramMaster.add(rs.getString(1));
        }
        rs.close();
        return paramMaster;
    }

    //4 Fetch assemly type parameters
    public ArrayList param_asy(Connection conn) throws Exception {

        ArrayList paramMaster = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE = 'ASM' ";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //Statement stmt = conn.createStatement();
        //ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            paramMaster.add(rs.getString(1));

        }
        rs.close();
        return paramMaster;
    }

    //5.To bring the BOM present in assymbely for duplication prevention
    public ArrayList assy_bom_in_db(Connection conn) throws Exception {
        ArrayList paramMaster = new ArrayList();
        String query = "select * from CAT_ASSY_BOM(NOLOCK) ";
        //Statement stmt = conn.createStatement();
        //ResultSet rs = stmt.executeQuery(query);
        PreparedStatement stmt = conn.prepareStatement(query);  
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            paramMaster.add(rs.getString(1));
            //   paramMaster.add(rs.getString(2));

        }
        rs.close();
        return paramMaster;
    }

    //6.Delete Components, if already present
    public void del_comp_db(Connection conn, String compNo, String comp_type) throws Exception {
        ArrayList paramMaster = new ArrayList();
        //Statement stmtDel = conn.createStatement();
        
        String query = "Delete from COMP_PARAM_VALUES(NOLOCK) where COMP_NO='" + compNo + "' and '" + comp_type + "' ";
        PreparedStatement stmtDel = conn.prepareStatement(query);
        stmtDel.executeUpdate(query);
        conn.commit();
    }

    //7.For getting highest CD NO running
    public int getCDNo(Connection conn) throws Exception {
        int cdNo = 0;
        //Statement stmt = conn.createStatement();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //ResultSet rs = stmt.executeQuery("Select Max(sno) from CAT_CD(NOLOCK)");
        String query = ("Select Max(sno) from CAT_CD(NOLOCK)");
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        if (rs.next()) {
            cdNo = rs.getInt(1);
        }
        return cdNo;
    }

    //8.For getting highest Patch No against Max CD NO
    public int getPatchNo(Connection conn, int cdNo) throws Exception {
        int patchNo = 0;
        //tement stmt = conn.createStatement();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //ResultSet rs = stmt.executeQuery("Select Max(PATCH_NO) from CAT_PATCH(NOLOCK) where CD_NO=" + cdNo + " ");
        String query = ("Select Max(PATCH_NO) from CAT_PATCH(NOLOCK) where CD_NO=" + cdNo + " ");
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        if (rs.next()) {
            patchNo = rs.getInt(1);
        }
        return patchNo;
    }

    //9.To bring data already present in comp_detail
    public ArrayList compDetail_in_db(Connection conn) throws Exception {
        ArrayList paramMaster = new ArrayList();
        String query = "Select COMP_NO,COMP_TYPE from COMP_DETAIL(NOLOCK)";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            paramMaster.add(rs.getString(1));
            paramMaster.add(rs.getString(2));

        }
        rs.close();
        return paramMaster;
    }

    //10  Fetch parameters of ASM , PRT & Kit union
    public ArrayList param_partUassyUkit(Connection conn) throws Exception {
        ArrayList paramMaster = new ArrayList();
        String query = "select distinct PARAM_DESC,PARAM_DOMAIN_TYPE from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE in ('PRT','ASM','KIT') order by PARAM_DESC ";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            paramMaster.add(rs.getString(1));
        }
        rs.close();
        return paramMaster;
    }

    //11. Fetch part type parameters
    public ArrayList param_kit(Connection conn) throws Exception {

        ArrayList paramMaster = new ArrayList();
        String query = "select PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE = 'KIT' ";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            paramMaster.add(rs.getString(1));
        }
        rs.close();
        return paramMaster;
    }

    //12.To bring the BOM present in Kit for duplication prevention
    public ArrayList kit_bom_in_db(Connection conn) throws Exception {
        ArrayList paramMaster = new ArrayList();
        String query = "select * from CAT_S_KIT_BOM(NOLOCK) ";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            paramMaster.add(rs.getString(1));
            paramMaster.add(rs.getString(2));

        }
        rs.close();
        return paramMaster;
    }
    //13.  Fetch all groups

//    public ArrayList grp_in_db(Connection conn) throws Exception {
//        ArrayList paramMaster = new ArrayList();
//        //String query = "Select GROUP_NO from group_status where APPROVED<>1";
//        Statement stmt = conn.createStatement();
//        String query = "Select DISTINCT GRP_KIT_NO from GROUP_KIT_DETAIL";
//        ResultSet rs = stmt.executeQuery(query);
//
//        while (rs.next()) {
//            paramMaster.add(rs.getString(1));
//
//        }
//        rs.close();
//        return paramMaster;
//    }
    //14.Fetch param desc from GRP_BOM_PARAM_MASTER

    public ArrayList grp_bom_param(Connection conn) throws Exception {
        ArrayList paramMaster = new ArrayList();
        String query = "select PARAM_DESC from GRP_BOM_PARAM_MASTER(NOLOCK)  order by PARAM_DESC ";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            paramMaster.add(rs.getString(1));

        }
        rs.close();
        stmt.close();
        return paramMaster;
    }
    //15. To get the seq, type, qty of a paricular group

    public ArrayList grp_detail(Connection conn, String grpNo) throws Exception {
        ArrayList paramMaster = new ArrayList();
        String query = "Select SEQUENCE,COMPONENT,QTY,REMARKS from CAT_GROUP_KIT_BOM where GRP_KIT_NO='" + grpNo + "'";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            paramMaster.add(rs.getString(1));
            paramMaster.add(rs.getString(2));
            paramMaster.add(rs.getString(3));
            paramMaster.add(rs.getString(4));

        }

        rs.close();
        return paramMaster;
    }

    public ArrayList grp_detail_cust(Connection conn, String grpNo) throws Exception {
        ArrayList paramMaster = new ArrayList();
        String query = "Select SEQUENCE,COMP_TYPE,COMPONENT, QTY from GROUP_KIT_BOM_CUSTOM(NOLOCK) where GRP_KIT_NO='" + grpNo + "'  order by INDEX_NO ";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            paramMaster.add(rs.getString(1));
            paramMaster.add(rs.getString(2));
            paramMaster.add(rs.getString(3));
            paramMaster.add(rs.getString(4));

        }

        rs.close();
        return paramMaster;
    }

    public ArrayList exsiting_grp_cust(Connection conn, String grpNo) {
        ArrayList paramMaster = new ArrayList();
        //Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "Select SEQUENCE,COMP_TYPE,COMPONENT, QTY,INDEX_NO from GROUP_KIT_BOM_CUSTOM(NOLOCK) where GRP_KIT_NO='" + grpNo + "'  order by INDEX_NO ";
           stmt = conn.prepareStatement(query);
           rs = stmt.executeQuery();
            // stmt = conn.createStatement();
            //rs = stmt.executeQuery(query);

            while (rs.next()) {
                paramMaster.add(rs.getString(1));
                paramMaster.add(rs.getString(2));
                paramMaster.add(rs.getString(3));
                paramMaster.add(rs.getString(4));
                paramMaster.add(rs.getString(5));
            }
            rs.close();
            rs = null;
            stmt.close();
            stmt=null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs!=null){
                    rs.close();
                    rs=null;
                }
                if (stmt!=null){
                    stmt.close();
                    stmt=null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return paramMaster;
    }
    //16.To get the parameter value against a paricular component of a group from GRP_BOM_PARAM_VALUES & GRP_BOM_PARAM_MASTER.

    public String grp_bom_detail(Connection conn, String grpNo, String compNo, String compVal, String indxNo) throws Exception {
        String param_val = "";
        String query = "SELECT GBPV.PARAM_VALUE from GRP_BOM_PARAM_VALUES(NOLOCK) GBPV, GRP_BOM_PARAM_MASTER(NOLOCK) GBPM where GBPV.Param_ID=GBPM.Param_ID and GBPV.GROUP_No='" + grpNo + "' and GBPV.COMP_NO='" + compNo + "' and GBPM.PARAM_DESC='" + compVal + "'  and GBPV.INDEX_NO='" + indxNo + "'";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            param_val = rs.getString(1);
        }
        rs.close();
        return param_val;
    }

    //17.To get index no against grpNo, compNo and paramId.
   

    //18.Check about presence of a group in GRP_BOM_PARAM_VALUES.
    public String isGrpPresent(Connection conn, String grpNo) throws Exception {
        String param_val = "";
        String query = "Select GROUP_NO from GRP_BOM_PARAM_VALUES(NOLOCK) where GROUP_NO='" + grpNo + "'";// and COMP_NO='"+compNo+"' and PARAM_ID="+paramId+" ";

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            param_val = rs.getString(1);
        }
        rs.close();
        return param_val;
    }

    //19. Check about presence of a group in GROUP_KIT_BOM.
    public String isGrpPresent_GKB(Connection conn, String grpNo) throws Exception {
        String param_val = "";
        String query = "Select GRP_KIT_NO from CAT_GROUP_KIT_BOM(NOLOCK) where GRP_KIT_NO='" + grpNo + "'";// and COMP_NO='"+compNo+"' and PARAM_ID="+paramId+" ";

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            param_val = rs.getString(1);
        }
        rs.close();
        return param_val;
    }
    //20.Check presence of BOM of a group in GROUP_KIT_BOM_CUSTOM

    public String isGrpBomPresent_GKB(Connection conn, String grpNo) throws Exception {
        String param_val = "";
        String query = "Select GRP_KIT_NO from GROUP_KIT_BOM_CUSTOM where GRP_KIT_NO='" + grpNo + "'";// and COMP_NO='"+compNo+"' and PARAM_ID="+paramId+" ";

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            param_val = rs.getString(1);
        }
        rs.close();
        return param_val;
    }
    //21.To bring the Assembly and its BOM at any level.

    public ArrayList getAsmBomAl(int index, int level, String assy_name, ArrayList assy) {
        ArrayList assyBom = new ArrayList();
        ArrayList assyItemBom = new ArrayList();
        ArrayList assyIndexBom = new ArrayList();
        ArrayList assyLevelBom = new ArrayList();
        ArrayList assyTypeBom = new ArrayList();

        ArrayList itemAl = (ArrayList) assy.get(0);
        ArrayList levelAl = (ArrayList) assy.get(1);
        ArrayList typeAl = (ArrayList) assy.get(2);

        assyItemBom.add(itemAl.get(index));
        assyIndexBom.add(index);
        assyLevelBom.add(level);
        assyTypeBom.add(typeAl.get(index));


        int nxt_level = level + 1;
        int indx = index + 1;
        int cur_level = nxt_level;
        while (level < cur_level && indx < levelAl.size()) {
            if (cur_level == nxt_level) {
                assyItemBom.add(itemAl.get(indx));
                assyIndexBom.add(indx);
                assyLevelBom.add(cur_level);
                assyTypeBom.add(typeAl.get(index));
            }
            indx++;
            if (indx < levelAl.size()) {
                cur_level = Integer.parseInt("" + levelAl.get(indx));
            }
        }
        assyBom.add(assyItemBom);
        assyBom.add(assyIndexBom);
        assyBom.add(assyLevelBom);
        assyBom.add(assyTypeBom);

        //System.out.println("assyBom:"+assyBom);
        return assyBom;
    }
    //22.To find the occurance of Assembly and its BOM in same Parent Level ASM.

    public ArrayList findOccurance(int index, String asm_no, String comp_type, ArrayList assy_no, ArrayList assy_type) {
        ArrayList occurance = new ArrayList();
        for (int i = index; i < assy_no.size(); i++) {
            if (assy_no.get(i).toString().equalsIgnoreCase(asm_no) && assy_type.get(i).equals(comp_type)) {
                occurance.add(i);
            }
        }
        return occurance;
    }
    //23.For checking The ASM Bom in the same excel.

    public boolean checkBom(ArrayList assy_Detail, int index, String comp_type) {
        boolean chk = true;
        ArrayList item_InExcel = (ArrayList) assy_Detail.get(0);
        ArrayList level_InExcel = (ArrayList) assy_Detail.get(1);
        ArrayList compType_InExcel = (ArrayList) assy_Detail.get(2);
        ArrayList masterAl = new ArrayList();

        int indx = 1;
        for (int i = index; i < item_InExcel.size(); i += indx) {
            if (compType_InExcel.get(i).equals(comp_type)) {
                String asm_item = "" + item_InExcel.get(i);
                ArrayList occuranceAl = findOccurance(i, asm_item, comp_type, item_InExcel, compType_InExcel);
                ArrayList compareAl = new ArrayList();
                if (occuranceAl.size() > 1) {
                    for (int j = 0; j < occuranceAl.size(); j++) {
                        indx = ((Integer) occuranceAl.get(j)).intValue();
                        int levelInt = Integer.parseInt("" + level_InExcel.get(indx));
                        String item_type = "" + compType_InExcel.get(indx);
                        compareAl = getAsmBomAl(indx, levelInt, item_type, assy_Detail);
                        masterAl.add(compareAl);

                    }
                }
                else {
                    break;
                }
            }
        }
        // //System.out.println("masterAl::::" + masterAl);
        if (masterAl.size() > 1) {
            ArrayList compareAl = (ArrayList) masterAl.get(0);
            ArrayList itemAl = (ArrayList) compareAl.get(0);
            int size = itemAl.size();
            //System.out.println("size:" + size);
            //System.out.println("itemAl:" + itemAl);
            for (int i = 1; i < masterAl.size(); i++) {
                compareAl = (ArrayList) masterAl.get(i);
                ArrayList tempItemAl = (ArrayList) compareAl.get(0);
                int size1 = tempItemAl.size();
                //System.out.println("size1:" + size1);
                //System.out.println("itemAl:" + tempItemAl);

                if (size1 != size) {
                    chk = false;
                    return chk;
                }

            }
            for (int i = 1; i < masterAl.size(); i++) {
                compareAl = (ArrayList) masterAl.get(i);
                ArrayList tempItemAl = (ArrayList) compareAl.get(0);
                for (int j = 0; j < itemAl.size(); j++) {
                    if (!tempItemAl.contains(itemAl.get(j))) {
                        chk = false;
                        return chk;
                    }
                    else {
                        tempItemAl.remove(itemAl.get(j));
                    }
                }
            }
        }
        return chk;
    }

    //24. To cross check The ASM/KIT Bom in the same excel.
    public String checkAssyBom(ArrayList assy_Detail, String comp_type) {
        String chk = "success";
        ArrayList item_InExcel = (ArrayList) assy_Detail.get(0);
        ArrayList compType_InExcel = (ArrayList) assy_Detail.get(2);
        ArrayList itemsChecked = new ArrayList();

        for (int i = 0; i < item_InExcel.size(); i++) {
            if (compType_InExcel.get(i).equals(comp_type)) {
                String asm_item = "" + item_InExcel.get(i);
                //System.out.println("asm_item::::" + asm_item);
                if (!itemsChecked.contains(asm_item)) {
                    boolean st = checkBom(assy_Detail, i, comp_type);
                    if (st) {
                        itemsChecked.add(asm_item);
                        //System.out.println("itemsChecked::::" + itemsChecked);
                    }
                    else {
                        if (comp_type.equals("ASM")) {
                            return "Bom Mismatch exists for Assembly '" + asm_item + "' in Excel.";
                        }
                        if (comp_type.equals("KIT")) {
                            return "Bom Mismatch exists for Kit '" + asm_item + "' in Excel.";
                        }
                    }
                }

            }
        }
        return chk;
    }
    // 25.To check the duplicasy of any assembly against its type.

    public String checkDuplicates(ArrayList assy_no, ArrayList assy_type) {
        String occurance = "";
        for (int i = 0; i < assy_no.size(); i++) {
            for (int j = i + 1; j < assy_no.size(); j++) {
                if (assy_no.get(j).toString().equalsIgnoreCase(assy_no.get(i).toString()) && !assy_type.get(j).equals(assy_type.get(i))) {
                    return "'" + assy_no.get(j) + "' already exists as '" + assy_type.get(i) + "' in Excel.";
                }
            }

        }
        return occurance;
    }

    public String deleteAlternates(Connection conn, String grpNo, ArrayList compNoAl) throws Exception {
        String param_val = "";
        ArrayList compAl = new ArrayList();
        String query = "Select COMPONENT from CAT_ALTERNATE(NOLOCK) where GRP_KIT_NO='" + grpNo + "'";// and COMP_NO='"+compNo+"' and PARAM_ID="+paramId+" ";

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            compAl.add(rs.getString(1));
        }
        rs.close();
        for (int i = 0; i < compAl.size(); i++) {
            if (!compNoAl.contains(compAl.get(i))) {
                query = "delete from CAT_ALTERNATE(NOLOCK) where GRP_KIT_NO='" + grpNo + "' and COMPONENT='" + compAl.get(i) + "'";// and COMP_NO='"+compNo+"' and PARAM_ID="+paramId+" ";
                PreparedStatement deleteStmt = conn.prepareStatement(query);
                deleteStmt.executeUpdate();
                //stmt.executeUpdate(query);
            }
        }
        param_val = "success";
        stmt.close();
        return param_val;
    }

    public ArrayList grp_Status(Connection conn) throws Exception {
        ArrayList paramMaster = new ArrayList();
        String query = "Select gs.GROUP_NO from group_status(NOLOCK) gs where gs.GROUP_NO not in (select mg.GROUP_NO from cat_model_group(NOLOCK) mg) and gs.APPROVED=1 order by gs.GROUP_NO";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            paramMaster.add(rs.getString(1));

        }
        rs.close();
        stmt.close();
        return paramMaster;
    }

    public boolean updateGupStatus(String groupno, String groupStatus, Connection conn) throws Exception {
        boolean isUpdated = false;
        int rowsUpdate = 0;
        String query = "";

        //checking whether custom present or not in the database.
        query = "update group_status set APPROVED = " + groupStatus + " where GROUP_NO = '" + groupno + "'";
        PreparedStatement pstmt = conn.prepareStatement(query);
        rowsUpdate = pstmt.executeUpdate();
        pstmt.close();
        pstmt = null;
        if ((rowsUpdate > 0)) {
            isUpdated = true;
        }
        return isUpdated;
    }

    public boolean updateModelStatus(String modelno, String modelStatus, Connection conn) throws Exception {
        boolean isModelUpdated = false;
        int rowsUpdateInModel = 0;
        int rowsUpdateModelRev = 0;
        String modelQuery = "";
        String modelRevQuery = "";
        PreparedStatement pstmtModel = null;
        PreparedStatement pstmtModelRev = null;
        //checking whether custom present or not in the database.
        try {
            modelQuery = "update cat_models set STATUS = '" + modelStatus + "' where MODEL_NO = '" + modelno + "'";

            modelRevQuery = "update model_revisions set STATUS = '" + modelStatus + "' where MODEL_NO = '" + modelno + "' and REV_NO=0 and ECN_NO=0";
            pstmtModel = conn.prepareStatement(modelQuery);
            rowsUpdateInModel = pstmtModel.executeUpdate();
            if (pstmtModel != null) {
                pstmtModel.close();
                pstmtModel = null;

            }
            pstmtModelRev = conn.prepareStatement(modelRevQuery);
            rowsUpdateModelRev = pstmtModelRev.executeUpdate();
            if (pstmtModelRev != null) {
                pstmtModelRev.close();
                pstmtModelRev = null;

            }

            if ((rowsUpdateInModel > 0) || (rowsUpdateModelRev > 0)) {
                isModelUpdated = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isModelUpdated;
    }
}
