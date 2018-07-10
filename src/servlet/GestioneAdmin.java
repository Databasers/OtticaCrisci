package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Admin;
import bean.Certificato;
import bean.LavorazioneDeposito;
import bean.LavorazioneLaboratorio;
import bean.OcchialeNuovo;
import bean.OcchialeRotto;
import bean.SessioneUtente;
import managerBean.AdminManager;
import managerBean.CertificatoManager;
import managerBean.DepositoManager;
import managerBean.LaboratorioManager;
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
    OcchialeNuovoManager occhialeNuovo;
	OcchialeRottoManager occhialeRotto;
	CertificatoManager certificato;
	LaboratorioManager managerL= new LaboratorioManager();
	DepositoManager managerD= new DepositoManager();
	
    public GestioneAdmin() {
        super();
        occhialeNuovo= new OcchialeNuovoManager(); 
        occhialeRotto= new OcchialeRottoManager();
        certificato= new CertificatoManager();
        }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=(String)request.getParameter("action");
		su=(SessioneUtente)request.getSession().getAttribute("Utente");
		try {
		if(action.equalsIgnoreCase("certificato"))
			doRetrieveCertificati(request,response);
		if(action.equalsIgnoreCase("occhiali"))
			doRetrieveOcchiali(request,response);
		if(action.equalsIgnoreCase("dati"))
			doRetrieveDati(request,response);
		if(action.equalsIgnoreCase("modCertificato"))
			doModificaCertificato(request,response);
		if(action.equalsIgnoreCase("modOcchiali"))
			doModificaOcchiali(request,response);
		
		RequestDispatcher x= getServletContext().getRequestDispatcher("/HTML/Admin.jsp");
		x.forward(request, response);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * Funziona, non toccare nulla
	 * @param request
	 * @param response
	 * @throws SQLException
	 */
	private void doModificaOcchiali(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		System.out.println("Inizio la modifica degli occhiali");
		String tabella=request.getParameter("tabella");
		String code=request.getParameter("code");
		int codice=Integer.parseInt(code);
		Integer IDOcchialeNuovo;
		Integer IDOcchialeRotto;
		if(tabella.equalsIgnoreCase("occhiale_nuovo")) {
			IDOcchialeNuovo=codice;
			IDOcchialeRotto=null;
		}
		else {
			IDOcchialeNuovo=null;
			IDOcchialeRotto=codice;
		}
		
		String azione=request.getParameter("spostamento");
		System.out.println("L'azione da fare e': " + azione); 
		if(azione.equalsIgnoreCase("deposito"))
			modifica(request, response, IDOcchialeNuovo, IDOcchialeRotto,"deposito",false);
		if(azione.equalsIgnoreCase("laboratorio"))
			modifica(request, response, IDOcchialeNuovo, IDOcchialeRotto,"laboratorio",false);
		if(azione.equalsIgnoreCase("completato")) {
			Date data=new Date(LocalDateTime.now().getYear()-1900, LocalDateTime.now().getMonthValue()-1, LocalDateTime.now().getDayOfMonth());
			OcchialeNuovo occhialeN;
			OcchialeRotto occhialeR;
			String stato;
			if(tabella.equalsIgnoreCase("occhiale_nuovo")) {
				occhialeN= occhialeNuovo.doRetrieveByKey(IDOcchialeNuovo);
				occhialeN.setDataRitiro(data);
				stato=occhialeN.getStato();
				occhialeN.setStato("completato");
				occhialeNuovo.doUpdate(occhialeN);
			}else {
				occhialeR= occhialeRotto.doRetrieveByKey(IDOcchialeRotto);
				occhialeR.setDataRitiro(data);
				stato=occhialeR.getStato();
				occhialeR.setStato("completato");
				occhialeRotto.doUpdate(occhialeR);
			}
			if(stato.equalsIgnoreCase("In lavorazione"))
				modifica(request, response, IDOcchialeNuovo, IDOcchialeRotto,"deposito",true);
			else
				modifica(request, response, IDOcchialeNuovo, IDOcchialeRotto,"laboratorio",true);
		}
		
	}
		
	private void modifica(HttpServletRequest request, HttpServletResponse response, Integer IDOcchialeNuovo, Integer IDOcchialeRotto,String tabella,boolean completato) throws SQLException {
		System.out.println("Inizio la modifica: ON="+IDOcchialeNuovo+" OR="+IDOcchialeRotto+" DoveInserire="+tabella+" completato="+completato);
		LavorazioneLaboratorio l;
		LavorazioneDeposito d;
		@SuppressWarnings("deprecation")
		Date data=new Date(LocalDateTime.now().getYear()-1900, LocalDateTime.now().getMonthValue()-1, LocalDateTime.now().getDayOfMonth());
		
		if(tabella.equalsIgnoreCase("laboratorio")) {
			System.out.println("Inizio in laboratorio");
			d=managerD.doRetrieveSpecificProcessing(IDOcchialeNuovo, IDOcchialeRotto, null);
			System.out.println("Processato il dato");
			d.setDataUscita(data);
			managerD.doUpdate(d);
			if(!completato) {
				System.out.println("Entro dopo completato");
				l= new LavorazioneLaboratorio(null, 3, "montaggio", data, null, IDOcchialeNuovo, IDOcchialeRotto);
				System.out.println("Salvo in laboratorio");
				managerL.doSaveAI(l);
				System.out.println("ON:" + IDOcchialeNuovo + "  OR:" + IDOcchialeRotto);
				if(IDOcchialeNuovo!=null) {
					OcchialeNuovo on=occhialeNuovo.doRetrieveByKey(IDOcchialeNuovo);
					on.setStato("In Lavorazione");
					occhialeNuovo.doUpdate(on);
				}
				else {
					OcchialeRotto on=occhialeRotto.doRetrieveByKey(IDOcchialeRotto);
					on.setStato("In Lavorazione");
					occhialeRotto.doUpdate(on);
				}	
			}
			
		}	
		if(tabella.equalsIgnoreCase("deposito")) {
			System.out.println("Inizio in deposito");
			l=managerL.doRetrieveSpecificProcessing(IDOcchialeNuovo, IDOcchialeRotto);
			System.out.println("Processato il dato");
			l.setDataFine(data);
			System.out.println("ID ON:"+l.getoN_idOcchiale()+"  OR:" +l.getoR_idOcchiale());
			managerL.doUpdate(l);
			if(!completato) {
				System.out.println("Entro dopo completato");
				d= new LavorazioneDeposito(null, 7, IDOcchialeNuovo, IDOcchialeRotto, null, "5x", data, null);
				System.out.println("Salvo in deposito");
				managerD.doSaveAI(d);
				if(IDOcchialeNuovo!=null) {
					OcchialeNuovo on=occhialeNuovo.doRetrieveByKey(IDOcchialeNuovo);
					on.setStato("In Deposito");
					occhialeNuovo.doUpdate(on);
				}
				else {
					OcchialeRotto on=occhialeRotto.doRetrieveByKey(IDOcchialeRotto);
					on.setStato("In Deposito");
					occhialeRotto.doUpdate(on);
				}
			}
		}	
		
	}
	

	private void doRetrieveCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		//da fare in ajax se abbiamo tempo
		
	}

	private void doModificaCertificato(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String v=request.getParameter("valido");
		Boolean valido=Boolean.parseBoolean(v);
		System.out.println("Il valore di valido è: " + valido);
		String code=request.getParameter("code");
		Certificato c=certificato.doRetrieveByKey(code);
		c.setValidato(true);
		c.setValido(valido);
		certificato.doUpdate(c);
		modifyLabel("Certificato", request, response);
		if(request.getAttribute("certificati")!=null)
			request.removeAttribute("certificati");
	}

	private void doRetrieveDati(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		AdminManager adminManager= new AdminManager();
		Admin admin=adminManager.doRetrieveByKey(su.getcF());
		modifyLabel("Dati", request, response);
		if(request.getAttribute("datiAdmin")!=null)
			request.removeAttribute("datiAdmin");
		request.setAttribute("datiAdmin", admin);
	}

	private void doRetrieveOcchiali(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		Collection<OcchialeNuovo> elencoNuovo=occhialeNuovo.doRetrieveIfNotCompleted("IDOcchiale");
		Collection<OcchialeRotto> elencoRotto=occhialeRotto.doRetrieveIfNotCompleted("IDOcchiale");
		if(request.getAttribute("occhialiNuovi")!=null)
			request.removeAttribute("occhialiNuovi");
		if(request.getAttribute("occhialiRotti")!=null)
			request.removeAttribute("occhialiRotti");
		request.setAttribute("occhialiNuovi", elencoNuovo);
		request.setAttribute("occhialiRotti", elencoRotto);
		modifyLabel("Occhiali", request, response);
		
	}

	private void doRetrieveCertificati(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		Collection<Certificato> elenco=certificato.doRetrieveIfNotValidated("CodiceFiscale");
		for(Certificato e: elenco)
			System.out.println("I valori sono: "+e.isValidato() + " " + e.isValido() + " " + e.getcF());
		modifyLabel("Certificato", request, response);
		if(request.getAttribute("certificati")!=null)
			request.removeAttribute("certificati");
		request.setAttribute("certificati", elenco);
	}
	
	private void modifyLabel(String l,HttpServletRequest request, HttpServletResponse response) {
		label=l;
		if(request.getAttribute("label")!=null)
			request.removeAttribute("label");
		request.setAttribute("label", label);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
