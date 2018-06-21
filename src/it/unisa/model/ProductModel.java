package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductModel<T,J> {
	
	public T doRetrieveByKey(J code) throws SQLException;
	
	public Collection<T> doRetrieveAll(String order) throws SQLException;
	
	public void doSave(T product) throws SQLException;
	
	public void doUpdate(T product) throws SQLException;
	
	public boolean doDelete(J code) throws SQLException;
}
