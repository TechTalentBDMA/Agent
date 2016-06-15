package upc.bdam.agent.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Declaración de interfaz para la recuperación de datos de tablas MySql de navegadores.
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public interface IJdbcTableBean {
	/**
	 *
	 * @param rs ResultSet con los registros leídos.
	 */
	public void readResultSet(ResultSet rs) throws SQLException;

	/**
	 * Devuelve el nombre de la tabla que se está leyendo
	 * @return
	 */
	public String getTableName();
	
	/**
	 * Clona el resultado de la instancia
	 * @return
	 */
	public IJdbcTableBean clone();
}
