package com.music.android.sensilence;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZigmundAfraidFragment extends Fragment {
    View rootView;
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
        rootView = inflater.inflate(R.layout.song_list, container, false);
        musicAlbum = new MusicAlbum();
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
                "https://cs1.djbag.biz/download/55367092/bXBhNTVGU1RONXozNnlSZFM3MmFKRHJNaFRDdTdObFF4UHBkL3lIdW00bzJtZkRCdWlxV0lMcFd0eUl2R2tVcmtCb3V1V21zRkg4Z3Vzci9lWS83ZGtrcXBadThXdUxFeUJ6SkRWakFVbnhDVzhyQWZwWWVTRUp1ZGtCa3hHeEs/Zigmund_Afraid_Abroad_(djbag.biz).mp3");
        songs.add(song);
        songs.add(new Song("Zigmund Afraid", "Abroad (Retroflex Encoded)",
                R.drawable.vt_dnb120, "https://cs1.djbag.biz/download/32561854/bXBhNTVGU1RONXozNnlSZFM3MmFKRHJNaFRDdTdObFF4UHBkL3lIdW00cEVNcHJyWGFyZUNpbTVoNXl3OGZuVmQrQ2FXS1hkNlpuYjF4OHJkeVpoSk1yQzl3QkJMUkZNM2hYdU1DaWJ1WFRRUDZ4N1FtVUFpM3VIRmdBTmZVREU/Zigmund_Afraid_Abroad_Retroflex_Encoded_(djbag.biz).mp3"));
        songs.add(new Song("Zigmund Afraid", "Pleasure was mine (∞)",
                R.drawable.pwm, "https://done.7cord.com/proxy?data=eGl1b09pMnVhclF6enFmcnBrUnA0U00xdTRWK2s5Mm52U1FZVkQzU0REdWNLVE1ndlN3TC9tOUFXQk9hNlZZbnMxbnNkaTdlVFZic1BBcGsxdk1scklrS1VXOGpIdjBNVDRSTnZUMG84NFZycGdwR0dDQXlHVFlZR0xLNUI5d0IrN29wSjRlRmxYYkVpNEppOUZWQXc1cXo1RzR0Rlp2aFhrK29uODJXdjRaMUhNZDhrcHoyQXFrb1EzZjhXMlRDZUhPenpPeGJkRC9NVTFpNE9qdVc5ZG5VVGRRQ2FWTXhOdmtyOG9vQXNoblpLR0hxbWRVWHhLR3B4bndBSEljV3R0VUVvakRLc2cxTXhvNGw1WTNrbEdJR1V3OXhoeER6Y3FuZVZCTFhuS3JsYlhqVzdMam5yak5LY1lmNzVDa1pGbUc5RTZlUnFKZDlIY0lLaWFzOW8zM1RQc2dVcmFnU0xzVDBqV0xUelhva0c1VENwbnFUV1hucGpkUnlUL2RkLzBUOGVSRS84Y0RHNzNCNEZreVJqOTlUMlhGMUUyMmhsbW9ERW9TMEJFSmNNa2ZvUkg0Vk9acCtlWE1QWUR2VW1ScktVeUc0aDQza1lqZVNQcjVWWlkzMk5HSXg2cUdhSUdkTWcvQTVOcmM9"));
        // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(getActivity(), songs, R.color.category_zigmund_afraid);
        listView.setAdapter(adapter);
        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(firstClickListener);
        return rootView;
    }

    AdapterView.OnItemClickListener firstClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            musicAlbum.onFirstClick( view, position, mOnAudioFocusChangeListener, mCompletionListener, secondClickListener, listView, songs, mAudioManager,getActivity());
        }
    };

    AdapterView.OnItemClickListener secondClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            musicAlbum.onSecondClick(firstClickListener,listView);
        }
    };

//    final AdapterView.OnItemClickListener firstClickListener = new AdapterView.OnItemClickListener() {
//
//        @RequiresApi(api = Build.VERSION_CODES.O)
//        @Override
//        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
//            if (mMediaPlayer != null && imageView == view.findViewById(R.id.btn_image)) {
//                musicAlbum. play(view,progressBar,mMediaPlayer,mCompletionListener,secondClickListener, listView);
//            } else {
//            progressBar = view.findViewById(R.id.loading_spinner);
//            progressBar.setVisibility(View.VISIBLE);
//            new Thread(new Runnable() {
//                public void run() {
//                    //do time consuming operations
//                    if (musicAlbum.isOnline()) {
//                        //Get the {@link Word} object at the given position the user clicked on
//                        final Song song = songs.get(position);
//
//                        //Release the media player if it currently exists because we are about to
//                        //play a different sound file.
//                      musicAlbum.releaseMediaPlayer();
//
//                        //Request audio focus for playback
//                        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
//                                //Use the music stream.
//                                AudioManager.STREAM_MUSIC,
//                                //Request permanent focus.
//                                AudioManager.AUDIOFOCUS_GAIN);
//                        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                            //We have an audio focus now.
//
////                Create and setup the {@link MedeaPlayer} for the audio resource associated
////                with the current song
//                            String url = song.getmAudioResourceId(); // my URL here
//                            mMediaPlayer = new MediaPlayer();
//                            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                            try {
//                                mMediaPlayer.setDataSource(url);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                mMediaPlayer.prepare(); // might take long! (for buffering, etc)
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                                musicAlbum.errorAlert(song, getActivity());
//                            }
//                             //  Start the audio file
//                           musicAlbum. play(view,progressBar,mMediaPlayer,mCompletionListener,secondClickListener, listView);
////                            listView.setOnItemClickListener(secondClickListener);
//                        }
//                    } else {
//                        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
//                            public void run() {
//                                Toast.makeText(view.getContext(),
//                                        "No internet", Toast.LENGTH_LONG).show();
//                                progressBar.setVisibility(View.INVISIBLE);
//                            }
//                        });
//                    }
//                }
//            }).start();
//        }
//        }
//    };
//
//    AdapterView.OnItemClickListener secondClickListener = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            mMediaPlayer.pause();
//            imageView = view.findViewById(R.id.btn_image);
//            imageView.setImageResource(R.drawable.ic_play_arrow);
//            listView.setOnItemClickListener(firstClickListener);
//        }
//    };
}
