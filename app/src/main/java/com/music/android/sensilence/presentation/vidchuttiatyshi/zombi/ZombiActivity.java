package com.music.android.sensilence.presentation.vidchuttiatyshi.zombi;

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

import androidx.appcompat.app.AppCompatActivity;

import com.music.android.sensilence.presentation.common.MusicPlayerActivity;
import com.music.android.sensilence.R;
import com.music.android.sensilence.domain.pojo.Song;
import com.music.android.sensilence.presentation.common.adapters.SongAdapter;

import java.util.ArrayList;

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
        setContentView(R.layout.activity_song_list);
        //Create and setup the {@link AudioManager} to request audio focus
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        musicPlayerActivity = new MusicPlayerActivity();

        setBackground();

        // fill up an album with a list of songs
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_zombi),
                        getString(R.string.song_name_zombi),
                        R.drawable.zombi,
                        getString(R.string.audio_zombi)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_zombi),
                        getString(R.string.song_name_zombi_dubstep),
                        R.drawable.vt_dnb120,
                        getString(R.string.audio_zombi_dubstep)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_zombi),
                        getString(R.string.song_name_japanese_zombie),
                        R.drawable.zombi,
                        getString(R.string.audio_japanese_zombie)
                )
        );
        /*  Create an {@link SongAdapter}, whose data source is a list of {@link Song}s.
         * The adapter knows how to create list items for each item in the list. */
        SongAdapter adapter = new SongAdapter(this, songs, R.color.category_zombi);
        listView.setAdapter(adapter);
        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(firstClickListener);
    }

    private void setBackground() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                R.drawable.zombi_txt);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmp);
        listView = findViewById(R.id.list_view);
        listView.setBackground(bitmapDrawable);
    }
}