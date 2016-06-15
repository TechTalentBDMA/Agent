package upc.bdam.agent.browser.mozilla.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import upc.bdam.agent.jdbc.IJdbcTableBean;

/**
 * 
 * Bookmarks. Bean para guardar los datos de la tabla Bookmarks.
 * Finalmente no se utiliza para el piloto
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class Bookmarks implements IJdbcTableBean {

	//Declaración del nombre de los campos de la tabla en la BBDD del navegador
	public static final String BOOKMARKS_TABLE_NAME="moz_bookmarks";
	public static final String BOOKMARKS_ID = "id";
	public static final String BOOKMARKS_TYPE = "type";
	public static final String BOOKMARKS_FK = "fk";
	public static final String BOOKMARKS_PARENT = "parent";
	public static final String BOOKMARKS_POSITION = "position";
	public static final String BOOKMARKS_TITTLE = "title";
	public static final String BOOKMARKS_KEYWORD_ID = "keyword_id";
	public static final String BOOKMARKS_FOLDER_TYPE = "folder_type";
	public static final String BOOKMARKS_DATA_ADDED = "dateAdded";
	public static final String BOOKMARKS_LAST_MODIFIED = "lastModified";
	public static final String BOOKMARKS_GUID = "guid";

	//Declaración de los atributos de clase
	private int id;
	private int type;
	private int fk;
	private int parent;
	private int position;
	private String title;
	private int keywordId;
	private String folderType;
	private int dateAdded;
	private int lastModified;
	private String guid;

	//DECLARACIÓN DE MÉTODOS GETTER
	public int getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public int getFk() {
		return fk;
	}

	public int getParent() {
		return parent;
	}

	public int getPosition() {
		return position;
	}

	public String getTitle() {
		return title;
	}

	public int getKeyword_id() {
		return keywordId;
	}

	public String getFolder_type() {
		return folderType;
	}

	public int getDateAdded() {
		return dateAdded;
	}

	public int getLastModified() {
		return lastModified;
	}

	public String getGuid() {
		return guid;
	}

	//DECLARACIÓN DE MÉTODOS SETTERS
	public void setId(int id) {
		this.id = id;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setFk(int fk) {
		this.fk = fk;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setKeywordId(int keyword_id) {
		this.keywordId = keyword_id;
	}

	public void setFolderType(String folder_type) {
		this.folderType = folder_type;
	}

	public void setDateAdded(int dateAdded) {
		this.dateAdded = dateAdded;
	}

	public void setLastModified(int lastModified) {
		this.lastModified = lastModified;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	
	/**
	 * Obtiene los valores del resultset.
	 */
	public void readResultSet(ResultSet rs) throws SQLException {
		id = rs.getInt(BOOKMARKS_ID);
		type = rs.getInt(BOOKMARKS_TYPE);
		fk = rs.getInt(BOOKMARKS_FK);
		parent = rs.getInt(BOOKMARKS_PARENT);
		position = rs.getInt(BOOKMARKS_POSITION);
		title = rs.getString(BOOKMARKS_TITTLE);
		keywordId = rs.getInt(BOOKMARKS_KEYWORD_ID);
		folderType = rs.getString(BOOKMARKS_FOLDER_TYPE);
		dateAdded = rs.getInt(BOOKMARKS_DATA_ADDED);
		lastModified = rs.getInt(BOOKMARKS_LAST_MODIFIED);
		guid = rs.getString(BOOKMARKS_GUID);

	}

	/**
	 * Devuelve el nombre de su propia tabla
	 */
	public String getTableName(){
		return BOOKMARKS_TABLE_NAME;
	}
	
	
	/**
	 * Clona la instancia actual de la clase
	 */
	public Bookmarks clone(){
		Bookmarks clone=new Bookmarks();
		clone.setId(id);
		clone.setType(type);
		clone.setFk(fk);
		clone.setParent(parent);
		clone.setPosition(position);
		clone.setTitle(title);
		clone.setKeywordId(keywordId);
		clone.setFolderType(folderType);
		clone.setDateAdded(dateAdded);
		clone.setLastModified(lastModified);
		clone.setGuid(guid);
		return clone;
	}
}
