package managerBean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.LavorazioneLaboratorio;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.ProductModel;

public class LaboratorioManager implements ProductModel<LavorazioneLaboratorio, Integer>, Serializable {

	private static final String TableName="Lavorazione_Laboratorio";
	
	public LavorazioneLaboratorio doRetrieveSpecificProcessing(Integer IDOcchialeNuovo, Integer IDOcchialeRotto) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		LavorazioneLaboratorio temp=new LavorazioneLaboratorio();
		
		String sql = null;
		if(IDOcchialeNuovo!=null)
			 sql="SELECT* FROM "+TableName+" WHERE DataUscita is null AND Occhiale_nuovoIDOcchiale= ?";
		else { 
				if(IDOcchialeRotto!=null)
					sql="SELECT* FROM "+TableName+" WHERE DataUscita is null AND Occhiale_rottoIDOcchiale= ?";
		}
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			if(IDOcchialeNuovo!=null)
				preparedStatement.setInt(1, IDOcchialeNuovo);
			else { 
					if(IDOcchialeRotto!=null)
						preparedStatement.setInt(1, IDOcchialeRotto);
			}
			System.out.println("Query: " + preparedStatement.toString());
			
			ResultSet rs= preparedStatement.executeQuery();
			
			rs.next();
			temp.setCodAddetto(rs.getInt("CodiceAddetto"));
			temp.setCodLavorazione(rs.getInt("CodiceLavorazione"));
			temp.setDataInizio(rs.getDate("DataIngresso"));
			temp.setDataFine(rs.getDate("DataUscita"));
			temp.setTipo(rs.getString("TipoLavorazione"));
			temp.setoN_idOcchiale(rs.getInt("Occhiale_nuovoIDOcchiale"));
			temp.setoR_idOcchiale(rs.getInt("Occhiale_rottoIDOcchiale"));
			

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
			temp.setoN_idOcchiale(rs.getInt("Occhiale_nuovoIDOcchiale"));
			temp.setoR_idOcchiale(rs.getInt("Occhiale_rottoIDOcchiale"));
			

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
				temp.setoN_idOcchiale(rs.getInt("Occhiale_nuovoIDOcchiale"));
				temp.setoR_idOcchiale(rs.getInt("Occhiale_rottoIDOcchiale"));

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
		if(product.getoN_idOcchiale()!=null) {
		String sql="INSERT INTO "+TableName+" VALUES(?,?,?,?,?,?,null)";
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			if(product.getCodLavorazione()==null)
				preparedStatement.setNull(1, java.sql.Types.INTEGER);
			else
				preparedStatement.setInt(1, product.getCodLavorazione());
			preparedStatement.setInt(2, product.getCodAddetto());
			preparedStatement.setString(3, product.getTipo());
			preparedStatement.setDate(4, product.getDataInizio());
			preparedStatement.setDate(5, product.getDataFine());
			preparedStatement.setInt(6, product.getoN_idOcchiale());
			
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
		if(product.getoR_idOcchiale()!=null) {
			//`CodiceLavorazione`, `CodiceAddetto`, `TipoLavorazione`, `DataIngresso`, `DataUscita`, `Occhiale_nuovoIDOcchiale`, `Occhiale_rottoIDOcchiale`
			String sql="INSERT INTO "+TableName+"  VALUES(?,?,?,?,?,null,?)";
			try {
				connection=DriverManagerConnectionPool.getConnection();
				preparedStatement= connection.prepareStatement(sql);
				
				preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1, product.getCodLavorazione());
				preparedStatement.setInt(2, product.getCodAddetto());
				preparedStatement.setString(3, product.getTipo());
				preparedStatement.setDate(4, product.getDataInizio());
				preparedStatement.setDate(5, product.getDataFine());
				preparedStatement.setInt(6, product.getoR_idOcchiale());
				
				System.out.println("doSave: "+ preparedStatement.toString());
				preparedStatement.executeUpdate();

				connection.commit();
			}catch (Exception e) {
				e.printStackTrace();
			} 
			finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			}

	}
	
	public void doSaveAI(LavorazioneLaboratorio product) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		if(product.getoN_idOcchiale()!=null) {
		System.out.println("Inizio doSave in laboratorio");
		//`CodiceLavorazione`, `CodiceAddetto`, `TipoLavorazione`, `DataIngresso`, `DataUscita`, `Occhiale_nuovoIDOcchiale`, `Occhiale_rottoIDOcchiale`
		String sql="INSERT INTO "+TableName+" VALUES(null,?,?,?,?,?,null)";
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			System.out.println("Inizio a settare i parametri");
			preparedStatement.setInt(1, product.getCodAddetto());
			System.out.println("2");
			preparedStatement.setString(2, product.getTipo());
			System.out.println("3");
			preparedStatement.setDate(3, product.getDataInizio());
			System.out.println("4");
			preparedStatement.setDate(4, product.getDataFine());
			System.out.println("5");
			preparedStatement.setInt(5, product.getoN_idOcchiale());
			
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
		if(product.getoR_idOcchiale()!=null) {
			//`CodiceLavorazione`, `CodiceAddetto`, `TipoLavorazione`, `DataIngresso`, `DataUscita`, `Occhiale_nuovoIDOcchiale`, `Occhiale_rottoIDOcchiale`
			String sql="INSERT INTO "+TableName+"  VALUES(null,?,?,?,?,null,?)";
			try {
				connection=DriverManagerConnectionPool.getConnection();
				preparedStatement= connection.prepareStatement(sql);
				
				preparedStatement.setInt(1, product.getCodAddetto());
				preparedStatement.setString(2, product.getTipo());
				preparedStatement.setDate(3, product.getDataInizio());
				preparedStatement.setDate(4, product.getDataFine());
				preparedStatement.setInt(5, product.getoR_idOcchiale());
				
				System.out.println("doSave: "+ preparedStatement.toString());
				preparedStatement.executeUpdate();

				connection.commit();
			}catch (Exception e) {
				e.printStackTrace();
			} 
			finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			}

	}

	@Override
	public void doUpdate(LavorazioneLaboratorio product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(product.getoN_idOcchiale()!=0) {
		String insertSQL = "UPDATE " + TableName
				+ " SET CodiceLavorazione = ?, CodiceAddetto = ?,TipoLavorazione= ?, DataIngresso= ?, DataUscita= ?, "
				+ "  Occhiale_nuovoIDOcchiale= ? "
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
			preparedStatement.setInt(7, product.getCodLavorazione());

			
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
		if(product.getoR_idOcchiale()!=0) {
			String insertSQL = "UPDATE " + TableName
					+ " SET CodiceLavorazione = ?, CodiceAddetto = ?,TipoLavorazione= ?, DataIngresso= ?, DataUscita= ?, "
					+ "  Occhiale_rottoIDOcchiale= ? "
					+ " WHERE CodiceLavorazione = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				
				preparedStatement.setInt(1, product.getCodLavorazione());
				preparedStatement.setInt(2, product.getCodAddetto());
				preparedStatement.setString(3, product.getTipo());
				preparedStatement.setDate(4, product.getDataInizio());
				preparedStatement.setDate(5, product.getDataFine());
				preparedStatement.setInt(6, product.getoR_idOcchiale());
				preparedStatement.setInt(7, product.getCodLavorazione());

				
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
