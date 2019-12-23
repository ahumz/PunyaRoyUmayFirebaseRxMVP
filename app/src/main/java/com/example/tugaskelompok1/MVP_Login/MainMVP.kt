package com.example.tugaskelompok1.MVP_Login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tugaskelompok1.MainMenu
import com.example.tugaskelompok1.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_layout.*
import org.jetbrains.anko.startActivity

class MainMVP  : AppCompatActivity(), MainView{


    private lateinit var presenter: MainPresenter
    private lateinit var button: Button
    lateinit var progress : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        progress = ProgressDialog(this)

        presenter = MainPresenterImp(this)

        button =findViewById(R.id.btn_login)
        button.setOnClickListener {
            val email = txt_username.text.toString()
            val password = txt_password.text.toString()
            progress.isIndeterminate = true
            progress.setMessage("Periksa Dulu, Sabar")
            progress.show()

           presenter.login(email, password)
        }
    }
    override fun intent() {
        startActivity(Intent(this, MainMenu::class.java))
    }

    override fun tampilDialog(pesan: String) {
        progress.setMessage("pesan")
        progress.show()
    }

    override fun hideDialog() {
        progress.hide()
    }

    override fun finish() {
        finish()
    }

    override fun tampilPesan(pesan: String) {
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show()
    }


}