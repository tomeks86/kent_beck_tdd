package edu.tseidler;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
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

}
