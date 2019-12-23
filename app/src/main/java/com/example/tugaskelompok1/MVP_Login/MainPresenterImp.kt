package com.example.tugaskelompok1.MVP_Login

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.tugaskelompok1.MainMenu
import com.google.firebase.auth.FirebaseAuth

class MainPresenterImp (private val aliong: MainView) : MainPresenter {
    override fun login(email:String, password : String) {
        if(email.isEmpty() || password.isEmpty()){
            aliong.tampilPesan("mohon isi jangan kosong")
            aliong.hideDialog()

        }else FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    aliong.tampilPesan("Login Sukses")
                    aliong.intent()
                    aliong.hideDialog()

                } else {
                    aliong.tampilPesan("Login Gagal, Periksa Kembali Username dan Password Anda.")
                    aliong.hideDialog()
                }
            }
            .addOnFailureListener {
                Log.e("fungsi login", "login gagal : ${it.message}")
                aliong.tampilPesan("Email / Password Salah")
                aliong.hideDialog()
            }
    }

}