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
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class CrimeActivity extends AppCompatActivity {
    ImageView imageView;
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

        //Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        listView = findViewById(R.id.list);

        songs.add(new Song("відчуття.тиші", "До Астарти", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/64a95a47daa9/ead14b4c2829" +
                        "/b31996b77e2788855af79da85f7dc4c8-28516309-11f59c6d-1-" +
                        "15b9d922d175/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%94%D0%BE%20%D0%90%D1%81%D1%82%D0%B0%D1%80%D1%82%D0%B8.mp3", R.drawable.ic_play_arrow));
        songs.add(new Song("відчуття.тиші", "angelscream", R.drawable.logo,
                "https://cs1.mp3ix.net/download/106038282" +
                        "/QU8zeGtCNUsyRTRQZmZ5TUd6bnJDTHVKRnlQV3pjdU1TazhjUndnTmpNR1BmQTZCNDYzOVE1a2k2TGd2MHhUNmhLdC9uZTdjZzkxSEU3WFk3RlZzdjV5YmZpK0dMRHgxS2dUK2drNUtQUXlsN3dweFBSMndvQ1BYUE5uWmNGa3c" +
                        "/Vdchuttya_Tish_angelscream_(mp3ix.net).mp3", R.drawable.ic_play_arrow));
        songs.add(new Song("відчуття.тиші", "Зомбі (album version)", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab" +
                        "/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee" +
                        "/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3", R.drawable.ic_play_arrow));
        songs.add(new Song("відчуття.тиші", "Не хотів ", R.drawable.logo,
                "https://t4.bcbits.com/stream/a3cb17f6c16abe47f26ee22d9001ec93/mp3-128" +
                        "/1592747743?p=0&ts=1555231112&t=f814b7ec5be63fd1ee1097c9722092f6394cc1d8&token=" +
                        "1555231112_799345503a57104af93a5670cfd9c67133a7a726", R.drawable.ic_play_arrow));
        songs.add(new Song("відчуття.тиші", "Злочин", R.drawable.logo,
                "https://cdnet2.mixmuz.ru/4012d24567f2/5ffd9e61bbb4" +
                        "/b31996b77e2788855af79da85f7dc4c8-11923422d-11f59e62-1-c07ced7e512" +
                        "/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F" +
                        ".%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BB%D0%BE%D1%87%D0%B8%D0%BD.mp3", R.drawable.ic_play_arrow));
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

    AdapterView.OnItemClickListener firstClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (isOnline()) {

                //Get the {@link Song} object at the given position the user clicked on
                Song song = songs.get(position);

                //Release the media player if it currently exists.
                releaseMediaPlayer();
                imageView = findViewById(R.id.btn_image);
                imageView.setImageResource(R.drawable.ic_pause);
//               imageView = song.setmBtnResourceID(R.drawable.ic_pause);
//               imageView = song.getBtnResourceID();
//                song.setmBtnResourceID(R.drawable.ic_pause);

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
    public void onBackPressed() {
        if(mMediaPlayer.isPlaying()){
            mMediaPlayer.pause();
        }
        super.onBackPressed();
    }

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

            //Regardless of whether or not we were granted audio focus, abandon it. This also
            //unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}