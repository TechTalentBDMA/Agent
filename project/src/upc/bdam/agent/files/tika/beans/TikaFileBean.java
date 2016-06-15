package upc.bdam.agent.files.tika.beans;

/**
 * Java Bean para almacenar el contenido de los ficheros procesados con tika
 * @author Grupo 9: 
 *           - Antol�n Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David P�rez Rodr�guez
 *
 */
public class TikaFileBean{

	//declaraci�n de atributos
	private byte[] metadata;
	private byte[] content;

	//DECLARACI�N DE M�TODOS GETTERS
	public byte[] getMetadata() {
		return metadata;
	}
	public byte[] getContent() {
		return content;
	}
	
	//DECLARACI�N DE M�TODOS SETTERS
	public void setMetadata(byte[] metadata) {
		this.metadata = metadata;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
}
