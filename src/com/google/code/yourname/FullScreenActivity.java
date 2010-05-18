package com.google.code.yourname;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 11.05.2010
 * Created by Dmitry Sidorenko.
 */
public class FullScreenActivity extends Activity {

    private EditText nameEditor;
    private TextView firstResult;
    private List<TextView> results;

    private LinearLayout resultsView;
    private final NumerologyWorker numerologyWorker = new NumerologyWorker();

    public FullScreenActivity() {
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
                cleanupResultRows();
                numerologyWorker.updateNumerologyValues(nameEditor.getText().toString().trim(), new NumerologyCallbackFullscreen(FullScreenActivity.this));
            }
        });
        numerologyWorker.updateNumerologyValues(nameEditor.getText().toString().trim(), null);
    }

    private void addNewResultRow() {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(firstResult.getLayoutParams()));
        textView.setTextSize(firstResult.getTextSize());
        textView.setGravity(firstResult.getGravity());

        results.add(textView);

        resultsView.addView(textView);
    }


    private void cleanupResultRows() {
        for (TextView textView: results){
            textView.setText("");
        }
        firstResult.setText(R.string.help_empty_string);
    }

    TextView getResultRow(int i) {
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
}
