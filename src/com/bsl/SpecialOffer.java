package com.bsl;

public class SpecialOffer
{
    private int _quantity;
    private int _amount;

    public SpecialOffer(int quantity, int amount)
    {
        _quantity = quantity;
        _amount = amount;
    }

    public int getQuantity()
    {
        return _quantity;
    }

    public int getAmount()
    {
        return _amount;
    }
}
