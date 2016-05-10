package upc.bdam.agent.files.tika;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ListFilesUtil {
	protected final Log log = LogFactory.getLog(getClass());
	/**
	 * List all files from a directory and its subdirectories
	 * 
	 * @param directoryName
	 *            to be listed
	 * @return
	 * @throws IOException 
	 */
	public ArrayList<String> listFilesAndFilesSubDirectories(String directoryName, String mymeType)  {
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		ArrayList<String> al = new ArrayList<String>();
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
				listFilesAndFilesSubDirectories(file.getAbsolutePath(), mymeType);
			}
		}
		System.out.println(al);
		return al;

	}
}