/*
 * EAMG_ValidateXlsWithTemplate.java
 * Avinash.pandey
 *
 * Created on October 31, 2008, 4:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package EAMG.Part.Action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

import com.EAMG.common.EAMG_MethodsUtility;

import EAMG.Group.DAO.EAMGGroupDAO_R;
import EAMG_MethodsUtility_Package.EAMG_MethodUtility2;
import dao.inventoryDAO;
import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author Avinash.Pandey
 ** S.No.   Method                          Author              Version     Methods Detail
 *    1.     isGroupExcelValidated          Avinash.Pandey           1.0         to validate Group Excel.
 *    2.     checkParamValue                Avinash.Pandey   1.0         to validate Component and Group Bom level parameters in Group Excel.
 *    3.     getGrpBomLevelParamIndexVec    Avinash.Pandey   1.0         to get GROUP BOM parameter and their index in Group Excel.
 *    4.     validateLevels                 Avinash.Pandey           1.0         to validate levels of Group Bom in Group Excel.
 *    5.     isPartExcelValidated           Avinash.Pandey   1.0         to validate part Excel.
 *    6.     isToolExcelValidated           Avinash.Pandey   1.0         to validate Tool Excel.
 *    7.     isKitExcel                     Avinash.Pandey      1.0         to validate Kit Excel.
 *    8.     isAssemblyExcel                Avinash.Pandey      1.0         to validate Assembly excel
 *    9.     isAssemblyExcelValidated       Avinash.Pandey   1.0         to validate Assembly Excel with data.
 *    10.    isKitExcelValidated            Avinash.Pandey   1.0         to validate Kit Excel with data.
 */
public class EAMG_ValidateXlsWithTemplate {

    /** Creates a new instance of AT4_ValidateXlsWithTemplate */
    public EAMG_ValidateXlsWithTemplate() {
    }
    String group_no = "";
    int row_index_bom = 0;

    ////1.to validate Group Excel./////////////////////////////////////////////////
    public String isGroupExcelValidated(File xlsfile, Connection conn) throws Exception {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        int row = 0;
        int column = 0;
        Workbook workbook1 = null;
        ArrayList level_InExcel = new ArrayList();
        ArrayList compType_InExcel = new ArrayList();
        ArrayList item_InExcel = new ArrayList();

        ///////////////////////////CHECK FOR CORRUPTED EXCEL//////////////////////////
        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path.";

        }
        //////////////////////////////////////////////////////////////////////////////
        Sheet sheet = workbook1.getSheet(0);
        EAMG_MethodsUtility method_obj = new EAMG_MethodsUtility();
        try {
            //System.out.println("sheet.getColumns():"+sheet.getColumns());
            ///////////////////CHECKING TOTAL COLUMN COUNT OF TEMPLATE/////////////////
            //int templateCols = 6 + method_obj.getPrtUAsmParamCount(conn) + method_obj.getGrpBomLevelParametersVec(conn).size();
            int templateCols = 6 + method_obj.getPrtUAsmParamCount(conn);
            //System.out.println("templateCols:"+templateCols);
            if (templateCols != sheet.getColumns()) {
                return "Excel File Columns does not match with Template Columns.";
            }
            //////////////////////////////////////////////////////////////////////////

            ////////////////////TEMPLATE CHECKING STARTS//////////////////////////////
            if (!(sheet.getCell(column, row).getContents().trim().equals("GROUP TEMPLATE"))) {
                return row + "@@" + column;
            }
            row++;
            if (!(sheet.getCell(column, row).getContents().trim().equals(""))) {
                return row + "@@" + column;
            }
            if (!(sheet.getCell((column + 1), row).getContents().trim().equals(""))) {
                return row + "@@" + (column + 1);
            }
            row++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("GROUP NUMBER"))) {
                return row + "@@" + column;
            }

            ////System.out.println("column+1="+(column+1)+", row= "+row+"********"+sheet.getCell(column+1,row).getContents().trim());
            if (sheet.getCell(column + 1, row).getContents().trim().equals("")) {
                return "Group Number at Row '" + (row + 1) + "' can not be blank.";
            } else {
                group_no = sheet.getCell(column + 1, row).getContents().trim();
                //CHECKING SPECIAL SYMBOLS IN GROUP NUMBER/////////////////////////////
                if (group_no.length() > 31) {
                    return "Group Number at Row '" + (row + 1) + "' can not be greater than 31 characters.";
                }
//                if (!method_obj.checkSpecialPart_1(group_no)) {
//                    return "Group Number at Row '" + (row + 1) + "' can not contain special characters or space.<br>Allowed Special Symbols are (_) underscore and (-) Hyphen.";
//                }

            }
            row++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("GROUP DESCRIPTION"))) {
                return row + "@@" + column;
            }
            if (sheet.getCell((column + 1), row).getContents().trim().equals("")) {
                return "Group Description at Row '" + (row + 1) + "' can not be blank.";
            }
            row++;

            //CHECKING GROUP PARAMETERS IN TEMPLATE/////////////////////////////////////
            //rs = st.executeQuery("select PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='GRP' order by PARAM_DESC");
            String query = ("select PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='GRP' order by PARAM_DESC");
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()) {
                String comp_param = rs.getString(1);
                ////System.out.println("comp_param:"+comp_param);
                if (!(sheet.getCell(column, row).getContents().trim().equals(comp_param))) {
                    return row + "@@" + column;
                }

                String grp_param = sheet.getCell((column + 1), row).getContents().trim();
                if (!grp_param.equals("")) {
                    String result = method_obj.validateCompParameter(group_no, "GRP", comp_param, grp_param, conn);
                    if (!result.equals("success")) {
                        return result + " at Row '" + (row + 1) + "' and Column '" + (column + 2) + "'.";
                    }
                }
                row++;
            }
            rs.close();
            ////System.out.println("row after params:"+(row+1));

            if (!(sheet.getCell(column, row).getContents().trim().equals(""))) {
                return row + "@@" + column;
            }
            if (!(sheet.getCell((column + 1), row).getContents().trim().equals(""))) {
                return row + "@@" + (column + 1);
            }
            row++;

            ////CHECKING BOM HEADER IN TEMPLATE////////////////////////////////////////
            //////BOM HEADER/////////
            //1.LEVEL
            //2.SEQ. NO.
            //3.ITEM
            //4.DESCRIPTION
            //5.TYPE
            //6.QTY

            if (!(sheet.getCell(column, row).getContents().trim().equals("LEVEL"))) {
                return row + "@@" + column;
            }

            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("SEQ. NO."))) {
                return row + "@@" + column;
            }
            column++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("ITEM"))) {
                return row + "@@" + column;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("DESCRIPTION"))) {
                return row + "@@" + column;
            }
            column++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("TYPE"))) {
                return row + "@@" + column;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("QTY"))) {
                return row + "@@" + column;
            }
            column++;
            /////////////////////////////////////////////////////////////////////////


            ////CHECKING PART UNION ASSEMBLY PARAMETERS IN TEMPLATE//////////////////
            //rs = st.executeQuery("select distinct PARAM_DESC,PARAM_DOMAIN_TYPE from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE in ('PRT','ASM') order by PARAM_DESC;");
            String query1 = ("select distinct PARAM_DESC,PARAM_DOMAIN_TYPE from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE in ('PRT','ASM') order by PARAM_DESC;");
            st = conn.prepareStatement(query1);
            rs = st.executeQuery();
            while (rs.next()) {
                String comp_param = rs.getString(1);
                String col_contents = "";

                col_contents = sheet.getCell(column, row).getContents();

                if (!(col_contents.trim().equals(comp_param))) {
                    return row + "@@" + column;
                }
                column++;
            }
            rs.close();
            //////////////////////////////////////////////////////////////////////////


            ////CHECKING GROP BOM PARAMETERS IN TEMPLATE/////////////////////////////
//            rs = st.executeQuery("select distinct PARAM_DESC  from GRP_BOM_PARAM_MASTER order by PARAM_DESC");
//            while (rs.next()) {
//                String comp_param = rs.getString(1);
//                String col_contents = "";
//                col_contents = sheet.getCell(column, row).getContents();
//
//                if (!(col_contents.trim().equals(comp_param))) {
//                    return row + "@@" + column;
//                }
//                column++;
//            }
//            rs.close();
            /////////////////////////////////////////////////////////////////////////
            st.close();

            //ROW INCREMENTED TO CHECK THE BOM
            row++;

            if (sheet.getCell(0, row).getContents().trim().equals("end")) {
                return "No Group Bom Data is available in the Excel File.";
            } else {
                //BOM LEVEL SHOULD START WITH LEVEL 1 CHECK///////////////////////////
                String start_level = sheet.getCell(0, row).getContents().trim();
                if (!start_level.equals("")) {
                    int start_level_num = 0;
                    try {
                        start_level_num = Integer.parseInt(start_level);

                    } catch (NumberFormatException ex) {
                        return "Level column can only be a Integer at Row '" + (row + 1) + "' in the Group Bom. Specify 'end' if required.";
                    }
                    //int start_level_num=Integer.parseInt(start_level);
                    if (start_level_num != 1) {
                        return "Group Bom Data should start with Level 1.";
                    }
                }
                //////////////////////////////////////////////////////////////////////

                row_index_bom = row;
                while (!sheet.getCell(0, row).getContents().trim().equals("end")) {
                    //System.out.println("in while");
                    for (int i = 0; i < 6; i++) {
                        String cell_contents = sheet.getCell(i, row).getContents().trim();
                        if (i == 4)//for validating Type column.It can be either a PRT value or ASM value
                        {
                            //System.out.println("cell_contents:"+cell_contents);
                            if (cell_contents.equals("")) {
                                return "Type column can not be blank at Row '" + (row + 1) + "' in the Group Bom.";
                            } else {
                                if (!(cell_contents.equals("PRT") || cell_contents.equals("ASM"))) {
                                    return "Type column can only be 'PRT' or 'ASM' at Row '" + (row + 1) + "' in the Group Bom.";
                                }
                            }
                            compType_InExcel.add(cell_contents);
                        }
                        if (i == 5)//for validating Quantity column.It can be either a number value or AR for "As Required"
                        {
                            //System.out.println("cell_contents:"+cell_contents);
                            if (cell_contents.equals("")) {
                                return "Qty column can not be blank at Row '" + (row + 1) + "' in the Group Bom.";
                            } else {
                                if (cell_contents.equals("0")) {
                                    return "Qty column can not be 0 at Row '" + (row + 1) + "' in the Group Bom.";
                                }
                                try {
                                    Integer.parseInt(cell_contents);
                                } catch (Exception e) {

                                    return "No Special Symbols allowed in Qty column at Row '" + (row + 1) + "' in the Group Bom.";
                                }

                                int qty = 0;
                                try {
                                    qty = Integer.parseInt(cell_contents);
                                    if (qty > 250) {
                                        return "Quantity can not be greater than 500 at Row '" + (row + 1) + "' in the Group Bom.";
                                    }
                                } catch (NumberFormatException ex) {
                                    //ex.printStackTrace();
                                    if (!cell_contents.equals("AR")) {
                                        return "Only numeric values are allowed as quantity at Row '" + (row + 1) + "' and quantity can not be greater than 500 or Give 'AR' for 'As Required' in the Group Bom.";
                                    }

                                }
                            }
                        } else if (i == 0)//for validating level column. it can not be empty and only numeric
                        {

                            if (cell_contents.equals("")) {
                                return "Level column can not be blank at Row '" + (row + 1) + "' in the Group Bom. Specify 'end' if required.";
                            } else {

                                int elem = 0;
                                try {
                                    elem = Integer.parseInt(cell_contents);

                                } catch (NumberFormatException ex) {
                                    return "Level column can only be a Integer at Row '" + (row + 1) + "' in the Group Bom. Specify 'end' if required.";
                                }
                                level_InExcel.add(cell_contents);
                            }
                        } else if (i == 1)//for validating Seq.No. column
                        {
                            int level_value = Integer.parseInt(sheet.getCell(0, row).getContents().trim());

                            int elem = 0;

                            if (level_value == 1)//for validating seq column for level=1,SEQUENCE Number required.
                            {
                                if (cell_contents.equals("")) {
                                    return "Seq.No. column can not be blank at Row '" + (row + 1) + "' in the Group Bom.";
                                } else {
                                    if (!method_obj.checkSpecialPart_1(cell_contents)) {
                                        return "Seq.No. at Row '" + (row + 1) + "' can not contain any special character.";
                                    }
                                }
                            }
                        } else if (i == 2)//for validating Item column
                        {
                            if (cell_contents.equals("")) {
                                return "Item column can not be blank at Row '" + (row + 1) + "' in the Group Bom.";
                            } else {
                                if (cell_contents.length() > 31) {
                                    return "Item at Row '" + (row + 1) + "' can not be greater than 31 characters.";
                                }
//                                if (!method_obj.checkSpecialPart_1(cell_contents)) {
//                                    return "Item at Row '" + (row + 1) + "' can not contain special characters or space.<br>Allowed Special Symbols are (_) underscore and (-) Hyphen.";
//                                }
                            }
                            item_InExcel.add(cell_contents.toUpperCase());
                        } else// for description column.
                        {
                            //System.out.println("cell_contents:"+cell_contents);
                            if (cell_contents.equals("")) {
                                return "Description column can not be blank at Row '" + (row + 1) + "' in the Group Bom.";
                            }
                        }
                    }
                    row++;
                }
            }

            /////////////FOR VALIDATING LEVELS OF BOM IN TEMPLATE //////////////////////
            String result = validateLevels(sheet);
            if (!result.equals("success")) {
                return result;
            }
            ////////////////////////////////////////////////////////////////////////////

            ////////////CHECKING DUPLICATE ITEMS AND THEIR BOM (IF ASSEMBLY)/////////////
            ArrayList assy_Detail = new ArrayList();
            assy_Detail.add(item_InExcel);
            assy_Detail.add(level_InExcel);
            assy_Detail.add(compType_InExcel);
            result = new EAMG_MethodUtility2().checkDuplicates(item_InExcel, compType_InExcel);
            if (!result.equals("")) {
                return result;
            }
            //////////////////////////////////////////////////////////////////////////

            /////////CHECKING ASSEMBLY WITHOUT BOM SHOULD NOT EXISTS IN BOM ///////////
            result = new EAMG_MethodUtility2().checkAssyBom(assy_Detail, "ASM");
            //System.out.println("result :" + result);
            if (!result.equals("success")) {
                return result;
            }
            ///////////////////////////////////////////////////////////////////////////


            //////////APPLYING CHECKS ON PARAMETER (COMPONENT+GROUP BOM LEVEL) VALUE 
//            result = checkParamValue(method_obj, sheet, conn);
            if (!result.equals("success")) {
                return result;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            return row + "@@" + column;

        }

        return "success@@" + group_no;
    }
    //////////////////////////////////////////////////////////////////////////////
    ////2.to validate Component and Group Bom level parameters in Group Excel./////////////////////////////////////////////////  
