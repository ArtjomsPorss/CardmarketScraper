package app.parsers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import app.parsers.info_objects.SellerAndCardRowInfo;

public class SellerAndCardRowParser {
	
	public static SellerAndCardRowInfo extractSellerOfferDetails(Element offerRow) {
		
		SellerAndCardRowInfo sellerAndCardRowInfo = new SellerAndCardRowInfo();
		// extracting sell count
		sellerAndCardRowInfo.setSellCount(extractSellCount(offerRow));
		sellerAndCardRowInfo.setAvailableCardAmount(extractAvailableCardAmount(offerRow));
		sellerAndCardRowInfo.setCountry(extractCountry(offerRow));
		sellerAndCardRowInfo.setSellerName(extractSellerName(offerRow));
		sellerAndCardRowInfo.setSellerType(extractSellerType(offerRow));
		sellerAndCardRowInfo.setCardCondition(extractCardCondition(offerRow));
		sellerAndCardRowInfo.setCardLanguage(extractCardLanguage(offerRow));		
		sellerAndCardRowInfo.setSigned(extractCardIsSigned(offerRow));
		sellerAndCardRowInfo.setPlayset(extractCardIsPlayset(offerRow));
		sellerAndCardRowInfo.setPrice(extractPrice(offerRow));
		sellerAndCardRowInfo.setCardCopiesAmount(extractCardCopiesAmount(offerRow));
		
		
		return sellerAndCardRowInfo;
	}

	private static String extractSellCount(Element offerRow) {
		String sellCount = "";
		Elements sellCountElements = offerRow.select("[data-original-title*=Sales]");
		if (null != sellCountElements && !sellCountElements.isEmpty()) {
			String wholeString = sellCountElements.get(0).attr("data-original-title"); 
			sellCount = wholeString.substring(0, wholeString.indexOf(" Sales"));
			System.out.print("sellCount: ");
			System.out.println(sellCount);
		}
		return sellCount;
	}
	
	private static String extractAvailableCardAmount(Element offerRow) {
		String availableCardAmount = "";
		Elements availableCardAmountElements = offerRow.select("[data-original-title*=Sales]");
		if (null != availableCardAmountElements && !availableCardAmountElements.isEmpty()) {
			String wholeString = availableCardAmountElements.get(0).attr("data-original-title");
			availableCardAmount = wholeString.substring(wholeString.indexOf("| ") + 2, wholeString.indexOf(" Available"));
			System.out.print("availableCardAmount: ");
			System.out.println(availableCardAmount);
		}
		return availableCardAmount;
	}
	
	private static String extractCountry(Element offerRow) {
		String country = "";
		Elements countryElements = offerRow.select("[data-original-title^=Item location:]");
		if (null != countryElements && !countryElements.isEmpty()) {
			country = countryElements.get(0).attr("data-original-title").substring("Item location: ".length());
			System.out.print("Country: ");
			System.out.println(country);
		}
		return country;
	}

	
	private static String extractSellerName(Element offerRow) {
		String sellerName = "";
		Elements sellerNameElements = offerRow.select("[href^=/en/Magic/Users/]");
		if (null != sellerNameElements && !sellerNameElements.isEmpty()) {
			sellerName = sellerNameElements.get(0).text();
			System.out.print("SellerName: ");
			System.out.println(sellerName);
		}
		return sellerName;
	}
	
	
	private static String extractSellerType(Element offerRow) {
		String sellerType = "";
		
		Elements sellerTypeElements = offerRow.select("[data-original-title=Professional]");
		if(sellerTypeElements.isEmpty()) {
			sellerTypeElements = offerRow.select("[data-original-title=Powerseller]");
		}
		if (null != sellerTypeElements && !sellerTypeElements.isEmpty()) {
			sellerType = sellerTypeElements.get(0).attr("data-original-title").trim();
		}
		System.out.print("SellerType: ");
		System.out.println(sellerType);
		return sellerType;
	}


	private static String extractCardCondition(Element offerRow) {
		String cardCondition = "";
		
		Elements cardConditionElements = offerRow.select("[data-original-title=Poor]");
		if(null == cardConditionElements || cardConditionElements.isEmpty()) {
			cardConditionElements = offerRow.select("[data-original-title=Light Played]");
		}
		if(null == cardConditionElements || cardConditionElements.isEmpty()) {
			cardConditionElements = offerRow.select("[data-original-title=Good]");
		}
		if(null == cardConditionElements || cardConditionElements.isEmpty()) {
			cardConditionElements = offerRow.select("[data-original-title=Excellent]");
		}
		if(null == cardConditionElements || cardConditionElements.isEmpty()) {
			cardConditionElements = offerRow.select("[data-original-title=Near Mint]");
		}
		if(null == cardConditionElements || cardConditionElements.isEmpty()) {
			cardConditionElements = offerRow.select("[data-original-title=Mint]");
		}		
		
		if (null != cardConditionElements && !cardConditionElements.isEmpty()) {
			cardCondition = cardConditionElements.get(0).attr("data-original-title").trim();
			System.out.print("Card Condition: ");
			System.out.println(cardCondition);
		}
		return cardCondition;
	}
	
	
	private static String extractCardLanguage(Element offerRow) {
		String language = "";
		
		Elements cardConditionElements = offerRow.select("div.product-attributes > span");
		if(null != cardConditionElements && !cardConditionElements.isEmpty()) {
			language =  cardConditionElements
					.get(0).getElementsByTag("span").get(0).attr("data-original-title").trim();
			
		}		
		System.out.print("Card Language: ");
		System.out.println(language);

		return language;
	}


	private static boolean extractCardIsSigned(Element offerRow) {
		boolean signed = false;
		Elements cardSignedElements = offerRow.select("[data-original-title=Signed]");
		if (null != cardSignedElements && !cardSignedElements.isEmpty()) {
			signed = !cardSignedElements.get(0).attr("data-original-title=Signed").trim().isEmpty();
		}
		System.out.print("Signed: ");
		System.out.println(signed);
		return signed;
	}
	
	private static boolean extractCardIsPlayset(Element offerRow) {
		boolean isPlayset = false;
		Elements cardIsPlaysetElements = offerRow.select("[data-original-title=Playset]");
		if (null != cardIsPlaysetElements && !cardIsPlaysetElements.isEmpty()) {
			isPlayset = !cardIsPlaysetElements.get(0).attr("data-original-title=Playset").trim().isEmpty();
		}
		System.out.print("Playset: ");
		System.out.println(isPlayset);
		return isPlayset;
	}
	
	private static String extractPrice(Element offerRow) {
		String price = "";
		Elements priceElements = offerRow.select("[class^=price-container]");
		if (null != priceElements && !priceElements.isEmpty()) {
			String wholeString = priceElements.get(0).text();
			price = wholeString.substring(0, wholeString.indexOf(" €"));
			System.out.print("Price: ");
			System.out.println(price);
		}
		return price;
	}
	


	private static String extractCardCopiesAmount(Element offerRow) {
		String copiesAmount = "";
		Elements copiesAmountElements = offerRow.select("[class^=actions-container]");
		if (null != copiesAmountElements && !copiesAmountElements.isEmpty()) {
			String wholeString = copiesAmountElements.get(0).text();
			copiesAmount = wholeString.substring(0, wholeString.indexOf(" avail."));
			System.out.print("Copies: ");
			System.out.println(copiesAmount);
		}
		return copiesAmount;
	}
}
