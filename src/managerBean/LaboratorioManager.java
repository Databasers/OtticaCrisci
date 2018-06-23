package managerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.LavorazioneLaboratorio;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.ProductModel;

public class LaboratorioManager implements ProductModel<LavorazioneLaboratorio, Integer> {

	private static final String TableName="LavorazioneLaboratorio";
	
	@Override
	public LavorazioneLaboratorio doRetrieveByKey(Integer code) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		LavorazioneLaboratorio temp=new LavorazioneLaboratorio();
		
		String sql="SELECT* FROM "+TableName+" WHERE CodiceLavorazione=?  ";
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, code);
			System.out.println("Query: " + preparedStatement.toString());
			
			ResultSet rs= preparedStatement.executeQuery();
			
			rs.next();
			temp.setCodAddetto(rs.getInt("CodiceAddetto"));
			temp.setCodLavorazione(rs.getInt("CodiceLavorazione"));
			temp.setDataInizio(rs.getDate("DataIngresso"));
			temp.setDataFine(rs.getDate("DataUscita"));
			temp.setTipo(rs.getString("TipoLavorazione"));
			temp.setoN_idOcchiale(rs.getInt("Occhiale_nuovo.IDOcchiale"));
			temp.setoR_idOcchiale(rs.getInt("Occhiale_rotto.IDOcchiale"));
			

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
	public Collection<LavorazioneLaboratorio> doRetrieveAll(String order) throws SQLException {
		
		Collection<LavorazioneLaboratorio> c= new LinkedList<LavorazioneLaboratorio>();
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
				LavorazioneLaboratorio temp= new LavorazioneLaboratorio();
				temp.setCodAddetto(rs.getInt("CodiceAddetto"));
				temp.setCodLavorazione(rs.getInt("CodiceLavorazione"));
				temp.setDataInizio(rs.getDate("DataIngresso"));
				temp.setDataFine(rs.getDate("DataUscita"));
				temp.setTipo(rs.getString("TipoLavorazione"));
				temp.setoN_idOcchiale(rs.getInt("Occhiale_nuovo.IDOcchiale"));
				temp.setoR_idOcchiale(rs.getInt("Occhiale_rotto.IDOcchiale"));

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
	public void doSave(LavorazioneLaboratorio product) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String sql="INSERT INTO "+TableName+" VALUES(?,?,?,?,?,?,?)";
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, product.getCodLavorazione());
			preparedStatement.setInt(2, product.getCodAddetto());
			preparedStatement.setString(3, product.getTipo());
			preparedStatement.setDate(4, product.getDataInizio());
			preparedStatement.setDate(5, product.getDataFine());
			preparedStatement.setInt(6, product.getoN_idOcchiale());
			preparedStatement.setInt(7, product.getoR_idOcchiale());
			
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
	public void doUpdate(LavorazioneLaboratorio product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "UPDATE " + TableName
				+ " SET CodiceLavorazione = ?, CodiceAddetto = ?, DataIngresso= ?, DataUscita= ?, "
				+ " PosizioneOcchiale= ?, Occhiale_nuovo.IDOcchiale= ?, Occhiale_rotto.IDOcchiale=?, "
				+ " IDFrame= ? "
				+ " WHERE CodiceLavorazione = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(1, product.getCodLavorazione());
			preparedStatement.setInt(2, product.getCodAddetto());
			preparedStatement.setString(3, product.getTipo());
			preparedStatement.setDate(4, product.getDataInizio());
			preparedStatement.setDate(5, product.getDataFine());
			preparedStatement.setInt(6, product.getoN_idOcchiale());
			preparedStatement.setInt(7, product.getoR_idOcchiale());

			
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

		String deleteSQL = "DELETE FROM " + TableName + " WHERE CodiceLavorazione = ?";

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
