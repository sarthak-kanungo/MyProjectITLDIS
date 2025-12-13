package com.i4o.dms.kubota.common.job.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.i4o.dms.kubota.common.job.IJob;
import com.i4o.dms.kubota.common.repo.MailRepo;
import com.i4o.dms.kubota.common.service.MailJobMain;


public class MailJobImpl implements IJob{

	@Autowired
	MailRepo mailRepo;
	@Autowired
	MailJobMain mailJobMain;
	
	@Scheduled(cron = "${MAIL_CORN_EXP}")
	public void executeInternal() {
		try {
			mailJobMain.sendMail(mailRepo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}