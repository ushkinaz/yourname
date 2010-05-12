package com.google.code.yourname;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.method.BaseKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.code.yourname.numerology.NumerologyCalculator;
import com.google.code.yourname.numerology.NumerologyCalculatorRussian;
import com.google.code.yourname.numerology.NumerologyResult;

/**
 * Date: 11.05.2010
 * Created by Dmitry Sidorenko.
 */
public class MainActivity extends Activity {

    private NumerologyCalculator numerologyCalculator;
    private EditText mEditor;
    private TextView sum;
    private TextView number;
    private TextView modality;

    public MainActivity() {
        numerologyCalculator = new NumerologyCalculatorRussian();
    }

    /**
     * Called with the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate our UI from its XML layout description.
        setContentView(R.layout.main);

        // Find the text editor view inside the layout, because we
        // want to do various programmatic things with it.
        mEditor = (EditText) findViewById(R.id.editor);

        sum = (EditText) findViewById(R.id.sum);
        number = (EditText) findViewById(R.id.number);
        modality = (EditText) findViewById(R.id.modality);

        // Hook up button presses to the appropriate event handler.
        findViewById(R.id.calculate).setOnClickListener(mBackListener);

        mEditor.setText(getText(R.string.main_label));
        mEditor.setKeyListener(new BaseKeyListener() {
            public int getInputType() {
                return InputType.TYPE_TEXT_VARIATION_PERSON_NAME;
            }

            @Override
            public boolean onKeyDown(View view, Editable content, int keyCode, KeyEvent event) {
                nameUpdated();
                return super.onKeyDown(view, content, keyCode, event);
            }
        });
    }

    private void nameUpdated() {
        NumerologyResult result = numerologyCalculator.calculateResult(mEditor.getText().toString());
        sum.setText(Integer.toString(result.getSum()));
        number.setText(Integer.toString(result.getNumber()));
        modality.setText(Integer.toString(result.getModality()));
    }

    /**
     * A call-back for when the user presses the calculate button.
     */
    View.OnClickListener mBackListener = new View.OnClickListener() {
        public void onClick(View v) {

        }
    };

}
