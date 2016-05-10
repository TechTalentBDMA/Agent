package upc.bdam.agent.browser.mozilla.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import upc.bdam.agent.jdbc.IJdbcTableBean;

public class InputHistory implements IJdbcTableBean{

	public static final String INPUT_HISTORY_TABLE_NAME="moz_inputhistory";
	public static final String INPUT_HISTORY_PLACED_ID = "placed_id";
	public static final String INPUT_HISTORY_INPUT = "input";
	public static final String INPUT_HISTORY_USE_COUNT = "use_count";

	private int placeId;
    private String input;
    private int useCount;

	public int getPlaceId() {
		return placeId;
	}

	public String getInput() {
		return input;
	}

	public int getUse_count() {
		return useCount;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public void setUseCount(int use_count) {
		this.useCount = use_count;
	}

	public void readResultSet(ResultSet rs) throws SQLException {
		placeId = rs.getInt(INPUT_HISTORY_PLACED_ID);
		input = rs.getString(INPUT_HISTORY_INPUT);
		useCount = rs.getInt(INPUT_HISTORY_USE_COUNT);
	}

	public String getTableName(){
		return INPUT_HISTORY_TABLE_NAME;
	}

	public InputHistory clone(){
		InputHistory clone=new InputHistory();
		clone.setPlaceId(placeId);
		clone.setInput(input);
		clone.setUseCount(useCount);
		return clone;
	}
}
