package com.i4o.dms.itldis.camunda;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Deploys BPMN processes on application startup
 * Automatically deploys all .bpmn files from the processes directory
 * 
 * @author ITLDIS Team
 */
public class ProcessDeploymentListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Wait a bit to ensure ProcessEngine is initialized first
            Thread.sleep(1000);
            
            ProcessEngine engine = ProcessEngineFactory.getProcessEngine();
            RepositoryService repositoryService = engine.getRepositoryService();
            
            // List of process files to deploy
            List<String> processFiles = new ArrayList<String>();
            processFiles.add("processes/sample-process.bpmn");
            processFiles.add("processes/approval-workflow.bpmn");
            // Add more process files here as needed
            // processFiles.add("processes/warranty-claim-process.bpmn");
            // processFiles.add("processes/service-request-process.bpmn");
            // processFiles.add("processes/purchase-order-process.bpmn");
            
            List<String> deployedFiles = new ArrayList<String>();
            
            // Deploy processes from classpath
            for (String processFile : processFiles) {
                try {
                    InputStream is = getClass().getClassLoader().getResourceAsStream(processFile);
                    if (is != null) {
                        Deployment deployment = repositoryService.createDeployment()
                            .addInputStream(processFile, is)
                            .name("ITLDIS Processes - " + processFile)
                            .deploy();
                        
                        deployedFiles.add(processFile);
                        sce.getServletContext().log("Deployed process: " + processFile + 
                            " (Deployment ID: " + deployment.getId() + ")");
                        System.out.println("Deployed Camunda process: " + processFile);
                        is.close();
                    } else {
                        sce.getServletContext().log("Process file not found: " + processFile);
                    }
                } catch (Exception e) {
                    sce.getServletContext().log("Failed to deploy process: " + processFile, e);
                    System.err.println("Failed to deploy process " + processFile + ": " + e.getMessage());
                }
            }
            
            if (deployedFiles.isEmpty()) {
                sce.getServletContext().log("No BPMN processes found to deploy. " +
                    "Add .bpmn files to src/main/resources/processes/ directory");
                System.out.println("No BPMN processes deployed. Add .bpmn files to processes directory.");
            } else {
                sce.getServletContext().log("Successfully deployed " + deployedFiles.size() + 
                    " process definition(s)");
                System.out.println("Successfully deployed " + deployedFiles.size() + 
                    " Camunda process definition(s)");
            }
            
        } catch (Exception e) {
            sce.getServletContext().log("Failed to deploy processes", e);
            System.err.println("ERROR: Failed to deploy Camunda processes: " + e.getMessage());
            e.printStackTrace();
            // Don't throw exception - allow application to continue even if deployment fails
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup if needed
        System.out.println("ProcessDeploymentListener destroyed");
    }
}
