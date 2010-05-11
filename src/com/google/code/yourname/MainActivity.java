package com.google.code.yourname;

import android.app.Activity;
import android.os.Bundle;

/**
 * Date: 11.05.2010
 * Created by Dmitry Sidorenko.
 */
public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
