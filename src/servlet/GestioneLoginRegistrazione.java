package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
		if(((String)request.getParameter("action")).equals("logout")) {
			doLogout(request, response);
		}
		else{
			//controllo se c'è già loggato
			if(request.getSession().getAttribute("Utente")!=null) {
				request.setAttribute("alreadyLogged", true); //Da gestire nella pagina Utente
				RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Utente.jsp");
				x.forward(request, response); 	
			}
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
		//controllo se non è loggato
		if(request.getSession().getAttribute("Utente")==null) {
			RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Login.jsp"); //lo manda a loggarsi
			x.forward(request, response); 	
		}
		request.getSession().removeAttribute("Utente");
		request.getSession().invalidate();
		RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Homepage.html");
		x.forward(request, response); 
	}

	private void doLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username, password;
		username=request.getParameter("username");
		password=request.getParameter("password");
		
		try {
			Admin c=aManager.doRetrieveIfRegistered(username, password);
			SessioneUtente su= new SessioneUtente(c, "Admin"); //creo l'oggetto sessione
			request.getSession().setAttribute("Utente", su);
			response.sendRedirect(request.getContextPath() + "\\HTML\\Admin.html"); 	
		} catch (Exception e) {
			System.out.println("Utente non registrato");
			request.setAttribute("Done", "falso");
			RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Login.jsp"); //ritento il login
			x.forward(request, response);
		}
	}


	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username, password;
		username=request.getParameter("username");
		password=request.getParameter("password");
		HashMap<String, SessioneUtente> map=(HashMap<String, SessioneUtente>)getServletContext().getAttribute("mappa");
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
			response.sendRedirect(request.getContextPath() + "\\HTML\\Utente.jsp"); 	
		} catch (Exception e) {
			System.out.println("Utente non registrato");
			request.setAttribute("Done", "falso");
			RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Login.jsp"); //ritento il login
			x.forward(request, response);
		}
	}
	
	
	private void doRegistrazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
	{
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
				RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Login.jsp");
				x.forward(request, response); 
		} catch (SQLException e) {
			
			System.out.println("Registrazione Utente");
			c=new Cliente(cf,nome,cognome,password,0);
			cManager.doSave(c);
			SessioneUtente su= new SessioneUtente(c, "Utente");
			request.getSession().setAttribute("Utente",su);
			response.sendRedirect(request.getContextPath() + "\\HTML\\Utente.jsp"); 
		}
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
