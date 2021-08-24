package io.github.turskyi.data.usecase;

import java.util.List;

import javax.inject.Inject;

import io.github.turskyi.domain.entities.pojo.Song;
import io.github.turskyi.domain.repository.SongsRepository;
import io.github.turskyi.domain.usecase.GetSongsUseCase;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GetSongsUseCaseImpl implements GetSongsUseCase {
    private final SongsRepository repository;

    private final io.reactivex.rxjava3.core.Scheduler subscribeOnScheduler;

    private final io.reactivex.rxjava3.core.Scheduler observeOnScheduler;

    @Inject
    GetSongsUseCaseImpl(SongsRepository repository) {
        this.repository = repository;
        this.subscribeOnScheduler = Schedulers.io();
        this.observeOnScheduler = AndroidSchedulers.mainThread();
    }

    @Override
    public io.reactivex.rxjava3.disposables.Disposable setSongs(
            String album,

            io.reactivex.rxjava3.functions.Consumer<List<Song>> successConsumer,
            io.reactivex.rxjava3.functions.Consumer<String> errorConsumer
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
