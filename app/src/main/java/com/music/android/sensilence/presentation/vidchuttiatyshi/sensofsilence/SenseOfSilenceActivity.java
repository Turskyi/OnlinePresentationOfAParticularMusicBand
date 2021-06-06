package com.music.android.sensilence.presentation.vidchuttiatyshi.sensofsilence;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.music.android.sensilence.presentation.common.MusicPlayerActivity;
import com.music.android.sensilence.R;
import com.music.android.sensilence.domain.pojo.Song;
import com.music.android.sensilence.presentation.common.adapters.SongAdapter;

import java.util.ArrayList;

public class SenseOfSilenceActivity extends AppCompatActivity {
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
                            SenseOfSilenceActivity.this
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
        musicPlayerActivity = new MusicPlayerActivity();
        // Create and setup the {@link AudioManager} to request audio focus
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        setBackground();

        // fill up a list of songs
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_sense_of_silence),
                        getString(R.string.song_name_winter),
                        R.drawable.logo_black,
                        getString(R.string.audio_winter)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_sense_of_silence),
                        getString(R.string.song_name_noli_respicere),
                        R.drawable.logo_black,
                        getString(R.string.audio_noli_respicere)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_sense_of_silence),
                        getString(R.string.song_name_last_time),
                        R.drawable.logo_black,
                        getString(R.string.audio_last_time)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_sense_of_silence),
                        getString(R.string.song_name_taste_of_my_oblivion),
                        R.drawable.logo_black,
                        getString(R.string.audio_taste_of_my_oblivion)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_sense_of_silence),
                        getString(R.string.song_name_rays),
                        R.drawable.logo_black,
                        getString(R.string.audio_rays)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_sense_of_silence),
                        getString(R.string.song_name_abyss),
                        R.drawable.logo_black,
                        getString(R.string.audio_abyss)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_sense_of_silence),
                        getString(R.string.song_name_not_the_end),
                        R.drawable.logo_black,
                        getString(R.string.audio_not_the_end)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_sense_of_silence),
                        getString(R.string.song_name_fear_again),
                        R.drawable.logo_black,
                        getString(R.string.audio_fear_again)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_sense_of_silence),
                        getString(R.string.song_name_in_half),
                        R.drawable.logo_black,
                        getString(R.string.audio_in_half)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_sense_of_silence),
                        getString(R.string.song_name_wrist),
                        R.drawable.logo_black,
                        getString(R.string.audio_wrist)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_sense_of_silence),
                        getString(R.string.song_name_falling),
                        R.drawable.logo_black,
                        getString(R.string.audio_falling)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_sense_of_silence),
                        getString(R.string.song_name_in_the_spring),
                        R.drawable.logo_black,
                        getString(R.string.audio_in_the_spring)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.album_sense_of_silence),
                        getString(R.string.song_name_alesia),
                        R.drawable.logo_black,
                        getString(R.string.audio_alesia)
                )
        );
        /* Create an {@link SongAdapter}, whose data source is a list of {@link Song}s.
         * The adapter knows how to create list items for each item in the list. */
        SongAdapter adapter = new SongAdapter(this, songs, R.color.album_crime_color);
        listView.setAdapter(adapter);
        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(firstClickListener);
    }

    private void setBackground() {
        listView = findViewById(R.id.list_view);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo_white);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmp);
        bitmapDrawable.setGravity(Gravity.NO_GRAVITY);
        listView.setBackground(bitmapDrawable);
    }
}