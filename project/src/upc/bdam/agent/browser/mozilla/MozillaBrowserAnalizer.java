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

/**
 * Analiza la BBDD local MySql de firefox. Para el piloto, hemos decidio analizar �nicamente firefox, pero el comportamiento
 * se podr�a generalizar para el resto de navegadores que utilicen este mecanismo de almacenamiento de historial de 
 * navegaci�n.
 * 
 * Igualmente y despu�s de analizar el modelo de datos que utiliza, hemos cre�do que con analizar la tabla places ser�a
 * suficiente, ya que la informaci�n es redundante en alguna de las tablas.
 * 
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class MozillaBrowserAnalizer implements BrowserAnalizer{

	//connector a la BBDD local de firefox
	private IJdbcConnector<IJdbcTableBean> connector;

	/**
	 * constructor
	 */
	public MozillaBrowserAnalizer() {
		try {
			init();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * M�todo de inicializaci�n de los componentes necesarios para la conexi�n a la BBDD local
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */	
	public void init()throws ClassNotFoundException, SQLException{
		//se accede al fichero de propiedades para obtener el path de la BBDD y la cadena de conexi�n
		String dbLocation=PropertiesLoader.PROPERTIES_LOADER_MOZILLA_BBDD_LOCATION;
		String dbDriver=PropertiesLoader.PROPERTIES_LOADER_MOZILLA_SQLITE_DRIVER;
		
		//se abre la conexi�n a la BBDD de datos
		connector=new SQLiteConnector<IJdbcTableBean>();
		connector.setDriverName(PropertiesLoader.getInstance().getProperty(dbDriver));
		connector.setDBLocation(PropertiesLoader.getInstance().getProperty(dbLocation));
		connector.openConnection();
	}

	
	/**
	 * Obtenci�n de los lugares visitados por el usuario y almacenados en la BBDD. 
	 */
	public List<URL> getPlaces(){
		List<URL> mozillaPlaces=new ArrayList<URL>(); //se instancia una lista en la que albergar las URL obtenidas
		Places placeBean=new Places(); 		//se instancia el poco en el que 
		
		//se accede a la tabla Places
		List<IJdbcTableBean> places=connector.queryTable(placeBean);

		//se recorren los places en busca de sus URL's
		for (IJdbcTableBean place: places){
			URL url;
			String uri=((Places)place).getUrl();
			
			//es este punto se a�ade el try, para que la iteraci�n no pare si hay errores y 
			//siga a�adiendo el resto de URL's
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
