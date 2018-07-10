package bean;

public class Frame {

	int id,prezzo,peso;
	String modello,colore,marchio,materiale,urlImmagine;
	int PartitaIva;
	
	public Frame(Integer object, String modello2, String colore2, Integer peso2, String materiale2, Integer prezzo2,
			Integer partitaIva2, String marchio2, String urlImmagine2) {
		
		this.id=object;
		this.modello=modello;
		colore=colore2;
		peso=peso2;
		materiale=materiale2;
		prezzo=prezzo2;
		PartitaIva=partitaIva2;
		marchio=marchio2;
		urlImmagine=urlImmagine2;		
	}
	
	public Frame() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public String getMarchio() {
		return marchio;
	}
	public void setMarchio(String marchio) {
		this.marchio = marchio;
	}
	public int getPartitaIva() {
		return PartitaIva;
	}
	public void setPartitaIva(int partitaIva) {
		PartitaIva = partitaIva;
	}
	public String getMateriale() {
		return materiale;
	}
	public void setMateriale(String materiale) {
		this.materiale = materiale;
	}
	public String getUrlImmagine() {
		return urlImmagine;
	}
	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}

	
}
