package bean;

import java.io.Serializable;

public class Certificato implements Serializable{
	
	boolean valido,validato;
	String url,cF;
	
	public Certificato(String getcF, String saveDir, boolean b) {
		cF=getcF;
		url=saveDir;
		valido=b;
	}
	public Certificato () {
		
	}
	
	public boolean isValido() {
		return valido;
	}
	public void setValido(boolean valido) {
		this.valido = valido;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getcF() {
		return cF;
	}
	public void setcF(String cF) {
		this.cF = cF;
	}
	public boolean isValidato() {
		return validato;
	}
	public void setValidato(boolean validato) {
		this.validato = validato;
	}
	

}
