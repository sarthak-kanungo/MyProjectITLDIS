package com.i4o.dms.itldis.configurations;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Camunda BPM Configuration
 * 
 * This configuration class enables Camunda BPM Process Application
 * and provides access to Camunda engine services.
 * 
 * @author ITLDIS Team
 */
@Configuration
@EnableProcessApplication
public class CamundaConfiguration {

    /**
     * Access to RuntimeService for starting and managing process instances
     */
    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    /**
     * Access to TaskService for managing user tasks
     */
    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    /**
     * Access to RepositoryService for managing process definitions
     */
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    /**
     * Access to HistoryService for querying historical process data
     */
    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }
}
