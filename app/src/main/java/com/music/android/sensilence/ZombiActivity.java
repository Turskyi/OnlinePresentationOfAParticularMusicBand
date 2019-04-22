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
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class ZombiActivity extends AppCompatActivity {
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
                        //play the song from the beginning when we resume playback.
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

        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                R.drawable.zombi_txt);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmp);
//        bitmapDrawable.setAlpha(100);
        listView = findViewById(R.id.list);
        listView.setBackground(bitmapDrawable);

        // Create a list of songs
        songs.add(new Song("відчуття.тиші", "Зомбі", R.drawable.zombi,
                "https://storage.mp3cc.biz/listen/57951498/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6SU51a2I0SEluUUhGUzNKNDQxM2xLbm14cUF4cmNYaXlTYnNCa3o3VnNYd1F4SjN4Y3ZEb3JCSTJFWUowYkRjY3J4K0tGM2F4dkRZb1FvYklvRzFqZEk/v-dchuttya.tish-zomb_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші",
                "Зомбі (aContrari Post-Apocalyptic Dubstep Mix)", R.drawable.vt_dnb120,
                "https://storage.mp3cc.biz/listen/17574914/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6TFBhQmFNaDlqSGFYZDVqZEZRMWErS0xWeVJQamJwTlpKK3hMQUxqV2NDUkZ0Zkp4a0hTek5za0hYN1JvWjFmTUw0RmFwMVdGWGZGbkI1VXVmYWFMU0w/v-dchuttya.tish-zomb-acontrari-post-apocalyptic-dubstep-mix_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "ゾンビ", R.drawable.zombi,
                "https://storage.mp3cc.biz/listen/57951499/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6TFlKWUs5aW0raFZ3MmlJMFpiS3E1NVRYSUw2andvQS9QaHk4ZUVQVklkdjZYbFgzekYyTnlNL0lHY3BJdzFrRDFjbHVSZ3I1S1lGdHE2Z0R4NkRKRzk/v-dchuttya.tish-_(mp3CC.biz).mp3"));
        // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(this, songs, R.color.category_zombi);
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
//                with the current word
                    String url = song.getmAudioResourceId(); // your URL here
                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mMediaPlayer.setDataSource(url);
                    } catch (IOException e) {
                        Toast.makeText(view.getContext(),
                                "No internet", Toast.LENGTH_SHORT).show();
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