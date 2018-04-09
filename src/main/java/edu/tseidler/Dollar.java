package edu.tseidler;

public class Dollar extends Money {

    public Dollar(int amount, String currency) {
        super(amount, currency);
    }

    @Override
    String currency() {
        return currency;
    }

    public Dollar times(int multiplier) {
        return new Dollar(amount * multiplier, null);
    }

}
