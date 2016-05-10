// SiteDownloadingCrawler.java
package upc.bdam.agent.crawler;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import upc.bdam.agent.config.PropertiesLoader;
import websphinx.Page;

public class SiteDownloadingCrawler extends AgentCrawler {

	private static final long serialVersionUID = 64989986095789110L;

	private String targetDir;

	private SiteDownloadingCrawler() {
	}

	public SiteDownloadingCrawler(boolean borrado) {
		String downloadPath = PropertiesLoader.PROPERTIES_LOADER_MOZILLA_CRAWLER_DOWNLOAD_PATH;
		targetDir = PropertiesLoader.getInstance().getProperty(downloadPath);

		if (borrado)
			init();
	}

	private void init() {

		File targetDirFile = new File(targetDir);
		try {
			if (targetDirFile.exists()) {

				FileUtils.forceDelete(targetDirFile);
			}
			FileUtils.forceMkdir(targetDirFile);
		} catch (IOException ex) {

		}
	}

	@Override
	protected void doVisit(Page page) {
		URL url = page.getURL();
		try {
			String path = url.getHost().replace('.', '-') + "/" + url.getPath().replaceFirst("/", "");
			if (StringUtils.isNotEmpty(path)) {
				String targetPathName = FilenameUtils.concat(targetDir, path);
				File targetPath = new File(FilenameUtils.getPath(targetPathName));
				FileUtils.forceMkdir(targetPath);
				File targetFile2 = new File(targetPathName + "(1)");
				FileUtils.writeByteArrayToFile(targetFile2, page.getContentBytes());
			}
		} catch (Exception e) {
			log.error("Could not download url:" + url.toString(), e);
		}
	}
}