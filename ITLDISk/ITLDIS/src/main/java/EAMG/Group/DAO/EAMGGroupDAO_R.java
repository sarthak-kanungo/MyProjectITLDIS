/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Group.DAO;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import EAMG.Group.ActionFormBean.EAMG_GroupCreationByWzActionForm;

public class EAMGGroupDAO_R {

    Connection conn = null;
    HttpSession session = null;
    private Logger logger = Logger.getLogger(this.getClass());

    public EAMGGroupDAO_R(Connection conn) {
        this.conn = conn;
    }

    public boolean checkGroup(String groupname) {
        boolean isExist = false;
        ResultSet rs = null;
        //Statement st = null;
        PreparedStatement st = null;
        String sql = null;

        try {
            //st = conn.createStatement();
            sql = "select grp_kit_no from CAT_GROUP_KIT_DETAIL(NOLOCK) where grp_kit_no='" + groupname + "'";
           // rs = st.executeQuery(sql);
           st = conn.prepareStatement(sql);
           rs = st.executeQuery();
            if (rs.next()) {
                isExist = true;
            } else {
                isExist = false;
            }


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Caught in Exception", e);
        } finally {
            try {

                if (st != null) {
                    st.close();
                    st = null;
                }

            } catch (Exception e) {
                logger.error("Caught in Final Exception", e);
            }
        }
        return isExist;
    }

    public ArrayList<String> isCompExist(EAMG_GroupCreationByWzActionForm groupform) {
        ResultSet rs = null;
        String sql = null;
        PreparedStatement ps = null;

        ArrayList<String> result = new ArrayList<String>();
        try {
            sql = "Select part_no from CAT_PART(NOLOCK) where part_no=?";
            ps = conn.prepareStatement(sql);
            for (String part_no : groupform.getComp_no_text()) {
                ps.setString(1, part_no);
                rs = ps.executeQuery();
                if (!rs.next()) {
                    result.add(0, "NotPresent");
                    result.add(1, part_no);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Caught in Exception", e);
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                    ps = null;
                }

            } catch (Exception e) {
                logger.error("Caught in Final Exception", e);
            }
        }
        return result;
    }

