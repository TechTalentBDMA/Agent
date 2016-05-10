package upc.bdam.agent.jdbc.SQLITE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import upc.bdam.agent.jdbc.IJdbcConnector;
import upc.bdam.agent.jdbc.IJdbcTableBean;

/**
 * Clase de utilidad para leer los registros de las tablas que componen el
 * perfil del usuario en la BBDD SQLITE de Mozilla.
 *
 */
public class SQLiteConnector <I extends IJdbcTableBean>implements IJdbcConnector<I>{

	private String driverName = null;
	private String dbLocation = null;
	private Connection conn = null;

	public void setDriverName(String name) {
		driverName = name;
	}

	public void setDBLocation(String location) {
		dbLocation = location;
	}

	public void openConnection() throws ClassNotFoundException, SQLException {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbLocation);


		System.out.println("Opened database successfully");
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		driverName = null;
		dbLocation = null;
		conn = null;
		System.out.println("Closed database successfully");
	}

	public List<I> queryTable(I table) {
		List<I> tuplas=new ArrayList<I>();
		try {
			Statement stmt = null;
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+table.getTableName());

			while (rs.next()) {
				table.readResultSet(rs);
				tuplas.add((I)table.clone());
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return tuplas;
	}
}