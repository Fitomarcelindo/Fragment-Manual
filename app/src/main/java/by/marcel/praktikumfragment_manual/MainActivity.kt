package by.marcel.praktikumfragment_manual

import  android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    val marcelhome: Fragment = HomeFragment()
    val marcelinfo: Fragment = InfoFragment()
    val marcel: FragmentManager = supportFragmentManager
    var active: Fragment = marcelhome

    private lateinit var menuItem: MenuItem
    private lateinit var menu: Menu
    private lateinit var btn_view: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_view = findViewById(R.id.btn_view)

        setUpNaviBottom()
    }

    private fun setUpNaviBottom(){

        marcel.beginTransaction().add(R.id.nav_content, marcelhome).hide(marcelhome).commit()
        marcel.beginTransaction().add(R.id.nav_content, marcelinfo).hide(marcelinfo).commit()


        menu = btn_view.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        btn_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navi_home -> {
                    callFrag(0, marcelhome)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_info -> {
                    callFrag(1, marcelinfo)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    private fun callFrag(i: Int, fragment: Fragment) {
        menuItem = menu.getItem(i)
        menuItem.isChecked = true

        marcel.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}


