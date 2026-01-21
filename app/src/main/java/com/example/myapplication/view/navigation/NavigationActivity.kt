package com.example.myapplication.view.navigation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.view.creatures.CreatureChooseFragmentDirections
import com.example.myapplication.view.creatures.CreaturesListFragmentDirections
import com.example.myapplication.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModels()

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)

        initNavigation()

        userViewModel.onChooseCreature.observe(this) {
            if (navController.currentDestination?.id == R.id.creature_choose_dest) {
                val action =
                    CreatureChooseFragmentDirections.creatureChooseToCreatureAddedAction(it.number)
                navController.navigate(action)
            }
        }
    }


    private fun initNavigation() {
// Add NavGraph
        val graphInflater = navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)
        navController.graph = navGraph
        if (userViewModel.user.hasCreatureAvailable
            && navController.currentDestination?.id !=
            R.id.creature_choose_dest
        ) {
            val action =
                CreaturesListFragmentDirections.creatureChooseAction()
            navController.navigate(action)
        }
    }
}
