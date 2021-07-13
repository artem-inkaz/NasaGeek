package ui.smartpro.nasageek.mars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.create
import ui.smartpro.nasageek.api.RetrofitModule
import ui.smartpro.nasageek.ui.main.MainViewModel

class MarsViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MarsViewModel::class.java -> MarsViewModel(apiService = RetrofitModule.retrofit.create())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}