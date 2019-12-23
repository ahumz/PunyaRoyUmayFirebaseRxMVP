package com.example.tugaskelompok1.MVP_Login

import android.app.Activity
import android.content.Context

interface MainView {

    //toast
    fun tampilPesan(pesan : String)
    //progressdialog
    fun tampilDialog(pesan: String)
    fun hideDialog()

    //intent
    fun intent()
    //finish
    fun finish()
}