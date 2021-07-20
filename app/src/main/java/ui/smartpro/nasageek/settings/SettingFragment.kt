package ui.smartpro.nasageek.settings

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.CycleInterpolator
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_setting.*
import ui.smartpro.nasageek.*
import ui.smartpro.nasageek.interfaces.ThemeMode

class SettingFragment : Fragment() {

    private var paramTheme: Int = 0 // параметр стиля темы

    private var saveTheme: ThemeMode? = null

    private var flagTheme: Int? = null

    companion object {

        private const val DURATION = 10000L
        const val BUNDLE_EXTRA_SETING = "fragmentSetting"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        if (arguments?.getInt(BUNDLE_EXTRA_SETING) != null) {
//            flagTheme = arguments?.getInt(BUNDLE_EXTRA_SETING)!!
//            rotater()
//        } else  {
//            flagTheme = 0
//            rotater()
//        }

        flagTheme = AppPreferences.getTheme()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flagTheme = AppPreferences.getTheme()
        Log.d("onCreate", "Наша тема ${flagTheme}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if (arguments?.getInt(BUNDLE_EXTRA_SETING) != null) {
//            flagTheme = arguments?.getInt(BUNDLE_EXTRA_SETING)!!
//            rotater()
//        } else  {
//            flagTheme = 0
//            rotater()
//        }

            flagTheme = AppPreferences.getTheme()
            rotater()


        Log.d("onViewCreated", "Наша тема ${flagTheme}")
        selectTheme(view)
    }

    override fun onStart() {
        super.onStart()
//        if (arguments?.getInt(BUNDLE_EXTRA_SETING) != null) {
//            flagTheme = arguments?.getInt(BUNDLE_EXTRA_SETING)!!
//            rotater()
//        } else  {
//            flagTheme = 0
//            rotater()
//        }

        flagTheme = AppPreferences.getTheme()
        rotater()

        Log.d("onStart", "Наша тема ${flagTheme}")
    }

    private fun selectTheme(view: View) {

        lightTheme.setOnClickListener {
            paramTheme = 0
            flagTheme = 0
            AppPreferences.setTheme(paramTheme)
            requireContext().switchLightMode()
            recreate(requireActivity())
        }

        darkTheme.setOnClickListener {
            paramTheme = 1
            flagTheme = 1
            AppPreferences.setTheme(paramTheme)
            requireContext().switchNightMode()
            recreate(requireActivity())
        }

        marsTheme.setOnClickListener {
            paramTheme = 2
            flagTheme = 2
            AppPreferences.setTheme(paramTheme)
            requireContext().switchMarsMode()
            recreate(requireActivity())

        }

        moonTheme.setOnClickListener {
            paramTheme = 3
            flagTheme = 3
            AppPreferences.setTheme(paramTheme)
            requireContext().switchMoonMode()
            recreate(requireActivity())
        }

        kosmosTheme.setOnClickListener {
            paramTheme = 4
            flagTheme = 4
            AppPreferences.setTheme(paramTheme)
            requireContext().switchKosmosMode()
            recreate(requireActivity())
        }
    }

    private fun rotater() {
//        animator()
        flagTheme?.let {
            // вращение
            objAnimator(it)
            // масштабирование
            scaler(it)
            // цветность
            fader(it)
        }
    }

    private fun animator() {
        val valueAnimator = ValueAnimator.ofFloat(360F)
        animation(imageView, valueAnimator)
    }
    private fun animation(view: View, animator: ValueAnimator) {
        animator.addUpdateListener {
            val value = it.animatedValue as Float
            view.rotation = value
        }
        animator.apply {
            interpolator = CycleInterpolator(1f)
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 2
            duration = DURATION
            start()
        }
    }

    private fun objAnimator(position: Int) {
        when (position) {
            0, null -> {
                objAnimation(ObjectAnimator.ofFloat(lightTheme, View.ROTATION, 360f, 0f))
            }
            1 -> {
                objAnimation(ObjectAnimator.ofFloat(darkTheme, View.ROTATION, 360f, 0f))
            }
            2 -> {
                objAnimation(ObjectAnimator.ofFloat(marsTheme, View.ROTATION, 360f, 0f))
            }
            3 -> {
                objAnimation(ObjectAnimator.ofFloat(moonTheme, View.ROTATION, 360f, 0f))
            }
            4 -> {
                objAnimation(ObjectAnimator.ofFloat(kosmosTheme, View.ROTATION, 360f, 0f))
            }
            else -> {
                objAnimation(ObjectAnimator.ofFloat(lightTheme, View.ROTATION, 360f, 0f))
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun objAnimation(animator: ObjectAnimator) {
        animator.apply {
            interpolator = CycleInterpolator(1f)
            repeatMode = ValueAnimator.INFINITE
            repeatCount = 2
            duration = DURATION
            start()
        }
    }

    private fun objAnimationFader(animator: ObjectAnimator) {
        animator.apply {
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 1
            start()
        }
    }

    private fun scaler(position: Int) {
        // Scale the view up to 1.5x its default size and back
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.5f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.5f)
        when (position) {
            0 -> {
                val animator = ObjectAnimator.ofPropertyValuesHolder(lightTheme, scaleX, scaleY)
                animator.repeatCount = 1
                animator.repeatMode = ObjectAnimator.REVERSE
                animator.start()
            }
            1 -> {
                val animator = ObjectAnimator.ofPropertyValuesHolder(darkTheme, scaleX, scaleY)
                animator.repeatCount = 1
                animator.repeatMode = ObjectAnimator.REVERSE
                animator.start()
            }
            2 -> {
                val animator = ObjectAnimator.ofPropertyValuesHolder(marsTheme, scaleX, scaleY)
                animator.repeatCount = 1
                animator.repeatMode = ObjectAnimator.REVERSE
                animator.start()
            }
            3 -> {
                val animator = ObjectAnimator.ofPropertyValuesHolder(moonTheme, scaleX, scaleY)
                animator.repeatCount = 1
                animator.repeatMode = ObjectAnimator.REVERSE
                animator.start()
            }
            4 -> {
                val animator = ObjectAnimator.ofPropertyValuesHolder(kosmosTheme, scaleX, scaleY)
                animator.repeatCount = 1
                animator.repeatMode = ObjectAnimator.REVERSE
                animator.start()
            }
            else -> {
                val animator = ObjectAnimator.ofPropertyValuesHolder(lightTheme, scaleX, scaleY)
                animator.repeatCount = 1
                animator.repeatMode = ObjectAnimator.REVERSE
                animator.start()
            }
        }
    }

    private fun fader(position: Int) {
        // Fade the view out to completely transparent and then back to completely opaque
        when (position) {
            0 -> {
                val animator = ObjectAnimator.ofFloat(lightTheme, View.ALPHA, 0f)
                animator.repeatCount = 1
                animator.repeatMode = ObjectAnimator.REVERSE
                animator.start()
            }
            1 -> {
                val animator = ObjectAnimator.ofFloat(darkTheme, View.ALPHA, 0f)
                animator.repeatCount = 1
                animator.repeatMode = ObjectAnimator.REVERSE
                animator.start()
            }
            2 -> {
                val animator = ObjectAnimator.ofFloat(marsTheme, View.ALPHA, 0f)
                animator.repeatCount = 1
                animator.repeatMode = ObjectAnimator.REVERSE
                animator.start()
            }
            3 -> {
                val animator = ObjectAnimator.ofFloat(moonTheme, View.ALPHA, 0f)
                animator.repeatCount = 1
                animator.repeatMode = ObjectAnimator.REVERSE
                animator.start()
            }
            4 -> {
                val animator = ObjectAnimator.ofFloat(kosmosTheme, View.ALPHA, 0f)
                animator.repeatCount = 1
                animator.repeatMode = ObjectAnimator.REVERSE
                animator.start()
            }
        }
    }
}