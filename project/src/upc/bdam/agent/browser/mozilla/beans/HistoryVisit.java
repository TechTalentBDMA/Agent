package upc.bdam.agent.browser.mozilla.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import upc.bdam.agent.jdbc.IJdbcTableBean;

/**
 * Clase que representa cada uno de los sitios que han sido visitados.
 * Perteneciente a PLACES.SQLITE
 *
 */
public class HistoryVisit implements IJdbcTableBean{

	public static final String HISTORY_VISIT_TABLE_NAME="moz_historyvisits";
	public static final String HISTORY_VISIT_ID = "id";
	public static final String HISTORY_VISIT_FROM_VISIT = "from_visit";
	public static final String HISTORY_VISIT_PLACE_ID = "place_id";
	public static final String HISTORY_VISIT_VISIT_DATE = "visit_date";
	public static final String HISTORY_VISIT_VISIT_TYPE = "visit_type";
	public static final String HISTORY_VISIT_SESSION = "session";

	// atributos
	private int id;
	private int fromVisit;
	private int placeId;
	private int visitDate;
	private int visitType;
	private int session;

	// métodos getter
	public int getId() {
		return id;
	}

	public int getFromVisit() {
		return fromVisit;
	}

	public int getPlaceId() {
		return placeId;
	}

	public int getVisitDate() {
		return visitDate;
	}

	public int getVisitType() {
		return visitType;
	}

	public int getSession() {
		return session;
	}

	// métodos setter
	public void setId(int id) {
		this.id = id;
	}

	public void setFromVisit(int fromVisit) {
		this.fromVisit = fromVisit;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public void setVisitDate(int visitDate) {
		this.visitDate = visitDate;
	}

	public void setVisitType(int visitType) {
		this.visitType = visitType;
	}

	public void setSession(int session) {
		this.session = session;
	}

	public void readResultSet(ResultSet rs) throws SQLException {

		id = rs.getInt(HISTORY_VISIT_ID);
		fromVisit = rs.getInt(HISTORY_VISIT_FROM_VISIT);
		placeId = rs.getInt(HISTORY_VISIT_ID);
		visitDate = rs.getInt(HISTORY_VISIT_VISIT_DATE);
		visitType = rs.getInt(HISTORY_VISIT_VISIT_TYPE);
		session = rs.getInt(HISTORY_VISIT_SESSION);
	}

	public String getTableName(){
		return HISTORY_VISIT_TABLE_NAME;
	}
	
	public HistoryVisit clone(){
		HistoryVisit clone=new HistoryVisit();
		clone.setId(id);
		clone.setFromVisit(fromVisit);
		clone.setPlaceId(placeId);
		clone.setVisitDate(visitDate);
		clone.setVisitType(visitType);
		clone.setSession(session);
		return clone;
	}
}
