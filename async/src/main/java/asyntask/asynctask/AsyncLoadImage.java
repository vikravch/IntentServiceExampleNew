package asyntask.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by maksym on 23.02.16.
 */
public class AsyncLoadImage extends AsyncTask<String,Void,Bitmap> {     //String - входящие параметры,Void - промежуточные ,Bitmap - выходящие параметры
    @Override
    protected Bitmap doInBackground(String... params) {                 //String - входящие параметры
        Bitmap bm = null; //отвечает за битовое хранение картинок

        try{
            URL imageUrl = new URL(params[0]);
            URLConnection connection = imageUrl.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream); //загоняем ответ в буфер
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(MainActivity.LOG_NAME,"IOException");
            e.printStackTrace();
        }
        return bm;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(MainActivity.LOG_NAME,"onPreExecute");
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        Log.d(MainActivity.LOG_NAME, "onPostExecute");
    }
}
