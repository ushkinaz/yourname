package com.google.code.yourname.numerology;
/**
 * @author Dmitry Sidorenko
 * @date May 12, 2010
 */

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class NumerologyResultTest {
    private NumerologyResult num1 = new NumerologyResult("one");
    private NumerologyResult num2 = new NumerologyResult("two");

    @Test
    public void testEquals() {
        assertThat(num1, is(num1));
        assertThat(num1, not(num2));
    }
}