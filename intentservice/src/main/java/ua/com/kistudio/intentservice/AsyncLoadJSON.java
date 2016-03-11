package ua.com.kistudio.intentservice;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Вiталя on 10.03.2016.
 */
public class AsyncLoadJSON extends AsyncTask<String,Void,JSONObject> {

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject localJSON = null;
        URL url = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (is.read(b)!=-1){
                baos.write(b);
            }
            String JSONString = new String(baos.toByteArray());
            String clearStringJSON = JSONString.substring(0,JSONString.lastIndexOf("}")+1);
           // Log.d(MainActivity.LOG_TAG, "JSONString - "+clearStringJSON);
            localJSON = new JSONObject(clearStringJSON);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return localJSON;
    }
}