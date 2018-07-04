package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.catalina.connector.Request;

import bean.Certificato;
import bean.Cliente;
import bean.SessioneUtente;
import managerBean.CertificatoManager;
import managerBean.ClienteManager;
/**
 * Servlet implementation class GestioneUtente
 */
@WebServlet("/GestioneUtente")
@MultipartConfig(fileSizeThreshold= 1024*1024*2, maxFileSize=1024*1024*10, maxRequestSize=1024*1024*50 )
public class GestioneUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CertificatoManager certificatoManager;
    private ClienteManager clienteManager;
    private Cliente cliente;
    
    public GestioneUtente() {
        super();
        certificatoManager=new CertificatoManager();
        clienteManager= new ClienteManager();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessioneUtente su=(SessioneUtente) request.getSession().getAttribute("su");
		try {
			cliente=clienteManager.doRetrieveByKey(su.getcF()); //recupero il cliente che riguarda questa chiamata
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String action=(String)request.getParameter("action");
		if(action.equalsIgnoreCase("modificaPassword"))
			doModificaPassword(request,response);
		if(action.equalsIgnoreCase("addCertificato"))
			try {
				doAddCertificato(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

	private void doAddCertificato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		String saveDir="C:\\Users\\Antonio\\eclipse--EEworkspace-servlet\\OtticaCrisci\\Data\\Certificati";
		for(Part certificato: request.getParts()) //tanto lo fa una sola volta
		{
			String filename= certificato.getSubmittedFileName();
			saveDir+=File.separator+filename;
			if(filename!=null && !filename.equals("")) {
				certificato.write(saveDir); 
				System.out.println("Directory di salvataggio: " + saveDir);
				certificatoManager.doSave(new Certificato( cliente.getcF(),saveDir,false));
				request.setAttribute("certificatoInserito", true);
			}
			else
				request.setAttribute("certificatoInserito", true);
		}
		RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Utente.jsp");
		x.forward(request, response);
	}



	private void doModificaPassword(HttpServletRequest request, HttpServletResponse response) {
		
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