//    public String checkParamValue(EAMG_MethodsUtility method_obj, Sheet sheet, Connection conn) throws Exception {
//        int bom_header_index = method_obj.getGRPParameterCount(conn) + 5;
//        //System.out.println("bom_header_index::::::"+bom_header_index);
////        Vector grpBomLevelParamvec = method_obj.getGrpBomLevelParametersVec(conn);
//        //System.out.println("grpBomLevelParamvec::::::"+grpBomLevelParamvec);
////        Vector grpBomLevelParamIndexesVec = new Vector();
////        grpBomLevelParamIndexesVec = getGrpBomLevelParamIndexVec(method_obj, sheet, bom_header_index, grpBomLevelParamIndexesVec, grpBomLevelParamvec, conn);
//        //System.out.println("grpBomLevelParamIndexesVec1::::::"+grpBomLevelParamIndexesVec);
////        int noof_param = grpBomLevelParamIndexesVec.size();
//        String result = "success";
//        int row = bom_header_index + 1;
//
//        AT4_ValidateGroupInsertion insert_obj = new AT4_ValidateGroupInsertion();
//        insert_obj.bom_header_index = bom_header_index;
//
//        insert_obj.partParamVec = method_obj.getPartParametersVec(conn);
//        Vector partParamIndexVec = new Vector();
//        partParamIndexVec = insert_obj.getPartParamIndex(sheet, partParamIndexVec, conn);
//
//        insert_obj.assyParamVec = method_obj.getAssyParametersVec(conn);
//        Vector asmParamIndexVec = new Vector();
//        asmParamIndexVec = insert_obj.getAssyParamIndex(sheet, asmParamIndexVec, conn);
//
//        //System.out.println("noof_param::::::"+noof_param);
//        //System.out.println("row::::::"+row);
//
//        int prt_u_asm_cnt = method_obj.getPrtUAsmParamCount(conn);
//        int comp_param_start_index = 6;
//        //For validating Component Prameters in BOM.
//        int col_index = 0;
//        String comp_no = "";
//        String comp_type = "";
//        String col_name = "";
//        String param_value = "";
//        int param_id = 0;
//        while (!sheet.getCell(0, row).getContents().trim().equals("end")) {
//            comp_no = sheet.getCell(2, row).getContents().trim();
//            comp_type = sheet.getCell(4, row).getContents().trim();
//
//            for (int i = 0; i < prt_u_asm_cnt; i++) {
//                col_index = comp_param_start_index + i;
//                param_value = sheet.getCell(col_index, row).getContents().trim();
//                if (!param_value.equals("")) {
//                    if (comp_type.equals("PRT")) {
//
//                        if (partParamIndexVec.contains(col_index)) {
//                            String param_name = "" + partParamIndexVec.elementAt(partParamIndexVec.indexOf(col_index) + 1);
//                            result = method_obj.validateCompParameter(comp_no, comp_type, param_name, param_value, conn);
//                            if (!result.equals("success")) {
//                                return result + " at Row '" + (row + 1) + "' and Column '" + (col_index + 1) + "'.";
//                            }
//                        } else {
//
//                            return "Wrong Parameter Value exists in Part '" + comp_no + "' for Assembly Parameter at Row '" + (row + 1) + "' and Column '" + (col_index + 1) + "'.";
//                        }
//                    }
//                    if (comp_type.equals("ASM")) {
//                        if (asmParamIndexVec.contains(col_index)) {
//                            String param_name = "" + asmParamIndexVec.elementAt(asmParamIndexVec.indexOf(col_index) + 1);
//                            result = method_obj.validateCompParameter(comp_no, comp_type, param_name, param_value, conn);
//                            if (!result.equals("success")) {
//                                return result + " at Row '" + (row + 1) + "' and Column '" + (col_index + 1) + "'.";
//                            }
//                        } else {
//
//                            return "Wrong Parameter Value exists in Assembly '" + comp_no + "' for Part Parameter at Row '" + (row + 1) + "' and Column '" + (col_index + 1) + "'.";
//                        }
//                    }
//                }
//            }

    //For validating GROUP BOM Prameters in BOM.
//            for (int i = 0; i < noof_param; i += 2) {
//                col_index = ((Integer) grpBomLevelParamIndexesVec.elementAt(i)).intValue();
//
//                col_name = "" + grpBomLevelParamIndexesVec.elementAt(i + 1);
//                //System.out.println("col_name::::::"+col_name);
//
//                param_value = sheet.getCell(col_index, row).getContents().trim();
//
//                if (!param_value.equals("")) {
//                    if(param_value.indexOf(",")!=-1)
//                    {
//                       String paramArr[]=null;
//                       paramArr=param_value.split(",");
//
//                       for(int ii=0;ii<paramArr.length;ii++)
//                       {
//                            param_id = method_obj.getGrpBomParamIdForMultiList(col_name, conn);
//
//                            if (param_id != 0)//for LIST parameter
//                            {
//                                Vector valueVec = method_obj.getGrpBomParamListValues(param_id, conn);
//                                //System.out.println("valueVec:"+valueVec);
//                                if (!valueVec.contains(paramArr[ii])) {
//                                    result = "Value for the Parameter '" + col_name + "' does not match at row '" + (row + 1) + "'. <br>It should be with in the List: " + valueVec + ".";
//                                    return result;
//                                }
//                            } else {
//                                result = method_obj.validateGrpBomParameter(comp_no, comp_type, col_name, paramArr[ii], conn);
//                                if (!result.equals("success")) {
//                                    return result + " at Row '" + (row + 1) + "' and Column '" + (col_index + 1) + "'.";
//                                }
//                            }
//                        }
//                    }
//                    else
//                    {
//                        param_id = method_obj.getGrpBomParamIdForList(col_name, conn);
//
//                        if (param_id != 0)//for LIST parameter
//                        {
//                            Vector valueVec = method_obj.getGrpBomParamListValues(param_id, conn);
//                            //System.out.println("valueVec:"+valueVec);
//                            if (!valueVec.contains(param_value)) {
//                                result = "Value for the Parameter '" + col_name + "' does not match at row '" + (row + 1) + "'. <br>It should be with in the List: " + valueVec + ".";
//                                return result;
//                            }
//                        } else {
//                            result = method_obj.validateGrpBomParameter(comp_no, comp_type, col_name, param_value, conn);
//                            if (!result.equals("success")) {
//                                return result + " at Row '" + (row + 1) + "' and Column '" + (col_index + 1) + "'.";
//                            }
//                        }
//                    }
//                }
//
//
//            }
//
//
//            row++;
//        }
//
//        return result;
//    }
    ////////////////////////////////////////////////////////////////////////////////////
    ////3.to get GROUP BOM parameter and their index in Group Excel./////////////////////////////////////////////////
    public Vector getGrpBomLevelParamIndexVec(EAMG_MethodsUtility method_obj, Sheet sheet, int bom_header_index, Vector grpBomLevelParamIndexesVec, Vector grpBomLevelParamvec, Connection conn) throws Exception {

        int param_count = method_obj.getPrtUAsmParamCount(conn);
        int param_index = 6 + param_count;
        //System.out.println("grpBomLevelParamvec:"+grpBomLevelParamvec);
        for (int i = 0; i < grpBomLevelParamvec.size(); i++) {
            String param_str = sheet.getCell(param_index, bom_header_index).getContents().trim();
            ////System.out.println("param_str:"+param_str);
            if (grpBomLevelParamvec.contains(param_str)) {
                grpBomLevelParamIndexesVec.add(param_index);
                grpBomLevelParamIndexesVec.add(param_str);
            }
            param_index++;
        }
        //System.out.println("grpBomLevelParamIndexesVec in *****:"+grpBomLevelParamIndexesVec);
        return grpBomLevelParamIndexesVec;
    }
    //////////////////////////////////////////////////////////////////////////////////
    ////4.to validate levels of Group Bom in Group Excel./////////////////////////////////////////////////

    public String validateLevels(Sheet sheet) {
        while (!sheet.getCell(0, row_index_bom).getContents().trim().equalsIgnoreCase("end")) {
            String comp_no = sheet.getCell(2, row_index_bom).getContents().trim();
            int level = Integer.parseInt(sheet.getCell(0, row_index_bom).getContents().trim());
            String comp_type = sheet.getCell(4, row_index_bom).getContents().trim();
            //checking bom of an assembly///////////////////////////////////////////
            if (comp_type.equals("ASM")) {
                int nextlevel = 0;
                try {
                    nextlevel = Integer.parseInt(sheet.getCell(0, row_index_bom + 1).getContents().trim());
                } catch (NumberFormatException ex) {
                    nextlevel = 0;
                    ex.printStackTrace();
                }
                if (nextlevel != level + 1) {
                    return "Bom of Assembly '" + comp_no + "' at Row '" + (row_index_bom + 1) + "' does not exist.";
                }
            } else// validating next level
            {
                int nextlevel = 0;
                try {
                    nextlevel = Integer.parseInt(sheet.getCell(0, row_index_bom + 1).getContents().trim());
                } catch (NumberFormatException ex)/////////check for end////////////////
                {
                    if ((sheet.getCell(0, row_index_bom + 1).getContents().trim()).equals("end")) {
                        return "success";
                    }
                }
                if (nextlevel > level || nextlevel == 0) {
                    return "Next Level after Item '" + comp_no + "' with Type '" + comp_type + "' at Row '" + (row_index_bom + 1) + "' is not valid.";
                }
            }

            row_index_bom++;
        }
        return "success";
    }
    //////////////////////////////////////////////////////////////////////////////////
    ////5.to validate Part Excel./////////////////////////////////////////////////

    public String isToolExcel(String fileName, int index, Sheet sheet, Connection conn) throws Exception {

        String result = "";
        String result1 = "<br>Hence Kit Creation Process aborted. Attach valid Excel to complete the Process successfully.";
        EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);
        ResultSet rs = null;
        String cellContent = null;
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        boolean r1 = false;

        try {

            if ((sheet.getCell(0, 0).getContents().trim()).equals("TOOL TEMPLATE")) {

                if (!sheet.getCell(0, 1).getContents().equalsIgnoreCase("")) {
                    result = "Row should be blank.";
                    return "There is an error at Column 1, Row 2. " + result + result1;
                    //return result;
                }
                if ((sheet.getCell(0, 2).getContents().trim()).equals("TOOL NUMBER")) {

                    String toolno = sheet.getCell(1, 2).getContents().trim();
                    if (toolno.equals("")) {
                        result = "Tool Number cannot be blank.";
                        return "There is an error at Column 2, Row 3. " + result + result1;
                    }
                    String comp_in_db = new EAMG_MethodsUtility().isCompPresent(toolno, conn);
                    if (!comp_in_db.equals("notpresent")) {
                        result = comp_in_db;
                        return "There is an error at Column 2, Row 3. " + result + result1;
                    }
                    if (toolno.length() > 31) {
                        return "There is an error at Column 2, Row 3. " + "Tool Number can not be greater than 31 characters." + result1;
                    }
                    r1 = methodutil.checkSpecialPart_1(toolno);
                    if (r1 != true) {
                        return "There is an error at Column 2, Row 3. " + "Space and Special Symbols are not allowed in Tool Number.<br>" + result1;
                    }

                } else {
                    result = "'TOOL NUMBER' Missing.";
                    return "There is an error at Column 1, Row 3. " + result + result1;
                }

                if ((sheet.getCell(0, 3).getContents().trim()).equals("TOOL DESCRIPTION")) {
                    cellContent = sheet.getCell(1, 3).getContents().trim();
                    if (cellContent.equals("")) {
                        result = "Tool Description cannot be blank.";
                        return "There is an error at Column 2, Row 4. " + result + result1;
                    } else if ((r1 = methodutil.checkSpecialSymbolDescription(cellContent)) != true) {
                        result = "'TOOL DESCRIPTION' Cannot Contain Special Symbol.";
                        return "There is an error at Column 2, Row 4. " + result + result1;
                    }
                } else {
                    result = "'TOOL DESCRIPTION' Missing.";
                    return "There is an error at Column 2, Row 4. " + result + result1;
                }
                if ((sheet.getCell(0, 4).getContents().trim()).equals("TOOL REMARKS")) {
                    cellContent = sheet.getCell(1, 4).getContents().trim();
                    if (cellContent.equals("")) {
                        result = "Tool Remarks cannot be blank.";
                        return "There is an error at Column 2, Row 5. " + result + result1;
                    } else if ((r1 = methodutil.checkSpecialSymbolDescription(cellContent)) != true) {
                        result = "'TOOL REMARKS' Cannot Contain Special Symbol.";
                        return "There is an error at Column 2, Row 5. " + result + result1;
                    }
                } else {
                    result = "'TOOL REMARKS' Missing.";
                    return "There is an error at Column 2, Row 5. " + result + result1;
                }
                if ((sheet.getCell(0, 5).getContents().trim()).equals("CATEGORY TYPE")) {
                    cellContent = sheet.getCell(1, 5).getContents().trim();
                    if (!cellContent.equals("")) {
                    if ((r1 = methodutil.checkSpecialSymbolDescription(cellContent)) != true) {
                        result = "'CATEGORY TYPE' Cannot Contain Special Symbol.";
                        return "There is an error at Column 2, Row 6. " + result + result1;
                    }
                    }
                } else {
                    result = "'CATEGORY TYPE' Missing.";
                    return "There is an error at Column 2, Row 6. " + result + result1;
                }

//                if ((sheet.getCell(0, 4).getContents().trim()).equals("MOQ")) {
//                    cellContent = sheet.getCell(1, 4).getContents().trim();
//                    if (cellContent.equals("")) {
//                        result = "MOQ cannot be blank.";
//                        return "There is an error at Column 2, Row 5. " + result + result1;
//                    } else if ((r1 = methodutil.checkNumeric(cellContent)) != true) {
//                        result = "'MOQ' Should Be Numeric.";
//                        return "There is an error at Column 2, Row 5. " + result + result1;
//                    } else if (cellContent.length() > 4) {
//                        result = "MOQ Should Be Less than 4 Character.";
//                        return "There is an error at Column 2, Row 5. " + result + result1;
//                    }
//                } else {
//                    result = "'MOQ' Missing.";
//                    return "There is an error at Column 2, Row 5. " + result + result1;
//                }
//                if ((sheet.getCell(0, 5).getContents().trim()).equals("QML")) {
//                    cellContent = sheet.getCell(1, 5).getContents().trim();
//                    if (cellContent.equals("")) {
//                        result = "QML cannot be blank.";
//                        return "There is an error at Column 2, Row 6. " + result + result1;
//                    } else if ((r1 = methodutil.checkNumeric(cellContent)) != true) {
//                        result = "'QML' Should Be Numeric.";
//                        return "There is an error at Column 2, Row 6. " + result + result1;
//                    } else if (cellContent.length() > 4) {
//                        result = "QML Should Be Less than 4 Character.";
//                        return "There is an error at Column 2, Row 5. " + result + result1;
//                    }
//                } else {
//                    result = "'QML' Missing.";
//                    return "There is an error at Column 2, Row 6. " + result + result1;
//                }
//                if ((sheet.getCell(0, 6).getContents().trim()).equals("NDP")) {
//                    cellContent = sheet.getCell(1, 6).getContents().trim();
//                    if (cellContent.equals("")) {
//                        result = "NDP cannot be blank.";
//                        return "There is an error at Column 2, Row 7. " + result + result1;
//                    } else if ((r1 = methodutil.checkNumeric(cellContent)) != true) {
//                        result = "'NDP' Should Be Numeric.";
//                        return "There is an error at Column 2, Row 7. " + result + result1;
//                    } else if (cellContent.length() > 4) {
//                        result = "NDP Should Be Less than 4 Character.";
//                        return "There is an error at Column 2, Row 5. " + result + result1;
//                    }
//                } else {
//                    result = "'NDP' Missing.";
//                    return "There is an error at Column 2, Row 7. " + result + result1;
//                }

                int row = 7;

                if (!sheet.getCell(0, row).getContents().equals("ITEM")) {
                    result = "'ITEM' Missing.";
                    return "There is an error at Column 2, Row " + (row + 1) + ". " + result + result1;
                }
                if (!sheet.getCell(1, row).getContents().equals("QTY")) {
                    result = "'QTY' Missing.";
                    return "There is an error at Column 5, Row " + (row + 1) + ". " + result + result1;
                }
                row++;
                int r = 0;

                while (!(sheet.getCell(0, row).getContents().trim()).equals("end")) {
                    for (int m = 0; m < 2; m++) {
                        if (m == 0) {
                            cellContent = sheet.getCell(m, row).getContents().trim();
                            if (cellContent.equals("")) {
                                result = "Item cannot be blank.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result + result1;
                            } else if ((r1 = methodutil.checkSpecialPart_1(cellContent)) != true) {
                                result = "Item Cannot Contain Special Character.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result + result1;
                            } else if ((r1 = dao.isCompExist(cellContent)) != true) {
                                result = "Item doesn't exist in database.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result + result1;
                            }
                        } else if (m == 1) {
                            cellContent = sheet.getCell(m, row).getContents().trim();
                            if (cellContent.equals("")) {
                                result = "Qty cannot be blank.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result + result1;
                            } else if ((r1 = methodutil.checkNumeric(cellContent)) != true) {
                                result = "Qty Should Be Numeric.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result + result1;
                            } else if (cellContent.length() > 4) {
                                result = "Qty Should Be Less than 4 Character.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result + result1;
                            }
                        }
                    }
                    row++;
                    r++;
                }

            } else {
                result = "'TOOL TEMPLATE' Missing.";
                return "There is an error at Column 1, Row 1. " + result + result1;
            }
            result = "Excel Validation Successful";
        } catch (Exception e) {
            e.printStackTrace();
            result = "TEMPLATE MISMATCH or 'end' Missing.";
            return result + result1;
        }
        return result;
    }

    public String isPartExcelValidated(File xlsfile, Connection conn) throws Exception {
        ////System.out.println("pxlsfile:"+xlsfile);
        //declaration of variables used.
        EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);

        Statement stmt = null;
        ResultSet rs = null;
        boolean r = false;
        int row = 0;
        int column = 0;
        int sheet_col = 0;
        int count = 0, c = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        String comp_no = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        Vector paramVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        try {
            stmt = conn.createStatement();
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for PART TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART TEMPLATE"))) {
                err = row + "@@" + column + "##'PART TEMPLATE' Missing.";
                ////System.out.println(err);
                return err;
            }
            row++;
            //checking for blank row.
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = row + "@@" + column + "##Row should be blank.";
                ////System.out.println(row+"@@"+column+"@@ROW SHOULD BE BLANK");
                return err;
            }
            row++;
            //checking for PART NUMBER.
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART NUMBER"))) {
                err = row + "@@" + column + "##'PART NUMBER' Missing.";
                ////System.out.println(row+"@@"+column+"@@PART NUMBER MISSING");
                return err;
            }
            ////System.out.println("column+1="+(column+1)+", row= "+row+"********"+sheet.getCell(column+1,row).getContents().trim());
            column++;
            //checking for PART DESCRIPTION.
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART DESCRIPTION"))) {
                err = row + "@@" + column + "##'PART DESCRIPTION' Missing.";
                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART REMARKS"))) {
                err = row + "@@" + column + "##'PART REMARKS' Missing.";
                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
                return err;
            }
            column++;
