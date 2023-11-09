package com.example.ethoscomponents.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.ethoscomponents.theme.EthOSTheme

@Composable
fun ethOSListItem(
    headlineContent: @Composable () -> Unit,
    supportingContent: @Composable (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,

    ) {
    ListItem(
        headlineContent = headlineContent,
        supportingContent = supportingContent,
        leadingContent = leadingContent,
        trailingContent = trailingContent
    )

}

@Composable
@Preview
private fun previewEthOsListItem() {
    EthOSTheme {
        ethOSListItem(
            headlineContent = {
                Text("Test")
            },
            supportingContent = {
                Text("support Text")
            }
        )
    }
}