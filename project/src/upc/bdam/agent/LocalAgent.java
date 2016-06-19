package upc.bdam.agent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.common.record.KafkaLZ4BlockInputStream;

import upc.bdam.agent.analysis.LocalAnalysis;
import upc.bdam.agent.crawler.SiteDownloadingCrawler;
import upc.bdam.agent.files.tika.beans.TikaFileBean;
import upc.bdam.agent.files.watchGuard.WatchGuard;
import upc.bdam.agent.kafka.AgentProducer;
import upc.bdam.agent.kafka.KafkaBean;
import upc.bdam.agent.kafka.KafkaEncoder;
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
	private static LocalAnalysis localAnalysis= new LocalAnalysis();

	
	public static void main(String[] args) throws Exception {
		menu();
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
	private static void filesAnalysis() {
		AgentProducer producer=new AgentProducer();
		KafkaEncoder encoder=new KafkaEncoder();
		
//		DocumentDataSource dataSource=new DocumentDataSource();
		
		List<KafkaBean> ficherosPDF = localAnalysis.getPdfFiles();
		
		List<KafkaBean> ficherosMP3 = localAnalysis.getMp3Files();

		KafkaBean aux=ficherosPDF.get(0);

		byte[] kafkaInfo=encoder.serialize(aux);
		producer.produce(kafkaInfo);
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
	private static void doWatch(){
		
		WatchGuard guard=new WatchGuard();
		Thread a=new Thread(guard);
		a.start();	
	}
	
	
	/**
	 * Muestra un peque�o men� para poder observar cada una de las cargas de forma independiente
	 */
	private static void menu(){

		int opcion=0;

		//se inicia el demonio de vigilancia de la estructura de directorios seleccionado
		doWatch();

		do{
			String salida=new String();
			salida+="\n***********************************************\n";
			salida+="* Que acci�n de las siguientes desea realizar:  *\n";
			salida+="* 1- An�lisis de ficheros                       *\n";
			salida+="* 2- An�lisis de navegador                      *\n";
			salida+="* 3- Salir                                      *\n";
			salida+="*************************************************\n";

			System.out.println(salida);

			try {
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			    String aux = bufferRead.readLine();
				opcion=Integer.parseInt(aux);
			}catch (Exception ex){
				System.out.println("Se produjo un error leyendo la opci�n. Elija de nuevo\n");
				continue;
			}

			if (opcion<0 || opcion>3){
				System.out.println("Opcion incorrecta. Elija de nuevo\n");
				continue;
			}else if (opcion==3)
				System.exit(0);
			else if (opcion==1)
				filesAnalysis();
			else if (opcion==2)
				browseAnalysis();
		}while (true);
	
	}
	
	
}
