/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Model.DAO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.log4j.Logger;

import EAMG.Model.Action.EAMG_Variant_AggregatesActionForm;
import EAMG.Other.ActionFormBean.ECNFormBean;
import EAMG.Other.ActionFormBean.UtilityOptionBean;
import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author avinash.pandey
 */
public class EAMG_ModelDAO
{
    //34. to get Level Description of Model from the database.

    private Logger logger = Logger.getLogger(this.getClass());

    public Vector getModelEngineSeries(Connection conn) throws Exception
    {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //stmt = conn.createStatement();
        String query = "";
        Vector levelValVec = new Vector();
        //getting Level Description of Model from the database.
        query = "Select distinct(ENGINE_SERIES) from CAT_MODEL_CLASSIFICATION(NOLOCK) order by ENGINE_SERIES";
        //stmt = conn.createStatement();
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //rs = stmt.executeQuery(query);
        while (rs.next())
        {
            levelValVec.add(rs.getString(1));
        }
        //////System.out.println("levelValVec:" + levelValVec);
        rs.close();
        stmt.close();
        return levelValVec;
    }

    public int insertmodelVsComp(Connection conn, EAMG_Variant_AggregatesActionForm modelBean)
    {
        int forward_main = 0;
        int count = 0;
        PreparedStatement stmt = null;
        try
        {
            String model = modelBean.getModel_no();
           // Statement stmt = conn.createStatement();
            //stmt.executeUpdate("delete from CAT_PART_MODEL_MATRIX where engine_model='" + model + "'");
            stmt = conn.prepareStatement("delete from CAT_PART_MODEL_MATRIX where engine_model='" + model + "'");
            stmt.executeUpdate();
            PreparedStatement pstmt = conn.prepareStatement("insert into CAT_PART_MODEL_MATRIX(Engine_model,part_no)  values(?,?)");
            for (String comp : modelBean.getAttachedlist())
            {

                pstmt.setString(1, model);
                pstmt.setString(2, comp);

                count++;

                if (count % 200 == 0)
                {
                    pstmt.executeBatch();
                }

                pstmt.addBatch();
            }
            pstmt.executeBatch();
            conn.commit();

            forward_main = 1;

            pstmt.close();
            stmt.close();
        }
        catch (Exception e)
        {
            try
            {
                conn.rollback();
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
            e.printStackTrace();
            //logger.error(WebConstants.logException, e);
        }
        return forward_main;
    }

    public ArrayList<ArrayList> getCompsByAjax(Connection conn, String engineSeries)
    {
        ArrayList<ArrayList> dataList = new ArrayList();
        ArrayList<String> assignedList = new ArrayList();
        ArrayList<String> unassignedList = new ArrayList<String>();
       // Statement stmt = null;
        PreparedStatement stmt = null;
        String temp = null;
        ResultSet rs = null;
        try
        {
            //stmt = conn.createStatement();
            if (engineSeries.equals("Select"))
            {
                //rs = stmt.executeQuery("select part_no  from cat_part");
            	String query = ("select part_no  from cat_part(NOLOCK)");
            	stmt = conn.prepareStatement(query);
            	rs = stmt.executeQuery();
            }
            else
            {
                //rs = stmt.executeQuery("select ");
            	String query = ("select ");
            	stmt = conn.prepareStatement(query);
            	rs = stmt.executeQuery();
            }
            while (rs.next())
            {
                if (!engineSeries.equals("Select"))
                {
                    temp = rs.getString(3);
                }
                if (temp != null)
                {
                    assignedList.add(temp);
                    assignedList.add(rs.getString(2));
                }
                else
                {
                    unassignedList.add(rs.getString(1));
                    unassignedList.add(rs.getString(2));
                }

            }
            dataList.add(assignedList);
            dataList.add(unassignedList);
            conn.commit();

            rs.close();
            stmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //logger.error(WebConstants.logException, e);
        }

        return dataList;
    }

    public ArrayList<String> getModel(Connection conn) throws Exception
    {
        ResultSet rs;
       // Statement stmt = null;
        PreparedStatement stmt = null;
        //stmt = conn.createStatement();
        String query = "";
        ArrayList<String> levelValVec = new ArrayList();
        //getting Level Description of Model from the database.
        query = "Select distinct (engine_model) from CAT_MODEL_CLASSIFICATION(NOLOCK) order by engine_model";
        //stmt = conn.createStatement();
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //rs = stmt.executeQuery(query);
        while (rs.next())
        {
            levelValVec.add(rs.getString(1));
        }
        //////System.out.println("levelValVec:" + levelValVec);
        rs.close();
        stmt.close();
        conn.commit();
        return levelValVec;
    }

    public ArrayList getModelComp(Connection conn, String modelNo) throws Exception
    {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //stmt = conn.createStatement();
        
        String query = "";
        String temp = null;
        ArrayList<String> unAssignedList = new ArrayList();
        ArrayList<String> AssingedList = new ArrayList();
        ArrayList data = new ArrayList();
        //getting Level Description of Model from the database.

        if (modelNo.equals("all"))
        {
            query = "Select distinct part_no from  CAT_PART(NOLOCK)  where part_type<>'PRT' order by part_no";
        }
        else
        {
            query = "Select p.part_no,pmm.part_no from cat_part(NOLOCK) p left join CAT_PART_MODEL_MATRIX pmm on (pmm.part_no=p.part_no and pmm.engine_model='" + modelNo + "') WHERE p.part_type<>'PRT' order by p.part_no";
        }

        // System.out.println("query" + query);


        //stmt = conn.createStatement();
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //rs = stmt.executeQuery(query);
        while (rs.next())
        {
            if (!modelNo.equals("all"))
            {
                temp = rs.getString(2);
            }

            if (temp != null && !modelNo.equals("all"))
            {
                AssingedList.add(temp);
            }
            else
            {
                unAssignedList.add(rs.getString(1));
            }
        }
        //////System.out.println("levelValVec:" + levelValVec);

        data.add(AssingedList);
        data.add(unAssignedList);
        rs.close();
        stmt.close();
        conn.commit();

        if (modelNo.equals("all"))
        {
            return unAssignedList;
        }
        else
        {
            return data;
        }
    }

    public String getModels(ECNFormBean ecnForm, Connection conn, String status) throws Exception
    {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        String message = "fail";
        // ArrayList<String> data = new ArrayList();
        try
        {

            //stmt = conn.createStatement();
            ArrayList<String> data = new ArrayList();
            //rs = stmt.executeQuery("select model_no from cat_models where status='" + status + "' order by model_no");
            String query = ("select model_no from cat_models(NOLOCK) where status='" + status + "' order by model_no");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next())
            {
                data.add(rs.getString(1));
            }
            ecnForm.setModelList(data);
            conn.commit();
            message = "success";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (stmt != null)
            {
                stmt.close();
            }
        }

        return message;

    }

