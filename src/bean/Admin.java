package bean;

import java.io.Serializable;
import java.sql.Date;

public class Admin implements Serializable{

	private String nome,cognome,cF,password;
	private Date dataAssunzione,dataNascita;
	private int telefono;
	
	public Admin(String cf2, String nome2, String cognome2, String password2, Date assunzione, Date nascita,Integer telefono) {
		cF=cf2;
		nome=nome2;
		cognome=cognome2;
		password=password2;
		dataAssunzione=assunzione;
		dataNascita=nascita;
		this.telefono=telefono;
	}


	public Admin() {
		
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
		return "Admin [nome=" + nome + ", cognome=" + cognome + ", cF=" + cF + ", password=" + password + "]";
	}


	public Date getDataAssunzione() {
		return dataAssunzione;
	}


	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}


	public Date getDataNascita() {
		return dataNascita;
	}


	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	
	

}
