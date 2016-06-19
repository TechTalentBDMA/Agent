package upc.bdam.agent.kafka;

/**
 * Bean de caracter general para el envío de información a la cola kafka
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class KafkaBean {

	//declaración de atributos
	public String metadata;
	public String content;



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
