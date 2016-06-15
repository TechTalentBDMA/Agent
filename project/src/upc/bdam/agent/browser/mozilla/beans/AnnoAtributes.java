package upc.bdam.agent.browser.mozilla.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import upc.bdam.agent.jdbc.IJdbcTableBean;

/**
 *
 * Annotation Attributes. Bean para guardar los datos de la tabla Attributes.
 * Finalmente no se utiliza para el piloto
 *
 */
public class AnnoAtributes implements IJdbcTableBean {

	public static final String ANNO_ATRIBUTES_TABLE_NAME = "moz_anno_attributes";
	public static final String ANNO_ATRIBUTES_ID = "id";
	public static final String ANNO_ATRIBUTES_NAME = "name";

	// atributos
	private int id;
	private String name;

	// getters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	// setters
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtiene los valores del resultset.
	 */
	public void readResultSet(ResultSet rs) throws SQLException {
		id = rs.getInt(ANNO_ATRIBUTES_ID);
		name = rs.getString(ANNO_ATRIBUTES_NAME);
	}

	/**
	 * Devuelve el nombre de su propia tabla
	 */
	public String getTableName() {
		return ANNO_ATRIBUTES_TABLE_NAME;
	}

	/**
	 * Clona la instancia actual de la clase
	 */
	public AnnoAtributes clone() {
		AnnoAtributes clone = new AnnoAtributes();
		clone.setId(id);
		clone.setName(name);
		return clone;
	}
}