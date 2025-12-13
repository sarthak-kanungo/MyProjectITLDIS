package com.common;

import java.math.BigInteger;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;

import HibernateMapping.UmEmailMaster;
import HibernateUtil.HibernateUtil;

public class SendMailMain {

    public static BigInteger sendMailMain() throws Exception {

        Session hrbsession = null;
        hrbsession = HibernateUtil.getSessionFactory().openSession();
        hrbsession.beginTransaction();
        String fromMailId = null;
        String toMailId[] = null;
        String ccMailId[] = null;
        String bccMailId[] = null;
        String mailText = null;
        Vector fileVector = null;
      //  String remarks = null;
        String subject = null;
        BigInteger mailId = null;

        try {
            List<Object[]> mailList = MethodUtility.getEmailData();
            UmEmailMaster uem = new UmEmailMaster();

            for (Object[] obj : mailList) {

                fromMailId = obj[0].toString();

                toMailId = null;
                ccMailId = null;
                bccMailId = null;


                if(obj[1]!=null)
                {
                   toMailId = obj[1].toString().split(",");
                }


                if(obj[2]!=null)
                {
                    ccMailId = obj[2].toString().split(",");
                }
                
                if(obj[3]!=null)
                {
                    bccMailId = obj[3].toString().split(",");
                }

                
                mailText = obj[4].toString();
                fileVector = new Vector();
                if (obj[5] != null && !obj[5].equals("")) {
                    fileVector.add(obj[5]);
                }
               // remarks = obj[9].toString();
                subject = obj[10].toString();
                mailId = new BigInteger(obj[11].toString());

                boolean send = SendMailUtility.send_mail(mailText, subject, fromMailId, toMailId, ccMailId, bccMailId, fileVector);
                if (!send) {
                    mailId = null;
                } else {
                    uem = (UmEmailMaster) hrbsession.get(UmEmailMaster.class, mailId);
                    uem.setStatus("SENT");
                    uem.setSentOn(new java.util.Date());
                    hrbsession.update(uem);
                }
            }
            hrbsession.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (hrbsession != null) {
                    hrbsession.close();
                    hrbsession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mailId;
    }

    public static void main(String args[]) {

        try {
            SendMailMain sm = new SendMailMain();
            sm.sendMailMain();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

