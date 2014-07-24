package net.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.fqjj.www.R;


/**
 * 左侧导航条
 * Created by joejoe on 2014/7/21.
 */
public class NavAdapter extends BaseAdapter {

    private Context context;
    LayoutInflater inflater;

    private final String[] titles = {"网易","百度", "新浪", "汽车之家"};


    public NavAdapter(Context ctx) {

        this.context = ctx;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.nav_item, null);
        ImageView img = (ImageView) layout.findViewById(R.id.index_image);
        TextView txt = (TextView) layout.findViewById(R.id.index_text);
        img.setImageResource(R.drawable.user);
        txt.setText(titles[position]);


        return layout;
    }


}
