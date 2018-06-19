package bean;

import java.util.Date;

public class LavorazioneDeposito {

	int codAddetto,codLavorazione;
	String pos;
	Date dataIngresso,dataUscita;
	
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
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public Date getDataIngresso() {
		return dataIngresso;
	}
	public void setDataIngresso(Date dataIngresso) {
		this.dataIngresso = dataIngresso;
	}
	public Date getDataUscita() {
		return dataUscita;
	}
	public void setDataUscita(Date dataUscita) {
		this.dataUscita = dataUscita;
	}
	
}
