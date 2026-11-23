package com.music.android.sensilence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.github.turskyi.domain.entities.enums.Album;

public class AlbumTest {

    @Test
    public void testAlbumValues() {
        assertEquals("Відчуття.Тиші LP", Album.SENSE_OF_SILENCE_LP.name);
        assertEquals("Злочин", Album.CRIME.name);
        assertEquals("Зомбі", Album.ZOMBI.name);
        assertEquals("Bonus Track", Album.BONUS.name);
        assertEquals("Zigmund Afraid", Album.ZIGMUND_AFRAID.name);
    }
}
