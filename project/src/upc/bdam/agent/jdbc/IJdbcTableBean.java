package upc.bdam.agent.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IJdbcTableBean {
	/**
	 *
	 * @param rs ResultSet con los registros leídos.
	 */
	public void readResultSet(ResultSet rs) throws SQLException;

	public String getTableName();
	
	public IJdbcTableBean clone();
}
