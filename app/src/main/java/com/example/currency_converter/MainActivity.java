package com.example.currency_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {

    // Booleans used for the first radio buttons, "From" buttons
    boolean isUsdFrom = false;
    boolean isEurFrom = false;
    boolean isGbpFrom = false;
    boolean isInrFrom = false;
    boolean isAudFrom = false;

    // Booleans used for the second radio buttons, "To" buttons
    boolean isUsdTo = false;
    boolean isEurTo = false;
    boolean isGbpTo = false;
    boolean isInrTo = false;
    boolean isAudTo = false;

    // Function that checks the the first radio buttons
    public void onFromRadio (View view) {

        // Gets the boolean value from the radio button
        boolean checked = ((RadioButton) view).isChecked();

        // Switch-Case statements that check each radio button to see if it was selected
        switch (view.getId())   {
            case R.id.usdFrom:
                if (checked) {
                    isUsdFrom = true;
                    isEurFrom = false;
                }
                break;
            case R.id.eurFrom:
                if (checked)    {
                    isEurFrom = true;
                    isUsdFrom = false;
                }
                break;
        }
    }

    public void onToRadio (View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId())   {
            case R.id.usdTo:
                if (checked) {
                    isUsdTo = true;
                    isEurTo = false;
                }
                break;
            case R.id.eurTo:
                if (checked)    {
                    isEurTo = true;
                    isUsdTo = false;
                }
                break;
        }
    }

    private int checkBools ()   {
        if (isUsdFrom && isUsdTo)   {
            return 1;
        }
        else if (isEurFrom && isEurTo)    {
            return 1;
        }
        else if (isUsdFrom && isEurTo) {
            return 2;
        }
        else if (isEurFrom && isUsdTo) {
            return 3;
        }

        return 0;
    }

    private double roundBy2(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private double doComputation (int funType, double numToConvert)   {
        if (funType == 1)   {
            return numToConvert;
        }
        else if (funType == 2)   {
            return roundBy2(numToConvert*0.90);
        }
        else if (funType == 3)  {
            return roundBy2(numToConvert*1.11);
        }
        else    {
            return 0.0;
        }
    }


    public void onSubmit (View view)    {

        EditText editText = (EditText)findViewById(R.id.textField);

        String text = editText.getText().toString();

        if (text == null && text.isEmpty())    {
            Toast.makeText(this, "Nothing was inputed", Toast.LENGTH_SHORT).show();
        }
        else if (!isUsdFrom && !isEurFrom)  {
            Toast.makeText(this, "No 'From' Currency was selected", Toast.LENGTH_SHORT).show();
        }
        else if (!isUsdTo && !isEurTo) {
            Toast.makeText(this, "No 'To' Currency was selected", Toast.LENGTH_SHORT).show();
        }
        else    {
            // make function to check what bools are selected
            int decideInt = checkBools();

            double origVal = Double.parseDouble(text);

            double newVal = doComputation(decideInt, origVal);

            String answer = newVal+"";

            Toast.makeText(this, answer, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
