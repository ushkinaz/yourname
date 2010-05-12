package com.google.code.yourname.numerology;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dsidorenko
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
        NumerologyResult numerologyResult;

        numerologyResult = new NumerologyResult("Андрей");
        numerologyResult.setSum(32);
        numerologyResult.setNumber(5);
        numerologyResult.setModality(4);

        numerologyResult = new NumerologyResult("Кириллович");
        numerologyResult.setSum(68);
        numerologyResult.setNumber(5);
        numerologyResult.setModality(8);

        numerologyResult = new NumerologyResult("Пупсиков");
        numerologyResult.setSum(65);
        numerologyResult.setNumber(2);
        numerologyResult.setModality(8);

        numerologyResult = new NumerologyResult("Цирконий");
        numerologyResult.setSum(88);
        numerologyResult.setNumber(7);
        numerologyResult.setModality(10);

        numerologyResult = new NumerologyResult("Иннокентьевич");
        numerologyResult.setSum(85);
        numerologyResult.setNumber(4);
        numerologyResult.setModality(10);

        numerologyResult = new NumerologyResult("Фицулкин");
        numerologyResult.setSum(87);
        numerologyResult.setNumber(6);
        numerologyResult.setModality(10);
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