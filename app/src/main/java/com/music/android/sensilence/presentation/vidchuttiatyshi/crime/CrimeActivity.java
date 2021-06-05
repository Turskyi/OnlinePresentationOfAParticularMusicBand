package com.music.android.sensilence.presentation.vidchuttiatyshi.crime;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.music.android.sensilence.presentation.common.MusicPlayerActivity;
import com.music.android.sensilence.R;
import com.music.android.sensilence.domain.Song;
import com.music.android.sensilence.presentation.common.adapters.SongAdapter;

import java.util.ArrayList;

public class CrimeActivity extends AppCompatActivity {
    private MusicPlayerActivity musicPlayerActivity;
    private ListView listView;
    protected MediaPlayer mediaPlayer;

    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager audioManager;

    /**
     * Creating an empty array of songs
     */
    private final ArrayList<Song> songs = new ArrayList<>();

    private final AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    musicPlayerActivity.onFocusChange(focusChange, mediaPlayer);
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
                    musicPlayerActivity.releaseMediaPlayer();
                }
            };

    private final AdapterView.OnItemClickListener firstClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(
                AdapterView<?> parent,
                final View view,
                final int position,
                long id
        ) {
            musicPlayerActivity.onFirstClick(
                    view,
                    position,
                    onAudioFocusChangeListener,
                    completionListener,
                    secondClickListener,
                    listView,
                    songs,
                    audioManager,
                    CrimeActivity.this
            );
        }
    };

    private final AdapterView.OnItemClickListener secondClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            musicPlayerActivity.onSecondClick(firstClickListener, listView);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);
        musicPlayerActivity = new MusicPlayerActivity();
        //Create and setup the {@link AudioManager} to request audio focus
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        setBackground();

//        fill up album of songs
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.song_name_to_astarta),
                        R.drawable.crime,
                        getString(R.string.audio_to_astarta)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.song_name_angelscream),
                        R.drawable.crime,
                        getString(R.string.audio_angelscream)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.song_name_zombi_album),
                        R.drawable.crime,
                        getString(R.string.audio_zombi_album_version)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.song_name_did_not_want),
                        R.drawable.crime,
                        getString(R.string.audio_did_not_want)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.song_name_crime),
                        R.drawable.crime,
                        getString(R.string.audio_crime)
                )
        );
        songs.add(
                new Song(
                        getString(R.string.band_sense_of_silence),
                        getString(R.string.song_name_noli_respicere_rmx),
                        R.drawable.vt_dnb120,
                        getString(R.string.audio_noli_respicere_rmx)
                )
        );
        /* Create an {@link SongAdapter}, whose data source is a list of {@link Song}s.
         * The adapter knows how to create list items for each item in the list. */
        SongAdapter adapter = new SongAdapter(this, songs, R.color.album_crime_color);
        listView.setAdapter(adapter);
        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(firstClickListener);
    }

    private void setBackground() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo_black350);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), scalePreserveRatio(bmp, bmp.getWidth()));
        bitmapDrawable.setGravity(Gravity.NO_GRAVITY | Gravity.FILL_HORIZONTAL);
        listView = findViewById(R.id.list);
        listView.setBackground(bitmapDrawable);
    }

    /**
     * Scale the image preserving the ratio
     *
     * @param imageToScale     Image to be scaled
     * @param destinationWidth Destination width after scaling
     * @return New scaled bitmap preserving the ratio
     */
    private Bitmap scalePreserveRatio(Bitmap imageToScale, int destinationWidth) {
        if (destinationWidth > 0 && imageToScale != null) {
            int width = imageToScale.getWidth();
            int height = imageToScale.getHeight();

            //Calculate the max changing amount and decide which dimension to use
            float widthRatio = (float) destinationWidth / (float) width;
            int destinationHeight = 1500;
            float heightRatio = (float) destinationHeight / (float) height;

            //Use the ratio that will fit the image into the desired sizes
            int finalWidth = (int) Math.floor(width * widthRatio);
            int finalHeight = (int) Math.floor(height * widthRatio);
            if (finalWidth > destinationWidth || finalHeight > destinationHeight) {
                finalWidth = (int) Math.floor(width * heightRatio);
                finalHeight = (int) Math.floor(height * heightRatio);
            }

            //Scale given bitmap to fit into the desired area
            imageToScale = Bitmap
                    .createScaledBitmap(imageToScale, finalWidth, finalHeight, true);

            //Created a bitmap with desired sizes
            Bitmap scaledImage = Bitmap
                    .createBitmap(destinationWidth, destinationHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(scaledImage);

            //Draw background color
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

            //Calculate the ratios and decide which part will have empty areas (width or height)
            float ratioBitmap = (float) finalWidth / (float) finalHeight;
            float destinationRatio = (float) destinationWidth / (float) destinationHeight;
            float left = ratioBitmap >= destinationRatio ? 0 : (float) (destinationWidth - finalWidth) / 2;
            float top = ratioBitmap < destinationRatio ? 0 : (float) (destinationHeight - finalHeight) / 2;
            canvas.drawBitmap(imageToScale, left, top, null);
            return scaledImage;
        } else {
            return imageToScale;
        }
    }
}