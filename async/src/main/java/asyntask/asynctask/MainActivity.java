package asyntask.asynctask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String IMAGE_URL = "http://cityfinder.esy.es/img/5.jpg";
    public static final String LOG_NAME = "My_logs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //LoadImage();

        //init ListView
        ListView LvOut = (ListView) findViewById(R.id.lvList);
        AsyncLodArrayImage asyncLodArrayImage = new AsyncLodArrayImage();

        asyncLodArrayImage.execute( "http://cityfinder.esy.es/img/1.jpg",
                "http://cityfinder.esy.es/img/2.jpg");
        ImageAdapter imageAdapter = null;
        try {
            imageAdapter = new ImageAdapter(this,asyncLodArrayImage.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //LvOut.setAdapter(imageAdapter);
        LvOut.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,new String[]{"first","second","third"}));
        ((Button) findViewById(R.id.btnIntent)).setOnClickListener(this);
/*
        ImageView ivLoadImage = (ImageView) findViewById(R.id.IvLoadImage);
        TextView TvOut = (TextView) findViewById(R.id.TvAdress);
        AsyncLoadImage asyncloadimage = new AsyncLoadImage();
        asyncloadimage.execute(IMAGE_URL);
        TvOut.setText(IMAGE_URL);
        try {
            ivLoadImage.setImageBitmap(asyncloadimage.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
*/
    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()){
            case R.id.btnIntent:
                i.setAction("com.example.i.secondapplication.START");
                //i.setAction(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                //i.setAction(Intent.ACTION_DIAL);
                startActivity(i);
                break;
        }
    }
}
