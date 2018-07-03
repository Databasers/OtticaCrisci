package bean;

import java.sql.Date;

// `IDOcchiale`, `Prezzo`, `DataRitiro`, `IDLente`, `IDFrame`, `CodiceFiscale`, `DataOrdine`, `Stato`

public class OcchialeNuovo {

	int idOcchiale, prezzo, idLente, idFrame;
	String stato, cF;
	Date dataOrdine, dataRitiro;

	public OcchialeNuovo(Integer idOcchiale2, double d, Date dr, int id, int id2, String getcF, Date data,
			String string) {
		
		idOcchiale=idOcchiale2;
		prezzo=(int) d;
		dataRitiro=dr;
		idLente=id;
		idFrame=id2;
		cF=getcF;
		dataOrdine=data;
		stato=string;
		
	}

	public OcchialeNuovo() {
		// TODO Auto-generated constructor stub
	}

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

	public int getIdOcchiale() {
		return idOcchiale;
	}

	public void setIdOcchiale(int idOcchiale) {
		this.idOcchiale = idOcchiale;
	}

	public String getcF() {
		return cF;
	}

	public void setcF(String cF) {
		this.cF = cF;
	}

	public int getIdLente() {
		return idLente;
	}

	public void setIdLente(int idLente) {
		this.idLente = idLente;
	}

	public int getIdFrame() {
		return idFrame;
	}

	public void setIdFrame(int idFrame) {
		this.idFrame = idFrame;
	}

}
