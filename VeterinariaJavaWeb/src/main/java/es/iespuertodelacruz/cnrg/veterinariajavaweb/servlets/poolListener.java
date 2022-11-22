package es.iespuertodelacruz.cnrg.veterinariajavaweb.servlets;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class poolListener
 *
 */
@WebListener
public class poolListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public poolListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("VeterinariaJavaWeb");
         sce.getServletContext().setAttribute("entityManagerFactory", entityManagerFactory);
    }
	
}
