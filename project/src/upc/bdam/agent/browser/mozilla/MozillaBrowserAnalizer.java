package upc.bdam.agent.browser.mozilla;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import upc.bdam.agent.browser.BrowserAnalizer;
import upc.bdam.agent.browser.mozilla.beans.Places;
import upc.bdam.agent.config.PropertiesLoader;
import upc.bdam.agent.jdbc.IJdbcConnector;
import upc.bdam.agent.jdbc.IJdbcTableBean;
import upc.bdam.agent.jdbc.SQLITE.SQLiteConnector;

public class MozillaBrowserAnalizer implements BrowserAnalizer{

	private IJdbcConnector<IJdbcTableBean> connector;

	public MozillaBrowserAnalizer() {
		try {
			init();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void init()throws ClassNotFoundException, SQLException{
		String dbLocation=PropertiesLoader.PROPERTIES_LOADER_MOZILLA_BBDD_LOCATION;
		String dbDriver=PropertiesLoader.PROPERTIES_LOADER_MOZILLA_SQLITE_DRIVER;
		connector=new SQLiteConnector<IJdbcTableBean>();
		connector.setDriverName(PropertiesLoader.getInstance().getProperty(dbDriver));
		connector.setDBLocation(PropertiesLoader.getInstance().getProperty(dbLocation));
		connector.openConnection();
	}

	public List<URL> getPlaces(){

		Places placeBean=new Places();
		List<IJdbcTableBean> places=connector.queryTable(placeBean);
		List<URL> mozillaPlaces=new ArrayList<URL>();
		for (IJdbcTableBean place: places){
			String uri=((Places)place).getUrl();
			URL url;
			//añado en este punto el try, para que no pare si hay errores y 
			//siga añadiendo el resto.
			try {
				url = new URL(uri);
				mozillaPlaces.add(url);
				System.out.println(uri);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return mozillaPlaces;	
	}

}
