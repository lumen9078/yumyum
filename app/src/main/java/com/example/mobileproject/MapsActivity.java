package com.example.mobileproject;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private FragmentManager fragmentManager;
    private MapFragment mapFragment;

    Mapss fragment_maps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        fragmentManager=getFragmentManager();
        mapFragment=(MapFragment)fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);


        fragment_maps=new Mapss();

    }

    public void LocaClick(View view) {
        androidx.fragment.app.FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_frame, fragment_maps );
        transaction.commit();
    }
    public void RecoClick(View view) {
        Intent intent = new Intent(MapsActivity.this, ThRoulette.class);
        startActivity(intent);
    }
    public  void SearchClick(View view) {
        Toast.makeText(getApplicationContext(), "검색기능 아직없어요", Toast.LENGTH_LONG).show();
    }
    public void onClick( View view ) {
        finish();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location1 = new LatLng(36.96522834530849, 127.87176031088835);//맘스터치 위치
        LatLng location2 = new LatLng(36.96529105751086, 127.87150275860314);//이삭토스트 위치
        LatLng location3 = new LatLng(36.964593331189626, 127.87142181713219);//백봉 위치
        LatLng location4 = new LatLng(36.958613437454424, 127.87037963589817);//김군파닭 위치
        LatLng location5 = new LatLng(36.965433550482665, 127.871379058821);//이춘봉 위치

        //맘스터치
        MarkerOptions Marker_Options1 = new MarkerOptions();
        Marker_Options1.title("맘스터치");
        Marker_Options1.snippet("043-847-0095");
        Marker_Options1.position(location1);
        googleMap.addMarker(Marker_Options1).showInfoWindow();

        //이삭토스트
        MarkerOptions Marker_Options2 = new MarkerOptions();
        Marker_Options2.title("이삭토스트");
        Marker_Options2.snippet("043-847-0095");
        Marker_Options2.position(location2);
        googleMap.addMarker(Marker_Options2).showInfoWindow();

        //백봉
        MarkerOptions Marker_Options3 = new MarkerOptions();
        Marker_Options3.title("백봉");
        Marker_Options3.snippet("043-847-0095");
        Marker_Options3.position(location3);
        googleMap.addMarker(Marker_Options3).showInfoWindow();

        //김군파닭
        MarkerOptions Marker_Options4 = new MarkerOptions();
        Marker_Options4.title("김군파닭");
        Marker_Options4.snippet("043-847-0095");
        Marker_Options4.position(location4);
        googleMap.addMarker(Marker_Options4).showInfoWindow();

        //이춘봉인생치킨
        MarkerOptions Marker_Options5 = new MarkerOptions();
        Marker_Options5.title("이춘봉인생치킨");
        Marker_Options5.snippet("043-851-9233");
        Marker_Options5.position(location5);
        googleMap.addMarker(Marker_Options5).showInfoWindow();

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location1, 16));//저숫자는 지도확대하는거임
    }
}