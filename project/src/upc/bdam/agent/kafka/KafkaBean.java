package upc.bdam.agent.kafka;

/**
 * Bean de caracter general para el env�o de informaci�n a la cola kafka
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class KafkaBean {

	//declaraci�n de atributos	
	private String metadata;
	private String content;
	private String id=new String();

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
