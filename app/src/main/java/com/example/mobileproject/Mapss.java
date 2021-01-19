package com.example.mobileproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapss extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {
    MapView sView = null;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        sView = view.findViewById(R.id.mapView);
        sView.onCreate(savedInstanceState);
        sView.getMapAsync(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        sView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        sView.onStop();

    }

    @Override
    public void onSaveInstanceState(@Nullable Bundle outState) {
        super.onSaveInstanceState(outState);
        sView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        sView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sView.onLowMemory();
    }

    //맵뷰 설정
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
        googleMap.setOnInfoWindowClickListener(this);
        //이삭토스트
        MarkerOptions Marker_Options2 = new MarkerOptions();
        Marker_Options2.title("이삭토스트");
        Marker_Options2.snippet("043-847-0095");
        Marker_Options2.position(location2);
        googleMap.addMarker(Marker_Options2).showInfoWindow();
        googleMap.setOnInfoWindowClickListener(this);
        //백봉
        MarkerOptions Marker_Options3 = new MarkerOptions();
        Marker_Options3.title("백봉");
        Marker_Options3.snippet("043-847-0095");
        Marker_Options3.position(location3);
        googleMap.addMarker(Marker_Options3).showInfoWindow();
        googleMap.setOnInfoWindowClickListener(this);
        //김군파닭
        MarkerOptions Marker_Options4 = new MarkerOptions();
        Marker_Options4.title("김군파닭");
        Marker_Options4.snippet("043-847-0095");
        Marker_Options4.position(location4);
        googleMap.addMarker(Marker_Options4).showInfoWindow();
        googleMap.setOnInfoWindowClickListener(this);
        //이춘봉인생치킨
        MarkerOptions Marker_Options5 = new MarkerOptions();
        Marker_Options5.title("이춘봉인생치킨");
        Marker_Options5.snippet("043-851-9233");
        Marker_Options5.position(location5);
        googleMap.addMarker(Marker_Options5).showInfoWindow();
        googleMap.setOnInfoWindowClickListener(this);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location1, 17));//저숫자는 지도확대하는거임

        //맵에 마커표시, 인포윈도우 보여줌
        //googleMap.addMarker(marker).showInfoWindow();

        //인포윈도우 클릭
        //googleMap.setOnInfoWindowClickListener(this);

        //맵뷰 카메라위치, 줌 설정
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(solnae, 13));
    }


    //인포윈도우 클릭 리스너
    @Override
    public void onInfoWindowClick(Marker marker) {

    }
}
