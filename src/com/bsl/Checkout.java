package com.bsl;

public class Checkout
{
    private final PricingRules _pricingRules;
    private int _currentUndiscountedTotal = 0;

    public Checkout(PricingRules pricingRules)
    {
        _pricingRules = pricingRules;
    }
    
    public void scan(char item)
    {
        _currentUndiscountedTotal += _pricingRules.getPriceFor(item);

        _pricingRules.addToPossibleDiscountItemsList(item);
    }

    public int calculateTotal()
    {
        return _currentUndiscountedTotal - _pricingRules.calculateDiscount();
    }

}
