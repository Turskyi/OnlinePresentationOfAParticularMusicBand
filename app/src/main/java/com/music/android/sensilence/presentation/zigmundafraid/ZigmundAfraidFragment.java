package com.music.android.sensilence.presentation.zigmundafraid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.music.android.sensilence.service.MusicAlbum;
import com.music.android.sensilence.R;
import com.music.android.sensilence.domain.Song;
import com.music.android.sensilence.presentation.adapters.SongAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZigmundAfraidFragment extends Fragment {
    public ZigmundAfraidFragment() {
        // Required empty public constructor
    }

    private MusicAlbum musicAlbum;
    private ListView listView;
    protected MediaPlayer mediaPlayer;

    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager audioManager;

    // Create an array of songs
    final ArrayList<Song> songs = new ArrayList<>();

    private final AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    musicAlbum.onFocusChange(focusChange, mediaPlayer);
                }
            };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private final MediaPlayer.OnCompletionListener completionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    musicAlbum.releaseMediaPlayer();
                }
            };


    private final AdapterView.OnItemClickListener firstClickListener =
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(
                        AdapterView<?> parent,
                        final View view,
                        final int position,
                        long id
                ) {
                    musicAlbum.onFirstClick(
                            view,
                            position,
                            onAudioFocusChangeListener,
                            completionListener,
                            secondClickListener,
                            listView,
                            songs,
                            audioManager,
                            getActivity()
                    );
                }
            };

    private final AdapterView.OnItemClickListener secondClickListener =
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    musicAlbum.onSecondClick(firstClickListener, listView);
                }
            };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_song_list, container, false);
        musicAlbum = new MusicAlbum();

        //Create and setup the {@link AudioManager} to request audio focus
        audioManager = (AudioManager) requireActivity().getSystemService(Context.AUDIO_SERVICE);

        setBackground(rootView);

        // fill up the album with a list of songs
        Song song = new Song(
                getString(R.string.band_zigmund_afraid),
                getString(R.string.song_name_abroad),
                R.drawable.ic_za,
                getString(R.string.audio_abroad)
        );
        songs.add(song);
        songs.add(
                new Song(
                        getString(R.string.band_zigmund_afraid),
                        getString(R.string.song_name_abroad_rmx),
                        R.drawable.vt_dnb120,
                        getString(R.string.audio_abroad_rmx)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_zigmund_afraid),
                        getString(R.string.song_name_pleasure_was_mine),
                        R.drawable.pwm,
                        getString(R.string.audio_pleasure_was_mine)
                )
        );
        /* Create an {@link SongAdapter}, whose data source is a list of {@link Song}s.
         * The adapter knows how to create list items for each item in the list. */
        SongAdapter adapter = new SongAdapter(getActivity(), songs, R.color.category_zigmund_afraid);
        listView.setAdapter(adapter);
        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(firstClickListener);
        return rootView;
    }

    private void setBackground(View rootView) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.zigmund_afraid_cover);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmp);
        bitmapDrawable.setAlpha(100);
        listView = rootView.findViewById(R.id.list);
        listView.setBackground(bitmapDrawable);
    }
}
