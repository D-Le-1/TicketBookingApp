package com.example.ticketbooking.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ticketbooking.Model.Location;
import com.example.ticketbooking.R;
import com.example.ticketbooking.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private int adultPassengers=1, childPassengers= 1;
    private SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private Calendar calendar=Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLocations();
        initPassengers();
        initClassSeat();
        initDatePickup();
        setVariable();
    }

    private void setVariable() {
        binding.searchBtn.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("from",((Location) binding.fromSp.getSelectedItem()).getName());
            intent.putExtra("to",((Location) binding.toSp.getSelectedItem()).getName());
            intent.putExtra("Date",binding.depatureDatetxt.getText().toString());
            intent.putExtra("numPassagers", adultPassengers + childPassengers);
            startActivity(intent);
        });
    }

    private void initDatePickup() {
        Calendar calendar=Calendar.getInstance();
        String currentDate=dateFormat.format(calendar.getTime());
        binding.depatureDatetxt.setText(currentDate);

        Calendar calendar1=Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_YEAR,1);
        String returnDate=dateFormat.format(calendar1.getTime());
        binding.returnDatetxt.setText(returnDate);

        binding.depatureDatetxt.setOnClickListener(v-> ShowDatePickerDialog(binding.depatureDatetxt));
        binding.returnDatetxt.setOnClickListener(v-> ShowDatePickerDialog(binding.returnDatetxt));
    }

    private void initClassSeat() {
        binding.progressClass.setVisibility(View.VISIBLE);
        ArrayList<String> list=new ArrayList<>();
        list.add("Immortal Class");
        list.add("Prime Class");
        list.add("Business Class");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this, R.layout.sp_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.classSp.setAdapter(adapter);
        binding.progressClass.setVisibility(View.GONE);
    }

    private void initPassengers() {
        binding.plsAdultBtn.setOnClickListener(v-> {
           adultPassengers++;
           binding.AdultTxt.setText(adultPassengers+" Adult");
        });

        binding.minusAdultBtn.setOnClickListener(v -> {
            if(adultPassengers>1) {
                adultPassengers--;
                binding.AdultTxt.setText(adultPassengers + " Adult");
            }
            });
        binding.plsChildBtn.setOnClickListener(v-> {
            childPassengers++;
            binding.ChildTxt.setText(childPassengers+" Child");
        });
        binding.minusChildBtn.setOnClickListener(v -> {
            if (childPassengers > 1) {
                childPassengers--;
                binding.ChildTxt.setText(childPassengers + " Child");
            }
        });
        }

    private void initLocations() {
        binding.progressBarFrom.setVisibility(View.VISIBLE);
        binding.progressBarTo.setVisibility(View.VISIBLE);
        DatabaseReference myRef=database.getReference("Locations");
        ArrayList<Location> list=new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(Location.class));
                    }
                    ArrayAdapter<Location> adapter=new ArrayAdapter<>(MainActivity.this, R.layout.sp_item,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.fromSp.setAdapter(adapter);
                    binding.toSp.setAdapter(adapter);
                    binding.fromSp.setSelection(1);
                    binding.progressBarFrom.setVisibility(View.GONE);
                    binding.progressBarTo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ShowDatePickerDialog(TextView textView){
        int year=calendar.get(calendar.YEAR);
        int month=calendar.get(calendar.MONTH);
        int day=calendar.get(calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this,(view, selectedYear,selectedMonth,selectedDay)->{
            calendar.set(selectedYear,selectedMonth,selectedDay);
            String formattedDate=dateFormat.format(calendar.getTime());
            textView.setText(formattedDate);
        }, year,month,day);
        datePickerDialog.show();
    }
}