//            if (!(sheet.getCell(column, row).getContents().trim().equals("CATEGORY TYPE"))) {
//                err = row + "@@" + column + "##'CATEGORY TYPE' Missing.";
//                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
//                return err;
//            }
//            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("SERVICEABLE"))) {
                err = row + "@@" + column + "##'SERVICEABLE' Missing.";
                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
                return err;
            }
            column++;
//            if (!(sheet.getCell(column, row).getContents().trim().equals("np4"))) {
//                err = row + "@@" + column + "##'PART STATUS' Missing.";
//                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
//                return err;
//            }
//            column++;
            //checking for PART PARAMETER DESCRIPTION from COMP_PARAM_MASTER
//            rs = stmt.executeQuery("select part_no from part order by part_no");
//            //sheet_col=sheet.getColumns();
//            ////System.out.println("Sheet Columns:"+sheet_col);
//            while (rs.next()) {
//                paramVec.add(rs.getString(1));
//                count++;
//            }
//            //checking for number of Parameters.
////            if(count!=(sheet_col-2))
////            {
////                column=sheet_col;
////                if(count<(sheet_col-2))
////                {
////                    column=sheet_col-1;
////                }
////                err=row+"@@"+column+"##Number of Parameters does not match.";
////                ////System.out.println(row+"@@"+column+"@@NUMBER OF PARAMETERS ARE DIFFERENT"+sheet_col);
////                return err;
////            }
//            //checking for Parameter Names.
//            for (int k = 0; k < paramVec.size(); k++) {
//                String part = "";
//                try {
//                    part = sheet.getCell(column, row).getContents().trim();
//                } catch (ArrayIndexOutOfBoundsException exception) {
//                    err = row + "@@" + column + "##Parameter Name doesn't match with '" + paramVec.elementAt(k) + "'.";
//                    ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+tool);
//                    return err;
//                }
//                if (!(part.equals(paramVec.elementAt(k)))) {
//                    err = row + "@@" + column + "##Parameter Name doesn't match with '" + paramVec.elementAt(k) + "'.";
//                    ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+tool);
//                    return err;
//                }
//                column++;
//            }
            // sheet_col = paramVec.size() + 2;
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equals("end"))) {

                err = row + "@@" + column + "##No Records Available.";
                ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+part);
                return err;
            }

            //getting total number of rows.
            totalrows = sheet.getRows();
            ////System.out.println("Rows:"+totalrows);
            //loop till 'end' encounters.
            while (row < totalrows) {
                comp_no = "";
                //if element does not equal to end.
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    comp_no = sheet.getCell(0, row).getContents().trim();
                    for (column = 0; column < 4; column++) {
                        //if Part Number is null.
                        //if (sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("")) {
                        if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if (partno.equals("")) {
                                err = row + "@@" + column + "##Part Number can not be blank. Specify 'end' if required.";
                                ////System.out.println(row+"@@"+column+"@@PART NUMBER CAN NOT BE NULL");
                                return err;
                            }
                        } else if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if (partno.length() > 31) {
                                return row + "@@" + column + "##Part Number can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(partno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in Part Number.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                            if (!(r = dao.isCompExist(partno)) != true) {
                                result = "Part Number doesn't exist in database.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result;
                            }
                        } else if (column == 1) {
                            //if Part Description is null.
                            String partDesc = sheet.getCell(column, row).getContents().trim();
                            if (partDesc.equals("")) {
                                err = row + "@@" + column + "##Description can not be blank.";
                                ////System.out.println(row+"@@"+column+"@@DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                            if (partDesc.length() > 250) {
                                return row + "@@" + column + "##Part Description can not be greater than 250 characters.";

                            }
                            r = methodutil.checkSpecialSymbolDescription(partDesc);
                            if (r != true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in Part Description.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        } else if (column == 2) {
                            //if Part Remarks is null.
                            String partRemrks = sheet.getCell(column, row).getContents().trim();
                            if (partRemrks.length() > 250) {
                                return row + "@@" + column + "##Part Remarks can not be greater than 250 characters.";
                            }
                            r = methodutil.checkSpecialSymbolDescription(partRemrks);
                            if (r != true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in Part Remarks.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        } else if (column == 3) {
                            String serviciability = sheet.getCell(column, row).getContents().trim();
                            if (serviciability.length() > 50) {
                                return row + "@@" + column + "##Serviciability can not be greater than 50 characters.";
                            }
                            if ((!serviciability.equalsIgnoreCase("Y")) && (!serviciability.equalsIgnoreCase("N"))) {
                                err = row + "@@" + column + "##Serviciability Y / N  are allowed only.";
//                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                            r = methodutil.checkSpecialSymbolPart(serviciability);
                            if (r != true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in Serviciability.";
//                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
//                            if (!(r = dao.isCategoryTypeExist(categoryType)) != true) {
//                                result = "Category doesn't exist in database.";
//                                return "There is an error at Column 1, Row " + (row + 1) + " " + result;
//                            }
                        }
                    }
//                    c = column;
//                    //checking for PART Parameter Values.
//                    for (column = c; column < sheet_col; column++) {
//                        String value = sheet.getCell(column, row).getContents().trim();
//                        String param_desc = sheet.getCell(column, 2).getContents().trim();
//                        if (!value.equals("")) {
//                            String res = methodutil.validateCompParameter(comp_no, "PRT", param_desc, value, conn);
//                            if (!res.equals("success")) {
//                                err = row + "@@" + column + "##" + res + ".";
//                                ////System.out.println(row+"@@"+column+"##"+res+".");
//                                return err;
//                            }
//                        }
//                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }


            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                ////System.out.println(row+"@@"+column+"@@'END' MISSING");
                return err;
            }

//            rs.close();
//            stmt.close();
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            ////System.out.println(row+"@@"+column+"@@TEMPLATE MISMATCH");
            return err;

        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;

    }

    public String isPricelistExcelValidated(File xlsfile, Connection conn) throws Exception {
        ////System.out.println("pxlsfile:"+xlsfile);
        //declaration of variables used.
        EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);
        // Session session = HibernateUtil.getSessionFactory().openSession();
        // Statement stmt = null;
        // ResultSet rs = null;
        boolean r = false;
        int row = 0;
        int column = 0;
        // int sheet_col = 0;
        //  int count = 0, c = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        // String comp_no = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        //  Vector paramVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        try {
            //  stmt = conn.createStatement();
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for PART TEMPLATE.
           /* if (!(sheet.getCell(column, row).getContents().trim().equals("PART TEMPLATE"))) {
            err = row + "@@" + column + "##'PART TEMPLATE' Missing.";
            ////System.out.println(err);
            return err;
            }
            row++;
            //checking for blank row.
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
            err = row + "@@" + column + "##Row should be blank.";
            ////System.out.println(row+"@@"+column+"@@ROW SHOULD BE BLANK");
            return err;
            }
            row++;
            //checking for PART NUMBER.
             */
            if (!(sheet.getCell(column, row).getContents().trim().equals("Part Code"))) {
                err = row + "@@" + column + "##'Part Code' Missing.";
                ////System.out.println(row+"@@"+column+"@@PART NUMBER MISSING");
                return err;
            }
            ////System.out.println("column+1="+(column+1)+", row= "+row+"********"+sheet.getCell(column+1,row).getContents().trim());
            column++;
            //checking for PART DESCRIPTION.


            if (!(sheet.getCell(column, row).getContents().trim().equals("Price"))) {
                err = row + "@@" + column + "##'Price' Missing.";
                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("Pricelist"))) {
                err = row + "@@" + column + "##'Pricelist' Missing.";
                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
                return err;
            }
            column++;
//            if (!(sheet.getCell(column, row).getContents().trim().equals("CATEGORY TYPE"))) {
//                err = row + "@@" + column + "##'CATEGORY TYPE' Missing.";
//                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
//                return err;
//            }
//            column++;
//            if (!(sheet.getCell(column, row).getContents().trim().equals("Cury."))) {
//                err = row + "@@" + column + "##'Cury.' Missing.";
//                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
//                return err;
//            }
//            column++;
//            if (!(sheet.getCell(column, row).getContents().trim().equals("np4"))) {
//                err = row + "@@" + column + "##'PART STATUS' Missing.";
//                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
//                return err;
//            }
//            column++;
            //checking for PART PARAMETER DESCRIPTION from COMP_PARAM_MASTER
