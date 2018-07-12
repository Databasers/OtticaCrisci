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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Frame;
import bean.SessioneUtente;
import it.unisa.model.Carrello;
import utilities.CookieManager;

/**
 * Servlet Filter implementation class CarrelloFilter
 */
@WebFilter(filterName="CarrelloFilter", urlPatterns= {"/HTML/Carrello.jsp","/gestione"})
public class CarrelloFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CarrelloFilter() {
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
		
		System.out.println("Accedo al filtro Carrello");
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		Carrello carrello;
		HashMap<String, Carrello<Frame>> map=(HashMap<String, Carrello<Frame>>) request.getServletContext().getAttribute("carrello");
		carrello=(Carrello<Frame>)httpRequest.getSession().getAttribute("carrello"); 
		SessioneUtente su=(SessioneUtente)httpRequest.getSession().getAttribute("Utente");
		
		if(su!=null) { //Se è loggato
			String uuid=CookieManager.getCookieValue(httpRequest, "CarrelloCookie"+su.getcF()); //recupero il codice dal cookie
			if(carrello==null && uuid==null) { //Se non esiste carrello in sessione nè nei cookie vado avanti
				System.out.println("\nEntrambi null\n");
			}
			if(carrello==null && uuid!=null) { //se esiste solo quello nei cookie
				carrello=map.get(uuid);
				if(carrello==null) { //Se il cookie esiste ma nella hashMap non c'è (nel caso in cui il server sia stato restartato
					System.out.println("\nEsiste solo il cookie\n");
					CookieManager.removeCookie(httpResponse, "CarrelloCookie"+su.getcF()); //elimino il cookie
							//vado avanti
				}
				else {
					System.out.println("\nRipristino il carrello salvato nei cookie\n");
					httpRequest.getSession().setAttribute("carrello", carrello);
					CookieManager.removeCookie(httpResponse, "CarrelloCookie"+su.getcF());
					CookieManager.addCookie(httpResponse, "CarrelloCookie"+su.getcF(), uuid, 60*60);
					
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
