package com.example.android.sensilence;

/**
 * {@link Song} represents a album song that the user wants to listen.
 * It contains a default Name of the album and a  song for that album.
 */

@SuppressWarnings("DanglingJavadoc")
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
    private int mImageResourceID = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Audio resource ID for the word
     */
    private String mAudioResourceId;

    private int mBtnResourceID;

//    /**
//     * Create a new Song object.
//     *  @param nameOfTheBand is the name of the band
//     * @param defaultSong is the song that the user is already familiar with
//     * @param audioResourceId is the resource ID for the audio file associated with word.
//     */
//   public Song(String nameOfTheBand, String defaultSong, String audioResourceId) {
//        mDefaultSong = defaultSong;
//        mNameOfTheBand = nameOfTheBand;
//        mAudioResourceId = audioResourceId;
//    }

    /**
     * Create a new Song object.
     *
     * @param defaultSong   is the song that the user is already familiar with
     * @param nameOfTheBand is the name of the band
     */
    Song(String nameOfTheBand, String defaultSong, int imageResourceId, String audioResourceId, int btnResourceId) {
        mDefaultSong = defaultSong;
        mNameOfTheBand = nameOfTheBand;
        mImageResourceID = imageResourceId;
        mAudioResourceId = audioResourceId;
        mBtnResourceID = btnResourceId;
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
    int getImageResourceId() {
        return mImageResourceID;
    }

    int getBtnResourceID() {
        return mBtnResourceID;
    }

    public void setmBtnResourceID(int mBtnResourceID) {
        this.mBtnResourceID = mBtnResourceID;
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
}