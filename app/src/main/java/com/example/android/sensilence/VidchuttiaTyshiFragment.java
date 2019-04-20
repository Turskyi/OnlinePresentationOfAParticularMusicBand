package com.example.android.sensilence;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class VidchuttiaTyshiFragment extends Fragment {

    public VidchuttiaTyshiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_vidchuttia_tyshi, container, false);

        // Find the View that shows the zombi category
        TextView zombi = rootView.findViewById(R.id.zombi);
        // Set a click listener on that View
        zombi.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the zombi category is clicked on.
            @Override
            public void onClick(View view) {
                //pop up message with description of a next page
                Toast.makeText(view.getContext(),
                        "Пісні з альбому Зомбі", Toast.LENGTH_SHORT).show();
                // Create a new intent to open the {@link ZombiActivity}
                Intent zombiIntent = new Intent(getActivity(), ZombiActivity.class);

                // Start the new activity
                startActivity(zombiIntent);
            }
        });
        // Find the View that shows the crime category
        TextView crime = rootView.findViewById(R.id.crime);

        // Set a click listener on that View
        crime.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the crime category is clicked on.
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        "Пісні з альбому Злочин", Toast.LENGTH_SHORT).show();
                // Create a new intent to open the {@link CrimeActivity}
                Intent crimeIntent = new Intent(getActivity(), CrimeActivity.class);
                // Start the new activity
                startActivity(crimeIntent);
            }
        });
        // Find the View that shows the sense of silence category
        TextView senseOfSilence = rootView.findViewById(R.id.sense_of_silence);

        // Set a click listener on that View
        senseOfSilence.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the sense of silence category is clicked on.
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        "Пісні з альбому Відчуття Тиші", Toast.LENGTH_SHORT).show();
                // Create a new intent to open the {@link SenseOfSilenceActivity}
                Intent senseOfSilenceIntent = new Intent(getActivity(),
                        SenseOfSilenceActivity.class);

                // Start the new activity
                startActivity(senseOfSilenceIntent);
            }
        });
        return rootView;
    }
}
