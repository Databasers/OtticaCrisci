package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import managerBean.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Frame;

/**
 * Servlet implementation class GestioneNegozio
 */
@WebServlet("/GestioneNegozio")
public class GestioneNegozio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneNegozio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\nAccedo a Gestione Negozio\n");
		String action=request.getParameter("action");
		try{
			if(action.equalsIgnoreCase("retrieve"))
			doRetrieve(request,response);
			
			response.sendRedirect(request.getContextPath() + "\\HTML\\Store.jsp"); 
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void doRetrieve(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		if(request.getSession().getAttribute("Frame")!=null)
			request.getSession().removeAttribute("Frame");
		FrameManager m= new FrameManager();
		Collection<Frame> elenco=m.doRetrieveAll("");
		request.getSession().setAttribute("Frame", elenco);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
