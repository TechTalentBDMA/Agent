package upc.bdam.agent.browser.mozilla.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import upc.bdam.agent.jdbc.IJdbcTableBean;


/**
 * 
 * Hosts. Bean para guardar los datos de la tabla Hosts.
 * Finalmente no se utiliza para el piloto
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class Hosts implements IJdbcTableBean {
	//Declaración del nombre de los campos de la tabla en la BBDD del navegador
	public static final String HOST_TABLE_NAME="moz_hosts";
	public static final String HOSTS_ID = "id";
	public static final String HOSTS_HOST = "host";
	public static final String HOSTS_FRECENCY = "frecency";
	public static final String HOSTS_TYPED = "TYPED";
	public static final String HOSTS_PREFIX = "prefix";

	//Declaración de los atributos de clase
	private int id;
	private String host;
	private int frecency;
	private int typed;
	private String prefix;

	//DECLARACIÓN DE MÉTODOS SETTERS
	public int getId() {
		return id;
	}

	public String getHost() {
		return host;
	}

	public int getFrecency() {
		return frecency;
	}

	public int getTyped() {
		return typed;
	}

	public String getPrefix() {
		return prefix;
	}

	//DECLARACIÓN DE MÉTODOS SETTERS
	public void setId(int id) {
		this.id = id;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setFrecency(int frecency) {
		this.frecency = frecency;
	}

	public void setTyped(int typed) {
		this.typed = typed;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * Obtiene los valores del resultset.
	 */
	public void readResultSet(ResultSet rs) throws SQLException {
		id = rs.getInt(HOSTS_ID);
		host = rs.getString(HOSTS_HOST);
		frecency = rs.getInt(HOSTS_FRECENCY);
		typed = rs.getInt(HOSTS_TYPED);
		prefix = rs.getString(HOSTS_PREFIX);
	}

	/**
	 * Devuelve el nombre de su propia tabla
	 */
	public String getTableName() {
		return HOST_TABLE_NAME;
	}

	/**
	 * Clona la instancia actual de la clase
	 */
	public Hosts clone(){
		Hosts clone=new Hosts();
		clone.setId(id);
		clone.setHost(host);
		clone.setFrecency(frecency);
		clone.setTyped(typed);
		clone.setPrefix(prefix);
		return clone;
	}

}
