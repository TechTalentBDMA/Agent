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
	private String metadata;
	private String content;
	private String id=new String();

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
