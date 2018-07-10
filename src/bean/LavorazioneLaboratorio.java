package bean;

import java.sql.Date;

public class LavorazioneLaboratorio {
	
	Integer codAddetto,codLavorazione,oR_idOcchiale,oN_idOcchiale;
	Date dataInizio,dataFine;
	String tipo;
	
	public LavorazioneLaboratorio() { }
	
	public LavorazioneLaboratorio(Integer codLavorazione1, Integer nextInt, String tipo1, Date data, Date object2, Integer id,
			Integer object3) {
		codLavorazione=codLavorazione1;
		codAddetto=nextInt;
		tipo=tipo1;
		dataInizio=data;
		dataFine=object2;
		oN_idOcchiale=id;
		oR_idOcchiale=object3;
		
	}
	public Integer getCodAddetto() {
		return codAddetto;
	}
	public void setCodAddetto(Integer codAddetto) {
		this.codAddetto = codAddetto;
	}
	public Integer getCodLavorazione() {
		return codLavorazione;
	}
	public void setCodLavorazione(Integer codLavorazione) {
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
	public Integer getoR_idOcchiale() {
		return oR_idOcchiale;
	}
	public void setoR_idOcchiale(Integer oR_idOcchiale) {
		this.oR_idOcchiale = oR_idOcchiale;
	}
	public Integer getoN_idOcchiale() {
		return oN_idOcchiale;
	}
	public void setoN_idOcchiale(Integer oN_idOcchiale) {
		this.oN_idOcchiale = oN_idOcchiale;
	}
	

}
