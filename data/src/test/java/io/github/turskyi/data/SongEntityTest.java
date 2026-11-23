package io.github.turskyi.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import io.github.turskyi.data.entity.SongEntity;
import io.github.turskyi.domain.entities.pojo.Song;

public class SongEntityTest {

    @Test
    public void testMapToDomain() {
        SongEntity entity = new SongEntity();
        entity.setId(1);
        entity.setBandName("Sense of Silence");
        entity.setAlbum("Злочин");
        entity.setSongName("Test Song");
        entity.setImageResourceId(999);
        entity.setImageResource("res");
        entity.setAudioLink("http://audio");

        Song song = entity.mapToDomain();

        assertNotNull(song);
        assertEquals("Sense of Silence", song.getNameOfTheBand());
        assertEquals("Злочин", song.getAlbum());
        assertEquals("Test Song", song.getNameOfTheSong());
        assertEquals(999, song.getImageResourceId());
        assertEquals("http://audio", song.getAudioLink());
    }
}
