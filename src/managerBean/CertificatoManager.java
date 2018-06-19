package managerBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

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
	public Collection<Certificato> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSave(Certificato product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(Certificato product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean doDelete(String code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
