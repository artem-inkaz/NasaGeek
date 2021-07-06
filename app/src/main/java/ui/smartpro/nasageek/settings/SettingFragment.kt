package ui.smartpro.nasageek.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_setting.*
import ui.smartpro.nasageek.*
import ui.smartpro.nasageek.interfaces.ThemeMode

class SettingFragment : Fragment() {

    private var paramTheme:Int = 0 // параметр стиля темы

    private var saveTheme: ThemeMode? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectTheme(view)
    }

    private fun selectTheme(view: View) {

        lightTheme.setOnClickListener {
          paramTheme =0
          AppPreferences.setTheme(paramTheme)
            requireContext().switchLightMode()
            recreate(requireActivity())
      }

        darkTheme.setOnClickListener {
            paramTheme =1
            AppPreferences.setTheme(paramTheme)
            requireContext().switchNightMode()
            recreate(requireActivity())
        }

        marsTheme.setOnClickListener {
            paramTheme =2

            AppPreferences.setTheme(paramTheme)
            requireContext().switchMarsMode()
            recreate(requireActivity())
        }

        moonTheme.setOnClickListener {
            paramTheme =3
            AppPreferences.setTheme(paramTheme)
            requireContext().switchMoonMode()
            recreate(requireActivity())
        }

        kosmosTheme.setOnClickListener {
            paramTheme =4
            AppPreferences.setTheme(paramTheme)
           requireContext().switchKosmosMode()
            recreate(requireActivity())
        }

    }
}