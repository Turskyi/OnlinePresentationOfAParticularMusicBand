package com.example.android.sensilence;

import android.net.Uri;

/**
 * {@link Song} represents a album song that the user wants to listen.
 * It contains a default Name of the album and a  song for that album.
 */

@SuppressWarnings("DanglingJavadoc")
public class Song {
    /** Default song for the album */
    private String mDefaultSong;

    /** Name of the album */
    private String mNameOfTheBand;

    /**Image resource ID for the song*/
private int mImageResourceID = NO_IMAGE_PROVIDED;

private static final int NO_IMAGE_PROVIDED = -1;

/** Audio resource ID for the word */
private String mAudioResourceId;

    /**
     * Create a new Song object.
     *  @param nameOfTheBand is the name of the band
     * @param defaultSong is the song that the user is already familiar with
     * @param audioResourceId is the resource ID for the audio file associated with word.
     * @param s
     */
//   public Song(String nameOfTheBand, String defaultSong, int audioResourceId, String s) {
//        mDefaultSong = defaultSong;
//        mNameOfTheBand = nameOfTheBand;
//        mAudioResourceId = audioResourceId;
//    }

    /**
     * Create a new Song object.
     *
     * @param defaultSong is the song that the user is already familiar with
     * @param nameOfTheBand is the name of the band
     *
     */
    public Song(String nameOfTheBand, String defaultSong, int imageResourceId, String audioResourceId) {
        mDefaultSong = defaultSong;
        mNameOfTheBand = nameOfTheBand;
        mImageResourceID = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Get the default song of the band.
     */
    String getDefaultSong() {
        return mDefaultSong;
    }

    /**
     * Get the name of the band.
     */
    String getNameOfTheBand() {
        return mNameOfTheBand;
    }

    /**
     * Return the image resource ID of the word.
      */
    public int getImageResourceId(){
        return mImageResourceID;
    }

    /**
     * Returns whether or not there is an image for this song.
     */
    public boolean hasImage() {
return mImageResourceID != NO_IMAGE_PROVIDED;
    }

    /**
     * Return the audio resource ID of the song.
     * @return
     */
    public String getmAudioResourceId() {
        return mAudioResourceId;
    }

//    public int getmAudioResourceId() {
//        return mAudioResourceId;
//    }
}