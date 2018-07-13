package managerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.fabric.xmlrpc.base.Array;

import bean.Frame;
import bean.Opzioni;
import bean.Where;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.ProductModel;

public class FrameManager implements ProductModel<Frame, Integer> {

	private static final String TableName="Frame";
	
public Collection<Frame> doRetrieveByCond(Opzioni opzioni) throws SQLException {
		
		Collection<Frame> c= new LinkedList<Frame>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="Select ";
		if(opzioni.isDistinct())
			sql+=" distinct ";
		for(String s: opzioni.getTab()) {
			sql+=" "+s+",";
		}
		sql=sql.substring(0, sql.length()-1);
		sql+=" from Frame";
		
		if(opzioni.getWhere()!=null) {
			sql+=" where ";
			for(Where e: opzioni.getWhere()) {
				if(sql.substring(sql.length()-13, sql.length()-1).equalsIgnoreCase("Frame where "))
					sql+=" "+e.getClausola()+"= "+e.getValore();
				else
					sql+=" AND "+e.getClausola()+"= "+e.getValore();
			}
		}
		
		if(opzioni.isIn()) {
			sql+=" AND ";
			if(opzioni.isNot())
				sql+=" NOT";
			sql+=" IN (";
			
		//parte la subQuery
		Opzioni subQuery=opzioni.getSubQuery();
		sql+=" Select ";
		if(subQuery.isDistinct())
			sql+=" distinct ";
		for(String s: subQuery.getTab()) {
			sql+=" "+s+",";
		}
		sql=sql.substring(0, sql.length()-1);
		sql+=" from Occhiale_nuovo";
		
		if(subQuery.getWhere()!=null) {
			sql+=" where ";
			for(Where e: subQuery.getWhere()) {
				if(sql.substring(sql.length()-13, sql.length()-1).equalsIgnoreCase("Frame where "))
					sql+=" "+e.getClausola()+"= "+e.getValore();
				else
					sql+=" AND "+e.getClausola()+"= "+e.getValore();
			}
		}
		if(subQuery.isIn()) {
			sql+=" AND ";
			if(subQuery.isNot())
				sql+=" NOT";
			sql+=" IN (";
		}
		}
		
		ArrayList<String> elenco= opzioni.getTab();
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			System.out.println("doRetrieveByCond: "+ preparedStatement.toString());
			
			ResultSet rs=preparedStatement.executeQuery();
			System.out.println("Quesry effettuata");
			while(rs.next()) {
				Frame temp= new Frame();
				temp.setId(rs.getInt("IDFrame"));
				if(elenco.contains("Colore")) {
				temp.setColore(rs.getString("Colore"));
				}
				if(elenco.contains("Marchio")) {
					temp.setMarchio(rs.getString("Marchio"));
				}
				if(elenco.contains("Modello")) {
					temp.setModello(rs.getString("Modello"));
				}
				if(elenco.contains("Peso")) {
					temp.setPeso(rs.getInt("Peso"));
				}
				if(elenco.contains("Prezzo")) {
					temp.setPeso(rs.getInt("Prezzo"));
				}
				if(elenco.contains("PartitaIva")) {
					temp.setPartitaIva(rs.getInt("PartitaIva"));
				}
				if(elenco.contains("Materiale")) {
					temp.setMateriale(rs.getString("Materiale"));
				}
				if(elenco.contains("UrlImmagine")) {
					temp.setUrlImmagine(rs.getString("UrlImmagine"));
				}
				
				c.add(temp);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return c;
	}
	
	
	@Override
	public Frame doRetrieveByKey(Integer code) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		Frame temp=new Frame();
		
		String sql="SELECT* FROM "+TableName+" WHERE IDFrame=?  ";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, code);
			System.out.println("Query: " + preparedStatement.toString());
			
			ResultSet rs= preparedStatement.executeQuery();
			
