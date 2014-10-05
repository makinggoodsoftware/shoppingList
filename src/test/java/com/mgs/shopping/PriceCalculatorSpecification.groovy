package com.mgs.shopping

import spock.lang.Specification
import spock.lang.Unroll


class PriceCalculatorSpecification extends Specification {
    PriceCalculator testObj

    def "setup" (){
        testObj = new PriceCalculator()
    }

    @Unroll ("[#itemsBought] undiscounted item(s) at [#undiscountedPricePerUnit]p should amount to [#expectedTotal]")
    def "should calculate price when it doesnt have discounts" (){
        when:
        def result = testObj.totalsForPrice(new Price(undiscountedPricePerUnit, 0), itemsBought)

        then:
        result == expectedTotal

        where:
        undiscountedPricePerUnit    | itemsBought       | expectedTotal
        10                          | 0                 | 0
        10                          | 1                 | 10
        10                          | 3                 | 30
        0                           | 10                | 0
        1                           | 1                 | 1
    }

    @Unroll ("[#itemsBought] item(s) with one free every [#itemFreeEvery] at [#undiscountedPricePerUnit]p should amount to [#expectedTotal]")
    def "should calculate price when it has discounts" (){
        when:
        def result = testObj.totalsForPrice(new Price(undiscountedPricePerUnit, itemFreeEvery), itemsBought)

        then:
        result == expectedTotal

        where:
        undiscountedPricePerUnit    | itemFreeEvery     | itemsBought       | expectedTotal
        10                          | 2                 | 0                 | 0
        10                          | 2                 | 1                 | 10
        10                          | 2                 | 2                 | 10
        10                          | 2                 | 3                 | 20
        10                          | 3                 | 4                 | 30
        10                          | 3                 | 9                 | 60
        10                          | 3                 | 10                | 70
    }

}
