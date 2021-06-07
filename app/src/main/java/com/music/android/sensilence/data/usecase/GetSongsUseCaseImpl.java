package com.music.android.sensilence.data.usecase;

import com.music.android.sensilence.domain.pojo.Song;
import com.music.android.sensilence.domain.repository.SongsRepository;
import com.music.android.sensilence.domain.usecase.GetSongsUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GetSongsUseCaseImpl implements GetSongsUseCase {
    private final SongsRepository repository;

    private final Scheduler subscribeOnScheduler;

    private final Scheduler observeOnScheduler;

    @Inject
    GetSongsUseCaseImpl(SongsRepository repository) {
        this.repository = repository;
        this.subscribeOnScheduler = Schedulers.io();
        this.observeOnScheduler = AndroidSchedulers.mainThread();
    }

    @Override
    public Disposable getDisposableSongs(
            String album,
            Consumer<List<Song>> successConsumer,
            Consumer<String> errorConsumer
    ) {

        return repository
                .getSongsFromAlbum(album)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .map((entities) -> {
                            List<Song> songs = new ArrayList<>();
                            for (int i = 0; i < entities.size(); i++) {
                                Song song = entities.get(i).mapToDomain();
                                songs.add(song);
                            }
                            return songs;
                        }
                ).subscribe(
                        successConsumer,
                        (error) -> errorConsumer.accept(error.getLocalizedMessage())
                );

    }
}
