package ua.com.kistudio.intentserviceexample.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ua.com.kistudio.intentserviceexample.R;

/**
 * Created by Вiталя on 16.03.2016.
 */



public class FragmentListView extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_view,container);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,new String[]{"first","second","third"});
        ListView listView = (ListView) v.findViewById(R.id.lvFragmentLV);
        listView.setAdapter(arrayAdapter);

        return v;
    }
}
