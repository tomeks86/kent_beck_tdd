package edu.tseidler;

public class Franc extends Money {

    public Franc(int amount, String currency) {
        super(amount, currency);
    }

    @Override
    String currency() {
        return currency;
    }

}
