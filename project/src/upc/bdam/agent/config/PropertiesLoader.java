package upc.bdam.agent.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	
	public static final String PROPERTIES_LOADER_TIKA_LOCATION= "tikaPath";
	public static final String PROPERTIES_LOADER_MOZILLA_CRAWLER_DOWNLOAD_PATH ="mozillaCrawlerDownloadPath";
	public static final String PROPERTIES_LOADER_MOZILLA_SQLITE_DRIVER="mozillaSQLITEDriver";
	public static final String PROPERTIES_LOADER_MOZILLA_BBDD_LOCATION="mozillaBBBDDLocation";
	public static final String PROPERTIES_LOADER_WATCHGUARD_LOCATION="watchguardPath";


	private static PropertiesLoader instance = null;
	Properties prop = new Properties();

	private PropertiesLoader() {
		init();
	}

	public static PropertiesLoader getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new PropertiesLoader();
			return instance;
		}
	}

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

	public String getProperty(String propertyName){
		return prop.getProperty(propertyName);
	}

}
