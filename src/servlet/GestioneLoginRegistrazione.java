package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import utilities.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Admin;
import bean.Cliente;
import bean.SessioneUtente;
import it.unisa.model.DriverManagerConnectionPool;
import managerBean.AdminManager;
import managerBean.ClienteManager;

@WebServlet("/GestioneLogin")
public class GestioneLoginRegistrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	ClienteManager cManager;
	AdminManager aManager;
   
    public GestioneLoginRegistrazione() {
        super();
        cManager= new ClienteManager();
        aManager= new AdminManager();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n GESTIONE LOGIN REGISTRAZIONE \n");
		if(((String)request.getParameter("action")).equals("logout")) {
			doLogout(request, response);
		}
		else{
			request.getSession().removeAttribute("Utente");
			if(((String)request.getParameter("action")).equals("login")) {
				doLogin(request, response);
			}
			if(((String)request.getParameter("action")).equals("loginAdmin")) {
				doLoginAdmin(request, response);
			}
			if(((String)request.getParameter("action")).equals("registrazione")) {
				try {
					doRegistrazione(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
}
	
	/**
	 * Elimina la sessioneUtente dalla sessione, controllando preventivamente se esiste un Utente loggato
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Logout");
		//controllo se non è loggato
		if(request.getSession().getAttribute("Utente")==null) 
			response.sendRedirect(request.getContextPath() + "\\HTML\\Login.jsp");  //lo manda a loggarsi

		String uuid=CookieManager.getCookieValue(request, "SessioneUtenteCookie");
		CookieManager.removeCookie(response, "SessioneUtenteCookie");
		HashMapStore<String, SessioneUtente> map=(HashMapStore<String, SessioneUtente>)getServletContext().getAttribute("mappa");
		if(uuid!=null)
			map.remove(uuid);
		getServletContext().setAttribute("mappa", map);
		
		request.getSession().removeAttribute("Utente");
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "\\HTML\\Homepage.jsp"); 
		System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
	}

	private void doLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login Admin");
		String username, password;
		username=request.getParameter("username");
		password=request.getParameter("password");
		
		try {
			Admin c=aManager.doRetrieveIfRegistered(username, password);
			SessioneUtente su= new SessioneUtente(c, "Admin"); //creo l'oggetto sessione
			request.getSession().setAttribute("Utente", su);
			System.out.println("Login effettuato!");
			System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
			response.sendRedirect(request.getContextPath() + "\\HTML\\Admin.html"); 	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Utente non registrato");
			request.setAttribute("Done", "falso");
			System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
			RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Login.jsp"); //ritento il login
			x.forward(request, response);
		}
	}


	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("Login");
		String username, password;
		username=request.getParameter("username");
		password=request.getParameter("password");
		HashMapStore<String, SessioneUtente> map=(HashMapStore<String, SessioneUtente>)getServletContext().getAttribute("mappa");
		try {
			Cliente c=cManager.doRetrieveIfRegistered(username, password);
			SessioneUtente su= new SessioneUtente(c, "Utente"); //creo l'oggetto sessione
			request.getSession().setAttribute("Utente", su);
			
			//Aggiungo la sessione utente nei cookie
			String uuid=UUID.randomUUID().toString();
			map.put(uuid, su);
			CookieManager.addCookie(response, "SessioneUtenteCookie",uuid, 60*60);
			getServletContext().setAttribute("mappa", map);
			
			System.out.println("Login effettuato!");
			System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
			response.sendRedirect(request.getContextPath() + "\\HTML\\Utente.jsp"); 	
		} catch (Exception e) {
			System.out.println("Utente non registrato");
			request.setAttribute("Done", "falso");
			System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
			RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Login.jsp"); //ritento il login
			x.forward(request, response);
		}
	}
	
	
	private void doRegistrazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
	{
		System.out.println("Registrazione");
		String cf,nome,cognome,password;
		cf=request.getParameter("codicefiscale");
		password=request.getParameter("password");
		cognome=request.getParameter("cognome");
		nome=request.getParameter("nome");
		Cliente c;
		try {
				c=cManager.doRetrieveByKey(cf);
				System.out.println("Utente già registrato");
				request.setAttribute("alreadyRegistered","true"); //Già registrato
				System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
				RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Login.jsp");
				x.forward(request, response); 
		} catch (SQLException e) {
			System.out.println("Registrazione Utente");
			c=new Cliente(cf,nome,cognome,password,0);
			cManager.doSave(c);
			SessioneUtente su= new SessioneUtente(c, "Utente");
			request.getSession().setAttribute("Utente",su);
			
			//Aggiungo la sessione utente nei cookie
			HashMapStore<String, SessioneUtente> map=(HashMapStore<String, SessioneUtente>)getServletContext().getAttribute("mappa");
			String uuid=UUID.randomUUID().toString();
			map.put(uuid, su);
			CookieManager.addCookie(response, "SessioneUtenteCookie",uuid, 60*60);
			getServletContext().setAttribute("mappa", map);
			
			System.out.println("\n FINE GESTIONE LOGIN REGISTRAZIONE \n");
			response.sendRedirect(request.getContextPath() + "\\HTML\\Utente.jsp"); 
		}
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
