package com.music.android.sensilence.senseofsilence;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.music.android.sensilence.R;
import com.music.android.sensilence.databinding.FragmentSenseOfSilenceBinding;

import org.jetbrains.annotations.NotNull;

import io.github.turskyi.domain.entities.enums.Album;

public class SenseOfSilenceFragment extends Fragment {
    public static final String EXTRA_ALBUM = "com.music.android.sensilence.EXTRA_ALBUM";
    private FragmentSenseOfSilenceBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSenseOfSilenceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull @NotNull View view,
            @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        /* Set a click listener on that View
         * The code in this method will be executed when the sense of silence album is clicked on. */
        binding.titleSenseOfSilence.setOnClickListener(v -> {
            Toast.makeText(
                    v.getContext(),
                    getString(R.string.sensilence_sense_of_silence_songs),
                    Toast.LENGTH_SHORT
            ).show();
            // Create a new intent to open the {@link SenseOfSilenceActivity}
            Intent senseOfSilenceIntent = new Intent(getActivity(), SenseOfSilenceActivity.class);
            senseOfSilenceIntent.putExtra(EXTRA_ALBUM, Album.SENSE_OF_SILENCE_LP);

            // Start the new activity
            startActivity(senseOfSilenceIntent);
        });

        // Set a click listener on that View
        binding.tvZombi.setOnClickListener(new View.OnClickListener() {
            /** The code in this method will be executed when the zombi album is clicked on. */
            @Override
            public void onClick(View view) {
                //pop up message with description of a next page
                Toast.makeText(
                        view.getContext(),
                        getString(R.string.sensilence_zombi_songs),
                        Toast.LENGTH_SHORT
                ).show();
                // Create a new intent to open the {@link ZombiActivity}
                Intent zombiIntent = new Intent(getActivity(), SenseOfSilenceActivity.class);
                zombiIntent.putExtra(EXTRA_ALBUM, Album.ZOMBI);
                // Start the new activity
                startActivity(zombiIntent);
            }
        });

        // The code in this method will be executed when the "crime" album is clicked on.
        binding.titleCrime.setOnClickListener(v -> {
            Toast.makeText(
                    v.getContext(),
                    getString(R.string.sensilence_crime_songs),
                    Toast.LENGTH_SHORT
            ).show();

            Intent crimeIntent = new Intent(getActivity(), SenseOfSilenceActivity.class);
            crimeIntent.putExtra(EXTRA_ALBUM, Album.CRIME);
            // Start the new activity
            startActivity(crimeIntent);
        });

        binding.fabBonus.setOnClickListener(v -> {
            /* The handler code here.
             * Creating a new intent to open the {@link SenseOfSilenceActivity} */
            Intent senseOfSilenceIntent = new Intent(getActivity(), SenseOfSilenceActivity.class);
            senseOfSilenceIntent.putExtra(EXTRA_ALBUM, Album.BONUS);

            // Start the new activity
            startActivity(senseOfSilenceIntent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
