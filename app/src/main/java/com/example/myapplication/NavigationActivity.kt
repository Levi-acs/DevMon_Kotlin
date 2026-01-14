package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {
    private val navigationViewModel: NavigationViewModel by viewModels()


}
