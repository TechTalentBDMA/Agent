package upc.bdam.agent.browser.mozilla.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import upc.bdam.agent.jdbc.IJdbcTableBean;

/**
 * 
 * ItemsAnnotations. Bean para guardar los datos de la tabla ItemsAnnotations.
 * Finalmente no se utiliza para el piloto
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class ItemsAnnotations implements IJdbcTableBean {
	//Declaración del nombre de los campos de la tabla en la BBDD del navegador
	public static final String ITEMS_ANNOTATIONS_TABLE_NAME="moz_items_annos";
	public static final String ITEMS_ANNOTATIONS_ID = "ID";
	public static final String ITEMS_ANNOTATIONS_ITEM_ID = "item_id";
	public static final String ITEMS_ANNOTATIONS_ANNO_ATTRIBUTE = "anno_attribute";
	public static final String ITEMS_ANNOTATIONS_MIME_TYPE = "mime_type";
	public static final String ITEMS_ANNOTATIONS_CONTENT = "content";
	public static final String ITEMS_ANNOTATIONS_EXPIRATION = "expiration";
	public static final String ITEMS_ANNOTATIONS_TYPE = "type";
	public static final String ITEMS_ANNOTATIONS_DATE_ADDED = "dateAdded";
	public static final String ITEMS_ANNOTATIONS_LAST_MODIFIED = "lastModified";

	//Declaración de los atributos de clase
	private int id;
	private int itemId;
	private int annoAttribute;
	private String mimeType;
	private String content;
	private int flags;
	private int expiration;
	private int type;
	private int dateAdded;
	private int lastModified;

	//DECLARACIÓN DE MÉTODOS GETTERS
	public int getId() {
		return id;
	}

	public int getItemId() {
		return itemId;
	}

	public int getAnnoAttribute() {
		return annoAttribute;
	}

	public String getMime_type() {
		return mimeType;
	}

	public String getContent() {
		return content;
	}

	public int getFlags() {
		return flags;
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

	//DECLARACIÓN DE MÉTODOS SETTERS
	public void setId(int id) {
		this.id = id;
	}

	public void setItemId(int item_id) {
		this.itemId = item_id;
	}

	public void setAnnoAttribute(int anno_attribute_id) {
		this.annoAttribute = anno_attribute_id;
	}

	public void setMimeType(String mime_type) {
		this.mimeType = mime_type;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setFlags(int flags) {
		this.flags = flags;
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
		id = rs.getInt(ITEMS_ANNOTATIONS_ID);
		itemId = rs.getInt(ITEMS_ANNOTATIONS_ITEM_ID);
		annoAttribute = rs.getInt(ITEMS_ANNOTATIONS_ANNO_ATTRIBUTE);
		mimeType = rs.getString(ITEMS_ANNOTATIONS_MIME_TYPE);
		content = rs.getString(ITEMS_ANNOTATIONS_CONTENT);
		expiration = rs.getInt(ITEMS_ANNOTATIONS_EXPIRATION);
		type = rs.getInt(ITEMS_ANNOTATIONS_TYPE);
		dateAdded = rs.getInt(ITEMS_ANNOTATIONS_DATE_ADDED);
		lastModified = rs.getInt(ITEMS_ANNOTATIONS_LAST_MODIFIED);
	}

	/**
	 * Devuelve el nombre de su propia tabla
	 */
	public String getTableName(){
		return ITEMS_ANNOTATIONS_TABLE_NAME;
	}

	/**
	 * Clona la instancia actual de la clase
	 */
	public ItemsAnnotations clone(){
		ItemsAnnotations clone=new ItemsAnnotations();
		clone.setId(id);
		clone.setItemId(itemId);
		clone.setAnnoAttribute(annoAttribute);
		clone.setMimeType(mimeType);
		clone.setContent(content);
		clone.setExpiration(expiration);
		clone.setType(type);
		clone.setDateAdded(dateAdded);
		clone.setLastModified(lastModified);
		return clone;
	}
}
