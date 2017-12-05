package com.example.samsung.a216230037_2;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TextView;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TabHost tabHost;
    int value = 0;
    private FrameLayout framelayout;
    private GoogleMap mMap;



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        framelayout = (FrameLayout) findViewById(R.id.frameLayout1);
        setUpMapIfNeeded();









        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "MoneyBook.db", null, 1);

        // 테이블에 있는 모든 데이터 출력
        final TextView result = (TextView) findViewById(R.id.result);

        final EditText etDate = (EditText) findViewById(R.id.date);
        final EditText etItem = (EditText) findViewById(R.id.item);
        final EditText etPrice = (EditText) findViewById(R.id.price);

        // 날짜는 현재 날짜로 고정
        // 현재 시간 구하기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        // 출력될 포맷 설정
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        etDate.setText(simpleDateFormat.format(date));

        // DB에 데이터 추가
        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = etDate.getText().toString();
                String item = etItem.getText().toString();
                int price = Integer.parseInt(etPrice.getText().toString());

                dbHelper.insert(date, item, price);
                result.setText(dbHelper.getResult());
            }

        });
        // DB에 있는 데이터 수정
        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();
                int price = Integer.parseInt(etPrice.getText().toString());

                dbHelper.update(item, price);
                result.setText(dbHelper.getResult());
            }
        });

        // DB에 있는 데이터 삭제
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();

                dbHelper.delete(item);
                result.setText(dbHelper.getResult());
            }
        });

        // DB에 있는 데이터 조회
        Button select = (Button) findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(dbHelper.getResult());
            }
        });


        tabHost = (TabHost) findViewById(R.id.tabhost);

        tabHost.setup();


        TabHost.TabSpec classa = tabHost.newTabSpec("1").setContent(R.id.class_).setIndicator("스레드");

        TabHost.TabSpec name = tabHost.newTabSpec("2").setContent(R.id.name).setIndicator("데이터베이스");

        TabHost.TabSpec number = tabHost.newTabSpec("3").setContent(R.id.number).setIndicator("지도");


        tabHost.addTab(classa);

        tabHost.addTab(name);

        tabHost.addTab(number);
    }

    @Override

    protected void onResume() {

        super.onResume();

        setUpMapIfNeeded();

    }



    private void setUpMapIfNeeded() {

        if (mMap != null) {

            return;

        }

        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        if (mMap == null) {

            return;

        }

        // Initialize map options. For example:

        // mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Random random = new Random();
            int color = 0;
            color = Color.rgb(random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255));
            framelayout.setBackgroundColor(color);
        }
        return super.onTouchEvent(event);
    }






}


















