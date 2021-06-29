package ui.smartpro.nasageek.api

sealed class State{
    class Init : State()
    data class Success(val serverResponseData: NasaModel) : State()
    data class Error(val error: Throwable) : State()
    data class Loading(val progress: Int?) : State()
}
