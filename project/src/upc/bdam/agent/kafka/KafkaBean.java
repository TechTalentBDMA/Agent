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
	public static final byte KAFKA_VIDEO=1;
	public static final byte KAFKA_AUDIO=2;
	public static final byte KAFKA_TEXTO=3;
	public static final byte KAFKA_WEB=4;

	//declaración de atributos	
	private String metadata;
	private String content;
	private String id=new String();
	private byte type=0;
	private String mimeType=new String();

	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
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
