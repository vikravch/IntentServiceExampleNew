package asyntask.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by maksym on 25.02.16.
 */
public class AsyncLodArrayImage extends AsyncTask<String,Integer,ArrayList<Bitmap>>{
    ArrayList<Bitmap> bmArray = new ArrayList<>();
    @Override
    protected ArrayList<Bitmap> doInBackground(String... params) {
        int i = 1;
        for(String imageUrl:params){
            bmArray.add(load(imageUrl));
            publishProgress(i++);
        }
        return bmArray;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(MainActivity.LOG_NAME,"load image "+values[0]);
    }

    private Bitmap load(String imageUrlIn) {
        Bitmap bm = null; //отвечает за битовое хранение картинок

        try{
            URL imageUrl = new URL(imageUrlIn);
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
            Log.d(MainActivity.LOG_NAME, "IOException");
            e.printStackTrace();
        }
        return bm;
    }
}
