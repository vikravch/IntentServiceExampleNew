package ua.com.kistudio.parcealable;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Вiталя on 15.03.2016.
 */
public class AlertDialogFragment extends DialogFragment implements View.OnClickListener {

    private Activity mActivity;

    public AlertDialogFragment(){}

    @SuppressLint("ValidFragment")
    public AlertDialogFragment(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dialog_alert,container);
        v.findViewById(R.id.btnSubmit).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        TextView textView = (TextView) mActivity.findViewById(R.id.tvContent);
        textView.setText("Dialog ok!!!");
        dismiss();
    }
}
