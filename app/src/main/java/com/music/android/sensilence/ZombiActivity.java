package com.music.android.sensilence;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ZombiActivity extends AppCompatActivity {
    MusicAlbum musicAlbum;
    ListView listView;
    protected MediaPlayer mMediaPlayer;

    /*Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    musicAlbum.onFocusChange(focusChange,mMediaPlayer);
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
        //Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        musicAlbum = new MusicAlbum();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                R.drawable.zombi_txt);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmp);
        listView = findViewById(R.id.list);
        listView.setBackground(bitmapDrawable);

        // Create a list of songs
        songs.add(new Song("відчуття.тиші", "Зомбі", R.drawable.zombi,
                "https://cs1.djbag.biz/download/36310921/bXBhNTVGU1RONXozNnlSZFM3MmFKRHJNaFRDdTdObFF4UHBkL3lIdW00ckQvcVNJbC8wd2trUHgxVDExb1d5bEN5N0hlRjhEK2pVLzRhU3hxZXhHajdZS2dpT1psMEtFRTVub1VlUjVXeFc3YWRIMzl3TXFlbjMybU55ZGdMVjA/Vdchuttya_Tish_Zomb_(djbag.biz).mp3"));
        songs.add(new Song("відчуття.тиші",
                "Зомбі (aContrari Post-Apocalyptic Dubstep Mix)", R.drawable.vt_dnb120,
                "https://cs1.djbag.biz/download/23875604/bXBhNTVGU1RONXozNnlSZFM3MmFKRHJNaFRDdTdObFF4UHBkL3lIdW00cVRJVlRBK2ZFbzhwQ0orbWdRdmpFOWN5TFB2NnpsVSsvZ2xDRUdDam0rSThSTEJvYkxrL09jUFpWSTkxN21qTlEycFcwaE81aTc0d29iZ3RQditCTkI/Vdchuttya_Tish_Zomb_aContrari_post_apocalyptic_dubstep_mix_(djbag.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "ゾンビ", R.drawable.zombi,
                "https://cs1.mp3ix.net/download/106038284/UkdTdnp6ditxVXdEU0FqbUt1eFo2T21kN0lFWlQzbjNZcGRuaEpoMXU2a0JVdEp3dmdwUGtIMGN0UFl2YlVQa0phVVBDZ1NSc21YeWZrSStZdG00MURLUWNDS2N3V2hOUytYeEtXUkNiSmFJRmhVNE9TN1UyTXlrVjJ1T1pNbkg/Vdchuttya_Tish_(mp3ix.net).mp3"));
        // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(this, songs, R.color.category_zombi);
        listView.setAdapter(adapter);
        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(firstClickListener);
    }

    AdapterView.OnItemClickListener firstClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            musicAlbum.onFirstClick( view, position, mOnAudioFocusChangeListener, mCompletionListener, secondClickListener, listView, songs, mAudioManager,ZombiActivity.this);
        }
    };

    AdapterView.OnItemClickListener secondClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            musicAlbum.onSecondClick(firstClickListener,listView);
        }
    };
}