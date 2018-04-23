package edu.tseidler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoneyTest {

    @Test
    public void testMultiplication() {
        Money five = Money.dolar(5);

        SoftAssert sa = new SoftAssert();
        sa.add(Money.dolar(10).equals(five.times(2)));
        sa.add(Money.dolar(15).equals(five.times(3)));
        sa.run();
    }

    @Test
    public void testEquality() {
        SoftAssert sa = new SoftAssert();
        sa.add(!Money.dolar(7).equals(Money.dolar(5)));
        sa.add(!Money.dolar(5).equals(Money.dolar(6)));
        sa.add(Money.franc(5).equals(Money.franc(5)));
        sa.add(!Money.franc(5).equals(Money.franc(6)));
        sa.add(!Money.franc(5).equals(Money.dolar(5)));
        sa.run();
    }

    @Test
    public void testFrancMultiplication() {
        Money five = Money.franc(5);

        SoftAssert sa = new SoftAssert();
        sa.add(Money.franc(10).equals(five.times(2)));
        sa.add(Money.franc(15).equals(five.times(3)));
        sa.run();
    }

    @Test
    public void testCurrency() {
        SoftAssert sa = new SoftAssert();
        sa.add("USD".equals(Money.dolar(1).currency()));
        sa.add("CHF".equals(Money.franc(1).currency()));
        sa.run();
    }

    @Test
    public void testSimpleAddition() {
        Money five = Money.dolar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dolar(10), reduced);
    }

    @Test
    public void testPlusReturnsSum() {
        Money five = Money.dolar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;

        SoftAssert sa = new SoftAssert();
        sa.add(five.equals(sum.augend));
        sa.add(five.equals(sum.addend));
        sa.run();
    }

    @Test
    public void testReduceSum() {
        Expression sum = new Sum(Money.dolar(3), Money.dolar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dolar(7), result);
    }

    @Test
    public void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dolar(1), "USD");
        assertEquals(Money.dolar(1), result);
    }

    @Test
    public void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(Money.dolar(1), result);
    }

    @Test
    public void testIdentityRate() {
        assertEquals(1, new Bank().rate("USD", "USD"));
    }

    @Test
    public void testMixedAddition() {
        Expression fiveBucks = Money.dolar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        assertEquals(Money.dolar(10), result);
    }

    @Test
    public void testSumPlusMoney() {
        Expression fiveBucks = Money.dolar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dolar(15), result);
    }

    @Test
    public void testSumTimes() {
        Expression fiveBucks = Money.dolar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dolar(20), result);
    }
}
