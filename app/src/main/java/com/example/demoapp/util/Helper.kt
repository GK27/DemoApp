package com.example.demoapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Patterns

fun isNetworkAvailable(context: Context?): Boolean {
    val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE)
    return if (connectivityManager is ConnectivityManager) {
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        networkInfo?.isConnected ?: false
    } else false
}

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.toRegex().matches(email);
}