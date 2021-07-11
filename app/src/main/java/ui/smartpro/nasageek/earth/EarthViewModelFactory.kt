package ui.smartpro.nasageek.earth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.create
import ui.smartpro.nasageek.api.RetrofitModule

class EarthViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        EarthViewModel::class.java -> EarthViewModel(apiService = RetrofitModule.retrofit.create())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}