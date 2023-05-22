package io.github.turskyi.data.database;

import static io.github.turskyi.data.entity.SongEntity.COLUMN_ALBUM;
import static io.github.turskyi.data.entity.SongEntity.COLUMN_AUDIO_LINK;
import static io.github.turskyi.data.entity.SongEntity.COLUMN_BAND;
import static io.github.turskyi.data.entity.SongEntity.COLUMN_ID;
import static io.github.turskyi.data.entity.SongEntity.COLUMN_IMAGE_RES;
import static io.github.turskyi.data.entity.SongEntity.COLUMN_IMAGE_RES_ID;
import static io.github.turskyi.data.entity.SongEntity.COLUMN_NAME;
import static io.github.turskyi.data.entity.SongEntity.TABLE_SONGS;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;

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

@Database(
        version = 1,
        entities = {SongEntity.class}
)
public abstract class SongsDatabase extends RoomDatabase {
    private static void recreateTable(SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        db.execSQL("CREATE TABLE " + TABLE_SONGS + "(" + COLUMN_ID + " INTEGER, " + COLUMN_BAND + " TEXT, " + COLUMN_NAME + " TEXT, " + COLUMN_ALBUM + " TEXT, " + COLUMN_IMAGE_RES_ID + " INTEGER, " + COLUMN_IMAGE_RES + " TEXT, " + COLUMN_AUDIO_LINK + " TEXT)");
    }

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
        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
            super.onDestructiveMigration(db);
            insertValuesInTable(db);
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            insertValuesInTable(db);
        }

        private void fillBonusAlbum(@NonNull SupportSQLiteDatabase db) {
            List<String> bonusNames = Arrays.asList(
                    applicationContext.getString(R.string.song_name_noli_respicere_rmx),
                    applicationContext.getString(R.string.song_name_fly_away),
                    applicationContext.getString(R.string.song_name_whisper),
                    applicationContext.getString(R.string.song_name_hate_number)
            );

            List<Integer> bonusImageResourceIds = Arrays.asList(
                    R.drawable.vt_dnb120,
                    R.drawable.pic_vt_cover,
                    R.drawable.pic_whisper_cover,
                    R.drawable.pic_hate_number_cover
            );

            List<String> bonusImageLinks = Arrays.asList(
                    applicationContext.getString(R.string.image_vt_dnb),
                    applicationContext.getString(R.string.image_vt_cover),
                    applicationContext.getString(R.string.image_whisper_cover),
                    applicationContext.getString(R.string.image_hate_number_cover)
            );

            List<String> bonusLinks = Arrays.asList(
                    applicationContext.getString(R.string.audio_noli_respicere_rmx),
                    applicationContext.getString(R.string.audio_fly_away),
                    applicationContext.getString(R.string.audio_whisper),
                    applicationContext.getString(R.string.audio_hate_number)
            );

            fillAlbum(
                    db,
                    applicationContext.getString(R.string.band_sense_of_silence),
                    Album.BONUS.name,
                    bonusNames,
                    bonusImageResourceIds,
                    bonusImageLinks,
                    bonusLinks
            );
        }

        private void fillSenseOfSilenceLpAlbum(SupportSQLiteDatabase db) {
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

            List<Integer> senseOfSilenceImageResourceIds = new ArrayList<>(Collections.nCopies(
                    senseOfSilenceNames.size(),
                    R.drawable.logo_black
            ));

            List<String> senseOfSilenceImageResources = new ArrayList<>(Collections.nCopies(
                    senseOfSilenceNames.size(),
                    applicationContext.getString(R.string.image_logo_black)
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
                    senseOfSilenceImageResourceIds,
                    senseOfSilenceImageResources,
                    senseOfSilenceLinks
            );
        }

        private void fillZombiAlbum(SupportSQLiteDatabase db) {
            List<String> zombiNames = Arrays.asList(
                    applicationContext.getString(R.string.song_name_zombi),
                    applicationContext.getString(R.string.song_name_zombi_dubstep),
                    applicationContext.getString(R.string.song_name_japanese_zombie),
                    applicationContext.getString(R.string.song_name_zombie_instrumental)
            );

            List<Integer> zombiImageResourceIds = new ArrayList<>(Collections.nCopies(
                    zombiNames.size(),
                    R.drawable.zombi
            ));

            List<String> zombiImageResources = new ArrayList<>(Collections.nCopies(
                    zombiNames.size(),
                    applicationContext.getString(R.string.image_zombi)
            ));

            List<String> zombiLinks = Arrays.asList(
                    applicationContext.getString(R.string.audio_zombi),
                    applicationContext.getString(R.string.audio_zombi_dubstep),
                    applicationContext.getString(R.string.audio_japanese_zombie),
                    applicationContext.getString(R.string.audio_zombie_instrumental)
            );

            fillAlbum(
                    db,
                    applicationContext.getString(R.string.band_sense_of_silence),
                    Album.ZOMBI.name,
                    zombiNames,
                    zombiImageResourceIds,
                    zombiImageResources,
                    zombiLinks
            );
        }

        private void fillCrimeAlbum(SupportSQLiteDatabase db) {
            List<String> crimeNames = Arrays.asList(
                    applicationContext.getString(R.string.song_name_to_astarta),
                    applicationContext.getString(R.string.song_name_angelscream),
                    applicationContext.getString(R.string.song_name_zombi_album),
                    applicationContext.getString(R.string.song_name_did_not_want),
                    applicationContext.getString(R.string.song_name_crime)
            );

            List<Integer> crimeImageResourceIds = new ArrayList<>(Collections.nCopies(
                    crimeNames.size(),
                    R.drawable.pic_crime_cover
            ));

            List<String> crimeImageResources = new ArrayList<>(Collections.nCopies(
                    crimeNames.size(),
                    applicationContext.getString(R.string.image_crime_cover)
            ));

            List<String> crimeLinks = Arrays.asList(
                    applicationContext.getString(R.string.audio_to_astarta),
                    applicationContext.getString(R.string.audio_angelscream),
                    applicationContext.getString(R.string.audio_zombi_album_version),
                    applicationContext.getString(R.string.audio_did_not_want),
                    applicationContext.getString(R.string.audio_crime)
            );

            fillAlbum(
                    db,
                    applicationContext.getString(R.string.band_sense_of_silence),
                    Album.CRIME.name,
                    crimeNames,
                    crimeImageResourceIds,
                    crimeImageResources,
                    crimeLinks
            );
        }

        private void fillZigmundAfraidAlbum(SupportSQLiteDatabase db) {
            List<String> zigmundAfraidNames = Arrays.asList(
                    applicationContext.getString(R.string.song_name_abroad),
                    applicationContext.getString(R.string.song_name_abroad_rmx),
                    applicationContext.getString(R.string.song_name_pleasure_was_mine)
            );

            List<Integer> zigmundAfraidImageResourceIds = Arrays.asList(
                    R.drawable.ic_za,
                    R.drawable.vt_dnb120,
                    R.drawable.pwm
            );

            List<String> zigmundAfraidImageResources = Arrays.asList(
                    applicationContext.getString(R.string.image_za),
                    applicationContext.getString(R.string.image_vt_dnb),
                    applicationContext.getString(R.string.image_pwm)
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
                    zigmundAfraidImageResourceIds,
                    zigmundAfraidImageResources,
                    zigmundAfraidLinks
            );
        }

        private void fillAlbum(
                SupportSQLiteDatabase db,
                String bandName,
                String albumName,
                List<String> names,
                List<Integer> imageResourceIds,
                List<String> imageLinks,
                List<String> links
        ) {
            for (int i = 0; i < names.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_BAND, bandName);
                contentValues.put(COLUMN_ALBUM, albumName);
                contentValues.put(COLUMN_NAME, names.get(i));
                contentValues.put(COLUMN_IMAGE_RES_ID, imageResourceIds.get(i));
                contentValues.put(COLUMN_IMAGE_RES, imageLinks.get(i));
                contentValues.put(COLUMN_AUDIO_LINK, links.get(i));
                try {
                    db.insert(TABLE_SONGS, OnConflictStrategy.REPLACE, contentValues);
                } catch (SQLiteException e) {
                    recreateTable(db);
                }
            }
        }

        private void insertValuesInTable(@NonNull SupportSQLiteDatabase db) {
            db.query("PRAGMA journal_mode = MEMORY");
            fillSenseOfSilenceLpAlbum(db);
            fillZombiAlbum(db);
            fillCrimeAlbum(db);
            fillBonusAlbum(db);
            fillZigmundAfraidAlbum(db);
        }
    }
}
