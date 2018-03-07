package ch.whattowat.android.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ch.whattowat.api.model.Film
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    private var _film: MutableLiveData<Film>? = null
    val film: LiveData<Film>
        get() {
            if (_film == null) {
                _film = MutableLiveData()
                getFilm()
            }

            return _film ?: throw AssertionError("Set to null by another thread")
        }

    private fun getFilm() {
        Thread(this::doInBackground).start()
    }

    private fun doInBackground() {
        Thread.sleep(3000)
        val film = Film("Sample title", 1988)
        _film?.postValue(film)
    }
}