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
import it.unisa.model.*;
/**
 * Servlet implementation class GestioneLogin
 */
@WebServlet("/GestioneLogin")
public class GestioneLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GestioneLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username, password;
		username=request.getParameter("username");
		password=request.getParameter("password");
		try {
			checkLogin(username,password);
			response.sendRedirect(request.getContextPath() + "\\HTML\\Marchi.html");
			
		} catch (Exception e) {
			System.out.println("Query errata");
			request.setAttribute("Done", "falso");
			RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Login.jsp");
			x.forward(request, response);
		}
		
	
	}

	
	private void checkLogin(String username, String password) throws Exception {
		Connection connessione=DriverManagerConnectionPool.getConnection();
		PreparedStatement statement;
		String query;
			query="SELECT CodiceFiscale FROM Cliente WHERE CodiceFiscale=? AND Password=?";
			statement=connessione.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			System.out.println("Executing query: " + statement.toString());
			ResultSet rs=statement.executeQuery();
			if(!rs.next()) throw new Exception();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
