package com.example.beyong.viewtest.ViewModel;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.beyong.viewtest.R;

public class TypefaceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeface);

        Typeface customFont = Typeface.createFromAsset(this.getAssets(), "fonts/UglyQua-Italic.ttf");
        TextView view = (TextView) findViewById(R.id.typeface);
        EditText eT = (EditText) findViewById(R.id.typefaceet);
        view.setTypeface(customFont);
        eT.setTypeface(customFont);
    }
}
