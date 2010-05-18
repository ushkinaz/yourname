package com.google.code.yourname;

import com.google.code.yourname.numerology.NumerologyCalculator;
import com.google.code.yourname.numerology.NumerologyCalculatorRussian;
import com.google.code.yourname.numerology.NumerologyResult;

public class NumerologyWorker {
    /**
     * TODO: Use IOC
     */
    private NumerologyCalculator numerologyCalculator;


    public NumerologyWorker() {
        numerologyCalculator = new NumerologyCalculatorRussian();
    }

    void updateNumerologyValues(String name, NumerologyCallback callback) {

        if (checkEasterEgg(name, callback)) {
            return;
        }

        String[] nameParts = name.split("\\s+");
        String namePart;

        for (int i = 0; i < nameParts.length; i++) {
            namePart = nameParts[i].trim();
            if (namePart.length() == 0) {
                continue;
            }
            NumerologyResult result = numerologyCalculator.calculateResult(namePart);
            callback.resultRetrieved(i, result);
        }
    }

    boolean checkEasterEgg(String name, NumerologyCallback callback) {
        //TODO: Refactor this
        if (name.toUpperCase().equals("ЛАРИСА ЮРЬЕВНА ПОПОВА")) {
            final NumerologyResult result = new NumerologyResult(name);
            callback.resultRetrieved(0, result);
            //"О заказчике - ни слова. Одни семерки и переходы на другой уровень."
            return true;
        } else if (name.toUpperCase().equals("АНТОН ПАВЛОВИЧ ЧЕХОВ")) {
            final NumerologyResult result = new NumerologyResult(name);
            //fullScreenActivity.getFirstResult().setText("Русский писатель, драматург, по профессии врач.");
            return true;
        }
        return false;
    }
}