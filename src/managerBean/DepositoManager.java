package managerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import bean.LavorazioneDeposito;
import bean.LavorazioneLaboratorio;
import it.unisa.model.DriverManagerConnectionPool;
import it.unisa.model.ProductModel;

public class DepositoManager  implements ProductModel<LavorazioneDeposito, Integer> {

	private static final String TableName="Lavorazione_Deposito";
	
	public LavorazioneDeposito doRetrieveSpecificProcessing(Integer IDOcchialeNuovo, Integer IDOcchialeRotto, Integer IDFrame) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		LavorazioneDeposito temp=new LavorazioneDeposito();
		
		String sql;
		if(IDOcchialeNuovo!=null)
			 sql="SELECT* FROM "+TableName+" WHERE DataUscita is null AND Occhiale_nuovoIDOcchiale= ?";
		else { 
				if(IDOcchialeRotto!=null)
					sql="SELECT* FROM "+TableName+" WHERE DataUscita is null AND Occhiale_rottoIDOcchiale= ?";
				else
					sql="SELECT* FROM "+TableName+" WHERE DataUscita is null AND IDFrame= ?";
		}
		
		try {
			System.out.println("Sto per prendere la connessione per "+sql);
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement= connection.prepareStatement(sql);
			if(IDOcchialeNuovo!=null)
				preparedStatement.setInt(1, IDOcchialeNuovo);
			else { 
					if(IDOcchialeRotto!=null)
						preparedStatement.setInt(1, IDOcchialeRotto);
					else
						preparedStatement.setInt(1, IDFrame);
			}
			System.out.println("Query: " + preparedStatement.toString());
			ResultSet rs= preparedStatement.executeQuery();
			
			rs.next();
			temp.setCodAddetto(rs.getInt("CodiceAddetto"));
			temp.setCodLavorazione(rs.getInt("CodiceLavorazione"));
			temp.setDataIngresso(rs.getDate("DataIngresso"));
			temp.setDataUscita(rs.getDate("DataUscita"));
			temp.setPos(rs.getString("PosizioneOcchiale"));
			temp.setoN_idOcchiale(rs.getInt("Occhiale_nuovoIDOcchiale"));
			temp.setoR_idOcchiale(rs.getInt("Occhiale_rottoIDOcchiale"));
			temp.setIdFrame(rs.getInt("IDFrame"));
			System.out.println("Ho finito");
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
	public LavorazioneDeposito doRetrieveByKey(Integer code) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		LavorazioneDeposito temp=new LavorazioneDeposito();
		
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
			temp.setDataIngresso(rs.getDate("DataIngresso"));
			temp.setDataUscita(rs.getDate("DataUscita"));
			temp.setPos(rs.getString("PosizioneOcchiale"));
			temp.setoN_idOcchiale(rs.getInt("Occhiale_nuovoIDOcchiale"));
			temp.setoR_idOcchiale(rs.getInt("Occhiale_rottoIDOcchiale"));
			temp.setIdFrame(rs.getInt("IDFrame"));
			
			
			
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
	public Collection<LavorazioneDeposito> doRetrieveAll(String order) throws SQLException {
		
		Collection<LavorazioneDeposito> c= new LinkedList<LavorazioneDeposito>();
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
				LavorazioneDeposito temp= new LavorazioneDeposito();
				temp.setCodAddetto(rs.getInt("CodiceAddetto"));
				temp.setCodLavorazione(rs.getInt("CodiceLavorazione"));
				temp.setDataIngresso(rs.getDate("DataIngresso"));
				temp.setDataUscita(rs.getDate("DataUscita"));
				temp.setPos(rs.getString("PosizioneOcchiale"));
				temp.setoN_idOcchiale(rs.getInt("Occhiale_nuovoIDOcchiale"));
				temp.setoR_idOcchiale(rs.getInt("Occhiale_rottoIDOcchiale"));
				temp.setIdFrame(rs.getInt("IDFrame"));

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

	public void doSaveAI(LavorazioneDeposito product) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		
		if(product.getoN_idOcchiale()!=null) {
			//`CodiceLavorazione`, `CodiceAddetto`, `DataIngresso`, `DataUscita`, `PosizioneOcchiale`, `Occhiale_nuovoIDOcchiale`, `Occhiale_rottoIDOcchiale`, `IDFrame`
			String sql="INSERT INTO "+TableName+" VALUES(null,?,?,?,?,?,null,null)";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, product.getCodAddetto());
				preparedStatement.setDate(2, product.getDataIngresso());
				preparedStatement.setDate(3, product.getDataUscita());
				preparedStatement.setString(4, product.getPos());
				preparedStatement.setInt(5, product.getoN_idOcchiale());
				
				
				
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
			
			if(product.getoR_idOcchiale()!=null) {
				String sql="INSERT INTO "+TableName+"  VALUES(null,?,?,?,?,null,?,null)";

				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, product.getCodAddetto());
					preparedStatement.setDate(2, product.getDataIngresso());
					preparedStatement.setDate(3, product.getDataUscita());
					preparedStatement.setString(4, product.getPos());
					preparedStatement.setInt(5, product.getoR_idOcchiale());
					preparedStatement.setInt(6, product.getCodLavorazione());
					
					
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
			if(product.getIdFrame()!=null) {
				String sql="INSERT INTO "+TableName+" VALUES(null,?,?,?,?,null,null,?)";

				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, product.getCodAddetto());
					preparedStatement.setDate(2, product.getDataIngresso());
					preparedStatement.setDate(3, product.getDataUscita());
					preparedStatement.setString(4, product.getPos());
					preparedStatement.setInt(5, product.getIdFrame());
					preparedStatement.setInt(6, product.getCodLavorazione());
					
					
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
	public void doSave(LavorazioneDeposito product) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		
		if(product.getoN_idOcchiale()!=null) {
			//`CodiceLavorazione`, `CodiceAddetto`, `DataIngresso`, `DataUscita`, `PosizioneOcchiale`, `Occhiale_nuovoIDOcchiale`, `Occhiale_rottoIDOcchiale`, `IDFrame`
			String sql="INSERT INTO "+TableName+" VALUES(?,?,?,?,?,?,null,null)";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, product.getCodLavorazione());
				preparedStatement.setInt(2, product.getCodAddetto());
				preparedStatement.setDate(3, product.getDataIngresso());
				preparedStatement.setDate(4, product.getDataUscita());
				preparedStatement.setString(5, product.getPos());
				preparedStatement.setInt(6, product.getoN_idOcchiale());
				
				
				
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
			
			if(product.getoR_idOcchiale()!=null) {
				String sql="INSERT INTO "+TableName+"  VALUES(?,?,?,?,?,null,?,null)";

				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, product.getCodLavorazione());
					preparedStatement.setInt(2, product.getCodAddetto());
					preparedStatement.setDate(3, product.getDataIngresso());
					preparedStatement.setDate(4, product.getDataUscita());
					preparedStatement.setString(5, product.getPos());
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
			if(product.getIdFrame()!=null) {
				String sql="INSERT INTO "+TableName+" VALUES(?,?,?,?,?,null,null,?)";

				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, product.getCodLavorazione());
					preparedStatement.setInt(2, product.getCodAddetto());
					preparedStatement.setDate(3, product.getDataIngresso());
					preparedStatement.setDate(4, product.getDataUscita());
					preparedStatement.setString(5, product.getPos());
					preparedStatement.setInt(6, product.getIdFrame());
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
	public void doUpdate(LavorazioneDeposito product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		if(product.getoN_idOcchiale()!=0) {
		String insertSQL = "UPDATE " + TableName
				+ " SET CodiceLavorazione = ?, CodiceAddetto = ?, DataIngresso= ?, DataUscita= ?, "
				+ " PosizioneOcchiale= ?, Occhiale_nuovoIDOcchiale= ? "
				+ " WHERE CodiceLavorazione = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getCodLavorazione());
			preparedStatement.setInt(2, product.getCodAddetto());
			preparedStatement.setDate(3, product.getDataIngresso());
			preparedStatement.setDate(4, product.getDataUscita());
			preparedStatement.setString(5, product.getPos());
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
					+ " SET CodiceLavorazione = ?, CodiceAddetto = ?, DataIngresso= ?, DataUscita= ?, "
					+ " PosizioneOcchiale= ?, Occhiale_rottoIDOcchiale= ? "
					+ " WHERE CodiceLavorazione = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, product.getCodLavorazione());
				preparedStatement.setInt(2, product.getCodAddetto());
				preparedStatement.setDate(3, product.getDataIngresso());
				preparedStatement.setDate(4, product.getDataUscita());
				preparedStatement.setString(5, product.getPos());
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
		if(product.getIdFrame()!=0) {
			String insertSQL = "UPDATE " + TableName
					+ " SET CodiceLavorazione = ?, CodiceAddetto = ?, DataIngresso= ?, DataUscita= ?, "
					+ " PosizioneOcchiale= ?, IDFrame= ? "
					+ " WHERE CodiceLavorazione = ?";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, product.getCodLavorazione());
				preparedStatement.setInt(2, product.getCodAddetto());
				preparedStatement.setDate(3, product.getDataIngresso());
				preparedStatement.setDate(4, product.getDataUscita());
				preparedStatement.setString(5, product.getPos());
				preparedStatement.setInt(6, product.getIdFrame());
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
