package ui.smartpro.nasageek.earth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ui.smartpro.nasageek.api.State
import ui.smartpro.nasageek.earth.api.EarthApi
import ui.smartpro.nasageek.earth.api.EarthModel
import ui.smartpro.nasageek.earth.api.convertEarthDtoToDomain
import java.lang.Exception

class EarthViewModel(private val apiService: EarthApi) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Init())
    val state: LiveData<State> get() = _state

    private val _mutableLiveDataEarth = MutableLiveData<List<EarthModel>>(emptyList())
    val earthFotoLive: LiveData<List<EarthModel>> get() = _mutableLiveDataEarth

    var listEarth = listOf<EarthModel>()

    fun updateData() {

        viewModelScope.launch {
            try {
                _state.value = State.Loading(null)
                // get Fotos of Mars
                val earthFoto = apiService.getPictureOfEarth()
                // get marsFoto domain data
                val earthFotos = convertEarthDtoToDomain(earthFoto)

//                listEarth = earthFoto
                listEarth = earthFotos

                _mutableLiveDataEarth.value = listEarth
                _state.value = State.SuccessEarth(listEarth)

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