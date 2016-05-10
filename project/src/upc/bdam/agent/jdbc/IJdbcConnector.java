package upc.bdam.agent.jdbc;

import java.sql.SQLException;
import java.util.List;

public interface IJdbcConnector <I extends IJdbcTableBean>{

	public void setDriverName(String driver);
	public void openConnection()throws ClassNotFoundException, SQLException;
	public void closeConnection();
	public List<I> queryTable(I table);
	public void setDBLocation(String location);

}
