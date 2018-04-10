package edu.tseidler;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MoneyTest {

    @Test
    public void testMultiplication() {
        Money five = Money.dolar(5);
        assertEquals(Money.dolar(10), five.times(2));
        assertEquals(Money.dolar(15), five.times(3));
    }

    @Test
    public void testEquality() {
        assertTrue(Money.dolar(5).equals(Money.dolar(5)));
        assertFalse(Money.dolar(5).equals(Money.dolar(6)));
        assertTrue(Money.franc(5).equals(Money.franc(5)));
        assertFalse(Money.franc(5).equals(Money.franc(6)));
        assertFalse(Money.franc(5).equals(Money.dolar(5)));
    }

    @Test
    public void testFrancMultiplication() {
        Money five = Money.franc(5);
        assertEquals(Money.franc(10), five.times(2));
        assertEquals(Money.franc(15), five.times(3));
    }

    @Test
    public void testCurrency() {
        assertEquals("USD", Money.dolar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
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
        assertEquals(five, sum.augend);
        assertEquals(five, sum.addend);
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
