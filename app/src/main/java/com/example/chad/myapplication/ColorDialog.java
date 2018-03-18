package com.example.chad.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by chad on 2018/3/18.
 */

public class ColorDialog extends AlertDialog.Builder implements View.OnClickListener {
    private Context mContext;
    private AlertDialog mAlertDialog;



    protected ColorDialog(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public AlertDialog show() {
        View color_dialog=LayoutInflater.from(mContext).inflate(R.layout.dialog_color,null);
        LinearLayout gallery=(LinearLayout)color_dialog.findViewById(R.id.color_gallery);
        for(Colors c:Colors.values()){
            Button button=new Button(mContext);
            button.setId(c.getColorsID());
            LinearLayout.LayoutParams layout=new LinearLayout.LayoutParams(128,128);
            layout.setMargins(6,6,6,6);
            button.setLayoutParams(layout);
            button.setBackgroundColor(c.getColorsID());
            button.setOnClickListener(this);
            gallery.addView(button);
        }
        setTitle("請選擇記事種類");
        setView(color_dialog);
        mAlertDialog = super.show();
        return mAlertDialog;
    }

    @Override
    public void onClick(View view) {
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(getContext());
        pref.edit().putInt("DEFAULT_COLOR",view.getId())
        .commit();
        mAlertDialog.cancel();
    }


}
