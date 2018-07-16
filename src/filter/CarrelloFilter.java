package filter;

import java.io.IOException;
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
import utilities.HashMapStore;

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
		
		System.out.println("\nAccedo al filtro Carrello\n");
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		Carrello carrello;
		HashMapStore<String, Carrello<Frame>> map=(HashMapStore<String, Carrello<Frame>>) request.getServletContext().getAttribute("carrello");
		carrello=(Carrello<Frame>)httpRequest.getSession().getAttribute("carrello"); 
		SessioneUtente su=(SessioneUtente)httpRequest.getSession().getAttribute("Utente");
		
		if(su!=null) { //Se è loggato
			String uuid=CookieManager.getCookieValue(httpRequest, "CarrelloCookie"+su.getcF()); //recupero il codice dal cookie
			if(carrello==null && uuid==null) { //Se non esiste carrello in sessione nè nei cookie vado avanti
				System.out.println("Entrambi null");
			}
			if(carrello==null && uuid!=null) { //se esiste solo quello nei cookie
				carrello=map.get(uuid);
				if(carrello==null) { //Se il cookie esiste ma nella hashMap non c'è (nel caso in cui il server sia stato restartato
					System.out.println("Esiste solo il cookie");
					CookieManager.removeCookie(httpResponse, "CarrelloCookie"+su.getcF()); //elimino il cookie
							//vado avanti
				}
				else {
					System.out.println("Ripristino il carrello salvato nei cookie");
					httpRequest.getSession().setAttribute("carrello", carrello);
					CookieManager.removeCookie(httpResponse, "CarrelloCookie"+su.getcF());
					CookieManager.addCookie(httpResponse, "CarrelloCookie"+su.getcF(), uuid, 60*60);
				}
			}
		}
		else
			System.out.println("Non è loggato");
		
		System.out.println("\nFine Filtro Carrello\n");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
