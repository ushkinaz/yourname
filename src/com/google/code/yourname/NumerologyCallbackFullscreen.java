package com.google.code.yourname;

import android.widget.TextView;
import com.google.code.yourname.numerology.NumerologyResult;

import java.text.MessageFormat;

/**
 * @author Dmitry Sidorenko
 * @date May 18, 2010
 */
public class NumerologyCallbackFullscreen implements NumerologyCallback {
    private FullScreenActivity fullScreenActivity;

    public NumerologyCallbackFullscreen(FullScreenActivity fullScreenActivity) {
        this.fullScreenActivity = fullScreenActivity;
    }

    public void resultRetrieved(int resultNum, NumerologyResult result) {
        TextView resultRow = fullScreenActivity.getResultRow(resultNum);
        resultRow.setText(MessageFormat.format("{0}: {1} = {2} ^ {3}", result.getToken(), result.getSum(), result.getNumber(), result.getModality()));
    }
}
