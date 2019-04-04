package javabrain.org.expenditure.service;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import javabrain.org.expenditure.R;
import javabrain.org.expenditure.api.PetitionApi;
import javabrain.org.expenditure.persistence.Global;

/**
 * Created by Fernando García on 04/04/2019.
 */

public class UpdateSalary extends AsyncTask<Integer, Void, String> {

    private Context context;
    private EditText salaryEdTEI;
    private ProgressBar loadExpenditurePrBEI;
    private LinearLayout salaryContentLiLEI;
    private LinearLayout contentLiLEI;
    private TextView salaryTeVEI;

    public UpdateSalary(Context context, EditText salaryEdTEI, ProgressBar loadExpenditurePrBEI, LinearLayout salaryContentLiLEI, LinearLayout contentLiLEI, TextView salaryTeVEI) {
        this.context = context;
        this.salaryEdTEI = salaryEdTEI;
        this.loadExpenditurePrBEI = loadExpenditurePrBEI;
        this.salaryContentLiLEI = salaryContentLiLEI;
        this.contentLiLEI = contentLiLEI;
        this.salaryTeVEI = salaryTeVEI;
    }

    @Override
    protected String doInBackground(Integer... integers) {
        Map params = new HashMap<>();
        params.put("id",integers[0]);
        params.put("salary",salaryEdTEI.getText().toString());
        String resp = PetitionApi.doPost(context.getString(R.string.host_name) + "/month/updateSalary.php",params);
        return resp;
    }

    @Override
    protected void onPostExecute(String s) {
        CreateMonth createMonth = new CreateMonth(context,loadExpenditurePrBEI,salaryContentLiLEI,contentLiLEI,salaryTeVEI);
        createMonth.execute(Global.user.getId());
    }
}
