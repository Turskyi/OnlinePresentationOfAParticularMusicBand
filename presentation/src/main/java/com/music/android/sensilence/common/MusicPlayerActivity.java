package com.music.android.sensilence.common;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.music.android.sensilence.R;

import java.io.IOException;
import java.util.ArrayList;

import io.github.turskyi.domain.entities.pojo.Song;

public class MusicPlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ImageView imageView;
    private ProgressBar progressBar;

    public MusicPlayerActivity() {
    }

    @Override
    public void onStop() {
        super.onStop();

        /* When the activity is stopped, release the media player resources because we won't
         * be playing any more sounds. */
        releaseMediaPlayer();
    }

    public void onFirstClick(
            final View view,
            final int position,
            final AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener,
            final MediaPlayer.OnCompletionListener mCompletionListener,
            final AdapterView.OnItemClickListener secondClickListener,
            final ListView listView,
            final ArrayList<Song> songs,
            final AudioManager audioManager,
            final Activity activity
    ) {
        if (mediaPlayer != null && imageView == view.findViewById(R.id.iv_btn_play)) {
            play(view, progressBar, mediaPlayer, mCompletionListener, secondClickListener, listView);
        } else {
            progressBar = view.findViewById(R.id.loading_spinner);
            progressBar.setVisibility(View.VISIBLE);
            new Thread(() -> {
                //do time consuming operations
                if (isOnline()) {
                    //Get the {@link Song} object at the given position the user clicked on
                    final Song song = songs.get(position);

                    /* Release the media player if it currently exists because we are about to
                     * play a different sound file. */
                    releaseMediaPlayer();
                    //Request audio focus for playback
                    int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                            //Use the music stream.
                            AudioManager.STREAM_MUSIC,
                            //Request permanent focus.
                            AudioManager.AUDIOFOCUS_GAIN);
                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        //We have an audio focus now.

/*                Create and setup the {@link MedeaPlayer} for the audio resource associated
                with the current song */
                        String url;
                        url = song.getAudioLink(); // your URL here
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mediaPlayer.setDataSource(url);
                        } catch (NullPointerException | IOException e) {
                            errorAlert(song, activity);
                        }
                        try {
                            // "mediaPlayer.prepare" might take long! (for buffering, etc)
                            mediaPlayer.prepare();
                        } catch (IllegalStateException e) {
                            play(
                                    view,
                                    progressBar,
                                    mediaPlayer,
                                    mCompletionListener,
                                    secondClickListener,
                                    listView
                            );
                        } catch (IOException e) {
                            errorAlert(song, activity);
                        }
                        // Start the audio file
                        play(
                                view,
                                progressBar,
                                mediaPlayer,
                                mCompletionListener,
                                secondClickListener,
                                listView
                        );
                    }
                } else {
                    activity.runOnUiThread(() -> {
                        Toast.makeText(
                                activity, getString(R.string.error_no_internet), Toast.LENGTH_LONG
                        ).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    });
                }
            }).start();
        }
    }

    public void onSecondClick(
            android.widget.AdapterView.OnItemClickListener firstClickListener,
            ListView listView
    ) {
        mediaPlayer.pause();
        imageView.setImageResource(R.drawable.ic_play_arrow);
        listView.setOnItemClickListener(firstClickListener);
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    public void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            /* Regardless of the current state of the media player, release its resources
             * because we no longer need it. */
            mediaPlayer.release();

            /* Set the media player back to null. For our code, we've decided that
             * setting the media player to null is an easy way to tell that the media player
             * is not configured to play an audio file at the moment. */
            mediaPlayer = null;
        }
    }

    public void onFocusChange(int focusChange, MediaPlayer mMediaPlayer) {
        if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
            /* The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus
             * short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means
             * our app is allowed to continue playing sound but at a lower volume. */

            /* Pause playback and reset player to the start of the file. That way, when
             * play the song from the beginning when we resume playback. */
            mMediaPlayer.pause();
            mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition());
        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
            /* The AUDIOFOCUS_GAIN case means we have regained focus and can
             * resume playback */
            mMediaPlayer.start();
        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            /* The AUDIOFOCUS_LOSS case means we've lost audio focus and
             * stop playback and cleanup resources */
            releaseMediaPlayer();
        }
    }

    //just a note: do not remove media player from parameters
    private void play(
            View view,
            ProgressBar progressBar,
            MediaPlayer mMediaPlayer,
            MediaPlayer.OnCompletionListener mCompletionListener,
            Object secondClickListener,
            AdapterView<?> listView
    ) {
        mMediaPlayer.start();
        runOnUiThread(() -> {
            progressBar.setVisibility(View.INVISIBLE);
            imageView = view.findViewById(R.id.iv_btn_play);
            imageView.setImageResource(R.drawable.ic_pause);
        });

        /* Setup a listener on the media player, so that we can stop and release the
         * media player once the sounds has finished */
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) secondClickListener);
    }

    /**
     * Checks the device is online or not
     */
    private boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void errorAlert(final Song song, final Activity activity) {
        activity.runOnUiThread(() -> {

            AlertDialog lastDialog =
                    new AlertDialog.Builder(activity)
                            .setTitle(getString(R.string.alert_error_title))
                            .setMessage(getString(R.string.alert_error_message))
                            .setCancelable(false)
                            .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                                Intent intent = new Intent(
                                        Intent.ACTION_SENDTO,
                                        Uri.fromParts(
                                                getString(R.string.scheme_mailto),
                                                getString(R.string.email),
//    // Gets the decoded fragment part of this URI, everything after the '#', null if undefined.
                                                null
                                        )
                                );
                                intent.putExtra(
                                        Intent.EXTRA_SUBJECT,
                                        getString(R.string.error_message_intro)
                                                + song.getNameOfTheSong()
                                );
                                if (intent.resolveActivity(activity.getPackageManager()) != null) {
                                    activity.startActivity(intent);
                                }
                            }).setNegativeButton(getString(R.string.no), (dialog, id) -> {
                        Toast.makeText(
                                activity,
                                getString(R.string.error_message_try_tomorrow),
                                Toast.LENGTH_SHORT
                        ).show();
                        dialog.cancel();
                        Intent newIntent = new Intent(
                                activity,
                                activity.getClass()
                        );
                        activity.startActivity(newIntent);
                    }).create();
            lastDialog.show();
        });
    }
}
