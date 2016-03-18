package ua.com.kistudio.intentserviceexample;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import ua.com.kistudio.intentserviceexample.ui.FeedBackFragment;
import ua.com.kistudio.intentserviceexample.ui.ProfileFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int PREF_REQUEST = 101;
    JSONObject jsonObjectUser;

    // work with fragments
    ProfileFragment profileFragment;
    FeedBackFragment feedBackFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(JSONIntentService.SERVICE_ACTION_BROADCAST);
        this.registerReceiver(receiver, intentFilter);

        // work with fragments
        findViewById(R.id.btnFirstFragment).setOnClickListener(this);
        findViewById(R.id.btnSecondFragment).setOnClickListener(this);
        profileFragment = new ProfileFragment();
        feedBackFragment = new FeedBackFragment();
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
        JSONIntentService.getOneUser(this,2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Prefs.MENU_ITEM_SETTINGS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getTitle().equals(Prefs.MENU_ITEM_SETTINGS)){
            Log.d(Prefs.LOG_TAG,"Clicked on Settings");
            Intent intentPrefs = new Intent(this,MySettinsActivity.class);
            startActivityForResult(intentPrefs, PREF_REQUEST);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PREF_REQUEST:
                showPrefs();
                break;
        }
    }

    private void showPrefs() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Map<String,Object> prefsMap = (Map<String, Object>) sharedPreferences.getAll();

        for (String name: prefsMap.keySet()  ){
            Log.d(Prefs.LOG_TAG,name+" - "+prefsMap.get(name));
        }

    }

    int step = 1;

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnFirstFragment:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment,profileFragment)
                        .addToBackStack("profileAdded at step "+step)
                        .commit();
                break;
            case R.id.btnSecondFragment:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment,feedBackFragment)
                        .addToBackStack("feedBackAdded at step "+step)
                        .commit();
                break;
        }
        step++;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
