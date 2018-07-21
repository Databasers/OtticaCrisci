package servlet;

import java.io.IOException;
import utilities.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.unisa.model.*;
import managerBean.CertificatoManager;
import managerBean.ClienteManager;
import managerBean.FrameManager;
import managerBean.LaboratorioManager;
import managerBean.LenteManager;
import managerBean.OcchialeNuovoManager;
import bean.Certificato;
import bean.Frame;
import bean.LavorazioneLaboratorio;
import bean.Lente;
import bean.OcchialeNuovo;
import bean.Opzioni;
import bean.SessioneUtente;
import bean.Where;

@WebServlet("/gestione")
public class GestioneCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ClienteManager cliente= new ClienteManager();
	CertificatoManager certificato= new CertificatoManager();
	LenteManager lente= new LenteManager();
	FrameManager model= new FrameManager();
	OcchialeNuovoManager occhialeNuovo= new OcchialeNuovoManager();
	LaboratorioManager lavorazioneManager= new LaboratorioManager();
	Carrello<Frame> carrello;
	String uuid=UUID.randomUUID().toString();
	
    public GestioneCarrello() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n Gestione Carrello \n");
		//Recupero il carrello se esiste, altrimenti lo creo
		request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
		carrello= (Carrello<Frame>) request.getSession().getAttribute("carrello");
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
				
			if(!action.equalsIgnoreCase("checkout")) { //Non deve toccare nuovamente i cookie ed il carrello
			request.getSession().setAttribute("carrello", carrello);
			//Creo il cookie carrello e lo salvo nella hash map, se è loggato
			SessioneUtente su=(SessioneUtente) request.getSession().getAttribute("Utente");
			if(su!=null) {
				System.out.println("\nAggiorno i valori del cookie di carrello\n");
				HashMap<String,Carrello> mappa=(HashMap<String,Carrello>)request.getServletContext().getAttribute("carrello");
				if(mappa==null)
					System.out.println("\nLa mappa di hash è null\n");
				else
					mappa.put(uuid, carrello);
				CookieManager.removeCookie(response, "CarrelloCookie"+su.getcF());
				CookieManager.addCookie(response, "CarrelloCookie"+su.getcF(), uuid, 60*60);
				request.getServletContext().setAttribute("carrello", mappa);
			}	
			}
			
			if(action.equalsIgnoreCase("addCart")) {
				response.sendRedirect(request.getContextPath() + "\\HTML\\Store.jsp"); 
			}
			if(action.equalsIgnoreCase("delCart")) {
				response.sendRedirect(request.getContextPath() + "\\HTML\\Carrello.jsp"); 
			}
			if(action.equalsIgnoreCase("checkout") && ((SessioneUtente) request.getSession().getAttribute("Utente"))!=null)
				response.sendRedirect(request.getContextPath() + "\\HTML\\Utente.jsp");
			
			System.out.println("\n Fine gestione Carrello \n");
			
		} catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		
		
		
		

		
	}

	//Bisogna sistemare l'attributo TipoLente
	private void doCheckout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		System.out.println("Checkout");
		String dispatcher="/HTML/Utente.jsp";
		SessioneUtente su=(SessioneUtente) request.getSession().getAttribute("Utente");
		if(su==null || !su.getRuolo().equalsIgnoreCase("utente")) { //se non è loggato
			System.out.println("Non è loggato");
			response.sendRedirect("/OtticaCrisci/HTML/Login.jsp");
		}
		else {
			Certificato c=certificato.doRetrieveByKey(su.getcF());
			if(c!=null && c.isValido()) { //se il certificato è valido
				List<Frame> lista = carrello.getList();
				//Per ogni frame nel carrello, creo un occhiale 
				for(Frame f: lista) {
					//controllo se esiste un frame non usato uguale in deposito, altrimenti lo creo
					ArrayList<String> select= new ArrayList<>();
					select.add("IDFrame");
					select.add("Prezzo");
					select.add("Peso");
					select.add("Modello");
					select.add("Colore");
					select.add("Marchio");
					select.add("Materiale");
					select.add("UrlImmagine");
					select.add("PartitaIva");
					ArrayList<Where> where= new ArrayList<>();
					//Colore, prezzo, materiale, modello, marchio, peso
					where.add(new Where("Colore",f.getColore()));
					where.add(new Where("Prezzo",""+f.getPrezzo()));
					where.add(new Where("Materiale",f.getMateriale()));
					where.add(new Where("Modello",f.getModello()));
					where.add(new Where("Marchio",f.getMarchio()));
					where.add(new Where("Peso",""+f.getPeso()));
					where.add(new Where("Colore",f.getColore()));
					
					//Creo il secondo campo opzioni
					ArrayList<String> select2= new ArrayList<>();
					select2.add("IDFrame");
					
					
					//Creo il campo opzioni per la subQuery
					Opzioni op2= new Opzioni(false, select2, null, false, null, false, false, null);
					
					//Creo il campo opzioni per la query
					Opzioni opzioni= new Opzioni(false, select, where, false, null, true, true, op2);
					
					//Inserisco tutto nel metodo;
					Collection<Frame> elenco=model.doRetrieveByCond(opzioni);				
					if(elenco.isEmpty()) {
						System.out.println("Devo Creare il frame");
						f= createAndRetrieveFrame(f); //Creo un nuovo frame
					}else {
						System.out.println("uso il frame in deposito");
						f=elenco.iterator().next(); //Uso uno di quelli recuperati
					}
					
					int gradazione=cliente.doRetrieveByKey(su.getcF()).getGradazione();
					//creo la lente
					Lente l=createAndRetrieveLente(f,gradazione);
					//Creo l'occhiale
					@SuppressWarnings("deprecation")
					Date data=new Date(LocalDateTime.now().getYear()-1900, LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth());
					OcchialeNuovo occhiale=createAndRetrieveOcchiale(l,f,su,data);
					//creo la lavorazione in laboratorio
					LavorazioneLaboratorio tmp= new LavorazioneLaboratorio(null,(new Random()).nextInt(50),"montaggio",data,null,occhiale.getId(),null);
					lavorazioneManager.doSave(tmp);
					
					
					}
				
				System.out.println("Lavoro sul carrello dopo il checkout");
				request.getSession().removeAttribute("carrello");
				Carrello test=(Carrello)request.getSession().getAttribute("carrello");
				CookieManager.removeCookie(response, "CarrelloCookie"+su.getcF());
				HashMap<String,Carrello> mappa=(HashMap<String,Carrello>)request.getServletContext().getAttribute("carrello");
				mappa.remove(uuid);
				request.getServletContext().setAttribute("carrello",mappa);
			}
			else {
				//Da inserire nella JSP della pagina Cliente
				request.removeAttribute("DaValidare");
				request.setAttribute("DaValidare", true);
			}
		}
		System.out.println("Fine checkout");
	}
		
	private synchronized Frame createAndRetrieveFrame(Frame f) throws SQLException {
		Frame frame= new Frame(null,f.getModello(),f.getColore(),f.getPeso(),f.getMateriale(),f.getPrezzo(),123456,f.getMarchio(),f.getUrlImmagine());
		model.doSaveAI(frame);
		ArrayList<Frame> elenco=(ArrayList<Frame>) model.doRetrieveAll("IDFrame");
		frame=elenco.get(elenco.size()-1);
		return frame;
	}


	/**
	 * Crea un nuovo occhiale nuovo,la salva e la recupera dal db
	 * Necessaria in quanto la chiave di Lente è auto-increment, pertanto alla definizione è semplicemente null
	 * @param f.
	 * @param gradazione
	 * @return
	 * @throws SQLException
	 */
	private synchronized OcchialeNuovo createAndRetrieveOcchiale(Lente l, Frame f,SessioneUtente su, Date data) throws SQLException {
		
		OcchialeNuovo occhiale=new OcchialeNuovo(null,(f.getPrezzo()+l.getPrezzo())*1.5,null,l.getId(),f.getId(),su.getcF(),data,"In Lavorazione");
		occhialeNuovo.doSave(occhiale);
		ArrayList<OcchialeNuovo> elenco=(ArrayList<OcchialeNuovo>) occhialeNuovo.doRetrieveAll("IDOcchiale");
		occhiale=elenco.get(elenco.size()-1);
		return occhiale;
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
