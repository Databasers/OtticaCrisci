package bean;

public abstract class Occhiale {
	
	Integer idOcchiale,prezzo;
	String cF;
	
	public Occhiale() {
		
	}

	public Occhiale(Integer idOcchiale2, double d, String getcF) {
		idOcchiale=idOcchiale2;
		prezzo=(int)d;
		cF=getcF;
	}
	public Integer getId() {
		return idOcchiale;
	}
	public void setId(Integer idOcchiale) {
		this.idOcchiale = idOcchiale;
	}
	public Integer getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}
	public String getcF() {
		return cF;
	}
	public void setcF(String cf) {
		this.cF = cf;
	}

}
