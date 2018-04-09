package edu.tseidler;

abstract public class Money {
    protected int amount;

    abstract Money times(int multiplier);

    static Dollar dolar(int amount) {
        return new Dollar(amount);
    }

    static Franc franc(int amount) {
        return new Franc(amount);
    }

    @Override
    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && getClass().equals(money.getClass());
    }

}
