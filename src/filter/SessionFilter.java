package filter;

import java.io.IOException;
import java.util.HashMap;

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
		System.out.println("Accedo al filtro");
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		SessioneUtente su;
		//Controllo se già loggato
		su=(SessioneUtente)httpRequest.getSession().getAttribute("Utente"); 
		if(su==null) {
			//Controllo se esiste un cookie sessione
			String uuid=CookieManager.getCookieValue(httpRequest, "SessioneUtenteCookie");
			if(uuid!=null) {
				HashMap<String, SessioneUtente> map=(HashMap<String, SessioneUtente>) request.getServletContext().getAttribute("mappa");
				su=map.get(uuid);
				if(su!=null) {
					System.out.println("\nRipristino la sessioneUtente di "+ su.getcF());
					httpRequest.getSession().setAttribute("Utente", su);
					CookieManager.removeCookie(httpResponse, "SessioneUtenteCookie");
					CookieManager.addCookie(httpResponse, "SessioneUtenteCookie",uuid, 60*60);
					chain.doFilter(request, response);
				}
				else
					httpResponse.sendRedirect("Login.jsp");
			}
			else
				httpResponse.sendRedirect("Login.jsp");
		}
		else {
			if(su.getRuolo().equalsIgnoreCase("Admin"))
				httpResponse.sendRedirect("Login.jsp"); //Se è un admin lo mando a loggarsi
			else {
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
