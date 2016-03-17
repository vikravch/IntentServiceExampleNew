package ua.com.kistudio.intentserviceexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Вiталя on 11.03.2016.
 */
public class MySettinsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getResources().getString(R.string.key_pref_name_set))) {
            EditTextPreference editTextPrefs = (EditTextPreference) findPreference(key);
            editTextPrefs.setSummary(sharedPreferences.
                    getString(key, getResources().getString(R.string.summary_pref_name_set)));
        }else if (key.equals(getResources().getString(R.string.key_pref_time_set))){

            String hourAlarm = sharedPreferences.getString(key, "10");
            Log.d(Prefs.LOG_TAG,"hour - "+hourAlarm);
            Calendar current_day = new GregorianCalendar();
            current_day.setTimeInMillis(System.currentTimeMillis());

            Calendar cal = new GregorianCalendar();
            cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hourAlarm));
            cal.set(Calendar.MINUTE,current_day.get(Calendar.MINUTE));
            cal.set(Calendar.DAY_OF_YEAR,current_day.get(Calendar.DAY_OF_YEAR));
            cal.set(Calendar.DATE, current_day.get(Calendar.DATE));
            cal.set(Calendar.MONTH, current_day.get(Calendar.MONTH));

            Intent intent = new Intent(this, JSONIntentService.class);
            intent.setAction(JSONIntentService.ACTION_GET_ONE);
            intent.putExtra(JSONIntentService.EXTRA_ID, 2);
            PendingIntent pIntent = PendingIntent.getService(this,0,intent,0);
            AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 1000*60*60*24,pIntent);
        }
    }




}
