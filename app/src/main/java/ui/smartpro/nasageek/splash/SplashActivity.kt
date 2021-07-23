package ui.smartpro.nasageek.splash

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.CycleInterpolator
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.fragment_setting.*
import ui.smartpro.nasageek.MainActivity
import ui.smartpro.nasageek.R

class SplashActivity : AppCompatActivity() {

    var handler = Handler(Looper.getMainLooper())

    companion object {

        private const val DURATION = 10000L

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        ellipseBackground.animate().rotationBy(180f)
            .setInterpolator(AccelerateDecelerateInterpolator()).setDuration(2750)
            .setListener(object : Animator.AnimatorListener {

                override fun onAnimationEnd(animation: Animator?) {
//                   startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//                   finish()
                }

                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationStart(animation: Animator?) {}
            })

        objAnimation(ObjectAnimator.ofFloat(textLogo, View.ALPHA, 0.5f))
        objAnimation(ObjectAnimator.ofFloat(redStar, View.ALPHA, 0f))
        scaler(1.2f)
        fader()

        handler.postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 11000)

    }

    private fun scaler(f: Float) {

        // Scale the view up to 4x its default size and back
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, f)
        objAnimation(ObjectAnimator.ofPropertyValuesHolder(ellipsewhite, scaleX, scaleY))

    }

    private fun fader() {
//        // Fade the view out to completely transparent and then back to completely opaque
        objAnimation(ObjectAnimator.ofFloat(ellipse1, View.ALPHA, 0f))
        objAnimation(ObjectAnimator.ofFloat(ellipse2, View.ALPHA, 1f))
        objAnimation(ObjectAnimator.ofFloat(ellipse3, View.ALPHA, 1.4f))
        objAnimation(ObjectAnimator.ofFloat(ellipse4, View.ALPHA, 2f))
        objAnimation(ObjectAnimator.ofFloat(ellipse5, View.ALPHA, 1.4f))
        objAnimation(ObjectAnimator.ofFloat(ellipse6, View.ALPHA, 2f))
        objAnimation(ObjectAnimator.ofFloat(ellipse7, View.ALPHA, 1f))

        objAnimation(ObjectAnimator.ofFloat(star1, View.ALPHA, 0f))
        objAnimation(ObjectAnimator.ofFloat(star2, View.ALPHA, 1f))
        objAnimation(ObjectAnimator.ofFloat(star3, View.ALPHA, 1.5f))
        objAnimation(ObjectAnimator.ofFloat(star4, View.ALPHA, 1.7f))
        objAnimation(ObjectAnimator.ofFloat(star5, View.ALPHA, 2f))
    }

    @SuppressLint("WrongConstant")
    private fun objAnimation(animator: ObjectAnimator) {
        animator.apply {
            interpolator = CycleInterpolator(1f)
            repeatMode = ValueAnimator.INFINITE
            repeatCount = 1
            duration = DURATION
            start()
        }
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}