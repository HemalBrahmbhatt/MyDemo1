package com.example.mydemo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.RelativeLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var actionbar: ActionBarDrawerToggle
    private lateinit var nav: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav = findViewById(R.id.nav_real)
        drawer = findViewById(R.id.nav_menu)
        actionbar = ActionBarDrawerToggle(this, drawer, R.string.nav_open, R.string.nav_close)
        drawer.addDrawerListener(actionbar)
        actionbar.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav.setNavigationItemSelectedListener(this)
        val lin = findViewById<RelativeLayout>(R.id.rel_lay)
        lin.setOnClickListener {
            drawer.close()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionbar.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val i1: Intent
        when (item.itemId) {
            R.id.nav_i_home -> drawer.close()
            R.id.nav_i_notification -> {
                i1 = Intent(this, NotificationActivity::class.java)
                startActivity(i1)
            }
            R.id.nav_i_rv -> {
                i1 = Intent(this, ReViewActivity2::class.java)
                startActivity(i1)
            }
            R.id.nav_i_dt -> {
                i1 = Intent(this, DateAndTimeActivity::class.java)
                startActivity(i1)
            }
        }
        return true
    }


}