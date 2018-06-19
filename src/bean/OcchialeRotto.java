package bean;

import java.util.Date;

public class OcchialeRotto {

	int idOcchiale;
	Date dataConsegna,dataRitiro;
	String tipoDanno;
	
	public int getIdOcchiale() {
		return idOcchiale;
	}
	public void setIdOcchiale(int idOcchiale) {
		this.idOcchiale = idOcchiale;
	}
	public Date getDataConsegna() {
		return dataConsegna;
	}
	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}
	public Date getDataRitiro() {
		return dataRitiro;
	}
	public void setDataRitiro(Date dataRitiro) {
		this.dataRitiro = dataRitiro;
	}
	public String getTipoDanno() {
		return tipoDanno;
	}
	public void setTipoDanno(String tipoDanno) {
		this.tipoDanno = tipoDanno;
	}
	
}
