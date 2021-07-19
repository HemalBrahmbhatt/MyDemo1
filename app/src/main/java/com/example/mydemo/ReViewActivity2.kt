package com.example.mydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReViewActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_re_view2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val iList = generateDummyList()
        val rv = findViewById<RecyclerView>(R.id.rec_view)
        val ad = ItemAdapter(iList)
        rv.adapter= ad
        rv.layoutManager=LinearLayoutManager(this)
        rv.setHasFixedSize(true)

        val btnAdd = findViewById<FloatingActionButton>(R.id.fab_add)
        val btnRemove = findViewById<FloatingActionButton>(R.id.fab_remove)

        btnAdd.setOnClickListener {
            if(iList.size<61){
                val newItem = ReItem(
                    R.drawable.ic_baseline_add,"New One","you Just added New Item"
                )
                iList.add(0,newItem)
                ad.notifyItemInserted(0)
            }
            else{
                Toast.makeText(this,"Ok, That's Enough",Toast.LENGTH_LONG).show()
            }

        }
        btnRemove.setOnClickListener {
            if(iList.size>0){
                iList.removeAt(0)
                ad.notifyItemRemoved(0)
            }
            else{
                Toast.makeText(this,"What Exactly Do You Wanna Remove ? There is NOTHING here",Toast.LENGTH_LONG).show()
            }

        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
    private fun generateDummyList(): ArrayList<ReItem> {
        val list = ArrayList<ReItem>()
        for (i in 0 until 11) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_home
                1 -> R.drawable.ic_notifications
                else -> R.drawable.ic_notes
            }
            val h = when (i % 3) {
                0 -> "Home"
                1 -> "Notification"
                else -> "Recycler View"
            }
            val item = ReItem(drawable, h,getString(R.string.app_name)+": $i")
            list += item
        }
        return list
    }
}