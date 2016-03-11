package ua.com.kistudio.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MyIntentService extends IntentService {

    private static final String ACTION_LOAD_USER = "ua.com.kistudio.intentservice.action.LOAD_USER";
    public static final String ACTION_RESULT = "ua.com.kistudio.intentservice.action.RESULT";
    private static final String EXTRA_ID = "ua.com.kistudio.intentservice.extra.ID";
    public static final String EXTRA_USER_INFO = "ua.com.kistudio.intentservice.extra.USER_INFO";

    public MyIntentService() {
        super("MyIntentService");
    }


    public static void actionLoadUser(Context context, int id){
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_LOAD_USER);
        intent.putExtra(EXTRA_ID,id);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_LOAD_USER.equals(action)){
                int id = intent.getIntExtra(EXTRA_ID,-1);
                loadUser(id);
            }
        }
    }

    private void loadUser(int id) {
        AsyncLoadJSON asyncLoadJSON = new AsyncLoadJSON();
        asyncLoadJSON.execute(String.format("http://cityfinder.esy.es/index.php?id=%d&type=id",id));
        JSONObject json = null;
        String userAbout = "Unknown";
        try {
            json = asyncLoadJSON.get();
            userAbout = String.format("%s %s - %s", json.getString("first_name"),
                    json.getString("last_name"), json.getString("birthday"));
            Log.d(MainActivity.LOG_TAG, userAbout);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(ACTION_RESULT);
        intent.putExtra(EXTRA_USER_INFO,userAbout);
        sendBroadcast(intent);
    }
}
