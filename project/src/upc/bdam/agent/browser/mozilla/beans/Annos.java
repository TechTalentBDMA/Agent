package upc.bdam.agent.browser.mozilla.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import upc.bdam.agent.jdbc.IJdbcTableBean;

/**
 * 
 * Annos. Bean para guardar los datos de la tabla Annos.
 * Finalmente no se utiliza para el piloto
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class Annos implements IJdbcTableBean {

	//Declaración del nombre de los campos de la tabla en la BBDD del navegador
	public static final String ANNO_TABLE_NAME="moz_annos";
	public static final String ANNO_ID = "id";
	public static final String ANNO_PLACED_ID = "place_id";
	public static final String ANNO_ATTRIBUTE_ID = "anno_attribute_id";
	public static final String ANNO_MIME_TYPE = "mime_type";
	public static final String ANNO_CONTENT = "content";
	public static final String ANNO_EXPIRATION = "expiration";
	public static final String ANNO_TYPE = "type";
	public static final String ANNO_DATE_ADDED = "dateAdded";
	public static final String ANNO_LAST_MODIFIED = "lastModified";

	//Declaración de los atributos de clase
	private int id;
	private int placeId;
	private int annoAttributeId;
	private String mimeType;
	private String content;
	private int expiration;
	private int type;
	private int dateAdded;
	private int lastModified;

	//DECLARACIÓN DE MÉTODOS GETTER
	public int getId() {
		return id;
	}

	public int getPlaceIdd() {
		return placeId;
	}

	public int getAnno_attributeId() {
		return annoAttributeId;
	}

	public String getMimeType() {
		return mimeType;
	}

	public String getContent() {
		return content;
	}

	public int getExpiration() {
		return expiration;
	}

	public int getType() {
		return type;
	}

	public int getDateAdded() {
		return dateAdded;
	}

	public int getLastModified() {
		return lastModified;
	}

	
	//DECLARACIÓN DE MÉTODOS SETTER
	public void setId(int id) {
		this.id = id;
	}

	public void setPlace_id(int place_id) {
		this.placeId = place_id;
	}

	public void setAnnoAttributeId(int anno_attribute_id) {
		this.annoAttributeId = anno_attribute_id;
	}

	public void setMimeType(String mime_type) {
		this.mimeType = mime_type;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setDateAdded(int dateAdded) {
		this.dateAdded = dateAdded;
	}

	public void setLastModified(int lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * Obtiene los valores del resultset.
	 */
	public void readResultSet(ResultSet rs) throws SQLException {

		id=rs.getInt(ANNO_ID);
		placeId=rs.getInt(ANNO_PLACED_ID);
		annoAttributeId=rs.getInt(ANNO_ATTRIBUTE_ID);
		mimeType=rs.getString(ANNO_MIME_TYPE);
		content=rs.getString(ANNO_CONTENT);
		expiration=rs.getInt(ANNO_EXPIRATION);
		type=rs.getInt(ANNO_TYPE);
		dateAdded=rs.getInt(ANNO_DATE_ADDED);
		lastModified=rs.getInt(ANNO_LAST_MODIFIED);
	}

	
	/**
	 * Devuelve el nombre de su propia tabla
	 */
	public String getTableName(){
		return ANNO_TABLE_NAME;
	}
	
	/**
	 * Clona la instancia actual de la clase
	 */
	public Annos clone(){
		Annos clone=new Annos();
		clone.setId(id);
		clone.setPlace_id(placeId);
		clone.setAnnoAttributeId(annoAttributeId);
		clone.setMimeType(mimeType);
		clone.setContent(content);
		clone.setExpiration(expiration);
		clone.setType(type);
		clone.setDateAdded(dateAdded);
		clone.setLastModified(lastModified);
		return clone;
	}
}
