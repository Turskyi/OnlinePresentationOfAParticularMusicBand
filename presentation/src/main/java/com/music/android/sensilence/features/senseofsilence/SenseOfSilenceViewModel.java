package com.music.android.sensilence.features.senseofsilence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.music.android.sensilence.common.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.github.turskyi.domain.entities.enums.Album;
import io.github.turskyi.domain.entities.pojo.Song;
import io.github.turskyi.domain.usecase.GetSongsUseCase;

@HiltViewModel
public class SenseOfSilenceViewModel extends BaseViewModel {

    private MutableLiveData<List<Song>> _songs;

    public LiveData<List<Song>> getSongs(Album album) {
        if (_songs == null) {
            _songs = new MutableLiveData<>();
            getSongsFromAlbum(album);
        }
        return _songs;
    }

    private MutableLiveData<String> _errorMessage;

    public LiveData<String> getErrorMessage() {
        if (_errorMessage == null) {
            _errorMessage = new MutableLiveData<>();
        }
        return _errorMessage;
    }

    private final GetSongsUseCase useCase;

    @Inject
    SenseOfSilenceViewModel(GetSongsUseCase useCase) {
        this.useCase = useCase;
    }

    private void getSongsFromAlbum(Album album) {
        io.reactivex.rxjava3.disposables.Disposable disposable = useCase.setSongs(
                album.name,
                (List<Song> albumSongs) -> _songs.postValue(albumSongs),
                (String error) -> _errorMessage.postValue(String.valueOf(error))
        );
        compositeDisposable.add(disposable);
    }
}
