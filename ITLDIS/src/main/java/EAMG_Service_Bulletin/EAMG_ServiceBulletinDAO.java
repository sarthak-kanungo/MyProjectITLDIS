/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG_Service_Bulletin;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author satyaprakash.verma
 */
public class EAMG_ServiceBulletinDAO {

    public static void getYears(EAMG_ServiceBulletinBean sbForm) {
        ArrayList<LabelValueBean> yearList = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sbForm.setIssueDate(sdf.format(new java.util.Date()));
            ArrayList<Date> dates = new ArrayList<Date>();
            yearList = new ArrayList<LabelValueBean>();
            String currentYear = sdf.format(new java.util.Date());
            String[] currentyear = currentYear.split("/");
            String currentYearNew = currentyear[2];
            sbForm.setYearOfIssue(Integer.parseInt(currentYearNew));
            DateFormat formatter;
            LabelValueBean lv = null;
            formatter = new SimpleDateFormat("dd/MM/yyyy");
            String str_date = PageTemplate.startYearOfIssue;
            Date startDate = (Date) formatter.parse(str_date);
            Date endDate = new Date();
            long interval = 24 * 1000 * 60 * 60;
            long endTime = endDate.getTime();
            long curTime = startDate.getTime();
            while (curTime <= endTime) {
                dates.add(new Date(curTime));
                curTime += interval;
            }
            for (int i = 0; i < dates.size(); i++) {
                Date lDate = (Date) dates.get(i);
                String mainDate = formatter.format(lDate);
                String[] year = mainDate.split("/");
                String newYear = year[2];
                lv = new LabelValueBean(newYear, newYear);
                if (!yearList.contains(lv)) {
                    yearList.add(lv);
                }
            }
            sbForm.setYearList(yearList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean generateSBNo(EAMG_ServiceBulletinBean sbForm, Connection conn) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        int value = 0;
        try {
            ps = conn.prepareStatement("SELECT max(ID) FROM CAT_BULLETIN(NOLOCK)");
            rs = ps.executeQuery();
            if (rs.next()) {
                value = rs.getInt(1);
            }
            rs.close();
            value = value + 1;
            sbForm.setSbID(value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static int updateServiceBulletin(EAMG_ServiceBulletinBean sbForm, Connection con) {
        PreparedStatement psmt = null;
        int result = 3;
        try {
            con.setAutoCommit(false);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            psmt = con.prepareStatement("insert into CAT_BULLETIN values(?,?,?,?,?,?,?,?)");
            psmt.setInt(1, sbForm.getSbID());
            psmt.setInt(2, sbForm.getYearOfIssue());
            psmt.setString(3, sbForm.getType());
            psmt.setDate(4, new java.sql.Date(sdf.parse(sbForm.getIssueDate()).getTime()));
            psmt.setString(5, sbForm.getSubject());
            psmt.setString(6, sbForm.getFormFileName());
             psmt.setString(7, sbForm.getFormFileName());
              psmt.setDate(8, new java.sql.Date(new java.util.Date().getTime()));
            psmt.executeUpdate();
            con.commit();
            result = 1;
        } catch (Exception e) {
            try {
                con.rollback();
                result = 2;
            } catch (Exception e2) {
            }
            e.printStackTrace();
        } finally {
            try {
                if (psmt != null) {
                    psmt.close();
                    psmt = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return result;
    }

    public static boolean uploadSBDocs(EAMG_ServiceBulletinBean sbForm, String ecatPath) {
        boolean isUploaded = false;
        FormFile formFile1 = sbForm.getDocument();
        String sbID = Integer.toString(sbForm.getSbID());
        String extension = "";
        File f = new File(ecatPath + "dealer/bulletin");
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            if (formFile1.getFileName() != null) {
                int i = formFile1.getFileName().lastIndexOf('.');
                if (i > 0) {
                    extension = formFile1.getFileName().substring(i + 1);
                }
            }
            FileOutputStream fos = null;
            if (formFile1 != null && formFile1.getFileName() != null && !formFile1.getFileName().equals("")) {
                sbForm.setFormFileName(sbID + "." + extension);
                fos = new FileOutputStream(ecatPath + "dealer/bulletin/" + sbID + "." + extension);
                fos.write(formFile1.getFileData());
                fos.flush();
                fos.close();
            }
            isUploaded = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUploaded;
    }

    public static void viewOFBulletinDetails(EAMG_ServiceBulletinBean sbForm, Connection con) {
       // Statement stmt = null;
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
           // stmt = con.createStatement();
            Vector<String> years = new Vector<String>();
            Vector<String> years_type = new Vector<String>();
            //rs = stmt.executeQuery("SELECT distinct ISSUE_YEAR FROM CAT_BULLETIN order by ISSUE_YEAR");
            String query = ("SELECT distinct ISSUE_YEAR FROM CAT_BULLETIN(NOLOCK) order by ISSUE_YEAR");
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                years.addElement(Integer.toString(rs.getInt("ISSUE_YEAR")));
            }
            rs.close();
            sbForm.setYearsVectorList(years);
            //rs = stmt.executeQuery("SELECT distinct ISSUE_YEAR,BULLETIN_TYPE FROM CAT_BULLETIN order by ISSUE_YEAR");
            String query1 = ("SELECT distinct ISSUE_YEAR,BULLETIN_TYPE FROM CAT_BULLETIN(NOLOCK) order by ISSUE_YEAR");
            stmt = con.prepareStatement(query1);
            rs = stmt.executeQuery();
            while (rs.next()) {
                years_type.addElement(Integer.toString(rs.getInt("ISSUE_YEAR")));
                years_type.addElement(rs.getString("BULLETIN_TYPE"));
            }
            rs.close();
            sbForm.setYearsTypeVectorList(years_type);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void viewOFBulletinSubjectDetails(EAMG_ServiceBulletinBean sbForm, Connection con) {
        //Statement stmt = null;
        PreparedStatement stmt = null;
    	ResultSet rs = null;
        EAMG_ServiceBulletinBean formData = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
        try {
            //stmt = con.createStatement();
            ArrayList dataList = new ArrayList();
            //rs = stmt.executeQuery("SELECT distinct HEADING,DOC_NAME,ISSUE_DATE FROM CAT_BULLETIN where ISSUE_YEAR = " + sbForm.getYearOfIssue() + " and BULLETIN_TYPE = '" + sbForm.getType() + "' order by ISSUE_DATE desc ");
            String query = ("SELECT distinct HEADING,DOC_NAME,ISSUE_DATE FROM CAT_BULLETIN(NOLOCK) where ISSUE_YEAR = " + sbForm.getYearOfIssue() + " and BULLETIN_TYPE = '" + sbForm.getType() + "' order by ISSUE_DATE desc ");
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                formData = new EAMG_ServiceBulletinBean();
                formData.setSubject(rs.getString("HEADING"));
                formData.setFormFileName(rs.getString("DOC_NAME"));
                formData.setIssueDate(sdf.format(rs.getDate("ISSUE_DATE")));
                dataList.add(formData);
            }
            rs.close();
            sbForm.setDataList(dataList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void getAllBulletin(EAMG_ServiceBulletinBean sbForm, Connection con) {
        //Statement stmt = null;
        PreparedStatement stmt = null;
    	ResultSet rs = null;
        try {
            //stmt = con.createStatement();
            ArrayList dataList = new ArrayList();
            EAMG_ServiceBulletinBean formData = null;
            //rs = stmt.executeQuery("SELECT distinct ID,HEADING,DOC_NAME,ISSUE_DATE FROM CAT_BULLETIN where ISSUE_YEAR = " + sbForm.getYearOfIssue() + " and BULLETIN_TYPE = '" + sbForm.getType() + "' order by ISSUE_DATE ");
            String query = ("SELECT distinct ID,HEADING,DOC_NAME,ISSUE_DATE FROM CAT_BULLETIN(NOLOCK) where ISSUE_YEAR = " + sbForm.getYearOfIssue() + " and BULLETIN_TYPE = '" + sbForm.getType() + "' order by ISSUE_DATE ");
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                formData = new EAMG_ServiceBulletinBean();
                formData.setSbID(rs.getInt("ID"));
                formData.setSubject(rs.getString("HEADING"));
                formData.setFormFileName(rs.getString("DOC_NAME"));
                dataList.add(formData);
            }
            rs.close();
            sbForm.setDataList(dataList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int deleteBulletin(EAMG_ServiceBulletinBean sbForm, Connection con, String ecatPath) {
        PreparedStatement psmt = null;
        int result = 3;
        try {
            con.setAutoCommit(false);
            String[] arrayValue = sbForm.getSubject().split("@@");
            int bulletinId = Integer.parseInt(arrayValue[0]);
            String doc_name = arrayValue[1];
            File f = new File(ecatPath + "dealer/bulletin/" + doc_name + "");
            if (f.exists()) {
                f.delete();
            }
            String sqlQuery = "delete from CAT_BULLETIN where ID=?";
            psmt = con.prepareStatement(sqlQuery);
            psmt.setInt(1, bulletinId);
            psmt.executeUpdate();
            con.commit();
            result = 1;

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception e1) {
            }
            result = 2;
            e.printStackTrace();
        } finally {
            try {
                if (psmt != null) {
                    psmt.close();
                    psmt = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
