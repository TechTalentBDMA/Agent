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
 * Analiza la BBDD local MySql de firefox. Para el piloto, hemos decidio analizar únicamente firefox, pero el comportamiento
 * se podría generalizar para el resto de navegadores que utilicen este mecanismo de almacenamiento de historial de 
 * navegación.
 * 
 * Igualmente y después de analizar el modelo de datos que utiliza, hemos creído que con analizar la tabla places sería
 * suficiente, ya que la información es redundante en alguna de las tablas.
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
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
	 * Método de inicialización de los componentes necesarios para la conexión a la BBDD local
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */	
	public void init()throws ClassNotFoundException, SQLException{
		//se accede al fichero de propiedades para obtener el path de la BBDD y la cadena de conexión
		String dbLocation=PropertiesLoader.PROPERTIES_LOADER_MOZILLA_BBDD_LOCATION;
		String dbDriver=PropertiesLoader.PROPERTIES_LOADER_MOZILLA_SQLITE_DRIVER;
		
		//se abre la conexión a la BBDD de datos
		connector=new SQLiteConnector<IJdbcTableBean>();
		connector.setDriverName(PropertiesLoader.getInstance().getProperty(dbDriver));
		connector.setDBLocation(PropertiesLoader.getInstance().getProperty(dbLocation));
		connector.openConnection();
	}

	
	/**
	 * Obtención de los lugares visitados por el usuario y almacenados en la BBDD. 
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
			
			//es este punto se añade el try, para que la iteración no pare si hay errores y 
			//siga añadiendo el resto de URL's
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
