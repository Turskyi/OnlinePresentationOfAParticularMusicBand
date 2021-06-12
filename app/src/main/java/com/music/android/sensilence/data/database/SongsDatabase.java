package com.music.android.sensilence.data.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.music.android.sensilence.R;
import com.music.android.sensilence.data.entity.SongEntity;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

import static com.music.android.sensilence.data.entity.SongEntity.TABLE_SONGS;

@Database(entities = {SongEntity.class}, version = 1, exportSchema = false)
public abstract class SongsDatabase extends RoomDatabase {
    public abstract SongDao getSongDao();

    public static class Callback extends RoomDatabase.Callback {

        private final Context applicationContext;

        @Inject
        Callback(@ApplicationContext Context context) {
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
            // fill up albums with a lists of songs

            fillZigmundAfraidAlbum(db);
        }

        private void fillZigmundAfraidAlbum(SupportSQLiteDatabase db) {
            String zigmundAfraidBandName = applicationContext.getString(R.string.band_zigmund_afraid);

            ContentValues abroadContentValues = new ContentValues();
            abroadContentValues.put(SongEntity.COLUMN_ID, 0);
            abroadContentValues.put(SongEntity.COLUMN_BAND, zigmundAfraidBandName);
            abroadContentValues.put(SongEntity.COLUMN_ALBUM, zigmundAfraidBandName);
            abroadContentValues.put(SongEntity.COLUMN_NAME, applicationContext.getString(R.string.song_name_abroad));
            abroadContentValues.put(SongEntity.COLUMN_IMAGE_RES_ID, R.drawable.ic_za);
            abroadContentValues.put(SongEntity.COLUMN_AUDIO_LINK, applicationContext.getString(R.string.audio_abroad));
            db.insert(TABLE_SONGS, OnConflictStrategy.REPLACE, abroadContentValues);

            ContentValues abroadRmxContentValues = new ContentValues();
            abroadRmxContentValues.put(SongEntity.COLUMN_ID, 1);
            abroadRmxContentValues.put(SongEntity.COLUMN_BAND, zigmundAfraidBandName);
            abroadRmxContentValues.put(SongEntity.COLUMN_ALBUM, zigmundAfraidBandName);
            abroadRmxContentValues.put(SongEntity.COLUMN_NAME, applicationContext.getString(R.string.song_name_abroad_rmx));
            abroadRmxContentValues.put(SongEntity.COLUMN_IMAGE_RES_ID, R.drawable.vt_dnb120);
            abroadRmxContentValues.put(SongEntity.COLUMN_AUDIO_LINK, applicationContext.getString(R.string.audio_abroad_rmx));
            db.insert(TABLE_SONGS, OnConflictStrategy.REPLACE, abroadRmxContentValues);

            ContentValues pleasureWasMineContentValues = new ContentValues();
            pleasureWasMineContentValues.put(SongEntity.COLUMN_ID, 2);
            pleasureWasMineContentValues.put(SongEntity.COLUMN_BAND, zigmundAfraidBandName);
            pleasureWasMineContentValues.put(SongEntity.COLUMN_ALBUM, zigmundAfraidBandName);
            pleasureWasMineContentValues.put(SongEntity.COLUMN_NAME, applicationContext.getString(R.string.song_name_pleasure_was_mine));
            pleasureWasMineContentValues.put(SongEntity.COLUMN_IMAGE_RES_ID, R.drawable.pwm);
            pleasureWasMineContentValues.put(SongEntity.COLUMN_AUDIO_LINK, applicationContext.getString(R.string.audio_pleasure_was_mine));
            db.insert(TABLE_SONGS, OnConflictStrategy.REPLACE, pleasureWasMineContentValues);
        }
    }
}
