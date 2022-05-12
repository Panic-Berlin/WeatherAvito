package com.example.weatheravito.utils

import androidx.lifecycle.LiveData
/**
 * Make in october 2021 by Magomedov Arslan
 */
fun <T> LiveData<T>.asLiveData(): LiveData<T> = this
