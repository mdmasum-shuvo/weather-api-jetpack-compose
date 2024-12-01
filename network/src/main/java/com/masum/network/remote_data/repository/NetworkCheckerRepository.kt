package com.masum.network.remote_data.repository

import com.masum.network.util.NetworkCheckerUtils

class NetworkCheckerRepository(private val networkUtils: NetworkCheckerUtils) {
    fun checkNetworkStatus(): Boolean {
        return networkUtils.isNetworkAvailable()
    }
}
