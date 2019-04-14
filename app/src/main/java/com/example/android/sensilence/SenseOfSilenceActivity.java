package com.example.android.sensilence;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class SenseOfSilenceActivity extends AppCompatActivity {
    ImageView imageView;
    TextView txtView1, txtView2;
    ListView listView;
    private MediaPlayer mMediaPlayer;

    /*Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        //The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus
                        //short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means
                        //our app is allowed to continue playing sound but at a lower volume.

                        //Pause playback and reset player to the start of the file. That way, when
                        //play the word from the beginning when we resume playback.
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //The AUDIOFOCUS_GAIN case means we have regained focus and can
                        //resume playback
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        //The AUDIOFOCUS_LOSS case means we've lost audio focus and
                        //stop playback and cleanup resources
                        releaseMediaPlayer();
                    }
                }
            };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    // Create an array of songs
    final ArrayList<Song> songs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

//        txtView1 = findViewById(R.id.sensilence_text_view);
//        txtView1.setTextColor(Color.BLACK);
//        txtView2 = findViewById(R.id.default_text_view);
//        txtView2.setTextColor(Color.BLACK);

        //Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        listView = findViewById(R.id.list);
        // Create a list of songs
        songs.add(new Song("відчуття.тиші", "Зима", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/a8e8422f5eee/b1786b39cb82/b31996b77e2788855af79da85f7dc4c8-" +
                        "90db721-a4c6465-1-18bed7fb3df5/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%B8%D0%BC%D0%B0.mp3"));
        songs.add(new Song("відчуття.тиші", "Noli Respicere", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/fc341055a001/94aac43d5085" +
                        "/b31996b77e2788855af79da85f7dc4c8-2934b1b-582213b-0-2e6b4558d07" +
                        "/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%2002%20-%20Noli%20Respicere.mp3"));
        songs.add(new Song("відчуття.тиші", "Востаннє", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/12e48eb2f3794/652e3465b630" +
                        "/b31996b77e2788855af79da85f7dc4c8-2934b1b-58221ce-0-f97b37b3511" +
                        "/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%2003%20" +
                        "-%20%D0%92%D0%BE%D1%81%D1%82%D0%B0%D0%BD%D0%BD%D1%94.mp3"));
        songs.add(new Song("відчуття.тиші", "Смак Мого Забуття", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/555a06228488/2a46c0ebc197" +
                        "/b31996b77e2788855af79da85f7dc4c8-3b9bd20-e2feb3b-1-1ef1ab43a46" +
                        "/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%A1%D0%BC%D0%B0%D0%BA%20%D0%9C%D0%BE%D0%B3%D0%BE%20%D0%97%D0%B0%D0%B1%D1%83%D1%82%D1%82%D1%8F.mp3"));
        songs.add(new Song("відчуття.тиші", "Промені", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/f5f9c2b74039/bfbe07c4bc57" +
                        "/b31996b77e2788855af79da85f7dc4c8-2934b1b-58222b3-0-f18e1abb344" +
                        "/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%2005%20-" +
                        "%20%D0%9F%D1%80%D0%BE%D0%BC%D0%B5%D0%BD%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Безодня", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/d58a191e62b2/339e35595143" +
                        "/b31996b77e2788855af79da85f7dc4c8-2934b1b-5822358-0-" +
                        "1705fea3d014/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%2006%20-%20%D0%91%D0%B5%D0%B7%D0%BE%D0%B4%D0%BD%D1%8F.mp3"));
        songs.add(new Song("відчуття.тиші", "Не край", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/1354a82816355/b7704af191f7/b31996b77e2788855af79da85f7dc4c8-" +
                        "2934b1b-58223c1-0-1168b4c96656/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%2007%20-%20%D0%9D%D0%B5%20%D0%BA%D1%80%D0%B0%D0%B9.mp3"));
        songs.add(new Song("відчуття.тиші", "Знову Страх", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/a5bf4cf6dd5/6466eab1ea37/b31996b77e2788855af79da85f7dc4c8-" +
                        "119233a73-11f59f26-1-7eb9411691/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BD%D0%BE%D0%B2%D1%83%20%D0%A1%D1%82%D1%80%D0%B0%D1%85.mp3"));
        songs.add(new Song("відчуття.тиші", "Навпіл", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/e87b6fe01b5d/f428bfb9b7d5" +
                        "/b31996b77e2788855af79da85f7dc4c8-2934b1b-582246c-0-84190495d72" +
                        "/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%2009%20-%20%D0%9D%D0%B0%D0%B2%D0%BF%D1%96%D0%BB.mp3"));
        songs.add(new Song("відчуття.тиші", "Зап'ястя", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/60827bde4579/1224d4260e70" +
                        "/b31996b77e2788855af79da85f7dc4c8-2934b1b-58224b7-0-c686eb2f2a2" +
                        "/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%2010%20" +
                        "-%20%D0%97%D0%B0%D0%BF%27%D1%8F%D1%81%D1%82%D1%8F.mp3"));
        songs.add(new Song("відчуття.тиші", "Падаю", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/6ae3a7e63a71/101a71290dcb1" +
                        "/b31996b77e2788855af79da85f7dc4c8-119175e15-11f59g27-1-1870561942f0" +
                        "/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%9F%D0%B0%D0%B4%D0%B0%D1%8E.mp3"));
        songs.add(new Song("відчуття.тиші", "Навесні", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/b45325d1b932/62110d96864c" +
                        "/b31996b77e2788855af79da85f7dc4c8-10d54a22-eb5d236-1-f12d7ae026b" +
                        "/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%9D%D0%B0%D0%B2%D0%B5%D1%81%D0%BD%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Алєся", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/506bdc55cc40" +
                        "/b31996b77e2788855af79da85f7dc4c8-1f6002b2-d9gdb5b-1-188e880db72d" +
                        "/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%90%D0%BB%D0%B5%D1%81%D1%8F.mp3"));
        // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(this, songs, R.color.category_crime);
        listView.setAdapter(adapter);
        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(firstClickListener);
    }

    /**
     * Checks the device is online or not
     */
    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
    final AdapterView.OnItemClickListener firstClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (isOnline()) {
                //Get the {@link Word} object at the given position the user clicked on
                Song song = songs.get(position);

                //Release the media player if it currently exists because we are about to
                //play a different sound file.
                releaseMediaPlayer();

                imageView = view.findViewById(R.id.btn_image);
                imageView.setImageResource(R.drawable.ic_pause);
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
                    }
                    //                Start the audio file
                    mMediaPlayer.start();

                    //Setup a listener on the media player, so that we can stop and release the
                    //media player once the sounds has finished
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                    listView.setOnItemClickListener(secondClickListener);
                } else {
                    Toast.makeText(view.getContext(),
                            "No internet", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    AdapterView.OnItemClickListener secondClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mMediaPlayer.pause();
            imageView.setImageResource(R.drawable.ic_play_arrow);
            listView.setOnItemClickListener(firstClickListener);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        //When the activity is stopped, release the media player resources because we won't
        //be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}