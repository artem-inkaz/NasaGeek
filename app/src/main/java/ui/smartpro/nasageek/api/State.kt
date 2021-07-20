package ui.smartpro.nasageek.api

import ui.smartpro.nasageek.earth.api.EarthApiDto
import ui.smartpro.nasageek.earth.api.EarthModel
import ui.smartpro.nasageek.mars.api.MarsModel

sealed class State{
    class Init : State()
    data class Success(val serverResponseData: NasaModel) : State()
    data class SuccessMars(val serverResponseData: List<MarsModel>) : State()
    data class SuccessEarth(val serverResponseData: List<EarthModel>) : State()
    data class Error(val error: Throwable) : State()
    data class Loading(val progress: Int?) : State()
}
