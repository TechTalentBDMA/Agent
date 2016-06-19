package upc.bdam.agent.files.tika.beans;

import upc.bdam.agent.kafka.KafkaBean;

/**
 * Java Bean para almacenar el contenido de los ficheros procesados con tika
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class TikaFileBean extends KafkaBean{

	//DECLARACIÓN DE MÉTODOS GETTERS
	public String getMetadata() {
		return metadata;
	}
	public String getContent() {
		return content;
	}
	
	//DECLARACIÓN DE MÉTODOS SETTERS
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
