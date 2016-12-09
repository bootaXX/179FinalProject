package com.example.mis.finalproject;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class
MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();

        ImageButton calendar = (ImageButton) findViewById(R.id.btn_calendar);
        calendar.setOnClickListener(new OnClickListenerOpenCalendar());

        ImageButton camera = (ImageButton) findViewById(R.id.btn_camera);
        camera.setOnClickListener(new OnClickListenerOpenCamera());
    }

    public class OnClickListenerOpenCalendar implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent  = new Intent(MainActivity.this, CalendarView.class);
            startActivity(intent);
        }
    }

    public class OnClickListenerOpenCamera implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        }
    }
}
