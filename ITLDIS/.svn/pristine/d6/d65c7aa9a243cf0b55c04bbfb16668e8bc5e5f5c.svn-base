/*
 * AT4_MethodsUtility.java
 *
 * Created on November 3, 2008, 9:27 AM
 **
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
/**
 * *************************************************************************************************************
 *
 * @author mina.yadav * S.No. Method Author Version Methods Detail 1.
 * checkCompExistsInDatabase Mina Yadav 1.0 to check whether Group exists or not
 * in the database. 2. writeToFile Mina Yadav 1.0 to write contents to a text
 * file. 3. readFileNUpdateinDB Mina Yadav 1.0 to read data from file and update
 * the database. 4. executeGrpVectorQueryInDB Mina Yadav 1.0 to execute Group
 * Vector query. 5. getSQLTodaysDate Mina Yadav 1.0 to get SQL Date. 6. getCD_No
 * Mina Yadav 1.0 to get CD Number. 7. getPatch_No Mina Yadav 1.0 to get Patch
 * Number. 8. getGRPParameterCount Mina Yadav 1.0 to get Group Parameter Count.
 * 9. checkCompExistsinDB Mina Yadav 1.0 to check whether Component exists or
 * not in the database. 10. checkAssyBomExistsinDB Mina Yadav 1.0 to check
 * whether Assembly Bom exists or not in the database. 11. getPrtUAsmParamCount
 * Mina Yadav 1.0 to get count for union of Part and Assembly. 12.
 * getPartParametersVec Mina Yadav 1.0 to get Part Parameters Vector. 13.
 * getAssyParametersVec Mina Yadav 1.0 to get Assembly Parameters Vector. 14.
 * getGrpBomLevelParametersVec Mina Yadav 1.0 to get Group Bom Level Parameters
 * Vector. 15. getCompParamId Mina Yadav 1.0 to get Component Parameter Id. 16.
 * getGrpBomParamId Mina Yadav 1.0 to get Group Bom Level Parameter Id. 17.
 * getCompDesc Mina Yadav 1.0 to get Component Description. 18. get_subComp Mina
 * Yadav 1.0 to get Sub Components. 19. check_desc Mina Yadav 1.0 to check the
 * subpart of the component. 20. get_printString Mina Yadav 1.0 to get Print
 * String. 21. getGrpBomCustom Pramod Vishwakarma 1.0 to get Group Bom from the
 * database. 22. getAssyBom Pramod Vishwakarma 1.0 to get Assembly Bom from the
 * database. 23. getCompDesc Pramod Vishwakarma 1.0 to get Component Description
 * from the database. 24. refreshGrpBomCustom Pramod Vishwakarma 1.0 to refresh
 * Group Bom Vectors. 25. getParentIdFromCustom Pramod Vishwakarma 1.0 to get
 * Parent Id of the component from the database. 26. getParamValue Pramod
 * Vishwakarma 1.0 to get Parameter Values from the database. 27.
 * refreshAssyBom1 Pramod Vishwakarma 1.0 to refresh Assembly Bom Vectors. 28.
 * getGrpBomVecFromCustom Pramod Vishwakarma 1.0 to get Group Bom Vectors from
 * the database. 29. getIndexFromCustom Pramod Vishwakarma 1.0 to get Index
 * Number from the database. 30. checkSpecialSymbol Pramod Vishwakarma 1.0 to
 * check special symbols. 31. createTemplate Pramod Vishwakarma 1.0 to create
 * templates. 32. getCompNoString Mina Yadav 1.0 to get Component Number String.
 * 33. isCustomPresent Pramod Vishwakarma 1.0 to check whether custom present or
 * not in the database. 34. getLevelValues Pramod Vishwakarma 1.0 to get Level
 * Description of Model from the database. 35. getGrpType Pramod Vishwakarma 1.0
 * to get Type of the Group from the database. 36. getAllGrpTypes Pramod
 * Vishwakarma 1.0 to get all Types of the Group from the database. 37.
 * getCompleteGrps Pramod Vishwakarma 1.0 to get all complete Groups from the
 * database. 38. getGrpBomParamListValues Pramod Vishwakarma 1.0 to get Group
 * Bom Values for List type from the database. 39. getGrpBomParamIdForList
 * Pramod Vishwakarma 1.0 to get Group Bom Parameter Id for List type from the
 * database. 40. isCompPresent Pramod Vishwakarma 1.0 to check whether component
 * present or not in the database. 41. getModelLevelValue Pramod Vishwakarma 1.0
 * to get Level Values of Model Description from the database. 42.
 * getFunctionBody Pramod Vishwakarma 1.0 to create dynamic javascript function
 * body for type checking. 43. getDomainTypeFrmBomParamMaster Pramod Vishwakarma
 * 1.0 to get Type of Parameter of Group Bom Parameter from the database. 44.
 * getDomainTypes Pramod Vishwakarma 1.0 to get all Types from the database. 45.
 * getCompParamDomain Pramod Vishwakarma 1.0 to get Type of Component Parameter
 * from the database. 46. validateCompParameter Pramod Vishwakarma 1.0 to
 * validate component values for their types from the database. 47.
 * validateGrpBomParameter Pramod Vishwakarma 1.0 to validate Group Bom
 * Parameter values for their types from the database. 48. validateDomainType
 * Mina Yadav 1.0 to validate any values with its type. 49.
 * checkSpecialSymbolAll Mina Yadav 1.0 to validate all special symbols. 50.
 * checkSpecialSymbolForComponentNoPramod Vishwakarma 1.0 to check special
 * symbols for Component Number.
 ************************************************************************************************************
 */
package com.EAMG.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mozilla.universalchardet.UniversalDetector;

import jxl.Workbook;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.write.Alignment;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Colour;
import jxl.write.NumberFormats;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author mina.yadav
 */
public class EAMG_MethodsUtility
{

    /**
     * Creates a new instance of AT4_MethodsUtility
     */
    //declaration of global variables.
    int index = 0;
    int level = 0;

    public EAMG_MethodsUtility()
    {
    }

