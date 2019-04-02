package javabrain.org.expenditure.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import javabrain.org.expenditure.R;
import javabrain.org.expenditure.service.RegisterUser;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText userNameEdTRU;
    private TextView userNameHintTeVRU;
    private EditText emailEdTRU;
    private TextView emailHintTeVRU;
    private EditText passwordEdTRU;
    private TextView passwordHintEdTRU;
    private EditText nameEdTRU;
    private TextView nameHintEdTRU;
    private EditText lastNameEdTRU;
    private TextView lastNameHintEdTRU;
    private Button registBtnRU;
    private ProgressBar registPrBRU;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        userNameEdTRU = (EditText) findViewById(R.id.userNameEdTRU);
        userNameHintTeVRU = (TextView) findViewById(R.id.userNameHintTeVRU);
        emailEdTRU = (EditText) findViewById(R.id.emailEdTRU);
        emailHintTeVRU = (TextView) findViewById(R.id.emailHintTeVRU);
        passwordEdTRU = (EditText) findViewById(R.id.passwordEdTRU);
        passwordHintEdTRU = (TextView) findViewById(R.id.passwordHintEdTRU);
        nameEdTRU = (EditText) findViewById(R.id.nameEdTRU);
        nameHintEdTRU = (TextView) findViewById(R.id.nameHintEdTRU);
        lastNameEdTRU = (EditText) findViewById(R.id.lastNameEdTRU);
        lastNameHintEdTRU = (TextView) findViewById(R.id.lastNameHintEdTRU);
        registBtnRU = (Button) findViewById(R.id.registBtnRU);
        registPrBRU = (ProgressBar) findViewById(R.id.registPrBRU);

        intent = new Intent(RegisterUserActivity.this,MainActivity.class);

        registBtnRU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    RegisterUser registerUser = new RegisterUser(RegisterUserActivity.this, intent,userNameEdTRU,emailEdTRU,passwordEdTRU,nameEdTRU,lastNameEdTRU, registBtnRU,registPrBRU);
                    registerUser.execute();
                }
            }
        });
    }

    private boolean validateFields() {

        boolean out = true;

        if (userNameEdTRU.getText().toString().isEmpty()) {
            userNameHintTeVRU.setVisibility(View.VISIBLE);
            userNameHintTeVRU.setText(this.getString(R.string.hint_usernamereg));
            out = false;
        } else {
            userNameHintTeVRU.setVisibility(View.INVISIBLE);
        }

        if (emailEdTRU.getText().toString().isEmpty()) {
            emailHintTeVRU.setVisibility(View.VISIBLE);
            emailHintTeVRU.setText(this.getString(R.string.hint_emailreg));
            out = false;
        } else {
            emailHintTeVRU.setVisibility(View.INVISIBLE);
        }


        if (passwordEdTRU.getText().toString().isEmpty()) {
            passwordHintEdTRU.setVisibility(View.VISIBLE);
            passwordHintEdTRU.setText(this.getString(R.string.hint_passwordreg));
            out = false;
        } else {
            passwordHintEdTRU.setVisibility(View.INVISIBLE);
        }

        if (nameEdTRU.getText().toString().isEmpty()) {
            nameHintEdTRU.setVisibility(View.VISIBLE);
            nameHintEdTRU.setText(this.getString(R.string.hint_namereg));
            out = false;
        } else {
            nameHintEdTRU.setVisibility(View.INVISIBLE);
        }

        if (lastNameEdTRU.getText().toString().isEmpty()) {
            lastNameHintEdTRU.setVisibility(View.VISIBLE);
            lastNameHintEdTRU.setText(this.getString(R.string.hint_lastnamereg));
            out = false;
        } else {
            lastNameHintEdTRU.setVisibility(View.INVISIBLE);
        }


        return out;
    }

    @Override
    public void onBackPressed() {
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
