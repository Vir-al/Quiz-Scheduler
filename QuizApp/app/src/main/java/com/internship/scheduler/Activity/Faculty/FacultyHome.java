package com.internship.scheduler.Activity.Faculty;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CalendarView;
import android.widget.TextView;

import com.internship.scheduler.API.TalkToDatabase;
import com.internship.scheduler.Adapter.CalendarAdapter;
import com.internship.scheduler.Entity.CalendarEvent;
import com.internship.scheduler.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FacultyHome extends AppCompatActivity implements OnDateSelectedListener {

    RecyclerView recyclerView;
    CalendarAdapter adapter;
    List<CalendarEvent> eventItems;
    MaterialCalendarView calendarView;
    TextView todaysDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home);


// floating button : https://github.com/yavski/fab-speed-dial/blob/master/README.md
// Calendar Day : https://prolificinteractive.github.io/material-calendarview/com/prolificinteractive/materialcalendarview/CalendarDay.html
// Calendar View : https://github.com/prolificinteractive/material-calendarview/blob/master/README.md


        todaysDate=findViewById(R.id.date_today);
        calendarView=findViewById(R.id.calendarView);

        calendarView.setOnDateChangedListener(this);
        calendarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);

        Calendar instance=Calendar.getInstance();
        calendarView.setSelectedDate(instance);

        CalendarDay cd=new CalendarDay(instance);
        todaysDate.setText("You are Viewing : "+cd.getDay()+"-"+(cd.getMonth()+1)+"-"+cd.getYear());

        eventItems=new ArrayList<>();
        for(int i=1;i<5;i++)
        {
            Date firstDate1 = new Date(2018,07,06);
            CalendarEvent calendarEvent=new CalendarEvent(i,"MP IA"+i,"Mobile Programming","Module 1&2",firstDate1,"3:30 PM to 3:45 PM","312,309,315,317",123,"SVJ Ma'am");
            eventItems.add(calendarEvent);
        }

        recyclerView=findViewById(R.id.faculty_events_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter=new CalendarAdapter(eventItems, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
        String selected = calendarDay.getDay()+"-"+(calendarDay.getMonth()+1)+"-"+calendarDay.getYear();
        String toSend = calendarDay.getYear() + "-"+(calendarDay.getMonth()+1)+"-"+calendarDay.getDay();
        todaysDate.setText("You are Viewing : "+ selected);
        new TalkToDatabase(this,recyclerView,"Loading details for " + toSend).execute(toSend);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
