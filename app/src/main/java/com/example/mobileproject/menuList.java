package com.example.mobileproject;

import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class menuList extends AppCompatActivity{
    private ListView listView1;
    private ListView listView2;
    private ListView listView3;

    private int position;
    NyomDB helper;
    SQLiteDatabase db;
    ArrayList<String> DBList;

    Mapss fragment_maps;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        EditText lotId_input = (EditText) findViewById(R.id.editTextSearch);
        lotId_input.setPrivateImeOptions("defaultInputmode=korean; ");

        final String[] listMenu1 = getResources().getStringArray(R.array.food1);
        final String[] listMenu2 = getResources().getStringArray(R.array.food2);
        final String[] listMenu3 = getResources().getStringArray(R.array.food3);
        final String[] listContent = getResources().getStringArray(R.array.contents_array);

        listView1 = (ListView) findViewById(R.id.menu1);
        listView2 = (ListView) findViewById(R.id.menu2);
        listView3 = (ListView) findViewById(R.id.menu3);

        CustomAdapter adapter1 = new CustomAdapter(this, listMenu1);
        listView1.setAdapter(adapter1);
        CustomAdapter adapter2 = new CustomAdapter(this, listMenu2);
        listView2.setAdapter(adapter2);
        CustomAdapter adapter3 = new CustomAdapter(this, listMenu3);
        listView3.setAdapter(adapter3);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(menuList.this, MainActivity.class);
                String item = (String)parent.getItemAtPosition(position);
                intent.putExtra("titleString", item);
                startActivity(intent);
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(menuList.this, MainActivity.class);
                String item = (String)parent.getItemAtPosition(position);
                intent.putExtra("titleString", item);
                startActivity(intent);
            }
        });
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(menuList.this, MainActivity.class);
                String item = (String)parent.getItemAtPosition(position);
                intent.putExtra("titleString", item);
                startActivity(intent);
            }
        });


        Button button2 = (Button)findViewById(R.id.LocationBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuList.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button)findViewById(R.id.RecoBtn);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(menuList.this, ThRoulette.class);
                startActivity(intent);
            }
       });


    }

    public ArrayList<String> viewDB(SQLiteDatabase db, String str){
        Cursor cursor;
        DBList = new ArrayList<String>();

        if( db != null){
            cursor= db.rawQuery("SELECT * FROM restaurants Where category = \'" + str + "\'",null);
            cursor.moveToFirst();
            while( ! cursor.isAfterLast() ) {
                DBList.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        return DBList;
    }
    public void SearchClick(View view) {
        Toast.makeText(getApplicationContext(), "검색기능 아직없어요", Toast.LENGTH_LONG).show();
    }
}

