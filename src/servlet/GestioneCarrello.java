package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.unisa.model.*;
import managerBean.CertificatoManager;
import managerBean.ClienteManager;
import managerBean.FrameManager;
import managerBean.LenteManager;
import managerBean.OcchialeNuovoManager;
import bean.Frame;
import bean.Lente;
import bean.OcchialeNuovo;
import bean.SessioneUtente;

@WebServlet("/gestione")
public class GestioneCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ClienteManager cliente= new ClienteManager();
	CertificatoManager certificato= new CertificatoManager();
	LenteManager lente= new LenteManager();
	FrameManager model= new FrameManager();
	OcchialeNuovoManager occhialeNuovo= new OcchialeNuovoManager();
	Carrello<Frame> carrello;
	
    public GestioneCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recupero il carrello se esiste, altrimenti lo creo
		request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
		carrello= (Carrello) request.getSession().getAttribute("carrello");
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
				else if(action.equalsIgnoreCase("checkout")) {
					doCheckout(request,response);
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

	//Bisogna sistemare l'attributo TipoLente
	private void doCheckout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String dispatcher="/HTML/Utente.jsp";
		SessioneUtente su=(SessioneUtente) request.getSession().getAttribute("su");
		if(su==null || !su.getRuolo().equalsIgnoreCase("utente")) { //se non è loggato
			dispatcher="/HTML/Login.jsp";
		}
		else { 
			if(certificato.doRetrieveByKey(su.getcF()).isValido()) { //se il certificato è valido
			List<Frame> lista = carrello.getList();
			//Per ogni frame nel carrello, creo un occhiale 
			for(Frame f: lista) {
				int gradazione=cliente.doRetrieveByKey(su.getcF()).getGradazione();
				//creo la lente
				//`IDLente`, `Diottria`, `Materiale`, `Peso`, `Prezzo`, `TipoLente`, `PartitaIva`
				Lente l=createAndRetrieveLente(f,gradazione);
				//Creo l'occhiale
				// `IDOcchiale`, `Prezzo`, `DataRitiro`, `IDLente`, `IDFrame`, `CodiceFiscale`, `DataOrdine`, `Stato`
				@SuppressWarnings("deprecation")
				Date data=new Date(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth());
				occhialeNuovo.doSave(new OcchialeNuovo(null,(f.getPrezzo()+l.getPrezzo())*1.5,null,l.getId(),f.getId(),su.getcF(),data,"In Lavorazione"));
			}
		}
		else {
			//Da inserire nella JSP della pagina Cliente
			request.removeAttribute("DaValidare");
			request.setAttribute("DaValidare", true);
		}
		}
		RequestDispatcher x=getServletContext().getRequestDispatcher(dispatcher);
		x.forward(request, response);
		
		}
		
	/**
	 * Crea una nuova lente,la salva e la recupera dal db
	 * Necessaria in quanto la chiave di Lente è auto-increment, pertanto alla definizione è semplicemente null
	 * @param f
	 * @param gradazione
	 * @return
	 * @throws SQLException
	 */
	private synchronized Lente createAndRetrieveLente(Frame f,int gradazione) throws SQLException {
		Lente l=new Lente(null,gradazione,"vetro",50,80*gradazione,f.getModello(),123456);
		lente.doSave(l);
		ArrayList<Lente> elenco=(ArrayList<Lente>) lente.doRetrieveAll("IDLente");
		l=elenco.get(elenco.size()-1);
		return l;
	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
