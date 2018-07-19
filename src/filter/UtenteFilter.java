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
 * Servlet Filter implementation class UtenteFilter
 */

public class UtenteFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public UtenteFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("\nAccedo al filtro Utente \n");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		SessioneUtente su=(SessioneUtente)httpRequest.getSession().getAttribute("Utente");
		if(su==null) {
			System.out.println("Non è loggato");
			System.out.println("\n Fine filtro Utente \n");
			httpResponse.sendRedirect("/OtticaCrisci/HTML/Login.jsp");
		}
		else{
			if(su.getRuolo().equalsIgnoreCase("Admin")) {
			System.out.println("E' un admin che vuole scroccare qualcosa. FERMALO, FILTRO");
			System.out.println("\n Fine filtro Utente \n");
			httpResponse.sendRedirect("/OtticaCrisci/HTML/Homepage.jsp");
		}
		else
		// pass the request along the filter chain
		System.out.println("\n Fine filtro Utente \n");
		chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