//            rs = stmt.executeQuery("select part_no from part order by part_no");
//            //sheet_col=sheet.getColumns();
//            ////System.out.println("Sheet Columns:"+sheet_col);
//            while (rs.next()) {
//                paramVec.add(rs.getString(1));
//                count++;
//            }
//            //checking for number of Parameters.
////            if(count!=(sheet_col-2))
////            {
////                column=sheet_col;
////                if(count<(sheet_col-2))
////                {
////                    column=sheet_col-1;
////                }
////                err=row+"@@"+column+"##Number of Parameters does not match.";
////                ////System.out.println(row+"@@"+column+"@@NUMBER OF PARAMETERS ARE DIFFERENT"+sheet_col);
////                return err;
////            }
//            //checking for Parameter Names.
//            for (int k = 0; k < paramVec.size(); k++) {
//                String part = "";
//                try {
//                    part = sheet.getCell(column, row).getContents().trim();
//                } catch (ArrayIndexOutOfBoundsException exception) {
//                    err = row + "@@" + column + "##Parameter Name doesn't match with '" + paramVec.elementAt(k) + "'.";
//                    ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+tool);
//                    return err;
//                }
//                if (!(part.equals(paramVec.elementAt(k)))) {
//                    err = row + "@@" + column + "##Parameter Name doesn't match with '" + paramVec.elementAt(k) + "'.";
//                    ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+tool);
//                    return err;
//                }
//                column++;
//            }
            // sheet_col = paramVec.size() + 2;
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equals("end"))) {

                err = row + "@@" + column + "##No Records Available.";
                ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+part);
                return err;
            }

            //getting total number of rows.
            totalrows = sheet.getRows();
            ////System.out.println("Rows:"+totalrows);
            //loop till 'end' encounters.
            while (row < totalrows) {
                // comp_no = "";
                //if element does not equal to end.
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    //   comp_no = sheet.getCell(0, row).getContents().trim();
                    for (column = 0; column < 4; column++) {
                        //if Part Number is null.
                        //if (sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("")) {
                        if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if ((r = dao.isCompExist(partno)) != true) {
//                                result = "Part Number doesn't exist in database.";
//                                return "There is an error at Column 1, Row " + (row + 1) + " " + result;
                                err = row + "@@" + column + "##Part Number doesn't exist in database.";
                                return err;
                            }
//                            Query query = session.createQuery("select partNo from CatPart where partNo=:partNo ");
//                            query.setParameter("partNo", partno);
//                            List list = query.list();
//                            Iterator itr = list.iterator();
//                            if (!itr.hasNext()) {
//                                return "Error, Row " + (row + 1) + ", Column " + (column + 1) + ".  Part No. not Exists. ";
//                            }
                            if (partno.equals("")) {
                                err = row + "@@" + column + "##Part Code can not be blank. Specify 'end' if required.";
                                ////System.out.println(row+"@@"+column+"@@PART NUMBER CAN NOT BE NULL");
                                return err;
                            }
                        } else if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if (partno.length() > 31) {
                                return row + "@@" + column + "##Part Code can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(partno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in Part Code.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
//                            if (!(r = dao.isCompExist(partno)) != true) {
//                                result = "Part Number doesn't exist in database.";
//                                return "There is an error at Column 1, Row " + (row + 1) + " " + result;
                            //                               err = row + "@@" + column + "##Part Number doesn't exist in database.";
                            //                               return err;
                            //                           }
                        } else if (column == 1) {
                            //if Part Description is null.
                            String partDesc = sheet.getCell(column, row).getContents().trim();
                            if (partDesc.equals("")) {
                                err = row + "@@" + column + "##Price can not be blank.";
                                ////System.out.println(row+"@@"+column+"@@DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
//                            if (partDesc.length() > 250) {
//                                return row + "@@" + column + "##Part Description can not be greater than 250 characters.";
//
//                            }
                            r = methodutil.checkSpecialSymbolDescription(partDesc);
                            if (r != true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in Price";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        } else if (column == 2) {
                            //if Part Remarks is null.
                            String currency = sheet.getCell(column, row).getContents().trim();
//                            if (currency.length() > 250) {
//                                return row + "@@" + column + "##Cury. can not be greater than 250 characters.";
//                            }
                            r = methodutil.checkSpecialSymbolDescription(currency);
                            if (r != true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in Pricelist";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                            /* if(!currency.equalsIgnoreCase("INR")){
                            err = row + "@@" + column + "##Cury. should be 'INR'";
                            ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                            return err;
                            }*/
                        }
                        /*  else if (column == 3) {
                        String serviciability = sheet.getCell(column, row).getContents().trim();
                        if (serviciability.length() > 50) {
                        return row + "@@" + column + "##Serviciability can not be greater than 50 characters.";
                        }
                        if ((!serviciability.equalsIgnoreCase("Y")) && (!serviciability.equalsIgnoreCase("N"))) {
                        err = row + "@@" + column + "##Serviciability Y / N  are allowed only.";
                        //                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                        return err;
                        }
                        r = methodutil.checkSpecialSymbolPart(serviciability);
                        if (r != true) {
                        err = row + "@@" + column + "##Special Symbols are not allowed in Serviciability.";
                        //                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                        return err;
                        }
                        //                            if (!(r = dao.isCategoryTypeExist(categoryType)) != true) {
                        //                                result = "Category doesn't exist in database.";
                        //                                return "There is an error at Column 1, Row " + (row + 1) + " " + result;
                        //                            }
                        }*/
                    }
//                    c = column;
//                    //checking for PART Parameter Values.
//                    for (column = c; column < sheet_col; column++) {
//                        String value = sheet.getCell(column, row).getContents().trim();
//                        String param_desc = sheet.getCell(column, 2).getContents().trim();
//                        if (!value.equals("")) {
//                            String res = methodutil.validateCompParameter(comp_no, "PRT", param_desc, value, conn);
//                            if (!res.equals("success")) {
//                                err = row + "@@" + column + "##" + res + ".";
//                                ////System.out.println(row+"@@"+column+"##"+res+".");
//                                return err;
//                            }
//                        }
//                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }


            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                ////System.out.println(row+"@@"+column+"@@'END' MISSING");
                return err;
            }

//            rs.close();
//            stmt.close();
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            ////System.out.println(row+"@@"+column+"@@TEMPLATE MISMATCH");
            return err;

        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;

    }

    public String isTSNExcelValidated(File xlsfile, Connection conn) throws Exception {
        ////System.out.println("pxlsfile:"+xlsfile);
        //declaration of variables used.
        EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);

       // Statement stmt = null;
        ResultSet rs = null;
        boolean r = false;
        int row = 0;
        int column = 0;
        int sheet_col = 0;
        int count = 0, c = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        String comp_no = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        Vector paramVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        try {
            //stmt = conn.createStatement();
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for TSN TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("TSN TEMPLATE"))) {
                err = row + "@@" + column + "##'TSN TEMPLATE' Missing.";
                ////System.out.println(err);
                return err;
            }
            row++;
            //checking for blank row.
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = row + "@@" + column + "##Row should be blank.";
                ////System.out.println(row+"@@"+column+"@@ROW SHOULD BE BLANK");
                return err;
            }
            row++;
            //checking for TSN.
            if (!(sheet.getCell(column, row).getContents().trim().equals("TSN"))) {
                err = row + "@@" + column + "##'TSN' Missing.";
                return err;
            }
            ////System.out.println("column+1="+(column+1)+", row= "+row+"********"+sheet.getCell(column+1,row).getContents().trim());
            column++;
            //checking for FCODE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("FCODE"))) {
                err = row + "@@" + column + "##'FCODE' Missing.";
                ////System.out.println(row+"@@"+column+"@@FCODE MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("MFG.DATE"))) {
                err = row + "@@" + column + "##'MFG.DATE' Missing.";
                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
                return err;
            }
            column++;

            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equals("end"))) {

                err = row + "@@" + column + "##No Records Available.";
                ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+TSN);
                return err;
            }

            //getting total number of rows.
            totalrows = sheet.getRows();
            ////System.out.println("Rows:"+totalrows);
            //loop till 'end' encounters.
            while (row < totalrows) {
                comp_no = "";
                //if element does not equal to end.
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    comp_no = sheet.getCell(0, row).getContents().trim();
                    for (column = 0; column < 3; column++) {
                        //if TSN Number is null.
                        //if (sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("")) {
                        if (column == 0) {
                            String tsn = sheet.getCell(column, row).getContents().trim();
                            if (tsn.equals("")) {
                                err = row + "@@" + column + "##TSN can not be blank. Specify 'end' if required.";
                                ////System.out.println(row+"@@"+column+"@@TSN CAN NOT BE NULL");
                                return err;
                            }

                            if (tsn.length() > 31) {
                                return row + "@@" + column + "##TSN can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(tsn);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in TSN.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
//                            if (!(r = dao.isCompExist(tsn)) != true) {
//                                result = "TSN doesn't exist in database.";
//                                return "There is an error at Column 1, Row " + (row + 1) + " " + result;
//                            }
                        } else if (column == 1) {
                            //if Part Description is null.
                            String fcode = sheet.getCell(column, row).getContents().trim();
                            if (fcode.equals("")) {
                                err = row + "@@" + column + "##FCode can not be blank.";
                                ////System.out.println(row+"@@"+column+"@@DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                            if (fcode.length() > 31) {
                                return row + "@@" + column + "##FCode can not be greater than 31 characters.";

                            }
                            r = methodutil.checkSpecialPart_1(fcode);
                            if (r != true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in FCode.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        } else if (column == 2) {
                            //if Part Remarks is null.
                            String mfgDate = sheet.getCell(column, row).getContents().trim();

                            boolean flag = methodutil.isValidDateString(mfgDate);
                            if (flag == false) {
                                return row + "@@" + column + "##Mfg.Date is Incorrect, Please Enter Date in Proper format.";
                            }
                        }
//								else if (column == 3) {
//                            String categoryType = sheet.getCell(column, row).getContents().trim();
//                            if (categoryType.length() > 250) {
//                                return row + "@@" + column + "##Category Type can not be greater than 250 characters.";
//                            }
//                            r = methodutil.checkSpecialSymbolPart(categoryType);
//                            if (r != true) {
//                                err = row + "@@" + column + "##Special Symbols are not allowed in Category Type.";
//                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
//                                return err;
//                            }
//                            if (!(r = dao.isCategoryTypeExist(categoryType)) != true) {
//                                result = "Category doesn't exist in database.";
//                                return "There is an error at Column 1, Row " + (row + 1) + " " + result;
//                            }
//                        }
                    }
//                    c = column;
//                    //checking for PART Parameter Values.
//                    for (column = c; column < sheet_col; column++) {
//                        String value = sheet.getCell(column, row).getContents().trim();
//                        String param_desc = sheet.getCell(column, 2).getContents().trim();
//                        if (!value.equals("")) {
//                            String res = methodutil.validateCompParameter(comp_no, "PRT", param_desc, value, conn);
//                            if (!res.equals("success")) {
//                                err = row + "@@" + column + "##" + res + ".";
//                                ////System.out.println(row+"@@"+column+"##"+res+".");
//                                return err;
//                            }
//                        }
//                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }


            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                ////System.out.println(row+"@@"+column+"@@'END' MISSING");
                return err;
            }

//            rs.close();
//            stmt.close();
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            ////System.out.println(row+"@@"+column+"@@TEMPLATE MISMATCH");
            return err;

        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;

    }

    ////////////////////////////////////////////////////////////////////////////////
    ////6.to validate Tool Excel./////////////////////////////////////////////////
    public String isToolExcelValidated(File xlsfile, Connection conn) throws Exception {
        EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        boolean r = false;
        int row = 0;
        int column = 0, c = 0;
        int sheet_col = 0;
        int count = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        String param = "";
        String comp_no = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        Vector paramVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();

        try {
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for KIT TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("TOOL TEMPLATE"))) {
                err = row + "@@" + column + "##'TOOL TEMPLATE' Missing.";
                ////System.out.println(err);
                return err;
            }
            row++;
            //checking for blank row.
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = row + "@@" + column + "##ROW SHOULD BE BLANK.";
                ////System.out.println(row+"@@"+column+"@@ROW SHOULD BE BLANK");
                return err;
            }
            row++;
            //checking for KIT NUMBER.
            if (!(sheet.getCell(column, row).getContents().trim().equals("TOOL NUMBER"))) {
                err = row + "@@" + column + "##'TOOL NUMBER' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL NUMBER MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("TOOL DESCRIPTION"))) {
                err = row + "@@" + column + "##'TOOL DESCRIPTION' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("TOOL REMARKS"))) {
                err = row + "@@" + column + "##'TOOL REMARKS' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("CATEGORY TYPE"))) {
                err = row + "@@" + column + "##'CATEGORY TYPE' Missing.";
                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
                return err;
            }
            column++;
