package servlet;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import bean.Certificato;
import bean.Cliente;
import bean.Frame;
import bean.Lente;
import bean.OcchialeNuovo;
import bean.OcchialeRotto;
import bean.SessioneUtente;
import it.unisa.model.Carrello;
import managerBean.CertificatoManager;
import managerBean.ClienteManager;
import managerBean.FrameManager;
import managerBean.LenteManager;
import managerBean.OcchialeNuovoManager;
import managerBean.OcchialeRottoManager;
import utilities.CookieManager;

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

	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessioneUtente su=(SessioneUtente) request.getSession().getAttribute("Utente");
		try {
			cliente=clienteManager.doRetrieveByKey(su.getcF()); //recupero il cliente che riguarda questa chiamata
			System.out.println("Il codice fiscale di cliente Ë " + cliente.getcF());
			String action=(String)request.getParameter("action");
			
			if(action.equalsIgnoreCase("ajax"))
				doAjax(request,response);
			else {
					if(action.equalsIgnoreCase("modificaPassword"))
						doModificaPassword(request,response);
					if(action.equalsIgnoreCase("addCertificato"))
						doAddCertificato(request,response);
					if(action.equalsIgnoreCase("retrieveOrdini"))
						doRetrieveOrdini(request,response);
					if(action.equalsIgnoreCase("retrieve"))
						doRetrieve(request,response);
					if(action.equalsIgnoreCase("delCertificato"))
						doDelCertificato(request,response);
					response.sendRedirect(request.getContextPath() + "\\HTML\\Utente.jsp"); 
				}
			}
		catch (Exception e) {
			System.out.println("SQLException nella servlet GestioneUtente");
			e.printStackTrace();
		}
		cliente=null;
	}

	

	private void doDelCertificato(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Certificato c=certificatoManager.doRetrieveByKey(cliente.getcF());
		if(!c.isValido() && c.isValidato() ) { //se non Ë valido ma Ë stato controllato
			File x= new File("C:\\Users\\Antonio\\eclipse--EEworkspace-servlet\\OtticaCrisci\\Data\\Certificati"+File.separator+c.getcF());
			certificatoManager.doDelete(c.getcF());
			x.delete();
			if(request.getAttribute("certificato")!=null)
				request.removeAttribute("certificato");
		}
		
	}

	/**
	 * Gestione ajax per dettagli su di un ordine
	 * Necessita un attributo param=IDOcchiale-NomeTabella
	 * Restituisce un file XML da leggere nell'handler ajax con gli attributi inseriti in tag xml (es. attributo IDFrame in <IDFrame>Valore</IDFrame>
	 * per occhiali nuovi, gli attributi comuni ad frame e lente hanno una F o una L finale nel nome del tag xml per differenziarli
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException 
	 */
	private void doAjax(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		response.setContentType("text/xml");
		StringBuffer risposta= new StringBuffer();
		
		risposta.append("<info>");
		String param= (String)request.getParameter("param");
		System.out.println("param=" + param);
		System.out.println("IndexOf stampa: " + param.trim().indexOf('-') );
		int idOcchiale=Integer.parseInt(param.trim().substring(0, param.trim().indexOf('-'))); //recupera l'id tra la pos. 0 e il carattere "-"
		String table=param.trim().substring(param.trim().indexOf("-")+1);
		System.out.println("IdOcchiale=" + idOcchiale + "\nTable name= " + table);
		if(table.equalsIgnoreCase("occhialeNuovo")) {
			OcchialeNuovo occhiale=occhialeNuovoManager.doRetrieveByKey(idOcchiale);
			int idFrame=occhiale.getIdFrame();
			int idLente=occhiale.getIdLente();
			FrameManager frameM= new FrameManager();
			LenteManager lenteM= new LenteManager();
			Frame frame=frameM.doRetrieveByKey(idFrame);
			Lente lente=lenteM.doRetrieveByKey(idLente);
			//`IDFrame`, `Modello`, `Colore`, `Peso`, `Materiale`, `Prezzo`, `Marchio`, `UrlImmagine`
			risposta.append("<IDFrame>"+frame.getId()+"</IDFrame>");
			risposta.append("<PrezzoF>"+frame.getPrezzo()+"</PrezzoF>");
			risposta.append("<Modello>"+frame.getModello()+"</Modello>");
			risposta.append("<Colore>"+frame.getColore()+"</Colore>");
			risposta.append("<PesoF>"+frame.getPeso()+"</PesoF>");
			risposta.append("<MaterialeF>"+frame.getMateriale()+"</MaterialeF>");
			risposta.append("<Marchio>"+frame.getMarchio()+"</Marchio>");
			risposta.append("<URLImmagine>"+frame.getUrlImmagine()+"</URLImmagine>");
			//`IDLente`, `Diottria`, `Materiale`, `Peso`, `Prezzo`, `TipoLente`
			risposta.append("<PesoL>"+lente.getPeso()+"</PesoL>");
			risposta.append("<IDLente>"+lente.getId()+"</IDLente>");
			risposta.append("<Diottria>"+lente.getDiottria()+"</Diottria>");
			risposta.append("<MaterialeL>"+lente.getMateriale()+"</MaterialeL>");
			risposta.append("<PrezzoL>"+lente.getPrezzo()+"</PrezzoL>");
			risposta.append("<TipoLente>"+lente.getTipo()+"</TipoLente>");
		}
		else {
			OcchialeRotto occhiale=occhialeRottoManager.doRetrieveByKey(idOcchiale);
			// `IDOcchiale`, `Prezzo`, `DataRitiro`, `DataConsegna`, `Entit‡Danno`, `CodiceFiscale`
			//Non so cosa restituire
		}
		risposta.append("</info>");
		response.getWriter().write(risposta.toString());
		System.out.println("Accesso terminato");
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
		Certificato c;
		if((c=certificatoManager.doRetrieveByKey(cliente.getcF()))==null)
			request.getSession().setAttribute("certificato", null);
		else
			request.getSession().setAttribute("certificato", c);
		
	}


	/**
	 * Invia alla pagina Utente.jsp gli ordini effettuati dal cliente, sia occhiali nuovi che rotti
	 * Request:
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
		request.getSession().setAttribute("OcchialiNuovi",occhialeN);
		request.getSession().setAttribute("OcchialiRotti",occhialeR);
	}

	/**
	 * Aggiunge un certificato inviato dall'utente
	 * Se ne Ë gi‡ presente uno, restituisce quello presente
	 * Se inserito uno non valido, ritorna il certificato con valore null
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 */
	private void doAddCertificato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		Certificato c;
		if(request.getAttribute("certificato")!=null) //elimino possibili rimasuglie
			request.removeAttribute("certificato");
		//controllo se esiste gi‡ un certificato
		if((c=certificatoManager.doRetrieveByKey(cliente.getcF()))==null) //Ho bisogno di mandare un messaggio all'utente, dato che nascondo l'accesso al form?
		{	
		String saveDir="C:\\Users\\giggi\\eclipse-workspace\\OtticaCrisci\\Data\\Certificati";
		for(Part certificato: request.getParts()) //tanto lo fa una sola volta
		{
			String filename= certificato.getSubmittedFileName();
			String estensione=filename.substring(filename.indexOf('.'));
			System.out.println("Estensione del file: "+ estensione);
			saveDir+=File.separator+cliente.getcF()+estensione;
			if(filename!=null && !filename.equals("")) {
				certificato.write(saveDir); 
				System.out.println("Directory di salvataggio: " + saveDir);
				certificatoManager.doSave(c=new Certificato( cliente.getcF(),saveDir,false));
				request.setAttribute("certificato", c);
			}
			else
				request.setAttribute("certificato", null);
		}
		}
		else
			request.setAttribute("certificato", c);
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
