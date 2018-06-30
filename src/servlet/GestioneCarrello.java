package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.model.*;
import managerBean.FrameManager;
import bean.Frame;

@WebServlet("/gestione")
public class GestioneCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	FrameManager model= new FrameManager();
   
    public GestioneCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recupero il carrello se esiste, altrimenti lo creo
		request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
		Carrello<Frame> carrello= (Carrello) request.getSession().getAttribute("carrello");
		if(carrello==null) {
			carrello= new Carrello<Frame>();
			request.getSession().setAttribute("carrello", carrello);
		}
		
		String action = request.getParameter("action");
		
		try {
			if(action != null) {
				if(action.equalsIgnoreCase("addCart")) {
					//Aggiungiamo al carrello
					int id = Integer.parseInt(request.getParameter("id"));
					carrello.addElement(model.doRetrieveByKey(id));
					
				}
				else if(action.equalsIgnoreCase("delCart")) {
					//Cancella dal carrello
					int id = Integer.parseInt(request.getParameter("id"));
					carrello.delete(model.doRetrieveByKey(id));
				}
			}
				request.getSession().setAttribute("carrello", carrello);
				
		} catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
		dispatcher.forward(request, response);
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
