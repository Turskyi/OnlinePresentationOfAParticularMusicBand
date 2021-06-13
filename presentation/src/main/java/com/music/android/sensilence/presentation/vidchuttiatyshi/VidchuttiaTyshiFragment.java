package com.music.android.sensilence.presentation.vidchuttiatyshi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.music.android.sensilence.presentation.vidchuttiatyshi.crime.CrimeActivity;
import com.music.android.sensilence.R;
import com.music.android.sensilence.presentation.vidchuttiatyshi.sensofsilence.SenseOfSilenceActivity;
import com.music.android.sensilence.presentation.vidchuttiatyshi.zombi.ZombiActivity;

public class VidchuttiaTyshiFragment extends Fragment {

    public VidchuttiaTyshiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater
                .inflate(R.layout.activity_vidchuttia_tyshi, container, false);

        // Find the View that shows the zombi category
        TextView zombi = rootView.findViewById(R.id.zombi);
        // Set a click listener on that View
        zombi.setOnClickListener(new View.OnClickListener() {
            /** The code in this method will be executed when the zombi album is clicked on. */
            @Override
            public void onClick(View view) {
                //pop up message with description of a next page
                Toast.makeText(view.getContext(), getString(R.string.sensilence_zombi_songs), Toast.LENGTH_SHORT).show();
                // Create a new intent to open the {@link ZombiActivity}
                Intent zombiIntent = new Intent(getActivity(), ZombiActivity.class);

                // Start the new activity
                startActivity(zombiIntent);
            }
        });
        // Find the View that shows the crime album
        TextView crime = rootView.findViewById(R.id.title_crime);

        // Set a click listener on that View
        // The code in this method will be executed when the crime album is clicked on.
        crime.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), getString(R.string.sensilence_crime_songs), Toast.LENGTH_SHORT).show();
            // Create a new intent to open the {@link CrimeActivity}
            Intent crimeIntent = new Intent(getActivity(), CrimeActivity.class);
            // Start the new activity
            startActivity(crimeIntent);
        });
        // Find the View that shows the sense of silence album
        TextView senseOfSilence = rootView.findViewById(R.id.title_sense_of_silence);

        // Set a click listener on that View
        // The code in this method will be executed when the sense of silence category is clicked on.
        senseOfSilence.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), getString(R.string.sensilence_sense_of_silence_songs), Toast.LENGTH_SHORT).show();
            // Create a new intent to open the {@link SenseOfSilenceActivity}
            Intent senseOfSilenceIntent = new Intent(getActivity(),
                    SenseOfSilenceActivity.class);

            // Start the new activity
            startActivity(senseOfSilenceIntent);
        });
        return rootView;
    }
}
