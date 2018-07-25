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

import bean.SessioneUtente;

/**
 * Servlet Filter implementation class AdminFilter
 */

public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
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
		System.out.println("\nAccedo al filtro Admin \n");
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		
		SessioneUtente su=(SessioneUtente)httpRequest.getSession().getAttribute("Utente");
		if(su==null) {
			System.out.println("Non è loggato");
			System.out.println("\n Fine filtro Admin \n");
			httpResponse.sendRedirect("/OtticaCrisci/HTML/Login.jsp");
		}
		else{
			if(su.getRuolo().equalsIgnoreCase("Utente")) {
			System.out.println("E' un utente che vuole impossessarsi del nostro negozio. FERMALO, FILTRO");
			System.out.println("\n Fine filtro Admin \n");
			httpResponse.sendRedirect("/OtticaCrisci/HTML/Homepage.jsp");
		}
		else {
		// pass the request along the filter chain
		System.out.println("\n Fine filtro Admin \n");
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
