package com.music.android.sensilence.zigmundafraid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.music.android.sensilence.R;
import com.music.android.sensilence.common.MusicPlayerActivity;
import com.music.android.sensilence.common.SongAdapter;
import com.music.android.sensilence.databinding.ActivitySongListBinding;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.github.turskyi.domain.entities.pojo.Song;

/**
 * A simple {@link Fragment} subclass.
 */
@AndroidEntryPoint
public class ZigmundAfraidFragment extends Fragment {

    private MusicPlayerActivity musicPlayerActivity;

    protected MediaPlayer mediaPlayer;

    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager audioManager;

    // Create an empty array of songs
    private final ArrayList<Song> songs = new ArrayList<>();

    private final AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    musicPlayerActivity.onFocusChange(focusChange, mediaPlayer);
                }
            };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private final MediaPlayer.OnCompletionListener completionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    musicPlayerActivity.releaseMediaPlayer();
                }
            };


    private final AdapterView.OnItemClickListener firstClickListener =
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(
                        AdapterView<?> parent,
                        final View view,
                        final int position,
                        long id
                ) {
                    musicPlayerActivity.onFirstClick(
                            view,
                            position,
                            onAudioFocusChangeListener,
                            completionListener,
                            secondClickListener,
                            binding.listView,
                            songs,
                            audioManager,
                            getActivity()
                    );
                }
            };

    private final AdapterView.OnItemClickListener secondClickListener =
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    musicPlayerActivity.onSecondClick(firstClickListener, binding.listView);
                }
            };
    private ActivitySongListBinding binding;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = ActivitySongListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        setBackground();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ZigmundAfraidViewModel viewModel = new ViewModelProvider(this)
                .get(ZigmundAfraidViewModel.class);

        /* Create an {@link SongAdapter}, whose data source is a list of {@link Song}s.
         * The adapter knows how to create list items for each item in the list. */
        SongAdapter adapter = new SongAdapter(getActivity(), R.color.colorPurple);
        binding.listView.setAdapter(adapter);

        initObservers(viewModel, adapter);

        //Set a click listener to play the audio when the list item is clicked on
        binding.listView.setOnItemClickListener(firstClickListener);

        musicPlayerActivity = new MusicPlayerActivity();

        //Create and setup the {@link AudioManager} to request audio focus
        audioManager = (AudioManager) requireActivity().getSystemService(Context.AUDIO_SERVICE);
    }

    private void initObservers(ZigmundAfraidViewModel viewModel, SongAdapter adapter) {
        // Create the observer which updates the UI.
        final Observer<List<Song>> songsObserver = albumSongs -> {
            // Update the UI
            if (songs.isEmpty()) {
                songs.addAll(albumSongs);
            }
            adapter.addAll(songs);
        };
        viewModel.getSongs().observe(getViewLifecycleOwner(), songsObserver);

        // Create the observer which updates the UI.
        final Observer<String> errorObserver = error -> Toast.makeText(
                getContext(),
                error,
                Toast.LENGTH_LONG
        ).show();

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorObserver);
    }

    private void setBackground() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.zigmund_afraid_cover);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmp);
        bitmapDrawable.setAlpha(100);
        binding.listView.setBackground(bitmapDrawable);
    }
}
