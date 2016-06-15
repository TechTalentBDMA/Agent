package upc.bdam.agent.browser.mozilla.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import upc.bdam.agent.jdbc.IJdbcTableBean;

/**
 * 
 * Places. Bean para guardar los datos de la tabla Places en la que se almacena la dirección de todas las páginas 
 * web visitadas por el usuario.
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class Places implements IJdbcTableBean{
	//Declaración del nombre de los campos de la tabla en la BBDD del navegador
	public static final String PLACES_TABLE_NAME= "moz_places";
	public static final String PLACES_ID = "id";
	public static final String PLACES_URL = "url";
	public static final String PLACES_TITLE = "title";
	public static final String PLACES_REV_HOST = "rev_host";
	public static final String PLACES_VISIT_COUNT = "visit_count";
	public static final String PLACES_HIDDEN = "hidden";
	public static final String PLACES_TYPED = "typed";
	public static final String PLACES_FAVICON_ID = "favicon_id";
	public static final String PLACES_FRECENCY = "frecency";
	public static final String PLACES_LAST_VISIT_DATE = "last_visit_date";
	public static final String PLACES_GUID = "guid";
	public static final String PLACES_FOREIGN_COUNT = "foreign_count";

	//Declaración de los atributos de clase
	private int id;
	private String url;
	private String title;
	private String revHost;
	private String visitCount;
	private int hidden;
	private int typed;
	private int faviconId;
	private int frecency;
	private int lastVisitDate;
	private String guid;
	private int foreignCount;

	//DECLARACIÓN DE MÉTODOS GETTERS
	public int getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public String getRevHost() {
		return revHost;
	}

	public String getVisitCount() {
		return visitCount;
	}

	public int getHidden() {
		return hidden;
	}

	public int getTyped() {
		return typed;
	}

	public int getFaviconId() {
		return faviconId;
	}

	public int getFrecency() {
		return frecency;
	}

	public int getLastVisitDate() {
		return lastVisitDate;
	}

	public String getGuid() {
		return guid;
	}

	public int getForeignCount() {
		return foreignCount;
	}

	//DECLARACIÓN DE MÉTODOS SETTERS
	public void setId(int id) {
		this.id = id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setRevHost(String revHost) {
		this.revHost = revHost;
	}

	public void setVisitCount(String visitCount) {
		this.visitCount = visitCount;
	}

	public void setHidden(int hidden) {
		this.hidden = hidden;
	}

	public void setTyped(int typed) {
		this.typed = typed;
	}

	public void setFaviconId(int faviconId) {
		this.faviconId = faviconId;
	}

	public void setFrecency(int frecency) {
		this.frecency = frecency;
	}

	public void setLastVisitDate(int lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public void setForeignCount(int foreignCount) {
		this.foreignCount = foreignCount;
	}

	/**
	 * Obtiene los valores del resultset.
	 */
	public void readResultSet(ResultSet rs) throws SQLException {

		id = rs.getInt(PLACES_ID);
		url = rs.getString(PLACES_URL);
		title = rs.getString(PLACES_TITLE);
		revHost = rs.getString(PLACES_REV_HOST);
		visitCount = rs.getString(PLACES_VISIT_COUNT);
		hidden = rs.getInt(PLACES_HIDDEN);
		typed = rs.getInt(PLACES_TYPED);
		faviconId = rs.getInt(PLACES_FAVICON_ID);
		frecency = rs.getInt(PLACES_FRECENCY);
		lastVisitDate = rs.getInt(PLACES_LAST_VISIT_DATE);
		guid = rs.getString(PLACES_GUID);
		foreignCount = rs.getInt(PLACES_FOREIGN_COUNT);
	}

	/**
	 * Devuelve el nombre de su propia tabla
	 */
	public String getTableName(){
		return PLACES_TABLE_NAME;
	}

	/**
	 * Clona la instancia actual de la clase
	 */
	public Places clone(){
		Places clone=new Places();
		clone.setId(id);
		clone.setUrl(url);
		clone.setTitle(title);
		clone.setRevHost(revHost);
		clone.setVisitCount(visitCount);
		clone.setHidden(hidden);
		clone.setTyped(typed);
		clone.setFaviconId(faviconId);
		clone.setFrecency(frecency);
		clone.setLastVisitDate(lastVisitDate);
		clone.setGuid(guid);
		clone.setForeignCount(foreignCount);
		return clone;
	}
}
