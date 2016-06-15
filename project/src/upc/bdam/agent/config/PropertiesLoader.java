package upc.bdam.agent.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Proporciona la interfaz de acceso al fichero de propiedades de la aplicación 
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class PropertiesLoader {
	//declaración de las constantes de los nombres de las variables declaradas en el fichero
	public static final String PROPERTIES_LOADER_TIKA_LOCATION= "tikaPath";
	public static final String PROPERTIES_LOADER_MOZILLA_CRAWLER_DOWNLOAD_PATH ="mozillaCrawlerDownloadPath";
	public static final String PROPERTIES_LOADER_MOZILLA_SQLITE_DRIVER="mozillaSQLITEDriver";
	public static final String PROPERTIES_LOADER_MOZILLA_BBDD_LOCATION="mozillaBBBDDLocation";
	public static final String PROPERTIES_LOADER_WATCHGUARD_LOCATION="watchguardPath";

	//declaración de atributos
	private static PropertiesLoader instance = null;
	Properties prop = new Properties();

	/**
	 * Constructor de clase
	 */
	private PropertiesLoader() {
		init();
	}

	/**
	 * Método instance para el acceso global a la única instancia de la clase
	 * @return
	 */
	public static PropertiesLoader getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new PropertiesLoader();
			return instance;
		}
	}

	/**
	 * Inicialización con la carga inicial de propiedades
	 */
	private void init() {

		String fileName = "localAgent.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			if (inputStream != null)
				prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método de acceso a las propiedades leídas
	 * @param propertyName
	 * @return
	 */
	public String getProperty(String propertyName){
		return prop.getProperty(propertyName);
	}

}
