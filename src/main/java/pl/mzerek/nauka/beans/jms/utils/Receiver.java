package pl.mzerek.nauka.beans.jms.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.FileSystemUtils;

public class Receiver {

    /**
     * Get a copy of the application context
     */
    @Autowired
    ConfigurableApplicationContext context;
  
    /**
     * When you receive a message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     * @throws InterruptedException 
     */
    public void receiveMessageOne(String message) throws InterruptedException {
    	System.out.println("Received <" + message + "> from Jms App");
       
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
        
       
  
      }
    
      
}
