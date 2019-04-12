package com.example.android.sensilence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class SenseOfSilenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        // Create an array of songs
        ArrayList<Song> songs = new ArrayList<>();

// Create a list of songs
        songs.add(new Song("відчуття.тиші", "Зима", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Noli Respicere", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Востаннє", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Смак Мого Забуття", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Промені", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Безодня", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Не край", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Знову Страх", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Навпіл", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Зап'ястя", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Падаю", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Навесні", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));
        songs.add(new Song("відчуття.тиші", "Алєся", R.drawable.logo, "https://cdnet2.mixmuz.ru/10ccf89fbf4c1/b66693572cab/b31996b77e2788855af79da85f7dc4c8-1191ff91d-11f59f66-1-137c23fe30ee/%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96%20%E2%80%94%20%D0%97%D0%BE%D0%BC%D0%B1%D1%96.mp3"));

        // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(this, songs, R.color.category_crime);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}