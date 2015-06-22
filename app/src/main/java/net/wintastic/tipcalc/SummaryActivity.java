package net.wintastic.tipcalc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.NumberFormat;


public class SummaryActivity extends ActionBarActivity {

    private float billAmount  = 0f;
    private int   numPeople   = 0;
    private float tipAmount   = 0f;
    private float tipTotal    = 0f;
    private float tipToPay    = 0f;

    private final NumberFormat formatter = NumberFormat.getCurrencyInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent intent = getIntent();
        billAmount = intent.getFloatExtra("billAmount", 0f);
        numPeople  = intent.getIntExtra("numPeople", 0);
        tipAmount  = intent.getFloatExtra("tipPercent", 0f);

        tipTotal   = calculateTipTotal(billAmount, numPeople, tipAmount);
        if (numPeople == 0){tipToPay = 0f;} else{tipToPay   = tipTotal / numPeople;}

        TextView txtBillAmount = (TextView) findViewById(R.id.txtBillAmount);
        TextView txtNumPeople  = (TextView) findViewById(R.id.txtNumPayees);
        TextView txtTipAmount  = (TextView) findViewById(R.id.txtTipPercent);
        TextView txtTipToPay   = (TextView) findViewById(R.id.txtTipToPay);
        TextView txtTipTotal   = (TextView) findViewById(R.id.txtTipTotal);

        txtBillAmount.setText("" + formatter.format(billAmount));
        txtNumPeople.setText("" + numPeople);
        txtTipAmount.setText("" + tipAmount * 100 + "%");
        txtTipTotal.setText("" + formatter.format(tipTotal));
        txtTipToPay.setText("" + formatter.format(tipToPay));

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
