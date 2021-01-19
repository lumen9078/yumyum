package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.example.mobileproject.R.id.RatingBar;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private FragmentManager fragmentManager;
    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    NyomDB helper;
    SQLiteDatabase db;
    ListView lv;
    TextView restv;
    TextView teltv;
    RatingBar RatingBar;
    String[] result;
    String[] map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.listview);
        restv = (TextView) findViewById(R.id.resTextview);
        teltv = (TextView) findViewById(R.id.TelNumTextview);
        RatingBar = (RatingBar)findViewById(R.id.RatingBar);

        helper = new NyomDB(this, "mydb");
        db = helper.getWritableDatabase();

        Intent intent = getIntent();
        restv.setText(intent.getStringExtra("titleString"));
        ratingBarDB(intent);
        telphoneDB(intent);
        mapDB(intent.getStringExtra("titleString"));

        fragmentManager = getFragmentManager();
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        lv.setVisibility(View.INVISIBLE);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(mapFragment);
        ft.commit();

        //viewDB(intent);
    }

    public void viewDB(Intent intent){
        restv.setText(intent.getStringExtra("titleString"));
        ratingBarDB(intent);
        telphoneDB(intent);

        mapDB(intent.getStringExtra("titleString"));
    }

    public void mapDB(String str) {
        Cursor cursor = db.rawQuery("SELECT * FROM restaurants Where name = \'" + str + "\'",null);

        cursor.moveToNext();
        str = cursor.getString(2);
        map = str.split(",", 2);
    }

    public void telphoneDB(Intent intent) {
        String str = intent.getStringExtra("titleString");
        Cursor cursor = db.rawQuery("SELECT * FROM restaurants Where name = \'" + str + "\'",null);

        cursor.moveToNext();
        teltv.setText(cursor.getString(3));
    }

    public void ratingBarDB(Intent intent) {
        int rating = 0, number =0;
        String str = intent.getStringExtra("titleString");

        Cursor cursor;
        cursor= db.rawQuery("SELECT * FROM review Where id_res = \'" + str + "\'",null);
        while (cursor.moveToNext()) {      //다음 DB가 존재한다면 While문을 통해  CounterMember.Count 합을 저장한다
            rating += cursor.getInt(2);
            number += 1;
        }

        cursor.close();
        RatingBar.setRating(rating/number);
    }

    public void onClickMenu(View view) {
        // 메뉴로 넘어감
         lv.setVisibility(View.VISIBLE);
         FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
         ft.hide(mapFragment);
         ft.commit();

         Cursor cursor;
         String sql1 = "select * from menu where id_res like '%" + restv.getText() + "%'";

         cursor = db.rawQuery(sql1, null);

         int count = cursor.getCount();
         result = new String[count];

         for (int i = 0; i < count; i++) {
             cursor.moveToNext();
             String str_name = cursor.getString(1);
             String str_price = cursor.getString(2);

             result[i] = str_name + "\n" + str_price;
         }

         ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, result);

         lv.setAdapter(adapter);
    }

    public void onClickReview(View view){
        // 리뷰로 넘어감

        lv.setVisibility(View.VISIBLE);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(mapFragment);
        ft.commit();

        Cursor cursor;
        String sql1 = "select * from review where id_res like '%" + restv.getText() +"%'";

        cursor = db.rawQuery(sql1, null );

        int count = cursor.getCount();
        result = new String[count];

        for(int i = 0; i < count; i++) {
            cursor.moveToNext();
            String str_name = cursor.getString(1);

            result[i] = str_name;
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, result);

        lv.setAdapter(adapter);
    }

    public void onClickMap(View view){
        lv.setVisibility(View.GONE);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.show(mapFragment);
        ft.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng location = new LatLng(Float.parseFloat(map[0]), Float.parseFloat(map[1]));
        //LatLng location = new LatLng(36.963584334772094, 127.87132534256561);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title((String) restv.getText());
        markerOptions.position(location);

        mMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
    }
}