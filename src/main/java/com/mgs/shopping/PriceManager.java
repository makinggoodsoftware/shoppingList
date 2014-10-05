package com.mgs.shopping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceManager {
	private final Map<String, Price> prices;
	private final PriceCalculator priceCalculator;

	public PriceManager(Map<String, Price> prices, PriceCalculator priceCalculator) {
		this.prices = prices;
		this.priceCalculator = priceCalculator;
	}


	public int calculatePrice (List<String> items){
		Map<String, Integer> itemsBoughtByType = initializeCounts();

		for (String item : items) {
			Integer currentCount = itemsBoughtByType.get(item);
			itemsBoughtByType.put(item, currentCount + 1);
		}
		return applyTotalsAndDiscounts(itemsBoughtByType);
	}

	private Map<String, Integer> initializeCounts() {
		Map<String, Integer> stringIntegerMap = new HashMap<String, Integer>();
		for (String key : prices.keySet()) {
			stringIntegerMap.put(key, 0);
		}
		return stringIntegerMap;
	}

	private int applyTotalsAndDiscounts(Map<String, Integer> itemsBoughtByType) {
		int total = 0;
		for (Map.Entry<String, Integer> itemsBoughtEntry : itemsBoughtByType.entrySet()) {
			Price price = prices.get(itemsBoughtEntry.getKey());
			Integer itemsBought = itemsBoughtEntry.getValue();

			total += priceCalculator.totalsForPrice(price, itemsBought);
		}
		return total;
	}
}
