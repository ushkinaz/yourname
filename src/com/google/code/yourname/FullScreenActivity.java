package com.google.code.yourname;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.code.yourname.numerology.NumerologyCalculator;
import com.google.code.yourname.numerology.NumerologyCalculatorRussian;
import com.google.code.yourname.numerology.NumerologyResult;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 11.05.2010
 * Created by Dmitry Sidorenko.
 */
public class FullScreenActivity extends Activity {

    /**
     * TODO: Use IOC
     */
    private NumerologyCalculator numerologyCalculator;
    private EditText nameEditor;
    private TextView firstResult;
    private List<TextView> results;

    private LinearLayout resultsView;

    public FullScreenActivity() {
        numerologyCalculator = new NumerologyCalculatorRussian();
    }

    /**
     * Called with the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        results = new ArrayList<TextView>(5);

        // Inflate our UI from its XML layout description.
        setContentView(R.layout.main);

        // Find the text editor view inside the layout, because we
        // want to do various programmatic things with it.
        nameEditor = (EditText) findViewById(R.id.editor);

        firstResult = (TextView) findViewById(R.id.first_result);
        results.add(firstResult);

        resultsView = (LinearLayout) findViewById(R.id.results_view);

        addNewResultRow();
        addNewResultRow();
        addNewResultRow();

        // Hook up button presses to the appropriate event handler.
//        findViewById(R.id.calculate).setOnClickListener(calculateListener);

        nameEditor.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                updateNumerologyValues();
            }
        });
        updateNumerologyValues();
    }

    private void addNewResultRow() {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(firstResult.getLayoutParams()));
        textView.setTextSize(firstResult.getTextSize());
        textView.setGravity(firstResult.getGravity());

        results.add(textView);

        resultsView.addView(textView);
    }

    private void updateNumerologyValues() {
        cleanupResultRows();

        String name = nameEditor.getText().toString().trim();
        if (checkEasterEgg(name)){
            return;
        }

        String[] nameParts = name.split("\\s+");
        String namePart;

        for (int i= 0; i< nameParts.length; i++){
            namePart = nameParts[i].trim();
            if (namePart.length() == 0) {
                continue;
            }
            NumerologyResult result = numerologyCalculator.calculateResult(namePart);
            TextView resultRow = getResultRow(i);
            resultRow.setText(MessageFormat.format("{0}: {1} = {2} ^ {3}", result.getToken(), result.getSum(), result.getNumber(), result.getModality()));
        }
    }

    private boolean checkEasterEgg(String name) {
        if (name.toUpperCase().equals("ЛАРИСА ЮРЬЕВНА ПОПОВА")) {
            firstResult.setText("О заказчике - ни слова. Одни семерки и переходы на другой уровень.");
            return true;
        }
        if (name.toUpperCase().equals("АНТОН ПАВЛОВИЧ ЧЕХОВ")) {
            firstResult.setText("Русский писатель, драматург, по профессии врач.");
            return true;
        }
        return false;
    }

    private void cleanupResultRows() {
        for (TextView textView: results){
            textView.setText("");
        }
        firstResult.setText(R.string.help_empty_string);
    }

    private TextView getResultRow(int i) {
        TextView resultsRow;
        try {
            resultsRow = results.get(i);
        } catch (IndexOutOfBoundsException e) {
            //Add result row and try again
            addNewResultRow();
            return getResultRow(i);
        }
        return resultsRow;
    }

    /**
     * A call-back for when the user presses the calculate button.
     */
    private final View.OnClickListener calculateListener = new View.OnClickListener() {
        public void onClick(View v) {
            updateNumerologyValues();
        }
    };

}