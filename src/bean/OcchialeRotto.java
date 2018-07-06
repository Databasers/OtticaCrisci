package bean;

import java.sql.Date;

// `IDOcchiale`, `Prezzo`, `DataRitiro`, `DataConsegna`, `Entit‡Danno`, `CodiceFiscale`

public class OcchialeRotto extends Occhiale{

	Date dataConsegna,dataRitiro;
	String tipoDanno;
	

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
