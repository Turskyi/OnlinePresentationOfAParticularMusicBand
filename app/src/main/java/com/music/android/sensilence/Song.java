package com.music.android.sensilence;

/**
 * {@link Song} represents a album song that the user wants to listen.
 * It contains a default Name of the album and a  song for that album.
 */

class Song {
    /**
     * Default song for the album
     */
    private String mDefaultSong;

    /**
     * Name of the album
     */
    private String mNameOfTheBand;

    /**
     * Image resource ID for the song
     */
    private int mImageResourceID;

    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Audio resource ID for the song
     */
    private String mAudioResourceId;
    private int mMp3ResourceId;

    /**
     * Create a new Song object.
     *
     * @param nameOfTheBand   is the name of the band
     * @param defaultSong     is the song that the user is already familiar with
     * @param audioResourceId is the resource ID for the audio file associated with song.
     */
    Song(String nameOfTheBand, String defaultSong, int imageResourceId, String audioResourceId) {
        mDefaultSong = defaultSong;
        mNameOfTheBand = nameOfTheBand;
        mAudioResourceId = audioResourceId;
        mImageResourceID = imageResourceId;
    }

    Song(String nameOfTheBand, String defaultSong, int imageResourceId, int audioResourceId) {
        mDefaultSong = defaultSong;
        mNameOfTheBand = nameOfTheBand;
        mMp3ResourceId = audioResourceId;
        mImageResourceID = imageResourceId;
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
     * Return the image resource ID of the song.
     */
    int getImageResourceId() {
        return mImageResourceID;
    }

    /**
     * Returns whether or not there is an image for this song.
     */
    boolean hasImage() {
        return mImageResourceID != NO_IMAGE_PROVIDED;
    }

    /**
     * Return the audio resource ID of the song.
     */
    String getmAudioResourceId() {
        return mAudioResourceId;
    }
    int getmMp3ResourceId() {
        return mMp3ResourceId;
    }
}