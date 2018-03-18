package com.example.chad.myapplication;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chad on 2018/3/15.
 */

public class ItemAdapter extends ArrayAdapter{
    private int resource;
    LayoutInflater inflater;


    public ItemAdapter(Context context, int resource, List items) {
        super(context,resource,items);
        this.resource=resource;
        this.inflater=LayoutInflater.from(context);
    }

    public static class ViewHolder{
        TextView item_title,item_date;
        RelativeLayout typeColor;

        public ViewHolder(View raw_view){
            item_title=(TextView)raw_view.findViewById(R.id.title_text);
            item_date=(TextView)raw_view.findViewById(R.id.date_text);
            typeColor=(RelativeLayout)raw_view.findViewById(R.id.type_color);
        }
        public void setView(Item item){
            item_title.setText(item.getTitle());
            item_date.setText(item.getLocaleDate());
            GradientDrawable background=(GradientDrawable)typeColor.getBackground();
            background.setColor(item.getColor().getColorsID());


        }



    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Item dataitem=(Item)getItem(position);
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            convertView=inflater.inflate(resource,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.setView(dataitem);

        return convertView;
    }
}
