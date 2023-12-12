package org.ethosmobile.components.library.models

data class TransferItem(
    val chainId: Int,
    val from: String,
    val to: String,
    val asset: String,
    val value: String,
    val timeStamp: String,
    var userSent: Boolean,
    val txHash: String,
)