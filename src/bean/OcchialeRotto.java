package bean;

import java.sql.Date;

// `IDOcchiale`, `Prezzo`, `DataRitiro`, `DataConsegna`, `Entit‡Danno`, `CodiceFiscale`

public class OcchialeRotto {

	int idOcchiale,prezzo;
	Date dataConsegna,dataRitiro;
	String tipoDanno,cF;
	
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
	public String getcF() {
		return cF;
	}
	public void setcF(String cF) {
		this.cF = cF;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	
}
