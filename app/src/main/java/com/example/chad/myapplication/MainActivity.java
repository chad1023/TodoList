package com.example.chad.myapplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listview;

//    private List<> items;
    private ArrayList<Item>data=new ArrayList<Item>();
//    private ArrayList<String> data = new ArrayList<String>();
//    private ArrayAdapter<String>adapter;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processView();
        processController();
//
//        data.add(new Item(1, new Date().getTime(), Colors.RED, "关于Android Tutorial的事情.", "Hello content", "", 0, 0, 0));
//        data.add(new Item(2, new Date().getTime(), Colors.BLUE, "一只非常可爱的小狗狗!", "她的名字叫“大热狗”，又叫\n作“奶嘴”，是一只非常可爱\n的小狗。", "", 0, 0, 0));

        data.add(new Item(new Date().getTime(),Colors.RED,"記事本","CONTENT","",0,0,0));
        //ListView
//        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        adapter=new ItemAdapter(this,R.layout.single_item,data);
        listview.setAdapter(adapter);




    }

    private void processView()
    {
        listview = (ListView)findViewById(R.id.list_item);
    }

    private void processController()
    {
        AdapterView.OnItemClickListener itemClickListener= new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this,data.get(i),Toast.LENGTH_LONG).show();

                Item item=data.get(i);
                Intent intent=new Intent("com.myapplication.EDIT_ITEM");
                intent.putExtra("position",i);//save the index of the data
                intent.putExtra("item",item);

                startActivityForResult(intent, 1);//1 for edit

            }
        };

        listview.setOnItemClickListener(itemClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    public void clickMenuItem(MenuItem item) {
        // 使用參數取得使用者選擇的選單項目元件編號
        int itemId = item.getItemId();

        // 判斷該執行什麼工作，目前還沒有加入需要執行的工作
        switch (itemId) {
//            case R.id.search_item:
//                break;
            case R.id.add_item:
                Intent intent=new Intent("com.myapplication.ADD_ITEM");
                startActivityForResult(intent, 0);//0 for add
                break;
            case R.id.setting:
                startActivity(new Intent(MainActivity.this,PrefActivity.class));
                break;

//            case R.id.revert_item:
//                break;
//            case R.id.delete_item:
//                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            String title=data.getStringExtra("title");

            Item item=(Item)data.getSerializableExtra("item");
//            Log.d("tag",title);
//            Log.d("tagrequestcode",Integer.toString(requestCode));
            if(requestCode==0) {
                this.data.add(item);
            }
            else if(requestCode==1){
                int position= data.getIntExtra("position",-1);
                if(position != -1){
                    this.data.set(position,item);
                }

            }
            // 通知資料已經改變，ListView元件才會重新顯示
            adapter.notifyDataSetChanged();

        }


    }

}
