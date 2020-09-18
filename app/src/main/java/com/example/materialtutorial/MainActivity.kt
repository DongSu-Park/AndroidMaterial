package com.example.materialtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.example.materialtutorial.navigation.DialogFragment
import com.example.materialtutorial.navigation.ListFragment
import com.example.materialtutorial.navigation.MapFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // header view Binding
        val headerView: View = main_navigationView.getHeaderView(0)

        // navigationView Selected Listener init
        main_navigationView.setNavigationItemSelectedListener(this)

        // fragment init
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_layout, DialogFragment()).commit()

        // toolbar Menu Button Show
        setSupportActionBar(layout_main_toolbar)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_button)
        }

        // profile Image Setting
        val imgProfile: ImageView = headerView.findViewById(R.id.iv_profile) as ImageView
        Glide.with(this)
            .load(R.drawable.profile)
            .circleCrop()
            .into(imgProfile)

        // Header menu selected
        val headerMenu: ImageView = headerView.findViewById(R.id.btn_header_menu) as ImageView
        headerMenu.setOnClickListener { button ->
            val popup = PopupMenu(this, button)
            popup.run {
                menuInflater.inflate(R.menu.header_menu, popup.menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.header_menu_about -> {
                            MaterialAlertDialogBuilder(
                                this@MainActivity,
                                R.style.CustomMaterialDialog
                            )
                                .setTitle("About 타이틀")
                                .setMessage("About Page 입니다")
                                .setPositiveButton("확인") { dialog, width -> }
                                .show()

                            layout_main_drawer.closeDrawers()
                        }
                    }
                    false
                }
                show()
            }
        }
    }

    // Home Button Click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                layout_main_drawer.openDrawer((Gravity.LEFT))
                layout_main_drawer.let{
                    if(it.isDrawerOpen(GravityCompat.START)){
                        it.closeDrawer(GravityCompat.START)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fr = supportFragmentManager.beginTransaction()
        when (item.itemId){
            R.id.item_dialog -> {fr.replace(R.id.fragment_layout, DialogFragment()).commit()
                layout_main_drawer.closeDrawers()}

            R.id.item_list -> {fr.replace(R.id.fragment_layout, ListFragment()).commit()
                layout_main_drawer.closeDrawers()}

            R.id.item_map -> {fr.replace(R.id.fragment_layout, MapFragment()).commit()
                layout_main_drawer.closeDrawers()}
        }
        return false
    }

    override fun onBackPressed() {
        if (layout_main_drawer.isDrawerOpen(GravityCompat.START)){
            layout_main_drawer.closeDrawers()

        } else {
            super.onBackPressed()
        }
    }
}