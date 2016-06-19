package upc.bdam.agent.files.tika.beans;

import upc.bdam.agent.kafka.KafkaBean;

/**
 * Java Bean para almacenar el contenido de los ficheros procesados con tika
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class TikaFileBean extends KafkaBean{

	//DECLARACI�N DE M�TODOS GETTERS
	public String getMetadata() {
		return metadata;
	}
	public String getContent() {
		return content;
	}
	
	//DECLARACI�N DE M�TODOS SETTERS
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
