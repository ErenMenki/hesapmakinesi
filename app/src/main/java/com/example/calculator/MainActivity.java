package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.sonucekran);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }

    public void sifir(View view) {
        updateText("0");
    }

    public void bir(View view) {
        updateText("1");
    }

    public void iki(View view) {
        updateText("2");
    }

    public void uc(View view) {
        updateText("3");
    }

    public void dort(View view) {
        updateText("4");
    }

    public void bes(View view) {
        updateText("5");
    }

    public void alti(View view) {
        updateText("6");
    }

    public void yedi(View view) {
        updateText("7");
    }

    public void sekiz(View view) {
        updateText("8");
    }

    public void dokuz(View view) { updateText("9"); }

    public void kare(View view) {
        updateText("^2");
    }

    public void kup(View view) {
        updateText("^3");
    }

    public void bolme(View view) {
        updateText("÷");
    }

    public void carpma(View view) {
        updateText("×");
    }

    public void toplama(View view) { updateText("+"); }

    public void cikartma(View view) {
        updateText("-");
    }

    public void esittir(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void temizle(View view) {
        display.setText("");
    }

    public void sil(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
}