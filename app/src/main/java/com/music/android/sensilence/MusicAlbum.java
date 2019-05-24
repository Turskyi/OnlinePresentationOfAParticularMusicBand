package com.music.android.sensilence;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

@SuppressLint("Registered")
class MusicAlbum extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    MusicAlbum(MediaPlayer mMediaPlayer) {
        this.mMediaPlayer = mMediaPlayer;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    void play(View view, ProgressBar progressBar, MediaPlayer mMediaPlayer, MediaPlayer.OnCompletionListener mCompletionListener, Object secondClickListener, AdapterView listView) {
        mMediaPlayer.start();
        progressBar.setVisibility(View.INVISIBLE);
        ImageView imageView = view.findViewById(R.id.btn_image);
        imageView.setImageResource(R.drawable.ic_pause);
        //Setup a listener on the media player, so that we can stop and release the
        //media player once the sounds has finished
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) secondClickListener);
    }
    void onFocusChange(int focusChange, MediaPlayer mMediaPlayer) {
        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
            //The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus
            //short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means
            //our app is allowed to continue playing sound but at a lower volume.

            //Pause playback and reset player to the start of the file. That way, when
            //play the song from the beginning when we resume playback.
            mMediaPlayer.pause();
            mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition());
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

    /**
     * Checks the device is online or not
     */
    boolean isOnline() {
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

    void errorAlert(final Song song, final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Intent lastIntent = new Intent(activity,
                        MyService.class);
                activity.startService(lastIntent);
                activity.stopService(lastIntent);
                AlertDialog lastDialog =
                        new AlertDialog.Builder(  activity)
                                .setTitle("Трапилось щось страшне!")
                                .setMessage("Хочете написати розробнику?")
                                .setCancelable(false)
                                .setPositiveButton("Так", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Intent.ACTION_SENDTO,
                                                Uri.fromParts("mailto", "dmitriy.turskiy@gmail.com", ""));
                                        intent.putExtra(Intent
                                                        .EXTRA_SUBJECT,
                                                "Страшна історія яка трапилася з піснею " + song.getDefaultSong());
                                        if (intent.resolveActivity(activity.getPackageManager()) != null) {
                                            activity.startActivity(intent);
                                        }
                                    }
                                }).setNegativeButton("Ні", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText( activity,
                                        "Тоді спробуйте завтра ;)", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                                Intent newIntent = new Intent(
                                        activity,
                                        activity.getClass());
                                activity.startActivity(newIntent);
                            }
                        }).create();
                lastDialog.show();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
     releaseMediaPlayer();
    }
}