    //4. to execute Group Vector query.
    public String executeGrpVectorQueryInDB(Vector groupInsertVec, Connection conn)
    {
        //////System.out.println("i am in executeGrpVectorQueryInDB");
        //declaration of variables used.
        PreparedStatement ps_insert = null;
        String select_query = "";
        String ins_query = "";
        String result = "success";
        try
        {
            conn.setAutoCommit(false);
            //Statement st = conn.createStatement();
            PreparedStatement st = null;
            //executing the query Vector.
            for (int i = 0; i < groupInsertVec.size(); i += 2)
            {
                select_query = "" + groupInsertVec.elementAt(i);
                ins_query = "" + groupInsertVec.elementAt(i + 1);
                //if Vector element is not 'NA'
                if (!select_query.equals("NA"))
                {
                    ResultSet rs = st.executeQuery(select_query);

                    if (!rs.next())
                    {
                        //////System.out.println("1 ins_query:" + ins_query);
                        //inserting into database.
                        ps_insert = conn.prepareStatement(ins_query);
                        ps_insert.executeUpdate();
                        ps_insert.close();
                    }
                    rs.close();
                } //if Vector element is 'NA'
                else
                {
                    //////System.out.println("2 ins_query:" + ins_query);
                    ps_insert = conn.prepareStatement(ins_query);
                    ps_insert.executeUpdate();
                    //ps_select.close();
                    ps_insert.close();
                }
            }
            st.close();
            conn.commit();
        }
        catch (SQLException ex)
        {
            try
            {
                conn.rollback();
                //conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            result = "SQL Exception";
            ex.printStackTrace();
        }
        return result;
    }

    //5. to get SQL Date.
    public java.sql.Date getSQLTodaysDate()
    {
        java.util.Date sysDate = new java.util.Date();
        java.sql.Date todaysSQLDate = new java.sql.Date(sysDate.getTime());
        return todaysSQLDate;
    }

    //6. to get CD Number.
    public int getCD_No(Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        //getting the CD Number.
        ResultSet rs = st.executeQuery("Select max(sno) from CAT_CD(NOLOCK)");
        int cd_no = 0;
        if (rs.next())
        {
            cd_no = rs.getInt(1);
        }
        rs.close();
        st.close();

        return cd_no;
    }

    //7. to get Patch Number.
    public int getPatch_No(Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        int cd_no = 0;
        rs = st.executeQuery("Select max(sno) from CAT_CD(NOLOCK)");
        if (rs.next())
        {
            cd_no = rs.getInt(1);
        }
        rs.close();

        int patch_no = 0;
        rs = st.executeQuery("Select  max(PATCH_NO) from CAT_PATCH(NOLOCK) where CD_NO=" + cd_no);
        if (rs.next())
        {
            patch_no = rs.getInt(1);
        }
        rs.close();
        st.close();

        return patch_no;
    }

    //8. to get Group Parameter Count.
    public int getGRPParameterCount(Connection conn) throws Exception
    {
       // Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        int grp_param_count = 0;
        rs = st.executeQuery("Select  count(*) from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='GRP'");
        if (rs.next())
        {
            grp_param_count = rs.getInt(1);
        }
        rs.close();
        st.close();
        return grp_param_count;
    }

    //9. to check whether Component exists or not in the database.
    public String checkCompExistsinDB(String comp_no, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        String result = "false";
        //if component exists as Assembly, Part, Tool or Kit.
        //rs = st.executeQuery("Select comp_type from COMP_DETAIL where COMP_NO='" + comp_no + "'");
        String query = ("Select comp_type from COMP_DETAIL(NOLOCK) where COMP_NO='" + comp_no + "'");
        st = conn.prepareStatement(query);
        rs = st.executeQuery();
        if (rs.next())
        {
            result = rs.getString(1);
        }
        rs.close();
        //if component exists as Group.
        rs = st.executeQuery("Select * from GROUP_KIT_DETAIL(NOLOCK) where GRP_KIT_NO='" + comp_no + "'");
        if (rs.next())
        {
            result = "GROUP";
        }
        rs.close();
        //if component exists as Model.
        rs = st.executeQuery("Select * from MODELS(NOLOCK) where MODEL_NO='" + comp_no + "'");
        if (rs.next())
        {
            result = "MODEL";
        }
        rs.close();
        st.close();
        return result;
    }

    //10. to check whether Assembly exists or not in the database.
    public boolean checkAssyBomExistsinDB(String comp_no, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        boolean result = false;
        rs = st.executeQuery("Select * from CAT_ASSY_BOM(NOLOCK) where ASSY_NO='" + comp_no + "' and REV_NO=0");
        if (rs.next())
        {
            result = true;
        }
        rs.close();
        st.close();
        return result;
    }

    //11. to get count for union of Part and Assembly.
    public int getPrtUAsmParamCount(Connection conn) throws Exception
    {
       // Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        int grp_param_count = 0;
        rs = st.executeQuery("select distinct PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE in ('PRT','ASM') order by PARAM_DESC");
        while (rs.next())
        {
            grp_param_count++;
        }
        rs.close();
        st.close();
        return grp_param_count;
    }

    //12. to get Part Parameters Vector.
    public Vector getPartParametersVec(Connection conn)
    {
        Vector partParamVec = new Vector();
        try
        {
            //Statement st = conn.createStatement();
        	PreparedStatement st = null;
            ResultSet rs = null;
            rs = st.executeQuery("select distinct PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE ='PRT' order by PARAM_DESC");
            while (rs.next())
            {
                partParamVec.addElement(rs.getString(1));
            }
            rs.close();
            st.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return partParamVec;
    }

    //13. to get Assembly Parameters Vector.
    public Vector getAssyParametersVec(Connection conn)
    {
        Vector assyParamVec = new Vector();
        try
        {
            //Statement st = conn.createStatement();
        	PreparedStatement st = null;
            ResultSet rs = null;

            rs = st.executeQuery("select distinct PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE ='ASM' order by PARAM_DESC");
            while (rs.next())
            {
                assyParamVec.addElement(rs.getString(1));
            }
            rs.close();
            st.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return assyParamVec;
    }

    //14. to get Group Bom Level Parameters Vector.
    public Vector getGrpBomLevelParametersVec(Connection conn)
    {
        Vector grpBomLevelParamVec = new Vector();
        try
        {
            Statement st = conn.createStatement();
            ResultSet rs = null;

            rs = st.executeQuery("select distinct PARAM_DESC from GRP_BOM_PARAM_MASTER order by PARAM_DESC");
            while (rs.next())
            {
                grpBomLevelParamVec.addElement(rs.getString(1));
            }
            rs.close();
            st.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return grpBomLevelParamVec;
    }

    //15. to get Component Parameter Id.
    public int getCompParamId(String param, String comp_type, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        int paramId = 0;
        rs = st.executeQuery("select PARAM_ID from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE ='" + comp_type + "' and PARAM_DESC='" + param + "'");
        if (rs.next())
        {
            paramId = rs.getInt(1);
        }
        rs.close();
        st.close();
        return paramId;
    }

    //16. to get Group Bom Level Parameter Id.
    public int getGrpBomParamId(String param, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
    	ResultSet rs = null;
        int paramId = 0;
        rs = st.executeQuery("select PARAM_ID from GRP_BOM_PARAM_MASTER(NOLOCK) where PARAM_DESC='" + param + "'");
        if (rs.next())
        {
            paramId = rs.getInt(1);
        }
        rs.close();
        st.close();
        return paramId;
    }

    //17. to get Component Description.
    public String getCompDesc(String comp_no, String comp_type, Connection conn) throws Exception
    {
        //////System.out.println("comp_no:" + comp_no);
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
    	ResultSet rs = null;
        String comp_desc = "";
        if (comp_type.equals("GRP"))
        {
            ////System.out.println("comp_no:" + comp_no);
            rs = st.executeQuery("select GRP_KIT_DESC from GROUP_KIT_DETAIL(NOLOCK) where GRP_KIT_NO='" + comp_no + "'");
            if (rs.next())
            {
                comp_desc = rs.getString(1);
            }
            rs.close();
            //////System.out.println("comp_desc:" + comp_desc);
        }
        else
        {
            rs = st.executeQuery("select COMP_DESC from COMP_DETAIL(NOLOCK) where COMP_NO='" + comp_no + "'");
            if (rs.next())
            {
                comp_desc = rs.getString(1);
            }
            rs.close();
        }
        st.close();
        return comp_desc;
    }

    //18. to get Sub Components.
    public Vector get_subComp(String group, int PARENT_ID, String dotplace, Connection conn)
    {
        //declaration of variables used.
        ResultSet rs, rs1;
        Vector subPartVec = new Vector();
        String comp_name = "";
        String seq = "";
        String COMP_TYPE = "";
        String var_DESCRIPTION = "";
        String var_SUPERCEEDENCE = "--";
        String var_DESCRIPTION1 = "";
        String param_val = "";
        boolean checkSubpart = false;
        try
        {

            //Statement stmt = conn.createStatement();
            //Statement stmt1 = conn.createStatement();
        	PreparedStatement stmt ,stmt1 = null;
            //v4_ltk_get_groupInfo ob_get_rev_no = new v4_ltk_get_groupInfo(conn, ps, date_OR_serial, inputDate, serialNo, group);
            //int revNo = ob_get_rev_no.get_groupRevisionNo();
            int revNo = 0;
            //rs.close();
            String query = "";
            if (PARENT_ID == 0)
            {
                query = "SELECT COMPONENT,SEQUENCE,COMP_TYPE,AS_REQUIRED,QTY,INDEX_NO,ROW_ID FROM GROUP_KIT_BOM_CUSTOM(NOLOCK) WHERE GRP_KIT_NO ='" + group + "' AND PARENT_ID=0 AND REV_NO = " + revNo + " ORDER BY INDEX_NO ";
            }
            else
            {
                query = "SELECT COMPONENT,SEQUENCE,COMP_TYPE,AS_REQUIRED,QTY,INDEX_NO,ROW_ID FROM GROUP_KIT_BOM_CUSTOM(NOLOCK) WHERE GRP_KIT_NO ='" + group + "' AND PARENT_ID=" + PARENT_ID + " AND REV_NO = " + revNo + " ORDER BY INDEX_NO";

            }
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            //rs = stmt.executeQuery(query);
            if (rs.next())
            {
                do
                {
                    comp_name = rs.getString(1);
                    seq = rs.getString(2);
                    COMP_TYPE = rs.getString(3);
                    var_DESCRIPTION = getCompDesc(comp_name, COMP_TYPE, conn);
                    //var_SUPERCEEDENCE = get_superceedence(ps, comp_name, group);

                    String qry = "select CV.PARAM_VALUE from COMP_PARAM_VALUES CV ,COMP_PARAM_MASTER CM where CV.COMP_NO='" + comp_name + "' AND CV.COMP_TYPE='" + COMP_TYPE + "' AND CV.PARAM_ID=CM.PARAM_ID AND CV.COMP_TYPE=CM.PARAM_TYPE AND CM.PARAM_DESC='HASHPARAM'";
                    ////System.out.println(qry);
                    stmt1 = conn.prepareStatement(qry);
                    rs1 = stmt1.executeQuery();
                   // rs1 = stmt1.executeQuery(qry);
                    if (rs1.next())
                    {
                        param_val = rs1.getString(1);
                    }
                    else
                    {
                        param_val = "--";
                    }

                    rs1.close();
                    subPartVec.add(comp_name);
                    subPartVec.add(seq);
                    subPartVec.add(COMP_TYPE);

                    if (PARENT_ID != 0)
                    {

                        if (COMP_TYPE.equals("ASM"))
                        {
                            var_DESCRIPTION1 = dotplace + "<b>" + var_DESCRIPTION;

                        }
                        else
                        {
                            var_DESCRIPTION1 = dotplace + var_DESCRIPTION;
                        }
                        subPartVec.add(var_DESCRIPTION1);

                    }
                    else
                    {
                        if (COMP_TYPE.equals("ASM"))
                        {
                            var_DESCRIPTION1 = "<b>" + var_DESCRIPTION;

                        }
                        else
                        {
                            var_DESCRIPTION1 = var_DESCRIPTION;
                        }
                        subPartVec.add(var_DESCRIPTION1);

                    }

                    subPartVec.add(var_SUPERCEEDENCE);
                    subPartVec.add(rs.getString(4));
                    subPartVec.add(rs.getString(5));
                    subPartVec.add(rs.getString(6));
                    subPartVec.add(param_val);
                    int rowid = rs.getInt(7);
                    checkSubpart = check_desc(conn, rowid);
                    //////System.out.println("rowid1:" + rowid);
                    if (checkSubpart)
                    {
                        //////System.out.println("in checkSubpart");
                        String dotplace1 = "&nbsp;&nbsp;<b>.</b>&nbsp;&nbsp;";
                        dotplace1 = dotplace1 + dotplace;
                        //////System.out.println("group:" + group + "******rowid:" + rowid + "@@@@@@@@@dotplace1:" + dotplace1);
                        subPartVec.addAll(get_subComp(group, rowid, dotplace1, conn));
                    }



                }
                while (rs.next());

            }


            rs.close();
            stmt.close();
            stmt1.close();



        }
        catch (Exception ae)
        {
            ae.printStackTrace();

        }
        return subPartVec;

    }

    //19. to check the subpart of the component.
    public boolean check_desc(Connection conn, int rowid)
    {
        try
        {
            //////System.out.println("rowid2:" + rowid);
            //Statement stmt = conn.createStatement();
        	PreparedStatement stmt = null;
            boolean checkSubpart = false;
            ResultSet rs;
            //////System.out.println("select * from GROUP_KIT_BOM_CUSTOM WHERE PARENT_ID=" + rowid + "");
            rs = stmt.executeQuery("select * from GROUP_KIT_BOM_CUSTOM(NOLOCK) WHERE PARENT_ID=" + rowid + "");
            if (rs.next())
            {
                checkSubpart = true;
            }
            rs.close();
            stmt.close();
            //////System.out.println("checkSubpart:" + checkSubpart);
            return checkSubpart;
        }
        catch (Exception e)
        {
            ////System.out.println(e);
            e.printStackTrace();
            return false;
        }

    }

    //20. to get Print String.
    public Vector get_printString(String part_no, String comp_type, String partSearch, String partVal)
    {
        Vector printVec = new Vector();
        try
        {
            String printText = "";
            String linkPart = part_no.replaceAll("&nbsp;", "");
            String winName = linkPart.replaceAll("-", "");
            int checkColor = 0;
            if (comp_type.equals("ASM"))
            {
                // printText = printText + "<a href=javascript:MM_openBrWindow('" + servletURL + "v4_ltk_assyDetails?assyNo=" + linkPart + "','" + winName + "','scrollbars=yes,width=700,height=605')>";
                //printText
                if (partVal.equals("YES"))
                {
                    if (!(partSearch == null) && (winName.equals(partSearch)))
                    {
                        printText = printText + "<font color=#3300FF>";
                        checkColor = 1;
                    }
                    else
                    {
                        printText = printText + "<font color=#990000>";
                    }

                }
                else
                {
                    if (!(partSearch == null) && (winName.equals(partSearch)))
                    {
                        printText = printText + "<font color=#3300FF>";
                        checkColor = 1;
                    }
                }

            }
            else
            {
                //printText = printText + "<a href=javascript:MM_openBrWindow('" + servletURL + "v4_ltk_partDetails?partNo=" + linkPart + "','DETAILS','scrollbars=yes,width=700,height=605')>";
                if (!(partSearch == null) && (winName.equals(partSearch)))
                {
                    printText = printText + "<font color=#3300FF>";
                    checkColor = 1;
                }
                else
                {
                    printText = printText + "<font color=#990000>";
                }

            }

            if (partVal.equals("YES"))
            {
                printText = printText + "<b>";
            }
            printVec.add(printText);
            printVec.add("" + checkColor);
        }
        catch (Exception ae)
        {
            ae.printStackTrace();
        }

        return printVec;



    }

    //21. to get Group Bom from the database.
    public Vector getGrpBomCustom(Vector GrpBomVec, String groupno, Connection conn) throws Exception
    {

        //declaration of variables used.
        String compno = "";
        String comptype = "";
        String qty = "";
        String asreq = "";
        String seqno = "";
        String desc = "";
        Integer revno = null;
        ResultSet rs = null;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        Integer level = new Integer(0);

        //getting the COMPONENT DESCRIPTIONS for the group.
        String query1 = "Select COMPONENT, COMP_TYPE, QTY, AS_REQUIRED,SEQUENCE, REV_NO from GROUP_KIT_BOM(NOLOCK) where GRP_KIT_NO='" + groupno + "'";
        stmt = conn.prepareStatement(query1);
        rs = stmt.executeQuery();
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query1);

        while (rs.next())
        {
            //creating a vector for the description.
            Vector tempVec = new Vector();
            compno = rs.getString(1);
            tempVec.add(compno);
            comptype = rs.getString(2);
            tempVec.add(comptype);
            qty = rs.getString(3);
            asreq = rs.getString(4);
            if (qty.equals("0") && asreq.equalsIgnoreCase("YES"))
            {
                tempVec.add("AR");
            }
            else
            {
                tempVec.add(qty);
            }
            seqno = rs.getString(5);
            tempVec.add(seqno);
            revno = new Integer(rs.getInt(6));
            tempVec.add(revno);
            tempVec.add(level);
            desc = getCompDesc(compno, conn);
            tempVec.add(desc);

            //adding the components to GrpBomVec Vectors.
            for (int i = 0; i < GrpBomVec.size(); i++)
            {
                Vector GrpCompVec = (Vector) GrpBomVec.elementAt(i);
                GrpCompVec.add(tempVec.elementAt(i));
            }

            //////System.out.println("Comp:" + compno);
            //////System.out.println("CompType:" + comptype);
            //if the component is an ASSEMBLY then getting its Bom.
            if (comptype.equals("ASM"))
            {
                GrpBomVec = getAssyBom(GrpBomVec, compno, revno, level, conn);
            }
        }
        return GrpBomVec;
    }

    //22. to get Assembly Bom from the database.
    public Vector getAssyBom(Vector GrpBomVec, String compno, Integer revno, Integer level, Connection conn) throws Exception
    {
        //declaration and initialization of variables used.
        int l = level.intValue();
        l++;
        level = new Integer(l);
        ResultSet rs;
       // Statement stmt = null;
        PreparedStatement stmt = null;
        String comp = "";
        String seq = "";
        String comptype = "";
        String desc = "";

        //getting the components of Assembly.
        String query1 = "Select COMPONENT, COMP_TYPE, QTY from CAT_ASSY_BOM(NOLOCK) where ASSY_NO='" + compno + "' and REV_NO=" + revno.intValue() + "";
        stmt = conn.prepareStatement(query1);
        rs = stmt.executeQuery();
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query1);

        while (rs.next())
        {

            Vector tempVec = new Vector();
            comp = rs.getString(1);
            tempVec.add(comp);
            comptype = rs.getString(2);
            tempVec.add(comptype);
            tempVec.add(rs.getString(3));
            tempVec.add(seq);
            tempVec.add(revno);
            tempVec.add(level);
            desc = getCompDesc(comp, conn);
            tempVec.add(desc);

            //adding the components to GrpBomVec Vectors.
            for (int i = 0; i < GrpBomVec.size(); i++)
            {
                Vector GrpCompVec = (Vector) GrpBomVec.elementAt(i);
                GrpCompVec.add(tempVec.elementAt(i));
            }

            //////System.out.println("CompBom:" + comp);
            //////System.out.println("CompTypeBom:" + comptype);

            //if the component is an ASSEMBLY then getting its Bom recursively.
            if (comptype.equals("ASM"))
            {
                GrpBomVec = getAssyBom(GrpBomVec, comp, revno, level, conn);
            }

        }

        return GrpBomVec;
    }

    //23. to get Component Description from the database.
    public String getCompDesc(String compno, Connection conn) throws Exception
    {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //getting the Component Description.
        String query = "Select COMP_DESC from COMP_DETAIL(NOLOCK) where COMP_NO='" + compno + "'";
       stmt = conn.prepareStatement(query);
       rs = stmt.executeQuery();
        // stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        String desc = "";
        if (rs.next())
        {
            desc = rs.getString(1);
        }
        return desc;
    }

    //24. to refresh Group Bom Vectors.
    public Vector refreshGrpBomCustom(Vector GrpBomVec, String[] arrangedcompindex) throws Exception
    {

        //declaration of variables used.
        String comp = "";
        String comptype = "";
        int level1 = 0;
        //getting the vectors of GrpBomVec Vector.
        Vector CompVec = (Vector) GrpBomVec.elementAt(0);
        Vector CompTypeVec = (Vector) GrpBomVec.elementAt(1);
        Vector QtyVec = (Vector) GrpBomVec.elementAt(2);
        Vector SeqNoVec = (Vector) GrpBomVec.elementAt(3);
        Vector RevNoVec = (Vector) GrpBomVec.elementAt(4);
        Vector LevelVec = (Vector) GrpBomVec.elementAt(5);
        Vector CompDescVec = (Vector) GrpBomVec.elementAt(6);
        Vector IndexVec = (Vector) GrpBomVec.elementAt(7);

        //creating new vectors for refreshing GrpBomVec.
        Vector tempCompVec = new Vector();
        Vector tempCompTypeVec = new Vector();
        Vector tempQtyVec = new Vector();
        Vector tempSeqNoVec = new Vector();
        Vector tempRevNoVec = new Vector();
        Vector tempLevelVec = new Vector();
        Vector tempCompDescVec = new Vector();
        Vector tempGrpBomVec = new Vector();
        Vector tempIndexVec = new Vector();

        tempGrpBomVec.add(tempCompVec);
        tempGrpBomVec.add(tempCompTypeVec);
        tempGrpBomVec.add(tempQtyVec);
        tempGrpBomVec.add(tempSeqNoVec);
        tempGrpBomVec.add(tempRevNoVec);
        tempGrpBomVec.add(tempLevelVec);
        tempGrpBomVec.add(tempCompDescVec);
        tempGrpBomVec.add(tempIndexVec);

        int length = arrangedcompindex.length;

        for (int i = 0; i < length; i++)
        {
            //storing values to new Vectors
            index = IndexVec.indexOf(Integer.parseInt(arrangedcompindex[i]));
            Vector tempVec = new Vector();
            comp = "" + CompVec.elementAt(index);
            tempVec.add(comp);
            comptype = "" + CompTypeVec.elementAt(index);
            tempVec.add(comptype);
            tempVec.add(QtyVec.elementAt(index));
            tempVec.add(SeqNoVec.elementAt(index));
            tempVec.add(RevNoVec.elementAt(index));
            level1 = ((Integer) LevelVec.elementAt(index)).intValue();
            tempVec.add(level1);
            tempVec.add(CompDescVec.elementAt(index));
            tempVec.add(IndexVec.elementAt(index));

            //storing new Vectors to tempGrpBomVec Vector.
            for (int j = 0; j < tempGrpBomVec.size(); j++)
            {
                Vector GrpCompVec = (Vector) tempGrpBomVec.elementAt(j);
                GrpCompVec.add(tempVec.elementAt(j));
            }
            //if component is an Assembly then refresh its Bom.
            if (comptype.equals("ASM"))
            {
                index++;
                tempGrpBomVec = refreshAssyBom1(GrpBomVec, tempGrpBomVec, level);
            }
        }
        return tempGrpBomVec;
    }

    //25. to get Parent Id of the component from the database.
    public int getParentIdFromCustom(String grpno, int indexno, Connection conn) throws Exception
    {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //getting the parent id of the component.
        String query = "Select ROW_ID from GROUP_KIT_BOM_CUSTOM(NOLOCK) where GRP_KIT_NO='" + grpno + "' and INDEX_NO=" + indexno;
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        int parentid = 0;
        if (rs.next())
        {
            parentid = rs.getInt(1);
        }
        return parentid;
    }

    //26. to get Parameter Values from the database.
    public Vector getParamValue(String grpno, String compno, String paramdesc, String indexno, Connection conn) throws Exception
    {

        String paramvalue = "";
        Vector typeValueVec = new Vector();
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //stmt = conn.createStatement();
        String paramtype = "";
        int paramId = 0;
        //selecting the Parameter Id for a Parameter Description.
        rs = stmt.executeQuery("select PARAM_ID,PARAM_VALUE_TYPE from GRP_BOM_PARAM_MASTER(NOLOCK) where PARAM_DESC='" + paramdesc + "'");
        if (rs.next())
        {
            paramId = rs.getInt(1);
            paramtype = rs.getString(2);
        }
        rs.close();
        //if Parameter Id is not 0
        if (paramId != 0)
        {
            String query = "Select PARAM_VALUE from GRP_BOM_PARAM_VALUES(NOLOCK) where GROUP_NO='" + grpno + "' and COMP_NO='" + compno + "' and PARAM_ID=" + paramId + " and INDEX_NO='" + indexno + "'";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            //stmt = conn.createStatement();
            //rs = stmt.executeQuery(query);

            while (rs.next())
            {
                paramvalue += rs.getString(1) + ",";
            }
        }

        typeValueVec.add(paramtype);
        typeValueVec.add(paramvalue);
        //if Parameter type is 'LIST' then storing its values to typeValueVec Vector.
        if (paramtype.equals("LIST") || paramtype.equals("MULTI-LIST"))
        {
            Vector valueVec = getGrpBomParamListValues(paramId, conn);
            typeValueVec.add(valueVec);
        }
        return typeValueVec;
    }

    //27. to refresh Assembly Bom Vectors.
    public Vector refreshAssyBom1(Vector GrpBomVec, Vector tempGrpBomVec, int level) throws Exception
    {

        //getting the vectors of GrpBomVec Vector.
        Vector CompVec = (Vector) GrpBomVec.elementAt(0);
        Vector CompTypeVec = (Vector) GrpBomVec.elementAt(1);
        Vector QtyVec = (Vector) GrpBomVec.elementAt(2);
        Vector SeqNoVec = (Vector) GrpBomVec.elementAt(3);
        Vector RevNoVec = (Vector) GrpBomVec.elementAt(4);
        Vector LevelVec = (Vector) GrpBomVec.elementAt(5);
        Vector CompDescVec = (Vector) GrpBomVec.elementAt(6);
        Vector IndexVec = (Vector) GrpBomVec.elementAt(7);

        //declaration of variables used.
        String comp = "";
        String comptype = "";
        int currlevel = ((Integer) LevelVec.elementAt(index)).intValue();

        //refreshing the Assembly Bom.
        while (currlevel > level)
        {

            Vector tempVec = new Vector();
            comp = "" + CompVec.elementAt(index);
            tempVec.add(comp);
            comptype = "" + CompTypeVec.elementAt(index);
            tempVec.add(comptype);
            tempVec.add(QtyVec.elementAt(index));
            tempVec.add(SeqNoVec.elementAt(index));
            tempVec.add(RevNoVec.elementAt(index));
            tempVec.add(new Integer(currlevel));
            tempVec.add(CompDescVec.elementAt(index));
            tempVec.add(IndexVec.elementAt(index));

            //adding the Vector to tempGrpBomVec
            for (int j = 0; j < tempGrpBomVec.size(); j++)
            {
                Vector GrpCompVec = (Vector) tempGrpBomVec.elementAt(j);
                GrpCompVec.add(tempVec.elementAt(j));
            }
            //////System.out.println("GrpCompVec:"+GrpCompVec);

            //if the component is Assembly then refreshing its Bom recursively.
            if (comptype.equals("ASM"))
            {
                tempGrpBomVec = refreshAssyBom1(GrpBomVec, tempGrpBomVec, currlevel);
            }
            if ((index + 1) < CompVec.size())
            {
                index++;
                currlevel = ((Integer) LevelVec.elementAt(index)).intValue();
            }
            else
            {
                return tempGrpBomVec;
            }
        }

        return tempGrpBomVec;
    }

    //28. to get Group Bom Vectors from the database.
    public Vector getGrpBomVecFromCustom(Vector GrpBomVec, String group, int PARENT_ID, Connection conn)
    {

        //declaration of variables used.
        String query = "";
        String compno = "";
        String comptype = "";
        String qty = "";
        String asreq = "";
        String seqno = "";
        String desc = "";
        int index_no = 0;
        int rowid = 0;
        Integer revno = null;
        ResultSet rs = null;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        int revNo = 0;
        //getting the Level Vector from GrpBomVec Vector.
        Vector LevelVec = (Vector) GrpBomVec.elementAt(5);
        boolean checkSubpart = false;
        try
        {
            //stmt = conn.createStatement();
            //getting the component details for first level components from GROUP_KIT_BOM_CUSTOM.
            if (PARENT_ID == 0)
            {
                query = "SELECT COMPONENT,COMP_TYPE,QTY,AS_REQUIRED,SEQUENCE,INDEX_NO,REV_NO,ROW_ID,PARENT_ID FROM GROUP_KIT_BOM_CUSTOM(NOLOCK) WHERE GRP_KIT_NO ='" + group + "' AND PARENT_ID=0 AND REV_NO = " + revNo + " ORDER BY INDEX_NO ";
            } //getting the component details for other level components from GROUP_KIT_BOM_CUSTOM.
            else
            {

                query = "SELECT COMPONENT,COMP_TYPE,QTY,AS_REQUIRED,SEQUENCE,INDEX_NO,REV_NO,ROW_ID,PARENT_ID FROM GROUP_KIT_BOM_CUSTOM(NOLOCK) WHERE GRP_KIT_NO ='" + group + "' AND PARENT_ID=" + PARENT_ID + " AND REV_NO = " + revNo + " ORDER BY INDEX_NO";

            }
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            //rs = stmt.executeQuery(query);
            //////System.out.println("Group no:" + group);
            //////System.out.println("PARENT_ID:" + PARENT_ID);

            while (rs.next())
            {
                Vector tempVec = new Vector();
                compno = rs.getString(1);
                tempVec.add(compno);
                comptype = rs.getString(2);
                tempVec.add(comptype);
                qty = rs.getString(3);
                asreq = rs.getString(4);
                if (qty.equals("0") && asreq.equalsIgnoreCase("YES"))
                {
                    tempVec.add("AR");
                }
                else
                {
                    tempVec.add(qty);
                }
                seqno = rs.getString(5);
                tempVec.add(seqno);
                index_no = 0;
                index_no = rs.getInt(6);
                revno = new Integer(rs.getInt(7));
                tempVec.add(revno);
                rowid = rs.getInt(8);
                PARENT_ID = rs.getInt(9);
                if (PARENT_ID == 0)
                {
                    level = 0;
                }
                if (PARENT_ID != 0)
                {
                    //if PARENT_ID is not 0 then getting PARENT_ID from GROUP_KIT_BOM_CUSTOM.
                    int ind = getIndexFromCustom(group, PARENT_ID, conn);
                    int l = ((Integer) LevelVec.elementAt(ind - 1)).intValue();
                    level = l + 1;
                }
                tempVec.add(new Integer(level));
                desc = getCompDesc(compno, conn);
                tempVec.add(desc);
                tempVec.add(new Integer(index_no));
                //////System.out.println("tempVec:" + tempVec);

                //adding the Vector to GrpBomVec Vectors.
                for (int i = 0; i < GrpBomVec.size(); i++)
                {
                    Vector GrpCompVec = (Vector) GrpBomVec.elementAt(i);
                    GrpCompVec.add(tempVec.elementAt(i));
                }

                if (comptype.equals("ASM"))
                {
                    level++;
                }
                //////System.out.println("GrpBomVec:" + GrpBomVec);
                //////System.out.println("rowid:" + rowid);

                //checking whether the subpart exists or not for a Component.
                checkSubpart = check_desc(conn, rowid);
                if (checkSubpart)
                {
                    //////System.out.println("in checkSubpart");
                    //////System.out.println("group:" + group + "******rowid:" + rowid + "@@@@@@@@@level:" + level);
                    GrpBomVec = getGrpBomVecFromCustom(GrpBomVec, group, rowid, conn);
                }

            }
            rs.close();
        }
        catch (Exception ae)
        {
            ae.printStackTrace();

        }
        return GrpBomVec;

    }

    //29. to get Index Number from the database.
    public int getIndexFromCustom(String grpno, int parentid, Connection conn) throws Exception
    {
        ResultSet rs;
        PreparedStatement stmt = null;
        //getting INDEX_NO from database.
        String query = "Select INDEX_NO from GROUP_KIT_BOM_CUSTOM(NOLOCK) where GRP_KIT_NO='" + grpno + "' and ROW_ID=" + parentid;
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        int indexno = 0;
        if (rs.next())
        {
            indexno = rs.getInt(1);
        }
        return indexno;
    }

    //30. to check special symbols.
    //50. to check special symbols for Component Number.
    public boolean checkSpecialSymbolDescription(String string)
    {

        String iChars = "\\'@&><\"\'";

        //checking special symbols.
        for (int i = 0; i < string.length(); i++)
        {
            //System.out.println(iChars.charAt(i));
            if (iChars.indexOf(string.charAt(i)) != -1)
            {
                return false;
            }
        }
        return true;
    }

    public boolean checkNumeric(String cellValue)
    {

        boolean result = true;

        try
        {
            Integer.parseInt(cellValue);
        }
        catch (Exception e)
        {
            result = false;
        }

        return result;
    }

    /*
     *@author tarun.lal
     *
     *@date 23/12/2014
     *
     * Used to validate Alpha numeric with space and dots Values.
     */
    public boolean isSpecialchar(String val)
    {
        String regexp = "^[a-zA-Z0-9. ]+$";
        // Create a Pattern object
        Pattern r = Pattern.compile(regexp);
        Matcher m = r.matcher(val);
        if (!m.find())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean isValidDateString(String dateString)
    {
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parseDate = null;
        try
        {
            parseDate = out.parse(dateString);

        }
        catch (Exception e)
        {
            return false;
        }

        if (!out.format(parseDate).equals(dateString))
        {
            return false;
        }

        return true;
    }

    public boolean checkSpecialSymbol(String string)
    {

        return true;
    }

    public static String getRealString(String strText)
    {
        //System.out.println("1 : " + strText);
        if (strText == null || strText.equals("") || strText.equals("null"))
        {
            return strText;
        }

        String sc[] =
        {
            "\\", "'"
        };
        String rc[] =
        {
            "\\"
        };
        for (int i = 0; i < sc.length; i++)
        {
            strText = strText.replace(sc[i], (rc[0] + sc[i]));
        }
        return strText;
    }

    public boolean checkSpecialPart_1(String string)
    {

//        String iChars = "+=^*|!,\"~:<>[]{}`\';()@&$#%" + "/" + "\\~?";
//        //checking special symbols.
//        for (int i = 0; i < string.length(); i++) {
//            if (iChars.indexOf(string.charAt(i)) != -1) {
//                return false;
//            }
//        }
        return true;
    }

    public boolean checkSpecialSymbolAll(String string)
    {

        return true;
    }
    //50. to check special symbols for Component Number.

    public boolean checkSpecialSymbolForComponentNo(String string)
    {

        return true;
    }

    public boolean checkSpecialSymbolPart(String string)
    {


        return true;
    }

    //31. to create templates.
    public boolean createTemplate(String comptype, String path, Connection conn)
    {
        //Statement st = null;
    	PreparedStatement st = null;
        ResultSet rs = null;
        String param_domain = "";
        //String param_desc="";
        int colCounter = 0, rowCounter = 0;
        WritableWorkbook workbook = null;
        String filepath = path + "_template.xls";
        File file = new File(filepath);
        if (file.exists())
        {
            file.delete();
        }
        //////System.out.println("File Path" + filepath);

        try
        {
            //getting the workbook.
            workbook = Workbook.createWorkbook(new File(filepath));

            // CREATE A CELL FORMAT HEADING WITH WRAP

            WritableFont heading = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
            WritableCellFormat headingFormat = new WritableCellFormat(heading);
            headingFormat.setWrap(true);
            headingFormat.setAlignment(Alignment.CENTRE);
            headingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            headingFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT HEADING WITH WRAP WITHOUT BORDER

            WritableFont heading_10 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat headingFormat_without = new WritableCellFormat(heading_10);
            headingFormat_without.setWrap(true);
            headingFormat_without.setAlignment(Alignment.CENTRE);
            headingFormat_without.setVerticalAlignment(VerticalAlignment.CENTRE);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTER ALIGNMENT WITH BORDER

            WritableFont text = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat textFormat = new WritableCellFormat(text);
            textFormat.setAlignment(Alignment.CENTRE);
            textFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTER ALIGNMENT WITHOUT BORDER

            WritableCellFormat textFormat_without = new WritableCellFormat(text);
            textFormat_without.setAlignment(Alignment.CENTRE);
            textFormat_without.setVerticalAlignment(VerticalAlignment.CENTRE);

            // CREATE A CELL FORMAT FOR TEXT WITH LEFT ALIGNMENT

            WritableCellFormat textFormat_LEFT = new WritableCellFormat(NumberFormats.TEXT);
            textFormat_LEFT.setAlignment(Alignment.LEFT);
            textFormat_LEFT.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat_LEFT.setBackground(Colour.YELLOW);
            textFormat_LEFT.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat textFormat_CENTER = new WritableCellFormat(text);
            textFormat_CENTER.setAlignment(Alignment.CENTRE);
            textFormat_CENTER.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat textFormat_TOP = new WritableCellFormat(text);
            textFormat_TOP.setAlignment(Alignment.LEFT);
            textFormat_TOP.setVerticalAlignment(VerticalAlignment.TOP);

            WritableCellFormat textFormat_RIGHT = new WritableCellFormat(text);
            textFormat_RIGHT.setAlignment(Alignment.RIGHT);
            textFormat_RIGHT.setVerticalAlignment(VerticalAlignment.CENTRE);
            // CREATE A CELL FORMAT FOR LINK

            WritableFont forLink = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD, true);
            WritableCellFormat linkFormat = new WritableCellFormat(forLink);
            linkFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            linkFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TABLE HEADING

            WritableFont tableHeading = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
            WritableCellFormat tableHeadingFormat = new WritableCellFormat(tableHeading);
            tableHeadingFormat.setAlignment(Alignment.LEFT);
            tableHeadingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            tableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//				tableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);

            WritableFont mandatoryFieldsHeading = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
            WritableCellFormat mandatoryFieldsTableHeadingFormat = new WritableCellFormat(mandatoryFieldsHeading);
            mandatoryFieldsTableHeadingFormat.setAlignment(Alignment.LEFT);
            mandatoryFieldsTableHeadingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            mandatoryFieldsTableHeadingFormat.setBackground(Colour.RED);
            mandatoryFieldsTableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat tableHeadingFormatRIGHT = new WritableCellFormat(tableHeading);
            tableHeadingFormatRIGHT.setAlignment(Alignment.RIGHT);
            tableHeadingFormatRIGHT.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat integerFormat = new WritableCellFormat(NumberFormats.INTEGER);
            integerFormat.setAlignment(Alignment.LEFT);
            integerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            integerFormat.setBackground(Colour.YELLOW);
            integerFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat defaultFormat = new WritableCellFormat(NumberFormats.DEFAULT);
            defaultFormat.setAlignment(Alignment.LEFT);
            defaultFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            defaultFormat.setBackground(Colour.YELLOW);
            defaultFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat floatFormat = new WritableCellFormat(NumberFormats.FLOAT);
            floatFormat.setAlignment(Alignment.LEFT);
            floatFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            floatFormat.setBackground(Colour.YELLOW);
            floatFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat floatFormatBOLD = new WritableCellFormat(tableHeading, NumberFormats.FLOAT);
            floatFormatBOLD.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
            floatFormatBOLD.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableSheet ws = null;

            if (comptype.equals("GRP"))
            {

                ws = workbook.createSheet("Group Template", 0);
                ws.setColumnView(0, 20);
                ws.setColumnView(1, 20);
                ws.setColumnView(2, 35);
                ws.setColumnView(3, 10);
                ws.setColumnView(4, 20);
                ws.setColumnView(5, 15);
                ws.setColumnView(6, 15);
                ws.setColumnView(7, 18);
                ws.setColumnView(8, 20);
                ws.setColumnView(9, 15);
                ws.setColumnView(10, 10);
                ws.setColumnView(11, 10);
                ws.setColumnView(12, 15);
                ws.setColumnView(13, 15);
                ws.setColumnView(14, 15);
                ws.setColumnView(15, 15);

                ws.getSettings().setPaperSize(PaperSize.A4);
                ws.getSettings().setOrientation(PageOrientation.LANDSCAPE);

                ws.getSettings().setHeaderMargin(.5);
                ws.getSettings().setFooterMargin(.5);
                ws.getSettings().setTopMargin(.5);
                ws.getSettings().setBottomMargin(.5);

                jxl.write.Label briefD = new jxl.write.Label(0, 0, "TABLE TEMPLATE", headingFormat);
                ws.mergeCells(0, 0, 1, 0);
                ws.addCell(briefD);

                jxl.write.Label label_1 = new jxl.write.Label(0, 2, "TABLE NUMBER", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_1);
                label_1 = new jxl.write.Label(1, 2, "<Table Number>", textFormat_LEFT);
                ws.addCell(label_1);

                jxl.write.Label label_2 = new jxl.write.Label(0, 3, "TABLE DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_2);
                label_2 = new jxl.write.Label(1, 3, "<Table Description>", textFormat_LEFT);
                ws.addCell(label_2);

//                jxl.write.Label label_3 = new jxl.write.Label(0, 4, "IMAGE SOURCE", mandatoryFieldsTableHeadingFormat);
//                ws.addCell(label_3);
//                label_3 = new jxl.write.Label(1, 4, "<IMAGE SOURCE>", textFormat_LEFT);
//                ws.addCell(label_3);
//
//                jxl.write.Label label_4 = new jxl.write.Label(0, 5, "GROUP REMARKS", mandatoryFieldsTableHeadingFormat);
//                ws.addCell(label_4);
//                label_4 = new jxl.write.Label(1, 5, "<GROUP REMARKS>", textFormat_LEFT);
//                ws.addCell(label_4);


                rowCounter = 4;
                rowCounter++;

                jxl.write.Label label_5 = new jxl.write.Label(colCounter, rowCounter, "SEQ. NO.", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_5);
                label_5 = new jxl.write.Label(colCounter, rowCounter + 1, "", textFormat_LEFT);
                ws.addCell(label_5);

                jxl.write.Label label_6 = new jxl.write.Label(colCounter + 1, rowCounter, "PART", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_6);
                label_6 = new jxl.write.Label(colCounter + 1, rowCounter + 1, "", textFormat_LEFT);
                ws.addCell(label_6);

                jxl.write.Label label_9 = new jxl.write.Label(colCounter + 2, rowCounter, "PART DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_9);
                label_9 = new jxl.write.Label(colCounter + 2, rowCounter + 1, "", textFormat_LEFT);
                ws.addCell(label_9);

                jxl.write.Label label_7 = new jxl.write.Label(colCounter + 3, rowCounter, "QTY", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_7);
                label_7 = new jxl.write.Label(colCounter + 3, rowCounter + 1, "", defaultFormat);
                ws.addCell(label_7);

                jxl.write.Label label_8 = new jxl.write.Label(colCounter + 4, rowCounter, "REMARKS", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_8);
                label_8 = new jxl.write.Label(colCounter + 4, rowCounter + 1, "", textFormat_LEFT);
                ws.addCell(label_8);

                jxl.write.Label label_10 = new jxl.write.Label(colCounter + 5, rowCounter, "INTERCHANGEABILITY", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_10);
                label_10 = new jxl.write.Label(colCounter + 5, rowCounter + 1, "", textFormat_LEFT);
                ws.addCell(label_10);

                jxl.write.Label label_11 = new jxl.write.Label(colCounter + 6, rowCounter, "CUT OFF CHASSIS", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_11);
                label_11 = new jxl.write.Label(colCounter + 6, rowCounter + 1, "", textFormat_LEFT);
                ws.addCell(label_11);

                jxl.write.Label label_12 = new jxl.write.Label(colCounter + 7, rowCounter, "CUT OFF (dd/mm/yyyy)", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_12);
                label_12 = new jxl.write.Label(colCounter + 7, rowCounter + 1, "", textFormat_LEFT);
                ws.addCell(label_12);

                //colCounter += 4;
                //st.close();

            }
            else if (comptype.equals("vehicle"))
            {
                ws = workbook.createSheet("Vehicle Template", 0);
                ws.setColumnView(0, 20);
                ws.setColumnView(1, 20);
                ws.setColumnView(2, 25);
                ws.setColumnView(3, 20);
                ws.setColumnView(4, 20);


                ws.getSettings().setPaperSize(PaperSize.A4);
                ws.getSettings().setOrientation(PageOrientation.LANDSCAPE);

                ws.getSettings().setHeaderMargin(.5);
                ws.getSettings().setFooterMargin(.5);
                ws.getSettings().setTopMargin(.5);
                ws.getSettings().setBottomMargin(.5);

                jxl.write.Label briefD = new jxl.write.Label(0, 0, "ADD VEHICLE TEMPLATE", headingFormat);
                ws.mergeCells(0, 0, 1, 0);
                ws.addCell(briefD);

                rowCounter = 2;
                colCounter = 0;

                jxl.write.Label label_1 = new jxl.write.Label(colCounter, rowCounter, "VEHICLE NUMBER", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_1);

                jxl.write.Label label_2 = new jxl.write.Label(colCounter + 1, rowCounter, "VARIANT NAME", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_2);

                jxl.write.Label label_3 = new jxl.write.Label(colCounter + 2, rowCounter, "MFG. DATE(DD/MM/YYYY)", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_3);

                jxl.write.Label label_4 = new jxl.write.Label(colCounter + 3, rowCounter, "DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_4);

                jxl.write.Label label_5 = new jxl.write.Label(colCounter + 4, rowCounter, "STATUS", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_5);

                rowCounter++;

                jxl.write.Label label_6 = new jxl.write.Label(colCounter, rowCounter, "<vehicle no>", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_6);

                jxl.write.Label label_7 = new jxl.write.Label(colCounter + 1, rowCounter, "<variant name>", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_7);

                jxl.write.Label label_8 = new jxl.write.Label(colCounter + 2, rowCounter, "<mfg. date>", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_8);

                jxl.write.Label label_9 = new jxl.write.Label(colCounter + 3, rowCounter, "<description>", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_9);

                jxl.write.Label label_10 = new jxl.write.Label(colCounter + 4, rowCounter, "<status>", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_10);

            }
            else if (comptype.equals("KIT"))
            {

                ws = workbook.createSheet("KIT Template", 0);
                ws.setColumnView(0, 15);
                ws.setColumnView(1, 25);
                ws.setColumnView(2, 25);
                ws.setColumnView(3, 25);
                ws.getSettings().setPaperSize(PaperSize.A4);
                ws.getSettings().setOrientation(PageOrientation.LANDSCAPE);
                ws.getSettings().setHeaderMargin(.5);
                ws.getSettings().setFooterMargin(.5);
                ws.getSettings().setTopMargin(.5);
                ws.getSettings().setBottomMargin(.5);

                jxl.write.Label briefD = new jxl.write.Label(0, 0, "KIT TEMPLATE", headingFormat);
                ws.mergeCells(0, 0, 1, 0);
                ws.addCell(briefD);

                jxl.write.Label label_1 = new jxl.write.Label(0, 2, "KIT NUMBER", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_1);
                label_1 = new jxl.write.Label(1, 2, "<Kit Number>", textFormat_LEFT);
                ws.addCell(label_1);

                jxl.write.Label label_2 = new jxl.write.Label(0, 3, "KIT DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_2);
                label_2 = new jxl.write.Label(1, 3, "<Kit Description>", textFormat_LEFT);
                ws.addCell(label_2);

                jxl.write.Label briefD5 = new jxl.write.Label(0, 4, "KIT REMARKS", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD5);
                label_2 = new jxl.write.Label(1, 4, "<Kit Remarks>", textFormat_LEFT);
                ws.addCell(label_2);

                jxl.write.Label briefD6 = new jxl.write.Label(0, 5, "CATEGORY TYPE", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD6);
                label_2 = new jxl.write.Label(1, 5, "<Category Type>", textFormat_LEFT);
                ws.addCell(label_2);


//                label_2 = new jxl.write.Label(0, 4, "MOQ", mandatoryFieldsTableHeadingFormat);
//                ws.addCell(label_2);
//                label_2 = new jxl.write.Label(1, 4, "<MOQ>", textFormat_LEFT);
//                ws.addCell(label_2);
//
//                label_2 = new jxl.write.Label(0, 5, "QML", mandatoryFieldsTableHeadingFormat);
//                ws.addCell(label_2);
//                label_2 = new jxl.write.Label(1, 5, "<QML>", textFormat_LEFT);
//                ws.addCell(label_2);
//
//                label_2 = new jxl.write.Label(0, 6, "NDP", mandatoryFieldsTableHeadingFormat);
//                ws.addCell(label_2);
//                label_2 = new jxl.write.Label(1, 6, "<NDP>", textFormat_LEFT);
//                ws.addCell(label_2);

                rowCounter += 7;

                jxl.write.Label label_6 = new jxl.write.Label(colCounter, rowCounter, "ITEM", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_6);
                label_6 = new jxl.write.Label(colCounter, rowCounter + 1, "", textFormat_LEFT);
                ws.addCell(label_6);

                jxl.write.Label label_9 = new jxl.write.Label(colCounter + 1, rowCounter, "QTY", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_9);
                label_9 = new jxl.write.Label(colCounter + 1, rowCounter + 1, "", defaultFormat);
                ws.addCell(label_9);

            }
            else if (comptype.equalsIgnoreCase("PRT"))
            {
                try
                {

                    String label = "Part";
                    ws = workbook.createSheet(label + " Excel", 0);

                    ws.setColumnView(0, 15);
                    ws.setColumnView(1, 25);
                    ws.setColumnView(2, 25);
                    ws.setColumnView(3, 25);
                    jxl.write.Label briefD1 = new jxl.write.Label(0, 0, label.toUpperCase() + " TEMPLATE", headingFormat);
                    ws.mergeCells(0, 0, 1, 0);
                    ws.addCell(briefD1);


                    // jxl.write.Label briefD2 = new jxl.write.Label(0, 1, " ",headingFormat_without);
                    //ws.mergeCells(0, 1, 6, 1);
                    // ws.addCell(briefD2);

                    jxl.write.Label briefD3 = new jxl.write.Label(0, 2, label.toUpperCase() + " NUMBER", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD3);
                    jxl.write.Label label_10 = new jxl.write.Label(0, 3, "<" + label + " Number>", textFormat_LEFT);
                    ws.addCell(label_10);

                    jxl.write.Label briefD4 = new jxl.write.Label(1, 2, label.toUpperCase() + " DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD4);
                    label_10 = new jxl.write.Label(1, 3, "<" + label + " Description>", textFormat_LEFT);
                    ws.addCell(label_10);

                    jxl.write.Label briefD5 = new jxl.write.Label(2, 2, label.toUpperCase() + " REMARKS", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD5);
                    label_10 = new jxl.write.Label(2, 3, "<" + label + " Remarks>", textFormat_LEFT);
                    ws.addCell(label_10);

                    jxl.write.Label briefD6 = new jxl.write.Label(3, 2, "SERVICEABLE", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD6);
                    label_10 = new jxl.write.Label(3, 3, "<Serviceable>", textFormat_LEFT);
                    ws.addCell(label_10);
                    colCounter = 2;
                    rowCounter = 2;
//                    Statement stmt = conn.createStatement();



                }
                catch (Exception ex)
                {
                    ////System.out.println("i m in sql exception ///");

                    ex.printStackTrace();
                    return false;
                }
            }
            else if (comptype.equalsIgnoreCase("SAP"))
            {
                try
                {

                    String label = "Part";
                    ws = workbook.createSheet(label + " Excel", 0);

                    ws.setColumnView(0, 15);
                    ws.setColumnView(1, 25);
                    ws.setColumnView(2, 25);
                    ws.setColumnView(3, 25);
//                    jxl.write.Label briefD1 = new jxl.write.Label(0, 0, label.toUpperCase() + " TEMPLATE", headingFormat);
//                    ws.mergeCells(0, 0, 1, 0);
//                    ws.addCell(briefD1);
                    // jxl.write.Label briefD2 = new jxl.write.Label(0, 1, " ",headingFormat_without);
                    //ws.mergeCells(0, 1, 6, 1);
                    // ws.addCell(briefD2);
                    jxl.write.Label briefD1 = new jxl.write.Label(0, 0, label.toUpperCase() + " CODE", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD1);
//                    jxl.write.Label label_10 = new jxl.write.Label(0, 1, "<" + label + " Code>", textFormat_LEFT);
//                    ws.addCell(label_10);
                    jxl.write.Label briefD2 = new jxl.write.Label(1, 0, label.toUpperCase() + " DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD2);
//                    label_10 = new jxl.write.Label(1, 3, "<" + label + " Description>", textFormat_LEFT);
//                    ws.addCell(label_10);
                    jxl.write.Label briefD3 = new jxl.write.Label(2, 0, "GRP", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD3);
//                    label_10 = new jxl.write.Label(2, 3, "<" + label + " GRP>", textFormat_LEFT);
//                    ws.addCell(label_10);
                    jxl.write.Label briefD4 = new jxl.write.Label(3, 0, "STD PACKING", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD4);
//                    label_10 = new jxl.write.Label(3, 3, "<STD Packing>", textFormat_LEFT);
//                    ws.addCell(label_10);
                    jxl.write.Label briefD5 = new jxl.write.Label(4, 0, "MOQ Stockiest/Dealers", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD5);
//                    label_10 = new jxl.write.Label(4, 3, "<MOQ Stockiest/Dealers>", textFormat_LEFT);
//                    ws.addCell(label_10);
                    jxl.write.Label briefD6 = new jxl.write.Label(5, 0, "SERVICEABLE", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD6);
//                    label_10 = new jxl.write.Label(3, 3, "<Serviceable>", textFormat_LEFT);
//                    ws.addCell(label_10);
                    colCounter = 1;
                    rowCounter = 0;
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    return false;
                }
            }
            else if (comptype.equalsIgnoreCase("TSN"))
            {
                try
                {

                    String label = "TSN";
                    ws = workbook.createSheet(label + " Excel", 0);

                    ws.setColumnView(0, 15);
                    ws.setColumnView(1, 25);
                    ws.setColumnView(2, 25);
                    ws.setColumnView(3, 25);
                    jxl.write.Label briefD1 = new jxl.write.Label(0, 0, label.toUpperCase() + " TEMPLATE", headingFormat);
                    ws.mergeCells(0, 0, 1, 0);
                    ws.addCell(briefD1);
                    jxl.write.Label briefD3 = new jxl.write.Label(0, 2, label.toUpperCase(), mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD3);
                    jxl.write.Label label_10 = new jxl.write.Label(0, 3, "<" + label + ">", textFormat_LEFT);
                    ws.addCell(label_10);

                    jxl.write.Label briefD4 = new jxl.write.Label(1, 2, "FCODE", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD4);
                    label_10 = new jxl.write.Label(1, 3, "<FCode>", textFormat_LEFT);
                    ws.addCell(label_10);

                    jxl.write.Label briefD5 = new jxl.write.Label(2, 2, "MFG.DATE", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD5);
                    label_10 = new jxl.write.Label(2, 3, "<Mfg.Date(dd/MM/yyyy)>", textFormat_LEFT);
                    ws.addCell(label_10);
                    colCounter = 2;
                    rowCounter = 2;

                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    return false;
                }

            }
            else if (comptype.equalsIgnoreCase("TOOL") || comptype.equalsIgnoreCase("LUBE"))
            {
                ws = workbook.createSheet("Component Template", 0);
                ws.setColumnView(0, 15);
                ws.setColumnView(1, 25);
                ws.setColumnView(2, 25);
                ws.setColumnView(3, 25);
                ws.getSettings().setPaperSize(PaperSize.A4);
                ws.getSettings().setOrientation(PageOrientation.LANDSCAPE);
                ws.getSettings().setHeaderMargin(.5);
                ws.getSettings().setFooterMargin(.5);
                ws.getSettings().setTopMargin(.5);
                ws.getSettings().setBottomMargin(.5);

                jxl.write.Label briefD = new jxl.write.Label(0, 0, "TEMPLATE", headingFormat);
                ws.mergeCells(0, 0, 4, 0);
                ws.addCell(briefD);

                jxl.write.Label label_1 = new jxl.write.Label(0, 2, "COMPONENT NUMBER", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_1);
                label_1 = new jxl.write.Label(0, 3, "<Component Number>", textFormat_LEFT);
                ws.addCell(label_1);

                label_1 = new jxl.write.Label(1, 2, "COMPONENT TYPE", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_1);
                label_1 = new jxl.write.Label(1, 3, "<Component Type>", textFormat_LEFT);
                ws.addCell(label_1);

                jxl.write.Label label_2 = new jxl.write.Label(2, 2, "COMPONENT DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_2);
                label_2 = new jxl.write.Label(2, 3, "<Component Description>", textFormat_LEFT);
                ws.addCell(label_2);

                jxl.write.Label briefD5 = new jxl.write.Label(3, 2, "COMPONENT REMARKS", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD5);
                label_2 = new jxl.write.Label(3, 3, "<Component Remarks>", textFormat_LEFT);
                ws.addCell(label_2);


                jxl.write.Label briefD6 = new jxl.write.Label(4, 2, "CATEGORY TYPE", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD6);
                label_2 = new jxl.write.Label(4, 3, "<Category Type>", textFormat_LEFT);
                ws.addCell(label_2);

                rowCounter = 2;


            }
            if (comptype.equalsIgnoreCase("SAP"))
            {
                rowCounter += 1;
                jxl.write.Label label_11 = new jxl.write.Label(0, rowCounter, "end", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_11);
            }
            else
            {

                rowCounter += 2;
                jxl.write.Label label_11 = new jxl.write.Label(0, rowCounter, "end", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_11);

                rowCounter += 2;
                label_11 = new jxl.write.Label(0, rowCounter, "", textFormat_LEFT);
                ws.addCell(label_11);
                jxl.write.Label label_12 = new jxl.write.Label(1, rowCounter, ":To Be Filled By User", tableHeadingFormat);
                ws.addCell(label_12);
                rowCounter++;

                jxl.write.Label label_13 = new jxl.write.Label(0, rowCounter, "", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_13);

                jxl.write.Label label_14 = new jxl.write.Label(1, rowCounter, ":Mandatory Fields", tableHeadingFormat);
                ws.addCell(label_14);
            }

            workbook.write();
            workbook.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    //32. to get Component Number String.
    public String getCompNoString(String type, Connection conn) throws Exception
    {
        ResultSet rs;
       // Statement stmt = null;
        PreparedStatement stmt  = null;
        String comp_str = "";
        String query = "Select COMP_NO from COMP_DETAIL(NOLOCK) where COMP_TYPE='" + type + "' order by COMP_NO";
        ////System.out.println("query:" + query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();

        ////System.out.println("");
        while (rs.next())
        {

            comp_str += rs.getString(1) + "@@";
        }
        rs.close();
        return comp_str;
    }

    //33. to check whether custom present or not in the database.
    public boolean isCustomPresent(String groupno, Connection conn) throws Exception
    {
        boolean isPresent = false;
        //checking whether custom present or not in the database.
        String query = "select * from GROUP_KIT_BOM_CUSTOM(NOLOCK) where GRP_KIT_NO='" + groupno + "'";
        ResultSet rs = null;
        PreparedStatement statement = conn.prepareStatement(query);
        rs = statement.executeQuery();
        isPresent = rs.next();
        rs.close();
        return isPresent;
    }

    //34. to get Level Description of Model from the database.
    public Vector getLevelValues(int level_id, Connection conn) throws Exception
    {
        ResultSet rs;
        PreparedStatement stmt = null;
        //stmt = conn.createStatement();
        String query = "";
        Vector levelValVec = new Vector();
        //getting Level Description of Model from the database.
        query = "Select distinct(LEVEL_VALUE) from CAT_MODEL_CLASSIFICATION(NOLOCK) where LEVEL=" + level_id;
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();

        while (rs.next())
        {
            levelValVec.add(rs.getString(1));
        }
        //////System.out.println("levelValVec:" + levelValVec);
        rs.close();
        stmt.close();
        return levelValVec;
    }

    //35. to get Type of the Group from the database.
    public ArrayList getGrpType(String model_no, Connection conn) throws Exception
    {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //stmt = conn.createStatement();
        ArrayList modelGrpList = new ArrayList();
        HashMap grpTypeMap = new HashMap();
         HashMap grpMap = new HashMap();
        //getting Type of the Group from the database.
       // stmt = conn.createStatement();
        String grp="";
        //rs = stmt.executeQuery("Select GROUP_NO,GROUP_TYPE,MAP_NAME from CAT_MODEL_GROUP where MODEL_NO='" + model_no + "'");
        String query = ("Select GROUP_NO,GROUP_TYPE,MAP_NAME from CAT_MODEL_GROUP(NOLOCK) where MODEL_NO='" + model_no + "'");
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next())
        {
            grp=rs.getString(1);
            grpTypeMap.put(grp,rs.getString(2)) ;
            grpMap.put(grp,rs.getString(3));
        }

        modelGrpList.add(grpTypeMap);
        modelGrpList.add(grpMap);
        //////System.out.println("grpType:" + grpType);
        rs.close();
        stmt.close();
        return modelGrpList;
    }

    //36. to get all Types of the Group from the database.
    public Vector getAllGrpTypes(Connection conn) throws Exception
    {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //stmt = conn.createStatement();
        String query = "";
        Vector grpTypeVec = new Vector();
        //getting all Type of the Group Types from MODEL_GROUP.
        query = "Select distinct(GROUP_TYPE) from CAT_MODEL_GROUP(NOLOCK)";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();

        while (rs.next())
        {
            grpTypeVec.add(rs.getString(1));
        }
        //////System.out.println("grpTypeVec:" + grpTypeVec);
        rs.close();
        stmt.close();
        return grpTypeVec;
    }

    public Vector getCompleteGrps(Connection conn) throws Exception
    {
        ResultSet rs;
        PreparedStatement stmt = null;
        //stmt = conn.createStatement();
        String query = "";
        Vector completeGrpVec = new Vector();
        //getting all complete Groups from the database.
        query = "Select GRP_KIT_NO from CAT_GROUP_KIT_DETAIL(NOLOCK) order by  GRP_KIT_NO";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();

        while (rs.next())
        {
            String grp_Kit_no = rs.getString("GRP_KIT_NO");
            completeGrpVec.add(grp_Kit_no);

        }
        //////System.out.println("completeGrpVec:" + completeGrpVec);
        rs.close();
        stmt.close();
        return completeGrpVec;
    }

    //38. to get Group Bom Values for List type from the database.
    public Vector getGrpBomParamListValues(int param_id, Connection conn) throws Exception
    {
        Vector valueVec = new Vector();
        //Statement st = conn.createStatement();
        PreparedStatement st = null;
        ResultSet rs = null;
        //getting Group Bom Values for List type from the database.
        //////System.out.println("select PARAM_LIST_VALUE from GRP_BOM_PARAM_LIST_VALUES where PARAM_ID=" + param_id);
        rs = st.executeQuery("select PARAM_LIST_VALUE from GRP_BOM_PARAM_LIST_VALUES(NOLOCK) where PARAM_ID=" + param_id + "");
        while (rs.next())
        {
            valueVec.add(rs.getString(1));
        }
        rs.close();
        st.close();
        // ////System.out.println("valueVec:" + valueVec);
        return valueVec;
    }

    //39. to get Group Bom Parameter Id for List type from the database.
    public int getGrpBomParamIdForList(String param, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        int paramId = 0;
        //getting Group Bom Parameter Id for List type from the database.
        //////System.out.println("select PARAM_ID from GRP_BOM_PARAM_MASTER where PARAM_DESC='" + param + "' and PARAM_VALUE_TYPE='LIST'");
        rs = st.executeQuery("select PARAM_ID from GRP_BOM_PARAM_MASTER(NOLOCK) where PARAM_DESC='" + param + "' and PARAM_VALUE_TYPE='LIST'");
        if (rs.next())
        {
            paramId = rs.getInt(1);
        }
        rs.close();
        st.close();
        ////System.out.println("paramId:" + paramId);
        return paramId;
    }
    //39. to get Group Bom Parameter Id for List type from the database.

    public int getGrpBomParamIdForMultiList(String param, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        int paramId = 0;
        //getting Group Bom Parameter Id for List type from the database.
        //////System.out.println("select PARAM_ID from GRP_BOM_PARAM_MASTER where PARAM_DESC='" + param + "' and PARAM_VALUE_TYPE='LIST'");
        rs = st.executeQuery("select PARAM_ID from GRP_BOM_PARAM_MASTER(NOLOCK) where PARAM_DESC='" + param + "' and PARAM_VALUE_TYPE='MULTI-LIST'");
        if (rs.next())
        {
            paramId = rs.getInt(1);
        }
        rs.close();
        st.close();
        ////System.out.println("paramId:" + paramId);
        return paramId;
    }

    public String isCompPresent(String comp_no, Connection conn) throws Exception
    {
        ResultSet rs;
        PreparedStatement stmt = null;
        String result = "notpresent";
        //////System.out.println("i am in isCompPresent");
        //checking whether the Component exists as Model or not.
        String query = "Select MODEL_NO from CAT_MODELS(NOLOCK) where MODEL_NO='" + comp_no + "'";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        if (rs.next())
        {
            result = "'" + comp_no + "' exists as " + PageTemplate.MODEL_NO + " in Database.";
            rs.close();
            stmt.close();
            return result;
        }
        rs.close();
        //checking whether the Component exists as Group or not.
        query = "Select GRP_KIT_NO from CAT_GROUP_KIT_DETAIL(NOLOCK) where GRP_KIT_NO='" + comp_no + "'";
        //////System.out.println(query);
        rs = stmt.executeQuery(query);
        if (rs.next())
        {

            result = "'" + comp_no + "' exists as " + PageTemplate.GROUP + " in Database.";
            ////System.out.println("result:" + result);
            rs.close();
            stmt.close();
            return result;
        }
        rs.close();

        //////System.out.println("i am in isCompPresent");
        query = "Select part_no,part_type from cat_part(NOLOCK) where part_no='" + comp_no + "'";
        rs = stmt.executeQuery(query);
        if (rs.next())
        {
            String comp_type = rs.getString("part_no");
            String part_type = rs.getString("part_type").toUpperCase();

            if (part_type.equals("PRT"))
            {
                part_type = "Part";
            }
            else if (part_type.equals("TOOL"))
            {
                part_type = "Tool";
            }
            else if (part_type.equals("LUBE"))
            {
                part_type = "Lube";
            }
            if (part_type.equals("KIT"))
            {
                part_type = "Kit";
            }

            //checking whether the Component exists as Part or not.
            if (comp_type.equalsIgnoreCase(comp_no))
            {
                result = "'" + comp_no + "' exists as " + part_type + " in Database.";
                rs.close();
                stmt.close();
                return result;
            }
        }
        rs.close();
        stmt.close();
        return result;
    }

    public boolean isModelPresent(String model_no, Connection conn) throws Exception
    {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        boolean result = false;
        //////System.out.println("i am in isCompPresent");
        //checking whether the Component exists as Model or not.
        String query = "Select MODEL_NO from CAT_MODELS(NOLOCK) where MODEL_NO='" + model_no + "'";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();  
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        if (rs.next())
        {
            result = true;
        }
        rs.close();

        stmt.close();
        return result;
    }

    //41. to get Level Values of Model Description from the database.
    public String getModelLevelValue(String model_no, int level_id, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
    	ResultSet rs = null;
        String param_val = "";
        //getting Level Values of Model Description from the database.
        rs = st.executeQuery("Select LEVEL_VALUE from CAT_MODEL_CLASSIFICATION(NOLOCK) where MODEL_NO='" + model_no + "' and LEVEL=" + level_id);
        if (rs.next())
        {
            param_val = rs.getString(1);
        }
        rs.close();
        st.close();
        //////System.out.println("param_val:" + param_val);
        return param_val;
    }

    //42. to create dynamic javascript function body for type checking.
    public String getFunctionBody(Connection conn) throws Exception
    {

        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
    	ResultSet rs = null;
        String param_val = "";
        //getting dynamic javascript function body.
        rs = st.executeQuery("Select PARAMETER_TYPE from PARAMETER_TYPES(NOLOCK) ");
        String conditions = "";
        while (rs.next())
        {
            param_val = rs.getString(1);
            conditions += "if(param=='" + param_val + "'){ return check" + param_val + "(val_obj);}";
        }
        rs.close();
        st.close();
        ////System.out.println("conditions:" + conditions);
        return conditions;
    }

    //43. to get Type of Parameter of Group Bom Parameter from the database.
    public String getDomainTypeFrmBomParamMaster(String param, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
    	ResultSet rs = null;
        String domain_type = "";
        rs = st.executeQuery("select PARAM_DOMAIN_TYPE from GRP_BOM_PARAM_MASTER(NOLOCK) where PARAM_DESC='" + param + "' order by PARAM_DESC");
        if (rs.next())
        {
            domain_type = rs.getString(1);
        }
        rs.close();
        st.close();
        return domain_type;
    }

    //44. to get Type of Parameter of Group Bom Parameter from the database.
    public Vector getDomainTypes(Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
    	ResultSet rs = null;
        Vector domainVec = new Vector();
        rs = st.executeQuery("Select PARAMETER_TYPE from PARAMETER_TYPES(NOLOCK)");
        while (rs.next())
        {
            domainVec.add(rs.getString(1));
        }
        rs.close();
        st.close();
        return domainVec;
    }

    //45. to get Type of Component Parameter from the database.
    public String getCompParamDomain(String comp_type, String param_desc, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
        PreparedStatement st = null;
    	ResultSet rs = null;
        String domain_type = "";

        rs = st.executeQuery("select PARAM_DOMAIN_TYPE from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='" + comp_type + "' and PARAM_DESC='" + param_desc + "'");
        if (rs.next())
        {
            domain_type = rs.getString(1);
        }
        rs.close();
        st.close();
        return domain_type;
    }

    //46. to validate component values for their types from the database.
    public String validateCompParameter(String comp_no, String comp_type, String param_desc, String value, Connection conn) throws Exception
    {
        String domain_type = getCompParamDomain(comp_type, param_desc, conn);
        value = value.trim();

        String comp_type_full = "";
        if (comp_type.equals("PRT"))
        {
            comp_type_full = "Part";
        }
        else if (comp_type.equals("ASM"))
        {
            comp_type_full = "Assembly";
        }
        else if (comp_type.equals("TOOL"))
        {
            comp_type_full = "Tool";
        }
        else if (comp_type.equals("KIT"))
        {
            comp_type_full = "Kit";
        }
        else if (comp_type.equals("GRP"))
        {
            comp_type_full = "Group";
        }

        String result = validateDomainType(value, domain_type);
        if (!result.equals("success"))
        {
            return "The value of " + comp_type_full + " '" + comp_no + "' for Parameter '" + param_desc + "' " + result;
        }

        return result;
    }

    //47. to validate Group Bom Parameter values for their types from the database.
    public String validateGrpBomParameter(String comp_no, String comp_type, String param_desc, String value, Connection conn) throws Exception
    {
        String domain_type = getDomainTypeFrmBomParamMaster(param_desc, conn);
        value = value.trim();

        String comp_type_full = "";
        if (comp_type.equals("PRT"))
        {
            comp_type_full = "Part";
        }
        else if (comp_type.equals("ASM"))
        {
            comp_type_full = "Assembly";
        }

        String result = validateDomainType(value, domain_type);
        if (!result.equals("success"))
        {
            return "The value of " + comp_type_full + " '" + comp_no + "' for Group BOM Parameter '" + param_desc + "' " + result;
        }
        return result;
    }

    public String validateDomainType(String value, String domain_type)
    {
        //validation for an Integer.
        if (domain_type.equals("Integer"))
        {
            try
            {
                int val = Integer.parseInt(value);
                if (value.indexOf("-") != -1)
                {
                    return "should only be Positive Integer";
                }
                if (value.indexOf("+") != -1)
                {
                    return "should not contain any Special Character";
                }
            }
            catch (Exception e)
            {

                return "should only be Integer";
            }

        } //validation for a Double.
        else if (domain_type.equals("Double"))
        {
            try
            {
                double val1 = Double.parseDouble(value);
                if (value.indexOf("-") != -1)
                {
                    return "should only be Positive Real";
                }
                if (value.indexOf("+") != -1)
                {
                    return "should not contain any Special Character";
                }
            }
            catch (Exception e)
            {
                return "should only be Real";
            }
            if (value.indexOf(".") == -1)
            {
                return "should only be Real";
            }
        } //validation for a Text.
        else if (domain_type.equals("Text"))
        {
            String iChars = "=*|+!^,\\:<>[]{}`';()@&$#%~?><//" + "\"";

            for (int i = 0; i < value.length(); i++)
            {
                if (iChars.indexOf(value.charAt(i)) != -1)
                {
                    return "should not contain any special characters. Allowed Characters are Underscore(_), Hyphen(-), Dot(.)";
                }
            }
        } //validation for a Boolean.
        else if (domain_type.equals("Boolean"))
        {
            if (!(value.equals("YES") || value.equals("NO")))
            {
                return "should only be either 'YES' or 'NO'";
            }

        }
        return "success";
    }

    //49. to check all special symbols.
    public String getValueTypeFrmBomParamMaster(String param, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        String value_type = "";
        rs = st.executeQuery("select PARAM_VALUE_TYPE from GRP_BOM_PARAM_MASTER(NOLOCK) where PARAM_DESC='" + param + "' order by PARAM_DESC");
        if (rs.next())
        {
            value_type = rs.getString(1);
        }
        rs.close();
        st.close();
        return value_type;
    }

    public boolean isVariantPresent(Connection conn, String variant)
    {
        //Statement st = null;
    	PreparedStatement st = null;
        ResultSet rs = null;
        boolean isPresent = false;
        try
        {
            //st = conn.createStatement();
            rs = null;

            rs = st.executeQuery("select model_no from models(NOLOCK) where model_no='" + variant + "'");
            if (rs.next())
            {
                isPresent = true;
            }
            else
            {
                isPresent = false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (st != null)
                {
                    st.close();
                }
            }
            catch (Exception e)
            {
            }
        }
        return isPresent;
    }

    public ArrayList<String> AutoComplete(Connection con, String sql)
    {
        ArrayList<String> output = new ArrayList<String>();
        StringBuilder item = new StringBuilder();

        //Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try
        {
            //stmt = con.createStatement();
            int count = 0;
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                if (count > 0)
                {
                    item.append(",");
                }
                item.append("'" + rs.getString(1) + "'");
                count += 1;
            }
            output.add(item.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (stmt != null)
                {
                    stmt.close();
                }
                item.setLength(0);
                item = null;
            }
            catch (Exception ignored)
            {
                ignored.printStackTrace();
            }
            sql = null;
            stmt = null;
            rs = null;
        }
        return output;
    }

    public boolean validateDate(String date, String format)
    {
        SimpleDateFormat df = null;
        java.util.Date todays = null;
        java.util.Date dateR = null;//"dd/MM/yyyy"
        try
        {
            df = new SimpleDateFormat(format);
            todays = new java.util.Date();
            dateR = df.parse(date);
            if (dateR.compareTo(todays) > 0)
            {
                return false;
            }
            else
            {
                return true;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public String getConvertDate(String dateString)
    {
        java.util.Date dtDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
        SimpleDateFormat sdfAct = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            dtDate = sdfAct.parse(dateString);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return (sdf.format(dtDate)).toString();
    }

    public java.sql.Date getConvertSQLDate(String dateString)
    {
        java.util.Date dtDate = null;

        SimpleDateFormat sdfAct = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            dtDate = sdfAct.parse(dateString);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new java.sql.Date(dtDate.getTime());
    }

    public void writeSVGId(File src, File dest)
    {
        //File f = new File("C:\\svgimages");//
        writeSVG(src, dest);
    }

    public static void writeSVG(File src, File dest)
    {
        String name = null;
        //String parent = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedReader br = null;
        //FileOutputStream writeNamesfos = null;
        String tmpString = null;

        int fisIndex = 0;
        int tempIndex = 0;


        try
        {
            name = src.getName();
            //parent = temp.getParent();
            //new ccode
            fis = new FileInputStream(src);

            byte[] buf = new byte[4096];
            //byte[] buf = new byte[99999999];
            UniversalDetector detector = new UniversalDetector(null);
            int nread;
            while ((nread = fis.read(buf)) > 0 && !detector.isDone())
            {
                detector.handleData(buf, 0, nread);
            }     // (3)
            detector.dataEnd();      // (4)
            String encoding = detector.getDetectedCharset();

            if (!dest.exists())
            {
                dest.mkdirs();
            }

            if (encoding == null)
            {
                encoding = "UTF-8";
                //writeNamesfos = new FileOutputStream(parent + "\\new\\" + "FileNames.txt", true);
                //writeNamesfos.write((temp.getName() + "\n").getBytes());
                //break;
            }
            // else
            {
               // System.out.println("Detected encoding = " + encoding);
               // System.out.println("temp.getName() = " + src.getName());
                //Detect Encoding
                fis = new FileInputStream(src);
                InputStreamReader isr = new InputStreamReader(fis, Charset.forName(encoding));
                br = new BufferedReader(isr);

                //fos = new FileOutputStream(parent + "\\new\\" + name, false);
                fos = new FileOutputStream(dest, false);
                OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName(encoding));//"UTF-8"
                BufferedWriter bw = new BufferedWriter(osw);
                String result = null;
                //new codel

                while ((result = br.readLine()) != null)
                {
                   // System.out.println("result.." + result);



                    if (result.indexOf("<a") != -1)
                    {
                        result = result.substring(0, result.indexOf("<a")) + result.substring(result.indexOf(">", result.indexOf("<a")) + 1);
                    }

                    if (result.indexOf("</a>") != -1)
                    {
                        result = result.substring(0, result.indexOf("</a>")) + result.substring(result.indexOf("</a>") + 4);
                    }


                    String tempStr = "";
                    int startIndex = 0;
                    int tempStartIndex = 0;
                    int xInxex = 0;

                    if (result.indexOf("<text") != -1)
                    {
                        if (result.indexOf("id=") != -1)
                        {
                            startIndex = result.indexOf("id=");
                            tempStartIndex = result.indexOf("\"", startIndex + 4);
                            result = result.substring(0, startIndex) + " " + result.substring(tempStartIndex + 1);
                        }


                        fisIndex = result.indexOf("<text");
                        tempIndex = result.indexOf("</text>");

                        startIndex = fisIndex;

                        if (result.indexOf("<tspan", startIndex) != -1)
                        {
                            while (result.indexOf("<tspan", startIndex) != -1)
                            {
                                xInxex = result.indexOf("<tspan", startIndex);
                                tempStartIndex = result.indexOf("</tspan>", startIndex);
                               // System.out.println("xInxex" + result.substring(xInxex));
                                //System.out.println(startIndex);
                                tempStr += result.substring(result.lastIndexOf(">", tempStartIndex) + 1, tempStartIndex);
                                tempStr = tempStr.trim();
                                startIndex = tempStartIndex + 7;
                            }
                           // System.out.println("tempStr" + tempStr);

                            if (!tempStr.trim().equals(""))
                            {
                                result = result.substring(0, fisIndex) + result.substring(fisIndex, fisIndex + 5) + " id=\"" + tempStr + "\" " + result.substring(fisIndex + 5);
                            }
                        }
                        else
                        {
                            tempStr = result.substring(result.lastIndexOf(">", tempIndex) + 1, tempIndex);
                            if (!tempStr.trim().equals(""))
                            {
                                result = result.substring(0, fisIndex) + result.substring(fisIndex, fisIndex + 5) + " id=\"" + tempStr + "\" " + result.substring(fisIndex + 5);
                            }
                        }
                        if (result.indexOf("<a") == -1)
                        {
                            result = "<a xlink:href=\"#\">" + result + "</a>";
                        }
                    }
                    //result=result.replaceAll(" ","");
                    bw.write(result);
                    bw.newLine();
                }
                bw.flush();
                bw.close();
                fis.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }//ujkuiu

    //1. to check whether Group exists or not in the database.
    public boolean checkCompExistsInDatabase(String comp_no, String comp_type, Connection conn) throws Exception
    {
        boolean result = false;
        //Statement st = conn.createStatement();
        PreparedStatement st = null;
        ResultSet rs = null;
        //checking whether Group exists or not in GROUP_KIT_DETAIL.
        if (comp_type == "GRP")
        {
            rs = st.executeQuery("select * from CAT_GROUP_KIT_DETAIL(NOLOCK) where GRP_KIT_NO='" + comp_no + "'");
            if (rs.next())
            {
                result = true;
            }
            rs.close();


        }
        st.close();
        return result;
    }

    //2. to write contents to a text file.
    public void writeToFile(File filename, String query, BufferedWriter bufferedWriter)
    {



        try
        {

            //Construct the BufferedWriter object
            //bufferedWriter = new BufferedWriter(new FileWriter(filename));

            //Start writing to the output stream
            //writing query to bufferedWriter.
            bufferedWriter.write(query);
            bufferedWriter.newLine();

        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            //Close the BufferedWriter
           /* try {
            if (bufferedWriter != null) {
            bufferedWriter.flush();
            bufferedWriter.close();
            }
            } catch (IOException ex) {
            ex.printStackTrace();
            }*/
        }
    }

    //3. to read data from file and update the database.
    public String readFileNUpdateinDB(File filename, Connection conn)
    {

        PreparedStatement ps = null;
        String result = "success";
        String query = "";
        try
        {
            //reading data from file and updating the database.
            conn.setAutoCommit(false);
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((query = br.readLine()) != null)
            { // while loop begins here
                //////System.out.println(query);
                ps = conn.prepareStatement(query);
                ps.execute();
            } // end while
            ps.close();
            conn.commit();

        }
        catch (FileNotFoundException ex)
        {
            result = "Txt file not found";
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            result = "IO Exception";
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            result = "SQL Exception";
            ex.printStackTrace();
        }
        return result;
    }

    public boolean checkNumeric1(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    //4. to execute Group Vector query.
    public String executeGrpVectorQueryInDB1(Vector groupInsertVec, Connection conn)
    {
        //////System.out.println("i am in executeGrpVectorQueryInDB");
        //declaration of variables used.
        PreparedStatement ps_insert = null;
        String select_query = "";
        String ins_query = "";
        String result = "success";
        try
        {
            conn.setAutoCommit(false);
            //Statement st = conn.createStatement();
            PreparedStatement st = null;
            //executing the query Vector.
            for (int i = 0; i < groupInsertVec.size(); i += 2)
            {
                select_query = "" + groupInsertVec.elementAt(i);
                ins_query = "" + groupInsertVec.elementAt(i + 1);
                //if Vector element is not 'NA'
                if (!select_query.equals("NA"))
                {
                    ResultSet rs = st.executeQuery(select_query);

                    if (!rs.next())
                    {
                        //////System.out.println("1 ins_query:" + ins_query);
                        //inserting into database.
                        ps_insert = conn.prepareStatement(ins_query);
                        ps_insert.executeUpdate();
                        ps_insert.close();
                    }
                    rs.close();
                } //if Vector element is 'NA'
                else
                {
                    //////System.out.println("2 ins_query:" + ins_query);
                    ps_insert = conn.prepareStatement(ins_query);
                    ps_insert.executeUpdate();
                    //ps_select.close();
                    ps_insert.close();
                }
            }
            st.close();
            conn.commit();
        }
        catch (SQLException ex)
        {
            try
            {
                conn.rollback();
                //conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            result = "SQL Exception";
            ex.printStackTrace();
        }
        return result;
    }

    //5. to get SQL Date.
    public java.sql.Date getSQLTodaysDate1()
    {
        java.util.Date sysDate = new java.util.Date();
        java.sql.Date todaysSQLDate = new java.sql.Date(sysDate.getTime());
        return todaysSQLDate;
    }

    //6. to get CD Number.
    public int getCD_No1(Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
    	ResultSet rs =null;
    	//getting the CD Number.
        //ResultSet rs = st.executeQuery("Select max(sno) from CAT_CD(NOLOCK)");
    	String query = ("Select max(sno) from CAT_CD(NOLOCK)");
    	st = conn.prepareStatement(query);
    	rs = st.executeQuery();
        int cd_no = 0;
        if (rs.next())
        {
            cd_no = rs.getInt(1);
        }
        rs.close();
        st.close();

        return cd_no;
    }

    //7. to get Patch Number.
    public int getPatch_No1(Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
    	ResultSet rs = null;
        int cd_no = 0;
        //rs = st.executeQuery("Select max(sno) from CAT_CD(NOLOCK)");
        String query = ("Select max(sno) from CAT_CD(NOLOCK)");
        st = conn.prepareStatement(query);
        rs = st.executeQuery();
        if (rs.next())
        {
            cd_no = rs.getInt(1);
        }
        rs.close();

        int patch_no = 0;
        //rs = st.executeQuery("Select  max(PATCH_NO) from CAT_PATCH(NOLOCK) where CD_NO=" + cd_no);
        String query1 = ("Select  max(PATCH_NO) from CAT_PATCH(NOLOCK) where CD_NO=" + cd_no);
        st = conn.prepareStatement(query1);
        rs = st.executeQuery();
        if (rs.next())
        {
            patch_no = rs.getInt(1);
        }
        rs.close();
        st.close();

        return patch_no;
    }

    //8. to get Group Parameter Count.
    public int getGRPParameterCount1(Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
    	ResultSet rs = null;
        int grp_param_count = 0;
        //rs = st.executeQuery("Select  count(*) from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='GRP'");
        String query = ("Select  count(*) from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='GRP'");
        st = conn.prepareStatement(query);
        rs = st.executeQuery();
        if (rs.next())
        {
            grp_param_count = rs.getInt(1);
        }
        rs.close();
        st.close();
        return grp_param_count;
    }

    //9. to check whether Component exists or not in the database.
    public String checkCompExistsinDB1(String comp_no, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
    	ResultSet rs = null;
        String result = "false";
        //if component exists as Assembly, Part, Tool or Kit.
        //rs = st.executeQuery("Select PART_NO from PART(NOLOCK) where PART_NO='" + comp_no + "'");
        String query = ("Select PART_NO from PART(NOLOCK) where PART_NO='" + comp_no + "'");
        st = conn.prepareStatement(query);
        rs = st.executeQuery();
        if (rs.next())
        {
            //result = rs.getString(1);
            result = "PRT";
        }
        rs.close();
        //if component exists as Group.
        //rs = st.executeQuery("Select * from GROUP_KIT_DETAIL(NOLOCK) where GRP_KIT_NO='" + comp_no + "'");
        String query1 = ("Select * from GROUP_KIT_DETAIL(NOLOCK) where GRP_KIT_NO='" + comp_no + "'");
        st = conn.prepareStatement(query1);
        rs = st.executeQuery();
        if (rs.next())
        {
            result = "GROUP";
        }
        rs.close();
        //if component exists as Model.
        //rs = st.executeQuery("Select * from MODELS(NOLOCK) where MODEL_NO='" + comp_no + "'");
        String query2 = ("Select * from MODELS(NOLOCK) where MODEL_NO='" + comp_no + "'");
        st = conn.prepareStatement(query2);
        rs = st.executeQuery();
       
        if (rs.next())
        {
            result = "MODEL";
        }
        rs.close();
        st.close();
        return result;
    }

    //10. to check whether Assembly exists or not in the database.
    public boolean checkAssyBomExistsinDB_5(String comp_no, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
    	ResultSet rs = null;
        boolean result = false;
        //rs = st.executeQuery("Select * from CAT_ASSY_BOM(NOLOCK) where ASSY_NO='" + comp_no + "' and REV_NO=0");
        String query = ("Select * from CAT_ASSY_BOM(NOLOCK) where ASSY_NO='" + comp_no + "' and REV_NO=0");
        st = conn.prepareStatement(query);
        rs = st.executeQuery();
        if (rs.next())
        {
            result = true;
        }
        rs.close();
        st.close();
        return result;
    }

    //11. to get count for union of Part and Assembly.
    public int getPrtUAsmParamCount_6(Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
    	ResultSet rs = null;
        int grp_param_count = 0;
        //rs = st.executeQuery("select distinct PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE in ('PRT','ASM') order by PARAM_DESC");
        String query = ("select distinct PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE in ('PRT','ASM') order by PARAM_DESC");
        st = conn.prepareStatement(query);
        rs = st.executeQuery();
        while (rs.next())
        {
            grp_param_count++;
        }
        rs.close();
        st.close();
        return grp_param_count;
    }

    //12. to get Part Parameters Vector.
    public Vector getPartParametersVec_6(Connection conn)
    {
        Vector partParamVec = new Vector();
        try
        {
            //Statement st = conn.createStatement();
        	PreparedStatement st = null;
        	ResultSet rs = null;
            //rs = st.executeQuery("select distinct PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE ='PRT' order by PARAM_DESC");
        	String query = ("select distinct PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE ='PRT' order by PARAM_DESC");
        	st = conn.prepareStatement(query);
        	rs = st.executeQuery();
            while (rs.next())
            {
                partParamVec.addElement(rs.getString(1));
            }
            rs.close();
            st.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return partParamVec;
    }

    //13. to get Assembly Parameters Vector.
    public Vector getAssyParametersVec_1(Connection conn)
    {
        Vector assyParamVec = new Vector();
        try
        {
            //Statement st = conn.createStatement();
        	PreparedStatement st = null;
        	ResultSet rs = null;

            //rs = st.executeQuery("select distinct PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE ='ASM' order by PARAM_DESC");
        	String query = ("select distinct PARAM_DESC from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE ='ASM' order by PARAM_DESC");
        	st = conn.prepareStatement(query);
        	rs = st.executeQuery();
            while (rs.next())
            {
                assyParamVec.addElement(rs.getString(1));
            }
            rs.close();
            st.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return assyParamVec;
    }

    //14. to get Group Bom Level Parameters Vector.
    public Vector getGrpBomLevelParametersVec_5(Connection conn)
    {
        Vector grpBomLevelParamVec = new Vector();
        try
        {
            //Statement st = conn.createStatement();
        	PreparedStatement st = null;
        	ResultSet rs = null;

            //rs = st.executeQuery("select distinct PARAM_DESC from GRP_BOM_PARAM_MASTER order by PARAM_DESC");
        	String query = ("select distinct PARAM_DESC from GRP_BOM_PARAM_MASTER(NOLOCK) order by PARAM_DESC");
        	st = conn.prepareStatement(query);
        	rs = st.executeQuery();
            while (rs.next())
            {
                grpBomLevelParamVec.addElement(rs.getString(1));
            }
            rs.close();
            st.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return grpBomLevelParamVec;
    }

    //15. to get Component Parameter Id.
    public int getCompParamId_6(String param, String comp_type, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
    	ResultSet rs = null;
        int paramId = 0;
        //rs = st.executeQuery("select PARAM_ID from COMP_PARAM_MASTER where PARAM_TYPE ='" + comp_type + "' and PARAM_DESC='" + param + "'");
        String query = ("select PARAM_ID from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE ='" + comp_type + "' and PARAM_DESC='" + param + "'");
        st = conn.prepareStatement(query);
        rs = st.executeQuery();
        if (rs.next())
        {
            paramId = rs.getInt(1);
        }
        rs.close();
        st.close();
        return paramId;
    }

    //16. to get Group Bom Level Parameter Id.
    public int getGrpBomParamId_6(String param, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        int paramId = 0;
        //rs = st.executeQuery("select PARAM_ID from GRP_BOM_PARAM_MASTER where PARAM_DESC='" + param + "'");
        String query = ("select PARAM_ID from GRP_BOM_PARAM_MASTER(NOLOCK) where PARAM_DESC='" + param + "'");
        st = conn.prepareStatement(query);
        rs = st.executeQuery();
        if (rs.next())
        {
            paramId = rs.getInt(1);
        }
        rs.close();
        st.close();
        return paramId;
    }

    //17. to get Component Description.
    public String getCompDesc_3(String comp_no, String comp_type, Connection conn) throws Exception
    {
        //////System.out.println("comp_no:" + comp_no);
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        String comp_desc = "";
        if (comp_type.equals("GRP"))
        {
            ////System.out.println("comp_no:" + comp_no);
            //rs = st.executeQuery("select GRP_KIT_DESC from GROUP_KIT_DETAIL where GRP_KIT_NO='" + comp_no + "'");
        	String query = ("select GRP_KIT_DESC from GROUP_KIT_DETAIL(NOLOCK) where GRP_KIT_NO='" + comp_no + "'");
        	st = conn.prepareStatement(query);
        	rs = st.executeQuery();
            if (rs.next())
            {
                comp_desc = rs.getString(1);
            }
            rs.close();
            //////System.out.println("comp_desc:" + comp_desc);
        }
        else
        {
            //rs = st.executeQuery("select COMP_DESC from COMP_DETAIL where COMP_NO='" + comp_no + "'");
            String query1 = ("select COMP_DESC from COMP_DETAIL(NOLOCK) where COMP_NO='" + comp_no + "'");
            st = conn.prepareStatement(query1);
            rs = st.executeQuery();
            if (rs.next())
            {
                comp_desc = rs.getString(1);
            }
            rs.close();
        }
        st.close();
        return comp_desc;
    }

    //18. to get Sub Components.
    public Vector get_subComp_5(String group, int PARENT_ID, String dotplace, Connection conn)
    {
        //declaration of variables used.
        ResultSet rs, rs1;
        PreparedStatement stmt,stmt1 = null;
        Vector subPartVec = new Vector();
        String comp_name = "";
        String seq = "";
        String COMP_TYPE = "";
        String var_DESCRIPTION = "";
        String var_SUPERCEEDENCE = "--";
        String var_DESCRIPTION1 = "";
        String param_val = "";
        boolean checkSubpart = false;
        try
        {

            //Statement stmt = conn.createStatement();
            //Statement stmt1 = conn.createStatement();
        	
        	
            //v4_ltk_get_groupInfo ob_get_rev_no = new v4_ltk_get_groupInfo(conn, ps, date_OR_serial, inputDate, serialNo, group);
            //int revNo = ob_get_rev_no.get_groupRevisionNo();
            int revNo = 0;
            //rs.close();
            String query = "";
            if (PARENT_ID == 0)
            {
                query = "SELECT COMPONENT,SEQUENCE,COMP_TYPE,AS_REQUIRED,QTY,INDEX_NO,ROW_ID FROM GROUP_KIT_BOM_CUSTOM(NOLOCK) WHERE GRP_KIT_NO ='" + group + "' AND PARENT_ID=0 AND REV_NO = " + revNo + " ORDER BY INDEX_NO ";
            }
            else
            {
                query = "SELECT COMPONENT,SEQUENCE,COMP_TYPE,AS_REQUIRED,QTY,INDEX_NO,ROW_ID FROM GROUP_KIT_BOM_CUSTOM(NOLOCK) WHERE GRP_KIT_NO ='" + group + "' AND PARENT_ID=" + PARENT_ID + " AND REV_NO = " + revNo + " ORDER BY INDEX_NO";

            }
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            //rs = stmt.executeQuery(query);
            if (rs.next())
            {
                do
                {
                    comp_name = rs.getString(1);
                    seq = rs.getString(2);
                    COMP_TYPE = rs.getString(3);
                    var_DESCRIPTION = getCompDesc(comp_name, COMP_TYPE, conn);
                    //var_SUPERCEEDENCE = get_superceedence(ps, comp_name, group);

                    String qry = "select CV.PARAM_VALUE from COMP_PARAM_VALUES(NOLOCK) CV ,COMP_PARAM_MASTER(NOLOCK) CM where CV.COMP_NO='" + comp_name + "' AND CV.COMP_TYPE='" + COMP_TYPE + "' AND CV.PARAM_ID=CM.PARAM_ID AND CV.COMP_TYPE=CM.PARAM_TYPE AND CM.PARAM_DESC='HASHPARAM'";
                    ////System.out.println(qry);
                    stmt1 = conn.prepareStatement(qry);
                    rs1 = stmt1.executeQuery();
                    //rs1 = stmt1.executeQuery(qry);
                    if (rs1.next())
                    {
                        param_val = rs1.getString(1);
                    }
                    else
                    {
                        param_val = "--";
                    }

                    rs1.close();
                    subPartVec.add(comp_name);
                    subPartVec.add(seq);
                    subPartVec.add(COMP_TYPE);

                    if (PARENT_ID != 0)
                    {

                        if (COMP_TYPE.equals("ASM"))
                        {
                            var_DESCRIPTION1 = dotplace + "<b>" + var_DESCRIPTION;

                        }
                        else
                        {
                            var_DESCRIPTION1 = dotplace + var_DESCRIPTION;
                        }
                        subPartVec.add(var_DESCRIPTION1);

                    }
                    else
                    {
                        if (COMP_TYPE.equals("ASM"))
                        {
                            var_DESCRIPTION1 = "<b>" + var_DESCRIPTION;

                        }
                        else
                        {
                            var_DESCRIPTION1 = var_DESCRIPTION;
                        }
                        subPartVec.add(var_DESCRIPTION1);

                    }

                    subPartVec.add(var_SUPERCEEDENCE);
                    subPartVec.add(rs.getString(4));
                    subPartVec.add(rs.getString(5));
                    subPartVec.add(rs.getString(6));
                    subPartVec.add(param_val);
                    int rowid = rs.getInt(7);
                    checkSubpart = check_desc(conn, rowid);
                    //////System.out.println("rowid1:" + rowid);
                    if (checkSubpart)
                    {
                        //////System.out.println("in checkSubpart");
                        String dotplace1 = "&nbsp;&nbsp;<b>.</b>&nbsp;&nbsp;";
                        dotplace1 = dotplace1 + dotplace;
                        //////System.out.println("group:" + group + "******rowid:" + rowid + "@@@@@@@@@dotplace1:" + dotplace1);
                        subPartVec.addAll(get_subComp(group, rowid, dotplace1, conn));
                    }



                }
                while (rs.next());

            }


            rs.close();
            stmt.close();
            stmt1.close();



        }
        catch (Exception ae)
        {
            ae.printStackTrace();

        }
        return subPartVec;

    }

    //19. to check the subpart of the component.
    public boolean check_desc_2(Connection conn, int rowid)
    {
        try
        {
            //////System.out.println("rowid2:" + rowid);
            //Statement stmt = conn.createStatement();
        	PreparedStatement stmt = null;
            boolean checkSubpart = false;
            ResultSet rs;
            //////System.out.println("select * from GROUP_KIT_BOM_CUSTOM WHERE PARENT_ID=" + rowid + "");
            //rs = stmt.executeQuery("select * from GROUP_KIT_BOM_CUSTOM WHERE PARENT_ID=" + rowid + "");
            String query = ("select * from GROUP_KIT_BOM_CUSTOM(NOLOCK) WHERE PARENT_ID=" + rowid + "");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next())
            {
                checkSubpart = true;
            }
            rs.close();
            stmt.close();
            //////System.out.println("checkSubpart:" + checkSubpart);
            return checkSubpart;
        }
        catch (Exception e)
        {
            ////System.out.println(e);
            e.printStackTrace();
            return false;
        }

    }

    //22. to get Assembly Bom from the database.
    public Vector getAssyBom_1(Vector GrpBomVec, String compno, Integer revno, Integer level, Connection conn) throws Exception
    {
        //declaration and initialization of variables used.
        int l = level.intValue();
        l++;
        level = new Integer(l);
        ResultSet rs;
        PreparedStatement stmt = null;
        String comp = "";
        String seq = "";
        String comptype = "";
        String desc = "";

        //getting the components of Assembly.
        String query1 = "Select COMPONENT, COMP_TYPE, QTY from CAT_ASSY_BOM(NOLOCK) where ASSY_NO='" + compno + "' and REV_NO=" + revno.intValue() + "";
        stmt = conn.prepareStatement(query1);
        rs = stmt.executeQuery();

        while (rs.next())
        {

            Vector tempVec = new Vector();
            comp = rs.getString(1);
            tempVec.add(comp);
            comptype = rs.getString(2);
            tempVec.add(comptype);
            tempVec.add(rs.getString(3));
            tempVec.add(seq);
            tempVec.add(revno);
            tempVec.add(level);
            desc = getCompDesc(comp, conn);
            tempVec.add(desc);

            //adding the components to GrpBomVec Vectors.
            for (int i = 0; i < GrpBomVec.size(); i++)
            {
                Vector GrpCompVec = (Vector) GrpBomVec.elementAt(i);
                GrpCompVec.add(tempVec.elementAt(i));
            }

            //////System.out.println("CompBom:" + comp);
            //////System.out.println("CompTypeBom:" + comptype);

            //if the component is an ASSEMBLY then getting its Bom recursively.
            if (comptype.equals("ASM"))
            {
                GrpBomVec = getAssyBom(GrpBomVec, comp, revno, level, conn);
            }

        }

        return GrpBomVec;
    }

    //23. to get Component Description from the database.
    public String getCompDesc_5(String compno, Connection conn) throws Exception
    {
        ResultSet rs;
        PreparedStatement stmt = null;
        //getting the Component Description.
        String query = "Select COMP_DESC from COMP_DETAIL(NOLOCK) where COMP_NO='" + compno + "'";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        String desc = "";
        if (rs.next())
        {
            desc = rs.getString(1);
        }
        return desc;
    }

    //24. to refresh Group Bom Vectors.
    public Vector refreshGrpBomCustom_1(Vector GrpBomVec, String[] arrangedcompindex) throws Exception
    {

        //declaration of variables used.
        String comp = "";
        String comptype = "";
        int level1 = 0;
        //getting the vectors of GrpBomVec Vector.
        Vector CompVec = (Vector) GrpBomVec.elementAt(0);
        Vector CompTypeVec = (Vector) GrpBomVec.elementAt(1);
        Vector QtyVec = (Vector) GrpBomVec.elementAt(2);
        Vector SeqNoVec = (Vector) GrpBomVec.elementAt(3);
        Vector RevNoVec = (Vector) GrpBomVec.elementAt(4);
        Vector LevelVec = (Vector) GrpBomVec.elementAt(5);
        Vector CompDescVec = (Vector) GrpBomVec.elementAt(6);
        Vector IndexVec = (Vector) GrpBomVec.elementAt(7);

        //creating new vectors for refreshing GrpBomVec.
        Vector tempCompVec = new Vector();
        Vector tempCompTypeVec = new Vector();
        Vector tempQtyVec = new Vector();
        Vector tempSeqNoVec = new Vector();
        Vector tempRevNoVec = new Vector();
        Vector tempLevelVec = new Vector();
        Vector tempCompDescVec = new Vector();
        Vector tempGrpBomVec = new Vector();
        Vector tempIndexVec = new Vector();

        tempGrpBomVec.add(tempCompVec);
        tempGrpBomVec.add(tempCompTypeVec);
        tempGrpBomVec.add(tempQtyVec);
        tempGrpBomVec.add(tempSeqNoVec);
        tempGrpBomVec.add(tempRevNoVec);
        tempGrpBomVec.add(tempLevelVec);
        tempGrpBomVec.add(tempCompDescVec);
        tempGrpBomVec.add(tempIndexVec);

        int length = arrangedcompindex.length;

        for (int i = 0; i < length; i++)
        {
            //storing values to new Vectors
            index = IndexVec.indexOf(Integer.parseInt(arrangedcompindex[i]));
            Vector tempVec = new Vector();
            comp = "" + CompVec.elementAt(index);
            tempVec.add(comp);
            comptype = "" + CompTypeVec.elementAt(index);
            tempVec.add(comptype);
            tempVec.add(QtyVec.elementAt(index));
            tempVec.add(SeqNoVec.elementAt(index));
            tempVec.add(RevNoVec.elementAt(index));
            level1 = ((Integer) LevelVec.elementAt(index)).intValue();
            tempVec.add(level1);
            tempVec.add(CompDescVec.elementAt(index));
            tempVec.add(IndexVec.elementAt(index));

            //storing new Vectors to tempGrpBomVec Vector.
            for (int j = 0; j < tempGrpBomVec.size(); j++)
            {
                Vector GrpCompVec = (Vector) tempGrpBomVec.elementAt(j);
                GrpCompVec.add(tempVec.elementAt(j));
            }
            //if component is an Assembly then refresh its Bom.
            if (comptype.equals("ASM"))
            {
                index++;
                tempGrpBomVec = refreshAssyBom1(GrpBomVec, tempGrpBomVec, level);
            }
        }
        return tempGrpBomVec;
    }

    //30. to check special symbols.
    //31. to create templates.
    public boolean createTemplate_3(String comptype, String path, Connection conn)
    {
        //Statement st = null;
    	PreparedStatement st = null;
        ResultSet rs = null;
        String param_domain = "";
        //String param_desc="";
        int colCounter = 0, rowCounter = 0;
        WritableWorkbook workbook = null;
        String filepath = path + comptype + "_template.xls";
        File file = new File(filepath);
        if (file.exists())
        {
            file.delete();
        }
        //////System.out.println("File Path" + filepath);

        try
        {
            //getting the workbook.
            workbook = Workbook.createWorkbook(new File(filepath));

            // CREATE A CELL FORMAT HEADING WITH WRAP

            WritableFont heading = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
            WritableCellFormat headingFormat = new WritableCellFormat(heading);
            headingFormat.setWrap(true);
            headingFormat.setAlignment(Alignment.CENTRE);
            headingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            headingFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT HEADING WITH WRAP WITHOUT BORDER

            WritableFont heading_10 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat headingFormat_without = new WritableCellFormat(heading_10);
            headingFormat_without.setWrap(true);
            headingFormat_without.setAlignment(Alignment.CENTRE);
            headingFormat_without.setVerticalAlignment(VerticalAlignment.CENTRE);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTER ALIGNMENT WITH BORDER

            WritableFont text = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat textFormat = new WritableCellFormat(text);
            textFormat.setAlignment(Alignment.CENTRE);
            textFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTER ALIGNMENT WITHOUT BORDER

            WritableCellFormat textFormat_without = new WritableCellFormat(text);
            textFormat_without.setAlignment(Alignment.CENTRE);
            textFormat_without.setVerticalAlignment(VerticalAlignment.CENTRE);

            // CREATE A CELL FORMAT FOR TEXT WITH LEFT ALIGNMENT

            WritableCellFormat textFormat_LEFT = new WritableCellFormat(NumberFormats.TEXT);
            textFormat_LEFT.setAlignment(Alignment.LEFT);
            textFormat_LEFT.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat_LEFT.setBackground(Colour.YELLOW);
            textFormat_LEFT.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat textFormat_CENTER = new WritableCellFormat(text);
            textFormat_CENTER.setAlignment(Alignment.CENTRE);
            textFormat_CENTER.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat textFormat_TOP = new WritableCellFormat(text);
            textFormat_TOP.setAlignment(Alignment.LEFT);
            textFormat_TOP.setVerticalAlignment(VerticalAlignment.TOP);

            WritableCellFormat textFormat_RIGHT = new WritableCellFormat(text);
            textFormat_RIGHT.setAlignment(Alignment.RIGHT);
            textFormat_RIGHT.setVerticalAlignment(VerticalAlignment.CENTRE);
            // CREATE A CELL FORMAT FOR LINK

            WritableFont forLink = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD, true);
            WritableCellFormat linkFormat = new WritableCellFormat(forLink);
            linkFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            linkFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TABLE HEADING

            WritableFont tableHeading = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
            WritableCellFormat tableHeadingFormat = new WritableCellFormat(tableHeading);
            tableHeadingFormat.setAlignment(Alignment.LEFT);
            tableHeadingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            tableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//				tableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);

            WritableFont mandatoryFieldsHeading = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
            WritableCellFormat mandatoryFieldsTableHeadingFormat = new WritableCellFormat(mandatoryFieldsHeading);
            mandatoryFieldsTableHeadingFormat.setAlignment(Alignment.LEFT);
            mandatoryFieldsTableHeadingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            mandatoryFieldsTableHeadingFormat.setBackground(Colour.RED);
            mandatoryFieldsTableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat tableHeadingFormatRIGHT = new WritableCellFormat(tableHeading);
            tableHeadingFormatRIGHT.setAlignment(Alignment.RIGHT);
            tableHeadingFormatRIGHT.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat integerFormat = new WritableCellFormat(NumberFormats.INTEGER);
            integerFormat.setAlignment(Alignment.LEFT);
            integerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            integerFormat.setBackground(Colour.YELLOW);
            integerFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat defaultFormat = new WritableCellFormat(NumberFormats.DEFAULT);
            defaultFormat.setAlignment(Alignment.LEFT);
            defaultFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            defaultFormat.setBackground(Colour.YELLOW);
            defaultFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat floatFormat = new WritableCellFormat(NumberFormats.FLOAT);
            floatFormat.setAlignment(Alignment.LEFT);
            floatFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            floatFormat.setBackground(Colour.YELLOW);
            floatFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat floatFormatBOLD = new WritableCellFormat(tableHeading, NumberFormats.FLOAT);
            floatFormatBOLD.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
            floatFormatBOLD.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableSheet ws = null;

            if (comptype.equals("GRP"))
            {
                try
                {
                    ws = workbook.createSheet("Group Template", 0);
                    ws.setColumnView(0, 20);
                    ws.setColumnView(1, 20);
                    ws.setColumnView(2, 5);
                    ws.setColumnView(3, 10);
                    ws.setColumnView(4, 5);
                    ws.setColumnView(5, 5);
                    ws.setColumnView(6, 5);
                    ws.setColumnView(7, 8);
                    ws.setColumnView(8, 15);
                    ws.setColumnView(9, 15);
                    ws.setColumnView(10, 10);
                    ws.setColumnView(11, 10);
                    ws.setColumnView(12, 15);
                    ws.setColumnView(13, 15);
                    ws.setColumnView(14, 15);
                    ws.setColumnView(15, 15);


                    ws.getSettings().setPaperSize(PaperSize.A4);
                    ws.getSettings().setOrientation(PageOrientation.LANDSCAPE);
//				ws.getSettings().setFitWidth(1);
//				ws.getSettings().setFitHeight(1);
                    ws.getSettings().setHeaderMargin(.5);
                    ws.getSettings().setFooterMargin(.5);
                    ws.getSettings().setTopMargin(.5);
                    ws.getSettings().setBottomMargin(.5);

                    jxl.write.Label briefD = new jxl.write.Label(0, 0, "GROUP TEMPLATE", headingFormat);
                    ws.mergeCells(0, 0, 1, 0);
                    ws.addCell(briefD);


                    jxl.write.Label label_1 = new jxl.write.Label(0, 2, "GROUP NUMBER", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(label_1);
                    label_1 = new jxl.write.Label(1, 2, "<Group Number>", textFormat_LEFT);
                    ws.addCell(label_1);

                    jxl.write.Label label_2 = new jxl.write.Label(0, 3, "GROUP DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(label_2);
                    label_2 = new jxl.write.Label(1, 3, "<Group Description>", textFormat_LEFT);
                    ws.addCell(label_2);
                    rowCounter = 4;
                    //st = conn.createStatement();
                    String comp_param = "";

                    //rs = st.executeQuery("select distinct PARAM_DESC,PARAM_DOMAIN_TYPE from COMP_PARAM_MASTER where PARAM_TYPE='" + comptype + "' order by PARAM_DESC");
                   String query = ("select distinct PARAM_DESC,PARAM_DOMAIN_TYPE from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='" + comptype + "' order by PARAM_DESC");
                   st = conn.prepareStatement(query);
                   rs = st.executeQuery();
                    while (rs.next())
                    {
                        comp_param = rs.getString(1);

                        jxl.write.Label label_3 = new jxl.write.Label(0, rowCounter, comp_param, tableHeadingFormat);
                        ws.addCell(label_3);
                        param_domain = rs.getString(2);
                        if (param_domain.equals("Integer"))
                        {
                            jxl.write.Label label_text = new jxl.write.Label(1, rowCounter, "<parameter value>", integerFormat);
                            ws.addCell(label_text);
                        }
                        else if (param_domain.equals("Double"))
                        {
                            jxl.write.Label label_text = new jxl.write.Label(1, rowCounter, "<parameter value>", floatFormat);
                            ws.addCell(label_text);
                        }
                        else
                        {
                            jxl.write.Label label_text = new jxl.write.Label(1, rowCounter, "<parameter value>", textFormat_LEFT);
                            ws.addCell(label_text);
                        }
                        rowCounter++;
                    }
                    rs.close();

                    rowCounter++;
                    jxl.write.Label label_4 = new jxl.write.Label(0, rowCounter, "LEVEL", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(label_4);
                    label_4 = new jxl.write.Label(0, rowCounter + 1, "", textFormat_LEFT);
                    ws.addCell(label_4);

                    jxl.write.Label label_5 = new jxl.write.Label(colCounter + 1, rowCounter, "SEQ. NO.", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(label_5);
                    label_5 = new jxl.write.Label(colCounter + 1, rowCounter + 1, "", textFormat_LEFT);
                    ws.addCell(label_5);

                    jxl.write.Label label_6 = new jxl.write.Label(colCounter + 2, rowCounter, "ITEM", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(label_6);
                    label_6 = new jxl.write.Label(colCounter + 2, rowCounter + 1, "", textFormat_LEFT);
                    ws.addCell(label_6);

                    jxl.write.Label label_7 = new jxl.write.Label(colCounter + 3, rowCounter, "DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(label_7);
                    label_7 = new jxl.write.Label(colCounter + 3, rowCounter + 1, "", textFormat_LEFT);
                    ws.addCell(label_7);

                    jxl.write.Label label_8 = new jxl.write.Label(colCounter + 4, rowCounter, "TYPE", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(label_8);
                    label_8 = new jxl.write.Label(colCounter + 4, rowCounter + 1, "", textFormat_LEFT);
                    ws.addCell(label_8);

                    jxl.write.Label label_9 = new jxl.write.Label(colCounter + 5, rowCounter, "QTY", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(label_9);
                    label_9 = new jxl.write.Label(colCounter + 5, rowCounter + 1, "", defaultFormat);
                    ws.addCell(label_9);

                    colCounter += 6;
                   // rs = st.executeQuery("select distinct PARAM_DESC,PARAM_DOMAIN_TYPE from COMP_PARAM_MASTER where PARAM_TYPE in ('PRT','ASM') order by PARAM_DESC;");
                    String query1 = ("select distinct PARAM_DESC,PARAM_DOMAIN_TYPE from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE in ('PRT','ASM') order by PARAM_DESC;");
                    st = conn.prepareStatement(query1);
                    rs = st.executeQuery();
                    while (rs.next())
                    {
                        comp_param = rs.getString(1);

                        //System.out.println("comp_param:" + comp_param);
                        jxl.write.Label label_10 = new jxl.write.Label(colCounter, rowCounter, comp_param, tableHeadingFormat);
                        ws.addCell(label_10);
                        param_domain = rs.getString(2);
                        //System.out.println("param_domain :"+param_domain);
                        if (param_domain.equals("Integer"))
                        {
                            label_10 = new jxl.write.Label(colCounter, rowCounter + 1, "", integerFormat);
                            ws.addCell(label_10);
                        }
                        else if (param_domain.equals("Double"))
                        {
                            label_10 = new jxl.write.Label(colCounter, rowCounter + 1, "", floatFormat);
                            ws.addCell(label_10);
                        }
                        else
                        {
                            label_10 = new jxl.write.Label(colCounter, rowCounter + 1, "", textFormat_LEFT);
                            ws.addCell(label_10);
                        }

                        colCounter++;
                    }
                    rs.close();

//                    rs = st.executeQuery("select distinct PARAM_DESC,PARAM_DOMAIN_TYPE from GRP_BOM_PARAM_MASTER order by PARAM_DESC;");
//                    while (rs.next()) {
//                        comp_param = rs.getString(1);
//                        jxl.write.Label label_11 = new jxl.write.Label(colCounter, rowCounter, comp_param, tableHeadingFormat);
//                        ws.addCell(label_11);
//                        param_domain= rs.getString(2);
//                        //System.out.println("param_domain :"+param_domain);
//                        if(param_domain.equals("Integer"))
//                        {
//                            label_11 = new jxl.write.Label(colCounter, rowCounter + 1, "", integerFormat);
//                            ws.addCell(label_11);
//                        }
//                        else if(param_domain.equals("Double"))
//                        {
//                            label_11 = new jxl.write.Label(colCounter, rowCounter + 1, "", floatFormat);
//                            ws.addCell(label_11);
//                        }
//                        else
//                        {
//                            label_11 = new jxl.write.Label(colCounter, rowCounter + 1, "", textFormat_LEFT);
//                            ws.addCell(label_11);
//                        }
//
//                        colCounter++;
//                    }
//                    rs.close();


                    st.close();
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                    return false;
                }
            }
            else if (comptype.equals("KIT"))
            {

                ws = workbook.createSheet("KIT Template", 0);
                ws.setColumnView(0, 15);
                ws.setColumnView(1, 20);
                ws.setColumnView(2, 10);
                ws.getSettings().setPaperSize(PaperSize.A4);
                ws.getSettings().setOrientation(PageOrientation.LANDSCAPE);
                ws.getSettings().setHeaderMargin(.5);
                ws.getSettings().setFooterMargin(.5);
                ws.getSettings().setTopMargin(.5);
                ws.getSettings().setBottomMargin(.5);

                jxl.write.Label briefD = new jxl.write.Label(0, 0, "KIT TEMPLATE", headingFormat);
                ws.mergeCells(0, 0, 1, 0);
                ws.addCell(briefD);

                jxl.write.Label label_1 = new jxl.write.Label(0, 2, "KIT NUMBER", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_1);
                label_1 = new jxl.write.Label(1, 2, "<Kit Number>", textFormat_LEFT);
                ws.addCell(label_1);

                jxl.write.Label label_2 = new jxl.write.Label(0, 3, "KIT DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_2);
                label_2 = new jxl.write.Label(1, 3, "<Kit Description>", textFormat_LEFT);
                ws.addCell(label_2);

                label_2 = new jxl.write.Label(0, 4, "MOQ", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_2);
                label_2 = new jxl.write.Label(1, 4, "<MOQ>", textFormat_LEFT);
                ws.addCell(label_2);

                label_2 = new jxl.write.Label(0, 5, "QML", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_2);
                label_2 = new jxl.write.Label(1, 5, "<QML>", textFormat_LEFT);
                ws.addCell(label_2);

                label_2 = new jxl.write.Label(0, 6, "NDP", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_2);
                label_2 = new jxl.write.Label(1, 6, "<NDP>", textFormat_LEFT);
                ws.addCell(label_2);

                rowCounter += 8;

                jxl.write.Label label_6 = new jxl.write.Label(colCounter, rowCounter, "ITEM", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_6);
                label_6 = new jxl.write.Label(colCounter, rowCounter + 1, "", textFormat_LEFT);
                ws.addCell(label_6);

                jxl.write.Label label_9 = new jxl.write.Label(colCounter + 1, rowCounter, "QTY", mandatoryFieldsTableHeadingFormat);
                ws.addCell(label_9);
                label_9 = new jxl.write.Label(colCounter + 1, rowCounter + 1, "", defaultFormat);
                ws.addCell(label_9);

            }
            else if (comptype.equals("PRT"))
            {
                try
                {
                    ws = workbook.createSheet("Part Excel", 0);

                    ws.setColumnView(0, 15);
                    ws.setColumnView(1, 20);
                    ws.setColumnView(2, 10);
                    ws.setColumnView(3, 15);
                    ws.setColumnView(4, 8);
                    ws.setColumnView(5, 8);
                    ws.setColumnView(6, 7);
                    ws.setColumnView(7, 15);
                    ws.setColumnView(8, 10);
                    ws.setColumnView(9, 10);
                    ws.setColumnView(10, 10);
                    ws.setColumnView(11, 10);
                    ws.setColumnView(12, 10);
                    ws.setColumnView(13, 10);
                    ws.setColumnView(14, 10);
                    ws.setColumnView(15, 10);
                    jxl.write.Label briefD1 = new jxl.write.Label(0, 0, "PART TEMPLATE", headingFormat);
                    ws.mergeCells(0, 0, 1, 0);
                    ws.addCell(briefD1);


                    // jxl.write.Label briefD2 = new jxl.write.Label(0, 1, " ",headingFormat_without);
                    //ws.mergeCells(0, 1, 6, 1);
                    // ws.addCell(briefD2);

                    jxl.write.Label briefD3 = new jxl.write.Label(0, 2, "part_no", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD3);
                    jxl.write.Label label_10 = new jxl.write.Label(0, 3, "", textFormat_LEFT);
                    ws.addCell(label_10);

                    jxl.write.Label briefD4 = new jxl.write.Label(1, 2, "p1", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD4);
                    label_10 = new jxl.write.Label(1, 3, "", textFormat_LEFT);
                    ws.addCell(label_10);
                    jxl.write.Label briefD5 = new jxl.write.Label(2, 2, "p3", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD5);
                    label_10 = new jxl.write.Label(2, 3, "", textFormat_LEFT);
                    ws.addCell(label_10);
                    jxl.write.Label briefD6 = new jxl.write.Label(3, 2, "p4", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD6);
                    label_10 = new jxl.write.Label(3, 3, "", textFormat_LEFT);
                    ws.addCell(label_10);
                    jxl.write.Label briefD7 = new jxl.write.Label(4, 2, "np4", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD7);
                    label_10 = new jxl.write.Label(4, 3, "", textFormat_LEFT);
                    ws.addCell(label_10);
                    colCounter = 2;
                    rowCounter = 2;
//                    Statement stmt = conn.createStatement();

                 //   System.out.println("conn status ::" + conn.isClosed());
//                    rs = stmt.executeQuery("select distinct part_no from PART where part_no='" + briefD3 + "' order by PARAM_DESC");
//
//                    while (rs.next()) {
//                        ////System.out.println("in rs");
//                        String param_desc = rs.getString(1);
//                        jxl.write.Label briefD8 = new jxl.write.Label(colCounter, rowCounter, param_desc, tableHeadingFormat);
//                        ws.addCell(briefD8);
//                        param_domain= rs.getString(2);
//                        //System.out.println("param_domain :"+param_domain);
//
//                        if(param_domain.equals("Integer"))
//                        {
//                            label_10 = new jxl.write.Label(colCounter, rowCounter + 1, "", integerFormat);
//                            ws.addCell(label_10);
//                        }
//                        else if(param_domain.equals("Double"))
//                        {
//                            label_10 = new jxl.write.Label(colCounter, rowCounter + 1, "", floatFormat);
//                            ws.addCell(label_10);
//                        }
//                        else
//                        {
//                            label_10 = new jxl.write.Label(colCounter, rowCounter + 1, "", textFormat_LEFT);
//                            ws.addCell(label_10);
//                        }
//                        colCounter++;
//                    }
//                    rs.close();
//                    stmt.close();
                }
                catch (Exception ex)
                {
                    ////System.out.println("i m in sql exception ///");

                    ex.printStackTrace();
                    return false;
                }
            }
            else if (comptype.equals("TOL"))
            {
                ws = workbook.createSheet("Tool Excel", 0);

                ws.setColumnView(0, 15);
                ws.setColumnView(1, 20);
                ws.setColumnView(2, 10);
                ws.setColumnView(3, 15);
                ws.setColumnView(4, 8);
                ws.setColumnView(5, 8);
                ws.setColumnView(6, 7);
                ws.setColumnView(7, 15);
                ws.setColumnView(8, 10);
                ws.setColumnView(9, 10);
                ws.setColumnView(10, 10);
                ws.setColumnView(11, 10);
                ws.setColumnView(12, 10);
                ws.setColumnView(13, 10);
                ws.setColumnView(14, 10);
                ws.setColumnView(15, 10);

                jxl.write.Label briefD1 = new jxl.write.Label(0, 0, "TOOL TEMPLATE", headingFormat);
                ws.mergeCells(0, 0, 1, 0);
                ws.addCell(briefD1);


                // jxl.write.Label briefD2 = new jxl.write.Label(0, 1, " ",headingFormat_without);
                //ws.mergeCells(0, 1, 6, 1);
                // ws.addCell(briefD2);

                jxl.write.Label briefD3 = new jxl.write.Label(0, 2, "TOOL NUMBER", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD3);
                jxl.write.Label label_10 = new jxl.write.Label(0, 3, "", textFormat_LEFT);
                ws.addCell(label_10);

                jxl.write.Label briefD4 = new jxl.write.Label(1, 2, "TOOL DESCRIPTION", mandatoryFieldsTableHeadingFormat);
                ws.addCell(briefD4);
                label_10 = new jxl.write.Label(1, 3, "", textFormat_LEFT);
                ws.addCell(label_10);

                String param_desc = "";
                colCounter = 2;
                rowCounter = 2;
                PreparedStatement stmt = null;
                //Statement stmt = conn.createStatement();
                ///rs = stmt.executeQuery("select distinct PARAM_DESC,PARAM_DOMAIN_TYPE from COMP_PARAM_MASTER where PARAM_TYPE='TOL' order by PARAM_DESC");
                String query = ("select distinct PARAM_DESC,PARAM_DOMAIN_TYPE from COMP_PARAM_MASTER(NOLOCK) where PARAM_TYPE='TOL' order by PARAM_DESC");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                while (rs.next())
                {
                    param_desc = rs.getString(1);
                    jxl.write.Label briefD5 = new jxl.write.Label(colCounter, rowCounter, "" + param_desc, tableHeadingFormat);
                    ws.addCell(briefD5);
                    param_domain = rs.getString(2);
                    if (param_domain.equals("Integer"))
                    {
                        label_10 = new jxl.write.Label(colCounter, rowCounter + 1, "", integerFormat);
                        ws.addCell(label_10);
                    }
                    else if (param_domain.equals("Double"))
                    {
                        label_10 = new jxl.write.Label(colCounter, rowCounter + 1, "", floatFormat);
                        ws.addCell(label_10);
                    }
                    else
                    {
                        label_10 = new jxl.write.Label(colCounter, rowCounter + 1, "", textFormat_LEFT);
                        ws.addCell(label_10);
                    }
                    colCounter++;
                }
                rs.close();
            }

            rowCounter += 2;
            jxl.write.Label label_11 = new jxl.write.Label(0, rowCounter, "end", mandatoryFieldsTableHeadingFormat);
            ws.addCell(label_11);

            rowCounter += 2;
            label_11 = new jxl.write.Label(0, rowCounter, "", textFormat_LEFT);
            ws.addCell(label_11);
            jxl.write.Label label_12 = new jxl.write.Label(1, rowCounter, ":To Be Filled By User", tableHeadingFormat);
            ws.addCell(label_12);
            rowCounter++;

            jxl.write.Label label_13 = new jxl.write.Label(0, rowCounter, "", mandatoryFieldsTableHeadingFormat);
            ws.addCell(label_13);

            jxl.write.Label label_14 = new jxl.write.Label(1, rowCounter, ":Mandatory Fields", tableHeadingFormat);
            ws.addCell(label_14);

            workbook.write();
            workbook.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    //35. to get Type of the Group from the database.
    public Vector getCategoryTypes(Connection conn, String partType) throws Exception
    {
        ResultSet rs;
       // Statement stmt = null;
        PreparedStatement stmt = null;
       // stmt = conn.createStatement();
        String query = "";
        String temp = null;
        Vector partTypeVec = new Vector();
        //getting all Type of the Group Types from MODEL_GROUP.
        query = "Select distinct np4 from cat_part(NOLOCK) where part_type='" + partType + "'";
        stmt = conn.prepareStatement(query);
        //stmt = conn.createStatement();
        rs = stmt.executeQuery();

        while (rs.next())
        {

            temp = rs.getString(1);
            if (temp != null)
            {
                partTypeVec.add(temp);
            }
        }
        //////System.out.println("grpTypeVec:" + grpTypeVec);
        rs.close();
        stmt.close();
        return partTypeVec;
    }

    public Vector getCategoryTypes(Connection conn) throws Exception
    {
        ResultSet rs;
        PreparedStatement stmt = null;
        //stmt = conn.createStatement();
        String query = "";
        String temp = null;
        Vector partTypeVec = new Vector();
        //getting all Type of the Group Types from MODEL_GROUP.
        query = "Select distinct np4 from part(NOLOCK)";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();

        while (rs.next())
        {

            temp = rs.getString(1);
            if (temp != null)
            {
                partTypeVec.add(temp);
            }
        }
        //////System.out.println("grpTypeVec:" + grpTypeVec);
        rs.close();
        stmt.close();
        return partTypeVec;
    }

    //49. to check all special symbols.
    public boolean createAMWPartandOEMPartTemplate(String comptype, String path, Connection conn)
    {
        //Statement st = null;
    	PreparedStatement st = null;
        ResultSet rs = null;
        String param_domain = "";
        //String param_desc="";
        int colCounter = 0, rowCounter = 0;
        WritableWorkbook workbook = null;
        String filepath = path + comptype + "_template.xls";
        File file = new File(filepath);
        if (file.exists())
        {
            file.delete();
        }
        //////System.out.println("File Path" + filepath);

        try
        {
            //getting the workbook.
            workbook = Workbook.createWorkbook(new File(filepath));

            // CREATE A CELL FORMAT HEADING WITH WRAP

            WritableFont heading = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
            WritableCellFormat headingFormat = new WritableCellFormat(heading);
            headingFormat.setWrap(true);
            headingFormat.setAlignment(Alignment.CENTRE);
            headingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            headingFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT HEADING WITH WRAP WITHOUT BORDER

            WritableFont heading_10 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            WritableCellFormat headingFormat_without = new WritableCellFormat(heading_10);
            headingFormat_without.setWrap(true);
            headingFormat_without.setAlignment(Alignment.CENTRE);
            headingFormat_without.setVerticalAlignment(VerticalAlignment.CENTRE);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTER ALIGNMENT WITH BORDER

            WritableFont text = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat textFormat = new WritableCellFormat(text);
            textFormat.setAlignment(Alignment.CENTRE);
            textFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TEXT WITH CENTER ALIGNMENT WITHOUT BORDER

            WritableCellFormat textFormat_without = new WritableCellFormat(text);
            textFormat_without.setAlignment(Alignment.CENTRE);
            textFormat_without.setVerticalAlignment(VerticalAlignment.CENTRE);

            // CREATE A CELL FORMAT FOR TEXT WITH LEFT ALIGNMENT

            WritableCellFormat textFormat_LEFT = new WritableCellFormat(NumberFormats.TEXT);
            textFormat_LEFT.setAlignment(Alignment.LEFT);
            textFormat_LEFT.setVerticalAlignment(VerticalAlignment.CENTRE);
            textFormat_LEFT.setBackground(Colour.YELLOW);
            textFormat_LEFT.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat textFormat_CENTER = new WritableCellFormat(text);
            textFormat_CENTER.setAlignment(Alignment.CENTRE);
            textFormat_CENTER.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat textFormat_TOP = new WritableCellFormat(text);
            textFormat_TOP.setAlignment(Alignment.LEFT);
            textFormat_TOP.setVerticalAlignment(VerticalAlignment.TOP);

            WritableCellFormat textFormat_RIGHT = new WritableCellFormat(text);
            textFormat_RIGHT.setAlignment(Alignment.RIGHT);
            textFormat_RIGHT.setVerticalAlignment(VerticalAlignment.CENTRE);
            // CREATE A CELL FORMAT FOR LINK

            WritableFont forLink = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD, true);
            WritableCellFormat linkFormat = new WritableCellFormat(forLink);
            linkFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            linkFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            // CREATE A CELL FORMAT FOR TABLE HEADING

            WritableFont tableHeading = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
            WritableCellFormat tableHeadingFormat = new WritableCellFormat(tableHeading);
            tableHeadingFormat.setAlignment(Alignment.LEFT);
            tableHeadingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            tableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//				tableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);

            WritableFont mandatoryFieldsHeading = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
            WritableCellFormat mandatoryFieldsTableHeadingFormat = new WritableCellFormat(mandatoryFieldsHeading);
            mandatoryFieldsTableHeadingFormat.setAlignment(Alignment.LEFT);
            mandatoryFieldsTableHeadingFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            mandatoryFieldsTableHeadingFormat.setBackground(Colour.RED);
            mandatoryFieldsTableHeadingFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat tableHeadingFormatRIGHT = new WritableCellFormat(tableHeading);
            tableHeadingFormatRIGHT.setAlignment(Alignment.RIGHT);
            tableHeadingFormatRIGHT.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableCellFormat integerFormat = new WritableCellFormat(NumberFormats.INTEGER);
            integerFormat.setAlignment(Alignment.LEFT);
            integerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            integerFormat.setBackground(Colour.YELLOW);
            integerFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat defaultFormat = new WritableCellFormat(NumberFormats.DEFAULT);
            defaultFormat.setAlignment(Alignment.LEFT);
            defaultFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            defaultFormat.setBackground(Colour.YELLOW);
            defaultFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat floatFormat = new WritableCellFormat(NumberFormats.FLOAT);
            floatFormat.setAlignment(Alignment.LEFT);
            floatFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            floatFormat.setBackground(Colour.YELLOW);
            floatFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            WritableCellFormat floatFormatBOLD = new WritableCellFormat(tableHeading, NumberFormats.FLOAT);
            floatFormatBOLD.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
            floatFormatBOLD.setVerticalAlignment(VerticalAlignment.CENTRE);

            WritableSheet ws = null;

            if (comptype.equals("AMW_PART_OEM_PART"))
            {
                try
                {
                    ws = workbook.createSheet("Part Excel", 0);

                    ws.setColumnView(0, 15);
                    ws.setColumnView(1, 20);
                    ws.setColumnView(2, 10);
                    ws.setColumnView(3, 15);
                    ws.setColumnView(4, 8);
                    ws.setColumnView(5, 8);
                    ws.setColumnView(6, 7);
                    ws.setColumnView(7, 15);
                    ws.setColumnView(8, 10);
                    ws.setColumnView(9, 10);
                    ws.setColumnView(10, 10);
                    ws.setColumnView(11, 10);
                    ws.setColumnView(12, 10);
                    ws.setColumnView(13, 10);
                    ws.setColumnView(14, 10);
                    ws.setColumnView(15, 10);
                    jxl.write.Label briefD1 = new jxl.write.Label(0, 0, "AMW PART OEM PART TEMPLATE", headingFormat);
                    ws.mergeCells(0, 0, 1, 0);
                    ws.addCell(briefD1);


                    // jxl.write.Label briefD2 = new jxl.write.Label(0, 1, " ",headingFormat_without);
                    //ws.mergeCells(0, 1, 6, 1);
                    // ws.addCell(briefD2);

                    jxl.write.Label briefD3 = new jxl.write.Label(0, 2, "AMW_PART_NO", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD3);
                    jxl.write.Label label_10 = new jxl.write.Label(0, 3, "", textFormat_LEFT);
                    ws.addCell(label_10);

                    jxl.write.Label briefD4 = new jxl.write.Label(1, 2, "OEM_PART_NO", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD4);
                    label_10 = new jxl.write.Label(1, 3, "", textFormat_LEFT);
                    ws.addCell(label_10);
                    jxl.write.Label briefD5 = new jxl.write.Label(2, 2, "OEM_PART_DESC", mandatoryFieldsTableHeadingFormat);
                    ws.addCell(briefD5);
                    label_10 = new jxl.write.Label(2, 3, "", textFormat_LEFT);
                    ws.addCell(label_10);
                    colCounter = 2;
                    rowCounter = 2;
                 //   System.out.println("conn status ::" + conn.isClosed());
                }
                catch (Exception ex)
                {
                    ////System.out.println("i m in sql exception ///");
                    ex.printStackTrace();
                    return false;
                }
            }
            rowCounter += 2;
            jxl.write.Label label_11 = new jxl.write.Label(0, rowCounter, "end", mandatoryFieldsTableHeadingFormat);
            ws.addCell(label_11);

            rowCounter += 2;
            label_11 = new jxl.write.Label(0, rowCounter, "", textFormat_LEFT);
            ws.addCell(label_11);
            jxl.write.Label label_12 = new jxl.write.Label(1, rowCounter, ":To Be Filled By User", tableHeadingFormat);
            ws.addCell(label_12);
            rowCounter++;

            jxl.write.Label label_13 = new jxl.write.Label(0, rowCounter, "", mandatoryFieldsTableHeadingFormat);
            ws.addCell(label_13);

            jxl.write.Label label_14 = new jxl.write.Label(1, rowCounter, ":Mandatory Fields", tableHeadingFormat);
            ws.addCell(label_14);

            workbook.write();
            workbook.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean specialCharacterCheck(String s)
    {
        final char[] regExpChars = new char[]
        {
            '*', '+', '[', ']', '.', '^', '&', '\\', '$', '~', '`', '\'', '!', '|', '/', '#', '(', ')', ',', '<', '>', '_',
            '?', '{', '}', '=', '@', '%', '"'
        };
        boolean result = false;
        if ((s != null) && (s.length() > 0))
        {
            for (int i = 0; i < regExpChars.length; i++)
            {
                result = (s.indexOf(regExpChars[i]) >= 0);
                if (result)
                {
                    break;
                }
            }
        }
        if ((s != null) && (s.length() > 0))
        {
            for (int i = 0; i < regExpChars.length; i++)
            {
                result = (s.indexOf(regExpChars[i]) >= 0);
                if (result)
                {
                    break;
                }
            }
        }
        return result;
    }

    public boolean containsOnlyNumbers(String str)
    {

        //It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0)
        {
            return false;
        }

        for (int i = 0; i < str.length(); i++)
        {

            //If we find a non-digit character we return false.
            if (!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    public String getgrpmap(String grpno, Connection conn)
    {
        String GROUP_MAP = "";
        int sts = 0;
        try
        {
            //Statement st = conn.createStatement();
        	PreparedStatement st = null;
        	ResultSet rs = null;
            //ResultSet rs = st.executeQuery("Select distinct MAP_NAME from cat_model_group(NOLOCK) where GROUP_NO='" + grpno + "'");
           String query = ("Select distinct MAP_NAME from cat_model_group(NOLOCK) where GROUP_NO='" + grpno + "'");
           st = conn.prepareStatement(query);
           rs = st.executeQuery();
        	if (rs.next())
            {
                GROUP_MAP = rs.getString(1);
                sts = 1;
            }
            rs.close();
            st.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (sts == 0)
        {
            GROUP_MAP = grpno.replace("_", "");

        }
        return GROUP_MAP;
    }
}