			rs.next();
			temp.setId(rs.getInt("IDFrame"));
			temp.setColore(rs.getString("Colore"));
			temp.setMarchio(rs.getString("Marchio"));
			temp.setModello(rs.getString("Modello"));
			temp.setPeso(rs.getInt("Peso"));
			temp.setPrezzo(rs.getInt("Prezzo"));
			temp.setPartitaIva(rs.getInt("PartitaIva"));
			temp.setMateriale(rs.getString("Materiale"));
			temp.setUrlImmagine(rs.getString("UrlImmagine"));
			
		}finally {
			try {
			if(preparedStatement!=null)
				preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return temp;
	}

	@Override
	public Collection<Frame> doRetrieveAll(String order) throws SQLException {
		
		Collection<Frame> c= new LinkedList<Frame>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql= "SELECT * FROM "+TableName+"";
		if(order != null && !order.equals("")) {
			sql += " ORDER BY " + order;
		}
			
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			System.out.println("doRetrieveAll: "+ preparedStatement.toString());
			
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				Frame temp= new Frame();
				temp.setId(rs.getInt("IDFrame"));
				temp.setColore(rs.getString("Colore"));
				temp.setMarchio(rs.getString("Marchio"));
				temp.setModello(rs.getString("Modello"));
				temp.setPeso(rs.getInt("Peso"));
				temp.setPrezzo(rs.getInt("Prezzo"));
				temp.setPartitaIva(rs.getInt("PartitaIva"));
				temp.setMateriale(rs.getString("Materiale"));
				temp.setUrlImmagine(rs.getString("UrlImmagine"));
				c.add(temp);
			}
		}finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return c;
	}
	
	public void doSaveAI(Frame product) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="INSERT INTO "+TableName+" VALUES(null,?,?,?,?,?,?)";
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setString(1, product.getModello());
			preparedStatement.setString(2, product.getColore());
			preparedStatement.setInt(3, product.getPeso());
			preparedStatement.setString(4, product.getMateriale());
			preparedStatement.setInt(5, product.getPrezzo());
			preparedStatement.setInt(6, product.getPartitaIva());
			preparedStatement.setString(7, product.getMarchio());
			preparedStatement.setString(8, product.getUrlImmagine());
			
			
			System.out.println("doSave: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}

	@Override
	public void doSave(Frame product) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="INSERT INTO "+TableName+" VALUES(?,?,?,?,?,?,?)";
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setString(2, product.getModello());
			preparedStatement.setString(3, product.getColore());
			preparedStatement.setInt(4, product.getPeso());
			preparedStatement.setString(5, product.getMateriale());
			preparedStatement.setInt(6, product.getPrezzo());
			preparedStatement.setInt(7, product.getPartitaIva());
			preparedStatement.setString(8, product.getMarchio());
			preparedStatement.setString(9, product.getUrlImmagine());
			
			
			System.out.println("doSave: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}

	@Override
	public void doUpdate(Frame product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "UPDATE " + TableName
				+ " SET IDFrame = ?, Modello = ?, Colore= ?, Peso = ?, Materiale= ?, Prezzo= ?, "
				+ " PartitaIva= ?, Marchio= ?, UrlImmagine= ?"
				+ " WHERE IDFrame = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setString(2, product.getModello());
			preparedStatement.setString(3, product.getColore());
			preparedStatement.setInt(4, product.getPeso());
			preparedStatement.setString(5, product.getMateriale());
			preparedStatement.setInt(6, product.getPrezzo());
			preparedStatement.setInt(7, product.getPartitaIva());
			preparedStatement.setString(8, product.getMarchio());
			preparedStatement.setString(9, product.getUrlImmagine());
			preparedStatement.setInt(10, product.getId());
			
			System.out.println("doUpdate: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	}

	@Override
	public boolean doDelete(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TableName + " WHERE IDFrame = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1,code);

			System.out.println("doDelete: "+ preparedStatement.toString());
			result = preparedStatement.executeUpdate();
			
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

}
