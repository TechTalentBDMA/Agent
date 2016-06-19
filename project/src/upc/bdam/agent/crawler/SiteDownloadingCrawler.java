// SiteDownloadingCrawler.java
package upc.bdam.agent.crawler;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import upc.bdam.agent.config.PropertiesLoader;
import upc.bdam.agent.kafka.AgentProducer;
import upc.bdam.agent.kafka.KafkaBean;
import upc.bdam.agent.kafka.KafkaEncoder;
import websphinx.Page;

/**
 * Clase especializada para la descarga de las páginas de las URL's recibidas como parámetro.
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class SiteDownloadingCrawler extends AgentCrawler {

	private static final long serialVersionUID = 64989986095789110L;

	private String targetDir;

	/**
	 * Se obtiene el path en el que se almacenarán las páginas descargadas. Dependiendo del valor del parámetro recibido
	 * se borra el contenido del directorio si este ya exite, creándolo de nuevo
	 * @param borrado
	 */
	public SiteDownloadingCrawler(boolean borrado) {
		//se recupera el path del fichero de propiedades
		String downloadPath = PropertiesLoader.PROPERTIES_LOADER_MOZILLA_CRAWLER_DOWNLOAD_PATH;
		targetDir = PropertiesLoader.getInstance().getProperty(downloadPath);

		//se decide si se debe borrar el directorio de destino
		if (borrado)
			init();
	}

	/**
	 * Se realizan las acciones de inicialización de la clase
	 */
	private void init() {

		File targetDirFile = new File(targetDir);
		try {
			if (targetDirFile.exists()) {

				FileUtils.forceDelete(targetDirFile);
			}
			FileUtils.forceMkdir(targetDirFile);
		} catch (IOException ex) {

		}
	}

	@Override
	protected void doVisit(Page page) {
		AgentProducer producer=new AgentProducer();
		KafkaEncoder encoder=new KafkaEncoder();
		
		//se obtiene la URL a descargar
		URL url = page.getURL();
		KafkaBean bean=new KafkaBean();
		String path = url.getHost().replace('.', '-') + "/" + url.getPath().replaceFirst("/", "");

		bean.setMetadata(path);
		bean.setContent(new String(page.getContentBytes()));
		
		byte[] kafkaInfo=encoder.serialize(bean);
		producer.produce(kafkaInfo);
	}
	
//	@Override
//	protected void doVisit(Page page) {
//		//se obtiene la URL a descargar
//		URL url = page.getURL();
//		try {
//			//se formatea el nombre de la página para que no tenga caracteres no permitidos
//			String path = url.getHost().replace('.', '-') + "/" + url.getPath().replaceFirst("/", "");
//			
//			//se comprueba que el nombre no esté vacío, hay con algunos elementos que ocurre esto 
//			if (StringUtils.isNotEmpty(path)) {
//				//otro error que puede ocurrir es que exista página y directorio con el mismo nombre (en el sitio a des-
//				//cargar). Si lo primero que se crea es el fichero, luego no se puede construir el directorio y todas
//				//las páginas qeu cuelgan de él, así que lo primero que hacemos es crear un directorio por cada URL.
//				String targetPathName = FilenameUtils.concat(targetDir, path);
//				File targetPath = new File(FilenameUtils.getPath(targetPathName));
//				FileUtils.forceMkdir(targetPath);
//				File targetFile2 = new File(targetPathName + "(1)");
//				FileUtils.writeByteArrayToFile(targetFile2, page.getContentBytes());
//			}
//		} catch (Exception e) {
//			log.error("Could not download url:" + url.toString(), e);
//		}
//	}
}