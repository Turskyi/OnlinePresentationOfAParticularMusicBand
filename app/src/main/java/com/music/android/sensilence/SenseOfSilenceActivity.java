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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class SenseOfSilenceActivity extends AppCompatActivity {
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
      musicAlbum = new MusicAlbum();
        //Create and setup the {@link AudioManager} to request audio focus
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
                "https://mp3-tut.com/musictutplay?id=-22431441_132224076&hash=b27d97dfec746e70466bfbb112e3192559263fc27a4d1fa91c311b7ff82722db&artist=%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96&title=03+-+%D0%92%D0%BE%D1%81%D1%82%D0%B0%D0%BD%D0%BD%D1%94&download=1"));
        songs.add(new Song("відчуття.тиші", "Смак Мого Забуття", R.drawable.logo_black,
                "https://done.7cord.com/proxy?data=Y1I3RUczR0pVTE1WVklqSTB4VGdVOEViYjlZT0pPaFZaZ0FuVTNBbzlwalRLMWIybVA0ZlpOVTAyakJXV2MycisxZGxweGFRRVp2Q2xNSDM4RSs1cVNNOGZaSlFYVXFrR1k3d09YTzM1SEkwNVZSc2J5Q0pUeHg0TmQ3LzhhdUN1R090aFFJemVBMjRicHZsMTdsTEhrbkNTY21Qa2ZPMHFIK3g2S0QwY24rcnFOS3NoYXdTRURvaHZkRnpkZC9nMzdueDJ6MURaWmtGbSs0dlhFR1dTcGIwaWJwTWF1cnovdHlpS2NFTWlJUjhUSXBENVRyc0tNQnFlR1RlNW9qbk10Ylcwa1NsVXhVSGR5TWZFUGZBajVqK1lQY3hyUDFBT2c2UWlxRGNIeEFkblVmSWRxYnV5ZHF2TjgwU2Z6ZkxjZDMwRFJ1V3hwTndRSjZGczFkWGgvQThUUUUrT1F6UWVhK3R6MjRzZy90djNWYzh6Wndncm04YmVtTnJveXNwWG5CcW9haHBrSzJUNmhJZDQwWHRmRTFWdHlJWHVIUXoxbGVrYmU1a2VVbDZnSmtGR09rQ3I0ajlhMnQzSFJ6NlpITllsRG9BdVlKbjNYaVAvOEtoaXJ3aElySmFYM3dncGNaM0R0RDFvWHNjUGdXODkza2g0eWJQN2kxUWt2NWk"));
        songs.add(new Song("відчуття.тиші", "Промені", R.drawable.logo_black,
                "https://mp3-tut.com/musictutplay?id=55436575_159116004&hash=d19d4733acac5e0cd42d095104bc9169b02101e0eff2f243dad0aeb2f28b134d&artist=%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96&title=%D0%9F%D1%80%D0%BE%D0%BC%D0%B5%D0%BD%D1%96&download=1"));
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

    final AdapterView.OnItemClickListener firstClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            if (mMediaPlayer != null && imageView == view.findViewById(R.id.btn_image)) {
                musicAlbum.play( view, progressBar,  mMediaPlayer, mCompletionListener, secondClickListener, listView);
            } else {
                progressBar = view.findViewById(R.id.loading_spinner);
                progressBar.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    public void run() {
                        //do time consuming operations
                        if (musicAlbum.isOnline()) {
                            //Get the {@link Word} object at the given position the user clicked on
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
//                with the current song
                                String url = song.getmAudioResourceId();// your URL here
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
                                    musicAlbum.errorAlert( song, SenseOfSilenceActivity.this);
                                }
                                //  Start the audio file
                                musicAlbum.play( view, progressBar,  mMediaPlayer, mCompletionListener, secondClickListener, listView);
                            }
                        } else {
                            SenseOfSilenceActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(SenseOfSilenceActivity.this,
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