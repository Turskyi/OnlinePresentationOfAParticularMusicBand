package com.example.android.sensilence;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class ZombiActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);
       final String link;
        // Create an array of songs
        final ArrayList<Song> songs = new ArrayList<>();

        // Create a list of songs
        songs.add(new Song("відчуття.тиші", "Зомбі", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші",
                "Зомбі (aContrari Post-Apocalyptic Dubstep Mix)", R.drawable.logo,
               "https://cdnet2.mixmuz.ru/5a05c0594c5e/963957a4e877/b31996b77e2788855af79da85f7dc4c8-1aba0fc1-11f59ad8-1-737d8aef826/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96%20%28aContrari%20post-apocalyptic%20dubstep%20mix%29.mp3"));
        songs.add(new Song("відчуття.тиші", "ゾンビ", R.drawable.logo, "https:////cdnet2.mixmuz.ru/f4ad9d36419a/760c1bf2a443/b31996b77e2788855af79da85f7dc4c8-4702c99-6c51394-1-17364e9156dc/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%E3%82%BE%E3%83%B3%E3%83%93.mp3"));

        // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(this, songs, R.color.category_zombi);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get the {@link Word} object at the given position the user clicked on
                Song song = songs.get(position);

//                Create and setup the {@link MedeaPlayer} for the audio resource associated
//                with the current word
//                mMediaPlayer = MediaPlayer.create(ZombiActivity.this, song.getmAudioResourceId());
////                Start the audio file
//                mMediaPlayer.start();

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
                mMediaPlayer.start();

            }
        });
    }
}