package org.ethosmobile.components.library.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import java.text.DecimalFormat

/**
 * Function for Formating
 */

fun formatDouble(input: Double): String {
    val decimalFormat = DecimalFormat("#.#####")
    return decimalFormat.format(input)
}

 fun truncateText(text: String): String {
    if (text.length > 19) {
        return text.substring(0, 5) + "..." //+ text.takeLast(3) + " "
    }
    return text
}


fun chainName(chainId: String) = when(chainId) {
    "1" -> "Mainnet"
    "5" -> "Görli"
    "10" -> "Optimism"
    "137" -> "Polygon"
    "8453" -> "Base"
    "42161" -> "Arbitrum"
    "7777777" -> "Zora"
    else -> "Loading..."
}

@Composable
fun isWifiConnected(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
}

fun registerNetworkCallback(context: Context, lifecycleOwner: LifecycleOwner, wifiConnected: MutableState<Boolean>) {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .build()

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: android.net.Network) {
            wifiConnected.value = true
        }

        override fun onLost(network: android.net.Network) {
            wifiConnected.value = false
        }
    }

    // Register callback to listen for Wi-Fi connectivity changes
    connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

    // Make sure to unregister the callback when the lifecycle owner is destroyed
    lifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_DESTROY) {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    })
}