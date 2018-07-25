package listener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import bean.SessioneUtente;
import it.unisa.model.Carrello;
import utilities.HashMapStore;

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
    	System.out.println("\nAccedo al filtro di contesto\n");
    	HashMapStore<String, SessioneUtente> map;
		try {
			map = new HashMapStore<>("C:\\Users\\Antonio\\eclipse--EEworkspace-servlet\\OtticaCrisci\\Data\\Mappe\\su.no");
			arg0.getServletContext().setAttribute("mappa", map);
	    	HashMapStore<String, Carrello> map2= new HashMapStore<>("C:\\Users\\Antonio\\eclipse--EEworkspace-servlet\\OtticaCrisci\\Data\\Mappe\\cart.no");
	    	arg0.getServletContext().setAttribute("carrello", map2);
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
}