package sg.edu.rp.c346.id20029699.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText inputAmt;
    EditText inputPax;
    ToggleButton SVS;
    ToggleButton GST;
    EditText discount;
    RadioGroup radioGp;
    RadioButton cashBtn;
    RadioButton PaynowBtn;
    Button splitBtn;
    Button resetBtn;
    TextView total;
    TextView eachPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputAmt = findViewById(R.id.editTextAmt);
        inputPax = findViewById(R.id.editTextPax);
        SVS = findViewById(R.id.toggleButtonSC);
        GST = findViewById(R.id.toggleButtonGST);
        discount = findViewById(R.id.editTextDiscount);
        radioGp = findViewById(R.id.radioGroupPay);
        cashBtn = findViewById(R.id.radioButtonCash);
        PaynowBtn = findViewById(R.id.radioButtonPN);
        splitBtn = findViewById(R.id.buttonSplit);
        resetBtn = findViewById(R.id.buttonReset);
        total = findViewById(R.id.textViewTotal);
        eachPerson = findViewById(R.id.textViewEach);

        //set event
        splitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = inputAmt.getText().toString();
                String data2 = inputPax.getText().toString();

                double newAmt = 0;
                double amt = Double.parseDouble(data1);
                int pax = Integer.parseInt(data2);

                if (SVS.isChecked() == true && GST.isChecked() == true){
                    newAmt = amt * 1.10 * 1.07;
                }
                else if (SVS.isChecked() == true && GST.isChecked() == false){
                    newAmt = amt * 1.10;

                }else if (GST.isChecked() == true && SVS.isChecked() == false){
                    newAmt = amt * 1.07;
                }
                else {
                    newAmt = amt;
                }

                double finalAmt = 0;
                String data3 = discount.getText().toString();
                int num = Integer.parseInt(data3);
                if (discount.isEnabled() == true){
                    double percent = 100 - num;
                    finalAmt = (newAmt/100) * percent;
                } else {
                    finalAmt = newAmt;
                }

                String totalAmt = String.format("%.2f", finalAmt);
                total.setText("Total Bill: $" + totalAmt);
                double eachPaxAmt = finalAmt/pax;
                int payment = radioGp.getCheckedRadioButtonId();
                if (payment == R.id.radioButtonCash){
                    String paxAmt = String.format("%.2f", eachPaxAmt);
                    eachPerson.setText("Each Pays: $" + paxAmt + " in cash" );
                }else {
                    String paxAmt = String.format("%.2f", eachPaxAmt);
                    eachPerson.setText("Each Pays: $" + paxAmt + " via PayNow to 91234567" );
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputAmt.setText("");
                inputPax.setText("");
                GST.setChecked(false);
                SVS.setChecked(false);
                discount.setText("");
                cashBtn.setChecked(false);
                PaynowBtn.setChecked(false);
                total.setText("");
                eachPerson.setText("");
            }
        });

    }
}