    public boolean isCompExist(String compno) {
        ResultSet rs = null;
        String sql = null;
        PreparedStatement ps = null;
        try {
            sql = "Select part_no from CAT_PART(NOLOCK) where part_no=?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, compno.trim());
            rs = ps.executeQuery();
            if (!rs.next()) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Caught in Exception", e);
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                    ps = null;
                }

            } catch (Exception e) {
                logger.error("Caught in Final Exception", e);
            }
        }
        return true;
    }

    public boolean isCategoryTypeExist(String CategoryType) {
        ResultSet rs = null;
        String sql = null;
        PreparedStatement ps = null;
        try {
            //sql = "Select distinct(GROUP_TYPE) from MODEL_GROUP where GROUP_TYPE='"+CategoryType+"'";
            sql = "Select distinct(GROUP_TYPE) from CAT_MODEL_GROUP(NOLOCK) where GROUP_TYPE=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, CategoryType);
            rs = ps.executeQuery();
            if (!rs.next()) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Caught in Exception", e);
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                    ps = null;
                }

            } catch (Exception e) {
                logger.error("Caught in Final Exception", e);
            }
        }
        return true;
    }

    public ArrayList<String> insertIntoGroupComponent(EAMG_GroupCreationByWzActionForm groupform, Vector<String> sqlVec) {

        String sql = null;
        PreparedStatement ps = null, ps1 = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String groupno = groupform.getGroupname();
        String comp_type = null;
        String quantity = null;
        String asRequired = null;
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyy");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String date="";
        try {

            pst = conn.prepareStatement("select part_type from CAT_PART where part_no=?");
            ps1 = conn.prepareStatement(sqlVec.get(0));
            ps1.addBatch();

            sql = "insert into CAT_GROUP_KIT_BOM(GRP_KIT_NO,COMPONENT,COMP_TYPE,QTY,AS_REQUIRED,SEQUENCE,INDEX_NO,REMARKS,INTERCHANGEABILITY,CUT_OFF_CHASSIS,CUT_OFF)  values(?,?,?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < groupform.getComp_no_text().length; i++) {

                pst.setString(1, groupform.getComp_no_text()[i]);

                rs = pst.executeQuery();
                if (rs.next()) {
                    comp_type = rs.getString(1);
                }
             quantity=groupform.getQuantity()[i];
                if (quantity.equalsIgnoreCase("AR")) {
                    quantity = "0";
                    asRequired = "YES";
                } else {
                    asRequired = "NO";
                }

                //GRP_KIT_NO,COMPONENT,COMP_TYPE,QTY,AS_REQUIRED,SEQUENCE,REV_NO,SEQORDER,REMARK
                ps.setString(1, groupno);
                ps.setString(2, groupform.getComp_no_text()[i]);
                ps.setString(3, comp_type);
                ps.setString(4, quantity);
                ps.setString(5, asRequired);
                ps.setString(6, groupform.getSeqeno()[i]);
                ps.setInt(7, i);
                ps.setString(8, groupform.getGroup_remarks()[i]);
                ps.setString(9, groupform.getInterchangeability()[i]);
                ps.setString(10, groupform.getCutoffchassis()[i]);
                date = groupform.getCutoff()[i].trim();
                if (date.equalsIgnoreCase("")) {
                    date=null;
                } else {
                    date = df2.format(df1.parse(groupform.getCutoff()[i]).getTime());
                }
                ps.setString(11, date);
                ps.addBatch();
            }
            ps1.executeBatch();
            ps.executeBatch();
            conn.commit();

            result.add(0, "Group '" + groupno + "' is Created Successfully");
            result.add(1, "Success");

        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            result.add(0, "Unable to Add Group. Please Contact to System Administrator.");
            result.add(1, "Failure");
            logger.error("Caught in Exception", e);
        } finally {
            try {


                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (ps1 != null) {
                    ps1.close();
                    ps1 = null;
                }

            } catch (Exception e) {
                logger.error("Caught in Final Exception", e);
            }
        }
        return result;
    }

    public String getComponentDetail(String partno, String comptype) {

        ResultSet rs = null;
        //Statement st = null;
        PreparedStatement st = null;
        String sql = null;
        PreparedStatement pstmt = null;
        StringBuilder data = new StringBuilder("<listdata>");


        try {
            //st = conn.createStatement();


            if (partno.equals("*")) {
                // sql = "Select part_no from part  where part_type like ('%" + comptype + "%') order by part_no";
                sql = "Select part_no from CAT_PART(NOLOCK)  where part_type like ? order by part_no";
                pstmt = conn.prepareStatement(sql);
            } else {
                //sql = "Select part_no from part where part_no LIKE ('%" + partno + "%') and part_type like ('%" + comptype + "%') order by part_no";
                sql = "Select part_no from CAT_PART(NOLOCK) where  part_type like ? and  part_no LIKE ? order by part_no";
                pstmt = conn.prepareStatement(sql);
            }


            pstmt.setString(1,"%" + comptype + "%");

            if (!partno.equals("*")) {
                pstmt.setString(2,"%" + partno + "%");
            }
            // System.out.println("sql" + sql);
//             if(partno.equals("*"))
//                 sql="Select part_no from part order by part_no";
//             else
//                sql="Select part_no from part where  part_no LIKE '"+partno+"%' order by part_no";
//
            int counter = 0;
            rs = pstmt.executeQuery();
            while (rs.next()) {
                counter++;

                data.append(rs.getString(1) + "|");
            }
            //System.out.println(counter);
            data.append("</listdata>");


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Caught in Exception", e);
        } finally {
            try {
//                data.setLength(0);
//                data=null;
                if (st != null) {
                    st.close();
                    st = null;
                }

            } catch (Exception e) {
                logger.error("Caught in Final Exception", e);
            }
        }
        return data.toString();

    }

    public String getGroupDetail(String groupno, String comptype) {

        ResultSet rs = null;
        //Statement st = null;
        PreparedStatement st = null;
        String sql = null;
        StringBuilder data = new StringBuilder("<listdata>");


        try {
            //st = conn.createStatement();

            if (groupno.equals("*")) {
                sql = "Select grp_kit_no from CAT_GROUP_KIT_DETAIL(NOLOCK) order by grp_kit_no";
            
            	st = conn.prepareStatement(sql);
            } else {
                sql = "Select grp_kit_no from CAT_GROUP_KIT_DETAIL(NOLOCK) where grp_kit_no LIKE ('%" + groupno + "%') order by grp_kit_no";
            st = conn.prepareStatement(sql);
            }
            // System.out.println("sql" + sql);
//             if(partno.equals("*"))
//                 sql="Select part_no from part order by part_no";
//             else
//                sql="Select part_no from part where  part_no LIKE '"+partno+"%' order by part_no";
//
            int counter = 0;
           // rs = st.executeQuery(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                counter++;

                data.append(rs.getString(1) + "|");
            }
            //System.out.println(counter);
            data.append("</listdata>");


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Caught in Exception", e);
        } finally {
            try {
//                data.setLength(0);
//                data=null;
                if (st != null) {
                    st.close();
                    st = null;
                }

            } catch (Exception e) {
                logger.error("Caught in Final Exception", e);
            }
        }
        return data.toString();

    }

    public boolean upload(String fileName, String filePath, FormFile ImageFile) {
        boolean isUploaded = false;
        if (!fileName.equals("")) {
            fileName.toUpperCase().replaceAll(".JPG", ".jpg");
            File currentToolDb = new File(filePath, fileName);
            try {
                FileOutputStream fileOutStream = new FileOutputStream(currentToolDb);
                fileOutStream.write(ImageFile.getFileData());
                fileOutStream.flush();
                fileOutStream.close();
                isUploaded = true;
            } catch (Exception e) {
                e.printStackTrace();
                isUploaded = false;
                logger.error("Caught in Final Exception", e);
            }
        }
        return isUploaded;
    }

    public boolean uploadSvg(String fileName, String filePath, FormFile ImageFile) {
        boolean isUploaded = false;
        if (!fileName.equals("")) {
            File currentToolDb = new File(filePath, fileName);
            try {
                FileOutputStream fileOutStream = new FileOutputStream(currentToolDb);
                fileOutStream.write(ImageFile.getFileData());
                fileOutStream.flush();
                fileOutStream.close();
                isUploaded = true;
            } catch (Exception e) {
                e.printStackTrace();
                isUploaded = false;
                logger.error("Caught in Final Exception", e);
            }
        }
        return isUploaded;
    }
