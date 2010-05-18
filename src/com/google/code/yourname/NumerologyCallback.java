package com.google.code.yourname;

import com.google.code.yourname.numerology.NumerologyResult;

/**
 * @author Dmitry Sidorenko
 * @date May 18, 2010
 */
public interface NumerologyCallback {
    void resultRetrieved(int resultNum, NumerologyResult result);
}
