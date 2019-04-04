package javabrain.org.expenditure.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

/**
 * Created by Fernando García on 30/01/2019.
 */

public class FileApi {

    public static void write(Context context,String filename,String text,String msg) {
        try {

            OutputStreamWriter file = new OutputStreamWriter(context.openFileOutput(filename,context.MODE_PRIVATE));
            file.write(text);
            file.flush();
            file.close();

        } catch (IOException e) {}

        Toast t = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        t.show();
    }

    public static String read(Context context, String filename) {

        String out = "";

        if (exist(context, filename)) {
            try {
                InputStreamReader file = new InputStreamReader(context.openFileInput(filename));
                BufferedReader br = new BufferedReader(file);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }

                out = todo;
                br.close();
                file.close();

            } catch (IOException e) {}
        }

        return out;
    }

    public static void read(Context context, String filename, OnReadFile onReadFile) {

        String out = "";

        if (exist(context, filename)) {
            try {
                InputStreamReader file = new InputStreamReader(context.openFileInput(filename));
                BufferedReader br = new BufferedReader(file);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }

                out = todo;
                br.close();
                file.close();

            } catch (IOException e) {}
        }
        onReadFile.onRead(out);
    }

    public static void download(Context context,String url,String name) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOS = new FileOutputStream(name)) {
            byte data[] = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            Log.d("ERROR",e.getMessage());
        }
    }

    private static boolean exist(Context context, String filename) {
        for (int f = 0; f < context.fileList().length; f++) {
            if (filename.equals(context.fileList()[f])) {
                return true;
            }
        }
        return false;
    }

}
