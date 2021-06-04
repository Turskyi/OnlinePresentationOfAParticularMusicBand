package com.music.android.sensilence.domain;

/**
 * {@link Song} represents an album song that the user wants to listen.
 * It contains a default Name of the album and a  song for that album.
 */
public class Song {
    /**
     * Name of the music band
     */
    private final String mNameOfTheBand;

    /**
     * name of the song from the album
     */
    private final String songName;

    /**
     * Image resource ID for the song
     */
    private final int mImageResourceID;

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
    public Song(String nameOfTheBand, String defaultSong, int imageResourceId, String audioResourceId) {
        songName = defaultSong;
        mNameOfTheBand = nameOfTheBand;
        mAudioResourceId = audioResourceId;
        mImageResourceID = imageResourceId;
    }

    public Song(String nameOfTheBand, String defaultSong, int imageResourceId, int audioResourceId) {
        songName = defaultSong;
        mNameOfTheBand = nameOfTheBand;
        mMp3ResourceId = audioResourceId;
        mImageResourceID = imageResourceId;
    }

    /**
     * Get the default song of the band.
     */
    public String getDefaultSong() {
        return songName;
    }

    /**
     * Get the name of the band.
     */
    public String getNameOfTheBand() {
        return mNameOfTheBand;
    }

    /**
     * Return the image resource ID of the song.
     */
    public int getImageResourceId() {
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
     */
    public String getAudioResourceId() {
        return mAudioResourceId;
    }
    public int getMp3ResourceId() {
        return mMp3ResourceId;
    }
}