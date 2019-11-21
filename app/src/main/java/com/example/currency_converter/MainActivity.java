package com.example.currency_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {

    // 0 = None Selected, 1 = USD, 2 = EUR, 3 = GDP, 4 = INR, 5 = AUD
    // Int and boolean used for the first radio buttons, "From" buttons
    int fromInt = 0;
    boolean fromSelected = false;

    // Int and boolean used for the first radio buttons, "To" buttons
    int toInt = 0;
    boolean toSelected = false;

    // Function that checks the the first radio buttons
    public void onFromRadio (View view) {

        // Gets the boolean value from the radio button
        boolean checked = ((RadioButton) view).isChecked();
        fromSelected = true;

        // Switch-Case statements that check each radio button to see if it was selected
        switch (view.getId())   {
            case R.id.usdFrom:
                if (checked) {
                    fromInt = 1;
                }
                break;
            case R.id.eurFrom:
                if (checked)    {
                    fromInt = 2;
                }
                break;
            case R.id.gbpFrom:
                if (checked)    {
                    fromInt = 3;
                }
                break;
            case R.id.inrFrom:
                if (checked)    {
                    fromInt = 4;
                }
                break;
            case R.id.audFrom:
                if (checked)    {
                    fromInt = 5;
                }
                break;
        }
    }


    // Function that checks the the second radio buttons
    public void onToRadio (View view) {

        // Gets the boolean value from the radio button
        boolean checked = ((RadioButton) view).isChecked();
        toSelected = true;

        // Switch-Case statements that check each radio button to see if it was selected
        switch (view.getId())   {
            case R.id.usdTo:
                if (checked) {
                    toInt = 1;
                }
                break;
            case R.id.eurTo:
                if (checked)    {
                    toInt = 2;
                }
                break;
            case R.id.gbpTo:
                if (checked)    {
                    toInt = 3;
                }
                break;
            case R.id.inrTo:
                if (checked)    {
                    toInt = 4;
                }
                break;
            case R.id.audTo:
                if (checked)    {
                    toInt = 5;
                }
                break;
        }
    }


    // Helper function that will round the double to two decimal places
    private double roundBy2(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    // Function that will convert the amount of money given to the other currency
    private double doComputation (double numToConvert)   {
        // If both buttons are the same, then it will return that same amount
        if (fromInt == toInt)   {
            return numToConvert;
        }
        // From USD
        else if (fromInt == 1)   {
            // To EUR
            if (toInt == 2)         return roundBy2(numToConvert*0.90);
            // To GBP
            else if (toInt == 3)    return roundBy2(numToConvert*0.773493);
            // To INR
            else if (toInt == 4)    return roundBy2(numToConvert*71.7924);
            // To AUD
            else if (toInt == 5)    return roundBy2(numToConvert*1.47085);
            else                    return numToConvert;
        }
        // From EUR
        else if (fromInt == 2)  {
            // To USD
            if (toInt == 1)         return roundBy2(numToConvert*1.11);
            // To GBP
            else if (toInt == 3)    return roundBy2(numToConvert*0.856798);
            // To INR
            else if (toInt == 4)    return roundBy2(numToConvert*79.5283);
            // To AUD
            else if (toInt == 5)    return roundBy2(numToConvert*1.62946);
            else                    return numToConvert;
        }
        // From GBP
        else if (fromInt == 3)  {
            // To USD
            if (toInt == 1)         return roundBy2(numToConvert*1.29287);
            // To EUR
            else if (toInt == 2)    return roundBy2(numToConvert*1.16709);
            // To INR
            else if (toInt == 4)    return roundBy2(numToConvert*92.7983);
            // To AUD
            else if (toInt == 5)    return roundBy2(numToConvert*1.90159);
            else                    return numToConvert;
        }
        // From INR
        else if (fromInt == 4)  {
            // To USD
            if (toInt == 1)         return roundBy2(numToConvert*0.0139305);
            // To EUR
            else if (toInt == 2)    return roundBy2(numToConvert*0.0125737);
            // To GBP
            else if (toInt == 3)    return roundBy2(numToConvert*0.0107713);
            // To AUD
            else if (toInt == 5)    return roundBy2(numToConvert*0.0204842);
            else                    return numToConvert;
        }
        // From AUD
        else if (fromInt == 5)  {
            // To USD
            if (toInt == 1)         return roundBy2(numToConvert*0.679897);
            // To EUR
            else if (toInt == 2)    return roundBy2(numToConvert*0.613800);
            // To GBP
            else if (toInt == 3)    return roundBy2(numToConvert*0.525888);
            // To INR
            else if (toInt == 4)    return roundBy2(numToConvert*48.8249);
            else                    return numToConvert;
        }
        else    {
            return 0.0;
        }
    }


    // Function for the submit button to do the calculation
    public void onSubmit (View view)    {

        // Gets the number the user inputed
        EditText editText = (EditText)findViewById(R.id.textField);
        String text = editText.getText().toString();

        // Checks the string to make sure its valid
        if (text == null && text.isEmpty())    {
            Toast.makeText(this, "Nothing was inputed", Toast.LENGTH_SHORT).show();
        }
        // If the user did not select a "From" radio button, it will show them an error
        else if (!fromSelected)  {
            Toast.makeText(this, "No 'From' Currency was selected", Toast.LENGTH_SHORT).show();
        }
        // If the user did not select a "To" radio button, it will show them an error
        else if (!toSelected) {
            Toast.makeText(this, "No 'To' Currency was selected", Toast.LENGTH_SHORT).show();
        }
        else    {
            // Converts the string to a double
            double origVal = Double.parseDouble(text);

            // Computes the new value the user wants
            double newVal = doComputation(origVal);

            // Puts the new value into a string
            String answer = newVal+"";

            // Toasts the answer back to the user
            Toast.makeText(this, answer, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
