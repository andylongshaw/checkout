package com.bsl;

import java.util.HashMap;

public class PricingRules
{
    private final HashMap<Character, Integer> _priceList;
    private final DiscountCalculator _discountCalculator;

    public PricingRules(HashMap<Character, Integer> priceList, DiscountCalculator discountCalculator)
    {
        _priceList = priceList;
        _discountCalculator = discountCalculator;
    }

    Integer getPriceFor(char item)
    {
        return _priceList.get(new Character(item));
    }

    void addToPossibleDiscountItemsList(char item)
    {
        _discountCalculator.register(item);
    }

    public int calculateDiscount()
    {
        return _discountCalculator.calculateDiscount();
    }
}
