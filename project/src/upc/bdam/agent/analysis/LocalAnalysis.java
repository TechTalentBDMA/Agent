package upc.bdam.agent.analysis;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import upc.bdam.agent.browser.BrowserAnalizer;
import upc.bdam.agent.browser.mozilla.MozillaBrowserAnalizer;
import upc.bdam.agent.config.PropertiesLoader;
import upc.bdam.agent.files.tika.FilesAnalizer;
import upc.bdam.agent.files.tika.beans.IMimeTypes;
import upc.bdam.agent.files.tika.beans.TikaFileBean;

public class LocalAnalysis {

	FilesAnalizer filesAnalizer = new FilesAnalizer();
	String tikaLocation = PropertiesLoader.getInstance()
			.getProperty(PropertiesLoader.PROPERTIES_LOADER_TIKA_LOCATION);
	
	public List<URL> browserAnalysis() {
		return getMozillaURIS();
	}

	private List<URL> getMozillaURIS() {
		BrowserAnalizer browserAnalizer = new MozillaBrowserAnalizer();
		return browserAnalizer.getPlaces();
	}

	public List<TikaFileBean> getPdfFiles() {
		List<TikaFileBean> pdfs = new ArrayList<TikaFileBean>();
		pdfs = filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_PDF_TYPE);
		return pdfs;
	}
	
	public List<TikaFileBean> getMp3Files() {
		List<TikaFileBean> mp3 = new ArrayList<TikaFileBean>();
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_1_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_2_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_3_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_4_TYPE));
		
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_5_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_7_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_8_TYPE));
		
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_9_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_10_TYPE));
		mp3.addAll(filesAnalizer.getFiles(tikaLocation, IMimeTypes.MIME_MP3_11_TYPE));
		
		return mp3;
	}
}
