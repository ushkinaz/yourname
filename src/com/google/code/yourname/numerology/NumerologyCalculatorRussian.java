package com.google.code.yourname.numerology;

import java.util.logging.Logger;

/**
 * @author Dmitry Sidorenko
 */
public class NumerologyCalculatorRussian implements NumerologyCalculator {
    private static final Logger LOGGER = Logger.getLogger(NumerologyCalculatorRussian.class.getName());

    private final static String alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    public NumerologyResult calculateResult(String token) {
        NumerologyResult result = new NumerologyResult(token);
        final int sum = calculateSum(token);
        result.setSum(sum);
        result.setModality(calculateModality(sum));
        result.setNumber(calculateNumber(sum));
        return result;
    }

    private int calculateNumber(int sum) {
        int number = 0;


        int remainder;
        while (sum > 0) {
            remainder = sum % 10;
            number += remainder;
            sum = (sum - remainder) / 10;
        }
        if (number > 9) {
            return calculateNumber(number);
        }
        return number;
    }

    private int calculateModality(int sum) {
        if (sum > 81) {
            sum = sum % 81;
        }
        int modality = 1 + (sum - sum % 9) / 9;
        if (sum % 9 == 0) {
            modality--;
        }
        return modality;
    }

    private int calculateSum(String token) {
        int sum = 0;
        for (char c : token.toUpperCase().toCharArray()) {
            sum += getCharValue(c);
        }
        return sum;
    }

    private int getCharValue(char c) {
        int val = alphabet.indexOf(c);
        val = val % 12 + 1;
        if (val == 12) {
            val = 22;
        }
        return val;
    }
}
