package com.music.android.sensilence;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SenseOfSilenceActivity extends AppCompatActivity {
    MusicAlbum musicAlbum;
    ListView listView;
    protected MediaPlayer mMediaPlayer;

    /*Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    musicAlbum.onFocusChange(focusChange, mMediaPlayer);
                }
            };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            musicAlbum.releaseMediaPlayer();
        }
    };

    // Create an array of songs
    final ArrayList<Song> songs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);
        musicAlbum = new MusicAlbum();
        /* Create and setup the {@link AudioManager} to request audio focus */
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        listView = findViewById(R.id.list);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                R.drawable.logo_white);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmp);
        bitmapDrawable.setGravity(Gravity.NO_GRAVITY);
        listView.setBackground(bitmapDrawable);

        // Create a list of songs
        songs.add(new Song("відчуття.тиші", "Зима", R.drawable.logo_black,
                "https://m.z1.fm/download/5669117?dwn=1&download=force"));
        songs.add(new Song("відчуття.тиші", "Noli Respicere", R.drawable.logo_black,
                "https://m.z1.fm/download/6565442?dwn=1&download=force"));
        songs.add(new Song("відчуття.тиші", "Востаннє", R.drawable.logo_black,
                "https://m.z1.fm/download/5663956?dwn=1&download=force"));
        songs.add(new Song("відчуття.тиші", "Смак Мого Забуття",
                R.drawable.logo_black,
                "https://m.z1.fm/download/5836923?dwn=1&download=force"));
        songs.add(new Song("відчуття.тиші", "Промені",
                R.drawable.logo_black,
                "https://m.z1.fm/download/5674456?dwn=1&download=force"));
        songs.add(new Song("відчуття.тиші", "Безодня", R.drawable.logo_black,
                "https://m.z1.fm/download/5587229?dwn=1&download=force"));
        songs.add(new Song("відчуття.тиші", "Не край", R.drawable.logo_black,
                "https://m.z1.fm/download/5831897?dwn=1&download=force"));
        songs.add(new Song("відчуття.тиші", "Знову Страх", R.drawable.logo_black,
                "https://m.z1.fm/download/5575558?dwn=1&download=force"));
        songs.add(new Song("відчуття.тиші", "Навпіл", R.drawable.logo_black,
                "https://m.z1.fm/download/5826157?dwn=1&download=force"));
        songs.add(new Song("відчуття.тиші", "Зап'ястя", R.drawable.logo_black,
                "https://m.z1.fm/download/5831553?dwn=1&download=force"));
        songs.add(new Song("відчуття.тиші", "Падаю", R.drawable.logo_black,
                "https://m.z1.fm/download/5853470?dwn=1&download=force"));
        songs.add(new Song("відчуття.тиші", "Навесні", R.drawable.logo_black,
                "https://m.z1.fm/download/5826346?dwn=1&download=force"));
        songs.add(new Song("відчуття.тиші", "Алєся", R.drawable.logo_black,
                "https://m.z1.fm/download/5441247?dwn=1&download=force"));
        // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(this, songs, R.color.category_crime);
        listView.setAdapter(adapter);
        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(firstClickListener);
    }

    AdapterView.OnItemClickListener firstClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            musicAlbum.onFirstClick(view, position, mOnAudioFocusChangeListener, mCompletionListener,
                    secondClickListener, listView, songs, mAudioManager,
                    SenseOfSilenceActivity.this);
        }
    };

    AdapterView.OnItemClickListener secondClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            musicAlbum.onSecondClick(firstClickListener, listView);
        }
    };
}