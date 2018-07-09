package bean;

import java.sql.Date;

public class LavorazioneDeposito {

	int codAddetto,codLavorazione,oR_idOcchiale,oN_idOcchiale,idFrame;
	String pos;
	Date dataIngresso,dataUscita;
	
	public LavorazioneDeposito(Integer lavorazione,Integer addetto,Integer ON,Integer OR, Integer f, String posizione, Date ingresso, Date uscita) {
		codAddetto=addetto;
		codLavorazione=lavorazione;
		oR_idOcchiale=OR;
		oN_idOcchiale=ON;
		idFrame=f;
		pos=posizione;
		dataIngresso=ingresso;
		dataUscita=uscita;
	}
	
	public LavorazioneDeposito() {
		// TODO Auto-generated constructor stub
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
	public int getIdFrame() {
		return idFrame;
	}
	public void setIdFrame(int idFrame) {
		this.idFrame = idFrame;
	}
	
}
