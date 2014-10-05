package com.mgs.shopping

import spock.lang.Specification


class PriceManagerSpecification extends Specification {
    PriceManager testObj

    Price item1PriceMock = Mock ()
    Price item2PriceMock = Mock ()
    Price item3PriceMock = Mock ()
    Map<String,Price> prices = [
            item1: item1PriceMock,
            item2: item2PriceMock,
            item3: item3PriceMock
    ]
    PriceCalculator priceCalculatorMock = Mock ()

    def "setup" (){
        testObj = new PriceManager(prices, priceCalculatorMock)
    }

    def "should calculate the correct prices" (){
        given:
        priceCalculatorMock.totalsForPrice(item1PriceMock, 2) >> 10
        priceCalculatorMock.totalsForPrice(item2PriceMock, 1) >> 5
        priceCalculatorMock.totalsForPrice(item3PriceMock, 1) >> 5

        when:
        def result = testObj.calculatePrice(["item1", "item2", "item3", "item1"])

        then:
        result == 20
    }
}
