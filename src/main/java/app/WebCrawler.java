package app;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import app.parsers.SellerAndCardRowParser;
import app.parsers.info_objects.SellerAndCardRowInfo;
import app.web.SellerAndCardPageFilter;

public class WebCrawler {

	private int crawlCount = 0;
	private HashSet<String> links;

	public WebCrawler() {
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
					// if crawled 25 times - exit
					if (++crawlCount == 25) {
						break;
					}
					getPageLinks(page.attr("abs:href"));
				}
			} catch (IOException e) {
				System.err.println(String.format("For %s: %s", url, e.getMessage()));
			}
		}
	}

	public void getSellerOffers(String url) {
		// check if URL was already crawled
		if (!links.contains(url)) {
			try {
				// if not, add to index
				if (links.add(url)) {
//					System.out.println(url);
				}
				// fetch html
				Document document = Jsoup.connect(url).get();
				// parse to html to extract links to other urls
//				Elements linksOnPage = document.select("#articleRow");
				Elements offersOnPage = document.select("[id^=articleRow]");

				// for extracted url, pass links again
				for (Element offerRow : offersOnPage) {
					if(++crawlCount > 60) {
//						break;
					}
					SellerAndCardRowInfo sellerAndCardRowInfo = SellerAndCardRowParser.extractSellerOfferDetails(offerRow);
//					System.out.println("OuterHTML================================================================================");
//					System.out.println(page.outerHtml());
//					System.out.println();
//					System.out.println();
//					System.out.println();
					System.out.println();
				}
				System.out.println("COUNT: " + crawlCount);
				
			} catch (IOException e) {
				System.err.println(String.format("For %s: %s", url, e.getMessage()));
			}
		}
	}
	
	public void getSellerOffers(String url, SellerAndCardPageFilter filter) {
		
	}

	private void postSellerOffers(String url) {
		// check if URL was already crawled
		if (!links.contains(url)) {
			try {
				// if not, add to index
				if (links.add(url)) {
				}
				// fetch html
				Response document = Jsoup.connect(url).method(Connection.Method.GET).execute();
				
				Map<String, String> data = new HashMap<String, String>();
				data.put("language[1]", "1");
				data.put("minCondition", "7");
				data.put("extra[isFoil]", "0");
				data.put("extra[isSigned]", "0");
				data.put("extra[isPlayset]", "0");
				data.put("extra[isAltered]", "0");
				data.put("amount", "");
				data.put("apply", "Filter");
				
				Response res = Jsoup
					    .connect(url)
					    .data(data)
					    .cookies(document.cookies())
					    .method(Method.POST)
					    .execute();
				
				Elements offersOnPage = res.parse().select("[id^=articleRow]");
				
				// for extracted url, pass links again
				for (Element offerRow : offersOnPage) {
					if(++crawlCount > 10000) {
//						break;
					}
					SellerAndCardRowInfo sellerAndCardRowInfo = SellerAndCardRowParser.extractSellerOfferDetails(offerRow);
//					System.out.println("OuterHTML================================================================================");
//					System.out.println(page.outerHtml());
//					System.out.println();
//					System.out.println();
//					System.out.println();
					System.out.println();
				}
				System.out.println("COUNT: " + crawlCount);
				
			} catch (IOException e) {
				System.err.println(String.format("For %s: %s", url, e.getMessage()));
			}
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
//		new WebCrawler().getSellerOffers("https://www.cardmarket.com/en/Magic/Products/Singles/Magic+2013/Thragtusk");
		new WebCrawler().postSellerOffers("https://www.cardmarket.com/en/Magic/Products/Singles/Magic+2013/Thragtusk");
//		new WebCrawler().postSellerOffers("https://www.cardmarket.com/en/Magic/Products/Singles/Magic+2013/Thragtusk"
//				+"?__cmtkn=f3cd00850b1ddd1226ff0b5ba77d08ee902f15fb2513c4f13dadf7c5acd18698&language%5B1%5D=1&minCondition=7&extra%5BisFoil%5D=0&extra%5BisSigned%5D=0&extra%5BisPlayset%5D=0&extra%5BisAltered%5D=0&amount=&apply=Filter");
//		new WebCrawler().getPageLinks("https://www.cardmarket.com/en/Magic/Products/Singles/Magic+2013/Thragtusk");
	}
}
