package com.example.tugaskelompok1.Menu

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.tugaskelompok1.Model.Menus
import com.example.tugaskelompok1.R
import com.google.firebase.database.FirebaseDatabase

class AdapterTrans (val mCtx: Context, val layoutResId: Int, val list: List<Menus>)
    : ArrayAdapter<Menus>(mCtx,layoutResId,list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View =layoutInflater.inflate(layoutResId, null)

        val textNama = view.findViewById<TextView>(R.id.txt_nama)
        val textHarga = view.findViewById<TextView>(R.id.txt_harga)
        val textJumlah = view.findViewById<TextView>(R.id.txt_jumlah)
        val textKeterangan = view.findViewById<TextView>(R.id.txt_keterangan)
        val btnDelete = view.findViewById<Button>(R.id.btn_delete)
        val viewGambar = view.findViewById<ImageView>(R.id.img_data)

        val menuus = list[position]

        textNama.text = menuus.nama
        textHarga.text = "Rp." + menuus.harga
        textJumlah.text = menuus.jumlah + " Unit"
        textKeterangan.text = menuus.keterangan

        if (menuus.gambar.equals("default")){
            Glide.with(mCtx).load(R.drawable.null1).into(viewGambar)
        }else {
            Glide.with(mCtx).load(menuus.gambar).into(viewGambar)
        }

        btnDelete.setOnClickListener {
            deleteInfo(menuus)
        }

        return view
    }

    private fun deleteInfo(menuus: Menus) {
        val progressDialog = ProgressDialog(context,R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Menghapus...")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("transaction")
        mydatabase.child(menuus.id).removeValue()
        Toast.makeText(mCtx, "Data berhasil di hapus, kak :)", Toast.LENGTH_SHORT).show()
        val habisdelete = Intent(context, ViewTrans::class.java)
        context.startActivity(habisdelete)
    }
}