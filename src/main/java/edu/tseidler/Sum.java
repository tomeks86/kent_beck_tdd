package edu.tseidler;

public class Sum implements Expression {
    Money augend;
    Money addend;

    public Sum(Money augend, Money addend) {
        this.augend = augend;
        this.addend = addend;
    }

    @Override
    public Money reduce(String to) {
        int amount = augend.amount + addend.amount;
        return new Money(amount, to);
    }
}
