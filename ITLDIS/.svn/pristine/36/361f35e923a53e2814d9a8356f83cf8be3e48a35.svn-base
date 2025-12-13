/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringType;

import HibernateUtil.HibernateUtil;
import sun.misc.BASE64Decoder;
import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author avinash.pandey
 */
public class InsertInstallationInSTGToINSDetail {

    public static void main(String args[]) {
        PageTemplate object_pageTemplate = new PageTemplate();

        String dsnPATH = "";
        Session session = null;
        dsnPATH = object_pageTemplate.dsnPATH();
        String dbUserName = object_pageTemplate.dbUserName();
        String dbPassword = object_pageTemplate.dbPasswd();
        String className = object_pageTemplate.jdbcDriverMAIN();
        Connection conn = null;
        session = HibernateUtil.getSessionFactory().openSession();
        String realPath = PageTemplate.imgPath;
        Query query = null, query2 = null, query3 = null;
        File dest_folder = null;
        String dest = "", newImageName = "", insNo = "", ImageBytes = "";
        boolean isDeleted = false;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(dsnPATH, dbUserName, dbPassword);
            conn.setAutoCommit(false);
            Iterator itr1 = null;
            session.beginTransaction();
            System.out.println("INS Stated");
            session.createSQLQuery("exec Sync_InsertInstallationInSTGToINSDetail").executeUpdate();
            session.getTransaction().commit();
            System.out.println("INS End");

            System.out.println("INS has been Updated Successfully");
            //query = session.createSQLQuery("Select Actual_INS_No,ImageBytes from STG_INS_DETAILS where isDMSsyn='Y' and Actual_INS_No is not null and imagesFlag is null order by Actual_INS_No").addScalar("Actual_INS_No", StringType.INSTANCE).addScalar("ImageBytes", StringType.INSTANCE);
            query = session.createSQLQuery("exec SP_STGINSImagesDetail").addScalar("Actual_INS_No", StringType.INSTANCE).addScalar("ImageBytes", StringType.INSTANCE);
            List insImages = query.list();
            Iterator itr = insImages.iterator();
            ByteArrayInputStream bis=null;
            byte[] imageByte=null;
            BASE64Decoder decoder=null;
            BufferedImage image=null;
            File outputfile=null;
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                insNo = obj[0].toString();
                ImageBytes = obj[1].toString();
                System.out.println("insNo.." + insNo);
                dest_folder = new File(realPath + "/DOCUMENTS/INSTALLATION/");
                if (!dest_folder.exists()) {
                    dest_folder.mkdirs();
                }
                dest = realPath + "/DOCUMENTS/INSTALLATION/";
                newImageName = insNo.replace("/", "-").concat(".").concat("jpg");
                String s1 = realPath + "/DOCUMENTS/INSTALLATION/" + newImageName;
                File f1 = new File(s1);
                if (f1.exists()) {
                    //String delete_dest=dest+"newImageName";
                    File file = new File(s1);
                    isDeleted = file.delete();
                } else {
                    isDeleted = true;
                }

                if (isDeleted) {
                    if (!ImageBytes.equals("")) {

                        try {
                            decoder = new BASE64Decoder();
                            imageByte = decoder.decodeBuffer(ImageBytes);
                            bis = new ByteArrayInputStream(imageByte);
                            image = ImageIO.read(bis);
                        } catch (IOException ex) {
                            System.err.println("image: " + bis.toString());
                            ex.printStackTrace();
                        } finally {
                            bis.close();
                        }

                        // write the image to a file
                        outputfile = new File(dest, newImageName);
                        //System.out.println("outputfile.." + outputfile);
                        ImageIO.write(image, "jpg", outputfile);

                        System.out.println("INS images updated" + newImageName);
                        updateINSImageStatus(insNo);

                        
                    }
                }

            }
            System.out.println("FINAL INS images updated");
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();

        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateINSImageStatus(String insNo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Iterator itr1 = null;
        Query query2 = null, update = null;
        try {
            session.beginTransaction();
            query2 = session.createSQLQuery("Select Dealercode,VinNo from STG_INS_DETAILS(NOLOCK) where Actual_INS_No='" + insNo + "' and isDMSsyn='Y' and Actual_INS_No is not null and imagesFlag is null order by Actual_INS_No").addScalar("Dealercode", StringType.INSTANCE).addScalar("VinNo", StringType.INSTANCE);
            itr1 = query2.list().iterator();
            if (itr1.hasNext()) {
                Object o[] = (Object[]) itr1.next();
                update = session.createSQLQuery("UPDATE STG_INS_DETAILS  SET  imagesFlag='Y' WHERE VinNo=? and Dealercode=? and Actual_INS_No=?");
                update.setString(0, o[1].toString().trim());
                update.setString(1, o[0].toString().trim());
                update.setString(2, insNo.toString().trim());
                update.executeUpdate();
//                Query update = session.createQuery("UPDATE STG_INS_DETAILS SET imagesFlag=:ImagesFlag WHERE VinNo=:vinNo and Dealercode=:dealercode and Actual_INS_No=:actualINSNo");
//                update.setParameter("ImagesFlag", "Y");
//                update.setParameter("vinNo", o[1].toString().trim());
//                update.setParameter("dealercode", o[0].toString().trim());
//                update.setParameter("actualINSNo", insNo.toString().trim());
//
//                update.executeUpdate();
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                    session = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