//            //checking for KIT PARAMETER DESCRIPTION from COMP_PARAM_MASTER
//            rs = stmt.executeQuery("select PARAM_DESC from COMP_PARAM_MASTER where PARAM_TYPE='KIT' order by PARAM_DESC");
//            sheet_col = sheet.getColumns();
//            ////System.out.println("Sheet Columns:"+sheet_col);
//
//            while (rs.next()) {
//                paramVec.add(rs.getString(1));
//                count++;
//            }
//            //checking for number of parameters.
//            if (count != (sheet_col - 2)) {
//                column = sheet_col;
//                if (count < (sheet_col - 2)) {
//                    column = sheet_col - 1;
//                }
//                err = row + "@@" + column + "##Number of Parameters does not match.";
//                ////System.out.println(row+"@@"+column+"@@NUMBER OF PARAMETERS ARE DIFFERENT"+sheet_col);
//                return err;
//            }
//            //checking for parameter names.
//            for (int k = 0; k < paramVec.size(); k++) {
//                param = "" + paramVec.elementAt(k);
//                if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(param))) {
//                    err = row + "@@" + column + "##Parameter Name doesn't match with '" + param + "'.";
//                    ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+tool);
//                    return err;
//                }
//                column++;
//            }

            column = 0;
            row++;
            //getting total number of rows.
            totalrows = sheet.getRows();
            ////System.out.println("Rows:"+totalrows);
            //loop till 'end' encounters
            while (row < totalrows) {
                comp_no = "";
                //if element does not equal to end.
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    comp_no = sheet.getCell(0, row).getContents().trim();
                    for (column = 0; column < 3; column++) {
                        //if KIT Number is null.
                        if (column == 0) {
                            String kitno = sheet.getCell(column, row).getContents().trim();
                            if (kitno.equals("")) {
                                err = row + "@@" + column + "##Tool Number can not be blank. Specify 'end' if required.";
                                ////System.out.println(row+"@@"+column+"@@PART NUMBER CAN NOT BE NULL");
                                return err;
                            }
                        } else if (column == 0) {
                            String kitno = sheet.getCell(column, row).getContents().trim();
                            if (kitno.length() > 31) {
                                return row + "@@" + column + "##Tool Number can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(kitno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in Tool Number.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                            if (!(r = dao.isCompExist(kitno)) != true) {
                                result = "Tool Number doesn't exist in database.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result;
                            }
                        } else if (column == 1) {
                            //if KIT Description is null.
                            String kitDesc = sheet.getCell(column, row).getContents().trim();
                            if (kitDesc.equals("")) {
                                err = row + "@@" + column + "##Description can not be blank.";
                                ////System.out.println(row+"@@"+column+"@@DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                            if (kitDesc.length() > 250) {
                                return row + "@@" + column + "##Tool Description can not be greater than 250 characters.";

                            }
                            r = methodutil.checkSpecialSymbolDescription(kitDesc);
                            if (r != true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in Tool Description.";
                                return err;
                            }
                        } else if (column == 2) {
                            //if KIT Remarks is null.
                            String partRemrks = sheet.getCell(column, row).getContents().trim();
                            if (partRemrks.length() > 250) {
                                return row + "@@" + column + "##Tool Remarks can not be greater than 250 characters.";
                            }
                            r = methodutil.checkSpecialSymbolDescription(partRemrks);
                            if (r != true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in Tool Remarks.";
                                return err;
                            }
                        } else if (column == 3) {
                            String categoryType = sheet.getCell(column, row).getContents().trim();
                            if (categoryType.length() > 250) {
                                return row + "@@" + column + "##Category Type can not be greater than 250 characters.";
                            }
                            r = methodutil.checkSpecialSymbolDescription(categoryType);
                            if (r != true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in Category Type.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                            if (!(r = dao.isCategoryTypeExist(categoryType)) != true) {
                                result = "Category doesn't exist in database.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result;
                            }
                        }
                    }
                    c = column;
                    //checking for KIT Parameter Values.
                    for (column = c; column < sheet_col; column++) {
                        String value = sheet.getCell(column, row).getContents().trim();
                        String param_desc = sheet.getCell(column, 2).getContents().trim();
                        if (!value.equals("")) {
                            String res = methodutil.validateCompParameter(comp_no, "TOOL", param_desc, value, conn);
                            if (!res.equals("success")) {
                                err = row + "@@" + column + "##" + res;
                                return err;
                            }
                        }
                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }
            //if 'end' missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                ////System.out.println(row+"@@"+column+"@@'END' Missing");
                return err;
            }
        } //if 'end' missing.
        catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            ////System.out.println(row + "@@" + column + "@@'END' MISSING");
            return err;

        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;

    }
    ////////////////////////////////////////////////////////////////////////////////
    ////7.to validate KIT Excel./////////////////////////////////////////////////

    public String isKitExcel(String fileName, int index, Sheet sheet, Connection conn) throws Exception {

        String result = "";
        String result1 = "<br>Hence Kit Creation Process aborted. Attach valid Excel to complete the Process successfully.";
        EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);
        ResultSet rs = null;
        String cellContent = null;
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        boolean r1 = false;

        try {

            if ((sheet.getCell(0, 0).getContents().trim()).equals("KIT TEMPLATE")) {

                if (!sheet.getCell(0, 1).getContents().equalsIgnoreCase("")) {
                    result = "Row should be blank.";
                    return "There is an error at Column 1, Row 2. " + result + result1;
                    //return result;
                }
                if ((sheet.getCell(0, 2).getContents().trim()).equals("KIT NUMBER")) {

                    String kitno = sheet.getCell(1, 2).getContents().trim();

                    if (kitno.equals("")) {
                        result = "Kit Number cannot be blank.";
                        return "There is an error at Column 2, Row 3. " + result + result1;
                    }

                    String comp_in_db = new EAMG_MethodsUtility().isCompPresent(kitno, conn);
                    if (!comp_in_db.equals("notpresent")) {
                        result = comp_in_db;
                        return "There is an error at Column 2, Row 3. " + result + result1;
                    }
                    if (kitno.length() > 31) {
                        return "There is an error at Column 2, Row 3. " + "Kit Number can not be greater than 31 characters." + result1;
                    }
                    r1 = methodutil.checkSpecialPart_1(kitno);
                    if (r1 != true) {
                        return "There is an error at Column 2, Row 3. " + "Space and Special Symbols are not allowed in Kit Number.<br>" + result1;
                    }

                } else {
                    result = "'KIT NUMBER' Missing.";
                    return "There is an error at Column 1, Row 3. " + result + result1;
                }

                if ((sheet.getCell(0, 3).getContents().trim()).equals("KIT DESCRIPTION")) {
                    cellContent = sheet.getCell(1, 3).getContents().trim();
                    if (cellContent.equals("")) {
                        result = "Kit Description cannot be blank.";
                        return "There is an error at Column 2, Row 4. " + result + result1;
                    } else if ((r1 = methodutil.checkSpecialSymbolDescription(cellContent)) != true) {
                        result = "'KIT DESCRIPTION' Cannot Contain Special Symbol.";
                        return "There is an error at Column 2, Row 4. " + result + result1;
                    }
                } else {
                    result = "'KIT DESCRIPTION' Missing.";
                    return "There is an error at Column 2, Row 4. " + result + result1;
                }
                if ((sheet.getCell(0, 4).getContents().trim()).equals("KIT REMARKS")) {
                    cellContent = sheet.getCell(1, 4).getContents().trim();
                    if (cellContent.equals("")) {
                        result = "Kit Remarks cannot be blank.";
                        return "There is an error at Column 2, Row 5. " + result + result1;
                    } else if ((r1 = methodutil.checkSpecialSymbolDescription(cellContent)) != true) {
                        result = "'KIT REMARKS' Cannot Contain Special Symbol.";
                        return "There is an error at Column 2, Row 5. " + result + result1;
                    }
                } else {
                    result = "'KIT REMARKS' Missing.";
                    return "There is an error at Column 2, Row 5. " + result + result1;
                }
                if ((sheet.getCell(0, 5).getContents().trim()).equals("CATEGORY TYPE")) {
                    cellContent = sheet.getCell(1, 5).getContents().trim();
                    if (!cellContent.equals("")) {
                        if ((r1 = methodutil.checkSpecialSymbolDescription(cellContent)) != true) {
                            result = "'CATEGORY TYPE' Cannot Contain Special Symbol.";
                            return "There is an error at Column 2, Row 6. " + result + result1;
                        }
                    }
                } else {
                    result = "'CATEGORY TYPE' Missing.";
                    return "There is an error at Column 2, Row 6. " + result + result1;
                }

//                if ((sheet.getCell(0, 4).getContents().trim()).equals("MOQ")) {
//                    cellContent = sheet.getCell(1, 4).getContents().trim();
//                    if (cellContent.equals("")) {
//                        result = "MOQ cannot be blank.";
//                        return "There is an error at Column 2, Row 5. " + result + result1;
//                    } else if ((r1 = methodutil.checkNumeric(cellContent)) != true) {
//                        result = "'MOQ' Should Be Numeric.";
//                        return "There is an error at Column 2, Row 5. " + result + result1;
//                    } else if (cellContent.length() > 4) {
//                        result = "MOQ Should Be Less than 4 Character.";
//                        return "There is an error at Column 2, Row 5. " + result + result1;
//                    }
//                } else {
//                    result = "'MOQ' Missing.";
//                    return "There is an error at Column 2, Row 5. " + result + result1;
//                }
//                if ((sheet.getCell(0, 5).getContents().trim()).equals("QML")) {
//                    cellContent = sheet.getCell(1, 5).getContents().trim();
//                    if (cellContent.equals("")) {
//                        result = "QML cannot be blank.";
//                        return "There is an error at Column 2, Row 6. " + result + result1;
//                    } else if ((r1 = methodutil.checkNumeric(cellContent)) != true) {
//                        result = "'QML' Should Be Numeric.";
//                        return "There is an error at Column 2, Row 6. " + result + result1;
//                    } else if (cellContent.length() > 4) {
//                        result = "QML Should Be Less than 4 Character.";
//                        return "There is an error at Column 2, Row 5. " + result + result1;
//                    }
//                } else {
//                    result = "'QML' Missing.";
//                    return "There is an error at Column 2, Row 6. " + result + result1;
//                }
//                if ((sheet.getCell(0, 6).getContents().trim()).equals("NDP")) {
//                    cellContent = sheet.getCell(1, 6).getContents().trim();
//                    if (cellContent.equals("")) {
//                        result = "NDP cannot be blank.";
//                        return "There is an error at Column 2, Row 7. " + result + result1;
//                    } else if ((r1 = methodutil.checkNumeric(cellContent)) != true) {
//                        result = "'NDP' Should Be Numeric.";
//                        return "There is an error at Column 2, Row 7. " + result + result1;
//                    } else if (cellContent.length() > 4) {
//                        result = "NDP Should Be Less than 4 Character.";
//                        return "There is an error at Column 2, Row 5. " + result + result1;
//                    }
//                } else {
//                    result = "'NDP' Missing.";
//                    return "There is an error at Column 2, Row 7. " + result + result1;
//                }

                int row = 7;

                if (!sheet.getCell(0, row).getContents().equals("ITEM")) {
                    result = "'ITEM' Missing.";
                    return "There is an error at Column 2, Row " + (row + 1) + ". " + result + result1;
                }
                if (!sheet.getCell(1, row).getContents().equals("QTY")) {
                    result = "'QTY' Missing.";
                    return "There is an error at Column 5, Row " + (row + 1) + ". " + result + result1;
                }
                row++;
                int r = 0;

                while (!(sheet.getCell(0, row).getContents().trim()).equals("end")) {
                    for (int m = 0; m < 2; m++) {
                        if (m == 0) {
                            cellContent = sheet.getCell(m, row).getContents().trim();
                            if (cellContent.equals("")) {
                                result = "Item cannot be blank.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result + result1;
                            } else if ((r1 = methodutil.checkSpecialPart_1(cellContent)) != true) {
                                result = "Item Cannot Contain Special Character.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result + result1;
                            } else if ((r1 = dao.isCompExist(cellContent)) != true) {
                                result = "Item doesn't exist in database.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result + result1;
                            }
                        } else if (m == 1) {
                            cellContent = sheet.getCell(m, row).getContents().trim();
                            if (cellContent.equals("")) {
                                result = "Qty cannot be blank.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result + result1;
                            } else if ((r1 = methodutil.checkNumeric(cellContent)) != true) {
                                result = "Qty Should Be Numeric.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result + result1;
                            } else if (cellContent.length() > 4) {
                                result = "Qty Should Be Less than 4 Character.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result + result1;
                            }
                        }
                    }
                    row++;
                    r++;
                }

            } else {
                result = "'KIT TEMPLATE' Missing.";
                return "There is an error at Column 1, Row 1. " + result + result1;
            }
            result = "Excel Validation Successful";
        } catch (Exception e) {
            e.printStackTrace();
            result = "TEMPLATE MISMATCH or 'end' Missing.";
            return result + result1;
        }
        return result;
    }

    public String isAssemblyExcel(String fileName, int index, Sheet sheet, Connection conn) throws Exception {

        String result = "";
        String result1 = "<br>Hence Assembly Creation Process aborted. Attach valid Excel to complete the Process successfully.";

        HashSet allData = new HashSet();
        ArrayList assyParams = new ArrayList();
        ArrayList dbParam = new ArrayList();
        ArrayList level_InExcel = new ArrayList();
        ArrayList compType_InExcel = new ArrayList();
        ArrayList item_InExcel = new ArrayList();
        ArrayList des_InExcel = new ArrayList();
        ArrayList qty_InExcel = new ArrayList();

        HashSet assyExcelData = new HashSet();
        int columns = sheet.getColumns();
        int rows = sheet.getRows();
        ResultSet rs, rs1 = null;
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        boolean r1 = false;

        try {

            //Statement stmt, stmt1 = null;
        	PreparedStatement stmt, stmt1 = null;
            String selectQury = "select distinct PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='ASM' ";
            stmt = conn.prepareStatement(selectQury);
            rs = stmt.executeQuery();
            //stmt = conn.createStatement();
            //stmt1 = conn.createStatement();

            //rs = stmt.executeQuery(selectQury);

            while (rs.next()) {
                assyParams.add(rs.getString(1));
            }
            dbParam = new EAMG_MethodUtility2().param_partUassy(conn);

            int assyParams_size = assyParams.size();


            String testSpecialChar = "";

            if ((sheet.getCell(0, 0).getContents().trim()).equals("ASSEMBLY TEMPLATE")) {
                if (!sheet.getCell(0, 1).getContents().equals("")) {
                    result = "Row should be blank.";
                    return "There is an error at Column 1, Row 2. " + result + result1;
                    //return result;
                }
                if ((sheet.getCell(0, 2).getContents().trim()).equals("ASSEMBLY NUMBER")) {


                    String assno = sheet.getCell(1, 2).getContents().trim();
                    if (assno.equals("")) {
                        result = "Assembly Number cannot be blank.";
                        return "There is an error at Column 2, Row 3. " + result + result1;
                    }
                    String comp_in_db = new EAMG_MethodsUtility().isCompPresent(assno, conn);
                    if (!comp_in_db.equals("notpresent")) {
                        result = comp_in_db;
                        return "There is an error at Column 2, Row 3. " + result + result1;
                    }
                    if (assno.length() > 31) {
                        result = "Assembly Number can not be greater than 31 characters.";
                        return "There is an error at Column 2, Row 3. " + result + result1;
                    }

                    r1 = methodutil.checkSpecialPart_1(assno);
                    if (r1 != true) {
                        result = "Space and Special Symbols are not allowed in Assembly Number.<br>Allowed Special Symbols are (_) underscore and (-) Hyphen.";
                        return "There is an error at Column 2, Row 3. " + result + result1;

                    }

                    if ((sheet.getCell(0, 3).getContents().trim()).equals("ASSEMBLY DESCRIPTION")) {
                        if (sheet.getCell(1, 3).getContents().equals("")) {
                            result = "Assembly Description cannot be blank.";
                            return "There is an error at Column 2, Row 4. " + result + result1;
                        }

                    } else {
                        result = "'ASSEMBLY DESCRIPTION' Missing.";
                        return "There is an error at Column 1, Row 4. " + result + result1;
                    }
                    int row = 4;
                    for (int j = 0; j < assyParams.size(); j++) {

                        if (!(sheet.getCell(0, row).getContents().trim()).equals(assyParams.get(j)) && row < rows) {
                            result = "Parameter Name doesn't match with '" + assyParams.get(j) + "'.";
                            return "There is an error at Column 1, Row " + (row + 1) + ". " + result + result1;

                        } else {
                            String comp_no = sheet.getCell(1, 2).getContents().trim();
                            String value = sheet.getCell(1, row).getContents().trim();
                            String param_desc = "" + assyParams.get(j);
                            if (!value.equals("")) {
                                String res = methodutil.validateCompParameter(comp_no, "ASM", param_desc, value, conn);
                                if (!res.equals("success")) {
                                    return "There is an error at Column 2, Row " + (row + 1) + ". " + res + result1;
                                }
                            }
                        }
                        row++;

                    }
                    int levelRow = assyParams_size + 5;
                    if (!sheet.getCell(0, levelRow - 1).getContents().equals("")) {
                        result = "Row should be blank.";
                        return "There is an error at Column 1, Row " + (levelRow) + ". " + result + result1;
                        //return result;
                    }

                    if (!sheet.getCell(0, levelRow).getContents().equals("LEVEL")) {
                        result = "'LEVEL' Missing.";
                        return "There is an error at Column 1, Row " + (levelRow + 1) + ". " + result + result1;
                        //return result;
                    }
                    if (!sheet.getCell(1, levelRow).getContents().equals("ITEM")) {
                        result = "'ITEM' Missing.";
                        return "There is an error at Column 2, Row " + (levelRow + 1) + ". " + result + result1;
                    }
                    if (!sheet.getCell(2, levelRow).getContents().equals("DESCRIPTION")) {
                        result = "'DESCRIPTION' Missing.";
                        return "There is an error at Column 3, Row " + (levelRow + 1) + ". " + result + result1;
                    }
                    if (!sheet.getCell(3, levelRow).getContents().equals("TYPE")) {
                        result = "'TYPE' Missing.";
                        return "There is an error at Column 4, Row " + (levelRow + 1) + ". " + result + result1;
                    }
                    if (!sheet.getCell(4, levelRow).getContents().equals("QTY")) {
                        result = "'QTY' Missing.";
                        return "There is an error at Column 5, Row " + (levelRow + 1) + ". " + result + result1;
                    }
                    if (sheet.getCell(0, levelRow + 1).getContents().equals("end")) {
                        result = "No Records Available for Assembly BOM.";
                        return "There is an error at Column 1, Row " + (levelRow + 2) + ". " + result + result1;
                    }
//                    if(sheet.getCell(0,levelRow+1).getContents().equals("")) {
//                        result="Level should not be blank. Specify 'end' if Required";
//                        return "There is an error at Column 1, Row "+(levelRow+2)+". "+result+result1;
//                    }
//                    if(!sheet.getCell(0,levelRow+1).getContents().equals("1")) {
//                        result="First value for Level column should be '1' only.";
//                        return "There is an error at Column 1, Row "+(levelRow+2)+". "+result;
//                    }
                    Vector allData1 = new Vector();
                    for (int col = 0; col < columns; col++) {
                        //System.out.println(sheet.getCell(col,assyParams_size+5).getContents().trim());
                        //allData.add(sheet.getCell(col,assyParams_size+5).getContents().trim());
                        allData1.add(sheet.getCell(col, assyParams_size + 5).getContents().trim());
                    }
//                    //System.out.println("dbParam :"+dbParam);
//                    //System.out.println("allData :"+allData);
                    //System.out.println("allData1 :"+allData1);

                    for (int i = 0; i < dbParam.size(); i++) {
                        String param_InExcel = "";
                        try {
                            param_InExcel = "" + allData1.elementAt(i + 5);
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            result = "Parameter Name doesn't match with '" + dbParam.get(i) + "'.";
                            return "There is an error at Column " + (i + 6) + ", Row " + (levelRow + 1) + ". " + result + result1;
                        }

                        if (!param_InExcel.equals(dbParam.get(i))) {

                            result = "Parameter Name doesn't match with '" + dbParam.get(i) + "'.";
                            return "There is an error at Column " + (i + 6) + ", Row " + (levelRow + 1) + ". " + result + result1;
                        }
                    }




                    int endRow = 0;

                    int r = levelRow + 1;
                    try {
                        while (!(sheet.getCell(0, r).getContents().trim()).equals("end")) {
                            //System.out.println(sheet.getCell(0,r).getContents().trim());
                            if ((sheet.getCell(0, r).getContents().trim()).equals("")) {
                                result = "Level can not be blank. Specify 'end' if Required.";
                                return "There is an error at Column 1, Row " + (r + 1) + ". " + result + result1;
                            } else {
                                level_InExcel.add(sheet.getCell(0, r).getContents());
                                item_InExcel.add(sheet.getCell(1, r).getContents());
                                des_InExcel.add(sheet.getCell(2, r).getContents());
                                compType_InExcel.add(sheet.getCell(3, r).getContents());
                                qty_InExcel.add(sheet.getCell(4, r).getContents());
                                r++;
                            }

                        }
                        endRow = r;

                    } catch (Exception ne) {
                        result = "Excel should be end with 'end'";
                        return "There is an error at Column 1, Row " + (r + 3) + ". " + result + result1;
                    }
                    ArrayList assy_Detail = new ArrayList();
                    assy_Detail.add(item_InExcel);
                    assy_Detail.add(level_InExcel);
                    assy_Detail.add(compType_InExcel);

                    if (!level_InExcel.get(0).equals("1")) {
                        result = "Assembly BOM should start with Level '1' only.";
                        return "There is an error at Column 1, Row " + (levelRow + 2) + ". " + result + result1;
                    }

                    for (int i = 0; i < compType_InExcel.size(); i++) {
                        try {
                            String level = "" + level_InExcel.get(i);
                            ////System.out.println("level data ;"+ level);
                            int level_val = Integer.parseInt(level);

                            if (level_InExcel.get(i).equals("0")) {
                                result = "Level can not be 0.";
                                return "There is an error at Column 1, Row " + (levelRow + i + 3) + ". " + result + result1;
                                //return result;
                            }

                            if (i != level_InExcel.size() - 1) {
                                String levela = "" + level_InExcel.get(i + 1);
                                int levela_val = Integer.parseInt(levela);
                                if (compType_InExcel.get(i).equals("ASM")) {
                                    if ((level_val + 1) != levela_val) {
                                        result = "Level is not valid.";
                                        return "There is an error at Column 1, Row " + (levelRow + i + 3) + ". " + result + result1;
                                    }
                                } else {
                                    if ((level_val) != levela_val && levela_val != 1) {
                                        result = "Assembly BOM should start with Level '1' only.";
                                        return "There is an error at Column 1, Row " + (levelRow + i + 3) + ". " + result + result1;
                                    }
                                }
                            }

                            //return true;
                        } catch (Exception e) {
                            //System.out.println("Exception :"+ e);
                            // return false;
                            result = "Only numeric values are allowed in Level.";
                            return "There is an error at Column 1, Row " + (levelRow + i + 3) + ". " + result + result1;
                        }
                        if (item_InExcel.get(i).equals("")) {
                            result = "Item can not be blank.";
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        if (item_InExcel.get(i).toString().equalsIgnoreCase(assno)) {
                            result = "Item can not be same as Assembly Number.";
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        if (("" + item_InExcel.get(i)).length() > 31) {
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + "Item can not be greater than 31 characters." + result1;
                        }
                        r1 = methodutil.checkSpecialPart_1(("" + item_InExcel.get(i)));
                        if (r1 != true) {
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + "Space and Special Symbols are not allowed in Item Number.<br>Allowed Special Symbols are (_) underscore and (-) Hyphen." + result1;
                        }
                        String compexistindb = new EAMG_MethodsUtility().checkCompExistsinDB(("" + item_InExcel.get(i)).toUpperCase(), conn);
                        if (!compexistindb.equals("" + compType_InExcel.get(i))) {
                            String type = "";
                            if (compexistindb.equals("PRT")) {
                                type = "Part";
                            } else if (compexistindb.equals("KIT")) {
                                type = "Kit";
                            } else if (compexistindb.equals("GROUP")) {
                                type = "Group";
                            } else if (compexistindb.equals("MODEL")) {
                                type = "Model";
                            } else {
                                type = "Tool";
                            }
                            result = "Item '" + "" + item_InExcel.get(i) + "' exists as '" + type + "' in database.";
                            return "There is an error at Column 2, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        if (des_InExcel.get(i).equals("")) {
                            result = "Description can not be blank.";
                            return "There is an error at Column 3, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        if (!(compType_InExcel.get(i).equals(("ASM")) || compType_InExcel.get(i).equals("PRT"))) {
                            result = "Type Should be ASM or PRT only. ";
                            return "There is an error at Column 4, Row " + (levelRow + i + 2) + ". " + result + result1;
                        }
                        String qty = "" + qty_InExcel.get(i);
                        //   //System.out.println("qty::"+qty);
                        if (qty.equals("")) {
                            //return false;
                            result = "Quantity of any Component can not be blank.";
                            return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                        } else {
                            if (qty.equals("0")) {
                                result = "Quantity can not be 0.";
                                return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                            }

                            boolean b = new EAMG_MethodsUtility().checkNumeric(qty);
                            if (b == false) {
                                result = "No Special Symbols allowed in quantity.";
                                return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                            }
                            if (!qty.equals("AR")) {
                                try {
                                    //System.out.println("Quantity :"+qty);

                                    int quan = Integer.parseInt(qty);
                                    if (quan > 500) {
                                        result = "Quantity can not be greater than 500. Give 'AR' for 'As Required'.";
                                        return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                                    }
                                } catch (Exception e) {
                                    //System.out.println("Exception :"+ e);
                                    result = "Only numeric values are allowed as quantity and quantity can not be greater than 500 or Give 'AR' for 'As Required'.";
                                    return "There is an error at Column 5, Row " + (levelRow + i + 2) + ". " + result + result1;
                                }
                            }


                        }
                    }
                    for (int i = 0; i < level_InExcel.size(); i++) {
                        if (compType_InExcel.get(i).equals("ASM")) {
                            if (level_InExcel.size() == 1) {
                                result = "An Assembly must have sub elements.";
                                return "There is an error at Column 2, Row " + (levelRow + 2) + ". " + result + result1;
                            }
                            if (i < level_InExcel.size()) {
                                String temp_flevel = "" + level_InExcel.get(i);
                                String temp_slevel = "";
                                try {
                                    temp_slevel = "" + level_InExcel.get(i + 1);
                                } catch (Exception exception) {
                                    result = "An Assembly must have sub elements.";
                                    return "There is an error at Column 2, Row " + (levelRow + i + 3) + ". " + result + result1;

                                }


                                int f_level = Integer.parseInt(temp_flevel);
                                int s_level = Integer.parseInt(temp_slevel);

                                if (s_level > f_level) {
                                } else {
                                    result = "An Assembly must have sub elements.";
                                    return "There is an error at Column 2, Row " + (levelRow + i + 3) + ". " + result + result1;
                                }
                            }
                        }
                    }

                    result = new EAMG_MethodUtility2().checkDuplicates(item_InExcel, compType_InExcel);
                    if (!result.equals("")) {
                        return result + result1;
                    }
                    result = new EAMG_MethodUtility2().checkAssyBom(assy_Detail, "ASM");
                    ////System.out.println("result :" + result);
                    if (!result.equals("success")) {
                        return result + result1;
                    }
                    Vector params = new Vector();
                    for (int i = 0; i < (allData1.size() - 5); i++) {
                        params.add(sheet.getCell(i + 5, levelRow).getContents());
                    }

                    for (int i = 0; i < params.size(); i++) {
                        int colmn = levelRow + 1;
                        Vector tempAl = new Vector();
                        int colUsed = (5 + i);
                        int cnt = 1;
                        for (int rw = colmn; rw < endRow; rw++) {
                            tempAl.add(sheet.getCell(colUsed, (levelRow + cnt)).getContents());
                            cnt++;
                        }
                        //System.out.println("tempAl :"+tempAl);
                        for (int j = 0; j < tempAl.size(); j++) {
                            String comp_no = "" + item_InExcel.get(j);
                            String comp_type = "" + compType_InExcel.get(j);
                            String param_desc = "" + params.elementAt(i);
                            String value = "" + tempAl.elementAt(j);
                            String message = "";
                            // //System.out.println("all values are :"+comp_no + "; "+comp_type+ ": "+param_desc+ ":"+ value);

                            if (!value.equals("")) {
                                message = new EAMG_MethodsUtility().validateCompParameter(comp_no, comp_type, param_desc, value, conn);

                                //  //System.out.println("message:"+message);
                                if (!message.equals("success")) {
                                    result = message;
                                    return "There is an error at Column " + (colUsed + 1) + ", Row " + (levelRow + j + 2) + ". " + result + result1;
                                }
                            }
                        }
                    }

//                    for(int row = 0;row < endRow;row++){
//                        for(int col = 0;col < columns;col++) {
//                            String special=sheet.getCell(col, row).getContents();//
//                           
//                            boolean b= new EAMG_MethodsUtility().checkSpecialSymbol(special);
//                            if(b==false) {
//                                result="Special characters are not allowed.";
//                                return "There is an error at Column "+(col+1)+", Row " +(row+1)+". "+result+result1;
//                            }
//                        }
//                    }

                    result = "Excel Validation Successful";

                } else {
                    result = "'ASSEMBLY NUMBER' Missing.";
                    return "There is an error at Column 1, Row 3. " + result + result1;
                }

            } else {
                result = "'ASSEMBLY TEMPLATE' Missing.";
                return "There is an error at Column 1, Row 1. " + result + result1;

            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "TEMPLATE MISMATCH or 'end' Missing.";
            return result + result1;
        }

        return result;
    }
    ///////////////////////////////////////////////////////////////////////////////////
    ////9.to validate Assembly Excel with data./////////////////////////////////////////////////

    public String isAssemblyExcelValidated(File xlsfile, Connection conn) throws Exception {
        ////System.out.println("xlsfile:"+xlsfile);
        //declaration of variables used.
        //Statement stmt = conn.createStatement();
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean r = false;
        int row = 0;
        int column = 0, c = 0;
        int sheet_col = 0;
        int count = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        String param = "";
        String comp_no = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        Vector paramVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();

        try {
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for ASSEMBLY TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("ASSEMBLY TEMPLATE"))) {
                err = row + "@@" + column + "##'ASSEMBLY TEMPLATE' Missing.";
                ////System.out.println(err);
                return err;
            }
            row++;
            //checking for blank row.
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = row + "@@" + column + "##ROW SHOULD BE BLANK.";
                ////System.out.println(row+"@@"+column+"@@ROW SHOULD BE BLANK");
                return err;
            }
            row++;
            //checking for ASSEMBLY NUMBER.
            if (!(sheet.getCell(column, row).getContents().trim().equals("ASSEMBLY NUMBER"))) {
                err = row + "@@" + column + "##'ASSEMBLY NUMBER' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL NUMBER MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("ASSEMBLY DESCRIPTION"))) {
                err = row + "@@" + column + "##'ASSEMBLY DESCRIPTION' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION MISSING");
                return err;
            }
            column++;
            //checking for ASSEMBLY PARAMETER DESCRIPTION from COMP_PARAM_MASTER
            //rs = stmt.executeQuery("select PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='ASM' order by PARAM_DESC");
            String query = ("select PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='ASM' order by PARAM_DESC");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            sheet_col = sheet.getColumns();
            ////System.out.println("Sheet Columns:"+sheet_col);

            while (rs.next()) {
                paramVec.add(rs.getString(1));
                count++;
            }
            //checking for number of parameters.
            if (count != (sheet_col - 2)) {
                column = sheet_col;
                if (count < (sheet_col - 2)) {
                    column = sheet_col - 1;
                }
                err = row + "@@" + column + "##Number of Parameters does not match.";
                ////System.out.println(row+"@@"+column+"@@NUMBER OF PARAMETERS ARE DIFFERENT"+sheet_col);
                return err;
            }
            //checking for parameter names.
            for (int k = 0; k < paramVec.size(); k++) {
                param = "" + paramVec.elementAt(k);
                if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(param))) {
                    err = row + "@@" + column + "##Parameter Name doesn't match with '" + param + "'.";
                    ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+tool);
                    return err;
                }
                column++;
            }

            column = 0;
            row++;
            //getting total number of rows.
            totalrows = sheet.getRows();
            ////System.out.println("Rows:"+totalrows);
            //loop till 'end' encounters
            while (row < totalrows) {
                comp_no = "";
                //if element does not equal to end.
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    comp_no = sheet.getCell(0, row).getContents().trim();
                    for (column = 0; column < 2; column++) {
                        //if Assembly Number is null.
                        if (sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("")) {
                            if (column == 0) {
                                err = row + "@@" + column + "##Assembly Number can not be blank. Specify 'end' if required.";
                                ////System.out.println(row+"@@"+column+"@@PART NUMBER CAN NOT BE NULL");
                                return err;
                            } //if Part Description is null.
                            else {
                                err = row + "@@" + column + "##Assembly Description can not be blank.";
                                ////System.out.println(row+"@@"+column+"@@DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        } //checking for special symbols.
                        else if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if (partno.length() > 31) {
                                return row + "@@" + column + "##Assembly Number can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(partno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in Assembly Number. Allowed Special Symbols are (_) underscore and (-) Hyphen.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        }

                    }


                    c = column;
                    //checking for Assembly Parameter Values.
                    for (column = c; column < sheet_col; column++) {
                        String value = sheet.getCell(column, row).getContents().trim();
                        String param_desc = sheet.getCell(column, 2).getContents().trim();
                        if (!value.equals("")) {
                            String res = methodutil.validateCompParameter(comp_no, "ASM", param_desc, value, conn);
                            if (!res.equals("success")) {
                                err = row + "@@" + column + "##" + res;
                                ////System.out.println(row+"@@"+column+"##"+res);
                                return err;
                            }
                        }

                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }
            //if 'end' missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                ////System.out.println(row+"@@"+column+"@@'END' Missing");
                return err;
            }
        } //if 'end' missing.
        catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            ////System.out.println(row + "@@" + column + "@@'END' MISSING");
            return err;

        } //if excel is currupted.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;

    }
    ////////////////////////////////////////////////////////////////////////////////
    ////10.to validate Kit Excel with data./////////////////////////////////////////////////

    public String isKitExcelValidated(File xlsfile, Connection conn) throws Exception {
        ////System.out.println("xlsfile:"+xlsfile);
        //declaration of variables used.
        EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);
        //Statement stmt = conn.createStatement();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean r = false;
        int row = 0;
        int column = 0, c = 0;
        int sheet_col = 0;
        int count = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        String param = "";
        String comp_no = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        Vector paramVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();

        try {
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for KIT TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("KIT TEMPLATE"))) {
                err = row + "@@" + column + "##'KIT TEMPLATE' Missing.";
                ////System.out.println(err);
                return err;
            }
            row++;
            //checking for blank row.
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = row + "@@" + column + "##ROW SHOULD BE BLANK.";
                ////System.out.println(row+"@@"+column+"@@ROW SHOULD BE BLANK");
                return err;
            }
            row++;
            //checking for KIT NUMBER.
            if (!(sheet.getCell(column, row).getContents().trim().equals("KIT NUMBER"))) {
                err = row + "@@" + column + "##'KIT NUMBER' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL NUMBER MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("KIT DESCRIPTION"))) {
                err = row + "@@" + column + "##'KIT DESCRIPTION' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("KIT REMARKS"))) {
                err = row + "@@" + column + "##'KIT REMARKS' Missing.";
                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION MISSING");
                return err;
            }
            column++;
