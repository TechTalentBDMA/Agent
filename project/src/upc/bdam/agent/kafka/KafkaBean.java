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
	public String metadata;
	public String content;



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
