package com.music.android.sensilence;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZigmundAfraidFragment extends Fragment {
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
//                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
//                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
//                        //The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus
//                        //short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means
//                        //our app is allowed to continue playing sound but at a lower volume.
//
//                        //Pause playback and reset player to the start of the file. That way, when
//                        //play the song from the beginning when we resume playback.
//                        mMediaPlayer.pause();
//                        mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition());
//                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
//                        //The AUDIOFOCUS_GAIN case means we have regained focus and can
//                        //resume playback
//                        mMediaPlayer.start();
//                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
//                        //The AUDIOFOCUS_LOSS case means we've lost audio focus and
//                        //stop playback and cleanup resources
//                        releaseMediaPlayer();
//                    }
                }
            };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
           musicAlbum.releaseMediaPlayer( );
        }
    };
    // Create an array of songs
    final ArrayList<Song> songs = new ArrayList<>();

    public ZigmundAfraidFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.song_list, container, false);
        musicAlbum = new MusicAlbum(mMediaPlayer);
        //Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) Objects.requireNonNull(getActivity())
                .getSystemService(Context.AUDIO_SERVICE);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                R.drawable.zigmund_afraid_cover);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmp);
        bitmapDrawable.setAlpha(100);
        listView = rootView.findViewById(R.id.list);
        listView.setBackground(bitmapDrawable);

        // Create a list of songs
        Song song = new Song("Zigmund Afraid", "Abroad", R.drawable.ic_za,
                "1https://cs1.djbag.biz/download/55367092/bXBhNTVGU1RONXozNnlSZFM3MmFKRHJNaFRDdTdObFF4UHBkL3lIdW00bzJtZkRCdWlxV0lMcFd0eUl2R2tVcmtCb3V1V21zRkg4Z3Vzci9lWS83ZGtrcXBadThXdUxFeUJ6SkRWakFVbnhDVzhyQWZwWWVTRUp1ZGtCa3hHeEs/Zigmund_Afraid_Abroad_(djbag.biz).mp3");
        songs.add(song);
        songs.add(new Song("Zigmund Afraid", "Abroad (Retroflex Encoded)",
                R.drawable.vt_dnb120, "https://cs1.djbag.biz/download/32561854/bXBhNTVGU1RONXozNnlSZFM3MmFKRHJNaFRDdTdObFF4UHBkL3lIdW00cEVNcHJyWGFyZUNpbTVoNXl3OGZuVmQrQ2FXS1hkNlpuYjF4OHJkeVpoSk1yQzl3QkJMUkZNM2hYdU1DaWJ1WFRRUDZ4N1FtVUFpM3VIRmdBTmZVREU/Zigmund_Afraid_Abroad_Retroflex_Encoded_(djbag.biz).mp3"));
        songs.add(new Song("Zigmund Afraid", "Pleasure was mine (∞)",
                R.drawable.pwm, "https://api.soundcloud.com/tracks/335521943" +
                "/download?client_id=xIa292zocJP1G1huxplgJKVnK0V3Ni9D&oauth_token=2-290076-327486136" +
                "-fzDcrgHney5w0F"));
        // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(getActivity(), songs, R.color.category_zigmund_afraid);
        listView.setAdapter(adapter);
        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(firstClickListener);
        return rootView;
    }

    final AdapterView.OnItemClickListener firstClickListener = new AdapterView.OnItemClickListener() {

        @RequiresApi(api = Build.VERSION_CODES.O)
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
                            String url = song.getmAudioResourceId(); // my URL here
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
                                musicAlbum.errorAlert(song, getActivity());
                            }
                             //  Start the audio file
                           musicAlbum.play(view,progressBar,mMediaPlayer,mCompletionListener,secondClickListener,listView);
//                            listView.setOnItemClickListener(secondClickListener);
                        }
                    } else {
                        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(view.getContext(),
                                        "No internet", Toast.LENGTH_LONG).show();
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

//    private void play(View view) {
//        mMediaPlayer.start();
//        progressBar.setVisibility(View.INVISIBLE);
//        imageView = view.findViewById(R.id.btn_image);
//        imageView.setImageResource(R.drawable.ic_pause);
//        //Setup a listener on the media player, so that we can stop and release the
//        //media player once the sounds has finished
//        mMediaPlayer.setOnCompletionListener(mCompletionListener);
//    }

//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // When the activity is stopped, release the media player resources because we won't
//        // be playing any more sounds.
//        musicAlbum.releaseMediaPlayer(mMediaPlayer);
//    }

//    /**
//     * Clean up the media player by releasing its resources.
//     */
//    private void releaseMediaPlayer() {
//        // If the media player is not null, then it may be currently playing a sound.
//        if (mMediaPlayer != null) {
//            // Regardless of the current state of the media player, release its resources
//            // because we no longer need it.
//            mMediaPlayer.release();
//
//            // Set the media player back to null. For our code, we've decided that
//            // setting the media player to null is an easy way to tell that the media player
//            // is not configured to play an audio file at the moment.
//            mMediaPlayer = null;
//        }
//    }
}
