package upc.bdam.agent;

import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import upc.bdam.agent.analysis.LocalAnalysis;
import upc.bdam.agent.crawler.SiteDownloadingCrawler;
import upc.bdam.agent.files.tika.beans.TikaFileBean;
import upc.bdam.agent.files.watchGuard.WatchGuard;
import websphinx.Link;

public class LocalAgent {

	private static LocalAnalysis localAnalysis;

	public static void main(String[] args) throws Exception {
		doWatch();
		localAnalysis = new LocalAnalysis();

		browseAnalysis();
		filesAnalysis();
	}

	/**
	 * Obtiene las páginas web de las URL almacenadas en la tabla
	 * Places de la BBDD de Mozilla
	 */
	public static void browseAnalysis() {

		List<URL> urls = localAnalysis.browserAnalysis();

		SiteDownloadingCrawler crawler = new SiteDownloadingCrawler(true);

		ExecutorService executor = Executors.newFixedThreadPool(2);
		for (URL url : urls) {
			crawler.setRoot(new Link(url));
			crawler.setMaxDepth(1); // only crawl 2 levels deep, default 5
			crawler.run();
			executor.execute(crawler);
			crawler = new SiteDownloadingCrawler(false);
		}

		executor.shutdown(); // Cierro el Executor
		while (!executor.isTerminated()) {
			// Espero a que terminen de ejecutarse todos los procesos
			// para pasar a las siguientes instrucciones
		}
	}

	/**
	 * Extrae mediante Tika los ficheros de música y de texto en
	 * formatos PDF y MP3
	 */
	public static void filesAnalysis() {
		List<TikaFileBean> ficherosPDF = localAnalysis.getPdfFiles();
		
		List<TikaFileBean> ficherosMP3 = localAnalysis.getMp3Files();

		System.out.println("EL NÚMERO DE FICHEROS PDF ES: " + ficherosPDF.size());
		System.out.println("EL NÚMERO DE FICHEROS MP3 ES: " + ficherosMP3.size());

	}
	
	public static void doWatch(){
		
		WatchGuard guard=new WatchGuard();
		Thread a=new Thread(guard);
		a.start();	
	}
}
