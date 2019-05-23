package com.music.android.sensilence;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        //The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus
                        //short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means
                        //our app is allowed to continue playing sound but at a lower volume.

                        //Pause playback and reset player to the start of the file. That way, when
                        //play the song from the beginning when we resume playback.
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //The AUDIOFOCUS_GAIN case means we have regained focus and can
                        //resume playback
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        //The AUDIOFOCUS_LOSS case means we've lost audio focus and
                        //stop playback and cleanup resources
                        releaseMediaPlayer();
                    }
                }
            };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
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

    /**
     * Checks the device is online or not
     */
    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    AdapterView.OnItemClickListener firstClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            progressBar = view.findViewById(R.id.loading_spinner);
            progressBar.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                public void run() {
                    //do time consuming operations
                    if (isOnline()) {
                        //Get the {@link Song} object at the given position the user clicked on
                        final Song song = songs.get(position);

                        //Release the media player if it currently exists.
                        releaseMediaPlayer();

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
                                CrimeActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Intent lastIntent = new Intent(CrimeActivity.this,
                                                MyService.class);
                                        startService(lastIntent);
                                        stopService(lastIntent);
                                        AlertDialog lastDialog =
                                                new AlertDialog.Builder(CrimeActivity.this).setTitle(
                                                        "Трапилось щось страшне!").setMessage("Хочете написати розробнику?")
                                                        .setCancelable(false).setPositiveButton("Так",
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                                                        "mailto", "dmitriy.turskiy@gmail.com", ""));
                                                                intent.putExtra(Intent.EXTRA_SUBJECT, "Страшна історія яка трапилася з піснею " + song.getDefaultSong());
                                                                if (intent.resolveActivity(getPackageManager()) != null) {
                                                                    startActivity(intent);
                                                                }
                                                            }
                                                        }).setNegativeButton("Ні", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        Toast.makeText(CrimeActivity.this,
                                                                "Тоді спробуйте завтра ;)", Toast.LENGTH_SHORT).show();
                                                        dialog.cancel();
                                                    }
                                                }).create();
                                        lastDialog.show();
                                    }
                                });
                            }
                            //                Start the audio file
                            mMediaPlayer.start();
                            progressBar.setVisibility(View.INVISIBLE);
                            imageView = view.findViewById(R.id.btn_image);
                            imageView.setImageResource(R.drawable.ic_pause);

                            //Setup a listener on the media player, so that we can stop and release the
                            //media player once the sounds has finished
                            mMediaPlayer.setOnCompletionListener(mCompletionListener);
                            listView.setOnItemClickListener(secondClickListener);

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
    };
    AdapterView.OnItemClickListener secondClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mMediaPlayer.pause();
            imageView.setImageResource(R.drawable.ic_play_arrow);
            listView.setOnItemClickListener(firstClickListener);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        //When the activity is stopped, release the media player resources because we won't
        //be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            //Regardless of whether or not we were granted audio focus, abandon it. This also
            //unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}