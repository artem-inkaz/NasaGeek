package ui.smartpro.nasageek.earth

import android.graphics.Movie
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import kotlinx.android.synthetic.main.earth_fragment.*
import kotlinx.android.synthetic.main.earth_view_holder.*
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.view_pager.*
import ui.smartpro.nasageek.R
import ui.smartpro.nasageek.api.State
import ui.smartpro.nasageek.databinding.EarthFragmentBinding
import ui.smartpro.nasageek.earth.adapter.EarthAdapter
import ui.smartpro.nasageek.earth.api.EarthModel
import ui.smartpro.nasageek.interfaces.OnItemViewClickListener
import ui.smartpro.nasageek.mars.api.MarsModel

class EarthFragment : Fragment() {

    private var _binding: EarthFragmentBinding? = null
    private val binding get() = _binding!!
    private var recycler: RecyclerView? = null
    private var progressBar: ProgressBar? = null

    companion object {
        fun newInstance() = EarthFragment()
    }

    private val viewModel: EarthViewModel by viewModels { EarthViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = EarthFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = binding.earthListRecyclerView
        recycler?.layoutManager = LinearLayoutManager(activity)
        recycler?.adapter = EarthAdapter(earthclickListener)

        scroll_view.setOnScrollChangeListener { _, _, _, _, _ ->
            tabLayout.isSelected = scroll_view.canScrollVertically(-1)
        }

        setObservers()
    }

    override fun onStart() {
        super.onStart()
        viewModel.updateData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setObservers() {
        viewModel.earthFotoLive.observe(viewLifecycleOwner, { earthList ->
            (recycler!!.adapter as EarthAdapter).apply {
                setMars(earthList)
            }
            binding.progressBar?.visibility = View.INVISIBLE
        })

        // observe status
        viewModel.state.observe(viewLifecycleOwner, { status ->
            when (status) {
                is State.Init, is State.Success -> {
                    progressBar?.visibility = View.VISIBLE
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

    private val earthclickListener = object : OnItemViewClickListener {
        override fun onItemViewClick(earthModel: EarthModel) {
        }

        override fun onItemViewClick(marsModel: MarsModel) {
        }
    }

}