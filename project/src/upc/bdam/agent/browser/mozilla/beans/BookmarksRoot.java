package upc.bdam.agent.browser.mozilla.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import upc.bdam.agent.jdbc.IJdbcTableBean;

/**
 * 
 * BookmarksRoot. Bean para guardar los datos de la tabla BookmarksRoot.
 * Finalmente no se utiliza para el piloto
 * 
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class BookmarksRoot implements IJdbcTableBean {

	//Declaraci�n del nombre de los campos de la tabla en la BBDD del navegador
	public static final String BOOKMARKS_ROOT_TABLE_NAME="moz_bookmarks_roots";
	public static final String BOOKMARKS_ROOT_ROOT_NAME = "root_name";
	public static final String BOOKMARKS_ROOT_FOLDER_ID = "folder_id";

	//Declaraci�n de los atributos de clase
	private String rootName;
	private int folderId;

	//DECLARACI�N DE M�TODOS GETTERS
	public String getRootName() {
		return rootName;
	}

	public int getFolderId() {
		return folderId;
	}

	//DECLARACI�N DE M�TODOS SETTERS
	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	/**
	 * Obtiene los valores del resultset.
	 */
	public void readResultSet(ResultSet rs) throws SQLException {
		rootName = rs.getString(BOOKMARKS_ROOT_ROOT_NAME);
		folderId = rs.getInt(BOOKMARKS_ROOT_FOLDER_ID);
	}

	/**
	 * Devuelve el nombre de su propia tabla
	 */
	public String getTableName(){
		return BOOKMARKS_ROOT_TABLE_NAME;
	}

	/**
	 * Clona la instancia actual de la clase
	 */
	public BookmarksRoot clone(){
		BookmarksRoot clone=new BookmarksRoot();
		clone.setRootName(rootName);
		clone.setFolderId(folderId);
		return clone;
	}
}
