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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class ZombiActivity extends AppCompatActivity {
    MusicAlbum musicAlbum;
    ProgressBar progressBar;
   ImageView imageView;
    ListView listView;
    private MediaPlayer mMediaPlayer;

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
        musicAlbum = new MusicAlbum(mMediaPlayer);
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
                "https://done.7cord.com/proxy?data=VEV3VVJFbG1tSitadVk5REpFbGN1dzY0ak5qbUxDNjZ2cU9Zcm9EcWwrblFsaFdVNFlteVRtTXdRditUdjdhMjZ1S2N1NXFBSE1lUzhtOUREU3QzTW5yeVpta09KcGMrRmp5UHBnUFFrbG51QkVlVVgxWUxvbS90ZWUyQklvSHVuOWZNNnpUQUdYR25PYzk3cU1yeXA0WDB6aUk0OE1aL2xkc25WU05nSzhHdExPQTJTZ2MvMTl0cFR1Q1BBRE9kZXI3UlBZS0JCeFhBVndEWkVzU0pDNW44MzdvYU85dHBOYWhJYTN0TDhLTWJWd0lQdFRYNHZYSitobXRFQmJZV2lHY2FBaHFqRU1TaWJ2NkdnVlE1eU1TQWttdjhHQWNTK1F1M0I1VWZNTmQzQWt5ZmFYaCthalVueUNtOENRZ2o3Qy8xLzlQNE9taU9GMFM5MVpZQ2c2V001V2RNSmlibjdnNnZYb0IwQjJKZ2JaOEtyY0RWZVJUYlNpZUdIM0tkTWMxQWdkMTNMazE5bkRMN25idXJxYmx5L3F1bHg1V3hDUmxtSkk0MWYyeldZRlUyMFdwc2M5RWlnSXlyK1lTbXp0bHo4T29RY0hVN2JNY3V5MkcvYmc9PQ"));
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
            if (mMediaPlayer != null && imageView == view.findViewById(R.id.btn_image)) {
                musicAlbum.play(view,progressBar,mMediaPlayer,mCompletionListener,secondClickListener,listView);
            } else {
                progressBar = view.findViewById(R.id.loading_spinner);
                progressBar.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    public void run() {
                        //do time consuming operations
                        if (musicAlbum.isOnline()) {
                            //Get the {@link Song} object at the given position the user clicked on
                            final Song song = songs.get(position);

                            //Release the media player if it currently exists because we are about to
                            //play a different sound file.
                      musicAlbum.releaseMediaPlayer();
                            //Request audio focus for playback
                            int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                                    //Use the music stream.
                                    AudioManager.STREAM_MUSIC,
                                    //Request permanent focus.
                                    AudioManager.AUDIOFOCUS_GAIN);
                            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                                //We have an audio focus now.

//                Create and setup the {@link MedeaPlayer} for the audio resource associated
//                with the current word
                                String url = song.getmAudioResourceId(); // your URL here
                                mMediaPlayer = new MediaPlayer();
                                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                try {
                                    mMediaPlayer.setDataSource(url);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    mMediaPlayer.prepare(); // might take long! (for buffering, etc)
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    musicAlbum.errorAlert( song, ZombiActivity.this);
                                }
                                // Start the audio file
                                musicAlbum.play(view,progressBar,mMediaPlayer,mCompletionListener,secondClickListener,listView);
                            }
                        } else {
                            ZombiActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(ZombiActivity.this,
                                            "Немає інтернету", Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                    }
                }).start();
            }
        }
    };

    AdapterView.OnItemClickListener secondClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mMediaPlayer.pause();
            imageView = view.findViewById(R.id.btn_image);
            imageView.setImageResource(R.drawable.ic_play_arrow);
            listView.setOnItemClickListener(firstClickListener);
        }
    };
}