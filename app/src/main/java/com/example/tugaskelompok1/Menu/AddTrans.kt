package com.example.tugaskelompok1.Menu

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.tugaskelompok1.MainMenu
import com.example.tugaskelompok1.Model.Menus
import com.example.tugaskelompok1.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.add_layout.*
import org.jetbrains.anko.toast

class AddTrans :  AppCompatActivity(), View.OnClickListener {

    private var imgPath: Uri? = null
    lateinit var ref: DatabaseReference
    internal lateinit var btn_menu: Button

    override fun onClick(v: View?) {

        val progressDialog = ProgressDialog(this)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Di Catet Dulu, Pulsanya sekalian kak ?")
        progressDialog.show()

        ref = FirebaseDatabase.getInstance().getReference("images")
        when (v) {
            btn_submit -> {
                val nama = edt_nama.text.toString()
                val harga = edt_harga.text.toString()
                val jumlah = edt_jumlah.text.toString()
                val keterangan = edt_keterangan.text.toString()

                val id = ref.push().key.toString()

                val storageRef = FirebaseStorage
                    .getInstance()
                    .getReference("transaction")
                val databaseRef = FirebaseDatabase
                    .getInstance()
                    .getReference("transaction")


                if (imgPath != null) {
                    storageRef.putFile(imgPath!!)
                        .addOnSuccessListener {
                            storageRef.downloadUrl.addOnSuccessListener {

                                val url = it!!.toString()

                                val isiMenu = Menus(id, nama, harga, jumlah, keterangan, url)
                                databaseRef.child(id).setValue(isiMenu)

                                toast("Data berhasil di tambah, kak :)")

                                progressDialog.hide()
                                finish()
                            }
                        }
                        .addOnFailureListener {
                            println("Info File : ${it.message}")
                            progressDialog.hide()
                        }
                } else {

                    val url = "default"
                    val isiMenu = Menus(id, nama, harga, jumlah, keterangan, url)
                    databaseRef.child(id).setValue(isiMenu)

                    toast("Data berhasil di tambah, kak :)")

                    progressDialog.hide()
                    finish()


                }
            }
            btn_image -> {
                val iImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(iImg, 0)
                progressDialog.hide()

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_layout)

        btn_menu = findViewById<View>(R.id.btn_menu) as Button
        btn_menu.setOnClickListener {
            startActivity(Intent(this, MainMenu::class.java))
        }

        btn_submit.setOnClickListener(this)
        btn_image.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            imgPath = data?.data
        }
    }
}