//////////amw_MethodUtility

    public Vector getGrpBomVecFromCustom(Vector GrpBomVec, String group, int PARENT_ID, Connection conn) {

        int level = 0;

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
        try {
            //stmt = conn.createStatement();
            //getting the component details for first level components from GROUP_KIT_BOM_CUSTOM.

            //query = "SELECT COMPONENT,COMP_TYPE,QTY,AS_REQUIRED,SEQUENCE,SEQORDER,REV_NO FROM GROUP_KIT_BOM WHERE GRP_KIT_NO ='" + group + "'  ORDER BY SEQORDER ";
            query = "SELECT COMPONENT,COMP_TYPE,QTY,AS_REQUIRED,SEQUENCE,INDEX_NO FROM CAT_GROUP_KIT_BOM(NOLOCK) WHERE GRP_KIT_NO ='" + group + "'  ORDER BY SEQUENCE ";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
           // rs = stmt.executeQuery(query);
            //////System.out.println("Group no:" + group);
            //////System.out.println("PARENT_ID:" + PARENT_ID);

            while (rs.next()) {
                Vector tempVec = new Vector();
                compno = rs.getString(1);
                tempVec.add(compno);//0
                comptype = rs.getString(2);
                tempVec.add(comptype);//1
                qty = rs.getString(3);
                asreq = rs.getString(4);
                if (qty.equals("0") && asreq.equalsIgnoreCase("YES")) {
                    tempVec.add("AR");
                } else {
                    tempVec.add(qty);//2
                }
                seqno = rs.getString(5);
                tempVec.add(seqno);//3
                index_no = 0;
                index_no = rs.getInt(6);
//                revno = new Integer(rs.getInt(7));
                //tempVec.add(revno);

//                if (PARENT_ID != 0) {
//                    //if PARENT_ID is not 0 then getting PARENT_ID from GROUP_KIT_BOM_CUSTOM.
//                    int ind = getIndexFromCustom(group, PARENT_ID, conn);
//                    int l = ((Integer) LevelVec.elementAt(ind - 1)).intValue();
//                    level = l + 1;
//                }
                //tempVec.add(new Integer(level));
                desc = getCompDesc(compno, conn);
                tempVec.add(desc);//5
                tempVec.add(new Integer(index_no));//6
                //////System.out.println("tempVec:" + tempVec);

                //adding the Vector to GrpBomVec Vectors.
                for (int i = 0; i < GrpBomVec.size(); i++) {
                    Vector GrpCompVec = (Vector) GrpBomVec.elementAt(i);
                    GrpCompVec.add(tempVec.elementAt(i));
                }

                if (comptype.equals("ASM")) {
                    level++;
                }
                //////System.out.println("GrpBomVec:" + GrpBomVec);
                //////System.out.println("rowid:" + rowid);

                //checking whether the subpart exists or not for a Component.
//                checkSubpart = check_desc(conn, rowid);
//                if (checkSubpart) {
//                    //////System.out.println("in checkSubpart");
//                    //////System.out.println("group:" + group + "******rowid:" + rowid + "@@@@@@@@@level:" + level);
//                    GrpBomVec = getGrpBomVecFromCustom(GrpBomVec, group, rowid, conn);
//                }

            }

            rs.close();
        } catch (Exception ae) {
            ae.printStackTrace();

        }
        return GrpBomVec;

    }

    public String getCompDesc(String compno, Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //getting the Component Description.
        String query = "Select p1 from CAT_PART(NOLOCK) where part_no='" + compno + "'";
       // stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        String desc = "";
        if (rs.next()) {
            desc = rs.getString(1);
        }

        return desc;
    }

    public int getIndexFromCustom(String grpno, int parentid, Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //getting INDEX_NO from database.
        String query = "Select INDEX_NO from GROUP_KIT_BOM_CUSTOM(NOLOCK) where GRP_KIT_NO='" + grpno + "' and ROW_ID=" + parentid;
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        int indexno = 0;
        if (rs.next()) {
            indexno = rs.getInt(1);
        }
        return indexno;
    }

    public String getCompDesc(String comp_no, String comp_type, Connection conn) throws Exception {
        //////System.out.println("comp_no:" + comp_no);
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        String comp_desc = "";
        if (comp_type.equals("GRP")) {
            ////System.out.println("comp_no:" + comp_no);
           // rs = st.executeQuery("select p1 from CAT_GROUP_KIT_DETAIL where GRP_KIT_NO='" + comp_no + "'");
        	String query = ("select p1 from CAT_GROUP_KIT_DETAIL(NOLOCK) where GRP_KIT_NO='" + comp_no + "'");
        	st = conn.prepareStatement(query);
        	rs = st.executeQuery();
            if (rs.next()) {
                comp_desc = rs.getString(1);
            }
            rs.close();
            //////System.out.println("comp_desc:" + comp_desc);
        } else {
            //rs = st.executeQuery("select p1 from CAT_PART where part_no='" + comp_no + "'");
        	String query1 =("select p1 from CAT_PART(NOLOCK) where part_no='" + comp_no + "'");
        	st = conn.prepareStatement(query1);
        	rs = st.executeQuery();
            if (rs.next()) {
                comp_desc = rs.getString(1);
            }
            rs.close();
        }

        st.close();
        return comp_desc;
    }

    public boolean check_desc(Connection conn, int rowid) {
        try {
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
            if (rs.next()) {
                checkSubpart = true;
            }
            rs.close();
            stmt.close();
            //////System.out.println("checkSubpart:" + checkSubpart);

            return checkSubpart;
        } catch (Exception e) {
            ////System.out.println(e);
            e.printStackTrace();
            return false;
        }

    }

    public String getKITDetail(String kitno) {

        ResultSet rs = null;
        //Statement st = null;
        PreparedStatement st = null;
        String sql = null;
        StringBuilder data = new StringBuilder("<listdata>");

        try {
            //st = conn.createStatement();

            if (kitno.equals("*")) {
                sql = "Select part_no from CAT_PART order(NOLOCK) by part_no where part_type='KIT'";
            } else {
                sql = "Select part_no from CAT_PART(NOLOCK) where part_type='KIT' and part_no LIKE ('%" + kitno + "%') order by part_no";
            }
            //System.out.println("sql" + sql);
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            //rs = st.executeQuery(sql);
            while (rs.next()) {
                data.append(rs.getString(1) + "|");
            }
            data.append("</listdata>");


        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Caught in Exception", e);
        } finally {
            try {
//                data.setLength(0);
//                data=null;
                if (st != null) {
                    st.close();
                    st = null;
                }

            } catch (Exception e) {
                logger.error("Caught in Final Exception", e);
            }
        }
        return data.toString();

    }
    //////////EAMG_MethodUtility

    public ArrayList getTableSeries(String fCodeValue, Connection conn) throws Exception {
        ResultSet rs;
        //Statement stmt = null;
        //stmt = conn.createStatement();
        PreparedStatement stmt = null;
        String query = "";
        ArrayList levelArrayList = new ArrayList();
        String fcodeSeries = null;
        query = "select distinct GROUP_NO,MAP_NAME,GROUP_SEQUENCE  from CAT_MODEL_GROUP(NOLOCK) where model_no='" + fCodeValue + "' order by GROUP_SEQUENCE";
        //stmt = conn.createStatement();
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //rs = stmt.executeQuery(query);
        while (rs.next()) {
            fcodeSeries = rs.getString(1);
            if (fcodeSeries != null) {
                levelArrayList.add(fcodeSeries);
                levelArrayList.add(rs.getString(2));
            }
        }
        rs.close();
        stmt.close();
        return levelArrayList;
    }

    public int updateModelGroupSequence(String[] groups, String modelNo, Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        String query = "";

        int result = 0;
        conn.setAutoCommit(false);
        try {

            query = "update CAT_MODEL_GROUP set GROUP_SEQUENCE=? where GROUP_NO=? and   model_no='" + modelNo + "'";
            pstmt = conn.prepareStatement(query);
            for (int i = 0; i < groups.length; i++) {
                pstmt.setInt(1, (i + 1));
                pstmt.setString(2, groups[i]);
                pstmt.addBatch();

                if (i % 200 == 0) {
                    pstmt.executeBatch();
                }

            }
            pstmt.executeBatch();
            result = 1;
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pstmt.close();
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }


        }

        pstmt.close();
        return result;
    }
    
    public Vector getGroupSeries(Connection conn) throws Exception
    {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //stmt = conn.createStatement();
        String query = "";
        Vector levelValVec = new Vector();
        String groupSeries = null;
        //getting Level Description of Model from the database.
        query = "Select  GRP_KIT_NO from CAT_GROUP_KIT_DETAIL(NOLOCK) order by GRP_KIT_NO";
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        while (rs.next())
        {
            groupSeries = rs.getString(1);
            if (!levelValVec.contains(groupSeries))
            {
                levelValVec.add(groupSeries);
            }
        }
        //////System.out.println("levelValVec:" + levelValVec);
        rs.close();
        stmt.close();
        return levelValVec;
    }

    
    
    public ArrayList<String> getGroupComponentSeries(String groupSeries, Connection conn) throws Exception
    {
        ResultSet rs;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        //stmt = conn.createStatement();
        String query = "";
        String component = "";
        String index_no = "";
        String sequence = "";
        ArrayList<String> levelValVec = new ArrayList();
        //getting Level Description of Model from the database.
        query = "select COMPONENT,INDEX_NO,SEQUENCE from CAT_GROUP_KIT_BOM(NOLOCK) where GRP_KIT_NO='" + groupSeries + "' order by INDEX_NO";
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();
        //stmt = conn.createStatement();
        //rs = stmt.executeQuery(query);
        while (rs.next())
        {

            component = rs.getString(1);
            index_no = rs.getString(2);
            sequence = rs.getString(3);
            sequence = sequence == null ? "-" : sequence;

            levelValVec.add(sequence + "-" + component);
            levelValVec.add(index_no + "$$##" + component + "$$##" + groupSeries);
        }
        //////System.out.println("levelValVec:" + levelValVec);
        rs.close();
        stmt.close();
        return levelValVec;
    }

     public static void deleteDir(File file) {
        try {
            if (file.isDirectory()) {
                if (file.list().length == 0) {
                    file.delete();
                } else {
                    String files[] = file.list();
                    for (String temp : files) {
                        File fileDelete = new File(file, temp);
                        deleteDir(fileDelete);
                    }
                    if (file.list().length == 0) {
                        file.delete();
                    }
                }
            } else {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int updateGroupSequence(Connection conn, String[] groupSequenceArr) throws Exception
    {
        PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null;
        int result = 0;
        ResultSet rs = null;
        String indexno = null;
        String component = null;
        String groupno = null;
        try
        {
            conn.setAutoCommit(false);

            String[] tempArr = null;
            pstmt2 = conn.prepareStatement("INSERT INTO CAT_GROUP_KIT_BOM(GRP_KIT_NO, COMPONENT, COMP_TYPE, QTY, AS_REQUIRED, SEQUENCE, "
                    + "STICKER_FILE, FDATE, TDATE, INDEX_NO, REMARKS,INTERCHANGEABILITY,CUT_OFF_CHASSIS,CUT_OFF)"
                    + " VALUES(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)");

            pstmt1 = conn.prepareStatement("SELECT GRP_KIT_NO, COMPONENT, COMP_TYPE, QTY, AS_REQUIRED, SEQUENCE,STICKER_FILE, FDATE, TDATE, INDEX_NO, REMARKS,INTERCHANGEABILITY,CUT_OFF_CHASSIS,CUT_OFF FROM CAT_GROUP_KIT_BOM where COMPONENT=? and INDEX_NO=? and GRP_KIT_NO=?");
            pstmt = conn.prepareStatement("delete from  CAT_GROUP_KIT_BOM where GRP_KIT_NO=?");

            for (int i = 0; i < groupSequenceArr.length; i++)
            {

                tempArr = groupSequenceArr[i].split("\\$\\$##");
                indexno = tempArr[0];
                component = tempArr[1];
                groupno = tempArr[2];

                pstmt1.setString(1, component);
                pstmt1.setString(2, indexno);
                pstmt1.setString(3, groupno);

                rs = pstmt1.executeQuery();
                if (rs.next())
                {
                    pstmt2.setString(1, rs.getString(1));
                    pstmt2.setString(2, rs.getString(2));
                    pstmt2.setString(3, rs.getString(3));
                    pstmt2.setString(4, rs.getString(4));
                    pstmt2.setString(5, rs.getString(5));
                    pstmt2.setString(6, rs.getString(6));
                    pstmt2.setString(7, rs.getString(7));
                    pstmt2.setDate(8, rs.getDate(8));
                    pstmt2.setDate(9, rs.getDate(9));
                    pstmt2.setInt(10, (i + 1));
                    pstmt2.setString(11, rs.getString(11));
                    pstmt2.setString(12, rs.getString(12));
                    pstmt2.setString(13, rs.getString(13));
                    pstmt2.setString(14, rs.getString(14));
                    pstmt2.addBatch();
                }
            }

            pstmt.setString(1, groupno);
            pstmt.executeUpdate();
            conn.commit();

            pstmt2.executeBatch();
            conn.commit();
            pstmt.close();
            pstmt1.close();
            pstmt2.close();
            result = 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                conn.rollback();
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }

        return result;
        //////System.out.println("levelValVec:" + levelValVec);
    }
}
