package bean;

import java.util.Date;

public class LavorazioneLaboratorio {
	
	int codAddetto,codLavirazione,oR_idOcchiale,oN_idOcchiale;
	Date dataInizio,dataFine;
	enum tipoLavorazione {
			Montaggio,
			Riparazione;
	}
	tipoLavorazione tipo;
	
	public int getCodAddetto() {
		return codAddetto;
	}
	public void setCodAddetto(int codAddetto) {
		this.codAddetto = codAddetto;
	}
	public int getCodLavirazione() {
		return codLavirazione;
	}
	public void setCodLavirazione(int codLavirazione) {
		this.codLavirazione = codLavirazione;
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
	
	public tipoLavorazione getTipo() {
		return tipo;
	}
	public void setTipo(tipoLavorazione tipo) {
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
