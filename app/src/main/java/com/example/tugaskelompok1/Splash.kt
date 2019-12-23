package com.example.tugaskelompok1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tugaskelompok1.MVP_Login.MainMVP

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        val ivNgopi = findViewById<View>(R.id.logo_splash) as ImageView
        val myanim = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        ivNgopi.startAnimation(myanim)
        val i = Intent(this, MainMVP::class.java)
        val timer = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(i)
                    finish()
                }
            }
        }
        timer.start()
    }
}