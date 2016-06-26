package upc.bdam.agent.files.tika;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import upc.bdam.agent.kafka.KafkaBean;

/**
 * Se procesa el contenido de los ficheros existentes en un determinado path
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class FilesAnalizer {

	/**
	 * Se obtienen los fichero de un determinado tipo, en un determinado directorio.
	 * 
	 * @param path en el que se deben procesar los ficheros
	 * @param mimeType de los ficheros que se deben procesar 
	 * @return
	 */
	public List<KafkaBean> getFiles(String path, String mimeType) {
		KafkaBean kafkaBean;
		
		//lista en la que se guardan los ficheros obtenidos
		List<KafkaBean> ficheros = new ArrayList<KafkaBean>();
		
		//clase de utilidad para la obtención de los ficheros
		ListFilesUtil listFilesUtil = new ListFilesUtil();
		ArrayList<String> fsc = listFilesUtil
				.listFilesAndFilesSubDirectories(path, mimeType);
		
		//una vez recuperados los ficheros se itera sobre ellos mostrando sus propiedades
		Iterator<String> itr = fsc.iterator();

		while (itr.hasNext()) {
			try {
				String filepath = itr.next();
				Parser parser = new AutoDetectParser();
				BodyContentHandler handler = new BodyContentHandler();
				Metadata metadata = new Metadata();
				ParseContext context = new ParseContext();
				FileInputStream inputstream;

				inputstream = new FileInputStream(filepath);

				parser.parse(inputstream, handler, metadata, context);
//				System.out
//						.println("====================================================");
//				System.out
//						.println("========== METADATOS   =============================");
//				System.out
//						.println("====================================================");
//				printMetadata(metadata);
//				System.out
//						.println("====================================================");
//				System.out
//						.println("========== CUERPO   =============================");
//				System.out
//						.println("====================================================");
//				System.out.println(handler.toString());
//				System.out
//						.println("====================================================");
				metadata.get("creator");
				kafkaBean=fillKafkaBean(metadata, handler, mimeType, path);

				ficheros.add(kafkaBean);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (TikaException e) {
				e.printStackTrace();
			}
		}
		return ficheros;
	}
	
	/**
	 * Se genera el bean que se va a transmitir a kafka con los datos obtenidos del procesamiento del fichero
	 * @param metadata
	 * @param handler
	 * @return
	 */
	private KafkaBean fillKafkaBean(Metadata metadata, BodyContentHandler handler, String mimeType, String path){
		KafkaBean kafkaBean=new KafkaBean();
		if (metadata!=null && handler!=null){
			String contenido=handler.toString();			
			kafkaBean.setPalabras(Integer.toString(contenido.split(" ").length));
			kafkaBean.setContent(contenido);
			kafkaBean.setMetadata(metadata.toString());
			kafkaBean.setMimeType(mimeType);
			kafkaBean.setFichero(path);
			kafkaBean.setId(Long.toString(new Date().getTime()));
			kafkaBean.setStatus("new");
			return kafkaBean;			
		}
		return kafkaBean;
	}

	/**
	 * Se muestra por pantalla las propiedades de metadatos del fichero. Inicialmente se muestran todos para evaluar la 
	 * frecuencia en que vienen rellenos, y ayudarnos a crear el modelo de datos que utilizaremos después en el Big1 de 
	 * Mongo.
	 * @param metadata
	 */
	public void printMetadata(Metadata metadata){
		System.out.println("CREATED");
		System.out.println("\n"+metadata.get("created")+"\n");			 
		System.out.println("CREATOR");
		System.out.println("\n"+metadata.get("creator")+"\n");	
		System.out.println("MODIFIED");
		System.out.println("\n"+metadata.get("modified")+"\n");	
		System.out.println("TITLE");
		System.out.println("\n"+metadata.get("title")+"\n");
		System.out.println("DESCRIPTION");
		System.out.println("\n"+metadata.get("description")+"\n");
		
		System.out.println("ALTITUDE");
		System.out.println("\n"+metadata.get("altitude")+"\n");
		System.out.println("COMMENTS");
		System.out.println("\n"+metadata.get("comments")+"\n");
		System.out.println("CONTRIBUTOR");
		System.out.println("\n"+metadata.get("contributor")+"\n");		 
		System.out.println("COVERAGE");
		System.out.println("\n"+metadata.get("coverage")+"\n");		 
	 
		System.out.println("CREATOR_TOOL");
		System.out.println("\n"+metadata.get("creator_tool")+"\n");		 
		 
		System.out.println("FORMAT");
		System.out.println("\n"+metadata.get("format")+"\n");		 
		System.out.println("IDENTIFIER");
		System.out.println("\n"+metadata.get("identifier")+"\n");		 
		System.out.println("KEYWORDS");
		System.out.println("\n"+metadata.get("keywords")+"\n");		 
		System.out.println("LANGUAGE");
		System.out.println("\n"+metadata.get("language")+"\n");			 
		System.out.println("LATITUDE");
		System.out.println("\n"+metadata.get("latitude")+"\n");			 
		System.out.println("LONGITUDE");
		System.out.println("\n"+metadata.get("longitude")+"\n");		 
		System.out.println("METADATA_DATE");
		System.out.println("\n"+metadata.get("metadata_date")+"\n");		 
	 
		System.out.println("MODIFIER");
		System.out.println("\n"+metadata.get("modifier")+"\n");			 
		System.out.println("PRINT_DATE");
		System.out.println("\n"+metadata.get("print_date")+"\n");			 
		System.out.println("PUBLISHER");
		System.out.println("\n"+metadata.get("publisher")+"\n");			 
		System.out.println("RATING");
		System.out.println("\n"+metadata.get("rating")+"\n");		 
		System.out.println("RELATION");
		System.out.println("\n"+metadata.get("relation")+"\n");			 
		System.out.println("RIGHTS");
		System.out.println("\n"+metadata.get("rights")+"\n");			 
		System.out.println("SOURCE");
		System.out.println("\n"+metadata.get("source")+"\n");					
	}
}
