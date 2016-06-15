package upc.bdam.agent.files.tika.beans;

/**
 * Java Bean para almacenar el contenido de los ficheros procesados con tika
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class TikaFileBean{

	//declaración de atributos
	private byte[] metadata;
	private byte[] content;

	//DECLARACIÓN DE MÉTODOS GETTERS
	public byte[] getMetadata() {
		return metadata;
	}
	public byte[] getContent() {
		return content;
	}
	
	//DECLARACIÓN DE MÉTODOS SETTERS
	public void setMetadata(byte[] metadata) {
		this.metadata = metadata;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
}
