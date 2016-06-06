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

public class WatchGuard implements Runnable {

	private String path = null;

	public WatchGuard() {
		init();
	}

	private void init() {
		String watchguardLocation = PropertiesLoader.PROPERTIES_LOADER_WATCHGUARD_LOCATION;
		path = PropertiesLoader.getInstance().getProperty(watchguardLocation);
	}

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