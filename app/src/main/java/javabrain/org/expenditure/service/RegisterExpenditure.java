package javabrain.org.expenditure.service;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javabrain.org.expenditure.R;
import javabrain.org.expenditure.api.JsonApi;
import javabrain.org.expenditure.api.PetitionApi;
import javabrain.org.expenditure.persistence.Global;
import javabrain.org.expenditure.pojo.Expenditure;

/**
 * Created by Fernando García on 05/04/2019.
 */
public class RegisterExpenditure extends AsyncTask<Integer, Void, String> {

    private Context context;
    private EditText expenditureNameEdTRE;
    private EditText expenditureEdTRE;
    private Button saveExpenditureBtnRE;
    private ProgressBar saveExpenditureLoadPrBRE;

    public RegisterExpenditure(Context context, EditText expenditureNameEdTRE, EditText expenditureEdTRE, Button saveExpenditureBtnRE, ProgressBar saveExpenditureLoadPrBRE) {
        this.context = context;
        this.expenditureNameEdTRE = expenditureNameEdTRE;
        this.expenditureEdTRE = expenditureEdTRE;
        this.saveExpenditureBtnRE = saveExpenditureBtnRE;
        this.saveExpenditureLoadPrBRE = saveExpenditureLoadPrBRE;
    }

    @Override
    protected void onPreExecute() {
        saveExpenditureBtnRE.setVisibility(View.GONE);
        saveExpenditureLoadPrBRE.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Integer... integers) {
        Map params = new HashMap<>();
        params.put("id", integers[0]);
        params.put("name",expenditureNameEdTRE.getText().toString());
        params.put("expenditure",expenditureEdTRE.getText().toString());
        params.put("monthPay",integers[1]);
        String resp = PetitionApi.doPost(context.getString(R.string.host_name) + "/expenditure/registerExpenditure.php",params);
        return resp;
    }

    @Override
    protected void onPostExecute(String s) {
        saveExpenditureBtnRE.setVisibility(View.VISIBLE);
        saveExpenditureLoadPrBRE.setVisibility(View.GONE);
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
            Global.expenditures.add(expenditure);
        }

    }
}
