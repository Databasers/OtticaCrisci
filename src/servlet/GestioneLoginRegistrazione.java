package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Cliente;
import bean.SessioneUtente;
import it.unisa.model.DriverManagerConnectionPool;
import managerBean.ClienteManager;

@WebServlet("/GestioneLogin")
public class GestioneLoginRegistrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	ClienteManager cManager;
   
    public GestioneLoginRegistrazione() {
        super();
        cManager= new ClienteManager();
        
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
				doRegistrazione(request, response);
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

	//Bisogna eliminare gli accessi al db
	private void doLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username, password;
		username=request.getParameter("username");
		password=request.getParameter("password");
		try {
			Connection connessione=DriverManagerConnectionPool.getConnection();
			PreparedStatement statement;
			String query;
				query="SELECT CodiceFiscale,Nome,cognome,Password FROM Cliente WHERE CodiceFiscale=? AND Password=?";
				statement=connessione.prepareStatement(query);
				statement.setString(1, username);
				statement.setString(2, password);
				System.out.println("Executing query: " + statement.toString());
				ResultSet rs=statement.executeQuery();
				if(!rs.next()) throw new Exception(); //eccezione se non c'è corrispondenza nel db
				Cliente c= new Cliente(rs.getString("CodiceFiscale"), rs.getString("Nome"), rs.getString("Cognome"), rs.getString("Password"));
			SessioneUtente su= new SessioneUtente(c, "Admin"); //creo l'oggetto sessione
			request.getSession().setAttribute("admin", su);
			response.sendRedirect(request.getContextPath() + "\\HTML\\Admin.html"); 	
		} catch (Exception e) {
			System.out.println("Query errata");
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
		try {
			Cliente c=checkLogin(username,password); //controllo se i dati sono corretti
			SessioneUtente su= new SessioneUtente(c, "Utente"); //creo l'oggetto sessione
			request.getSession().setAttribute("Utente", su);
			System.out.println("Login effettuato!");
			response.sendRedirect(request.getContextPath() + "\\HTML\\Utente.jsp"); 	
		} catch (Exception e) {
			System.out.println("Query errata");
			request.setAttribute("Done", "falso");
			RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Login.jsp"); //ritento il login
			x.forward(request, response);
		}
	}
	
	
	private void doRegistrazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String cf,nome,cognome,password;
		cf=request.getParameter("codicefiscale");
		password=request.getParameter("password");
		cognome=request.getParameter("cognome");
		nome=request.getParameter("nome");
		
		//controllo se esiste un Utente con lo stesso codice fiscale
		String sql="Select codicefiscale from cliente where codicefiscale=?";
		Connection connessione;
		try {
			connessione = DriverManagerConnectionPool.getConnection();
			PreparedStatement statement;
			statement=connessione.prepareStatement(sql);
			statement.setString(1, cf);
			ResultSet rs=statement.executeQuery();
			if(!rs.next()) { //se non esistono altri utenti con lo stesso codice
				System.out.println("Registrazione Utente");
				Cliente c=new Cliente(cf,nome,cognome,password);
				cManager.doSave(c);
				SessioneUtente su= new SessioneUtente(c, "Utente");
				request.getSession().setAttribute("Utente",su);
				response.sendRedirect(request.getContextPath() + "\\HTML\\Utente.jsp"); 
			}
			else
			{
				System.out.println("Utente già registrato");
				request.setAttribute("alreadyRegistered","true"); //Già registrato
				RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Login.jsp");
				x.forward(request, response); 
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	private Cliente checkLogin(String username, String password) throws Exception {
		Connection connessione=DriverManagerConnectionPool.getConnection();
		PreparedStatement statement;
		String query;
			query="SELECT CodiceFiscale,Nome,cognome,Password FROM Cliente WHERE CodiceFiscale=? AND Password=?";
			statement=connessione.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			System.out.println("Executing query: " + statement.toString());
			ResultSet rs=statement.executeQuery();
			if(!rs.next()) throw new Exception(); //eccezione se non c'è corrispondenza nel db
			Cliente c= new Cliente(rs.getString("CodiceFiscale"), rs.getString("Nome"), rs.getString("Cognome"), rs.getString("Password"));
			return c;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
