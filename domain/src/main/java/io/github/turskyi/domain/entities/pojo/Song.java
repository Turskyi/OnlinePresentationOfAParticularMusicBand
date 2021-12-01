package io.github.turskyi.domain.entities.pojo;

/**
 * {@link Song} represents an album song that the user wants to listen.
 * It contains a Name of the song from the album and a  song from that album.
 */
public class Song {
    /**
     * Name of the music band
     */
    private final String nameOfTheBand;

    /**
     * name of the song from the album
     */
    private final String songName;

    /**
     * name of the album
     */
    private final String album;

    /**
     * Image resource ID for the song
     */
    private final int imageResourceID;

    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Audio resource for the song
     */
    private final String audioLink;

    /**
     * Create a new Song object.
     *
     * @param nameOfTheBand is the name of the band
     * @param songName      is the song from the album
     * @param audioLink     is the resource link for the audio file associated with song.
     */
    public Song(String nameOfTheBand,
                String album,
                String songName,
                int imageResourceId,
                String audioLink
    ) {
        this.songName = songName;
        this.nameOfTheBand = nameOfTheBand;
        this.album = album;
        this.audioLink = audioLink;
        this.imageResourceID = imageResourceId;
    }

    /**
     * Get the song's name from the album.
     */
    public String getNameOfTheSong() {
        return songName;
    }

    /**
     * Get the name of the band.
     */
    public String getNameOfTheBand() {
        return nameOfTheBand;
    }

    /**
     * Get the name of the album.
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Return the image resource ID of the song.
     */
    public int getImageResourceId() {
        return imageResourceID;
    }

    /**
     * Returns whether there is an image for this song.
     */
    public boolean hasImage() {
        return imageResourceID != NO_IMAGE_PROVIDED;
    }

    /**
     * Returns the audio link of the song.
     */
    public String getAudioLink() {
        return audioLink;
    }
}