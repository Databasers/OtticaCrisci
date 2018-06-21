package managerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.Frame;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.ProductModel;

public class FrameManager implements ProductModel<Frame, Integer> {

	private static final String TableName="Frame";
	
	@Override
	public Frame doRetrieveByKey(Integer code) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		Frame temp=new Frame();
		
		String sql="SELECT* FROM "+TableName+" WHERE PartitaIva=?  ";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, code);
			System.out.println("Query: " + preparedStatement.toString());
			
			ResultSet rs= preparedStatement.executeQuery();
			
			rs.next();
			temp.setId(rs.getInt("IDOcchiale"));
			temp.setColore(rs.getString("Colore"));
			temp.setMarchio(rs.getString("Marchio"));
			temp.setModello(rs.getString("Modello"));
			temp.setPeso(rs.getInt("Peso"));
			temp.setPrezzo(rs.getInt("Prezzo"));
			temp.setPartitaIva(rs.getInt("PartitaIva"));
			temp.setMateriale(rs.getString("Materiale"));
			
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
				temp.setId(rs.getInt("IDOcchiale"));
				temp.setColore(rs.getString("Colore"));
				temp.setMarchio(rs.getString("Marchio"));
				temp.setModello(rs.getString("Modello"));
				temp.setPeso(rs.getInt("Peso"));
				temp.setPrezzo(rs.getInt("Prezzo"));
				temp.setPartitaIva(rs.getInt("PartitaIva"));
				temp.setMateriale(rs.getString("Materiale"));

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
				+ " PartitaIva= ?, Marchio= ? "
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
