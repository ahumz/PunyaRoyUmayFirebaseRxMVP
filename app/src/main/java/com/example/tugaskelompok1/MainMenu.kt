package com.example.tugaskelompok1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tugaskelompok1.MVP_Login.MainMVP
import com.example.tugaskelompok1.Menu.AddTrans
import com.example.tugaskelompok1.Menu.ViewTrans
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MainMenu :  AppCompatActivity(){

    internal lateinit var txt_add : TextView
    internal lateinit var txt_view : TextView
    internal lateinit var txt_logout : TextView

    var fireAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)

        val add : TextView = findViewById(R.id.txt_add)
        val view : TextView = findViewById(R.id.txt_view)
        val logout : TextView = findViewById(R.id.txt_logout)

        val animation = AnimationUtils.loadAnimation(this, R.anim.slide_right)

        add.startAnimation(animation)
        view.startAnimation(animation)
        logout.startAnimation(animation)

//        add.setOnClickListener {
//            startActivity(Intent(this, AddTrans::class.java))
//        }

        txt_add = findViewById<View>(R.id.txt_add) as TextView

        RxView.clicks(txt_add)
            .subscribe {
                val intentLogout = Intent(this, AddTrans::class.java)
                startActivity(intentLogout)

                Toast.makeText(this, "Sok, tambahkeun kak :)", Toast.LENGTH_SHORT).show()
            }

//        view.setOnClickListener {
//            startActivity(Intent(this, ViewTrans::class.java))
//        }

        txt_view = findViewById<View>(R.id.txt_view) as TextView

        RxView.clicks(txt_view)
            .subscribe {
                val intentLogout = Intent(this, ViewTrans::class.java)
                startActivity(intentLogout)

                Toast.makeText(this, "List transaksinya kakak :)", Toast.LENGTH_SHORT).show()
            }

        txt_logout = findViewById<View>(R.id.txt_logout) as TextView

        RxView.clicks(txt_logout)
            .subscribe {
                val intentLogout = Intent(this, MainMVP::class.java)
                startActivity(intentLogout)

                Toast.makeText(this, "Akun Anda Sudah di Logout, kak :)", Toast.LENGTH_SHORT).show()
            }

    }
    fun signOut(){
        fireAuth.signOut()
    }
}