package managerBean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.OcchialeNuovo;
import bean.OcchialeRotto;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.ProductModel;



public class OcchialeRottoManager implements ProductModel<OcchialeRotto, Integer>, Serializable {

	private static final String TableName="Occhiale_rotto";
	
	public Collection<OcchialeRotto> doRetrieveIfNotCompleted(String order) throws SQLException{
		Collection<OcchialeRotto> c= new LinkedList<OcchialeRotto>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql= "SELECT * FROM "+TableName+" WHERE DataRitiro IS NULL";
			
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			System.out.println("doRetrieveByCondition: "+ preparedStatement.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				OcchialeRotto temp= new OcchialeRotto();
				temp.setId(rs.getInt("IDOcchiale"));
				temp.setPrezzo(rs.getInt("Prezzo"));
				temp.setDataRitiro(rs.getDate("DataRitiro"));
				temp.setDataConsegna(rs.getDate("DataConsegna"));
				temp.setTipoDanno(rs.getString("Entit‡Danno"));
				temp.setcF(rs.getString("CodiceFiscale"));
				temp.setStato(rs.getString("Stato"));
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
	
	public Collection<OcchialeRotto> doRetrieveByCondition(String codiceFiscale) throws SQLException{
		Collection<OcchialeRotto> c= new LinkedList<OcchialeRotto>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		String sql= "SELECT * FROM "+TableName+" WHERE codiceFiscale= ?";
			
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, codiceFiscale);
			System.out.println("doRetrieveByCondition: "+ preparedStatement.toString());
			
			
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				OcchialeRotto temp= new OcchialeRotto();
				temp.setId(rs.getInt("IDOcchiale"));
				temp.setPrezzo(rs.getInt("Prezzo"));
				temp.setDataRitiro(rs.getDate("DataRitiro"));
				temp.setDataConsegna(rs.getDate("DataConsegna"));
				temp.setTipoDanno(rs.getString("Entit‡Danno"));
				temp.setcF(rs.getString("CodiceFiscale"));
				temp.setStato(rs.getString("Stato"));
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
	public OcchialeRotto doRetrieveByKey(Integer code) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		OcchialeRotto temp=new OcchialeRotto();
		
		String sql="SELECT* FROM "+TableName+" WHERE IdOcchiale=?  ";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, code);
			System.out.println("Query: " + preparedStatement.toString());
			
			ResultSet rs= preparedStatement.executeQuery();
			
			rs.next();
			temp.setId(rs.getInt("IDOcchiale"));
			temp.setPrezzo(rs.getInt("Prezzo"));
			temp.setDataRitiro(rs.getDate("DataRitiro"));
			temp.setDataConsegna(rs.getDate("DataConsegna"));
			temp.setTipoDanno(rs.getString("Entit‡Danno"));
			temp.setStato(rs.getString("Stato"));
			temp.setcF(rs.getString("CodiceFiscale"));
						
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
	public Collection<OcchialeRotto> doRetrieveAll(String order) throws SQLException {
		
		Collection<OcchialeRotto> c= new LinkedList<OcchialeRotto>();
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
				OcchialeRotto temp= new OcchialeRotto();
				temp.setId(rs.getInt("IDOcchiale"));
				temp.setPrezzo(rs.getInt("Prezzo"));
				temp.setDataRitiro(rs.getDate("DataRitiro"));
				temp.setDataConsegna(rs.getDate("DataConsegna"));
				temp.setTipoDanno(rs.getString("Entit‡Danno"));
				temp.setcF(rs.getString("CodiceFiscale"));
				temp.setStato(rs.getString("Stato"));
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
	public void doSave(OcchialeRotto product) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="INSERT INTO "+TableName+" VALUES(?,?,?,?,?,?,?)";
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setInt(2, product.getPrezzo());
			preparedStatement.setDate(3, product.getDataRitiro());
			preparedStatement.setDate(4, product.getDataConsegna());
			preparedStatement.setString(5, product.getTipoDanno());
			preparedStatement.setString(6, product.getcF());
			preparedStatement.setString(7, product.getStato());
			

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
	
	//`Id`, `Prezzo`, `DataRitiro`, `DataConsegna`, `Entit‡Danno`, `CodiceFiscale`

	@Override
	public void doUpdate(OcchialeRotto product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "UPDATE " + TableName
				+ " SET IDOcchiale = ?, Prezzo = ?, DataRitiro= ?, DataConsegna = ?, Entit‡Danno= ?, "
				+ " CodiceFiscale= ?, Stato=  ? "
				+ " WHERE IdOcchiale = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setInt(2, product.getPrezzo());
			preparedStatement.setDate(3, product.getDataRitiro());
			preparedStatement.setDate(4, product.getDataConsegna());
			preparedStatement.setString(5, product.getTipoDanno());
			preparedStatement.setString(6, product.getcF());
			preparedStatement.setString(7, product.getStato());;
			preparedStatement.setInt(8, product.getId());

			
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

		String deleteSQL = "DELETE FROM " + TableName + " WHERE IdOcchiale = ?";

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
