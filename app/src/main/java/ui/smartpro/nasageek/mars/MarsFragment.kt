package ui.smartpro.nasageek.mars

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ui.smartpro.nasageek.R

class MarsFragment : Fragment() {

    companion object {
        fun newInstance() = MarsFragment()
    }

    private lateinit var viewModel: MarsViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.mars_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MarsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}