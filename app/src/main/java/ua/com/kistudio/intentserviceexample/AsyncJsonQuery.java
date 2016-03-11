package ua.com.kistudio.intentserviceexample;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Вiталя on 09.03.2016.
 */
public class AsyncJsonQuery extends AsyncTask<String,String,JSONObject> {
    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject localJSON = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (is.read(b)!=-1)
                baos.write(b);

            String JSONString = new String(baos.toByteArray());
            String clearJSON = JSONString.substring(0,JSONString.lastIndexOf("}")+1);
            Log.d(Prefs.LOG_TAG,clearJSON);
            localJSON = new JSONObject(clearJSON);
            publishProgress("Success");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            publishProgress("Error");
        } catch (IOException e) {
            e.printStackTrace();
            publishProgress("Error");
        } catch (JSONException e) {
            e.printStackTrace();
            publishProgress("Error");
        }
        return localJSON;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Log.d(Prefs.LOG_TAG,values[0]);
    }
}