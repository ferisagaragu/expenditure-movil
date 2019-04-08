package javabrain.org.expenditure.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import javabrain.org.expenditure.R;
import javabrain.org.expenditure.persistence.Global;
import javabrain.org.expenditure.service.RegisterExpenditure;

public class RegisterExpenditureActivity extends AppCompatActivity {

    private EditText expenditureNameEdTRE;
    private TextView expenditureNameHintTeVRE;
    private EditText expenditureEdTRE;
    private TextView expenditureHintTeVRE;
    private Spinner payTypeSpiRE;
    private LinearLayout monthContentLiLRE;
    private LinearLayout xMonthLiLRE;
    private RadioGroup xMonthGroupRaGRE;
    private RadioButton x3RaBRE;
    private RadioButton x6RaBRE;
    private RadioButton x12RaBRE;
    private RadioButton x18RaBRE;
    private RadioButton x24RaBRE;
    private CheckBox otherMonthChBRE;
    private EditText xMonthCustomEdTRE;
    private TextView xMonthCustomHintTeVRE;
    private Button saveExpenditureBtnRE;
    private ProgressBar saveExpenditureLoadPrBRE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_expenditure);

        expenditureNameEdTRE = (EditText) findViewById(R.id.expenditureNameEdTRE);
        expenditureNameHintTeVRE = (TextView) findViewById(R.id.expenditureNameHintTeVRE);
        expenditureEdTRE = (EditText) findViewById(R.id.expenditureEdTRE);
        expenditureHintTeVRE = (TextView) findViewById(R.id.expenditureHintTeVRE);
        payTypeSpiRE = (Spinner) findViewById(R.id.payTypeSpiRE);
        monthContentLiLRE = (LinearLayout) findViewById(R.id.monthContentLiLRE);
        xMonthLiLRE = (LinearLayout) findViewById(R.id.xMonthLiLRE);
        x3RaBRE = (RadioButton) findViewById(R.id.x3RaBRE);
        x6RaBRE = (RadioButton) findViewById(R.id.x6RaBRE);
        x12RaBRE = (RadioButton) findViewById(R.id.x12RaBRE);
        x18RaBRE = (RadioButton) findViewById(R.id.x18RaBRE);
        x24RaBRE = (RadioButton) findViewById(R.id.x24RaBRE);
        otherMonthChBRE = (CheckBox) findViewById(R.id.otherMonthChBRE);
        xMonthCustomEdTRE = (EditText) findViewById(R.id.xMonthCustomEdTRE);
        xMonthCustomHintTeVRE = (TextView) findViewById(R.id.xMonthCustomHintTeVRE);
        saveExpenditureBtnRE = (Button) findViewById(R.id.saveExpenditureBtnRE);
        saveExpenditureLoadPrBRE = (ProgressBar) findViewById(R.id.saveExpenditureLoadPrBRE);



        payTypeSpiRE.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        monthContentLiLRE.setVisibility(View.GONE);
                        break;
                    case 1:
                        monthContentLiLRE.setVisibility(View.GONE);
                        break;
                    case 2:
                        monthContentLiLRE.setVisibility(View.GONE);
                        break;
                    case 3:
                        monthContentLiLRE.setVisibility(View.VISIBLE);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        otherMonthChBRE.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    xMonthLiLRE.setVisibility(View.GONE);
                    xMonthCustomEdTRE.setVisibility(View.VISIBLE);
                } else {
                    xMonthLiLRE.setVisibility(View.VISIBLE);
                    xMonthCustomEdTRE.setVisibility(View.GONE);
                }
            }
        });

        saveExpenditureBtnRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    RegisterExpenditure registerExpenditure = new RegisterExpenditure(RegisterExpenditureActivity.this, expenditureNameEdTRE, expenditureEdTRE,saveExpenditureBtnRE,saveExpenditureLoadPrBRE);
                    registerExpenditure.execute(Global.user.getId(), 0);
                }
            }
        });

    }

    private boolean validateFields() {
        boolean out = true;

        if (expenditureNameEdTRE.getText().toString().isEmpty()) {
            expenditureNameHintTeVRE.setVisibility(View.VISIBLE);
            out = false;
        } else {
            expenditureNameHintTeVRE.setVisibility(View.GONE);
        }

        if (expenditureEdTRE.getText().toString().isEmpty()) {
            expenditureHintTeVRE.setVisibility(View.VISIBLE);
            out = false;
        } else {
            int value = Integer.parseInt(expenditureEdTRE.getText().toString());
            if (value <= 0) {
                expenditureHintTeVRE.setVisibility(View.VISIBLE);
                out = false;
            } else {
                expenditureHintTeVRE.setVisibility(View.GONE);
            }
        }

        return out;
    }

}
