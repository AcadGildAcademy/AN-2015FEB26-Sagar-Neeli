package com.example.acadgild.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by sneeli on 3/20/2015.
 */
public class CreditCardHelper extends Activity implements View.OnClickListener  {

    EditText[] editTxt;
    TextView txtView;
    Button[] btns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit_card);
//        editTxt = findViewById();
        btns = new Button[2];
        int[] buttonIDs = new int[] {R.id.buttonCompute, R.id.buttonClear};

        for (int i = 0; i < btns.length; i++) {
            btns[i] = ((Button) findViewById(buttonIDs[i]));
            btns[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCompute:
                compute();
                break;
            case R.id.buttonClear:
                break;

        }
    }

    private void compute() {
//        int[] editTxtIDs = new int[] {R.id.editCardBalance, R.id.editYearlyIntRate, R.id.editMinPay, R.id.editMinPay, R.id.editFinalCardBal,
//                R.id.editMonRem, R.id.editIntPaid};
        editTxt = new EditText[6];
        editTxt[0] = (EditText)findViewById(R.id.editCardBalance);
        editTxt[1] = (EditText)findViewById(R.id.editYearlyIntRate);
        editTxt[2] = (EditText)findViewById(R.id.editIntPaid);
        editTxt[3] = (EditText)findViewById(R.id.editFinalCardBal);
        editTxt[4] = (EditText)findViewById(R.id.editMonRem);
        editTxt[5] = (EditText)findViewById(R.id.editIntPaid);
//        float principal = Float.parseFloat(editTxt[0].getText().toString());
//        float rate = Float.parseFloat(editTxt[1].getText().toString());
//        float minimum_payment = Float.parseFloat(editTxt[2].getText().toString());
//        float monthlyfloatInterestPaid = Math.round((principal * (rate / (100 * 12))));
//        float monthlyPrinciple = minimum_payment - monthlyfloatInterestPaid;
//        editTxt[3].setText((int)monthlyPrinciple);
//        editTxt[5].setText((int) monthlyfloatInterestPaid);
        int principal = 0;
        int rate = 0;
        int minimum_payment = 0;
        int monthlyfloatInterestPaid = 0;
        int monthlyPrinciple = 0;
        try {
            principal = Integer.parseInt(editTxt[0].getText().toString());
            rate = Integer.parseInt(editTxt[1].getText().toString());
            minimum_payment = Integer.parseInt(editTxt[2].getText().toString());
            monthlyfloatInterestPaid = Math.round((principal * (rate / (100 * 12))));
            monthlyPrinciple = minimum_payment - monthlyfloatInterestPaid;
//            principal = Integer.valueOf(editTxt[0].getText().toString()).intValue();
//            rate = Integer.valueOf(editTxt[1].getText().toString()).intValue();
//            minimum_payment = Integer.valueOf(editTxt[2].getText().toString()).intValue();
//            monthlyfloatInterestPaid = Math.round((principal * (rate / (100 * 12))));
//            monthlyPrinciple = minimum_payment - monthlyfloatInterestPaid;
//            editTxt[3].setText(String.valueOf(monthlyPrinciple));
//            editTxt[5].setText(String.valueOf(monthlyfloatInterestPaid));
            editTxt[3].setText(monthlyPrinciple + "");
            editTxt[5].setText(monthlyfloatInterestPaid + "");

        } catch(NumberFormatException ex){

//            System.out.println("Value at TextView is not a valid integer");
            editTxt[3].setText("Value at TextView is not a valid integer");
            editTxt[5].setText("Value at TextView is not a valid integer");

        }
//        editTxt[3].setText(String.valueOf(monthlyPrinciple));
//        editTxt[5].setText(String.valueOf(monthlyfloatInterestPaid));
//        editTxt[3].setText(Integer.toString(monthlyPrinciple));
//        editTxt[5].setText(Integer.toString(monthlyfloatInterestPaid));

    }


//    1.  card balance will become your principal.
//    2. monthlyfloatInterestPaid= Math.round((principal * (rate / (100 * 12))))
//    3. monthlyPrinciple= minimum_payment-monthlyfloatInterestPaid
//    4. monthlyBalance=principal-monthlyPrinciple
//    5. For counting next month use below formula and repeat the step 2,3,4,5.
//    principal= monthlyBalance
//    6. For calculating how many months you have to pay use count variable for step 5

}
