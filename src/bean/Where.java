package bean;

import java.io.Serializable;

public class Where implements Serializable
{

	String clausola;
	String valore;
	
	public Where(String clausola1, String valore1) {
		clausola=clausola1;
		valore=valore1;
	}
	public String getClausola() {
		return clausola;
	}
	public void setClausola(String clausola) {
		this.clausola = clausola;
	}
	public String getValore() {
		return valore;
	}
	public void setValore(String valore) {
		this.valore = valore;
	}
}
