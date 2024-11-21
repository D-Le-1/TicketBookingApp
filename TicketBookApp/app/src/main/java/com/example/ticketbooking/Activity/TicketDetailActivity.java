package com.example.ticketbooking.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.ticketbooking.Model.Flight;
import com.example.ticketbooking.R;
import com.example.ticketbooking.databinding.ActivityTicketDetailBinding;
import com.google.firebase.database.core.view.View;

public class TicketDetailActivity extends BaseActivity {
    private ActivityTicketDetailBinding binding;
    private Flight flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTicketDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();

    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(view -> finish());
        binding.fromTxt.setText(flight.getFromShort());
        binding.fromSmallTxt.setText(flight.getFrom());
        binding.ToTxt.setText(flight.getTo());
        binding.toShortTxt.setText(flight.getToShort());
        binding.toSmallTxt.setText(flight.getTo());
        binding.dateTxt.setText(flight.getDate());
        binding.timeTxt.setText(flight.getTime());
        binding.classTxt.setText(flight.getClassSeat());
        binding.priceTxt.setText("$"+ flight.getPrice());
        binding.airlineTxt.setText(flight.getAirlineName());
        binding.seatTxt.setText(flight.getPassager());

        Glide.with(TicketDetailActivity.this)
                .load(flight.getAirlineLogo())
                .into(binding.logo);
    }


    private void getIntentExtra() {
        flight= (Flight) getIntent().getSerializableExtra("flight");
    }
}