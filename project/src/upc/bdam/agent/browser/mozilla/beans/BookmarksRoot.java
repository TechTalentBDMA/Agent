package upc.bdam.agent.browser.mozilla.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import upc.bdam.agent.jdbc.IJdbcTableBean;

public class BookmarksRoot implements IJdbcTableBean {

	public static final String BOOKMARKS_ROOT_TABLE_NAME="moz_bookmarks_roots";
	public static final String BOOKMARKS_ROOT_ROOT_NAME = "root_name";
	public static final String BOOKMARKS_ROOT_FOLDER_ID = "folder_id";

	// atributos
	private String rootName;
	private int folderId;

	// getters
	public String getRootName() {
		return rootName;
	}

	public int getFolderId() {
		return folderId;
	}

	// setters
	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	public void readResultSet(ResultSet rs) throws SQLException {

		rootName = rs.getString(BOOKMARKS_ROOT_ROOT_NAME);
		folderId = rs.getInt(BOOKMARKS_ROOT_FOLDER_ID);
	}

	public String getTableName(){
		return BOOKMARKS_ROOT_TABLE_NAME;
	}
	
	public BookmarksRoot clone(){
		BookmarksRoot clone=new BookmarksRoot();
		clone.setRootName(rootName);
		clone.setFolderId(folderId);
		return clone;
	}
}
