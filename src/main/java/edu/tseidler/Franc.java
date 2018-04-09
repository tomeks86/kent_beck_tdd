package edu.tseidler;

public class Franc extends Money {

    public Franc(int amount, String currency) {
        super(amount, currency);
    }

    @Override
    String currency() {
        return currency;
    }

    public Franc times(int multiplier) {
        return new Franc(amount * multiplier, null);
    }

}
