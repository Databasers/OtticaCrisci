package bean;

public class Cliente {
	
	private String nome,cognome,cF,password;

	
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
	
	
	

}
