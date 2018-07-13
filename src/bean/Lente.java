package bean;

public class Lente {

	Integer id;
	int diottria,peso,prezzo,partitaIva;
	String materiale,tipo;
	
	//Sorry per i nomi ma mi scocciavo, è tardi
	public Lente(Integer id2, int gradazione, String string, int i, int j, String modello, int k) {
		id=id2;
		diottria=gradazione;
		materiale=string;
		peso=i;
		prezzo=j;
		tipo=modello;
		partitaIva=k;
		
	}
	
	public Lente() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDiottria() {
		return diottria;
	}
	public void setDiottria(int diottria) {
		this.diottria = diottria;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	public String getMateriale() {
		return materiale;
	}
	public void setMateriale(String materiale) {
		this.materiale = materiale;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(int partitaIva) {
		this.partitaIva = partitaIva;
	}
	
	
}
