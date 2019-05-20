package com.music.android.sensilence;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
                "https://myzcloud.pro/song/dl/636938060118364889/80cce9ff959ad65f5eb38281595d5dcd/6413410"));
        songs.add(new Song("відчуття.тиші", "Noli Respicere", R.drawable.logo_black,
                "https://myzcloud.pro/song/dl/636938061760121514/d30c9918af8c3332311e4aa70eeca592/6413411"));
        songs.add(new Song("відчуття.тиші", "Востаннє", R.drawable.logo_black,
                "https://mp3-tut.com/musictutplay?id=-22431441_132224076&hash=b27d97dfec746e70466bfbb112e3192559263fc27a4d1fa91c311b7ff82722db&artist=%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96&title=03+-+%D0%92%D0%BE%D1%81%D1%82%D0%B0%D0%BD%D0%BD%D1%94&download=1"));
        songs.add(new Song("відчуття.тиші", "Смак Мого Забуття", R.drawable.logo_black,
                "https://done.7cord.com/proxy?data=Y1I3RUczR0pVTE1WVklqSTB4VGdVOEViYjlZT0pPaFZaZ0FuVTNBbzlwalRLMWIybVA0ZlpOVTAyakJXV2MycisxZGxweGFRRVp2Q2xNSDM4RSs1cVNNOGZaSlFYVXFrR1k3d09YTzM1SEkwNVZSc2J5Q0pUeHg0TmQ3LzhhdUN1R090aFFJemVBMjRicHZsMTdsTEhrbkNTY21Qa2ZPMHFIK3g2S0QwY24rcnFOS3NoYXdTRURvaHZkRnpkZC9nMzdueDJ6MURaWmtGbSs0dlhFR1dTcGIwaWJwTWF1cnovdHlpS2NFTWlJUjhUSXBENVRyc0tNQnFlR1RlNW9qbk10Ylcwa1NsVXhVSGR5TWZFUGZBajVqK1lQY3hyUDFBT2c2UWlxRGNIeEFkblVmSWRxYnV5ZHF2TjgwU2Z6ZkxjZDMwRFJ1V3hwTndRSjZGczFkWGgvQThUUUUrT1F6UWVhK3R6MjRzZy90djNWYzh6Wndncm04YmVtTnJveXNwWG5CcW9haHBrSzJUNmhJZDQwWHRmRTFWdHlJWHVIUXoxbGVrYmU1a2VVbDZnSmtGR09rQ3I0ajlhMnQzSFJ6NlpITllsRG9BdVlKbjNYaVAvOEtoaXJ3aElySmFYM3dncGNaM0R0RDFvWHNjUGdXODkza2g0eWJQN2kxUWt2NWk"));
        songs.add(new Song("відчуття.тиші", "Промені", R.drawable.logo_black,
                "https://mp3-tut.com/musictutplay?id=55436575_159116004&hash=d19d4733acac5e0cd42d095104bc9169b02101e0eff2f243dad0aeb2f28b134d&artist=%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96&title=%D0%9F%D1%80%D0%BE%D0%BC%D0%B5%D0%BD%D1%96&download=1"));
        songs.add(new Song("відчуття.тиші", "Безодня", R.drawable.logo_black,
                "https://myzcloud.pro/song/dl/636938100351166801/4e828bc44d7cdbdaf623018c6249336f/6413415"));
        songs.add(new Song("відчуття.тиші", "Не край", R.drawable.logo_black,
                "https://myzcloud.pro/song/dl/636938102340900143/dcdfc4e69c228cd796b3527148015e99/6413416"));
        songs.add(new Song("відчуття.тиші", "Знову Страх", R.drawable.logo_black,
                "https://myzcloud.pro/song/dl/636938103582647501/e3e276e8b5fdac0def96a3c1f2e255f0/6413417"));
        songs.add(new Song("відчуття.тиші", "Навпіл", R.drawable.logo_black,
                "https://myzcloud.pro/song/dl/636938104901896641/22c5eadf4bd91b4d0b9aeca9c7bd1978/6413420"));
        songs.add(new Song("відчуття.тиші", "Зап'ястя", R.drawable.logo_black,
                "https://myzcloud.pro/song/dl/636938106468026480/fca32c2835df7b659b58d87b13a3fa8e/6413421"));
        songs.add(new Song("відчуття.тиші", "Падаю", R.drawable.logo_black,
                "https://myzcloud.pro/song/dl/636938106819753325/8f394b9cf4e6bb55af41270847ecac24/6413422"));
        songs.add(new Song("відчуття.тиші", "Навесні", R.drawable.logo_black,
                "https://myzcloud.pro/song/dl/636938107170073931/20dec9fe780d0be5ad1b29ad51bbf9f0/6413423"));
        songs.add(new Song("відчуття.тиші", "Алєся", R.drawable.logo_black,
                "https://myzcloud.pro/song/dl/636938107439923878/0aa41f5eeae4e6e951b5aab5f798bf28/6413424"));
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
                final Song song = songs.get(position);

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
                        Intent lastIntent = new Intent(SenseOfSilenceActivity.this,
                                MyService.class);
                        startService(lastIntent);
                        stopService(lastIntent);
                        AlertDialog lastDialog =
                                new AlertDialog.Builder(SenseOfSilenceActivity.this)
                                        .setTitle("Трапилось щось страшне!")
                                        .setMessage("Хочете написати розробнику?")
                                        .setCancelable(false)
                                        .setPositiveButton("Так", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(Intent.ACTION_SENDTO,
                                                        Uri.fromParts("mailto", "dmitriy.turskiy@gmail.com", ""));
                                                intent.putExtra(Intent
                                                                .EXTRA_SUBJECT,
                                                        "Страшна історія яка трапилася з піснею " + song.getDefaultSong());
                                                if (intent.resolveActivity(getPackageManager()) != null) {
                                                    startActivity(intent);
                                                }
                                            }
                                        }).setNegativeButton("Ні", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(SenseOfSilenceActivity.this,
                                                "Тоді спробуйте завтра ;)", Toast.LENGTH_SHORT).show();
                                        dialog.cancel();
                                    }
                                }).create();
                        lastDialog.show();
                    }
                    //                Start the audio file
                    mMediaPlayer.start();

                    //Setup a listener on the media player, so that we can stop and release the
                    //media player once the sounds has finished
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                    listView.setOnItemClickListener(secondClickListener);
                } else {
                    Toast.makeText(view.getContext(),
                            "Немає інтернету", Toast.LENGTH_SHORT).show();
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