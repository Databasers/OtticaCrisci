package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Admin;
import bean.Certificato;
import bean.OcchialeNuovo;
import bean.OcchialeRotto;
import bean.SessioneUtente;
import managerBean.AdminManager;
import managerBean.CertificatoManager;
import managerBean.OcchialeNuovoManager;
import managerBean.OcchialeRottoManager;

/**
 * Servlet implementation class GestioneAdmin
 */
@WebServlet("/GestioneAdmin")
public class GestioneAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    String label;
    SessioneUtente su;
    public GestioneAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action=(String)request.getParameter("action");
		su=(SessioneUtente)request.getSession().getAttribute("Utente");
		try {
		if(action.equalsIgnoreCase("certificato"))
			doRetrieveCertificati(request,response);
		if(action.equalsIgnoreCase("occhiali"))
			doRetrieveOcchiali(request,response);
		if(action.equalsIgnoreCase("dati"))
			doRetrieveDati(request,response);
		//Qui aggiungiamo i 3 handler per le modifiche
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void doRetrieveDati(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		AdminManager adminManager= new AdminManager();
		Admin admin=adminManager.doRetrieveByKey(su.getcF());
		label="dati";
		if(request.getAttribute("label")!=null)
			request.removeAttribute("label");
		request.setAttribute("label", label);
		if(request.getAttribute("datiAdmin")!=null)
			request.removeAttribute("datiAdmin");
		request.setAttribute("datiAdmin", admin);
	}

	private void doRetrieveOcchiali(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		OcchialeNuovoManager occhialeNuovo= new OcchialeNuovoManager();
		OcchialeRottoManager occhialeRotto= new OcchialeRottoManager();
		Collection<OcchialeNuovo> elencoNuovo=occhialeNuovo.doRetrieveIfNotCompleted("IDOcchiale");
		Collection<OcchialeRotto> elencoRotto=occhialeRotto.doRetrieveIfNotCompleted("IDOcchiale");
		if(request.getAttribute("occhialiNuovi")!=null)
			request.removeAttribute("occhialiNuovi");
		if(request.getAttribute("occhialiRotti")!=null)
			request.removeAttribute("occhialiRotti");
		request.setAttribute("occhialiNuovi", elencoNuovo);
		request.setAttribute("occhialiRotti", elencoRotto);
		label="occhiali";
		if(request.getAttribute("label")!=null)
			request.removeAttribute("label");
		request.setAttribute("label", label);
		
	}

	private void doRetrieveCertificati(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		CertificatoManager certificato= new CertificatoManager();
		Collection<Certificato> elenco=certificato.doRetrieveIfNotValidated("Codice Fiscale");
		label="certificati";
		if(request.getAttribute("label")!=null)
			request.removeAttribute("label");
		request.setAttribute("label", label);
		if(request.getAttribute("certificati")!=null)
			request.removeAttribute("certificati");
		request.setAttribute("certificati", elenco);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
