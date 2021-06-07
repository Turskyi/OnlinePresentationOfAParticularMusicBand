package com.music.android.sensilence.presentation.zigmundafraid;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.music.android.sensilence.domain.pojo.Song;
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
        if(_songs == null){
            _songs = new MutableLiveData<>();
        }
        return _songs;
    }

    private final GetSongsUseCase useCase;
    private final SavedStateHandle state;

    @Inject
    ZigmundAfraidViewModel(GetSongsUseCase useCase, SavedStateHandle state) {
        this.useCase = useCase;
        this.state = state;
    }

    void getSongsFromAlbum(String album) {
        Disposable disposable = useCase.getDisposableSongs(album, (List<Song> albumSongs) -> {
            _songs.postValue(albumSongs);
        }, (String error) -> {
            Log.d("===>>>", error);
        });
        compositeDisposable.add(disposable);
    }


}
