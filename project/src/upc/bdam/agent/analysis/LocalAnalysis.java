package upc.bdam.agent.analysis;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import upc.bdam.agent.browser.BrowserAnalizer;
import upc.bdam.agent.browser.mozilla.MozillaBrowserAnalizer;
import upc.bdam.agent.config.PropertiesLoader;
import upc.bdam.agent.files.tika.FilesAnalizer;
import upc.bdam.agent.files.tika.beans.IMimeTypes;
import upc.bdam.agent.kafka.KafkaBean;

/**
 * Gestor especializado en la búsqueda de contenidos del dispositivo del usuario
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class LocalAnalysis {

	//se obtiene el path que se debe analizar con Tika
	String tikaLocation = PropertiesLoader.getInstance()
			.getProperty(PropertiesLoader.PROPERTIES_LOADER_TIKA_LOCATION);
	
	/**
	 * Obtiene todas las URL's almacenadas en la BBDD del navegador.
	 * @return
	 */
	public List<URL> browserAnalysis() {
		BrowserAnalizer browserAnalizer = new MozillaBrowserAnalizer();
		return browserAnalizer.getPlaces();
	}

	/**
	 * Procesa los ficheros PDF existentes en el PATH
	 * @return
	 */
	public List<KafkaBean> getPdfFiles() {
		FilesAnalizer filesAnalizer = new FilesAnalizer();
		List<KafkaBean> pdfs = new ArrayList<KafkaBean>();
		pdfs = filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_PDF_TYPE);
		return pdfs;
	}
	
	/**
	 * Procesa los ficheros de audio existnetes en el PATH
	 * @return
	 */
	public List<KafkaBean> getMp3Files() {
		FilesAnalizer filesAnalizer = new FilesAnalizer();
		List<KafkaBean> mp3 = new ArrayList<KafkaBean>();
		
		//Se buscan los ficheros de audio por cada uno de los posibles mime type 
		//que definen un fichero de audio.
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_1_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_2_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_3_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_4_TYPE));
		
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_5_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_7_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_8_TYPE));
		
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_9_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_10_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_11_TYPE));
		
		return mp3;
	}
}