    public String generateNewGroupNumber(String oldGroupNumber, Connection conn, String realPath)
    {
        String newGroupNumber = null;
        ResultSet rs = null;
        PreparedStatement pstmt3 = null;

        try
        {
            /* double randomNumber = Math.random();
            NumberFormat nf = NumberFormat.getInstance();
            nf.format(randomNumber);
            nf.setMaximumIntegerDigits(2);
            nf.setMaximumFractionDigits(0);
            int random = (int) randomNumber;
             */
            String random = String.valueOf(Math.random());
            random = random.substring(2);
            random = random.substring(random.length() - 2);

            
            if(oldGroupNumber.lastIndexOf("_")==oldGroupNumber.length()-3)
            {
                newGroupNumber = oldGroupNumber.substring(0, oldGroupNumber.lastIndexOf("_")) + "_" + random;
            }
            else
            {
                newGroupNumber = oldGroupNumber + "_" + random;
            }
            


          //  System.out.println("newGroupNumber " + newGroupNumber);

            pstmt3 = conn.prepareStatement("SELECT GRP_KIT_NO FROM cat_group_kit_detail(NOLOCK) where GRP_KIT_NO=?");
            pstmt3.setString(1, newGroupNumber);
            rs = pstmt3.executeQuery();
            if (rs.next())
            {
                pstmt3.close();
                pstmt3 = null;
                generateNewGroupNumber(oldGroupNumber, conn, realPath);
            }
            else
            {

                FileInputStream fis = new FileInputStream(realPath + "/svg/" + oldGroupNumber + ".svg");
                FileOutputStream fos = new FileOutputStream(realPath + "/svg/" + newGroupNumber + ".svg");

                int d = 0;
                while ((d = fis.read()) != -1)
                {
                    fos.write(d);
                }
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
                if (pstmt3 != null)
                {
                    pstmt3.close();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return newGroupNumber;
    }

    public String implementECN(ECNFormBean ecnForm, Connection conn, String userCode, String realPath) throws Exception
    {
        ResultSet rs, rs1 = null;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        UtilityOptionBean lv = null;
        ArrayList<UtilityOptionBean> affectedModelList = null;
        PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null,
                pstmt3 = null, pstmt4 = null, pstmt5 = null, pstmt6 = null;
        String message = "fail";
        boolean isMoreModelAffected = false;
        String modelNo = null, changeType = null, interchangeStatus = null, effectiveDate = null, effectiveTSN = null, ecnNo = null;
        String oldPartNo = null, newPartNo = null;
        String sequenceNo = null, groupno = null, qty = null, as_Required = null, comp_type = null, grpRemark = null;
     //  String cutoff=null,interchangeability=null,cutoffchassis=null;
        // ArrayList<String> data = new ArrayList();
        try
        {

            //stmt = conn.createStatement();
            modelNo = ecnForm.getModelNo();
            changeType = ecnForm.getChangeType();
            interchangeStatus = ecnForm.getStatus();
            ArrayList<String> exist = new ArrayList();
            effectiveDate = ecnForm.getEffectiveDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date effDate = sdf.parse(effectiveDate);
            java.sql.Date effDateSql = new java.sql.Date(effDate.getTime());
            java.sql.Date todayDateSql = new java.sql.Date(new java.util.Date().getTime());


            effectiveTSN = ecnForm.getEffectiveTSN();
            oldPartNo = ecnForm.getOldPartNo();
            newPartNo = ecnForm.getNewPartNo();
            ecnNo = ecnForm.getEcnNo();
            int index_no = 0;
            int counter = 0;
            int affectedModelCounter = 0;
            String option = ecnForm.getOption();
            String tempModel = null;
            String[] attachedList = null;
            String otherAffectedGroup = null;
            String newGroupName = null;
        //    int randomnumber = 0;
            ArrayList<String> affectedGroupList = new ArrayList();

            if (option.equals("implementECNSelectedModel"))
            {
                attachedList = ecnForm.getAttachedlist();
                StringBuilder sb = new StringBuilder("");

                for (int k = 0; k < attachedList.length; k++)
                {
                    if (k == attachedList.length - 1)
                    {
                        sb.append("'" + attachedList[k] + "'");
                    }
                    else
                    {
                        sb.append("'" + attachedList[k] + "',");
                    }
                }
                pstmt = conn.prepareStatement("UPDATE cat_model_group set GROUP_NO=? WHERE  MODEL_NO=? and GROUP_NO=?");
                pstmt1 = conn.prepareStatement("INSERT INTO  cat_group_kit_bom SELECT ?, COMPONENT, COMP_TYPE, QTY, AS_REQUIRED, SEQUENCE, STICKER_FILE, FDATE, TDATE, INDEX_NO, REMARKS,INTERCHANGEABILITY,CUT_OFF_CHASSIS,CUT_OFF FROM cat_group_kit_bom where grp_kit_no=?");
                pstmt2 = conn.prepareStatement("INSERT INTO cat_group_kit_detail SELECT ?, CREATION_DATE, CREATOR, REMARKS, GK_TYPE, CD_NO, PATCH_NO, IMAGE_SOURCE, STICKER_FILE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10 FROM cat_group_kit_detail where grp_kit_no=?");
                pstmt4 = conn.prepareStatement("insert into CAT_ECN_IMPL_HISTORY(GROUP_ASSY_NO,GRP_ASM_TYPE,NEW_PART,CHANGE_TYPE,OLD_PART,STATUS,QTY,EFFECTIVE_DATE,EFFECTIVE_TSN,UPDATION_DATE,MODIFIED_BY,ECN_NO) SELECT ?,GRP_ASM_TYPE,NEW_PART,CHANGE_TYPE,OLD_PART,STATUS,QTY,EFFECTIVE_DATE,EFFECTIVE_TSN,UPDATION_DATE,MODIFIED_BY,ECN_NO from CAT_ECN_IMPL_HISTORY where GROUP_ASSY_NO=?");



                /*rs = stmt.executeQuery("select  y.model_no,y.GRP_KIT_NO from "
                        + "(select distinct gkb.GRP_KIT_NO,mg.model_no "
                        + "from cat_model_group mg,CAT_GROUP_KIT_BOM gkb  "
                        + "where gkb.grp_kit_no=mg.group_no and "
                        + "gkb.component='" + oldPartNo + "' and "
                        + "mg.model_no in(" + sb.toString() + " ))  x,"
                        + "( select distinct gkb.GRP_KIT_NO,mg.model_no from "
                        + " cat_model_group mg,CAT_GROUP_KIT_BOM gkb  "
                        + "where gkb.grp_kit_no=mg.group_no and "
                        + "gkb.component='" + oldPartNo + "' and "
                        + "mg.model_no not in(" + sb.toString() + " )) y "
                        + "where x.GRP_KIT_NO=y.GRP_KIT_NO order by x.model_no");*/
                String query = ("select  y.model_no,y.GRP_KIT_NO from "
                        + "(select distinct gkb.GRP_KIT_NO,mg.model_no "
                        + "from cat_model_group(NOLOCK) mg,CAT_GROUP_KIT_BOM(NOLOCK) gkb  "
                        + "where gkb.grp_kit_no=mg.group_no and "
                        + "gkb.component='" + oldPartNo + "' and "
                        + "mg.model_no in(" + sb.toString() + " ))  x,"
                        + "( select distinct gkb.GRP_KIT_NO,mg.model_no from "
                        + " cat_model_group(NOLOCK) mg,CAT_GROUP_KIT_BOM(NOLOCK) gkb  "
                        + "where gkb.grp_kit_no=mg.group_no and "
                        + "gkb.component='" + oldPartNo + "' and "
                        + "mg.model_no not in(" + sb.toString() + " )) y "
                        + "where x.GRP_KIT_NO=y.GRP_KIT_NO order by x.model_no");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                while (rs.next())
                {

                    affectedModelCounter++;

                    otherAffectedGroup = rs.getString(2);

                    if (!affectedGroupList.contains(otherAffectedGroup))
                    {
                        affectedGroupList.add(otherAffectedGroup);

                        newGroupName = generateNewGroupNumber(otherAffectedGroup, conn, realPath);

                        pstmt2.setString(1, newGroupName);
                        pstmt2.setString(2, otherAffectedGroup);
                        pstmt2.addBatch();

                        pstmt4.setString(1, newGroupName);
                        pstmt4.setString(2, otherAffectedGroup);
                        pstmt4.addBatch();

                        pstmt1.setString(1, newGroupName);
                        pstmt1.setString(2, otherAffectedGroup);
                        pstmt1.addBatch();

                    }

                    pstmt.setString(1, newGroupName);
                    pstmt.setString(2, rs.getString(1));
                    pstmt.setString(3, otherAffectedGroup);
                    pstmt.addBatch();

                    if (affectedModelCounter % 200 == 0)
                    {
                        pstmt.executeBatch();
                        pstmt1.executeBatch();
                        pstmt2.executeBatch();
                        pstmt4.executeBatch();
                    }
                }

                pstmt.executeBatch();
                pstmt1.executeBatch();
                pstmt2.executeBatch();
                pstmt4.executeBatch();
            }


            pstmt = conn.prepareStatement("select part_no from cat_part(NOLOCK) where part_no=?");
            pstmt1 = conn.prepareStatement("update CAT_GROUP_KIT_BOM set TDATE=? where component=? and index_no=? and SEQUENCE=? and grp_kit_no=?");
            pstmt2 = conn.prepareStatement("update CAT_GROUP_KIT_BOM set index_no=index_no+1 where index_no>? and grp_kit_no=?");
            pstmt3 = conn.prepareStatement("insert into CAT_GROUP_KIT_BOM(GRP_KIT_NO,COMPONENT,COMP_TYPE,QTY,AS_REQUIRED,SEQUENCE,FDATE,TDATE,INDEX_NO,REMARKS,INTERCHANGEABILITY,CUT_OFF_CHASSIS,CUT_OFF) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt4 = conn.prepareStatement("insert into CAT_ECN_IMPL_HISTORY(GROUP_ASSY_NO,GRP_ASM_TYPE,NEW_PART,CHANGE_TYPE,OLD_PART,STATUS,QTY,EFFECTIVE_DATE,EFFECTIVE_TSN,UPDATION_DATE,MODIFIED_BY,ECN_NO) values(?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt5 = conn.prepareStatement("select  GROUP_ASSY_NO from CAT_ECN_IMPL_HISTORY(NOLOCK)  where GROUP_ASSY_NO=? and NEW_PART=? and OLD_PART=?");
            pstmt6 = conn.prepareStatement("select distinct x.model_no,y.model_no from (select distinct gkb.GRP_KIT_NO,mg.model_no from cat_model_group(NOLOCK) mg,CAT_GROUP_KIT_BOM(NOLOCK) gkb  where gkb.grp_kit_no=mg.group_no and gkb.component='" + oldPartNo + "' and mg.model_no='" + modelNo + "' ) x ,( select distinct gkb.GRP_KIT_NO,mg.model_no from cat_model_group(NOLOCK) mg,CAT_GROUP_KIT_BOM(NOLOCK) gkb  where gkb.grp_kit_no=mg.group_no and gkb.component='" + oldPartNo + "' and mg.model_no<>'" + modelNo + "' ) y where x.GRP_KIT_NO=y.GRP_KIT_NO order by x.model_no");



           // rs = stmt.executeQuery("select distinct gkb.GRP_KIT_NO,gkb.COMP_TYPE,gkb.QTY,gkb.AS_REQUIRED,gkb.SEQUENCE,gkb.INDEX_NO,gkb.REMARKS,gkb.INTERCHANGEABILITY,gkb.CUT_OFF_CHASSIS,gkb.CUT_OFF from cat_model_group mg,CAT_GROUP_KIT_BOM gkb  where gkb.grp_kit_no=mg.group_no and gkb.component='" + oldPartNo + "' and mg.model_no='" + modelNo + "' order by gkb.grp_kit_no,gkb.INDEX_NO");
            String query = ("select distinct gkb.GRP_KIT_NO,gkb.COMP_TYPE,gkb.QTY,gkb.AS_REQUIRED,gkb.SEQUENCE,gkb.INDEX_NO,gkb.REMARKS,gkb.INTERCHANGEABILITY,gkb.CUT_OFF_CHASSIS,gkb.CUT_OFF from cat_model_group(NOLOCK) mg,CAT_GROUP_KIT_BOM(NOLOCK) gkb  where gkb.grp_kit_no=mg.group_no and gkb.component='" + oldPartNo + "' and mg.model_no='" + modelNo + "' order by gkb.grp_kit_no,gkb.INDEX_NO");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next())
            {

                index_no = rs.getInt("index_no");
                sequenceNo = rs.getString("SEQUENCE");
                groupno = rs.getString("grp_kit_no");
                qty = rs.getString("qty");
                as_Required = rs.getString("AS_REQUIRED");
                comp_type = rs.getString("comp_type");
              //  interchangeability = rs.getString("INTERCHANGEABILITY");
             //   cutoffchassis = rs.getString("CUT_OFF_CHASSIS");
             //   cutoff = rs.getString("CUT_OFF");


                pstmt5.setString(1, groupno);
                if (changeType.equals("Replaced By"))
                {
                    pstmt5.setString(2, newPartNo);
                }
                else
                {
                    pstmt5.setString(2, "");
                }
                pstmt5.setString(3, oldPartNo);
                rs1 = pstmt5.executeQuery();
                if (rs1.next())
                {
                    exist.add(groupno);
                }
                else
                {

                    if (changeType.equals("Replaced By"))
                    {

                        pstmt.setString(1, newPartNo);
                        rs1 = pstmt.executeQuery();
                        if (!rs1.next())
                        {
                            message = "ECN of the " + PageTemplate.MODEL_NO + " \"" + modelNo + "\" can't be implemented as New Part \"" + newPartNo + "\" doesn't exist in database, Please add part in database.";
                            return message;
                        }
                        else
                        {
                            if (option.equals("implement"))
                            {
                                rs1 = pstmt6.executeQuery();
                                if (rs1.next())
                                {
                                    isMoreModelAffected = true;
                                    affectedModelList = new ArrayList();

                                    do
                                    {
                                        tempModel = rs1.getString(1);
                                        lv = new UtilityOptionBean(tempModel, tempModel);
                                        if (!affectedModelList.contains(lv))
                                        {
                                            affectedModelList.add(lv);
                                        }


                                        tempModel = rs1.getString(2);
                                        lv = new UtilityOptionBean(tempModel, tempModel);
                                        if (!affectedModelList.contains(lv))
                                        {
                                            affectedModelList.add(lv);
                                        }
                                    }
                                    while (rs1.next());
                                }
                                else
                                {

                                    pstmt1.setDate(1, effDateSql);
                                    pstmt1.setString(2, oldPartNo);
                                    pstmt1.setInt(3, index_no);
                                    pstmt1.setString(4, sequenceNo);
                                    pstmt1.setString(5, groupno);
                                    pstmt1.addBatch();
//
                                    pstmt2.setInt(1, index_no);
                                    pstmt2.setString(2, groupno);
                                    pstmt2.addBatch();
                                    //
                                    // GRP_KIT_NO,COMPONENT,COMP_TYPE,QTY,AS_REQUIRED,SEQUENCE,FDATE,TDATE,INDEX_NO,REMARKS

                                    pstmt3.setString(1, groupno);
                                    pstmt3.setString(2, newPartNo.toUpperCase());
                                    pstmt3.setString(3, comp_type);
                                    pstmt3.setString(4, qty);
                                    pstmt3.setString(5, as_Required);
                                    pstmt3.setString(6, sequenceNo);
                                    pstmt3.setDate(7, effDateSql);
                                    pstmt3.setString(8, null);
                                    pstmt3.setInt(9, ++index_no);
                                    pstmt3.setString(10, grpRemark);
                                    pstmt3.setString(11, interchangeStatus);
                                    pstmt3.setString(12,effectiveTSN);
                                    pstmt3.setDate(13,effDateSql );
                                    pstmt3.addBatch();


                                    // GROUP_ASSY_NO,GRP_ASM_TYPE,NEW_PART,CHANGE_TYPE,OLD_PART,STATUS,QTY,EFFECTIVE_DATE,EFFECTIVE_TSN,UPDATION_DATE,MODIFIED_BY,INTERCHANGE_AFFECTED,CONTD_FOR_SERVIVE


                                    pstmt4.setString(1, groupno);
                                    pstmt4.setString(2, "GRP");
                                    pstmt4.setString(3, newPartNo.toUpperCase());
                                    pstmt4.setString(4, "Replaced");
                                    pstmt4.setString(5, oldPartNo);
                                    pstmt4.setString(6, interchangeStatus);
                                    pstmt4.setString(7, qty);
                                    pstmt4.setDate(8, effDateSql);
                                    pstmt4.setString(9, effectiveTSN);
                                    pstmt4.setDate(10, todayDateSql);
                                    pstmt4.setString(11, userCode);
                                    pstmt4.setString(12, ecnNo);
                                    pstmt4.addBatch();

                                }

                            }
                            else
                            {

                                pstmt1.setDate(1, effDateSql);
                                pstmt1.setString(2, oldPartNo);
                                pstmt1.setInt(3, index_no);
                                pstmt1.setString(4, sequenceNo);
                                pstmt1.setString(5, groupno);
                                pstmt1.addBatch();
//
                                pstmt2.setInt(1, index_no);
                                pstmt2.setString(2, groupno);
                                pstmt2.addBatch();
                                //
                                // GRP_KIT_NO,COMPONENT,COMP_TYPE,QTY,AS_REQUIRED,SEQUENCE,FDATE,TDATE,INDEX_NO,REMARKS

                                pstmt3.setString(1, groupno);
                                pstmt3.setString(2, newPartNo.toUpperCase());
                                pstmt3.setString(3, comp_type);
                                pstmt3.setString(4, qty);
                                pstmt3.setString(5, as_Required);
                                pstmt3.setString(6, sequenceNo);
                                pstmt3.setDate(7, effDateSql);
                                pstmt3.setString(8, null);
                                pstmt3.setInt(9, ++index_no);
                                pstmt3.setString(10, grpRemark);
                                pstmt3.setString(11,interchangeStatus );
                                pstmt3.setString(12,effectiveTSN);
                                pstmt3.setDate(13,effDateSql );
                                pstmt3.addBatch();


                                // GROUP_ASSY_NO,GRP_ASM_TYPE,NEW_PART,CHANGE_TYPE,OLD_PART,STATUS,QTY,EFFECTIVE_DATE,EFFECTIVE_TSN,UPDATION_DATE,MODIFIED_BY,INTERCHANGE_AFFECTED,CONTD_FOR_SERVIVE


                                pstmt4.setString(1, groupno);
                                pstmt4.setString(2, "GRP");
                                pstmt4.setString(3, newPartNo.toUpperCase());
                                pstmt4.setString(4, "Replaced");
                                pstmt4.setString(5, oldPartNo);
                                pstmt4.setString(6, interchangeStatus);
                                pstmt4.setString(7, qty);
                                pstmt4.setDate(8, effDateSql);
                                pstmt4.setString(9, effectiveTSN);
                                pstmt4.setDate(10, todayDateSql);
                                pstmt4.setString(11, userCode);
                                pstmt4.setString(12, ecnNo);
                                pstmt4.addBatch();

                            }

                        }

                    }
                    else if (changeType.equals("Deleted"))
                    {
                        if (option.equals("implement"))
                        {
                            rs1 = pstmt6.executeQuery();
                            if (rs1.next())
                            {
                                isMoreModelAffected = true;
                                affectedModelList = new ArrayList();

                                do
                                {
                                    tempModel = rs1.getString(1);
                                    lv = new UtilityOptionBean(tempModel, tempModel);
                                    if (!affectedModelList.contains(lv))
                                    {
                                        affectedModelList.add(lv);
                                    }


                                    tempModel = rs1.getString(2);
                                    lv = new UtilityOptionBean(tempModel, tempModel);
                                    if (!affectedModelList.contains(lv))
                                    {
                                        affectedModelList.add(lv);
                                    }
                                }
                                while (rs1.next());
                            }
                            else
                            {
                                pstmt1.setDate(1, effDateSql);
                                pstmt1.setString(2, oldPartNo);
                                pstmt1.setInt(3, index_no);
                                pstmt1.setString(4, sequenceNo);
                                pstmt1.setString(5, groupno);
                                pstmt1.addBatch();


                                pstmt4.setString(1, groupno);
                                pstmt4.setString(2, "GRP");
                                pstmt4.setString(3, "");
                                pstmt4.setString(4, changeType);
                                pstmt4.setString(5, oldPartNo);
                                pstmt4.setString(6, interchangeStatus);
                                pstmt4.setString(7, qty);
                                pstmt4.setDate(8, effDateSql);
                                pstmt4.setString(9, effectiveTSN);
                                pstmt4.setDate(10, todayDateSql);
                                pstmt4.setString(11, userCode);
                                pstmt4.setString(12, ecnNo);
                                pstmt4.addBatch();

                            }
                        }
                        else
                        {

                            pstmt1.setDate(1, effDateSql);
                            pstmt1.setString(2, oldPartNo);
                            pstmt1.setInt(3, index_no);
                            pstmt1.setString(4, sequenceNo);
                            pstmt1.setString(5, groupno);
                            pstmt1.addBatch();


                            pstmt4.setString(1, groupno);
                            pstmt4.setString(2, "GRP");
                            pstmt4.setString(3, "");
                            pstmt4.setString(4, changeType);
                            pstmt4.setString(5, oldPartNo);
                            pstmt4.setString(6, interchangeStatus);
                            pstmt4.setString(7, qty);
                            pstmt4.setDate(8, effDateSql);
                            pstmt4.setString(9, effectiveTSN);
                            pstmt4.setDate(10, todayDateSql);
                            pstmt4.setString(11, userCode);
                            pstmt4.setString(12, ecnNo);
                            pstmt4.addBatch();
                        }

                    }
                    counter++;

                    if (counter % 200 == 0)
                    {
                        pstmt.executeBatch();
                        pstmt1.executeBatch();
                        pstmt2.executeBatch();
                        pstmt3.executeBatch();
                        pstmt4.executeBatch();
                    }

                }
            }

            if (isMoreModelAffected)
            {
                message = "MoreModelAffected";
                ecnForm.setAffectedModelList(affectedModelList);
                return message;
            }

            if (counter == 0 && exist.size() == 0)
            {
                message = "ECN of the " + PageTemplate.MODEL_NO + " \"" + modelNo + "\" can't be implemented as Old Part \"" + oldPartNo + "\" doesn't exist in BOM.";
                return message;
            }
            else if (counter == 0 && exist.size() > 0)
            {
                message = "ECN of the " + PageTemplate.MODEL_NO + " \"" + modelNo + "\" has already been Implemented.";//has not been implemented as It
                return message;
            }
            //ecnForm.setModelList(data);
            pstmt.executeBatch();
            pstmt1.executeBatch();
            pstmt2.executeBatch();
            pstmt3.executeBatch();
            pstmt4.executeBatch();

            conn.commit();
            message = "success";
            ecnForm.setExistList(exist);
            ecnForm.setCounter(counter);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            message = "ECN has not been implemented , Please contact System Administrator.";
            try
            {
                conn.rollback();
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
        finally
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (pstmt != null)
            {
                pstmt.close();
            }
            if (pstmt1 != null)
            {
                pstmt1.close();
            }
            if (pstmt2 != null)
            {
                pstmt2.close();
            }
            if (pstmt3 != null)
            {
                pstmt3.close();
            }
            if (pstmt4 != null)
            {
                pstmt4.close();
            }
            if (pstmt5 != null)
            {
                pstmt5.close();
            }
            if (pstmt6 != null)
            {
                pstmt6.close();
            }
        }

        return message;

    }

    public Vector getApplicationType(Connection conn) throws Exception
    {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //stmt = conn.createStatement();
        String query = "";
        Vector ApplicationTypeVec = new Vector();
        //getting Level Description of Model from the database.
        query = "Select distinct(APPLICATION_TYPE) from CAT_MODEL_CLASSIFICATION(NOLOCK) order by APPLICATION_TYPE";
        //stmt = conn.createStatement();
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //rs = stmt.executeQuery(query);
        while (rs.next())
        {
            ApplicationTypeVec.add(rs.getString(1));
        }
        //////System.out.println("levelValVec:" + levelValVec);
        rs.close();
        stmt.close();
        return ApplicationTypeVec;
    }

    public Vector getGrupNoByModel_group(String modelno, Connection conn) throws Exception
    {
        ResultSet rs;
        Vector selectedgroups = new Vector();
        //Statement stmt = null;
        PreparedStatement stmt = null;
        String query2 = "Select GROUP_NO from cat_MODEL_GROUP(NOLOCK) where MODEL_NO='" + modelno + "' order by  GROUP_SEQUENCE";
        //stmt = conn.createStatement();
        stmt = conn.prepareStatement(query2);
        rs = stmt.executeQuery();
        //rs = stmt.executeQuery(query2);
        if (rs != null)
        {
            while (rs.next())
            {
                selectedgroups.add(new String(rs.getString(1)));

            }
        }
        rs.close();
        stmt.close();
        return selectedgroups;
    }

    public int getGroupMap(String grpno, Connection conn) throws Exception
    {
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
                String GROUP_MAP = rs.getString(1);
                sts = 1;
            }
            else
            {
                sts = 0;
            }
            rs.close();
            st.close();

        }
        catch (Exception e)
        {

            e.printStackTrace();
        }
        return sts;
    }

    public String getModelLevelValue(String model_no, Connection conn) throws Exception
    {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        String param_val = "";
        //getting Level Values of Model Description from the database.
        //rs = st.executeQuery("Select ENGINE_SERIES from CAT_MODEL_CLASSIFICATION where MODEL_NO='" + model_no + "'");
        String query = ("Select ENGINE_SERIES from CAT_MODEL_CLASSIFICATION(NOLOCK) where MODEL_NO='" + model_no + "'");
        st = conn.prepareStatement(query);
        rs = st.executeQuery();
        if (rs.next())
        {
            param_val = rs.getString(1);
        }
        rs.close();
        st.close();
        //////System.out.println("param_val:" + param_val);
        return param_val;
    }

    public ArrayList<String> getAggregatesValue(Connection conn)
    {
        //Statement st = null;
    	PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<String> data = null;
        try
        {
           // st = conn.createStatement();
            rs = null;
            data = new ArrayList<String>();
            //rs = st.executeQuery("Select aggregateName from VARIANT_AGGREGATE_MASTER order by aggregateName");
           String query = ("Select aggregateName from VARIANT_AGGREGATE_MASTER(NOLOCK) order by aggregateName");
           st = conn.prepareStatement(query);
           rs = st.executeQuery();
            while (rs.next())
            {
                data.add(rs.getString(1));
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
                if (rs != null)
                {
                    rs.close();
                }
                if (st != null)
                {
                    st.close();
                }
            }
            catch (Exception e1)
            {
            }
        }
        return data;
    }

    public String insertVariantAggregates(Connection conn, EAMG_Variant_AggregatesActionForm form)
    {
        PreparedStatement psmt = null, psmt1 = null, psmt2 = null;
        ResultSet rs = null;
        String model_no = null;
        String aggregateName = null;
        String aggregateValue = null;
        String result = "fail";
        try
        {
            psmt = conn.prepareStatement("insert into VARIANT_AGGREGATES (variant,spec_name,spec_value) values(?,?,?)");
            psmt1 = conn.prepareStatement("update VARIANT_AGGREGATES set spec_value=? where variant=? and spec_name=?");
            psmt2 = conn.prepareStatement("select variant from VARIANT_AGGREGATES(NOLOCK) where variant=? and spec_name=?");

            for (int i = 0; i < form.getAttachedlist().length; i++)
            {
                model_no = form.getModel_no();
                aggregateName = form.getAttachedlist()[i];
                aggregateValue = form.getAttachedlistvalue()[i];
                psmt2.setString(1, model_no);
                psmt2.setString(2, aggregateName);
                rs = psmt2.executeQuery();
                if (rs.next())
                {
                    psmt1.setString(1, aggregateValue);
                    psmt1.setString(2, model_no);
                    psmt1.setString(3, aggregateName);
                    psmt1.addBatch();
                }
                else
                {
                    psmt.setString(1, model_no);
                    psmt.setString(2, aggregateName);
                    psmt.setString(3, aggregateValue);
                    psmt.addBatch();
                }
            }
            psmt.executeBatch();
            psmt1.executeBatch();
            result = model_no;
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
                if (psmt != null)
                {
                    psmt.close();
                }
                if (psmt1 != null)
                {
                    psmt1.close();
                }
                if (psmt2 != null)
                {
                    psmt2.close();
                }
            }
            catch (Exception e1)
            {
            }
            rs = null;
            psmt = null;
            psmt1 = null;
            psmt2 = null;
        }
        return result;
    }

    public String getAggregateName(String AggregateName, Connection conn) throws Exception
    {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;

        String result = "notpresent";
        //////System.out.println("i am in isCompPresent");
        //checking whether the Component exists as Model or not.
        String query = "Select * from VARIANT_AGGREGATE_MASTER(NOLOCK) where aggregateName='" + AggregateName + "'";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //stmt = conn.createStatement();
       // rs = stmt.executeQuery(query);
        if (rs.next())
        {
            result = "'" + AggregateName + "' exists as Aggregate Name in Database.";
            return result;
        }
        rs.close();
        stmt.close();
        return result;
    }

    public boolean insertVehicleData(ArrayList data, Connection conn) throws Exception
    {
        ResultSet rs = null;
        PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null;
        ArrayList<EAMG_Variant_AggregatesActionForm> formData = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/mmm/yy");
        java.util.Date tempD = null;
        java.sql.Date dateS = null;
        boolean isVehicleInserted = false;


        try
        {
            formData = (ArrayList) data.get(1);
            pstmt1 = conn.prepareStatement("select  VEHICLE_NO from  cat_vehicle_sono(NOLOCK) where VEHICLE_NO=?");
            pstmt2 = conn.prepareStatement("update cat_vehicle_sono set description=?,status=? where VEHICLE_NO=?");
            pstmt = conn.prepareStatement("insert into cat_vehicle_sono(VEHICLE_NO,SO_NO,Mfg_Date,description,status) values(?,?,?,?,?)");

            for (EAMG_Variant_AggregatesActionForm form : formData)
            {
                pstmt1.setString(1, form.getVehiclenumber());
                rs = pstmt1.executeQuery();
                if (rs.next())
                {
                    pstmt2.setString(1, form.getDescription());
                    pstmt2.setString(2, form.getStatus());
                    pstmt2.setString(3, form.getVehiclenumber());
                    pstmt2.addBatch();
                }
                else
                {
                    pstmt.setString(1, form.getVehiclenumber());
                    pstmt.setString(2, form.getVariantname());
                    String s = form.getMfgdate();
                    tempD = df.parse(form.getMfgdate());
                    dateS = new java.sql.Date(tempD.getTime());
                    pstmt.setDate(3, dateS);
                    pstmt.setString(4, form.getDescription());
                    pstmt.setString(5, form.getStatus());
                    pstmt.addBatch();
                }
            }
            pstmt.executeBatch();
            pstmt2.executeBatch();
            isVehicleInserted = true;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            isVehicleInserted = false;
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }
                if (pstmt1 != null)
                {
                    pstmt1.close();
                }
                if (pstmt2 != null)
                {
                    pstmt2.close();
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return isVehicleInserted;
    }

    public ArrayList<String> getEngineModelByEngineSeries(String engineSeries, Connection conn)
    {
        ResultSet rs = null;
        //Statement st = null;
        PreparedStatement st = null;
        String sql = null;
        ArrayList<String> data = new ArrayList<String>();
        try
        {
            //st = conn.createStatement();
            sql = "Select distinct ENGINE_MODEL from CAT_MODEL_CLASSIFICATION(NOLOCK) where  ENGINE_SERIES='" + engineSeries + "' order by ENGINE_MODEL";
          st = conn.prepareStatement(sql);
          rs = st.executeQuery();
            // rs = st.executeQuery(sql);
            // System.out.println("sql"+sql);
            while (rs.next())
            {
                data.add(rs.getString(1));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error("Caught in Exception", e);
        }
        finally
        {
            try
            {
                if (st != null)
                {
                    st.close();
                    st = null;
                }

            }
            catch (Exception e)
            {
                logger.error("Caught in Final Exception", e);
            }
        }
        return data;

    }

    public Vector getEngineModelByEngineSeriesModel(String engineSeries, String model_no, Connection conn)
    {
        ResultSet rs = null;
        //Statement st = null;
        PreparedStatement st = null;
        String sql = null;
        Vector data = new Vector();
        try
        {
            //st = conn.createStatement();
            sql = "Select  ENGINE_MODEL,APPLICATION_TYPE from CAT_MODEL_CLASSIFICATION(NOLOCK) where  ENGINE_SERIES='" + engineSeries + "' and MODEL_NO ='" + model_no + "' order by ENGINE_MODEL";
           st = conn.prepareStatement(sql);
           rs = st.executeQuery();
            //rs = st.executeQuery(sql);
            //System.out.println("sql" + sql);
            while (rs.next())
            {
                data.add(rs.getString(1));
                data.add(rs.getString(2));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error("Caught in Exception", e);
        }
        finally
        {
            try
            {
//                data.setLength(0);
//                data=null;
                if (st != null)
                {
                    st.close();
                    st = null;
                }

            }
            catch (Exception e)
            {
                logger.error("Caught in Final Exception", e);
            }
        }
        return data;

    }
}
