package listener;

import java.util.HashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import bean.SessioneUtente;
import it.unisa.model.Carrello;

/**
 * Application Lifecycle Listener implementation class ContexListener
 *
 */
@WebListener
public class ContexListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContexListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 

    	HashMap<String, SessioneUtente> map= new HashMap<>();
    	arg0.getServletContext().setAttribute("mappa", map);
    	HashMap<String, Carrello> map2= new HashMap<>();
    	arg0.getServletContext().setAttribute("carrello", map2);
    }
	
}
