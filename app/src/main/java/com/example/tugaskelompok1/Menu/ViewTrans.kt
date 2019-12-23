package com.example.tugaskelompok1.Menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.tugaskelompok1.MainMenu
import com.example.tugaskelompok1.Model.Menus
import com.example.tugaskelompok1.R
import com.google.firebase.database.*

class ViewTrans :  AppCompatActivity(){

    internal lateinit var btn_menu_view : Button
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Menus>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_layout)

        btn_menu_view = findViewById<View>(R.id.btn_menu_view) as Button
        btn_menu_view.setOnClickListener {
            startActivity(Intent(this, MainMenu::class.java))
        }

        ref = FirebaseDatabase.getInstance().getReference("transaction")
        list = mutableListOf()
        listView = findViewById(R.id.lv_trans)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    list.clear()
                    for (h in p0.children){
                        val user = h.getValue(Menus::class.java)
                        list.add(user!!)
                    }
                    val adapter = AdapterTrans(this@ViewTrans,R.layout.item_layout,list)
                    listView.adapter = adapter
                }
            }
        })
    }
}