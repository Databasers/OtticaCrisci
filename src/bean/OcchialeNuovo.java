package bean;

import java.sql.Date;

// `IDOcchiale`, `Prezzo`, `DataRitiro`, `IDLente`, `IDFrame`, `CodiceFiscale`, `DataOrdine`, `Stato`

public class OcchialeNuovo extends Occhiale {

	int  idLente, idFrame;
	String stato;
	Date dataOrdine, dataRitiro;

	public OcchialeNuovo(Integer idOcchiale2, double d, Date dr, int id, int id2, String getcF, Date data,
			String string) {
		
		super(idOcchiale2,d,getcF);
		dataRitiro=dr;
		idLente=id;
		idFrame=id2;
		dataOrdine=data;
		stato=string;
		
	}

	public OcchialeNuovo() {
		super();
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
