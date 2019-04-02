package javabrain.org.expenditure.api;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Fernando García
 * @version 0.0.3
 */
public class PetitionApi {

    public static String doGet(String url) {
        //Permisos para Internet
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //=================================================================
        URL urlIn;
        try {
            urlIn = new URL(url);
            URLConnection con = urlIn.openConnection();
            HttpURLConnection conn = (HttpURLConnection) urlIn.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            String linea = "";
            String out = "";
            while ((linea = in.readLine()) != null) {
                out += linea;
            }
            return out;
        } catch (IOException e) {
            Log.e("PETITION ERROR:",e.getMessage());
        }
        return null;
    }

    public static String doPost(String url, Map<Object, Object> params) {
        //Permisos para Internet
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //=================================================================
        try {
            URL urlIn = new URL(url);
            StringBuilder postData = new StringBuilder();

            for (Map.Entry<Object, Object> param : params.entrySet()) {
                if (postData.length() != 0) {
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey().toString(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }

            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) urlIn.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String result = "";
            for (int c = in.read(); c != -1; c = in.read()) {
                result += String.valueOf((char) c);
            }
            return result;
        } catch (IOException ex) {
            Log.e("PETITION ERROR:", ex.getMessage());
        }
        return null;
    }

}