//            //checking for KIT PARAMETER DESCRIPTION from COMP_PARAM_MASTER
//            rs = stmt.executeQuery("select PARAM_DESC from COMP_PARAM_MASTER where PARAM_TYPE='KIT' order by PARAM_DESC");
//            sheet_col = sheet.getColumns();
//            ////System.out.println("Sheet Columns:"+sheet_col);
//
//            while (rs.next()) {
//                paramVec.add(rs.getString(1));
//                count++;
//            }
//            //checking for number of parameters.
//            if (count != (sheet_col - 2)) {
//                column = sheet_col;
//                if (count < (sheet_col - 2)) {
//                    column = sheet_col - 1;
//                }
//                err = row + "@@" + column + "##Number of Parameters does not match.";
//                ////System.out.println(row+"@@"+column+"@@NUMBER OF PARAMETERS ARE DIFFERENT"+sheet_col);
//                return err;
//            }
//            //checking for parameter names.
//            for (int k = 0; k < paramVec.size(); k++) {
//                param = "" + paramVec.elementAt(k);
//                if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(param))) {
//                    err = row + "@@" + column + "##Parameter Name doesn't match with '" + param + "'.";
//                    ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+tool);
//                    return err;
//                }
//                column++;
//            }

            column = 0;
            row++;
            //getting total number of rows.
            totalrows = sheet.getRows();
            ////System.out.println("Rows:"+totalrows);
            //loop till 'end' encounters
            while (row < totalrows) {
                comp_no = "";
                //if element does not equal to end.
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    comp_no = sheet.getCell(0, row).getContents().trim();
                    for (column = 0; column < 3; column++) {
                        //if KIT Number is null.
                        if (column == 0) {
                            String kitno = sheet.getCell(column, row).getContents().trim();
                            if (kitno.equals("")) {
                                err = row + "@@" + column + "##Kit Number can not be blank. Specify 'end' if required.";
                                ////System.out.println(row+"@@"+column+"@@PART NUMBER CAN NOT BE NULL");
                                return err;
                            }
                        } else if (column == 0) {
                            String kitno = sheet.getCell(column, row).getContents().trim();
                            if (kitno.length() > 31) {
                                return row + "@@" + column + "##Kit Number can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(kitno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in Kit Number.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                            if (!(r = dao.isCompExist(kitno)) != true) {
                                result = "Kit Number doesn't exist in database.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result;
                            }
                        } else if (column == 1) {
                            //if KIT Description is null.
                            String kitDesc = sheet.getCell(column, row).getContents().trim();
                            if (kitDesc.equals("")) {
                                err = row + "@@" + column + "##Description can not be blank.";
                                ////System.out.println(row+"@@"+column+"@@DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                            if (kitDesc.length() > 250) {
                                return row + "@@" + column + "##Kit Description can not be greater than 250 characters.";

                            }
                            r = methodutil.checkSpecialSymbolDescription(kitDesc);
                            if (r != true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in Kit Description.";
                                return err;
                            }
                        } else if (column == 2) {
                            //if KIT Remarks is null.
                            String partRemrks = sheet.getCell(column, row).getContents().trim();
                            if (partRemrks.length() > 250) {
                                return row + "@@" + column + "##Kit Remarks can not be greater than 250 characters.";
                            }
                            r = methodutil.checkSpecialSymbolDescription(partRemrks);
                            if (r != true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in Kit Remarks.";
                                return err;
                            }
                        }
                    }
                    c = column;
                    //checking for KIT Parameter Values.
                    for (column = c; column < sheet_col; column++) {
                        String value = sheet.getCell(column, row).getContents().trim();
                        String param_desc = sheet.getCell(column, 2).getContents().trim();
                        if (!value.equals("")) {
                            String res = methodutil.validateCompParameter(comp_no, "KIT", param_desc, value, conn);
                            if (!res.equals("success")) {
                                err = row + "@@" + column + "##" + res;
                                return err;
                            }
                        }
                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }
            //if 'end' missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                ////System.out.println(row+"@@"+column+"@@'END' Missing");
                return err;
            }
        } //if 'end' missing.
        catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            ////System.out.println(row + "@@" + column + "@@'END' MISSING");
            return err;

        } //if excel is currupted.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;

    }

    public String isAMWPart_OEMPartExcelValidated(File xlsfile, Connection conn) throws Exception {
        ////System.out.println("pxlsfile:"+xlsfile);
        //declaration of variables used.
        //Statement stmt = null;
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean r = false;
        int row = 0;
        int column = 0;
        int sheet_col = 0;
        int count = 0, c = 0;
        ;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        String comp_no = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        Vector paramVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        try {
            //stmt = conn.createStatement();
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for PART TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("AMW PART OEM PART TEMPLATE"))) {
                err = row + "@@" + column + "##'AMW PART OEM PART TEMPLATE' Missing.";
                ////System.out.println(err);
                return err;
            }
            row++;
            //checking for blank row.
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = row + "@@" + column + "##Row should be blank.";
                ////System.out.println(row+"@@"+column+"@@ROW SHOULD BE BLANK");
                return err;
            }
            row++;
            //checking for PART NUMBER.
            if (!(sheet.getCell(column, row).getContents().trim().equals("AMW_PART_NO"))) {
                err = row + "@@" + column + "##'AMW PART NO' Missing.";
                ////System.out.println(row+"@@"+column+"@@PART NUMBER MISSING");
                return err;
            }
            ////System.out.println("column+1="+(column+1)+", row= "+row+"********"+sheet.getCell(column+1,row).getContents().trim());
            column++;
            //checking for PART DESCRIPTION.
            if (!(sheet.getCell(column, row).getContents().trim().equals("OEM_PART_NO"))) {
                err = row + "@@" + column + "##'OEM PART NUMBER' Missing.";
                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("OEM_PART_DESC"))) {
                err = row + "@@" + column + "##'OEM PART DESCRIPTION' Missing.";
                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
                return err;
            }
            column++;

            sheet_col = paramVec.size() + 2;
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equals("end"))) {

                err = row + "@@" + column + "##No Records Available.";
                ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+part);
                return err;
            }

            //getting total number of rows.
            totalrows = sheet.getRows();
            ////System.out.println("Rows:"+totalrows);
            //loop till 'end' encounters.
            while (row < totalrows) {
                comp_no = "";
                //if element does not equal to end.
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    comp_no = sheet.getCell(0, row).getContents().trim();
                    for (column = 0; column < 2; column++) {
                        //if Part Number is null.
                        if (sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("")) {
                            if (column == 0) {
                                err = row + "@@" + column + "##AMW PART can not be blank. Specify 'end' if required.";
                                ////System.out.println(row+"@@"+column+"@@PART NUMBER CAN NOT BE NULL");
                                return err;
                            } //if Part Description is null.
                            else {
                                err = row + "@@" + column + "##OEM PART NUMBER can not be blank.";
                                ////System.out.println(row+"@@"+column+"@@DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        } else if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if (partno.length() > 31) {
                                return row + "@@" + column + "##AMW PART can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(partno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in AMW PART. Allowed Special Symbols are (_) underscore and (-) Hyphen.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                            if (partno.length() > 31) {
                                return row + "@@" + column + "##OEM PART NUMBER can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(partno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in OEM PART NUMBER. Allowed Special Symbols are (_) underscore and (-) Hyphen.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        }

                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }
            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                ////System.out.println(row+"@@"+column+"@@'END' MISSING");
                return err;
            }

//            rs.close();
//            stmt.close();
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            ////System.out.println(row+"@@"+column+"@@TEMPLATE MISMATCH");
            return err;

        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;

    }

    public String isLubeToolExcelValidated(File xlsfile, Connection conn) throws Exception {
        ////System.out.println("pxlsfile:"+xlsfile);
        //declaration of variables used.
        EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);

        //Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean r = false;
        int row = 0;
        int column = 0;
        int sheet_col = 0;
        int count = 0, c = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        String comp_no = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        Vector paramVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        try {
            //stmt = conn.createStatement();
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            //checking for PART TEMPLATE.
            if (!(sheet.getCell(column, row).getContents().trim().equals("TEMPLATE"))) {
                err = "Row " + (row + 1) + " Column " + (column + 1) + " 'TEMPLATE' Missing.";
                ////System.out.println(err);
                return err;
            }
            row++;
            //checking for blank row.
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = "Row " + (row + 1) + " Column " + (column + 1) + " Row should be blank.";
                ////System.out.println(row+"@@"+column+"@@ROW SHOULD BE BLANK");
                return err;
            }
            row++;
            //checking for PART NUMBER.
            if (!(sheet.getCell(column, row).getContents().trim().equals("COMPONENT NUMBER"))) {
                err = "Row " + (row + 1) + " Column " + (column + 1) + " 'COMPONENT NUMBER' Missing.";
                ////System.out.println(row+"@@"+column+"@@PART NUMBER MISSING");
                return err;
            }
            ////System.out.println("column+1="+(column+1)+", row= "+row+"********"+sheet.getCell(column+1,row).getContents().trim());
            column++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("COMPONENT TYPE"))) {
                err = "Row " + (row + 1) + " Column " + (column + 1) + " 'COMPONENT TYPE' Missing.";
                ////System.out.println(row+"@@"+column+"@@PART NUMBER MISSING");
                return err;
            }
            ////System.out.println("column+1="+(column+1)+", row= "+row+"********"+sheet.getCell(column+1,row).getContents().trim());
            column++;
            //checking for PART DESCRIPTION.
            if (!(sheet.getCell(column, row).getContents().trim().equals("COMPONENT DESCRIPTION"))) {
                err = "Row " + (row + 1) + " Column " + (column + 1) + " 'COMPONENT DESCRIPTION' Missing.";
                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("COMPONENT REMARKS"))) {
                err = "Row " + (row + 1) + " Column " + (column + 1) + " 'COMPONENT REMARKS' Missing.";
                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("CATEGORY TYPE"))) {
                err = "Row " + (row + 1) + " Column " + (column + 1) + " 'CATEGORY TYPE' Missing.";
                ////System.out.println(row+"@@"+column+"@@DESCRIPTION MISSING");
                return err;
            }
            column++;

            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equals("end"))) {

                err = "Row " + (row + 1) + " Column " + (column + 1) + " No Records Available.";
                ////System.out.println(row+"@@"+column+"@@DOESN'T MATCH WITH "+part);
                return err;
            }

            //getting total number of rows.
            totalrows = sheet.getRows();
            ////System.out.println("Rows:"+totalrows);
            //loop till 'end' encounters.
            while (row < totalrows) {
                comp_no = "";
                //if element does not equal to end.
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    comp_no = sheet.getCell(0, row).getContents().trim();
                    for (column = 0; column < 5; column++) {
                        //if Part Number is null.
                        //if (sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("")) {
                        if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if (partno.equals("")) {
                                err = "Row " + (row + 1) + " Column " + (column + 1) + " Component Number can not be blank. Specify 'end' if required.";
                                ////System.out.println(row+"@@"+column+"@@PART NUMBER CAN NOT BE NULL");
                                return err;
                            }
                        } else if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if (partno.length() > 31) {
                                return "Row " + (row + 1) + " Column " + (column + 1) + " Component Number can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(partno);
                            if (r != true) {
                                err = "Row " + (row + 1) + " Column " + (column + 1) + " Space and Special Symbols are not allowed in Part Number.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                            if (!(r = dao.isCompExist(partno)) != true) {
                                result = "Component Number doesn't exist in database.";
                                return "There is an error at Column 1, Row " + (row + 1) + " " + result;
                            }
                        } else if (column == 1) {
                            //if Part Description is null.
                            String partType = sheet.getCell(column, row).getContents().trim();
                            if (partType.equals("")) {
                                err = "Row " + (row + 1) + " Column " + (column + 1) + " Type can not be blank.";
                                ////System.out.println(row+"@@"+column+"@@DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }

                            if (!partType.equalsIgnoreCase("Tool") && !partType.equalsIgnoreCase("Lubes")) {
                                return "Row " + (row + 1) + " Column " + (column + 1) + " Invalid Component Type.";

                            }

                        } else if (column == 2) {
                            //if Part Description is null.
                            String partDesc = sheet.getCell(column, row).getContents().trim();
                            if (partDesc.equals("")) {
                                err = "Row " + (row + 1) + " Column " + (column + 1) + " Description can not be blank.";
                                ////System.out.println(row+"@@"+column+"@@DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                            if (partDesc.length() > 250) {
                                return "Row " + (row + 1) + " Column " + (column + 1) + " Component Description can not be greater than 250 characters.";

                            }
                            r = methodutil.checkSpecialSymbolDescription(partDesc);
                            if (r != true) {
                                err = "Row " + (row + 1) + " Column " + (column + 1) + " Special Symbols are not allowed in Component Description.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        } else if (column == 3) {
                            //if Part Remarks is null.
                            String partRemrks = sheet.getCell(column, row).getContents().trim();
                            if (partRemrks.length() > 250) {
                                return "Row " + (row + 1) + " Column " + (column + 1) + " Part Remarks can not be greater than 250 characters.";
                            }
                            r = methodutil.checkSpecialSymbolDescription(partRemrks);
                            if (r != true) {
                                err = "Row " + (row + 1) + " Column " + (column + 1) + " Special Symbols are not allowed in Part Remarks.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }
                        } else if (column == 4) {
                            String categoryType = sheet.getCell(column, row).getContents().trim();
                            if (categoryType.length() > 250) {
                                return "Row " + (row + 1) + " Column " + (column + 1) + " Category Type can not be greater than 250 characters.";
                            }
                            r = methodutil.checkSpecialSymbolDescription(categoryType);
                            if (r != true) {
                                err = "Row " + (row + 1) + " Column " + (column + 1) + " Special Symbols are not allowed in Category Type.";
                                ////System.out.println(row+"@@"+column+"@@TOOL DESCRIPTION CAN NOT BE NULL");
                                return err;
                            }

                        }
                    }

                } else {
                    result = "Excel Validation Successful";
                    break;
                }
                row++;
                column = 0;
            }


            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = "Row " + (row + 1) + " Column " + (column + 1) + " 'end' Missing.";
                ////System.out.println(row+"@@"+column+"@@'END' MISSING");
                return err;
            }

