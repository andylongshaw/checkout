package com.bsl;

import java.util.HashMap;

public class DiscountCalculator
{
    private HashMap<Character, Integer> _itemCounts = new HashMap<Character, Integer>();
    private HashMap<Character, SpecialOffer> _specialOffers = new HashMap<Character, SpecialOffer>();

    public DiscountCalculator()
    {
        _specialOffers.put('A', new SpecialOffer(3, 20));
        _specialOffers.put('B', new SpecialOffer(2, 15));
    }

    int calculateDiscount()
    {
        int overallDiscount = 0;
        for (Character item : _itemCounts.keySet())
        {
            overallDiscount += calculateItemDiscount(item);
        }
        return overallDiscount;
    }

    private int calculateItemDiscount(char item)
    {
        return getItemCount(item) / getItemDiscountQuantity(item) * getItemDiscountAmount(item);
    }

    private int getItemDiscountAmount(char item)
    {
        if (itemHasADiscount(item))
            return _specialOffers.get(item).getAmount();
        return 0;
    }

    private boolean itemHasADiscount(char item)
    {
        return _specialOffers.containsKey(item);
    }

    private int getItemDiscountQuantity(char item)
    {
        if (itemHasADiscount(item))
            return _specialOffers.get(item).getQuantity();
        return 1;
    }

    private int getItemCount(char item)
    {
        createItemCountIfNoneExists(item);

        return _itemCounts.get(item);
    }

    public void register(char item)
    {
        createItemCountIfNoneExists(item);

        incrementItemCount(item);
    }

    private void incrementItemCount(char item)
    {
        int itemCount = _itemCounts.get(item);
        _itemCounts.put(item, ++itemCount);
    }

    private void createItemCountIfNoneExists(char item)
    {
        if (!_itemCounts.containsKey(item))
            _itemCounts.put(item, 0);
    }
}
