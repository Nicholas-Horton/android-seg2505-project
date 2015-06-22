package net.wintastic.tipcalc;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButtonClickListener();
        tipMethodsButtonClickListener();

        NumberPicker np = (NumberPicker) findViewById(R.id.tip_picker);
        np.setMaxValue(30);
        np.setMinValue(1);
        np.setWrapSelectorWheel(false);
    }

    public void submitButtonClickListener(){
        Button btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }

    public void tipMethodsButtonClickListener(){
        Button btnManual = (Button)findViewById(R.id.buttonManual);
        Button btnSuggested = (Button)findViewById(R.id.buttonSuggested);
        btnManual.setOnClickListener(this);
        btnSuggested.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText etxtBillAmount = (EditText) findViewById(R.id.billAmount);
        EditText etxtNumberOfPeople = (EditText) findViewById(R.id.numberPeople);

        String billAmount = etxtBillAmount.getText().toString();
        String numPeople  = etxtNumberOfPeople.getText().toString();

        NumberPicker np = (NumberPicker) findViewById(R.id.tip_picker);

        float tipPercent = (float)np.getValue() / 100;


        LinearLayout ratingLayout=(LinearLayout)this.findViewById(R.id.ratingLayout);
        LinearLayout tipPercentLayout=(LinearLayout)this.findViewById(R.id.tipPercentLayout);
        switch(v.getId()){
            case R.id.btnSubmit:
                b_submitClick(Float.parseFloat(billAmount), Integer.parseInt(numPeople), tipPercent);
                break;
            case R.id.buttonManual:
                ratingLayout.setVisibility(LinearLayout.GONE);
                tipPercentLayout.setVisibility(LinearLayout.VISIBLE);
                break;
            case R.id.buttonSuggested:
                ratingLayout.setVisibility(LinearLayout.VISIBLE);
                tipPercentLayout.setVisibility(LinearLayout.GONE);
                break;
        }
    }

    public void b_submitClick(float billAmount, int numPeople, float tipPercent){
        Intent i = new Intent("net.wintastic.tipcalc.SummaryActivity");
        i.putExtra("billAmount", billAmount);
        i.putExtra("numPeople",  numPeople);
        i.putExtra("tipPercent", tipPercent);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
