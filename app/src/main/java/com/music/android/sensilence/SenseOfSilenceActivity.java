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
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class SenseOfSilenceActivity extends AppCompatActivity {
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

        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                R.drawable.logo_white);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmp);
        bitmapDrawable.setGravity(Gravity.NO_GRAVITY);
        listView.setBackground(bitmapDrawable);


        // Create a list of songs
        songs.add(new Song("відчуття.тиші", "Зима", R.drawable.logo_black,
                "https://storage.mp3cc.biz/listen/99041062/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6S1doVTYwQS85NmJJU3hKcUdQUTN6RXJTazNrYnkyS3FhVm1pY09OZzRST09KS1FIaW5IeTRFOW1BOEZRb1NTcW9pZ0ROYkdpZnJnTHAxRE9zRzIyQlM/v-dchuttya-tish-zima_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Noli Respicere", R.drawable.logo_black,
                "https://storage.mp3cc.biz/listen/99041071/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6SWhvMGtNSTk2dXoxc0VvUVNzdXk3OUdIQncxUzhDdVNCVEtDWGV1UjFZdHUyeGZGOENWRzF5TFUyS25JdWFRT2diNm52VG9JL2VYRzNNQlVNZUpVZVk/v-dchuttya.tish-02-noli-respicere_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Востаннє", R.drawable.logo_black,
                "https://storage.mp3cc.biz/listen/99041067/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6S1pBNmNEYUdXVUw5QXU4UDdtMmpzMWxITTlvMkFteVlISG1HMUxiT1NUUWxvT1gzR0hjc3o5S2VEbElKYXZCajZoT3BpZ0p5Vmp1TFdZUitHSE5rbmw/v-dchuttya.tish-03-vostann_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Смак Мого Забуття", R.drawable.logo_black,
                "https://storage.mp3cc.biz/listen/99041073/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6S21adnV1WjR0V1NrWVNuQnBuRlpxVEVRUjJ1TjczQ3grUTh3Q3NiV3J3MTNRb0dXRTgrZlpuU3hxT054Y0x0aDBlNWw0V3pBMVc1UXZKVGRTRUlFVWs/v-dchuttya.tish-04-smak-mogo-zabuttya_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Промені", R.drawable.logo_black,
                "https://storage.mp3cc.biz/listen/99041075/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6TFFnWWhTWlZXeWtKdkFEYm53eGo5QWFUN1NNelhCVmtaU1JPWm1CbmdTU0w4enBuZk4wV2dDb0s1a3gzUFN5SUpNM0doSjZSRDlONkJxeWVqYXozSnM/v-dchuttya.tish-05-promen_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Безодня", R.drawable.logo_black,
                "https://storage.mp3cc.biz/listen/137055067/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6SjI3cW1WdDRoaUtUMUZxVUtNTVdIaDRxRkRvV2lvbzNnLzJFc0FsTVNJVit3V2ZuRHpxWkhTUitCQjRaWEJqU1N5em9jRTR4V3dMdm51akNFb3JpVDk/v-dchuttya.tish-bezodnya_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Не край", R.drawable.logo_black,
                "https://storage.mp3cc.biz/listen/57951507/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6STRsbHNENDFVQ21Ea1lpU09LR05pbWVqaFdWMU9ZaDZNc3JLUmZQZ1BSMXA0L05Sdm9PYmk4eERoWGNzV0tzZlJFTnFXWkJiUFB4N2hLL05aMzJkcW0/v-dchuttya.tish-ne-kraj_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Знову Страх", R.drawable.logo_black,
                "https://storage.mp3cc.biz/listen/99041069/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6TEo3MXRxVGdSejVIYjVjLzQwWGo1bmpBdmd5Zm5CTFpPaDZtUEVFVHR6aUlRd0FlMUV2VHZoOUZiaXpTbldLaHFISXM1K0M5aU1zNlA4S0RXTkYrWS8/v-dchuttya.tish-08-znovu-strah_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Навпіл", R.drawable.logo_black,
                "https://storage.mp3cc.biz/listen/99041064/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6SytFbDJqMkg2OWFTUjljRnlVR3lpbDg0ZER3cUptbHByOExuR3ByOUUrWlRPeFVTWHhQNVBKeU14YVFEbEhVOWw1UndkL1FvYmMrMDAxNVFYSEdvYmw/v-dchuttya.tish-09-navp-l_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Зап'ястя", R.drawable.logo_black,
                "https://storage.mp3cc.biz/listen/99041078/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6SWtLRVZSMU9UT0RJNTFNaHl0cXl1UDYvUmVORm8veUFlRU02SnZyNlA5Vit6Nm9rdlNiaVkweHFWbmpiR05lODJhcjA5b25oZkYzZm93YTF5SitUd00/v-dchuttya.tish-10-zap-yastya_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Падаю", R.drawable.logo_black,
                "https://storage.mp3cc.biz/listen/53515677/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6TDJBd2Nab2pQNUREbGI4eTNoRWNVUWZlS1RHemNBMlk4bFNudFY0RkZrY0NaQzlrdUFKSU5YRzFHL05EdzdHOTQ1LzlxTFdtWHhtMUVGanJtUTdQWGg/v-dchuttya.tish-padayu_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Навесні", R.drawable.logo_black,
                "https://storage.mp3cc.biz/listen/99041070/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6TGhueFo0NWVqaVY3SlZCWk4yYmNCWHFYSVVVOFlUS3JTNWIrenlON1BwemdBNFRDeFliVVF5VmtDZXhTUGoxRG9nVkQ3RkUvQmdEc2x5N2k1eDhxM0I/v-dchuttya.tish-12-navesn_(mp3CC.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Алєся", R.drawable.logo_black,
                "https://storage.mp3cc.biz/listen/19493397/ZHJMMXFDNzVSOTd6Zm5CK2lTckJ2cTQ1WlcxWUpET2phbW11Z2JuNVd6SktVbkJVUDB6VXpleU9OWUJzKzZpTkJnTGhka1owRUZDWGNhT1BEVWN0UVNLQlZBRGdGdWoyVUxZM3BaeVRJQ1V3KytyMXpMNUg4dFg4QWZ5VmhTcDg/v-dchuttya.tish-alesya_(mp3CC.biz).mp3"));
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