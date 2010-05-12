package com.google.code.yourname.numerology;

/**
 * @author dsidorenko
 * @date May 12, 2010
 */
public class NumerologyCalculatorRussian implements NumerologyCalculator {

    /**
     * Value of first letter in alphabet
     */
    private final static int charAValue;

    static{
        charAValue = 'A';
    }


    public NumerologyResult calculateResult(String token) {
        int sum = calculateSum(token);
        return null;
    }

    private int calculateSum(CharSequence token) {
        return 0;
    }
}
