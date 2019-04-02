package javabrain.org.expenditure.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import javabrain.org.expenditure.R;
import javabrain.org.expenditure.service.Login;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText userNameEdTMA;
    private EditText passPsTMA;
    private Button loginBtnMA;
    private ProgressBar loginPrBMA;
    private TextView errorMsgTxVMA;
    private Intent registerUser;
    private Intent expenditureIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        userNameEdTMA = (EditText) findViewById(R.id.userNameEdTMA);
        passPsTMA = (EditText) findViewById(R.id.passPsTMA);
        loginBtnMA = (Button) findViewById(R.id.loginBtnMA);
        loginPrBMA = (ProgressBar) findViewById(R.id.loginPrBMA);
        errorMsgTxVMA = (TextView) findViewById(R.id.errorMsgTxVMA);
        registerUser = new Intent(MainActivity.this,RegisterUserActivity.class);
        expenditureIndex = new Intent(MainActivity.this,ExpenditureIndex.class);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActivity(registerUser);
            }
        });


        loginBtnMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userNameEdTMA.getText().toString().isEmpty() && !passPsTMA.getText().toString().isEmpty()) {
                    Login login = new Login(MainActivity.this, userNameEdTMA, passPsTMA, loginBtnMA, loginPrBMA, errorMsgTxVMA, expenditureIndex);
                    login.execute();
                } else {
                    errorMsgTxVMA.setVisibility(View.VISIBLE);
                    errorMsgTxVMA.setText(MainActivity.this.getString(R.string.error_fields_sesion));
                }
            }
        });

    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }

    @Override
    public void onBackPressed() {}
}
