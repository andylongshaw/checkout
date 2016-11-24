package com.bsl;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestCheckout
{
    private static final HashMap<Character, Integer> _priceList = new HashMap<Character, Integer>();

    static {
        _priceList.put('A', 50);
        _priceList.put('B', 30);
        _priceList.put('C', 20);
        _priceList.put('D', 15);
    }

    @Test
    public void an_empty_cart_costs_nothing()
    {
        assertEquals(0, price(""));
    }

    @Test
    public void the_total_for_a_basket_of_mixed_individually_priced_items_is_calculated()
    {
        assertEquals(50, price("A"));
        assertEquals(80, price("AB"));
        assertEquals(115, price("CDBA"));
    }

    @Test
    public void multi_item_discounts_for_the_same_item_are_applied()
    {
        assertEquals(100, price("AA"));
        assertEquals(130, price("AAA"));
        assertEquals(180, price("AAAA"));
        assertEquals(230, price("AAAAA"));
        assertEquals(260, price("AAAAAA"));
    }

    @Test
    public void multi_item_discounts_for_mixed_items_are_applied()
    {
        assertEquals(160, price("AAAB"));
        assertEquals(175, price("AAABB"));
        assertEquals(190, price("AAABBD"));
        assertEquals(190, price("DABABA"));
    }

    @Test
    public void discounts_are_applied_as_you_add_items()
    {
        Checkout checkout = new Checkout(new PricingRules(_priceList, new DiscountCalculator()));
        assertEquals(0, checkout.calculateTotal());

        checkout.scan('A');
        assertEquals(50, checkout.calculateTotal());

        checkout.scan('B');
        assertEquals(80, checkout.calculateTotal());

        checkout.scan('A');
        assertEquals(130, checkout.calculateTotal());

        checkout.scan('A');
        assertEquals(160, checkout.calculateTotal());

        checkout.scan('B');
        assertEquals(175, checkout.calculateTotal());
    }

    private static int price(String basketContents)
    {
        Checkout checkout = new Checkout(new PricingRules(_priceList, new DiscountCalculator()));

        char[] items = basketContents.toCharArray();
        for (char item : items)
            checkout.scan(item);

        return checkout.calculateTotal();
    }

}
