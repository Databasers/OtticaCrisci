package bean;

import java.io.Serializable;

public class Fornitore implements Serializable{

	int partitaIva;
	String nome;
	
	public int getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(int partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
