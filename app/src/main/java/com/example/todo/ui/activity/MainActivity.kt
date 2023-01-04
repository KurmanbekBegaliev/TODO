package com.example.todo.ui.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todo.R
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.ui.App

class MainActivity : AppCompatActivity() {

    private lateinit var controller: NavController
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(100)
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavController()
        setAppBarConfiguration()
        isBoardShowed()
    }



    private fun initNavController() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        controller = navHostFragment.navController
    }

    //Shared Preferences
    private fun isBoardShowed() {
         if (!App.prefs.isBoardShow()) {
            controller.navigate(R.id.onBoardFragment)
        }
    }


    private fun setAppBarConfiguration() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.noteFragment,
                R.id.contactFragment,
                R.id.profileFragment
            )
        )
        setSupportActionBar(binding.toolbar)
        binding.toolbar.isGone = true

        setupActionBarWithNavController(controller, appBarConfiguration)
        binding.bottomNav.setupWithNavController(controller)

        controller.addOnDestinationChangedListener{_, destination, _ ->
            val list : ArrayList<Int> = arrayListOf()
            list.add(R.id.noteFragment)
            list.add(R.id.contactFragment)
            list.add(R.id.profileFragment)

            if (list.contains(destination.id)) {
                binding.bottomNav.isVisible = true
            } else {
                binding.bottomNav.isGone = true
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

    }



}