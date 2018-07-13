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
		while(lista.hasNext()) 
			if(lista.next().equals(elem))
				lista.remove();
		
	}
	
	public List<T> getList()
	{
		return list;
	}

}
