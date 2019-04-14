package com.example.android.sensilence;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);
        // Find the View that shows the zombi category
        TextView zombi = findViewById(R.id.zombi);
        // Set a click listener on that View
        zombi.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the zombi category is clicked on.
            @Override
            public void onClick(View view) {
                //pop up message with description of a next page
                Toast.makeText(view.getContext(),
                        "Пісні з альбому Зомбі", Toast.LENGTH_SHORT).show();
                // Create a new intent to open the {@link ZombiActivity}
                Intent zombiIntent = new Intent(MainActivity.this, ZombiActivity.class);

                // Start the new activity
                startActivity(zombiIntent);
            }
        });
        // Find the View that shows the crime category
        TextView crime = findViewById(R.id.crime);

        // Set a click listener on that View
        crime.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the crime category is clicked on.
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        "Пісні з альбому Злочин", Toast.LENGTH_SHORT).show();
                // Create a new intent to open the {@link CrimeActivity}
                Intent crimeIntent = new Intent(MainActivity.this, CrimeActivity.class);
                // Start the new activity
                startActivity(crimeIntent);
            }
        });
        // Find the View that shows the sense of silence category
        TextView senseOfSilence = findViewById(R.id.sense_of_silence);

        // Set a click listener on that View
        senseOfSilence.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the sense of silence category is clicked on.
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        "Пісні з альбому Відчуття Тиші", Toast.LENGTH_SHORT).show();
                // Create a new intent to open the {@link SenseOfSilenceActivity}
                Intent senseOfSilenceIntent = new Intent(MainActivity.this,
                        SenseOfSilenceActivity.class);

                // Start the new activity
                startActivity(senseOfSilenceIntent);
            }
        });
        // Find the View that shows the zigmund afraid category
        TextView zigmundAfraid = findViewById(R.id.zigmund_afraid);

        // Set a click listener on that View
        zigmundAfraid.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the zigmund afraid  category is clicked on.
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        "Songs of Zigmund Afraid band", Toast.LENGTH_SHORT).show();
                // Create a new intent to open the {@link ZigmundAfraidActivity}
                Intent zigmundAfraidIntent = new Intent(MainActivity.this,
                        ZigmundAfraidActivity.class);
                // Start the new activity
                startActivity(zigmundAfraidIntent);
            }
        });
    }
}