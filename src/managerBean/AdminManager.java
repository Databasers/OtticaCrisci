package managerBean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.Admin;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.ProductModel;

public class AdminManager implements ProductModel<Admin,String>, Serializable {

	private static final String TableName="Admin";
	
	public Admin doRetrieveIfRegistered(String code, String password) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		Admin temp=new Admin();
		
		String sql="SELECT* FROM "+TableName+" WHERE CodiceFiscale=? AND Password= ? ";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setString(1, code);
			preparedStatement.setString(2, password);
			System.out.println("Query: " + preparedStatement.toString());
			
			ResultSet rs= preparedStatement.executeQuery();
			
			rs.next();
			temp.setcF(rs.getString("CodiceFiscale"));
			temp.setNome(rs.getString("Nome"));
			temp.setCognome(rs.getString("Cognome"));
			temp.setPassword(rs.getString("Password"));
			temp.setDataAssunzione(rs.getDate("DataAssunzione"));
			temp.setDataNascita(rs.getDate("DataNascita"));
			temp.setTelefono(rs.getString("Telefono"));
			
				
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
	public Admin doRetrieveByKey(String code) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		Admin temp=new Admin();
		
		String sql="SELECT* FROM "+TableName+" WHERE CodiceFiscale=?  ";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setString(1, code);
			System.out.println("Query: " + preparedStatement.toString());
			
			ResultSet rs= preparedStatement.executeQuery();
			
			rs.next();
			temp.setcF(rs.getString("CodiceFiscale"));
			temp.setNome(rs.getString("Nome"));
			temp.setCognome(rs.getString("Cognome"));
			temp.setPassword(rs.getString("Password"));
			temp.setDataAssunzione(rs.getDate("DataAssunzione"));
			temp.setDataNascita(rs.getDate("DataNascita"));
			temp.setTelefono(rs.getString("Telefono"));
			
				
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
	public Collection<Admin> doRetrieveAll(String order) throws SQLException {
		
		Collection<Admin> c= new LinkedList<Admin>();
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
				Admin temp= new Admin();
				temp.setcF(rs.getString("CodiceFiscale"));
				temp.setNome(rs.getString("Nome"));
				temp.setCognome(rs.getString("Cognome"));
				temp.setPassword(rs.getString("Password"));
				temp.setDataAssunzione(rs.getDate("DataAssunzione"));
				temp.setDataNascita(rs.getDate("DataNascita"));
				temp.setTelefono(rs.getString("Telefono"));
				
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
	public void doSave(Admin product) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="INSERT INTO "+TableName+" VALUES(?,?,?,?,?,?)";
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setString(1, product.getcF());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setString(3, product.getCognome());
			preparedStatement.setString(4, product.getPassword());
			preparedStatement.setDate(5,product.getDataAssunzione());
			preparedStatement.setDate(6, product.getDataNascita());
			preparedStatement.setString(7, product.getTelefono());
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
	public void doUpdate(Admin product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "UPDATE " + TableName
				+ " SET CodiceFiscale = ?, Nome = ?, Cognome = ?, Password= ?, DataAssunzione= ?, DataNascita= ?, Telefono= ? "
				+ " WHERE CodiceFiscale = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getcF());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setString(3, product.getCognome());
			preparedStatement.setString(4, product.getCognome());
			preparedStatement.setDate(5,product.getDataAssunzione());
			preparedStatement.setDate(6, product.getDataNascita());
			preparedStatement.setString(7, product.getTelefono());
			preparedStatement.setString(8, product.getcF());
			
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
