package com.electrolobzik.starwarsplanetsviewer.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectionCheckerImpl(applicationContext: Context) : ConnectionChecker {
    private val connectivityManager = applicationContext.getSystemService(ConnectivityManager::class.java)

    override fun isNetworkAvailable(): Boolean {
        val network = connectivityManager.activeNetwork

        val result = if (network != null) {
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
        } else {
            false
        }

        return result
    }
}