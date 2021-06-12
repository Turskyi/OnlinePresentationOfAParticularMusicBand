package com.music.android.sensilence.presentation.zigmundafraid;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.music.android.sensilence.domain.entities.enums.Album;
import com.music.android.sensilence.domain.entities.pojo.Song;
import com.music.android.sensilence.domain.usecase.GetSongsUseCase;
import com.music.android.sensilence.presentation.common.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class ZigmundAfraidViewModel extends BaseViewModel {

    private MutableLiveData<List<Song>> _songs;

    public LiveData<List<Song>> getSongs() {
        if (_songs == null) {
            _songs = new MutableLiveData<>();
            getSongsFromAlbum();
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
    ZigmundAfraidViewModel(GetSongsUseCase useCase) {
        this.useCase = useCase;
    }

    private void getSongsFromAlbum() {
        Disposable disposable = useCase.getDisposableSongs(
                Album.ZIGMUND_AFRAID.name,
                (List<Song> albumSongs) -> _songs.postValue(albumSongs),
                (String error) -> _errorMessage.postValue(error)
        );
        compositeDisposable.add(disposable);
    }

}
