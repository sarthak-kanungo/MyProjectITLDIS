package com.i4o.dms.itldis.common.job.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.i4o.dms.itldis.common.job.IJob;
import com.i4o.dms.itldis.common.job.impl.MailJobImpl;

@Configuration
@EnableScheduling
public class JobSchedulerConfig  implements SchedulingConfigurer{

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}
	
	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(100);
	}
	
	//The Bean for starting the mail sending service.
//	@Bean
//	public IJob getMailJob() {
//		return new MailJobImpl();
//	}
}
