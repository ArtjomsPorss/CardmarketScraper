package app;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BasicWebCrawler {
	
	private int crawlCount = 0;
	private HashSet<String> links;
	
	public BasicWebCrawler() {
		links = new HashSet<String>();
	}
	
	public void getPageLinks(String url) {
		// check if URL was already crawled
		if (!links.contains(url)) {
			try {
				// if not, add to index
				if (links.add(url)) {
					System.out.println(url);
				}
				// fetch html
				Document document = Jsoup.connect(url).get();
				// parse to html to extract links to other urls
				Elements linksOnPage = document.select("a[href]");
				
				// for extracted url, pass links again
				for (Element page : linksOnPage) {
					//if crawled 25 times - exit
					if(++crawlCount == 25) {
						break;
					}
					getPageLinks(page.attr("abs:href"));
				}
			} catch (IOException e) {
				System.err.println(String.format("For %s: %s", url, e.getMessage()));
			}
		}
	}

	public static void main(String[] args) {
		new BasicWebCrawler().getPageLinks("https://www.cardmarket.com/en/Magic/Products/Singles/Magic+2013/Thragtusk");
	}
}
