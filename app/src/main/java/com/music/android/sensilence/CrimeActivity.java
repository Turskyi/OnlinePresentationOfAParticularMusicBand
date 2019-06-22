package com.music.android.sensilence;

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
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class CrimeActivity extends AppCompatActivity {
    MusicAlbum musicAlbum;
    ListView listView;
    protected MediaPlayer mMediaPlayer;

    /*Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    musicAlbum.onFocusChange(focusChange, mMediaPlayer);
                }
            };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            musicAlbum.releaseMediaPlayer();
        }
    };

    // Create an array of songs
    final ArrayList<Song> songs = new ArrayList<>();

    /**
     * Scale the image preserving the ratio
     *
     * @param imageToScale      Image to be scaled
     * @param destinationWidth  Destination width after scaling
     * @param destinationHeight Destination height after scaling
     * @return New scaled bitmap preserving the ratio
     */
    public static Bitmap scalePreserveRatio(Bitmap imageToScale, int destinationWidth,
                                            int destinationHeight) {
        if (destinationHeight > 0 && destinationWidth > 0 && imageToScale != null) {
            int width = imageToScale.getWidth();
            int height = imageToScale.getHeight();

            //Calculate the max changing amount and decide which dimension to use
            float widthRatio = (float) destinationWidth / (float) width;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);
        musicAlbum = new MusicAlbum();
        //Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                R.drawable.logo_black350);


        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), scalePreserveRatio(bmp,
                bmp.getWidth(), 1500));
        bitmapDrawable.setGravity(Gravity.NO_GRAVITY | Gravity.FILL_HORIZONTAL);
        listView = findViewById(R.id.list);
        listView.setBackground(bitmapDrawable);

        songs.add(new Song("відчуття.тиші", "До Астарти",
                R.drawable.crime,R.raw.vdchuttya_tsh_do_astart));
        songs.add(new Song("відчуття.тиші", "angelscream", R.drawable.crime,
                R.raw.v_dchuttya_tish_angelscream));
        songs.add(new Song("відчуття.тиші", "Зомбі (album version)",
                R.drawable.crime,
                R.raw.vdchuttya_tish_zomb));
        songs.add(new Song("відчуття.тиші", "Не хотів ", R.drawable.crime,
                R.raw.vdchuttya_tish_ne_khotv));
        songs.add(new Song("відчуття.тиші", "Злочин", R.drawable.crime,
                R.raw.vdchuttya_tish_zlochin));
        songs.add(new Song("відчуття.тиші", "Noli Respicere (Culturno rmx)",
                R.drawable.vt_dnb120,
                R.raw.v_dchuttya_tish_noli_respicere_culturno_rmx_dub_step_ukraine));
        // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(this, songs, R.color.category_crime);
        listView.setAdapter(adapter);
        //Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(firstClickListener);
    }

    AdapterView.OnItemClickListener firstClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            musicAlbum.onFirstClick(view, position, mOnAudioFocusChangeListener, mCompletionListener,
                    secondClickListener, listView, songs, mAudioManager, CrimeActivity.this);
        }
    };

    AdapterView.OnItemClickListener secondClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            musicAlbum.onSecondClick(firstClickListener, listView);
        }
    };
}