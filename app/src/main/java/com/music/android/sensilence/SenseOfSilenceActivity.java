package com.music.android.sensilence;

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
                R.raw.vidchuttjatishi_zima));
        songs.add(new Song("відчуття.тиші", "Noli Respicere", R.drawable.logo_black,
                R.raw.vidchuttjatishi_noli_respicere));
        songs.add(new Song("відчуття.тиші", "Востаннє", R.drawable.logo_black,
                R.raw.vidchuttjatishi_vostann));
        songs.add(new Song("відчуття.тиші", "Смак Мого Забуття",
                R.drawable.logo_black,
                R.raw.v_dchuttya_tish_smak_mogo_zabuttya));
        songs.add(new Song("відчуття.тиші", "Промені",
                R.drawable.logo_black,
                R.raw.v_dchuttya_tish_promen));
        songs.add(new Song("відчуття.тиші", "Безодня", R.drawable.logo_black,
                R.raw.v_dchuttya_tish_bezodnya));
        songs.add(new Song("відчуття.тиші", "Не край", R.drawable.logo_black,
                R.raw.v_dchuttya_tish_ne_kraj));
        songs.add(new Song("відчуття.тиші", "Знову Страх", R.drawable.logo_black,
                R.raw.v_dchuttya_tish_znovu_strah));
        songs.add(new Song("відчуття.тиші", "Навпіл", R.drawable.logo_black,
                R.raw.vidchuttjatishi_navpil));
        songs.add(new Song("відчуття.тиші", "Зап'ястя", R.drawable.logo_black,
                R.raw.v_dchuttya_tish_zapyastya));
        songs.add(new Song("відчуття.тиші", "Падаю", R.drawable.logo_black,
                R.raw.vidchuttjatishi_padaju));
        songs.add(new Song("відчуття.тиші", "Навесні", R.drawable.logo_black,
                R.raw.vidchuttjatishi_navesni));
        songs.add(new Song("відчуття.тиші", "Алєся", R.drawable.logo_black,
                R.raw.v_dchuttya_tish_alesya));
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