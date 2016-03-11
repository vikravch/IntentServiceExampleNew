package ua.com.kistudio.intentserviceexample;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class JSONIntentService extends IntentService {

    private static final String ACTION_GET_ONE = "ua.com.kistudio.intentserviceexample.action.GET_ONE";
    private static final String EXTRA_ID = "ua.com.kistudio.intentserviceexample.extra.ID";

    public static final String SERVICE_ACTION_BROADCAST
            = "ua.com.kistudio.intentserviceexample.action.BROADCAST";
    public static final String  EXTRA_RESULT
            = "ua.com.kistudio.intentserviceexample.extra.RESULT";

    public JSONIntentService() {
        super("JSONIntentService");
    }

    public static void getOneUser(Context context, int id){
        Intent intent = new Intent(context, JSONIntentService.class);
        intent.setAction(ACTION_GET_ONE);
        intent.putExtra(EXTRA_ID, id);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_ONE.equals(action)){
                int localId = intent.getIntExtra(EXTRA_ID,-1);
                handleActionGetOne(localId);
            }
        }
    }



    private void handleActionGetOne(int localId) {
        JSONObject jsonObjectUser;
        AsyncJsonQuery asyncJsonQuery = new AsyncJsonQuery();
        asyncJsonQuery.execute(Prefs.TEST_URL);
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

        Intent intent = new Intent(SERVICE_ACTION_BROADCAST);
        intent.putExtra(EXTRA_RESULT,userInfo);
        sendBroadcast(intent);
    }

}
