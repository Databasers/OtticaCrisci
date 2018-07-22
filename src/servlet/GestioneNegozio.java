package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import managerBean.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Frame;
import bean.Opzioni;

@WebServlet("/GestioneNegozio")
public class GestioneNegozio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GestioneNegozio() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\nAccedo a Gestione Negozio\n");
		String action=request.getParameter("action");
		try{
			if(action.equalsIgnoreCase("retrieve"))
			{
				doRetrieve(request,response);
			
				System.out.println("\n Fine Gestione Negozio\n");
				response.sendRedirect(request.getContextPath() + "\\HTML\\Store.jsp"); 
			}
			if(action.equalsIgnoreCase("retrieveForHome"))
			{
				doRetrieve(request,response);
				
				System.out.println("\n Fine Gestione Negozio\n");
				response.sendRedirect(request.getContextPath() + "\\HTML\\Homepage.jsp");
			}
		}
		catch (Exception e) {
			
		}
	}

	/**
	 * Recupera una lista di tutti i frame trattati dall'ottica
	 * @param request
	 * @param response
	 * @throws SQLException
	 */
	private void doRetrieve(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		if(request.getSession().getAttribute("Frame")!=null)
			request.getSession().removeAttribute("Frame");
		FrameManager m= new FrameManager();
		ArrayList<String> s= new ArrayList<>();
		s.add("Colore");
		s.add("Prezzo");
		s.add("Materiale");
		s.add("Modello");
		s.add("Marchio");
		s.add("Peso");
		s.add("UrlImmagine");
		s.add("MAX(IDFrame)");
		String groupBy="Colore,Prezzo,Materiale,Modello,Marchio,Peso";
		System.out.println("Prima del costruttore");
		Opzioni opzioni=new Opzioni(true, s, null, true, groupBy, false,false, null);
		System.out.println("Dopo del costruttore");
		Collection<Frame> elenco=m.doRetrieveByCond(opzioni);
		request.getSession().setAttribute("Frame", elenco);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
