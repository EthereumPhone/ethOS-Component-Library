package org.ethosmobile.components.library.models

data class TokenAsset(
    val address: String,
    val chainId: Int,
    val symbol: String,
    val name: String,
    val balance: Double,
    val decimals: Int = 0,
    val swappable: Boolean = false
)