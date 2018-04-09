package edu.tseidler;

public interface Expression {
    Money reduce(Bank bank, String to);
}
