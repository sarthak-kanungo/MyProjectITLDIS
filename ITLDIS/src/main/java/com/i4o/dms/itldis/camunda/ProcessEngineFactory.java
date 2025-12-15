package com.i4o.dms.itldis.camunda;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngines;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;
import java.io.InputStream;

/**
 * ProcessEngine Factory for Struts 1 Application
 * Initializes and manages Camunda ProcessEngine lifecycle
 * 
 * @author ITLDIS Team
 */
public class ProcessEngineFactory implements ServletContextListener {
    
    private static ProcessEngine processEngine;
    private static final String PROCESS_ENGINE_NAME = "default";
    
    /**
     * Initialize ProcessEngine when application starts
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        
        try {
            servletContext.log("Initializing Camunda ProcessEngine...");
            
            // Load database configuration from properties file
            Properties props = loadProperties();
            
            // Create ProcessEngine configuration
            ProcessEngineConfiguration config = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration()
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setJdbcUrl(props.getProperty("camunda.db.url"))
                .setJdbcUsername(props.getProperty("camunda.db.username"))
                .setJdbcPassword(props.getProperty("camunda.db.password"))
                .setJdbcDriver(props.getProperty("camunda.db.driver"))
                .setDatabaseType(props.getProperty("camunda.db.type", "h2"))
                .setHistory(props.getProperty("camunda.history.level", "full"))
                .setJobExecutorActivate(Boolean.parseBoolean(
                    props.getProperty("camunda.job.executor.activate", "true")))
                .setProcessEngineName(PROCESS_ENGINE_NAME);
            
            // Build ProcessEngine
            processEngine = config.buildProcessEngine();
            
            // Register with ProcessEngines
            ProcessEngines.registerProcessEngine(processEngine);
            
            servletContext.log("Camunda ProcessEngine initialized successfully: " + processEngine.getName());
            System.out.println("========================================");
            System.out.println("Camunda ProcessEngine initialized: " + processEngine.getName());
            System.out.println("Database: " + props.getProperty("camunda.db.type", "h2"));
            System.out.println("========================================");
            
        } catch (Exception e) {
            servletContext.log("Failed to initialize Camunda ProcessEngine", e);
            System.err.println("ERROR: Failed to initialize Camunda ProcessEngine");
            e.printStackTrace();
            throw new RuntimeException("ProcessEngine initialization failed", e);
        }
    }
    
    /**
     * Shutdown ProcessEngine when application stops
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (processEngine != null) {
            try {
                ProcessEngines.unregister(processEngine);
                processEngine.close();
                processEngine = null;
                sce.getServletContext().log("Camunda ProcessEngine shut down successfully");
                System.out.println("Camunda ProcessEngine shut down");
            } catch (Exception e) {
                sce.getServletContext().log("Error shutting down ProcessEngine", e);
                System.err.println("Error shutting down ProcessEngine: " + e.getMessage());
            }
        }
    }
    
    /**
     * Get the ProcessEngine instance
     */
    public static ProcessEngine getProcessEngine() {
        if (processEngine == null) {
            throw new IllegalStateException("ProcessEngine not initialized. " +
                "Ensure ProcessEngineFactory is configured as ServletContextListener in web.xml");
        }
        return processEngine;
    }
    
    /**
     * Get ProcessEngine by name (for multiple engines)
     */
    public static ProcessEngine getProcessEngine(String name) {
        return ProcessEngines.getProcessEngine(name);
    }
    
    /**
     * Load properties from camunda.properties file
     */
    private Properties loadProperties() {
        Properties props = new Properties();
        try {
            InputStream is = getClass().getClassLoader()
                .getResourceAsStream("camunda.properties");
            if (is != null) {
                props.load(is);
                System.out.println("Loaded camunda.properties configuration");
            } else {
                // Use default H2 configuration
                System.out.println("camunda.properties not found, using default H2 configuration");
                props.setProperty("camunda.db.url", 
                    "jdbc:h2:mem:camunda;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
                props.setProperty("camunda.db.username", "sa");
                props.setProperty("camunda.db.password", "");
                props.setProperty("camunda.db.driver", "org.h2.Driver");
                props.setProperty("camunda.db.type", "h2");
                props.setProperty("camunda.history.level", "full");
                props.setProperty("camunda.job.executor.activate", "true");
            }
        } catch (Exception e) {
            System.err.println("Error loading camunda.properties, using defaults: " + e.getMessage());
            // Set defaults
            props.setProperty("camunda.db.url", 
                "jdbc:h2:mem:camunda;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
            props.setProperty("camunda.db.username", "sa");
            props.setProperty("camunda.db.password", "");
            props.setProperty("camunda.db.driver", "org.h2.Driver");
            props.setProperty("camunda.db.type", "h2");
            props.setProperty("camunda.history.level", "full");
            props.setProperty("camunda.job.executor.activate", "true");
        }
        return props;
    }
}
