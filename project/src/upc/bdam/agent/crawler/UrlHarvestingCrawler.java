// UrlHarvestingCrawler.java
package upc.bdam.agent.crawler;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;

import websphinx.Crawler;
import websphinx.Link;
import websphinx.Page;

public class UrlHarvestingCrawler extends AgentCrawler {

	private static final long serialVersionUID = 9015164947202781853L;
	private static final String URL_PATTERN = "some_pattern";

	private PrintWriter output;

	protected void init() throws Exception {
		output = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
				"urls-from-public-site.txt")), true);
	}

	protected void destroy() {
		output.flush();
		output.close();
	}

	@Override
	protected void doVisit(Page page) {
		URL url = page.getURL();
		output.println(url.toString());
	}

	@Override
	public boolean shouldVisit(Link link) {
		URL linkUrl = link.getURL();
		return (linkUrl.toString().contains(URL_PATTERN));
	}

	/**
	 * This is how we are called.
	 *
	 * @param argv
	 *            command line args.
	 */
	public static void main(String[] argv) {
		UrlHarvestingCrawler crawler = new UrlHarvestingCrawler();
		try {
			crawler.init();
			crawler.setRoot(new Link(new URL("http://www.public-site.com/page")));
			crawler.setDomain(Crawler.SERVER); // reset this since we are
												// interested in siblings
			crawler.setMaxDepth(2); // only crawl 2 levels deep, default 5
			crawler.run();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			crawler.destroy();
		}
	}
}