package bean;

import java.io.Serializable;

public class SessioneUtente implements Serializable{
	
	String cF,ruolo,nome,cognome;
	
	public SessioneUtente(Cliente c,String r) {
		cF=c.getcF();
		nome=c.getNome();
		cognome=c.getCognome();
		ruolo=r;
	}

	public SessioneUtente(Admin c, String r) {
		cF=c.getcF();
		nome=c.getNome();
		cognome=c.getCognome();
		ruolo=r;
	}

	public String getcF() {
		return cF;
	}

	public void setcF(String cF) {
		this.cF = cF;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	

}
