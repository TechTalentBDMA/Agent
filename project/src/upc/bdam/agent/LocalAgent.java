package upc.bdam.agent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import upc.bdam.agent.analysis.LocalAnalysis;
import upc.bdam.agent.crawler.SiteDownloadingCrawler;
import upc.bdam.agent.files.tika.beans.TikaFileBean;
import websphinx.Link;

public class LocalAgent {

	private static LocalAnalysis localAnalysis;

	public static void main(String[] args) throws MalformedURLException {
		localAnalysis = new LocalAnalysis();

		browseAnalysis();
		filesAnalysis();
	}

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

	public static void filesAnalysis() {
		List<TikaFileBean> ficherosPDF = localAnalysis.getPdfFiles();
		List<TikaFileBean> ficherosMP3 = localAnalysis.getMp3Files();

		System.out.println("EL N�MERO DE FICHEROS PDF ES: " + ficherosPDF.size());
		System.out.println("EL N�MERO DE FICHEROS MP3 ES: " + ficherosMP3.size());

	}
}