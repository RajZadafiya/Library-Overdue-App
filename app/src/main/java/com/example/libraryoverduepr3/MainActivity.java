package com.example.libraryoverduepr3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    CalendarView calendar;
    TextView charge;
    Button submit;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Raj PR3");

        calendar = findViewById(R.id.calendar);
        charge = findViewById(R.id.charge);
        submit = findViewById(R.id.submit);

        calendar
                .setOnDateChangeListener(
                        new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
                            {
                                date =  (month + 1) + "/" + dayOfMonth  + "/" + year;

//                                charge.setText(date);
                            }
                        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!date.equals("")){
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    String formattedDate = simpleDateFormat.format(c);
                    Log.d("DATE",simpleDateFormat.format(c));

//                    String CurrentDate= "09/24/2018";
//                    String FinalDate= "09/26/2019";

                    try {
                        Date date11 ;
                        Date date21;
                        date11 = simpleDateFormat.parse(date);
                        date21 = simpleDateFormat.parse(formattedDate);
                        long difference = Math.abs(date11.getTime() - date21.getTime());
                        long differenceDates = difference / (24 * 60 * 60 * 1000);
                        String dayDifference = Long.toString(differenceDates);
                        Toast.makeText(MainActivity.this, ""+dayDifference, Toast.LENGTH_SHORT).show();
                        Log.d("DATE",dayDifference);

                        if (!(Integer.valueOf(dayDifference) == 0)){
                                Float charges = Float.valueOf(dayDifference)*10;
                                charge.setText(charges.toString() + " INR.");
                            }else {
                            charge.setText("0 INR. \nPlease Return The Book Today Otherwise You Give Charges At Everyday ");
                            Toast.makeText(MainActivity.this, "Please Return The Book Today Otherwise You Give Charges At Everyday ", Toast.LENGTH_SHORT).show();
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
}