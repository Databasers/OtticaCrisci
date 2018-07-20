package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Opzioni implements Serializable{

	boolean distinct;
	ArrayList<String> select= new ArrayList<>();
	ArrayList<Where> where= new ArrayList<>();
	boolean sort;
	String sortBy;
	boolean in,not;
	Opzioni subQuery;
	
	public Opzioni(boolean distinct2,ArrayList<String> tab2,ArrayList<Where> where2,boolean sort2,String sortBy2,boolean in2,boolean not2,Opzioni subQuery2) {
		distinct=distinct2;
		for(String e: tab2){
			select.add(e);
		}
		
		if(where2==null)
			where=null;
		else
			for(Where e: where2){
				where.add(e);
			}
		in=in2;
		not=not2;
		subQuery=subQuery2;
		sort=sort2;
		sortBy=sortBy2;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public ArrayList<String> getTab() {
		return select;
	}

	public void setTab(ArrayList<String> tab) {
		this.select = tab;
	}

	public boolean isSort() {
		return sort;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public ArrayList<String> getSelect() {
		return select;
	}

	public void setSelect(ArrayList<String> select) {
		this.select = select;
	}

	public ArrayList<Where> getWhere() {
		return where;
	}

	public void setWhere(ArrayList<Where> where) {
		this.where = where;
	}



	public Opzioni getSubQuery() {
		return subQuery;
	}

	public void setSubQuery(Opzioni subQuery) {
		this.subQuery = subQuery;
	}

	public boolean isIn() {
		return in;
	}

	public void setIn(boolean in) {
		this.in = in;
	}

	public boolean isNot() {
		return not;
	}

	public void setNot(boolean not) {
		this.not = not;
	}
	
	
	
}
