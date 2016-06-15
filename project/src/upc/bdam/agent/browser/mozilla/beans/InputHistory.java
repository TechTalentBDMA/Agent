package upc.bdam.agent.browser.mozilla.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import upc.bdam.agent.jdbc.IJdbcTableBean;

/**
 * 
 * InputHistory. Bean para guardar los datos de la tabla InputHistory.
 * Finalmente no se utiliza para el piloto
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class InputHistory implements IJdbcTableBean{
	//Declaración del nombre de los campos de la tabla en la BBDD del navegador
	public static final String INPUT_HISTORY_TABLE_NAME="moz_inputhistory";
	public static final String INPUT_HISTORY_PLACED_ID = "placed_id";
	public static final String INPUT_HISTORY_INPUT = "input";
	public static final String INPUT_HISTORY_USE_COUNT = "use_count";

	//Declaración de los atributos de clase
	private int placeId;
    private String input;
    private int useCount;

    //DECLARACIÓN DE MÉTODOS GETTERS
	public int getPlaceId() {
		return placeId;
	}

	public String getInput() {
		return input;
	}

	public int getUse_count() {
		return useCount;
	}

	//DECLARACIÓN DE MÉTODOS SETTERS
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public void setUseCount(int use_count) {
		this.useCount = use_count;
	}

	/**
	 * Obtiene los valores del resultset.
	 */
	public void readResultSet(ResultSet rs) throws SQLException {
		placeId = rs.getInt(INPUT_HISTORY_PLACED_ID);
		input = rs.getString(INPUT_HISTORY_INPUT);
		useCount = rs.getInt(INPUT_HISTORY_USE_COUNT);
	}

	/**
	 * Devuelve el nombre de su propia tabla
	 */
	public String getTableName(){
		return INPUT_HISTORY_TABLE_NAME;
	}

	/**
	 * Clona la instancia actual de la clase
	 */
	public InputHistory clone(){
		InputHistory clone=new InputHistory();
		clone.setPlaceId(placeId);
		clone.setInput(input);
		clone.setUseCount(useCount);
		return clone;
	}
}
