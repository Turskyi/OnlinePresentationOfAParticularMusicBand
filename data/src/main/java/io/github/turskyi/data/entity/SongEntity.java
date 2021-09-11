package io.github.turskyi.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.github.turskyi.domain.entities.pojo.Song;

/**
 * {@link SongEntity} represents an album song that the user wants to listen.
 * It contains a Name of the song from the album and a  song from that album.
 */
@Entity(tableName = SongEntity.TABLE_SONGS)
public class SongEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    private int id = 0;
    /**
     * Name of the music band
     */
    @ColumnInfo(name = COLUMN_BAND)
    private String bandName;

    /**
     * Name of the album
     */
    @ColumnInfo(name = COLUMN_ALBUM)
    private String album;

    /**
     * name of the song from the album
     */
    @ColumnInfo(name = COLUMN_NAME)
    private String songName;

    /**
     * Image resource ID for the song
     */
    @ColumnInfo(name = COLUMN_IMAGE_RES_ID)
    private int imageResourceId;

    /**
     * Audio resource for the song
     */
    @ColumnInfo(name = COLUMN_AUDIO_LINK)
    private String audioLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the name of the band.
     */
    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    /**
     * Get album name.
     */
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * Get the song's name from the album.
     */
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     * Return the image resource ID of the song.
     */
    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    /**
     * Returns the audio link of the song.
     */
    public String getAudioLink() {
        return audioLink;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public static final String TABLE_SONGS = "table_songs";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BAND = "band";
    public static final String COLUMN_ALBUM = "album";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGE_RES_ID = "image_resource_id";
    public static final String COLUMN_AUDIO_LINK = "audio_link";

    public Song mapToDomain() {
        return new Song(
                this.bandName,
                this.album,
                this.songName,
                this.imageResourceId,
                this.audioLink
        );
    }
}
