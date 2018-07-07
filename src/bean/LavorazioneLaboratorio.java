package bean;

import java.sql.Date;

public class LavorazioneLaboratorio {
	
	int codAddetto,codLavorazione,oR_idOcchiale,oN_idOcchiale;
	Date dataInizio,dataFine;
	String tipo;
	
	public LavorazioneLaboratorio() { }
	
	public LavorazioneLaboratorio(Integer codLavorazione, int nextInt, String tipo, Date data, Date object2, Integer id,
			Integer object3) {
		this.codLavorazione=codLavorazione;
		codAddetto=nextInt;
		this.tipo=tipo;
		dataInizio=data;
		dataFine=object2;
		oN_idOcchiale=id;
		oR_idOcchiale=object3;
		
	}
	public int getCodAddetto() {
		return codAddetto;
	}
	public void setCodAddetto(int codAddetto) {
		this.codAddetto = codAddetto;
	}
	public int getCodLavorazione() {
		return codLavorazione;
	}
	public void setCodLavorazione(int codLavorazione) {
		this.codLavorazione = codLavorazione;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getoR_idOcchiale() {
		return oR_idOcchiale;
	}
	public void setoR_idOcchiale(int oR_idOcchiale) {
		this.oR_idOcchiale = oR_idOcchiale;
	}
	public int getoN_idOcchiale() {
		return oN_idOcchiale;
	}
	public void setoN_idOcchiale(int oN_idOcchiale) {
		this.oN_idOcchiale = oN_idOcchiale;
	}
	

}
