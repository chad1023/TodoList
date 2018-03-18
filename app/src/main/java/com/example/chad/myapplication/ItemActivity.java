package com.example.chad.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Date;

public class ItemActivity extends AppCompatActivity {

    EditText title_text,content_text;
    ImageButton picture_button,record_button,location_button,color_button,alarmbutton;
    Button confirm_button,cancel_button;

    // 启动功能用的请求代码
    private static final int START_CAMERA = 0;
    private static final int START_RECORD = 1;
    private static final int START_LOCATION = 2;
    private static final int START_ALARM = 3;
    private static final int START_COLOR = 4;


    private Item item;

    private String action;
    private View colorgallery;
    private AlertDialog dialog;
    private AlertDialog.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        processView();
        processController();

        colordialogSetup();


        //
        Intent intent=getIntent();
        action = intent.getAction();
        if(action.equals("com.myapplication.EDIT_ITEM")){
            item=(Item)intent.getSerializableExtra("item");

            title_text.setText(item.getTitle());
            content_text.setText(item.getContent());
        }
        else{
            item =new Item();
            SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
            int color=sharedPreferences.getInt("DEFAULT_COLOR",-1);
            item.setColor(getColors(color));
        }

    }

    private void colordialogSetup(){
        //alertdialog builder
        ColorListener colorListener=new ColorListener();
        LayoutInflater layoutInflater=LayoutInflater.from(ItemActivity.this);
        colorgallery=layoutInflater.inflate(R.layout.dialog_color,null);
        LinearLayout gallery=(LinearLayout)colorgallery.findViewById(R.id.color_gallery);


        for(Colors c:Colors.values()){
            Button button=new Button(this);
            button.setId(c.getColorsID());
            LinearLayout.LayoutParams layout=new LinearLayout.LayoutParams(128,128);
            layout.setMargins(6,6,6,6);
            button.setLayoutParams(layout);
            button.setBackgroundColor(c.getColorsID());
            button.setOnClickListener(colorListener);
            gallery.addView(button);
        }
        builder=new AlertDialog.Builder(ItemActivity.this);
        builder.setView(colorgallery);
        builder.setTitle("請選擇記事種類");

    }
    private void processView()
    {
        title_text=(EditText)findViewById(R.id.title_input);
        content_text=(EditText)findViewById(R.id.content_input);
        picture_button=(ImageButton)findViewById(R.id.picture);
        record_button=(ImageButton)findViewById(R.id.record);
        location_button=(ImageButton)findViewById(R.id.location);
        color_button=(ImageButton)findViewById(R.id.color);
        alarmbutton=(ImageButton)findViewById(R.id.alarm);

        confirm_button=(Button)findViewById(R.id.confirmbutton);
        cancel_button=(Button)findViewById(R.id.cancelbutton);

    }

    private void processController()
    {


        OnClickListener clicklistener=new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.picture:
                        break;
                    case R.id.record:
                        break;
                    case R.id.alarm:
                        break;
                    case R.id.location:
                        break;
                    case R.id.color:
                        dialog=builder.show();
                        break;
                }
            }
        };

        picture_button.setOnClickListener(clicklistener);
        record_button.setOnClickListener(clicklistener);
        location_button.setOnClickListener(clicklistener);
        color_button.setOnClickListener(clicklistener);
        alarmbutton.setOnClickListener(clicklistener);

        OnClickListener submit =new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.confirmbutton)
                {
                    String titletext=title_text.getText().toString();
                    String contenttext=content_text.getText().toString();


                    item.setTitle(titletext);
                    item.setContent(contenttext);

                    if(action.equals("com.myapplication.EDIT_ITEM")){
                        item.setLastModify(new Date().getTime());
                    }
                    else{
                        item.setDatetime(new Date().getTime());
                    }

                    Intent result=getIntent();
                    result.putExtra("title",titletext);
                    result.putExtra("content",contenttext);
//                    result.putExtra("datetime",new Date().getTime())
//                    int position=result.getIntExtra("position",-1);
//                    Log.d("tagitem",Integer.toString(position));

                    result.putExtra("item",item);
                    setResult(Activity.RESULT_OK,result);

                }
                finish();
            }
        };

        confirm_button.setOnClickListener(submit);
        cancel_button.setOnClickListener(submit);


    }
    private class ColorListener implements OnClickListener{

        @Override
        public void onClick(View view) {

            item.setColor(getColors(view.getId()));
            dialog.cancel();
        }
    }

    public static Colors getColors(int color){
        Colors result = Colors.LIGHTGREY;
        if(color == Colors.BLUE.getColorsID()) {
            result=Colors.BLUE;
        }
        else if(color == Colors.RED.getColorsID()) {
            result=Colors.RED;
        }
        else if(color == Colors.ORANGE.getColorsID()) {
            result=Colors.ORANGE;
        }
        else if(color == Colors.PURPLE.getColorsID()) {
            result=Colors.PURPLE;
        }
        else if(color == Colors.GREEN.getColorsID()) {
            result=Colors.GREEN;
        }
        return result;
    }


}
