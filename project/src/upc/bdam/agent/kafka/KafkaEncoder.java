package upc.bdam.agent.kafka;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;

public class KafkaEncoder{
	
	ObjectMapper mapper = new ObjectMapper(new SmileFactory());
	public byte[] serialize(KafkaBean bean) {
		byte[]result=null;
	    try {
	        result= mapper.writeValueAsBytes(bean);
	    }
	    catch(JsonProcessingException e) {
	        e.printStackTrace();;
	    }

	    return result;
	}
	
	
	public KafkaBean deserialize(byte[] bytes) {
		KafkaBean aux=null;
	    try {
	        aux= mapper.readValue(bytes, KafkaBean.class);
	    }
	    catch(IOException e) {
	        e.printStackTrace();
	    }
	    return aux;
	}
}
