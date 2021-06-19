package com.music.android.sensilence.vidchuttiatyshi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.music.android.sensilence.vidchuttiatyshi.crime.CrimeActivity;
import com.music.android.sensilence.R;
import com.music.android.sensilence.vidchuttiatyshi.sensofsilencelp.SenseOfSilenceLpActivity;
import com.music.android.sensilence.vidchuttiatyshi.zombi.ZombiActivity;

public class SenseOfSilenceFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater
                .inflate(R.layout.fragment_sense_of_silence, container, false);

        // Find the View that shows the zombi category
        TextView tvZombi = rootView.findViewById(R.id.tv_zombi);
        // Set a click listener on that View
        tvZombi.setOnClickListener(new View.OnClickListener() {
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
        TextView tvCrime = rootView.findViewById(R.id.title_crime);

        // Set a click listener on that View
        // The code in this method will be executed when the crime album is clicked on.
        tvCrime.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), getString(R.string.sensilence_crime_songs), Toast.LENGTH_SHORT).show();
            // Create a new intent to open the {@link CrimeActivity}
            Intent crimeIntent = new Intent(getActivity(), CrimeActivity.class);
            // Start the new activity
            startActivity(crimeIntent);
        });
        // Find the View that shows the sense of silence album
        TextView tvSenseOfSilence = rootView.findViewById(R.id.title_sense_of_silence);

        // Set a click listener on that View
        // The code in this method will be executed when the sense of silence category is clicked on.
        tvSenseOfSilence.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), getString(R.string.sensilence_sense_of_silence_songs), Toast.LENGTH_SHORT).show();
            // Create a new intent to open the {@link SenseOfSilenceLpActivity}
            Intent senseOfSilenceIntent = new Intent(getActivity(), SenseOfSilenceLpActivity.class);

            // Start the new activity
            startActivity(senseOfSilenceIntent);
        });
        return rootView;
    }
}
