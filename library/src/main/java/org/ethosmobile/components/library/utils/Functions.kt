package org.ethosmobile.components.library.utils

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