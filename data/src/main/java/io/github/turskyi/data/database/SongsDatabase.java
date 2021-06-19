package io.github.turskyi.data.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.github.turskyi.data.R;
import io.github.turskyi.data.entity.SongEntity;

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

//            set values for ZigmundAfraid album
            List<String> names = Arrays.asList(
                    applicationContext.getString(R.string.song_name_abroad),
                    applicationContext.getString(R.string.song_name_abroad_rmx),
                    applicationContext.getString(R.string.song_name_pleasure_was_mine)
            );
            List<Integer> imageResources = Arrays.asList(
                    R.drawable.ic_za,
                    R.drawable.vt_dnb120,
                    R.drawable.pwm
            );
            List<String> links = Arrays.asList(
                    applicationContext.getString(R.string.audio_abroad),
                    applicationContext.getString(R.string.audio_abroad_rmx),
                    applicationContext.getString(R.string.audio_pleasure_was_mine)
            );
            // fill up albums with a lists of songs
            fillAlbum(
                    db,
                    applicationContext.getString(R.string.band_zigmund_afraid),
                    names,
                    imageResources,
                    links,
                    0
            );
        }

        private void fillAlbum(
                SupportSQLiteDatabase db,
                String bandName,
                List<String> names,
                List<Integer> imageResources,
                List<String> links,
                int startIndex
        ) {

            for (int i = startIndex; i < names.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(SongEntity.COLUMN_ID, i);
                contentValues.put(SongEntity.COLUMN_BAND, bandName);
                contentValues.put(SongEntity.COLUMN_ALBUM, bandName);
                contentValues.put(SongEntity.COLUMN_NAME, names.get(i));
                contentValues.put(SongEntity.COLUMN_IMAGE_RES_ID, imageResources.get(i));
                contentValues.put(SongEntity.COLUMN_AUDIO_LINK, links.get(i));
                db.insert(TABLE_SONGS, OnConflictStrategy.REPLACE, contentValues);
            }
        }
    }
}
