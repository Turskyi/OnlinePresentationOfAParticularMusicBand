package io.github.turskyi.data.usecase;

import java.util.List;

import javax.inject.Inject;

import io.github.turskyi.domain.entities.pojo.Song;
import io.github.turskyi.domain.repository.SongsRepository;
import io.github.turskyi.domain.usecase.GetSongsUseCase;
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
                .map((songs) -> songs)
                .subscribe(
                        successConsumer,
                        (error) -> errorConsumer.accept(error.getLocalizedMessage())
                );

    }
}
