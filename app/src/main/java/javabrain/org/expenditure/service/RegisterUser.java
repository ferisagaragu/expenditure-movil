package javabrain.org.expenditure.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import javabrain.org.expenditure.R;
import javabrain.org.expenditure.api.PetitionApi;
import javabrain.org.expenditure.controller.RegisterUserActivity;

/**
 * Created by Fernando García on 02/04/2019.
 */

public class RegisterUser extends AsyncTask<Void, Void, String> {

    private Context context;
    private Intent intent;
    private EditText userNameEdTRU;
    private EditText emailEdTRU;
    private EditText passwordEdTRU;
    private EditText nameEdTRU;
    private EditText lastNameEdTRU;
    private ProgressBar registPrBRU;
    private Button registBtnRU;

    public RegisterUser(Context context, Intent intent, EditText userNameEdTRU, EditText emailEdTRU, EditText passwordEdTRU, EditText nameEdTRU, EditText lastNameEdTRU, Button registBtnRU, ProgressBar registPrBRU) {
        this.context = context;
        this.intent = intent;
        this.userNameEdTRU = userNameEdTRU;
        this.emailEdTRU = emailEdTRU;
        this.passwordEdTRU = passwordEdTRU;
        this.nameEdTRU = nameEdTRU;
        this.lastNameEdTRU = lastNameEdTRU;
        this.registBtnRU = registBtnRU;
        this.registPrBRU = registPrBRU;
    }

    @Override
    protected void onPreExecute() {
        registBtnRU.setVisibility(View.GONE);
        registPrBRU.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Map params = new HashMap<>();
        params.put("user",userNameEdTRU.getText().toString());
        params.put("email",emailEdTRU.getText().toString());
        params.put("password",passwordEdTRU.getText().toString());
        params.put("firstname",nameEdTRU.getText().toString());
        params.put("lastname",lastNameEdTRU.getText().toString());
        String resp = PetitionApi.doPost(context.getResources().getString(R.string.host_name) + "/user/registerUser.php",params);
        return resp;
    }

    @Override
    protected void onPostExecute(String s) {

        registBtnRU.setVisibility(View.VISIBLE);
        registPrBRU.setVisibility(View.GONE);

        if (s.equals("1")) {
            Toast t = Toast.makeText(context, context.getString(R.string.susses_register), Toast.LENGTH_SHORT);
            t.show();
            context.startActivity(intent);
        } else {
            Toast t = Toast.makeText(context, context.getString(R.string.error_register), Toast.LENGTH_SHORT);
            t.show();
        }
    }
}
