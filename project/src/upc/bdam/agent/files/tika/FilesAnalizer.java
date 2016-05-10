package upc.bdam.agent.files.tika;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import upc.bdam.agent.files.tika.beans.TikaFileBean;

public class FilesAnalizer {
	// final String directoryLinuxMac = ;
	public List<TikaFileBean> getFiles(String path, String mimeType) {
		// File file = new File(filepath);
		List<TikaFileBean> ficheros = new ArrayList<TikaFileBean>();
		ListFilesUtil listFilesUtil = new ListFilesUtil();
		ArrayList<String> fsc = listFilesUtil
				.listFilesAndFilesSubDirectories(path, mimeType);
		Iterator<String> itr = fsc.iterator();
		TikaFileBean tikaFile = new TikaFileBean();

		while (itr.hasNext()) {
			try {
				String filepath = itr.next();
				Parser parser = new AutoDetectParser();
				BodyContentHandler handler = new BodyContentHandler();
				Metadata metadata = new Metadata();
				ParseContext context = new ParseContext();
				FileInputStream inputstream;

				inputstream = new FileInputStream(filepath);

				parser.parse(inputstream, handler, metadata, context);
				System.out
						.println("====================================================");
				System.out
						.println("========== METADATOS   =============================");
				System.out
						.println("====================================================");
				System.out.println(metadata.toString());
				System.out
						.println("====================================================");
				System.out
						.println("========== CUERPO   =============================");
				System.out
						.println("====================================================");
				System.out.println(handler.toString());
				System.out
						.println("====================================================");
				tikaFile.setContent(handler.toString().getBytes());
				tikaFile.setMetadata(metadata.toString().getBytes());
				ficheros.add(tikaFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (TikaException e) {
				e.printStackTrace();
			}
		}
		return ficheros;
	}

}
