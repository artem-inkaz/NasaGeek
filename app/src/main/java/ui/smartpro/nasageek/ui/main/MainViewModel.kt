package ui.smartpro.nasageek.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ui.smartpro.nasageek.api.NasaApi
import ui.smartpro.nasageek.api.NasaModel
import ui.smartpro.nasageek.api.State
import java.lang.Exception

class MainViewModel(private val apiService: NasaApi) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Init())
    val state: LiveData<State> get() = _state

    private val _mutableLiveDataNasa = MutableLiveData<NasaModel>()
    val nasaFotoOfDay: LiveData<NasaModel> get() = _mutableLiveDataNasa

    fun updateData() {

        viewModelScope.launch {
            try {
                _state.value = State.Loading(null)
                // get Fotos of day
                val nasaFoto = apiService.getPictureOfTheDay()

                _mutableLiveDataNasa.value = nasaFoto
                _state.value = State.Success(nasaFoto)

            } catch (e: Exception) {
                val message = "Ошибка загрузки данных"
                _state.value = State.Error(Throwable(message))
                Log.e(
                    ViewModel::class.java.simpleName,
                    "Error grab foto from Nasa data ${e.message}"
                )
            }
        }
    }
}