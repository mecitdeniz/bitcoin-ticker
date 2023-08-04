package com.mecitdeniz.bitcointicker.presentation.components

import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


enum class MaterialCardVariant {
    PRIMARY,
    SECONDARY,
    TERTIARY,
    DEFAULT
}

@Composable
fun MaterialCard(
    variant: MaterialCardVariant,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val cardColors = when (variant) {
        MaterialCardVariant.PRIMARY -> CardColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(
                alpha = .8f
            ),
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = .8f
            ),
        )
        MaterialCardVariant.SECONDARY -> CardColors(
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                alpha = .8f
            ),
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(
                alpha = .8f
            ),
        )
        MaterialCardVariant.TERTIARY -> CardColors(
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(
                alpha = .8f
            ),
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(
                alpha = .8f
            ),
        )
        MaterialCardVariant.DEFAULT -> CardDefaults.cardColors()
    }
    Card(
        modifier = modifier,
        colors = cardColors
    ) {
        content()
    }
}