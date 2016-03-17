package ua.com.kistudio.parcealable;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Вiталя on 15.03.2016.
 */
public class MailDialogFragment extends DialogFragment {

    View v;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.mail_dialog_layout,container);
        v.findViewById(R.id.btnMailSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMailSubmitClick();
            }
        });
        return v;
    }

    public void btnMailSubmitClick(){
        String titleText = ((EditText) v.findViewById(R.id.etTitle)).getText().toString();
        String fromText = ((EditText) v.findViewById(R.id.etFrom)).getText().toString();
        String toText = ((EditText) v.findViewById(R.id.etTo)).getText().toString();
        String bodyText = ((EditText) v.findViewById(R.id.etBody)).getText().toString();

        MyMail myMail = new MyMail(titleText,fromText,toText,bodyText);

        Intent intent = new Intent(getActivity(),Mailer.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("mail",myMail);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
