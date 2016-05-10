package upc.bdam.agent.files.tika.beans;

public class TikaFileBean{

	private byte[] metadata;
	private byte[] content;


	public byte[] getMetadata() {
		return metadata;
	}
	public byte[] getContent() {
		return content;
	}
	public void setMetadata(byte[] metadata) {
		this.metadata = metadata;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
}
