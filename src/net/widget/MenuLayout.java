package net.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by joejoe on 2014/7/20.
 */
public class MenuLayout extends RelativeLayout implements View.OnClickListener {


    public MenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setClickable(true);
        this.setEnabled(true);
        this.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

       // new AlertDialog.Builder(this.getContext()).setTitle("MenuLayout").show();

        //Toast.makeText(getContext(), "MenuLayout", Toast.LENGTH_SHORT).show();
    }
}
