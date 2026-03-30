package com.example.startupmentor

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.startupmentor.data.PreferenceManager
import com.example.startupmentor.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Animation
        val scaleX = ObjectAnimator.ofFloat(binding.ivLogo, View.SCALE_X, 0.5f, 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.ivLogo, View.SCALE_Y, 0.5f, 1f)
        val alpha = ObjectAnimator.ofFloat(binding.ivLogo, View.ALPHA, 0f, 1f)
        val textAlpha = ObjectAnimator.ofFloat(binding.tvAppName, View.ALPHA, 0f, 1f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY, alpha, textAlpha)
        animatorSet.duration = 1500
        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.start()

        val prefManager = PreferenceManager(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (prefManager.isLoggedIn()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 2000)
    }
}