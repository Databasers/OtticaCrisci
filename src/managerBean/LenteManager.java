package managerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.Lente;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.ProductModel;

/*
 * `IDLente`, `Diottria`, `Materiale`, `Peso`, `Prezzo`, `TipoLente`, `PartitaIva`
 */

public class LenteManager implements ProductModel<Lente, Integer> {

	private static final String TableName="Lente";
	
	@Override
	public Lente doRetrieveByKey(Integer code) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		Lente temp=new Lente();
		
		String sql="SELECT* FROM "+TableName+" WHERE IDLente=?  ";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, code);
			System.out.println("Query: " + preparedStatement.toString());
			
			ResultSet rs= preparedStatement.executeQuery();
			
			rs.next();
			temp.setId(rs.getInt("IDLente"));
			temp.setDiottria(rs.getInt("Diottria"));
			temp.setMateriale(rs.getString("Materiale"));
			temp.setTipo(rs.getString("TipoLente"));
			temp.setPeso(rs.getInt("Peso"));
			temp.setPartitaIva(rs.getInt("PartitaIva"));
			temp.setPrezzo(rs.getInt("Prezzo"));
			
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
	public Collection<Lente> doRetrieveAll(String order) throws SQLException {
		
		Collection<Lente> c= new LinkedList<Lente>();
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
				Lente temp= new Lente();
				
				temp.setId(rs.getInt("IDLente"));
				temp.setDiottria(rs.getInt("Diottria"));
				temp.setMateriale(rs.getString("Materiale"));
				temp.setTipo(rs.getString("TipoLente"));
				temp.setPeso(rs.getInt("Peso"));
				temp.setPartitaIva(rs.getInt("PartitaIva"));
				temp.setPrezzo(rs.getInt("Prezzo"));
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
	public void doSave(Lente product) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="INSERT INTO "+TableName+" VALUES(?,?,?,?,?,?,?)";
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setInt(2, product.getDiottria());
			preparedStatement.setString(3, product.getMateriale());
			preparedStatement.setInt(4, product.getPeso());
			preparedStatement.setInt(5, product.getPrezzo());
			preparedStatement.setString(6, product.getTipo());
			preparedStatement.setInt(7, product.getPartitaIva());
			

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
	public void doUpdate(Lente product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "UPDATE " + TableName
				+ " SET IDLente = ?, Diottria = ?, Materiale= ?, Peso = ?, Prezzo= ?, TipoLente= ?, "
				+ " PartitaIva= ?  "
				+ " WHERE IDLente = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setInt(2, product.getDiottria());
			preparedStatement.setString(3, product.getMateriale());
			preparedStatement.setInt(4, product.getPeso());
			preparedStatement.setInt(5, product.getPrezzo());
			preparedStatement.setString(6, product.getTipo());
			preparedStatement.setInt(7, product.getPartitaIva());
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

		String deleteSQL = "DELETE FROM " + TableName + " WHERE IDLente = ?";

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

