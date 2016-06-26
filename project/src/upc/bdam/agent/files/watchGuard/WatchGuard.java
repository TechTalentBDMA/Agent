package upc.bdam.agent.files.watchGuard;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import upc.bdam.agent.config.PropertiesLoader;

/**
 * Clase de utilidad para vigilar los cambios producidos en un directorio específico
 * 
 * @author Grupo 9: 
 *           - Antolín Barrena Rico
 *           - Carles Castillejo
 *           - Raffaele Ghermandi
 *           - David Pérez Rodríguez
 *
 */
public class WatchGuard implements Runnable {
	//path a vigilar
	private String path = null;

	/**
	 * constructor de clase
	 */
	public WatchGuard() {
		init();
	}

	/**
	 * Inicialización de las propiedades de la clase
	 */
	private void init() {
		System.out.print("Se intenta cargar la propiedad: "+PropertiesLoader.PROPERTIES_LOADER_WATCHGUARD_LOCATION);
		String watchguardLocation = PropertiesLoader.PROPERTIES_LOADER_WATCHGUARD_LOCATION;
		path = PropertiesLoader.getInstance().getProperty(watchguardLocation);
		System.out.print("Se vigilará la ubicación: "+path);		
	}

	/**
	 * Ejecución de las acciones asociadas al hilo
	 */
	public void run() {

		try {

			System.out.println("WatchService in " + path);

			// Obtenemos el directorio
			Path directoryToWatch = Paths.get(path);
			if (directoryToWatch == null) {
				throw new UnsupportedOperationException("Directory not found");
			}

			// Solicitamos el servicio WatchService
			WatchService watchService = directoryToWatch.getFileSystem().newWatchService();

			// Registramos los eventos que queremos monitorear
			directoryToWatch.register(watchService, new WatchEvent.Kind[] { ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY });

			System.out.println("Started WatchService in " + path);

			// Esperamos que algo suceda con el directorio
			WatchKey key = watchService.take();

			// Algo ocurrio en el directorio para los eventos registrados
			while (key != null) {
				for (WatchEvent event : key.pollEvents()) {
					String eventKind = event.kind().toString();
					String file = event.context().toString();
					System.out.println("Event : " + eventKind + " in File " + file);
				}

				// Volvemos a escuchar. Lo mantenemos en un loop para escuchar
				// indefinidamente.
				key.reset();
				key = watchService.take();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}