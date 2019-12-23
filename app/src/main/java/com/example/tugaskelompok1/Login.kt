package com.example.tugaskelompok1

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_layout.*

class Login  : AppCompatActivity() {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

//        val animationDrawable = root_layout.getBackground() as AnimationDrawable
//        animationDrawable.setEnterFadeDuration(2000)
//        animationDrawable.setExitFadeDuration(2000)
//        animationDrawable.start()

        button =findViewById(R.id.btn_login)

        button.setOnClickListener {

            val email = txt_username.text.toString()
            val password = txt_password.text.toString()
            val progressDialog = ProgressDialog(this)
            progressDialog.isIndeterminate = true
            progressDialog.setMessage("Periksa dulu, sabar")
            progressDialog.show()


            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Masukan Username dan Password", Toast.LENGTH_SHORT).show()
                progressDialog.hide()
                return@setOnClickListener

            }else FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {

                    if (it.isSuccessful) {
                            Toast.makeText(this, "Login Sukses", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainMenu::class.java))
                            progressDialog.hide()
                            finish()

                    } else {
                        Toast.makeText(
                            this,
                            "Login GAGAL, Periksa Kembali Username dan Password Anda.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener {
                    Log.e("fungsi login", "login gagal : ${it.message}")
                    Toast.makeText(this, "E-mail / password salah", Toast.LENGTH_SHORT).show()
                    progressDialog.hide()
                }
        }
    }
}