//            rs.close();
//            stmt.close();
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = "Row " + (row + 1) + " Column " + (column + 1) + " TEMPLATE MISMATCH or 'end' Missing.";
            ////System.out.println(row+"@@"+column+"@@TEMPLATE MISMATCH");
            return err;

        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        System.out.println("manish" + result);
        return result;

    }

    public String isSAPPartExcelValidated(File xlsfile, Connection conn) throws Exception {
        ////System.out.println("pxlsfile:"+xlsfile);
        //declaration of variables used.
        EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);

       // Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean r = false;
        int row = 0;
        int column = 0;
        int sheet_col = 0;
        int count = 0, c = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        ArrayList<String> part_no = null;
        Workbook workbook1 = null;
        Sheet sheet = null;
        Vector paramVec = new Vector();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        try {
            //stmt = conn.createStatement();
            part_no = new ArrayList<String>();
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);

            if (!(sheet.getCell(column, row).getContents().trim().equals("PART CODE"))) {
                err = row + "@@" + column + "##'PART CODE' Missing.Template error.";
                return err;
            }
            column++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("PART DESCRIPTION"))) {
                err = row + "@@" + column + "##'PART DESCRIPTION' Missing.Template error.";
                return err;
            }
            column++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("GRP"))) {
                err = row + "@@" + column + "##'GRP' Missing.Template error.";
                return err;
            }
            column++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("STD PACKING"))) {
                err = row + "@@" + column + "##'STD PACKING' Missing.Template error.";
                return err;
            }
            column++;


            if (!(sheet.getCell(column, row).getContents().trim().equals("MOQ Stockiest/Dealers"))) {
                err = row + "@@" + column + "##'MOQ Stockiest/Dealers' Missing.Template error.";
                return err;
            }
            column++;

            if (!(sheet.getCell(column, row).getContents().trim().equals("SERVICEABLE"))) {
                err = row + "@@" + column + "##'SERVICEABLE' Missing.Template error.";
                return err;
            }

            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equals("end"))) {

                err = row + "@@" + column + "##No Records Available.";
                return err;
            }

            //getting total number of rows.
            totalrows = sheet.getRows();
            ////System.out.println("Rows:"+totalrows);
            //loop till 'end' encounters.
            while (row < totalrows) {
                //if element does not equal to end.
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    for (column = 0; column < 6; column++) {
                        if (column == 0) {
                            String partcode = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (partcode.equals("")) {
                                err = row + "@@" + column + "##Part Code can not be blank. Specify 'end' if required.";
                                return err;
                            }

                            if (partcode.length() > 50) {
                                return row + "@@" + column + "##Part Code can not be greater than 50 characters.";
                            }
                            r = methodutil.specialCharacterCheck(partcode);
                            if (r == true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in Part Code.";
                                return err;
                            }
                            if (partcode.equals("-")) {
                                err = row + "@@" + column + "##Part Code is Not Valid.";
                                return err;
                            }
                            if (part_no.contains(partcode)) {
                                err = row + "@@" + column + "##Part Code cannot be entered twice.";
                                return err;
                            }
                            if (!part_no.contains(partcode)) {
                                part_no.add(partcode);
                            }
                        } else if (column == 1) {
                            //if Part Description is null.
                            String partdesc = sheet.getCell(column, row).getContents().trim();
                            if (partdesc.equals("")) {
                                err = row + "@@" + column + "##Part Description can not be blank.";
                                return err;
                            }

                        } else if (column == 2) {

                            String grp = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (grp.equals("")) {
                                err = row + "@@" + column + "##GRP can not be blank.";
                                return err;
                            }
                            r = methodutil.specialCharacterCheck(grp);
                            if (r == true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in GRP.";
                                return err;
                            }
                            if (!(grp.equalsIgnoreCase("PRT") || grp.equalsIgnoreCase("LUBES"))) {
                                err = row + "@@" + column + "##GRP can be either 'PRT' or 'LUBES'.";
                                return err;
                            }
                        } else if (column == 3) {
                            String stdpack = sheet.getCell(column, row).getContents().trim();
                            if (stdpack.equals("")) {
                                err = row + "@@" + column + "##STD Packing can not be blank.";
                                return err;
                            }
                            r = methodutil.containsOnlyNumbers(stdpack);
                            if (r != true) {
                                err = row + "@@" + column + "##Only Numbers are allowed in STD Packing.";
                                return err;
                            }
                        } else if (column == 4) {
                            String moq = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (moq.equals("")) {
                                err = row + "@@" + column + "##GRP can not be blank.";
                                return err;
                            }
                            r = methodutil.containsOnlyNumbers(moq);
                            if (r != true) {
                                err = row + "@@" + column + "##Only Numbers are allowed in MOQ.";
                                return err;
                            }
                        } else if (column == 5) {
                            String serviceable = sheet.getCell(column, row).getContents().replaceAll("\\s", "").trim();
                            if (serviceable.equals("")) {
                                err = row + "@@" + column + "##Serviceable can not be blank.";
                                return err;
                            }
                            if (serviceable.equalsIgnoreCase("yes") || serviceable.equalsIgnoreCase("no")) {
                            } else {
                                err = row + "@@" + column + "##Only 'yes' or 'no' can be entered in Serviceable.";
                                return err;
                            }
                        }
                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }


            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                ////System.out.println(row+"@@"+column+"@@'END' MISSING");
                return err;
            }

