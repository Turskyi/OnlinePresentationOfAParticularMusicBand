package io.github.turskyi.data.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.github.turskyi.data.R;
import io.github.turskyi.data.entity.SongEntity;
import io.github.turskyi.domain.entities.enums.Album;

import static io.github.turskyi.data.entity.SongEntity.TABLE_SONGS;

@Database(entities = {SongEntity.class}, version = 1, exportSchema = false)
public abstract class SongsDatabase extends RoomDatabase {
    public abstract SongDao getSongDao();

    public static class Callback extends RoomDatabase.Callback {

        private final Context applicationContext;

        @Inject
        public Callback(Context context) {
            applicationContext = context;
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            db.query("PRAGMA synchronous = OFF");
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            db.query("PRAGMA journal_mode = MEMORY");
            int senseOfSilenceSize = fillSenseOfSilenceLpAlbum(db);
            int crimeSize = fillCrimeAlbum(db, senseOfSilenceSize);
            int zombiSize = fillZombiAlbum(db, crimeSize);
            fillZigmundAfraidAlbum(db, zombiSize);
        }

        private void fillZigmundAfraidAlbum(SupportSQLiteDatabase db, int zombiSize) {
            List<String> zigmundAfraidNames = Arrays.asList(
                    applicationContext.getString(R.string.song_name_abroad),
                    applicationContext.getString(R.string.song_name_abroad_rmx),
                    applicationContext.getString(R.string.song_name_pleasure_was_mine)
            );

            List<Integer> zigmundAfraidImageResources = Arrays.asList(
                    R.drawable.ic_za,
                    R.drawable.vt_dnb120,
                    R.drawable.pwm
            );

            List<String> zigmundAfraidLinks = Arrays.asList(
                    applicationContext.getString(R.string.audio_abroad),
                    applicationContext.getString(R.string.audio_abroad_rmx),
                    applicationContext.getString(R.string.audio_pleasure_was_mine)
            );

            fillAlbum(
                    db,
                    applicationContext.getString(R.string.band_zigmund_afraid),
                    Album.ZIGMUND_AFRAID.name,
                    zigmundAfraidNames,
                    zigmundAfraidImageResources,
                    zigmundAfraidLinks,
                    zombiSize
            );
        }


        private int fillZombiAlbum(SupportSQLiteDatabase db, int crimeSize) {
            List<String> zombiNames = Arrays.asList(
                    applicationContext.getString(R.string.song_name_zombi),
                    applicationContext.getString(R.string.song_name_zombi_dubstep),
                    applicationContext.getString(R.string.song_name_japanese_zombie)
            );

            List<Integer> zombiImageResources = new ArrayList<>(Collections.nCopies(
                    zombiNames.size(),
                    R.drawable.zombi
            ));

            List<String> zombiLinks = Arrays.asList(
                    applicationContext.getString(R.string.audio_zombi),
                    applicationContext.getString(R.string.audio_zombi_dubstep),
                    applicationContext.getString(R.string.audio_japanese_zombie)
            );

            fillAlbum(
                    db,
                    applicationContext.getString(R.string.band_sense_of_silence),
                    Album.ZOMBI.name,
                    zombiNames,
                    zombiImageResources,
                    zombiLinks,
                    crimeSize
            );
            return zombiNames.size();
        }

