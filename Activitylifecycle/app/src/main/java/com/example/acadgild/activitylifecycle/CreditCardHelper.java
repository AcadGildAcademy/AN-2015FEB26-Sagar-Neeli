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
        btns = new Button[2];
        int[] buttonIDs = new int[] {R.id.buttonCompute, R.id.buttonClear};
        editTxt = new EditText[6];

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
                for (int i = 0; i < 6; i++) {
                    editTxt[i].setText("");
                }
                break;

        }
    }

    private void compute() {
//        int[] editTxtIDs = new int[] {R.id.editCardBalance, R.id.editYearlyIntRate, R.id.editMinPay, R.id.editMinPay, R.id.editFinalCardBal,
//                R.id.editMonRem, R.id.editIntPaid};
        float rate = 0;
        float minimum_payment = 0;
        float monthlyfloatInterestPaid = 0;
        float monthlyPrinciple = 0;
        float principal = 0;
        float balance = 0;
        float prevBalance = 0;
        int count = 0;
        editTxt[0] = (EditText)findViewById(R.id.editCardBalance);
        editTxt[1] = (EditText)findViewById(R.id.editYearlyIntRate);
        editTxt[2] = (EditText)findViewById(R.id.editMinPay);
        editTxt[3] = (EditText)findViewById(R.id.editFinalCardBal);
        editTxt[4] = (EditText)findViewById(R.id.editMonRem);
        editTxt[5] = (EditText)findViewById(R.id.editIntPaid);
        try {
            principal = Float.parseFloat(editTxt[0].getText().toString());
            rate = Float.parseFloat(editTxt[1].getText().toString());
            minimum_payment = Float.parseFloat(editTxt[2].getText().toString());
            while(monthlyfloatInterestPaid != 0) {
                monthlyfloatInterestPaid = Math.round((principal * (rate / (100 * 12))));
                monthlyPrinciple = minimum_payment - monthlyfloatInterestPaid;
                if (monthlyPrinciple == minimum_payment)
                    break;
                balance = principal - monthlyPrinciple;
                principal = balance;
//                if (balance > 0)
//                    principal = balance;
//                else
//                    prevBalance = principal;
                count++;
            }

            editTxt[3].setText(String.valueOf(monthlyfloatInterestPaid));
            editTxt[4].setText(String.valueOf(count));
            editTxt[5].setText(String.valueOf(balance));

        } catch (NumberFormatException ex) {
            editTxt[3].setText("Value at TextView is not a valid integer");
            editTxt[5].setText("Value at TextView is not a valid integer");
        }

    }


//    1.  card balance will become your principal.
//    2. monthlyfloatInterestPaid= Math.round((principal * (rate / (100 * 12))))
//    3. monthlyPrinciple= minimum_payment-monthlyfloatInterestPaid
//    4. monthlyBalance=principal-monthlyPrinciple
//    5. For counting next month use below formula and repeat the step 2,3,4,5.
//    principal= monthlyBalance
//    6. For calculating how many months you have to pay use count variable for step 5

}
