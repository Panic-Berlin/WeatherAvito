package com.example.weatheravito.utils

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.asLiveData(): LiveData<T> = this