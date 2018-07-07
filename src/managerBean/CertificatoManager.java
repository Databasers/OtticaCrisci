package managerBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.Certificato;
import it.unisa.model.*;

public class CertificatoManager implements ProductModel<Certificato,String> {

	private static final String TableName="certificato";
	
	@Override
	public Certificato doRetrieveByKey(String code) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		Certificato temp=new Certificato();
		
		String sql="SELECT* FROM "+TableName+" WHERE CodiceFiscale=?  ";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setString(1, code);
			System.out.println("Query: " + preparedStatement.toString());
			
			ResultSet rs= preparedStatement.executeQuery();
			
			rs.next();
			temp.setcF(rs.getString("CodiceFiscale"));
			temp.setUrl(rs.getString("Url"));
			temp.setValido(rs.getBoolean("Valido"));
			temp.setValidato(rs.getBoolean("Validato"));
			
				
		}catch (SQLException e) {
			temp=null;
		}
		finally {
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
	public Collection<Certificato> doRetrieveAll(String order) throws SQLException {
		
		Collection<Certificato> c= new LinkedList<Certificato>();
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
				Certificato temp= new Certificato();
				temp.setcF(rs.getString("CodiceFiscale"));
				temp.setUrl(rs.getString("Url"));
				temp.setValido(rs.getBoolean("Valido"));
				temp.setValidato(rs.getBoolean("Validato"));
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

	@Override
	public void doSave(Certificato product) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="INSERT INTO "+TableName+" VALUES(?,?,?)";
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setString(1, product.getcF());
			preparedStatement.setString(2, product.getUrl());
			preparedStatement.setBoolean(3, product.isValido());
			
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
	public void doUpdate(Certificato product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "UPDATE " + TableName
				+ " SET CodiceFiscale = ?, Url = ?, Valido = ?, validato=? "
				+ " WHERE CodiceFiscale = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getcF());
			preparedStatement.setString(2, product.getUrl());
			preparedStatement.setBoolean(3, product.isValido());
			preparedStatement.setBoolean(4, product.isValidato());
			preparedStatement.setString(5, product.getcF());
			
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
	public boolean doDelete(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TableName + " WHERE CodiceFiscale = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, code);

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
