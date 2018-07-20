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
import utilities.HashMapStore;

/**
 * Servlet Filter implementation class LoginFilter
 */

public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
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
		System.out.println("\n FILTRO LOGIN \n");
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		SessioneUtente su=(SessioneUtente)httpRequest.getSession().getAttribute("Utente");
		if(su==null){
			System.out.println("Non è loggato");
			System.out.println("\n FINE FILTRO LOGIN \n");
			chain.doFilter(request, response);
		}
		else {
			if(su.getRuolo().equalsIgnoreCase("Admin")) {
				System.out.println("E' un admin");
				System.out.println("\n FINE FILTRO LOGIN \n");
				httpResponse.sendRedirect("/OtticaCrisci/HTML/Admin.jsp");
			}else {
				System.out.println("E' un utente");
				System.out.println("\n FINE FILTRO LOGIN \n");
				httpResponse.sendRedirect("/OtticaCrisci/HTML/Utente.jsp");
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
