package ua.com.kistudio.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "MyLogsIntent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyIntentService.ACTION_RESULT);
        registerReceiver(receiver,intentFilter);
    }

    // Listener button btnLoad
    public void onClickLoadButton(View v){
        MyIntentService.actionLoadUser(this,3);
    /*    AsyncLoadJSON asyncLoadJSON = new AsyncLoadJSON();
        asyncLoadJSON.execute("http://cityfinder.esy.es/index.php?id=1&type=id");
        JSONObject json = null;
        try {
            json = asyncLoadJSON.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        TextView tvOut = (TextView) findViewById(R.id.tvOut);
        try {
            tvOut.setText(String.format("%s %s - %s",json.getString("first_name")
                    ,json.getString("last_name"),json.getString("birthday")));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (MyIntentService.ACTION_RESULT.equals(action)){
                TextView tvOut = (TextView) findViewById(R.id.tvOut);
                tvOut.setText(intent.getStringExtra(MyIntentService.EXTRA_USER_INFO));
            }

        }
    };
}
