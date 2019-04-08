package javabrain.org.expenditure.service;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import javabrain.org.expenditure.pojo.Expenditure;

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
                    GetExpenditure expenditure = new GetExpenditure(context,loadExpenditurePrBEI,contentLiLEI);
                    expenditure.execute(Global.month.getId(),Global.month.getPeriod());
                }
            }

        }
    }
}

class GetExpenditure extends AsyncTask<Integer, Void, String> {

    private Context context;
    private ProgressBar loadExpenditurePrBEI;
    private LinearLayout contentLiLEI;

    public GetExpenditure(Context context, ProgressBar loadExpenditurePrBEI, LinearLayout contentLiLEI) {
        this.context = context;
        this.loadExpenditurePrBEI = loadExpenditurePrBEI;
        this.contentLiLEI = contentLiLEI;
    }

    @Override
    protected void onPreExecute() {
        loadExpenditurePrBEI.setVisibility(View.VISIBLE);
        contentLiLEI.setVisibility(View.GONE);
    }

    @Override
    protected String doInBackground(Integer... integers) {
        Map params = new HashMap<>();
        params.put("monthId",integers[0]);
        params.put("period",integers[1]);
        String resp = PetitionApi.doPost(context.getString(R.string.host_name) + "/expenditure/getExpenditures.php", params);
        return resp;
    }

    @Override
    protected void onPostExecute(String s) {

        loadExpenditurePrBEI.setVisibility(View.GONE);
        contentLiLEI.setVisibility(View.VISIBLE);
        Global.expenditures.clear();

        JSONArray respsJson = (JSONArray) JsonApi.parser(s);
        for (Object obj : respsJson) {
            JSONObject object = (JSONObject) obj;
            Expenditure expenditure = new Expenditure();
            expenditure.setId(Integer.parseInt(object.get("id") == null ? "0" : object.get("id").toString()));
            expenditure.setExpenditureName(object.get("expenditureName").toString());
            expenditure.setPeriod(Integer.parseInt(object.get("period") == null ? "0" : object.get("period").toString()));
            expenditure.setExpenditure(Double.parseDouble(object.get("expenditure") == null ? "0.0" : object.get("expenditure").toString()));
            expenditure.setExpenditureMonth(Double.parseDouble(object.get("expenditureMonth") == null ? "0.0" : object.get("expenditureMonth").toString()));
            expenditure.setExpenditureMonthPayed(Double.parseDouble(object.get("expenditureMonthPayed") == null ? "0.0" : object.get("expenditureMonthPayed").toString()));
            expenditure.setMonthPay(Double.parseDouble(object.get("monthPay") == null ? "0.0" : object.get("monthPay").toString()));
            expenditure.setMonthPayed(Double.parseDouble(object.get("monthPayed") == null ? "0.0" : object.get("monthPayed").toString()));
            expenditure.setExpenditureDate(object.get("expenditureDate").toString());
            expenditure.setPayed(object.get("isPayed").toString().equals("1"));

            View view = LayoutInflater.from(context).inflate(R.layout.layout_expenditure,null,false);
            TextView nameTeVLE = (TextView) view.findViewById(R.id.nameTeVLE);
            TextView expenditureTeVLE = (TextView) view.findViewById(R.id.expenditureTeVLE);
            TextView payMonthTeVLE = (TextView) view.findViewById(R.id.payMonthTeVLE);
            TextView payedTeVLE = (TextView) view.findViewById(R.id.payedTeVLE);
            TextView dateTeVLE = (TextView) view.findViewById(R.id.dateTeVLE);
            CheckBox isPayChKLE = (CheckBox) view.findViewById(R.id.isPayChKLE);

            nameTeVLE.setText(expenditure.getExpenditureName());
            expenditureTeVLE.setText(expenditure.getExpenditure() + "");
            payMonthTeVLE.setText(payMonthTeVLE.getText().toString().replace("0", expenditure.getExpenditureMonth() + ""));
            payedTeVLE.setText(payedTeVLE.getText().toString().replace("0", expenditure.getExpenditureMonth() + ""));
            dateTeVLE.setText(expenditure.getExpenditureDate());
            isPayChKLE.setChecked(expenditure.isPayed());
            contentLiLEI.addView(view);

            Global.expenditures.add(expenditure);
        }
    }
}
