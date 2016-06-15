package upc.bdam.agent.browser;

import java.net.URL;
import java.util.List;

/**
 * Interface que deben cumplir las clases especializadas en el an�lisis de los distintos navegadores que se vayan a 
 * analizar.
 * 
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public interface BrowserAnalizer {

	//public List<URL> getHistoryVisits();

	//public List<URL> getBookmarks();
	
	//public List<URL> getHosts();
	
	public List<URL> getPlaces();
} 
