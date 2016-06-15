package upc.bdam.agent.jdbc;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface que declara los m�todos que debe implementar cualquier especializaci�n de clase qeu acceda a la BBDD MySql 
 * de un navegador.
 * 
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 * @param <I>
 */
public interface IJdbcConnector <I extends IJdbcTableBean>{

	/**
	 * Asigna la cadena de conexi�n
	 * @param driver
	 */
	public void setDriverName(String driver);
	
	/**
	 * Abre la conexi�n a la BBDD
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void openConnection()throws ClassNotFoundException, SQLException;
	
	/**
	 * Cierra la conexi�n a la BBDD
	 */
	public void closeConnection();
	
	/**
	 * Realiza una consulta a la BBDD
	 * @param table
	 * @return
	 */
	public List<I> queryTable(I table);
	
	/**
	 * Establece el path en el que se encuentra la BBDD
	 * @param location
	 */
	public void setDBLocation(String location);

}
