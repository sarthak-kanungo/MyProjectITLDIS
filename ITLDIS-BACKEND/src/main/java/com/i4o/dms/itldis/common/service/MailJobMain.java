package com.i4o.dms.itldis.common.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.i4o.dms.itldis.common.model.MailEntity;
import com.i4o.dms.itldis.common.repo.MailRepo;

@Component
public class MailJobMain {

	@Autowired
	private SendMailService sendMailService;
	
	public MailJobMain() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void sendMail(MailRepo mailRepo) {

        boolean send =false;
		List<MailEntity> list = null;
		MailEntity mailEntity = null;

        try
        {
        	list = mailRepo.findByMailstatus("Pending");
        	
			Iterator<MailEntity> it = list.iterator();

			while (it.hasNext()) {
				
				mailEntity = it.next();
				send = sendMailService.sendMail(mailEntity, null);
               
                if (send) {
                	mailEntity.setMailstatus("DONE");
                	mailEntity.setMailsentdate(new Date());
                	mailRepo.save(mailEntity);
				}
				else {
					mailEntity.setMailstatus("FAILED");		//Suraj--01/12/2022
                	mailRepo.save(mailEntity);		//Suraj--01/12/2022
					continue;
				}
            }
			System.out.println("End of Utility");
        }
    	catch (ConstraintViolationException e) {
			e.printStackTrace();
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

    }
}