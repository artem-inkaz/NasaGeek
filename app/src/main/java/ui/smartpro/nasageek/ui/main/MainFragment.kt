package ui.smartpro.nasageek.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import ui.smartpro.nasageek.R
import ui.smartpro.nasageek.api.NasaModel
import ui.smartpro.nasageek.api.State
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.main_fragment.*
import ui.smartpro.nasageek.MainActivity
import ui.smartpro.nasageek.settings.SettingFragment
import ui.smartpro.nasageek.wiki.WikiFragment

class MainFragment : Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var progressBar: ProgressBar? = null
    private var nasaModel: NasaModel? = null

    private var url: String? = null
    private var description: String? = null

    private val viewModel: MainViewModel by viewModels { NasaViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
//        setBottomAppBar(view)
        setObservers()
    }

    override fun onStart() {
        super.onStart()
        viewModel.updateData()
    }

    private fun setObservers() {
        // observe Nasa data
        viewModel.nasaFotoOfDay.observe(viewLifecycleOwner, { it ->
            nasaModel = it
            url = it?.url
            description = it?.explanation
        })

        // observe status
        viewModel.state.observe(viewLifecycleOwner, { status ->
            when (status) {
                is State.Init, is State.Success -> {
                    progressBar?.visibility = View.VISIBLE
                    image_view.load(url) {
                        lifecycle(this@MainFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                    }
                    message.text = description
                    progressBar?.visibility = View.INVISIBLE
                }
                is State.Loading -> {
                    progressBar?.visibility = View.VISIBLE
                }
                is State.Error -> {
                    progressBar?.visibility = View.INVISIBLE
                    toast(getString(R.string.errorMassage))
                }
            }
        })
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

    companion object {
        fun newInstance() = MainFragment()
        var isMain = true
    }
}