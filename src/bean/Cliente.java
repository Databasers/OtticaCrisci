package bean;

import java.io.Serializable;

public class Cliente implements Serializable{
	
	private String nome,cognome,cF,password;
	private int gradazione;

	public Cliente() {
		
	}


	public Cliente(String cf2, String nome2, String cognome2, String password2, Integer gradazione) {
		cF=cf2;
		nome=nome2;
		cognome=cognome2;
		password=password2;
		this.gradazione=gradazione;
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

	
	public String getcF() {
		return cF;
	}

	
	public void setcF(String cF) {
		this.cF = cF;
	}

	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cognome=" + cognome + ", cF=" + cF + ", password=" + password + "]";
	}


	public int getGradazione() {
		return gradazione;
	}


	public void setGradazione(int gradazione) {
		this.gradazione = gradazione;
	}
	
	
	

}
