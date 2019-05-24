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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class CrimeActivity extends AppCompatActivity {
    MusicAlbum musicAlbum;
    ProgressBar progressBar;
    ImageView imageView;
    ListView listView;
    private MediaPlayer mMediaPlayer;

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

        songs.add(new Song("відчуття.тиші", "До Астарти", R.drawable.crime,
                "https://mp3-tut.com/musictutplay?id=317906193_456239502&hash=8bdc55622c5d183fa40397e070f84ace2b5a224f7e90d439cf1458ec8520306d&artist=%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96&title=%D0%94%D0%BE+%D0%90%D1%81%D1%82%D0%B0%D1%80%D1%82%D0%B8&download=1"));
        songs.add(new Song("відчуття.тиші", "angelscream", R.drawable.crime,
                "https://mp3-tut.com/musictutplay?id=2000207615_456240114&hash=dd3cb727e366661f9b5b6f5874062839dc47153054185e91b467fd92e068bf6e&artist=%D0%92%D1%96%D0%B4%D1%87%D1%83%D1%82%D1%82%D1%8F.%D0%A2%D0%B8%D1%88%D1%96&title=angelscream&download=1"));
        songs.add(new Song("відчуття.тиші", "Зомбі (album version)", R.drawable.crime,
                "https://cs1.djbag.biz/download/36310910/bXBhNTVGU1RONXozNnlSZFM3MmFKRHJNaFRDdTdObFF4UHBkL3lIdW00cjdBc2pwKzBhZHNiK3QyMGgyb3M1Y0JYdTRPNzdFMzYrZmlyZGQ0YXpETmRmTG1hVHZzM2p0MjBKUndDY0tvdjlrai9HOFY1cVRjNXM4cnZRcUdRV1E/Vdchuttya_Tish_Zomb_(djbag.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Не хотів ", R.drawable.crime,
                "https://cs1.djbag.biz/download/36310917/cEFrVDk4NDZyRndpU2xhY3VKRWJ5NzVYSTlUZ3psQjZDTlFtaEFsVXhuc2I4dFI2dVJka3JPWGVLbE9YUk5SaEMyYmkzT1RwSUp5b3NUeUh5WmNNd2IyZnZzWm1ZTFlsNEd1TmlYLytGSUoxQW8yQ1Z0SzBIc2xRN1JQcTN5MU8/Vdchuttya_Tish_Ne_khotv_(djbag.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Злочин", R.drawable.crime,
                "https://cs1.djbag.biz/download/36310919/bXBhNTVGU1RONXozNnlSZFM3MmFKRHJNaFRDdTdObFF4UHBkL3lIdW00cmdwQ1lTdUdFT3l1aVVhNXR5c2hCNEJXNjRlcm4vazk2L1k5MHh3NWFUM3pDaGR5K0ZhR2txTTVQanlOcUNtUlRCMlZEZVNiS1RGZjhkMDF6ZDlyeTc/Vdchuttya_Tish_Zlochin_(djbag.biz).mp3"));
        songs.add(new Song("відчуття.тиші", "Noli Respicere (Culturno rmx)", R.drawable.vt_dnb120,
                "https://cs1.djbag.biz/download/36310915/bXBhNTVGU1RONXozNnlSZFM3MmFKRHJNaFRDdTdObFF4UHBkL3lIdW00bzZLYXNQaUNjUmRBTzh0akk0ekJIdjhxNDMzRDJrMyt4N1JpeGNORVUvMjJjd3NXS285OHZuRGhjcyt4TGE4ZlkxVXNSZWlRSGNVaXJQZmk2cXBwaFU/Vdchuttya_Tish_Noli_Respicere_Culturno_rmx_Dub_step_Ukraine_(djbag.biz).mp3"));
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
            if (mMediaPlayer != null && imageView == view.findViewById(R.id.btn_image)) {
                musicAlbum.play(view,progressBar,mMediaPlayer,mCompletionListener,secondClickListener, listView);
            } else {
            progressBar = view.findViewById(R.id.loading_spinner);
            progressBar.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                public void run() {
                    //do time consuming operations
                    if (musicAlbum.isOnline()) {
                        //Get the {@link Song} object at the given position the user clicked on
                        final Song song = songs.get(position);

                        //Release the media player if it currently exists.
                        musicAlbum.releaseMediaPlayer();

                        //Request audio focus for playback
                        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                                //Use the music stream.
                                AudioManager.STREAM_MUSIC,
                                //Request permanent focus.
                                AudioManager.AUDIOFOCUS_GAIN);
                        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                            //We have an audio focus now.

//                Create and setup the {@link MedeaPlayer} for the audio resource associated
//                with the current song
                            String url = song.getmAudioResourceId(); // my URL here
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
                                musicAlbum.errorAlert( song, CrimeActivity.this);
                            }
                            //                Start the audio file
                            musicAlbum.play( view, progressBar,  mMediaPlayer, mCompletionListener, secondClickListener, listView);
                        }
                    } else {
                        CrimeActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(CrimeActivity.this,
                                        "Немає інтернету", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                }
            }).start();
        }
        }
    };
    AdapterView.OnItemClickListener secondClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mMediaPlayer.pause();
            imageView = view.findViewById(R.id.btn_image);
            imageView.setImageResource(R.drawable.ic_play_arrow);
            listView.setOnItemClickListener(firstClickListener);
        }
    };
}