package javabrain.org.expenditure.controller;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import javabrain.org.expenditure.R;
import javabrain.org.expenditure.persistence.Global;
import javabrain.org.expenditure.service.CreateMonth;
import javabrain.org.expenditure.service.UpdateSalary;

public class ExpenditureIndexActivity extends AppCompatActivity {

    private ProgressBar loadExpenditurePrBEI;
    private LinearLayout salaryContentLiLEI;
    private EditText salaryEdTEI;
    private Button saveSalaryBtnEI;
    private LinearLayout contentLiLEI;
    private TextView salaryTeVEI;
    private FloatingActionButton registBtnEI;

    private Intent registerExpenditure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure_index);

        loadExpenditurePrBEI = (ProgressBar) findViewById(R.id.loadExpenditurePrBEI);
        salaryContentLiLEI = (LinearLayout) findViewById(R.id.salaryContentLiLEI);
        salaryEdTEI = (EditText) findViewById(R.id.salaryEdTEI);
        saveSalaryBtnEI = (Button) findViewById(R.id.saveSalaryBtnEI);
        contentLiLEI = (LinearLayout) findViewById(R.id.contentLiLEI);
        salaryTeVEI = (TextView) findViewById(R.id.salaryTeVEI);
        registBtnEI = (FloatingActionButton) findViewById(R.id.registBtnEI);

        registerExpenditure = new Intent(ExpenditureIndexActivity.this, RegisterExpenditureActivity.class);

        saveSalaryBtnEI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateSalary updateSalary = new UpdateSalary(ExpenditureIndexActivity.this, salaryEdTEI, loadExpenditurePrBEI, salaryContentLiLEI, contentLiLEI, salaryTeVEI);
                updateSalary.execute(Global.user.getId());
            }
        });

        registBtnEI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExpenditureIndexActivity.this.startActivity(registerExpenditure);
            }
        });

    }

    @Override
    protected void onResume() {
        CreateMonth createMonth = new CreateMonth(this,loadExpenditurePrBEI, salaryContentLiLEI, contentLiLEI, salaryTeVEI);
        createMonth.execute(Global.user.getId());
        super.onResume();
    }
}
