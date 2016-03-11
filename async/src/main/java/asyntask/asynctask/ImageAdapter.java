package asyntask.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by maksym on 25.02.16.
 */
public class ImageAdapter extends BaseAdapter {
    Context context;
    ArrayList<Bitmap> bitmapArrayList;
    LayoutInflater inflater;
    public ImageAdapter(Context context, ArrayList<Bitmap> bitmapArrayListl) {
        this.bitmapArrayList = bitmapArrayListl;
        Log.d(MainActivity.LOG_NAME,"Size of array - "+bitmapArrayListl.size());
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bitmapArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return bitmapArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.item,parent,false);
        }
        ((ImageView)view.findViewById(R.id.ivItem)).setImageBitmap(bitmapArrayList.get(position));
        return view;
    }
}
