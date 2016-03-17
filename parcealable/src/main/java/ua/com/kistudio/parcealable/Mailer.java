package ua.com.kistudio.parcealable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Mailer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailer);
        Intent intent = getIntent();
        MyMail myMail = (MyMail) intent.getParcelableExtra("mail");

        if (myMail==null){
            Log.d("Mailer", "Is null");
        }

        TextView textView = (TextView) findViewById(R.id.tvMailer);
        textView.setText(
                String.format("title - %s, from - %s, to - %s, body - %s ",
                        myMail.getmTitle(),
                        myMail.getmFrom(),
                        myMail.getmTo(),
                        myMail.getmBody())
                );
    }
}
