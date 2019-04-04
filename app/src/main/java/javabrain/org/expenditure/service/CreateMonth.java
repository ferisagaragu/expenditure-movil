package javabrain.org.expenditure.service;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javabrain.org.expenditure.R;
import javabrain.org.expenditure.api.Animator;
import javabrain.org.expenditure.api.JsonApi;
import javabrain.org.expenditure.api.PetitionApi;
import javabrain.org.expenditure.persistence.Global;

/**
 * Created by Fernando García on 03/04/2019.
 */

public class CreateMonth extends AsyncTask<Integer, Void, String> {

    private Context context;
    private ProgressBar loadExpenditurePrBEI;
    private LinearLayout salaryContentLiLEI;
    private LinearLayout contentLiLEI;
    private TextView salaryTeVEI;

    public CreateMonth(Context context, ProgressBar loadExpenditurePrBEI, LinearLayout salaryContentLiLEI, LinearLayout contentLiLEI, TextView salaryTeVEI) {
        this.context = context;
        this.loadExpenditurePrBEI = loadExpenditurePrBEI;
        this.salaryContentLiLEI = salaryContentLiLEI;
        this.contentLiLEI = contentLiLEI;
        this.salaryTeVEI = salaryTeVEI;
    }

    @Override
    protected void onPreExecute() {
        loadExpenditurePrBEI.setVisibility(View.VISIBLE);
        salaryContentLiLEI.setVisibility(View.GONE);
        contentLiLEI.setVisibility(View.GONE);
    }

    @Override
    protected String doInBackground(Integer... id) {
        Map params = new HashMap<>();
        params.put("id",id[0]);
        String resp = PetitionApi.doPost(context.getString(R.string.host_name) + "/month/createMonth.php",params);
        return resp;
    }

    @Override
    protected void onPostExecute(String s) {

        loadExpenditurePrBEI.setVisibility(View.GONE);

        JSONObject respJs = (JSONObject) ((JSONArray) JsonApi.parser(s)).get(0);
        if (respJs.get("error") != null) {
            Toast t = Toast.makeText(context, context.getString(R.string.error_moth), Toast.LENGTH_SHORT);
            t.show();
        } else {
            Log.e("================", s);
            Global.month.setId(Integer.parseInt(respJs.get("id") == null ? "0" : respJs.get("id").toString()));
            Global.month.setStart(respJs.get("start").toString());
            Global.month.setEnd(respJs.get("end").toString());
            Global.month.setSalary(Double.parseDouble(respJs.get("salary") == null ? "0" : respJs.get("salary").toString()));
            Global.month.setTotalPay(Double.parseDouble(respJs.get("totalPay") == null ? "0" : respJs.get("totalPay").toString()));
            Global.month.setTotalPeriod1(Double.parseDouble(respJs.get("totalPeriod1") == null ? "0" : respJs.get("totalPeriod1").toString()));
            Global.month.setTotalPayPeriod1(Double.parseDouble(respJs.get("totalPayPeriod1") == null ? "0" : respJs.get("totalPayPeriod1").toString()));
            Global.month.setTotalPayedPeriod1(Double.parseDouble(respJs.get("totalPayedPeriod1") == null ? "0" : respJs.get("totalPayedPeriod1").toString()));
            Global.month.setEnableMoneyPeriod1(Double.parseDouble(respJs.get("enableMoneyPeriod1") == null ? "0" : respJs.get("enableMoneyPeriod1").toString()));
            Global.month.setTotalPeriod2(Double.parseDouble(respJs.get("totalPeriod2") == null ? "0" : respJs.get("totalPeriod2").toString()));
            Global.month.setTotalPayPeriod2(Double.parseDouble(respJs.get("totalPayPeriod2") == null ? "0" : respJs.get("totalPayPeriod2").toString()));
            Global.month.setTotalPayedPeriod2(Double.parseDouble(respJs.get("totalPayedPeriod2") == null ? "0" : respJs.get("totalPayedPeriod2").toString()));
            Global.month.setEnableMoneyPeriod2(Double.parseDouble(respJs.get("enableMoneyPeriod2") == null ? "0" : respJs.get("enableMoneyPeriod2").toString()));
            Global.month.setPeriod(Integer.parseInt(respJs.get("period") == null ? "0" : respJs.get("period").toString()));

            if (Global.month.getSalary() == 0) {
                salaryContentLiLEI.setVisibility(View.VISIBLE);
            } else {
                contentLiLEI.setVisibility(View.VISIBLE);
                if (Global.month.getPeriod() != 0) {
                    Animator.numberAnimation(Global.month.getPeriod() == 1 ? Global.month.getEnableMoneyPeriod1() : Global.month.getEnableMoneyPeriod2(), salaryTeVEI);
                }
            }

        }
    }
}