//            rs.close();
//            stmt.close();
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            ////System.out.println(row+"@@"+column+"@@TEMPLATE MISMATCH");
            return err;

        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;

        }
        return result;

    }
    ////////////////////////////////////////////////////////////////////////////////

    public String isPartVsPaintedValidated(File xlsfile, Connection conn) throws Exception {
        EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);
        boolean r = false;
        int row = 0;
        int column = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART VS PAINT TEMPLATE"))) {
                err = row + "@@" + column + "##'PART VS PAINT TEMPLATE' Missing.";
                return err;
            }
            row++;
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = row + "@@" + column + "##Row should be blank.";
                return err;
            }
            row++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART NUMBER"))) {
                err = row + "@@" + column + "##'PART NUMBER' Missing.";
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("PAINT CODE"))) {
                err = row + "@@" + column + "##'PAINT CODE' Missing.";
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("TYPE"))) {
                err = row + "@@" + column + "##'TYPE' Missing.";
                return err;
            }
            column++;
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equals("end"))) {

                err = row + "@@" + column + "##No Records Available.";
                return err;
            }
            totalrows = sheet.getRows();
            while (row < totalrows) {
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    for (column = 0; column < 3; column++) {
                        if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if (partno.equals("")) {
                                err = row + "@@" + column + "##Part Number can not be blank. Specify 'end' if required.";
                                return err;
                            }
                            if (partno.length() > 31) {
                                return row + "@@" + column + "##Part Number can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(partno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in Part Number.";
                                return err;
                            }
                            if ((r = dao.isCompExist(partno)) != true) {
                                //result = "Part Number doesn't exist in database.";
                                //return "There is an error at Column 1, Row " + (row + 1) + " " + result;
                                err = row + "@@" + column + "##Part Number doesn't exist in database.";
                                return err;
                            }
                        } else if (column == 1) {
                            String paintedno = sheet.getCell(column, row).getContents().trim();
                            if (paintedno.equals("")) {
                                err = row + "@@" + column + "##Paint Code can not be blank.";
                                return err;
                            }
                            if (paintedno.length() > 31) {
                                return row + "@@" + column + "##Paint Code can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(paintedno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in Paint Code.";
                                return err;
                            }
                            if ((r = dao.isCompExist(paintedno)) != true) {
                                //result = "Paint Coder doesn't exist in database.";
                                //return "There is an error at Column 1, Row " + (row + 1) + " " + result;
                                err = row + "@@" + column + "##Paint Code doesn't exist in database.";
                                return err;
                            }
                        } else if (column == 2) {
                            String funcType = sheet.getCell(column, row).getContents().trim();
                            if (funcType.equals("")) {
                                err = row + "@@" + column + "##Type can not be blank.";
                                return err;
                            }
                            if (funcType.length() > 15) {
                                return row + "@@" + column + "##Type can not be greater than 250 characters.";

                            }
                            if (!funcType.equalsIgnoreCase("ADD") && !funcType.equalsIgnoreCase("DELETE")) {
                                err = row + "@@" + column + "##Type can  be allowed ADD / DELETE.";
                                return err;
                            }
                            r = methodutil.checkSpecialSymbolDescription(funcType);
                            if (r != true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in Type.";
                                return err;
                            }
                        }
                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                return err;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            return err;

        } catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;
        }
        return result;
    }

    public String isPartVsAlternateValidated(File xlsfile, Connection conn) throws Exception {
        EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);
        boolean r = false;
        int row = 0;
        int column = 0;
        int totalrows = 0;
        String result = "fail";
        String err = "";
        Workbook workbook1 = null;
        Sheet sheet = null;
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        inventoryDAO inDao = new inventoryDAO();
        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART VS ALTERNATE TEMPLATE"))) {
                err = row + "@@" + column + "##'PART VS ALTERNATE TEMPLATE' Missing.";
                return err;
            }
            row++;
            if (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase(""))) {
                err = row + "@@" + column + "##Row should be blank.";
                return err;
            }
            row++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("PART NUMBER"))) {
                err = row + "@@" + column + "##'PART NUMBER' Missing.";
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("ALTERNATE PART NUMBER"))) {
                err = row + "@@" + column + "##'ALTERNATE PART NUMBER' Missing.";
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("SET"))) {
                err = row + "@@" + column + "##'SET' Missing.";
                return err;
            }
            column++;
            if (!(sheet.getCell(column, row).getContents().trim().equals("TYPE"))) {
                err = row + "@@" + column + "##'TYPE' Missing.";
                return err;
            }
            column++;
            column = 0;
            row++;
            if ((sheet.getCell(column, row).getContents().trim().equals("end"))) {

                err = row + "@@" + column + "##No Records Available.";
                return err;
            }
            totalrows = sheet.getRows();
            while (row < totalrows) {
                if (!(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                    for (column = 0; column < 4; column++) {
                        if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if (partno.equals("")) {
                                err = row + "@@" + column + "##Part Number can not be blank. Specify 'end' if required.";
                                return err;
                            }
                        } else if (column == 0) {
                            String partno = sheet.getCell(column, row).getContents().trim();
                            if (partno.length() > 31) {
                                return row + "@@" + column + "##Part Number can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(partno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in Part Number.";
                                return err;
                            }
                            if ((r = dao.isCompExist(partno)) != true) {
                                //result = "##Part Number doesn't exist in database.";
                                //return "There is an error at Column 1  Row " + (row + 1) + " " + result;
                                err = row + "@@" + column + "##Part Number doesn't exist in database.";
                                return err;
                            }
                        } else if (column == 1) {
                            String alternateno = sheet.getCell(column, row).getContents().trim();
                            if (alternateno.length() > 31) {
                                return row + "@@" + column + "##Alternate Part Number can not be greater than 31 characters.";
                            }
                            r = methodutil.checkSpecialPart_1(alternateno);
                            if (r != true) {
                                err = row + "@@" + column + "##Space and Special Symbols are not allowed in Alternate Part Number.";
                                return err;
                            }
                            if ((r = dao.isCompExist(alternateno)) != true) {
                                //result = "Alternate Part Number doesn't exist in database.";
                                //return "There is an error at Column 1, Row " + (row + 1) + " " + result;
                                err = row + "@@" + column + "##Alternate Part Number doesn't exist in database.";
                                return err;
                            }
                        } else if (column == 2) {
                            String set = sheet.getCell(column, row).getContents().trim();
                            if (set.equals("")) {
                                err = row + "@@" + column + "##set can not be blank.";
                                return err;
                            } else {
                                r = inDao.containsOnlyNumbers(set);
                                if (r != true) {
                                    err = row + "@@" + column + "##Only Numbers are allowed in set.";
                                    return err;
                                }
                            }
                        } else if (column == 3) {
                            //if Type is null.
                            String funcType = sheet.getCell(column, row).getContents().trim();
                            if (funcType.equals("")) {
                                err = row + "@@" + column + "##Type can not be blank.";
                                return err;
                            }
                            if (funcType.length() > 15) {
                                return row + "@@" + column + "##Type can not be greater than 15 characters.";

                            }
                            if (!funcType.equalsIgnoreCase("ADD") && !funcType.equalsIgnoreCase("DELETE")) {
                                err = row + "@@" + column + "##Type can  be allowed ADD / DELETE.";
                                return err;
                            }
                            r = methodutil.checkSpecialSymbolDescription(funcType);
                            if (r != true) {
                                err = row + "@@" + column + "##Special Symbols are not allowed in Type.";
                                return err;
                            }
                        }
                    }
                } else {
                    result = "success";
                    break;
                }
                row++;
                column = 0;
            }
            //checking for 'END' Missing.
            if (row == totalrows && !(sheet.getCell(column, row).getContents().trim().equals("end"))) {
                err = row + "@@" + column + "##'end' Missing.";
                return err;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            err = row + "@@" + column + "##TEMPLATE MISMATCH or 'end' Missing.";
            return err;

        } //if excel is currupted or wrong path specified.
        catch (Exception e) {
            e.printStackTrace();
            result = "readingerr";
            return result;
        }
        return result;
    }
}
