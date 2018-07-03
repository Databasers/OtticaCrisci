package it.unisa.model;

import java.io.Serializable;
import java.util.ArrayList;
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
		for(T e: list){
			if(e.equals(elem))
				list.remove(elem);
		}
	}
	
	public List<T> getList()
	{
		return list;
	}

}
