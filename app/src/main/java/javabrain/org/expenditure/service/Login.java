package javabrain.org.expenditure.service;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import javabrain.org.expenditure.R;
import javabrain.org.expenditure.api.FileApi;
import javabrain.org.expenditure.api.JsonApi;
import javabrain.org.expenditure.api.PetitionApi;
import javabrain.org.expenditure.persistence.Global;

/**
 * Created by Fernando García on 30/01/2019.
 */
public class Login extends AsyncTask<Void, Void, String> {

    private Context context;
    private EditText userNameEdTMA;
    private EditText passPsTMA;
    private Button loginBtnMA;
    private ProgressBar loginPrBMA;
    private TextView errorMsgTxVMA;
    private Intent intent;

    public Login(Context context, EditText userNameEdTMA, EditText passPsTMA, Button loginBtnMA, ProgressBar loginPrBMA, TextView errorMsgTxVMA, Intent intent) {
        this.context = context;
        this.userNameEdTMA = userNameEdTMA;
        this.passPsTMA = passPsTMA;
        this.loginBtnMA = loginBtnMA;
        this.loginPrBMA = loginPrBMA;
        this.errorMsgTxVMA = errorMsgTxVMA;
        this.intent = intent;
    }

    @Override
    protected void onPreExecute() {
        loginBtnMA.setVisibility(View.GONE);
        loginPrBMA.setVisibility(View.VISIBLE);
        String s = FileApi.read(context,"userData.json");
    }

    @Override
    protected String doInBackground(Void... voids) {
        Map params = new LinkedHashMap();
        params.put("user",userNameEdTMA.getText().toString());
        params.put("password",passPsTMA.getText().toString());
        String resp = PetitionApi.doPost( context.getResources().getString(R.string.host_name) + "/user/login.php",params);
        resp = resp.isEmpty() ? "[{\"id\":\"0\",\"user\":\"\",\"password\":\"\",\"active\":\"-1\",\"firstname\":\"\",\"lastname\":\"\"}]" : resp;
        return resp;
    }

    @Override
    protected void onPostExecute(String resp) {

        loginBtnMA.setVisibility(View.VISIBLE);
        loginPrBMA.setVisibility(View.GONE);
        errorMsgTxVMA.setVisibility(View.GONE);

        JSONObject respJson = (JSONObject) ((JSONArray) JsonApi.parser(resp)).get(0);

        if (!respJson.get("id").toString().equals("0") && respJson.get("active").equals("1")) {
            Global.user.setId(Integer.parseInt(respJson.get("id").toString()));
            Global.user.setUser(respJson.get("user").toString());
            Global.user.setPassword(respJson.get("password").toString());
            Global.user.setEmail(respJson.get("email").toString());
            Global.user.setActive(respJson.get("active").equals("1"));
            Global.user.setFirstName(respJson.get("firstname").toString());
            Global.user.setLastName(respJson.get("lastname").toString());
            FileApi.write(context,"userData.json",resp,context.getString(R.string.start_sesion) +" "+ Global.user.getEmail() +".");
            context.startActivity(intent);
        } else if (!respJson.get("id").toString().equals("0") && respJson.get("active").equals("0")){
            errorMsgTxVMA.setVisibility(View.VISIBLE);
            errorMsgTxVMA.setText(context.getResources().getString(R.string.inactive_user));
        } else {
            errorMsgTxVMA.setVisibility(View.VISIBLE);
            errorMsgTxVMA.setText(context.getResources().getString(R.string.error_sesion));
        }

    }
}
