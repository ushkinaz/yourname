package com.google.code.yourname.numerology;

/**
 * An interface for calculating numerology results.
 * Should be stateless object.
 *
 * @author Dmitry Sidorenko
 */
public interface NumerologyCalculator {
    /**
     * Calculates result.
     *
     * @param token token to calculate  @return result of calculation
     */
    NumerologyResult calculateResult(String token);
}
