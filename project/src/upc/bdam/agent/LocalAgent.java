package upc.bdam.agent;

import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import upc.bdam.agent.analysis.LocalAnalysis;
import upc.bdam.agent.crawler.SiteDownloadingCrawler;
import upc.bdam.agent.files.tika.beans.TikaFileBean;
import upc.bdam.agent.files.watchGuard.WatchGuard;
import upc.bdam.recommender.documentDDBB.dao.DocumentDataSource;
import websphinx.Link;

/**
 * Clase principal para la gesti�n del agente.
 * 
 * Dispone de un men� para ejecutar de forma independiente las acciones que recopilan informaci�n del dispositivo del 
 * usuario.
 * 
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class LocalAgent {

	//se instancia el gestor de reopilaci�n 
	private static LocalAnalysis localAnalysis;

	
	public static void main(String[] args) throws Exception {
		//se inicia el demonio de vigilancia de la estructura de directorios seleccionado
		doWatch();
		
		
		localAnalysis = new LocalAnalysis();		
		filesAnalysis();
		browseAnalysis();

	}

	/**
	 * Obtiene las p�ginas web de las URL almacenadas en la tabla
	 * Places de la BBDD de Mozilla
	 */
	public static void browseAnalysis() {
		//an�lisis de las p�ginas almacenadas en la BBDD del navegador
		List<URL> urls = localAnalysis.browserAnalysis();

		//se instancia el crawler para analizar las p�ginas identificadas
		SiteDownloadingCrawler crawler = new SiteDownloadingCrawler(true);

		//se inicia un pool de thread para lanzar de forma paralela la descarga de p�ginas
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for (URL url : urls) {
			crawler.setRoot(new Link(url));
			crawler.setMaxDepth(1); // only crawl 2 levels deep, default 5
			crawler.run();
			executor.execute(crawler);
			crawler = new SiteDownloadingCrawler(false);
		}

		//una vez descargadas las p�ginas se para el pool de thrads
		executor.shutdown(); 
		
		
		while (!executor.isTerminated()) {
			// Espero a que terminen de ejecutarse todos los procesos
			// para pasar a las siguientes instrucciones
		}
	}

	/**
	 * Extrae mediante Tika los ficheros de m�sica y de texto en
	 * formatos PDF y MP3.
	 * 
	 * Se recopila cada tipo de ficheros de forma independente
	 */
	public static void filesAnalysis() {
		DocumentDataSource dataSource=new DocumentDataSource();
		
		List<TikaFileBean> ficherosPDF = localAnalysis.getPdfFiles();
		
		List<TikaFileBean> ficherosMP3 = localAnalysis.getMp3Files();

		//c�digo a eliminar. Inserta en una BBDD similar a lo que se espera en el consumer de kafka
		//for (TikaFileBean fichero: ficherosPDF){
		//	dataSource.insertTika(fichero);
		//}
		
		System.out.println("EL N�MERO DE FICHEROS PDF ES: " + ficherosPDF.size());
		System.out.println("EL N�MERO DE FICHEROS MP3 ES: " + ficherosMP3.size());

	}
	
	/**
	 * Se lanza un proceso para vigilar la modificaci�n de documentos en el directorio seleccionado
	 */
	public static void doWatch(){
		
		WatchGuard guard=new WatchGuard();
		Thread a=new Thread(guard);
		a.start();	
	}
}
