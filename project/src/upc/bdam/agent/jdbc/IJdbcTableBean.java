package upc.bdam.agent.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Declaraci�n de interfaz para la recuperaci�n de datos de tablas MySql de navegadores.
 * 
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public interface IJdbcTableBean {
	/**
	 *
	 * @param rs ResultSet con los registros le�dos.
	 */
	public void readResultSet(ResultSet rs) throws SQLException;

	/**
	 * Devuelve el nombre de la tabla que se est� leyendo
	 * @return
	 */
	public String getTableName();
	
	/**
	 * Clona el resultado de la instancia
	 * @return
	 */
	public IJdbcTableBean clone();
}
