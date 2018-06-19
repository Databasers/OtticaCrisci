package bean;

import java.util.Date;

public class OcchialeNuovo {
	
	int idOcchiale,prezzo;
	String stato;
	Date dataOrdine,dataRitiro;
	
	public int getId() {
		return idOcchiale;
	}
	public void setId(int id) {
		this.idOcchiale = id;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public Date getDataOrdine() {
		return dataOrdine;
	}
	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	public Date getDataRitiro() {
		return dataRitiro;
	}
	public void setDataRitiro(Date dataRitiro) {
		this.dataRitiro = dataRitiro;
	}
	
	
	

}
