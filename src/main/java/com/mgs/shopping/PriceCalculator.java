package com.mgs.shopping;

public class PriceCalculator {
	public int totalsForPrice(Price price, Integer itemsBought) {
		if (itemsBought == 0) {
			return 0;
		}

		if (!price.hasDiscount()) {
			return itemsBought * price.getUndiscountedPrice();
		}

		int freeItems = itemsBought / price.getOneFreeEvery();
		return (itemsBought - freeItems) * price.getUndiscountedPrice();
	}
}