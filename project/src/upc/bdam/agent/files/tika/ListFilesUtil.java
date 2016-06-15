package upc.bdam.agent.files.tika;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase de utilidad para el procesamiento de ficheros 
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class ListFilesUtil {
	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * Lista todos los ficheros de un directorio y sus subdirectorios
	 * 
	 * @param directoryName
	 * @param mymeType
	 * @return
	 * @throws IOException 
	 */
	public ArrayList<String> listFilesAndFilesSubDirectories(String directoryName, String mymeType)  {
		//se obtiene el directorio
		File directory = new File(directoryName);

		//se recorren todos los subdirectorios
		File[] fList = directory.listFiles();
		ArrayList<String> al = new ArrayList<String>();
		
		//se recorren los ficheros de cada subdirectorio
		for (File file : fList) {
			if (file.isFile()) {

				try {
					if (Files.probeContentType(file.toPath()).equalsIgnoreCase(mymeType)) {
						al.add(file.getAbsolutePath());
					}
				} catch (IOException e) {
					log.error("Error comprobando el tipo de fichero:" + file.getName(), e);
				}

			} else if (file.isDirectory()) {
				al.addAll(listFilesAndFilesSubDirectories(file.getAbsolutePath(), mymeType));
			}
		}
		System.out.println(al);
		return al;
	}
}