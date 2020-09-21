package com.example.materialtutorial

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.materialtutorial.navigation.DialogFragment
import com.example.materialtutorial.navigation.ListFragment
import com.example.materialtutorial.navigation.MapFragment
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeDrawable.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // fragment init
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_layout, DialogFragment()).commitAllowingStateLoss()

        // BottomNavigationView Selected
        val bottomNavigationView = findViewById(R.id.layout_main_bottomNavigationView) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val fr = supportFragmentManager.beginTransaction()
            when(item.itemId){
                R.id.item_dialog -> { fr.replace(R.id.fragment_layout, DialogFragment()).commitAllowingStateLoss()
                    true
                }

                R.id.item_list -> { fr.replace(R.id.fragment_layout, ListFragment()).commitAllowingStateLoss()
                    bottomNavigationView.removeBadge(R.id.item_list)
                    true
                }

                R.id.item_map -> { fr.replace(R.id.fragment_layout, MapFragment()).commitAllowingStateLoss()
                    true
                }

                R.id.item_about -> {
                    MaterialAlertDialogBuilder(this@MainActivity, R.style.CustomMaterialDialog)
                        .setTitle("About 타이틀")
                        .setMessage("About Page 입니다")
                        .setPositiveButton("확인") { dialog, width -> }
                        .show()

                    true
                }
                else -> false
            }
        }

        // Adding badges
        val badge_dialog = bottomNavigationView.getOrCreateBadge(R.id.item_dialog)
        badge_dialog.run {
            isVisible = true
            backgroundColor = Color.RED
        }

        val badge_list : BadgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.item_list)
        badge_list.run {
            number = 12
            badgeGravity = BOTTOM_END
            backgroundColor = Color.RED
        }
    }
}