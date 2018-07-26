package it.unisa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Carrello<T> implements Serializable{
	private static final long serialVersionUID = 1L;
  
	List<T> list;
	
	public Carrello(){
		list= new ArrayList<T>();
	}
	
	public void addElement(T elem)
	{
		list.add(elem);
	}
	
	public void delete(T elem)
	{
		Iterator<T> lista= list.iterator();
		if(!lista.hasNext())
			System.out.println("Il carrello +è vuoto");
		while(lista.hasNext()) 
			if(lista.next().equals(elem)) {
				System.out.println("Ho trovato l'elemento corrispondente");
				lista.remove();
			}
	}
	
	public List<T> getList()
	{
		return list;
	}

}
