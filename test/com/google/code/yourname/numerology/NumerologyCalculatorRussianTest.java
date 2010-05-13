package com.google.code.yourname.numerology;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Sidorenko
 */
public class NumerologyCalculatorRussianTest {
    private static Map<String, NumerologyResult> checkResults;

    private static NumerologyCalculatorRussian calculator;
    private static final String NAME1 = "Фицулкин";

    /**
     * Андрей	Кириллович	Пупсиков	32 = 5^4	68 = 5^8	65 = 2^8
     * Цирконий	Иннокентьевич	Фицулкин	88 = 7^10	85 = 4^10	87 = 6^10
     * Антон	Павлович	Чехов	19 = 1^3	28 = 1^4	25 = 7^3
     * Сигизмунд	Зефирович	Полонский	59 = 5^7	59 = 5^7	67 = 4^8
     */

    @BeforeClass
    public static void setUp() {
        calculator = new NumerologyCalculatorRussian();

        checkResults = new HashMap<String, NumerologyResult>();

        //Prepare results
        putResult("Андрей", 32, 5, 4);
        putResult("Кириллович", 68, 5, 8);
        putResult("Пупсиков", 65, 2, 8);
        putResult("Цирконий", 88, 7, 1);
        putResult("Иннокентьевич", 85, 4, 1);
        putResult("Фицулкин", 87, 6, 1);
        putResult("Мёрдок", 46, 1, 6);
    }

    private static void putResult(String token, int expectedSum, int expectedNumber, int expectedModality) {
        NumerologyResult numerologyResult;
        numerologyResult = new NumerologyResult(token);
        numerologyResult.setSum(expectedSum);
        numerologyResult.setNumber(expectedNumber);
        numerologyResult.setModality(expectedModality);
        checkResults.put(numerologyResult.getToken(), numerologyResult);
    }

    @Test
    public void testCalculateResult() {
        for (Map.Entry<String, NumerologyResult> entry : checkResults.entrySet()) {
            NumerologyResult result = calculator.calculateResult(entry.getKey());
            Assert.assertEquals(entry.getValue(), result);
        }
    }

    /**
     * Tests that results of mixed caps tokens are equal
     */
    @Test
    public void testCalculateResultEquals() {
        Assert.assertEquals(calculator.calculateResult(NAME1), calculator.calculateResult(NAME1.toUpperCase()));
        Assert.assertEquals(calculator.calculateResult(NAME1), calculator.calculateResult(NAME1.toLowerCase()));
    }

    /**
     * Tests that results of mixed caps tokens are equal
     */
    @Test
    public void testCalculateResultNotNull() {
        Assert.assertNotNull(calculator.calculateResult(NAME1));
        Assert.assertNotNull(calculator.calculateResult(""));
    }
}