package managerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.OcchialeNuovo;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.ProductModel;


public class OcchialeNuovoManager implements ProductModel<OcchialeNuovo, Integer> {

	private static final String TableName="Occhiale_nuovo";
	
	public Collection<OcchialeNuovo> doRetrieveByCondition(String codiceFiscale) throws SQLException{
		Collection<OcchialeNuovo> c= new LinkedList<OcchialeNuovo>();
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql= "SELECT * FROM "+TableName+" WHERE codiceFiscale=?";
			
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			System.out.println("doRetrieveByCondition: "+ preparedStatement.toString());
			preparedStatement.setString(1, codiceFiscale);
			ResultSet rs=preparedStatement.executeQuery();
			if(!rs.next()) //se il resultSet è vuoto
				c=null;
			 do{
				OcchialeNuovo temp= new OcchialeNuovo();
				temp.setId(rs.getInt("IDOcchiale"));
				temp.setPrezzo(rs.getInt("Prezzo"));
				temp.setDataRitiro(rs.getDate("DataRitiro"));
				temp.setIdLente(rs.getInt("IDLente"));
				temp.setIdFrame(rs.getInt("IDFrame"));
				temp.setcF(rs.getString("CodiceFiscale"));
				temp.setDataOrdine(rs.getDate("DataOrdine"));
				temp.setStato(rs.getString("Stato"));
				c.add(temp);
			}while(rs.next());
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
	public OcchialeNuovo doRetrieveByKey(Integer code) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		OcchialeNuovo temp=new OcchialeNuovo();
		
		String sql="SELECT* FROM "+TableName+" WHERE IDOcchiale=?  ";
		
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
			temp.setIdLente(rs.getInt("IDLente"));
			temp.setIdFrame(rs.getInt("IDFrame"));
			temp.setcF(rs.getString("CodiceFiscale"));
			temp.setDataOrdine(rs.getDate("DataOrdine"));
			temp.setStato(rs.getString("Stato"));
			
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
	public Collection<OcchialeNuovo> doRetrieveAll(String order) throws SQLException {
		
		Collection<OcchialeNuovo> c= new LinkedList<OcchialeNuovo>();
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
				OcchialeNuovo temp= new OcchialeNuovo();
				
				temp.setId(rs.getInt("IDOcchiale"));
				temp.setPrezzo(rs.getInt("Prezzo"));
				temp.setDataRitiro(rs.getDate("DataRitiro"));
				temp.setIdLente(rs.getInt("IDLente"));
				temp.setIdFrame(rs.getInt("IDFrame"));
				temp.setcF(rs.getString("CodiceFiscale"));
				temp.setDataOrdine(rs.getDate("DataOrdine"));
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
	public void doSave(OcchialeNuovo product) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="INSERT INTO "+TableName+" VALUES(?,?,?,?,?,?,?,?)";
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setInt(2, product.getPrezzo());
			preparedStatement.setDate(3, product.getDataRitiro());
			preparedStatement.setInt(4, product.getIdLente());
			preparedStatement.setInt(5, product.getIdFrame());
			preparedStatement.setString(6, product.getcF());
			preparedStatement.setDate(7, product.getDataOrdine());
			preparedStatement.setString(8, product.getStato());
			

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
	public void doUpdate(OcchialeNuovo product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "UPDATE " + TableName
				+ " SET IDOcchiale = ?, Prezzo = ?, DataRitiro= ?, IDLente = ?, IDFrame= ?, CodiceFiscale= ?, "
				+ " DataOrdine= ?, Stato= ?  "
				+ " WHERE IDOcchiale = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setInt(2, product.getPrezzo());
			preparedStatement.setDate(3, product.getDataRitiro());
			preparedStatement.setInt(4, product.getIdLente());
			preparedStatement.setInt(5, product.getIdFrame());
			preparedStatement.setString(6, product.getcF());
			preparedStatement.setDate(7, product.getDataOrdine());
			preparedStatement.setString(8, product.getStato());
			preparedStatement.setInt(9, product.getId());

			
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

		String deleteSQL = "DELETE FROM " + TableName + " WHERE IDOcchiale = ?";

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

