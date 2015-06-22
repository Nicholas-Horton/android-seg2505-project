package net.wintastic.tipcalc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;


public class SummaryActivity extends ActionBarActivity {

    private float billAmount      = 0f;
    private int   numPeople       = 0;
    private float tipAmount       = 0f;
    private float grandTotal      = 0f;
    private float tipTotal        = 0f;
    private float tipToPay        = 0f;
    private float amountPerPerson = 0f;

    private String currencyString = "$";
    private NumberFormat         formatter      = NumberFormat.getCurrencyInstance();
    private DecimalFormatSymbols currencyFormat = new DecimalFormatSymbols();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent intent = getIntent();

        //Currency formatting
        currencyString = intent.getStringExtra("currencyString");
        currencyFormat.setCurrencySymbol(currencyString);
        currencyFormat.setDecimalSeparator('.');
        ((DecimalFormat) formatter).setDecimalFormatSymbols(currencyFormat);

        billAmount = intent.getFloatExtra("billAmount", 0f);
        numPeople  = intent.getIntExtra("numPeople", 0);
        tipAmount  = intent.getFloatExtra("tipPercent", 0f);
        tipTotal   = calculateTipTotal(billAmount, numPeople, tipAmount);
        grandTotal = billAmount + tipTotal;
        float amountToPay = 0f;
        if (numPeople == 0){tipToPay = 0f; amountToPay = billAmount;}
        else{tipToPay = tipTotal / numPeople; amountToPay = billAmount / numPeople;}

        amountPerPerson = amountToPay + tipToPay;

        TextView txtBillAmount      = (TextView) findViewById(R.id.txtBillAmount);
        TextView txtNumPeople       = (TextView) findViewById(R.id.txtNumPayees);
        TextView txtTipAmount       = (TextView) findViewById(R.id.txtTipPercent);
        TextView txtGrandTotal      = (TextView) findViewById(R.id.txtGrandTotal);
        TextView txtTipToPay        = (TextView) findViewById(R.id.txtTipToPay);
        TextView txtTipTotal        = (TextView) findViewById(R.id.txtTipTotal);
        TextView txtAmountPerPerson = (TextView) findViewById(R.id.txtAmountToPay);

        txtBillAmount.setText(      "" + formatter.format(billAmount));
        txtNumPeople.setText(       "" + numPeople);
        txtTipAmount.setText(       "" + Math.round(tipAmount * 100) + "%"); // tip amount does not display correctly without rounding
        txtGrandTotal.setText(      "" + formatter.format(grandTotal));
        txtTipTotal.setText(        "" + formatter.format(tipTotal));
        txtTipToPay.setText(        "" + formatter.format(tipToPay));
        txtAmountPerPerson.setText( "" + formatter.format(amountPerPerson));

    }

    private float calculateTipTotal(float billAmount, int numPeople, float tipAmount){
        return billAmount * tipAmount;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent("net.wintastic.tipcalc.SettingsActivity");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