        private int fillCrimeAlbum(SupportSQLiteDatabase db, int startIndex) {
            List<String> crimeNames = Arrays.asList(
                    applicationContext.getString(R.string.song_name_to_astarta),
                    applicationContext.getString(R.string.song_name_angelscream),
                    applicationContext.getString(R.string.song_name_zombi_album),
                    applicationContext.getString(R.string.song_name_did_not_want),
                    applicationContext.getString(R.string.song_name_crime),
                    applicationContext.getString(R.string.song_name_noli_respicere_rmx)
            );

            List<Integer> crimeImageResources = new ArrayList<>(Collections.nCopies(
                    crimeNames.size() - 1,
                    R.drawable.pic_crime_cover
            ));
            crimeImageResources.add(R.drawable.vt_dnb120);

            List<String> crimeLinks = Arrays.asList(
                    applicationContext.getString(R.string.audio_to_astarta),
                    applicationContext.getString(R.string.audio_angelscream),
                    applicationContext.getString(R.string.audio_zombi_album_version),
                    applicationContext.getString(R.string.audio_did_not_want),
                    applicationContext.getString(R.string.audio_crime),
                    applicationContext.getString(R.string.audio_noli_respicere_rmx)
            );

            fillAlbum(
                    db,
                    applicationContext.getString(R.string.band_sense_of_silence),
                    Album.CRIME.name,
                    crimeNames,
                    crimeImageResources,
                    crimeLinks,
                    startIndex
            );
            return crimeNames.size();
        }

        private int fillSenseOfSilenceLpAlbum(SupportSQLiteDatabase db) {
            List<String> senseOfSilenceNames = Arrays.asList(
                    applicationContext.getString(R.string.song_name_winter),
                    applicationContext.getString(R.string.song_name_noli_respicere),
                    applicationContext.getString(R.string.song_name_last_time),
                    applicationContext.getString(R.string.song_name_taste_of_my_oblivion),
                    applicationContext.getString(R.string.song_name_rays),
                    applicationContext.getString(R.string.song_name_abyss),
                    applicationContext.getString(R.string.song_name_not_the_end),
                    applicationContext.getString(R.string.song_name_fear_again),
                    applicationContext.getString(R.string.song_name_in_half),
                    applicationContext.getString(R.string.song_name_wrist),
                    applicationContext.getString(R.string.song_name_falling),
                    applicationContext.getString(R.string.song_name_in_the_spring),
                    applicationContext.getString(R.string.song_name_alesia)
            );

            List<Integer> senseOfSilenceImageResources = new ArrayList<>(Collections.nCopies(
                    senseOfSilenceNames.size(),
                    R.drawable.logo_black
            ));

            List<String> senseOfSilenceLinks = Arrays.asList(
                    applicationContext.getString(R.string.audio_winter),
                    applicationContext.getString(R.string.audio_noli_respicere),
                    applicationContext.getString(R.string.audio_last_time),
                    applicationContext.getString(R.string.audio_taste_of_my_oblivion),
                    applicationContext.getString(R.string.audio_rays),
                    applicationContext.getString(R.string.audio_abyss),
                    applicationContext.getString(R.string.audio_not_the_end),
                    applicationContext.getString(R.string.audio_fear_again),
                    applicationContext.getString(R.string.audio_in_half),
                    applicationContext.getString(R.string.audio_wrist),
                    applicationContext.getString(R.string.audio_falling),
                    applicationContext.getString(R.string.audio_in_the_spring),
                    applicationContext.getString(R.string.audio_alesia)
            );

            fillAlbum(
                    db,
                    applicationContext.getString(R.string.band_sense_of_silence),
                    Album.SENSE_OF_SILENCE_LP.name,
                    senseOfSilenceNames,
                    senseOfSilenceImageResources,
                    senseOfSilenceLinks,
                    0
            );
            return senseOfSilenceNames.size();
        }

        private void fillAlbum(
                SupportSQLiteDatabase db,
                String bandName,
                String albumName,
                List<String> names,
                List<Integer> imageResources,
                List<String> links,
                int startIndex
        ) {

            for (int i = 0; i < names.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(SongEntity.COLUMN_BAND, bandName);
                contentValues.put(SongEntity.COLUMN_ALBUM, albumName);
                contentValues.put(SongEntity.COLUMN_NAME, names.get(i));
                contentValues.put(SongEntity.COLUMN_IMAGE_RES_ID, imageResources.get(i));
                contentValues.put(SongEntity.COLUMN_AUDIO_LINK, links.get(i));
                db.insert(TABLE_SONGS, OnConflictStrategy.REPLACE, contentValues);
            }
        }
    }
}
