// MainActivity.java
// Calculates bills using 15% and custom percentage tips
package com.zerochass.tipcalculator;

import java.text.NumberFormat; // for currency formatting

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle; // for saving state information
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.SeekBar; // for changing custom tip percentage
import android.widget.SeekBar.OnSeekBarChangeListener; // SeekBar listener
import android.widget.TextView; // for displaying text

public class MainActivity extends AppCompatActivity {

    // Current and percent formatters
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0; // Bill amount entered by the user
    private double customPercent = 0.18; // Initial custom tip percentage
    private TextView amountDisplayTextView; // Shows the formatted bill amount
    private TextView percentCustomTextView; // Shows the custom tip percentage
    private TextView tip15TextView; // Shows the 15% tip
    private TextView total15TextView; // Shows the total with a 15% tip
    private TextView tipCustomTextView; // Shows the custom tip amount
    private TextView totalCustomTextView; // Shows the total with the custom tip amount

    // The onCreate method is called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Calls the onCreate of the superclass
        setContentView(R.layout.activity_main); // inflates the GUI for the activity

        // Attains the references for the TextViews that our MainActivity interacts with programmatically
        amountDisplayTextView = (TextView) findViewById(R.id.amountDisplayTextView);
        percentCustomTextView = (TextView) findViewById(R.id.percentCustomTextView);
        tip15TextView = (TextView) findViewById(R.id.tip15TextView);
        total15TextView = (TextView) findViewById(R.id.total15TextView);
        tipCustomTextView = (TextView) findViewById(R.id.tipCustomTextView);
        totalCustomTextView =(TextView) findViewById(R.id.totalCustomTextView);

        // Updates the GUI based on the billAmount and customPercent values
        amountDisplayTextView.setText(currencyFormat.format(billAmount));
        updateStandard(); // Updates the 15% tip TextViews
        updateCustom(); // Updates the custom tip TextViews

        // Sets the amountEditText's TextWatcher
        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        // Sets the customTipSeekBar's OnSeekBarChangeListener
        SeekBar customTipSeekBar = (SeekBar) findViewById(R.id.customTipSeekBar);
        customTipSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }

    /* Updates the 15% tip TextViews */
    private void updateStandard() {
        // Calculates the 15% tip and total
        double fifteenPercentTip = billAmount * 0.15;
        double fifteenPercentTotal = billAmount + fifteenPercentTip;

        // Displays the 15% tip and total formatted as currency
        tip15TextView.setText(currencyFormat.format(fifteenPercentTip));
        total15TextView.setText(currencyFormat.format(fifteenPercentTotal));
    }

    private void updateCustom() {
        // Shows the customPercent in the percentCustomTextView formatted as a percentage
        percentCustomTextView.setText(percentFormat.format(customPercent));

        // Calculates the custom tip and the total
        double customTip = billAmount + customPercent;
        double customTotal = billAmount + customTip;

        // Displays the custom tip and total formatted as currency
        tipCustomTextView.setText(currencyFormat.format(customTip));
        totalCustomTextView.setText(currencyFormat.format(customTotal));
    }
}
