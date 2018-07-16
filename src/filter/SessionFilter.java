package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.*;
import bean.SessioneUtente;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter(filterName="LogFilter", urlPatterns= {"/GestioneUtente","/HTML/Utente.jsp"})
public class SessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("\nAccedo al filtro Sessione\n");
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		SessioneUtente su;
		//Controllo se già loggato
		su=(SessioneUtente)httpRequest.getSession().getAttribute("Utente"); 
		if(su==null) {
			//Controllo se esiste un cookie sessione
			String uuid=CookieManager.getCookieValue(httpRequest, "SessioneUtenteCookie");
			if(uuid!=null) {
				System.out.println("Esiste il codice nel cookie");
				HashMapStore<String, SessioneUtente> map=(HashMapStore<String, SessioneUtente>) request.getServletContext().getAttribute("mappa");
				su=map.get(uuid);
				if(su!=null) {
					System.out.println("Ripristino la sessioneUtente di "+ su.getcF());
					System.out.println("\nFine Filtro sessione\n");
					httpRequest.getSession().setAttribute("Utente", su);
					CookieManager.removeCookie(httpResponse, "SessioneUtenteCookie");
					CookieManager.addCookie(httpResponse, "SessioneUtenteCookie",uuid, 60*60);
					chain.doFilter(request, response);
				}
				else {
					System.out.println("Esiste solo il cookie");
					System.out.println("\nFine Filtro sessione\n");
					CookieManager.removeCookie(httpResponse, "SessioneUtenteCookie");
					httpResponse.sendRedirect("Login.jsp");
				}
			}
			else {
				System.out.println("Il cookie non esiste");
				System.out.println("\nFine Filtro sessione\n");
				httpResponse.sendRedirect("Login.jsp");
			}
		}
		else {
			System.out.println("C'è già qualcuno in sessione");
			if(su.getRuolo().equalsIgnoreCase("Admin")) {
				System.out.println("E' un admin");
				System.out.println("\nFine Filtro sessione\n");
				httpResponse.sendRedirect("Login.jsp"); //Se è un admin lo mando a loggarsi
			}
			else {
				System.out.println("C'è un utente già loggato");
				System.out.println("\nFine Filtro sessione\n");
				chain.doFilter(request, response);
			}
		}
		
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
