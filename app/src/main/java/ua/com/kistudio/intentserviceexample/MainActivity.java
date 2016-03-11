package ua.com.kistudio.intentserviceexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    JSONObject jsonObjectUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(JSONIntentService.SERVICE_ACTION_BROADCAST);
        this.registerReceiver(receiver, intentFilter);

    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(JSONIntentService.SERVICE_ACTION_BROADCAST)){
                String res = intent.getStringExtra(JSONIntentService.EXTRA_RESULT);
                TextView tvOut = (TextView) findViewById(R.id.tvOut);
                tvOut.setText(res);
            };
        }
    };

    // Click btnLoad listener
    public void loadButton(View view){
       /* AsyncJsonQuery asyncJsonQuery = new AsyncJsonQuery();
        asyncJsonQuery.execute(Prefs.TEST_URL);
        TextView tvOut = (TextView) findViewById(R.id.tvOut);
        String userInfo = "Unknown";
        try {
            jsonObjectUser = asyncJsonQuery.get();
            userInfo = String.format("%s %s - %s",
                    jsonObjectUser.getString("first_name"),
                    jsonObjectUser.getString("last_name"),
                    jsonObjectUser.getString("birthday"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tvOut.setText(userInfo);*/
        JSONIntentService.getOneUser(this,3);
    }

}
