package com.music.android.sensilence.vidchuttiatyshi.zombi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.music.android.sensilence.R;
import com.music.android.sensilence.common.MusicPlayerActivity;
import com.music.android.sensilence.common.adapters.SongAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.github.turskyi.domain.entities.pojo.Song;

@AndroidEntryPoint
public class ZombiActivity extends AppCompatActivity {
    private MusicPlayerActivity musicPlayerActivity;
    private ListView listView;
    protected MediaPlayer mediaPlayer;

    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager audioManager;

    // Create an array of songs
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
                            listView,
                            songs,
                            audioManager,
                            ZombiActivity.this
                    );
                }
            };

    private final AdapterView.OnItemClickListener secondClickListener =
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    musicPlayerActivity.onSecondClick(firstClickListener, listView);
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZombiViewModel viewModel = new ViewModelProvider(this)
                .get(ZombiViewModel.class);

        SongAdapter adapter = initView();

        initObservers(viewModel, adapter);

        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(firstClickListener);

        //Create and setup the {@link AudioManager} to request audio focus
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        musicPlayerActivity = new MusicPlayerActivity();
    }

    @NotNull
    private SongAdapter initView() {
        setContentView(R.layout.activity_song_list);

        setBackground();

        /*  Create an {@link SongAdapter}, whose data source is a list of {@link Song}s.
         * The adapter knows how to create list items for each item in the list. */
        SongAdapter adapter = new SongAdapter(this, R.color.category_zombi);

        listView.setAdapter(adapter);
        return adapter;
    }

    private void initObservers(ZombiViewModel viewModel, SongAdapter adapter) {
        // Create the observer which updates the UI.
        final Observer<List<Song>> songsObserver = albumSongs -> {
            // Update the UI
            if (songs.isEmpty()) {
                songs.addAll(albumSongs);
            }
            adapter.addAll(songs);
        };
        viewModel.getSongs().observe(this, songsObserver);

        // Create the observer which updates the UI.
        final Observer<String> errorObserver = error -> Toast.makeText(
                this,
                error,
                Toast.LENGTH_LONG
        ).show();

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getErrorMessage().observe(this, errorObserver);
    }

    private void setBackground() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                R.drawable.zombi_txt);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmp);
        listView = findViewById(R.id.list_view);
        listView.setBackground(bitmapDrawable);
    }
}