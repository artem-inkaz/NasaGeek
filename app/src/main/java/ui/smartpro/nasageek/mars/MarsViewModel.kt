package ui.smartpro.nasageek.mars

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ui.smartpro.nasageek.api.State
import ui.smartpro.nasageek.mars.api.MarsApi
import ui.smartpro.nasageek.mars.api.MarsModel
import ui.smartpro.nasageek.mars.api.convertMarsDtoToDomain
import java.lang.Exception

class MarsViewModel(private val apiService: MarsApi) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Init())
    val state: LiveData<State> get() = _state

    private val _mutableLiveDataMars = MutableLiveData<List<MarsModel>>(emptyList())
    val marsFotoLive: LiveData<List<MarsModel>> get() = _mutableLiveDataMars

    var listMars = listOf<MarsModel>()

    fun updateData() {

        viewModelScope.launch {
            try {
                _state.value = State.Loading(null)
                // get Fotos of Mars
                val marsFoto = apiService.getPictureOfMars()
                // get marsFoto domain data
                val marsFotos = convertMarsDtoToDomain(marsFoto.photos)

                listMars = marsFotos

                _mutableLiveDataMars.value = listMars
                _state.value = State.SuccessMars(listMars)

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