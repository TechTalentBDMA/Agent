package upc.bdam.agent.kafka;

import upc.bdam.agent.config.PropertiesLoader;

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
	
	private static final String USER_DATA_NICK_NAME="nickName";
	private static final String USER_DATA_NICK="name";
	private static final String USER_DATA_SURNAME="name";
	private static final String USER_DATA_BORN="born";	
	private static final String USER_DATA_SEX="sex";
	private static final String USER_DATA_PROFESSION="profession";
	private static final String USER_DATA_HOBBY="hobby";
	private static final String USER_DATA_INTERESTED_IN="interestedIn";
	//declaración de atributos del usuario
	private String nickName;
	private String name;
	private String surname;
	private String born;
	private String sex;
	private String profession;
	private String hobby;
	private String interestedIn;

	//declaración de atributos	
	private String metadata;
	private String content;
	private String id=new String();
	private byte type=0;
	private String mimeType=new String();
	
	/**
	 * En el constructor del bean, y a falta de interfaz gráfica que permita pedir 
	 * los datos al usuario, los cargaremos de un fichero de configuración para la demo
	 */
	public KafkaBean(){
		PropertiesLoader properties=PropertiesLoader.getInstance();
		nickName=properties.getProperty(USER_DATA_NICK_NAME);
		name=properties.getProperty(USER_DATA_NICK);
		surname=properties.getProperty(USER_DATA_SURNAME);
		born=properties.getProperty(USER_DATA_BORN);
		sex=properties.getProperty(USER_DATA_SEX);
		profession=properties.getProperty(USER_DATA_PROFESSION);
		hobby=properties.getProperty(USER_DATA_HOBBY);
		interestedIn=properties.getProperty(USER_DATA_INTERESTED_IN);		
	}
	
	public String getNickName() {
		return nickName;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getBorn() {
		return born;
	}
	public String getSex() {
		return sex;
	}
	public String getProfession() {
		return profession;
	}
	public String getHobby() {
		return hobby;
	}
	public String getInterestedIn() {
		return interestedIn;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setBorn(String born) {
		this.born = born;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public void setInterestedIn(String interestedIn) {
		this.interestedIn = interestedIn;
	}

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
