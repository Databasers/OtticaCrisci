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
import bean.Certificato;
import bean.Cliente;
import bean.OcchialeNuovo;
import bean.OcchialeRotto;
import bean.SessioneUtente;
import managerBean.CertificatoManager;
import managerBean.ClienteManager;
import managerBean.OcchialeNuovoManager;
import managerBean.OcchialeRottoManager;

@WebServlet("/GestioneUtente")
@MultipartConfig(fileSizeThreshold= 1024*1024*2, maxFileSize=1024*1024*10, maxRequestSize=1024*1024*50 )
public class GestioneUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CertificatoManager certificatoManager;
    private ClienteManager clienteManager;
    private Cliente cliente;
    private OcchialeNuovoManager occhialeNuovoManager;
    private OcchialeRottoManager occhialeRottoManager;
    
    public GestioneUtente() {
        super();
        certificatoManager=new CertificatoManager();
        clienteManager= new ClienteManager();
        occhialeNuovoManager=new OcchialeNuovoManager();
        occhialeRottoManager= new OcchialeRottoManager();
    }

	//Inserire chiamata asincrona per restituzione frame e lente di ordine
    //Inserire filtro per sessione
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessioneUtente su=(SessioneUtente) request.getSession().getAttribute("utente");
		try {
			cliente=clienteManager.doRetrieveByKey(su.getcF()); //recupero il cliente che riguarda questa chiamata
			String action=(String)request.getParameter("action");
			if(action.equalsIgnoreCase("modificaPassword"))
				doModificaPassword(request,response);
			if(action.equalsIgnoreCase("addCertificato"))
					doAddCertificato(request,response);
			if(action.equalsIgnoreCase("retrieveOrdini"))
					doRetrieveOrdini(request,response);
			if(action.equalsIgnoreCase("retrieve"))
				doRetrieve(request,response);
			RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Utente.jsp");
			x.forward(request, response);
		}
		catch (Exception e) {
			System.out.println("SQLException nella servlet GestioneUtente");
			e.printStackTrace();
		}
		cliente=null;
	}

	


	/**
	 * Da chiamare all'istanziazione
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doRetrieve(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		doRetrieveOrdini(request, response);
		if(certificatoManager.doRetrieveByKey(cliente.getcF())==null)
			request.setAttribute("certificatoInserito", false);
		else
			request.setAttribute("certificatoInserito", true);
		
	}


	/**
	 * Invia alla pagina Utente.jsp gli ordini effettuati dal cliente, sia occhiali nuovi che rotti
	 * Sessione:
	 * OcchialiNuovi: Collection<OcchialeNuovo>, null se vuoto
	 * OcchialiRotti: Collection<OcchialeRotto>, null se vuoto
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doRetrieveOrdini(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		if(request.getSession().getAttribute("OcchialiNuovi")!=null)
			request.getSession().removeAttribute("OcchialiNuovi");
		if(request.getSession().getAttribute("OcchialiRotti")!=null)
			request.getSession().removeAttribute("OcchialiRotti");
		Collection<OcchialeNuovo> occhialeN=occhialeNuovoManager.doRetrieveByCondition(cliente.getcF());
		Collection<OcchialeRotto> occhialeR= occhialeRottoManager.doRetrieveByCondition(cliente.getcF());
		request.getSession().setAttribute("OcchialiNuovi", occhialeN);
		request.getSession().setAttribute("OcchialiRotti", occhialeR);
	}


	private void doAddCertificato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		//controllo se esiste già un certificato
		if(certificatoManager.doRetrieveByKey(cliente.getcF())==null) //Ho bisogno di mandare un messaggio all'utente, dato che nascondo l'accesso al form?
		{	
		String saveDir="C:\\Users\\Antonio\\eclipse--EEworkspace-servlet\\OtticaCrisci\\Data\\Certificati";
		for(Part certificato: request.getParts()) //tanto lo fa una sola volta
		{
			String filename= certificato.getSubmittedFileName();
			//Da aggiugere gestione dell'estensione
			saveDir+=File.separator+cliente.getcF();
			if(filename!=null && !filename.equals("")) {
				certificato.write(saveDir); 
				System.out.println("Directory di salvataggio: " + saveDir);
				certificatoManager.doSave(new Certificato( cliente.getcF(),saveDir,false));
				request.setAttribute("certificatoInserito", true);
			}
			else
				request.setAttribute("certificatoInserito", false);
		}
		}
	}

	/**
	 * Cambia password
	 * Richiede attributo "passwordVecchia" e "passowrdNuova"
	 * @param request
	 * @param response
	 * @throws SQLException
	 */
	private void doModificaPassword(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String password=request.getParameter("passwordVecchia");
		if(cliente.getPassword().equals(password)) {
			cliente.setPassword(request.getParameter("passwordNuova"));
			clienteManager.doSave(cliente);
			request.setAttribute("passwordCambiata", true);
		}
		else {
			request.setAttribute("passwordCambiata", false);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
