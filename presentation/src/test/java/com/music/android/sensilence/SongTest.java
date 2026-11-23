package com.music.android.sensilence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.github.turskyi.domain.entities.pojo.Song;

public class SongTest {

    @Test
    public void testSongGetters() {
        Song song = new Song(
                "Sense of Silence",
                "Відчуття.Тиші LP",
                "Intro",
                12345,
                "image_link",
                "audio_link"
        );

        assertEquals("Sense of Silence", song.getNameOfTheBand());
        assertEquals("Відчуття.Тиші LP", song.getAlbum());
        assertEquals("Intro", song.getNameOfTheSong());
        assertEquals(12345, song.getImageResourceId());
        assertEquals("audio_link", song.getAudioLink());
        assertTrue(song.hasImage());
    }

    @Test
    public void testSongWithoutImage() {
        Song song = new Song(
                "Sense of Silence",
                "Відчуття.Тиші LP",
                "Intro",
                -1,
                "",
                "audio_link"
        );

        assertFalse(song.hasImage());
    }
}
