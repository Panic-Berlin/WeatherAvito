package com.example.weatheravito.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatheravito.R
import dagger.hilt.android.AndroidEntryPoint
/**
 * Make in october 2021 by Magomedov Arslan
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
