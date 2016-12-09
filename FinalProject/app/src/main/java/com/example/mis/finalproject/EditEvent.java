package com.example.mis.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pauline Sarana on 12/10/2016.
 */

public class EditEvent  extends AppCompatActivity {
    EditText event_title;
    String event_title_string;
    EditText event_details;
    String event_details_string;
    DatePicker datepicker_start;
    int datepicker_start_day;
    int datepicker_start_month;
    int datepicker_start_year;
    DatePicker datepicker_end;
    int datepicker_end_day;
    int datepicker_end_month;
    int datepicker_end_year;
    TimePicker timestart;
    int timestart_hour;
    int timestart_min;
    TimePicker timeend;
    int timeend_hour;
    int timeend_min;
    Button addevent;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_event_event);

        event_title = (EditText) findViewById(R.id.edit_event_id);
        event_details = (EditText) findViewById(R.id.event_detail_id);

        datepicker_start = (DatePicker) findViewById(R.id.date_start_picker_id);
        Calendar calendar  = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datepicker_start.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener(){
            @Override
            public void onDateChanged(DatePicker datepicker, int year, int month, int dayofMonth) {
                datepicker_start_day = dayofMonth;
                datepicker_start_month = month + 1;
                datepicker_start_year = year;
            }
        });

        datepicker_end = (DatePicker) findViewById(R.id.date_end_picker_id);
        Calendar calendar1  = Calendar.getInstance();
        calendar1.setTimeInMillis(System.currentTimeMillis());
        datepicker_end.init(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener(){
            @Override
            public void onDateChanged(DatePicker datepicker, int year, int month, int dayofMonth) {
                datepicker_end_day = dayofMonth;
                datepicker_end_month = month + 1;
                datepicker_end_year = year;
            }
        });

        timestart = (TimePicker) findViewById(R.id.start_time_id);
        timestart.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int min) {
                timestart_min = min;
                timestart_hour = hour;
            }
        });

        timeend = (TimePicker) findViewById(R.id.end_time_id);
        timeend.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int min) {
                timeend_hour = hour;
                timeend_min = min;
            }
        });

        addevent = (Button) findViewById(R.id.add_event_button_id);
        addevent.setOnClickListener(new AddEvent.OnClickListenerAddEvent());
    }

    public class OnClickListenerAddEvent implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            event_title_string = event_title.getText().toString();
            event_details_string = event_details.getText().toString();
            String datetimeStart = dateConverter(datepicker_start_year,datepicker_start_month,datepicker_start_day,timestart_hour, timestart_min);
            String datetimeEnd = dateConverter(datepicker_end_year,datepicker_end_month,datepicker_end_day,timeend_hour, timeend_min);
            Toast.makeText(getApplicationContext(), datetimeStart + " - " + datetimeEnd, Toast.LENGTH_SHORT).show();
//            Toast.makeText(getApplicationContext(), event_title_string + ", " + event_details_string + "\n" + datepicker_start_month + " " + datepicker_start_day + ", " + datepicker_start_year + " - " + datepicker_end_month + " " + datepicker_end_day + ", " + datepicker_end_year +" \n" + timestart_hour + ":" + timestart_min + "\n" + timeend_hour + ":" + timeend_min, Toast.LENGTH_SHORT).show();
        }
    }

    public String dateConverter(int year, int month, int day, int hour, int min){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String yearStr = Integer.toString(year);
        String monthStr = Integer.toString(month);
        String dayStr = Integer.toString(day);
        String hourStr = Integer.toString(hour);
        String minStr =Integer.toString(min);
        String dateformat = new String();
        try {
            date = sdf.parse(yearStr + "-" + monthStr + "-" + dayStr + " " + hourStr + ":" + minStr);
            dateformat = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateformat;
    }
}
