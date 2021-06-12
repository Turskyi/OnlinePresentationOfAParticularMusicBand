package com.music.android.sensilence.domain.usecase;

import com.music.android.sensilence.domain.entities.pojo.Song;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public interface GetSongsUseCase {

    Disposable getDisposableSongs(
            String album,
            Consumer<List<Song>> successConsumer,
            Consumer<String> errorConsumer
    );
}
