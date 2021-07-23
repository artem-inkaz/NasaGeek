package ui.smartpro.nasageek.wiki

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_wiki.*
import kotlinx.android.synthetic.main.fragment_wiki.input_edit_text
import kotlinx.android.synthetic.main.fragment_wiki.input_layout
import kotlinx.android.synthetic.main.fragment_wiki.ivGalactic
import kotlinx.android.synthetic.main.fragment_wiki.ivMars
import kotlinx.android.synthetic.main.fragment_wiki.ivSolar
import kotlinx.android.synthetic.main.fragment_wiki.ivSpace
import kotlinx.android.synthetic.main.fragment_wiki_end.*
import ui.smartpro.nasageek.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WikiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WikiFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wiki, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        input_layout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${input_edit_text.text.toString()}")
            })
        }

        ivGalactic.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.nasa.gov/image-feature/goddard/2021/hubble-glimpses-a-galactic-duo")
            })
        }
        ivMars.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.nasa.gov/feature/ames/curiosity-rover-finds-patches-of-rock-record-on-mars-erased")
            })
        }
        ivSolar.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.nasa.gov/feature/goddard/2021/operations-underway-to-restore-payload-computer-on-nasas-hubble-space-telescope")
            })
        }
        ivSpace.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.nasa.gov/feature/goddard/2021/nasa-space-lasers-map-meltwater-lakes-in-antarctica-with-striking-precision")
            })
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WikiFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WikiFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}